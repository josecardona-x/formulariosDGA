/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.aduana.form;

import io.swagger.model.Login;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.UserTransaction;
import sv.gob.aduana.entity.Flow;
import sv.gob.aduana.entity.Request;
import sv.gob.aduana.entity.Requestflow;
import sv.gob.aduana.mdb.FormQueue;
import sv.gob.aduana.mtto.bean.EventLogFacade;
import sv.gob.aduana.mtto.bean.FlowFacade;
import sv.gob.aduana.mtto.bean.FormuFacade;
import sv.gob.aduana.mtto.bean.GeneratorIdFacade;
import sv.gob.aduana.mtto.bean.RequestFacade;
import sv.gob.aduana.mtto.bean.RequestflowFacade;
import sv.gob.mh.oim.OIMService;
import sv.gob.mh.oim.pojo.Email;
import sv.gob.mh.oim.pojo.MessageQueue;

/**
 *
 * @author Datum-Redsoft
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class FlowService {

    @Resource
    private UserTransaction utx;

    @EJB
    protected GeneratorIdFacade generatorIdFacade;

    @EJB
    protected RequestFacade requestFacade;

    @EJB
    protected FormuFacade formuFacade;

    @EJB
    protected FlowFacade flowFacade;

    @EJB
    protected RequestflowFacade requestflowFacade;

    @EJB
    protected OIMService oimService;

    @EJB
    protected FormService formWrapper;

    @EJB
    protected EventLogFacade eventLogFacade;

    public void createFlow(String requestId) throws Exception {
        utx.begin();

        //Obtener el formulario
        sv.gob.aduana.entity.Request request = requestFacade.find(requestId);
        sv.gob.aduana.entity.Formu form = formuFacade.find(request.getForm().getId());
        form.setRequestCollection(requestFacade.getRequestsById(request.getForm().getId()));

        String processCode = null;
        Boolean sameRequesterToApplicant = false;
        //Extraer el tipo de formulario
        String typeForm = form.getFormType();
        String userCretedBy = form.getCreateby();

        //Extraer si es para un externo de AFPA
        Boolean externoAFPA = false;

        if (form.getApplicant().getExternalname() != null) {
            externoAFPA = true;
        }

        io.swagger.model.Applicant applicant = FormTranslator.dbToAPI(form.getApplicant());
        io.swagger.model.Person requesterPerson = oimService.loadUser(applicant.getDocument(), "");
      
        io.swagger.model.Applicant requester = oimService.getApplicant(userCretedBy);
        

        if (form.getApplicant().getExternalname() != null) {
            sameRequesterToApplicant = false;
        } else {
            if (requester.getDocument().equals(applicant.getDocument())) {
                sameRequesterToApplicant = true;
            }
        }

        processCode = selectFlow(typeForm, externoAFPA, processCode, sameRequesterToApplicant);

        eventLogFacade.insertInAudit(form.getId(), "", "El proceso que se seguir es: " + processCode, "WORKFLOW", userCretedBy);

        List<sv.gob.aduana.entity.Flow> flows = flowFacade.getFlowByProcess(processCode);

        sv.gob.aduana.entity.GeneratorId gen = generatorIdFacade.find("FLW");

        form.setHashCode(UUID.randomUUID().toString());

        form.getRequestCollection().forEach((req) -> {

            for (sv.gob.aduana.entity.Flow f : flows) {
                sv.gob.aduana.entity.Requestflow rf = new Requestflow();
                rf.setComment("");

                if (form.getApplicant().getExternalname() != null) {
                    rf.setDeveloperemail(applicant.getMail());
                } else {
                    rf.setDeveloperemail(requester.getMail());
                }

                rf.setRequesteremail(applicant.getMail());

                gen.setCounter(gen.getCounter() + 1);

                rf.setId("REQ-FLO" + gen.getCounter());
                rf.setEnddate(null);
                rf.setFlow(f);
                rf.setRequest(req);
                if (f.getStep() == 1) {
                    rf.setStartdate(new Date());
                    rf.setStatus("PENDING");

                    form.setStep(f.getNameStep());
                    form.setStepisrole(f.getSendtorole());

                    try {
                        form.setApplicantviewer(requesterPerson.getUid());
                    } catch (Exception e) {
                    }

                    form.setRoleStep(f.getRole());

                    formuFacade.edit(form);

                    req.setHashcode(UUID.randomUUID().toString());
                    req.setCurrentFlowId(rf.getId());

                    requestFacade.edit(req);

                } else {
                    rf.setStartdate(null);
                    rf.setStatus("NOTSTARTED");
                }
                rf.setUser(null);

                requestflowFacade.create(rf);
            }
        });

        generatorIdFacade.edit(gen);

        utx.commit();
    }

    public String selectFlow(String typeForm, Boolean externoAFPA, String processCode, Boolean sameRequesterToApplicant) {
        if (RequestTypeService.isExtern(typeForm)) {
            if (externoAFPA) {
                processCode = "EXTERN_AFPA";
            } else {
                if (sameRequesterToApplicant) {
                    processCode = "AFPA_QUICK";
                } else {
                    processCode = "AFPA";
                }
            }
        }
        if (RequestTypeService.isIntern(typeForm)) {
            if (sameRequesterToApplicant) {
                processCode = "INTERN_QUICK";
            } else {
                processCode = "INTERN";
            }
        }
        return processCode;
    }

    //Todos los pasos son los mismos al llegar a este punto 
    public void sendStep(String requestId) {
        //Cargar Formulario
        sv.gob.aduana.entity.Request request = requestFacade.find(requestId);
        //Cargar el Paso actual
        Boolean existRequestAvailable = false;

        sv.gob.aduana.entity.Formu form = formWrapper.getFormAll(request.getForm().getId());

        sv.gob.aduana.entity.Requestflow requestflow = null;
        String title = null;

        //Obtener el paso actual
        for (sv.gob.aduana.entity.Requestflow rf : form.getRequestCollection().get(0).getRequestflowCollection()) {
            if (rf.getStatus().equals("PENDING")) {
                requestflow = rf;
                break;
            }
        }

        //Cargar reglas
        sv.gob.aduana.entity.Flow flow = flowFacade.find(requestflow.getFlow().getId());

        String template = readAllFile("template.html");

        if (RequestTypeService.isIntern(form.getFormType())) {
            template = template.replaceAll("\\{title\\}", "Solicitud de servicios informaticos para accesos de usuario interno");
        } else if (RequestTypeService.isExtern(form.getFormType())) {
            template = template.replaceAll("\\{title\\}", "Solicitud de servicios informaticos para accesos de usuario externo");
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("<table style='margin:0 30px !important;'>");
        strBuilder.append("<tr><td><strong>ID: </strong></td><td>");
        strBuilder.append(form.getId());
        strBuilder.append("</tr><tr><td><strong>Solicitante:</strong></td><td>");
        strBuilder.append(form.getApplicant().getName());
        strBuilder.append("</tr><tr><td><strong>Creado en: </strong></td><td>");
        strBuilder.append(simpleDateFormat.format(form.getCreatedon()));
        strBuilder.append("</td></tr>");
        strBuilder.append("</table>");

        template = template.replaceAll("\\{solicitanteTabla\\}", strBuilder.toString());
        strBuilder.delete(0, strBuilder.length());

        strBuilder.append("<table  style='margin:0 30px !important;'>");

        if (RequestTypeService.isIntern(form.getFormType())) {
            strBuilder.append("<tr><td><strong>Cargo:</strong> </td><td>");
            strBuilder.append(form.getApplicant().getPosition().getName());
            strBuilder.append("</td></tr><tr><td><strong>Email: </strong></td><td>");
            strBuilder.append(form.getApplicant().getMail());
            strBuilder.append("</td></tr><tr><td><strong>Documento: </strong></td><td>");
            strBuilder.append(form.getApplicant().getDocument());
            strBuilder.append("</td></tr>");

        } else if (RequestTypeService.isExtern(form.getFormType())) {

            strBuilder.append("<tr><td>Cargo: </td><td>");
            strBuilder.append(form.getApplicant().getPosition().getName());
            strBuilder.append("</td></tr><tr><td><strong>Email: </strong></td><td>");
            strBuilder.append(form.getApplicant().getMail());
            strBuilder.append("</td></tr><tr><td><strong>Documento: </strong></td><td>");
            strBuilder.append(form.getApplicant().getDocument());
            strBuilder.append("</td></tr>");
        }
        strBuilder.append("</table>");

        strBuilder.append("<br/><br/>");
        strBuilder.append("Estado: ");
        strBuilder.append(form.getStatus());
        strBuilder.append("<br/><br/>");
        strBuilder.append("Observaciones: ");
        if (form.getComment() != null) {
            strBuilder.append(form.getComment());
        }

        template = template.replaceAll("\\{detalleSolicitanteTabla\\} ", strBuilder.toString());
        strBuilder.delete(0, strBuilder.length());

        for (sv.gob.aduana.entity.Request r : form.getRequestCollection()) {

            if (r.getState().equals("REJECT")) {
                continue;
            }

            existRequestAvailable = true;

            strBuilder.append("<h4><strong>Solicitud : ");
            strBuilder.append(r.getId());
            strBuilder.append("</h4></strong><br/><br/>");
            strBuilder.append("<table width='100%'>");
            strBuilder.append("<tr>");
            strBuilder.append("<td><strong>Tipo de Servicio: </strong></td><td>");
            strBuilder.append(r.getTyperequest().getName());
            if (r.getPerson().getUserType() != null) {
                strBuilder.append("</td></tr><tr><td><strong>Tipo de usuario: </strong></td><td>");
                strBuilder.append(r.getPerson().getUserType());
            }
            strBuilder.append("</td></tr><tr><td><strong>Cuenta de usuario: </strong></td><td>");
            strBuilder.append(r.getPerson().getLogin());
            strBuilder.append("</td></tr><tr><td><strong>Nombre completo: </strong></td><td>");
            strBuilder.append(r.getPerson().getFullname());
            strBuilder.append("</td></tr><tr><td><strong>Correo: </strong></td><td>");
            strBuilder.append(r.getPerson().getMail());
            strBuilder.append("</td></tr><tr><td><strong>Documento: </strong></td><td>");
            strBuilder.append(r.getPerson().getDocument());
            strBuilder.append("</td></tr><tr><td><strong>Estado: </strong></td><td>");
            strBuilder.append(r.getState());

            if (RequestTypeService.isExtern(form.getFormType())) {
                if (r.getPerson().getCodedeclarant() != null) {
                    strBuilder.append("<br/>Tipo AFPA: ");
                    strBuilder.append(r.getPerson().getCatTypeafpaid().getName());
                    strBuilder.append("<br/>Estado: ");
                    strBuilder.append(r.getPerson().getCodedeclarant());
                    strBuilder.append("<br/>Resoluci�n: ");
                    strBuilder.append(r.getPerson().getResolution());
                }
            } else {
                strBuilder.append("<br/>Nivel 1: ");
                strBuilder.append(r.getPerson().getLevelone().getName());
                strBuilder.append("<br/>Nivel 2: ");
                strBuilder.append(r.getPerson().getLeveltwo().getName());
                strBuilder.append("<br/>Nivel 3: ");
                strBuilder.append(r.getPerson().getLevelthree().getName());
                strBuilder.append("<br/>Nivel 4: ");
                strBuilder.append(r.getPerson().getLevelfour().getName());
            }

            strBuilder.append("</td></tr></table><br/>");

            if (!RequestTypeService.isRequestWithPermissionsFilled(r.getTyperequest().getId())) {
                strBuilder.append("<table width='100%'>");
                strBuilder.append("<tr><td  width='33%'><strong>Perfiles</strong></td><td width='33%'><strong>Sistemas</strong></td><td width='33%'><strong>Otros permisos</strong></td></tr>");
                strBuilder.append("<tr><td>");
                if (r.getProfileCollection().size() > 0) {
                    for (sv.gob.aduana.entity.Profile p : r.getProfileCollection()) {
                        strBuilder.append("<br/><br/>Perfil: ");
                        strBuilder.append(p.getProfile().getName());
                        strBuilder.append("<br/><br/>Acci�n: ");
                        strBuilder.append(p.getStatus());
                        strBuilder.append("<br/><br/>Es temporal: ");
                        strBuilder.append(p.getTemporal() == 1 ? "SI" : "NO");
                        if (p.getTemporal() == 1) {
                            strBuilder.append("<br/> Fecha fin:");
                            strBuilder.append(simpleDateFormat.format(p.getEnddate()));
                        }
                        strBuilder.append("<br/><br/>Aduanas: ");
                        strBuilder.append(p.getCustom());
                    }
                }

                strBuilder.append("</td><td>");

                if (r.getSystemCollection().size() > 0) {
                    for (sv.gob.aduana.entity.System s : r.getSystemCollection()) {
                        strBuilder.append("<br/><br/>Sistema: ");
                        strBuilder.append(s.getGroup1().getSystem().getName());
                        strBuilder.append("<br/><br/>Grupo: ");
                        strBuilder.append(s.getGroup1().getFriendlyname());
                        strBuilder.append("<br/><br/>Acci�n: ");
                        strBuilder.append(s.getStatus());
                        strBuilder.append("<br/><br/>Es temporal: ");
                        strBuilder.append(s.getTemporal() == 1 ? "SI" : "NO");
                        if (s.getTemporal() == 1) {
                            strBuilder.append("<br/> Fecha fin:");
                            strBuilder.append(simpleDateFormat.format(s.getEnddate()));
                        }
                        strBuilder.append("<br/><br/>Aduanas: ");
                        strBuilder.append(s.getCustom());
                    }
                }

                strBuilder.append("</td><td>");

                if (r.getOtherCollection().size() > 0) {
                    for (sv.gob.aduana.entity.Other o : r.getOtherCollection()) {
                        strBuilder.append("<br/><br/>Plataforma: ");
                        strBuilder.append(o.getPlatform());
                        strBuilder.append("<br/><br/>Informaci�n: ");

                        if (o.getData() != null) {
                            strBuilder.append(o.getData());
                        }

                        strBuilder.append("<br/><br/>Acci�n: ");
                        strBuilder.append(o.getStatus());
                        strBuilder.append("<br/><br/>Es temporal: ");
                        strBuilder.append(o.getTemporal() == 1 ? "SI" : "NO");
                        if (o.getTemporal() == 1) {
                            strBuilder.append("<br/> Fecha fin:");
                            strBuilder.append(simpleDateFormat.format(o.getEnddate()));
                        }
                    }
                }

                strBuilder.append("</td></tr></table><br/>");

            } else {
                strBuilder.append("<table width='100%'>");
                strBuilder.append("<tr>");
                strBuilder.append("<td><strong>Borrar todos los grupos: </strong></td><td>");
                strBuilder.append(r.getDeleteAllGroup() == 0 ? "NO" : "SI");
                strBuilder.append("</td></tr><tr><td><strong>Mover a desactivados: </strong></td><td>");
                strBuilder.append(r.getMoveToDesactive() == 0 ? "NO" : "SI");
                strBuilder.append("</td></tr></table><br/>");

            }

            strBuilder.append("<h4><strong>Acciones</strong></h4>");
            strBuilder.append("<a class='button' href='");
            if (System.getProperty("custom.enviroment.oim").equals("TEST")) {
                strBuilder.append("http://oim-test.mh.gob.sv/custom-app/dga/form/ApproveByMail?hashcode=");
                title = "[TEST] - Solicitud de servicios inform�ticos para accesos - " + form.getId() + " - " + form.getStep();
            } else {
                strBuilder.append("http://identidad.mh.gob.sv/custom-app/dga/form/ApproveByMail?hashcode=");
                title = "Solicitud de servicios inform�ticos para accesos - " + form.getId() + " - " + form.getStep();
            }
            strBuilder.append(r.getHashcode());
            strBuilder.append("&formId=");
            strBuilder.append(form.getId());
            strBuilder.append("&action=approve' class='button'>Ir al formulario</a>");
            /*strBuilder.append(" | <a href='");
            if (System.getProperty("custom.enviroment.oim").equals("TEST")) {
                strBuilder.append("http://oim-test.mh.gob.sv/custom-app/dga/form/ApproveByMail?hashcode=");
            } else {
                strBuilder.append("http://identidad.mh.gob.sv/custom-app/dga/form/ApproveByMail?hashcode=");
            }
            strBuilder.append(r.getHashcode());
            strBuilder.append("&formId=");
            strBuilder.append(form.getId());
            strBuilder.append("&action=reject'>Rechazar</a>");*/

            strBuilder.append("<br/><hr/><br/>");
        }

        template = template.replaceAll("\\{solicitudes\\}", strBuilder.toString());
        Email email = new Email();
        String emails = null;

        if (existRequestAvailable) {
            email.setMessage(template);
            email.setTitle(title);

            if (flow.getSendtorole() == 0) {
                email.setTo(requestflow.getRequesteremail());
                oimService.sendMail(email);
            } else {
                emails = getEmailsStep(flow.getRole());

                if (emails.contains(",")) {
                    String[] emailsArray = emails.split(",");

                    for (String emailsArray1 : emailsArray) {
                        email.setTo(emailsArray1);
                        oimService.sendMail(email);
                    }

                } else {
                    email.setTo(emails);
                    oimService.sendMail(email);
                }

            }
        } else {

            email.setMessage("Todos las solicitudes de esta petici�n fueron rechazados, favor verifique en el portal para verificar el detalle.");
            email.setTitle("Formulario rechazado - " + form.getId());
            email.setTo(requestflow.getDeveloperemail());
            oimService.sendMail(email);

        }

    }

    private String readAllFile(String filePath) {
        String content = "";
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();

            InputStream is = classloader.getResourceAsStream(filePath);

            try (InputStreamReader streamReader
                    = new InputStreamReader(is, StandardCharsets.UTF_8);
                    BufferedReader reader = new BufferedReader(streamReader)) {

                String line;
                while ((line = reader.readLine()) != null) {
                    content += line;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return content;

    }

    public String addStep(io.swagger.model.Flow flow) {

        Request request = null;

        try {
            utx.begin();

            sv.gob.aduana.entity.Requestflow requestFlow = FormTranslator.apiToDB(flow);
            sv.gob.aduana.entity.Requestflow requestFlowSaved = requestflowFacade.find(flow.getId());

            requestFlowSaved.setComment(requestFlow.getComment());
            requestFlowSaved.setEnddate(requestFlow.getEnddate());
            requestFlowSaved.setResult(requestFlow.getResult());
            requestFlowSaved.setStatus(requestFlow.getStatus());
            requestFlowSaved.setUser(requestFlow.getUser());

            request = requestflowFacade.getRequestByRequestFlow(requestFlow.getId());

            eventLogFacade.insertInAudit(request.getForm().getId(), request.getId(), "El resultado del paso es: " + requestFlow.getResult() + ", realizado por usuario " + requestFlow.getUser(),
                    "WORKFLOW", requestFlow.getUser());

            if (request == null) {
                return null;
            }

            requestflowFacade.edit(requestFlowSaved);

            utx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return request.getId();
    }

    private String getEmailsStep(String role) {

        if (role.contains("SERVICIO_AL_CLIENTE")) {
            role = "DGA_FORM_SERVICIO_CLIENTE";
        }

        if (role.contains("SOLICITANTE")) {
            role = "DGA_FORM_SOLICITANTE";
        }

        if (role.contains("APROBADOR")) {
            role = "DGA_FORM_APROBADOR";
        }

        if (role.contains("SOLICITANTE_JURIDICO")) {
            role = "DGA_FORM_SOLICITANTE_JUR";
        }

        if (role.contains("SOLICITANTE_JURIDICO")) {
            role = "DGA_FORM_ELABORADOR_JUR";
        }

        if (role.equals("DGA_FORM_SERVICIO_CLIENTE")) {
            if (System.getProperty("custom.enviroment.oim").equals("TEST")) {
                return "edson.artiga@datumredsoft.com";
            } else {
                return "asistencia@aduana.gob.sv";
            }
            // return "edson.artiga@datumredsoft.com";
        }

        StringBuilder strBuilder = new StringBuilder();

        oimService.getMailsByRole(role).forEach(s -> {
            strBuilder.append(s);
            strBuilder.append(",");
        });

        strBuilder.delete(strBuilder.length() - 1, strBuilder.length());

        return strBuilder.toString();
    }

    public void rejectFlow(String requestId) {
        try {
            utx.begin();

            Request request = requestFacade.find(requestId);
            sv.gob.aduana.entity.Formu form = formuFacade.find(request.getForm().getId());

            form.setStep("Rechazado");
            form.setStepisrole(0);
            form.setRoleStep("Rechazado");
            form.setHashCode(UUID.randomUUID().toString());
            form.setClosed(1);
            form.setStatus("FINISH");

            eventLogFacade.insertInAudit(request.getForm().getId(), request.getId(), "El formulario ser� rechazado, ya que no existen otras solicitudes",
                    "WORKFLOW", "");

            formuFacade.edit(form);

            for (Request r : form.getRequestCollection()) {
                for (Requestflow rf : requestflowFacade.getByRequestId(r.getId())) {

                    if (!rf.getStatus().equals("FINISH")) {
                        continue;
                    }

                    rf.setStatus("FINISH");
                    rf.setUser("-");
                    rf.setComment("Rechazado el paso autom�ticamente debido a que un paso anterior fue rechazado.");
                    rf.setEnddate(new Date());
                    rf.setResult("REJECT");
                    rf.setStartdate(new Date());

                    requestflowFacade.edit(rf);

                    r.setState("REJECT");

                    requestFacade.edit(r);

                }

            }

            utx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void moveStep(String requestId) {

        try {
            utx.begin();

            Request request = requestFacade.find(requestId);
            sv.gob.aduana.entity.Formu form = formuFacade.find(request.getForm().getId());

            Long step = requestflowFacade.findMinStep(requestId);

            for (Request r : form.getRequestCollection()) {
                for (Requestflow rf : requestflowFacade.getByRequestId(r.getId())) {

                    if (!rf.getStatus().equals("NOTSTARTED")) {
                        continue;
                    }

                    Flow f = flowFacade.find(rf.getFlow().getId());

                    if (f.getStep() != step) {
                        continue;
                    }

                    Requestflow lastFlow = requestflowFacade.getLastStep(rf.getRequest().getId(), step - 1);

                    if (lastFlow == null) {
                        continue;
                    }

                    if (!lastFlow.getStatus().equals("FINISH")) {
                        continue;
                    }

                    if (lastFlow.getResult().equals("REJECT")) {
                        rf.setStatus("FINISH");
                        rf.setUser(lastFlow.getUser());
                        rf.setComment("Rechazado el paso autom�ticamente debido a que un paso anterior fue rechazado.");
                        rf.setEnddate(lastFlow.getEnddate());
                        rf.setResult("REJECT");
                        rf.setStartdate(lastFlow.getStartdate());

                        requestflowFacade.edit(rf);

                        r.setState("REJECT");

                        requestFacade.edit(r);

                    } else {

                        form.setStep(rf.getFlow().getNameStep());
                        form.setStepisrole(rf.getFlow().getSendtorole());
                        form.setRoleStep(rf.getFlow().getRole());
                        form.setHashCode(UUID.randomUUID().toString());

                        formuFacade.edit(form);

                        rf.setStatus("PENDING");
                        rf.setStartdate(lastFlow.getStartdate());

                        requestflowFacade.edit(rf);

                        eventLogFacade.insertInAudit(request.getForm().getId(), request.getId(), "El formulario acanza al siguiente paso, el cual es rol: " + rf.getFlow().getRole(),
                                "WORKFLOW", "");

                        r.setCurrentFlowId(rf.getId());
                        r.setHashcode(UUID.randomUUID().toString());

                        requestFacade.edit(r);

                    }

                }

            }

            utx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean allRequestIsFinished(String requestId) {

        Request request = requestFacade.find(requestId);
        sv.gob.aduana.entity.Formu form = formuFacade.find(request.getForm().getId());

        //Validar que no existan pendientes
        Boolean allFinished = true;
        for (Request r : form.getRequestCollection()) {
            for (Requestflow rf : requestflowFacade.getByRequestId(r.getId())) {
                if (!rf.getStatus().equals("FINISH")) {
                    allFinished = false;
                }
            }
        }

        return allFinished;
    }

    public boolean allPendingAreFinished(String requestId) {

        Request request = requestFacade.find(requestId);
        sv.gob.aduana.entity.Formu form = formuFacade.find(request.getForm().getId());

        //Validar que no existan pendientes
        Boolean finishPending = true;

        for (Request r : form.getRequestCollection()) {

            for (Requestflow rf : requestflowFacade.getByRequestId(r.getId())) {

                if (rf.getStatus().equals("PENDING")) {
                    finishPending = false;
                }
            }
        }

        return finishPending;
    }

    public boolean isStillRequestToApprove(String requestId) {

        Request request = requestFacade.find(requestId);
        sv.gob.aduana.entity.Formu form = formuFacade.find(request.getForm().getId());

        //Validar que no existan pendientes
        Boolean isApproveSomeRequest = false;

        for (Request r : form.getRequestCollection()) {

            for (Requestflow rf : requestflowFacade.getByRequestId(r.getId())) {

                if (rf.getStatus().equals("REJECT") || rf.getStatus().equals("PENDING")) {
                    isApproveSomeRequest = true;
                }
            }
        }

        return isApproveSomeRequest;
    }

    public void closeFlow(String requestId) {
        //Cargar Formulario
        sv.gob.aduana.entity.Request request = requestFacade.find(requestId);
        //Cargar el Paso actual
        Boolean existRequestAvailable = false;

        sv.gob.aduana.entity.Formu form = formWrapper.getFormAll(request.getForm().getId());

        sv.gob.aduana.entity.Requestflow requestflow = null;
        String title = null;

        //Obtener el paso actual
        for (sv.gob.aduana.entity.Requestflow rf : form.getRequestCollection().get(0).getRequestflowCollection()) {

            if (rf.getStatus().equals("FINISH")) {
                requestflow = rf;
                break;
            }
        }

        //Cargar reglas
        sv.gob.aduana.entity.Flow flow = flowFacade.find(requestflow.getFlow().getId());

        String template = readAllFile("close.html");

        if (RequestTypeService.isIntern(form.getFormType())) {
            template = template.replaceAll("\\{title\\}", "Solicitud de servicios informaticos para accesos de usuario interno");
        } else if (RequestTypeService.isExtern(form.getFormType())) {
            template = template.replaceAll("\\{title\\}", "Solicitud de servicios informaticos para accesos de usuario externo");
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("<table style='margin:0 30px !important;'>");
        strBuilder.append("<tr><td><strong>ID: </strong></td><td>");
        strBuilder.append(form.getId());
        strBuilder.append("</tr><tr><td><strong>Solicitante:</strong></td><td>");
        strBuilder.append(form.getApplicant().getName());
        strBuilder.append("</tr><tr><td><strong>Creado en: </strong></td><td>");
        strBuilder.append(simpleDateFormat.format(form.getCreatedon()));
        strBuilder.append("</td></tr>");
        strBuilder.append("</table>");

        template = template.replaceAll("\\{solicitanteTabla\\}", strBuilder.toString());
        strBuilder.delete(0, strBuilder.length());

        strBuilder.append("<table  style='margin:0 30px !important;'>");

        if (RequestTypeService.isIntern(form.getFormType())) {
            strBuilder.append("<tr><td><strong>Cargo:</strong> </td><td>");
            strBuilder.append(form.getApplicant().getPosition().getName());
            strBuilder.append("</td></tr><tr><td><strong>Email: </strong></td><td>");
            strBuilder.append(form.getApplicant().getMail());
            strBuilder.append("</td></tr><tr><td><strong>Documento: </strong></td><td>");
            strBuilder.append(form.getApplicant().getDocument());
            strBuilder.append("</td></tr>");

        } else if (RequestTypeService.isExtern(form.getFormType())) {

            strBuilder.append("<tr><td><strong>Cargo:</strong> </td><td>");
            strBuilder.append(form.getApplicant().getPosition().getName());
            strBuilder.append("</td></tr><tr><td><strong>Email: </strong></td><td>");
            strBuilder.append(form.getApplicant().getMail());
            strBuilder.append("</td></tr><tr><td><strong>Documento: </strong></td><td>");
            strBuilder.append(form.getApplicant().getDocument());
            strBuilder.append("</td></tr>");
        }
        strBuilder.append("</table>");

        strBuilder.append("<br/><br/>");
        strBuilder.append("Observaciones: ");
        strBuilder.append(form.getComment());
        strBuilder.append("<br/><br/>");

        template = template.replaceAll("\\{detalleSolicitanteTabla\\} ", strBuilder.toString());
        try {

            utx.begin();

            form.setClosed(1);
            form.setStatus("FINISH");
            form.setStep("Finalizado el procesamiento");
            form.setStepisrole(0);
            form.setRoleStep("-");

            formuFacade.edit(form);

            for (sv.gob.aduana.entity.Request r : form.getRequestCollection()) {

                strBuilder.append("<h4><strong>Solicitud : ");
                strBuilder.append(r.getId());
                strBuilder.append("</h4></strong><br/><br/>");
                strBuilder.append("<table width='100%'>");
                strBuilder.append("<tr>");
                strBuilder.append("<td><strong>Tipo de Servicio: </strong></td><td>");
                strBuilder.append(r.getTyperequest().getName());
                if (r.getPerson().getUserType() != null) {
                    strBuilder.append("</td></tr><tr><td><strong>Tipo de usuario: </strong></td><td>");
                    strBuilder.append(r.getPerson().getUserType());
                }
                strBuilder.append("</td></tr><tr><td><strong>Cuenta de usuario: </strong></td><td>");
                strBuilder.append(r.getPerson().getLogin());
                strBuilder.append("</td></tr><tr><td><strong>Nombre completo: </strong></td><td>");
                strBuilder.append(r.getPerson().getFullname());
                strBuilder.append("</td></tr><tr><td><strong>Correo: </strong></td><td>");
                strBuilder.append(r.getPerson().getMail());

                if (RequestTypeService.isNew(r.getTyperequest().getId()) || RequestTypeService.isEnabled(r.getTyperequest().getId())) {
                    strBuilder.append("</td></tr><tr><td><strong>Contrase�a: </strong></td><td>");
                    strBuilder.append(r.getPassword());
                } else {
                    strBuilder.append("</td></tr><tr><td><strong>Contrase�a (Si aplica cambio): </strong></td><td>");
                    strBuilder.append(r.getPassword());
                }

                strBuilder.append("</td></tr><tr><td><strong>Documento: </strong></td><td>");
                strBuilder.append(r.getPerson().getDocument());
                strBuilder.append("</td></tr><tr><td><strong>Estado: </strong></td><td>");
                strBuilder.append(r.getState());
                strBuilder.append("</td></tr></table><br/>");

                strBuilder.append("<br/><h5>Flujo de aprobaci�n</h5><br/><table width='100%'>");
                strBuilder.append("<tr>");
                strBuilder.append("<td>Usuario</td><td>Fecha</td><td>Estado</td></tr>");
                
                for(Requestflow rf : r.getRequestflowCollection()){
                    strBuilder.append("<tr><td>");
                    strBuilder.append(rf.getUser());
                    strBuilder.append("</td><td>");
                    strBuilder.append(rf.getEnddate());
                    strBuilder.append("</td><td>");
                    strBuilder.append(rf.getResult());
                    strBuilder.append("</td></tr>");
                }
                
                strBuilder.append("</table><br/>");

                if (!RequestTypeService.isRequestWithPermissionsFilled(r.getTyperequest().getId())) {
                    strBuilder.append("<table width='100%'>");
                    strBuilder.append("<tr><td  width='33%'><strong>Perfiles</strong></td><td width='33%'><strong>Sistemas</strong></td><td width='33%'><strong>Otros permisos</strong></td></tr>");
                    strBuilder.append("<tr><td>");
                    if (r.getProfileCollection().size() > 0) {
                        for (sv.gob.aduana.entity.Profile p : r.getProfileCollection()) {
                            strBuilder.append("<br/><br/>Perfil: ");
                            strBuilder.append(p.getProfile().getName());
                            strBuilder.append("<br/><br/>Acci�n: ");
                            strBuilder.append(p.getStatus());
                            strBuilder.append("<br/><br/>Es temporal: ");
                            strBuilder.append(p.getTemporal() == 1 ? "SI" : "NO");
                            if (p.getTemporal() == 1) {
                                strBuilder.append("<br/> Fecha fin:");
                                strBuilder.append(simpleDateFormat.format(p.getEnddate()));
                            }
                            strBuilder.append("<br/><br/>Aduanas: ");
                            strBuilder.append(p.getCustom());
                        }
                    }

                    strBuilder.append("</td><td>");

                    if (r.getSystemCollection().size() > 0) {
                        for (sv.gob.aduana.entity.System s : r.getSystemCollection()) {
                            strBuilder.append("<br/><br/>Sistema: ");
                            strBuilder.append(s.getGroup1().getSystem().getName());
                            strBuilder.append("<br/><br/>Grupo: ");
                            strBuilder.append(s.getGroup1().getFriendlyname());
                            strBuilder.append("<br/><br/>Acci�n: ");
                            strBuilder.append(s.getStatus());
                            strBuilder.append("<br/><br/>Es temporal: ");
                            strBuilder.append(s.getTemporal() == 1 ? "SI" : "NO");
                            if (s.getTemporal() == 1) {
                                strBuilder.append("<br/> Fecha fin:");
                                strBuilder.append(simpleDateFormat.format(s.getEnddate()));
                            }
                            strBuilder.append("<br/><br/>Aduanas: ");
                            strBuilder.append(s.getCustom());
                        }
                    }

                    strBuilder.append("</td><td>");

                    if (r.getOtherCollection().size() > 0) {
                        for (sv.gob.aduana.entity.Other o : r.getOtherCollection()) {
                            strBuilder.append("<br/><br/>Plataforma: ");
                            strBuilder.append(o.getPlatform());
                            strBuilder.append("<br/><br/>Informaci�n: ");
                            strBuilder.append(o.getData());
                            strBuilder.append("<br/><br/>Acci�n: ");
                            strBuilder.append(o.getStatus());
                            strBuilder.append("<br/><br/>Es temporal: ");
                            strBuilder.append(o.getTemporal() == 1 ? "SI" : "NO");
                            if (o.getTemporal() == 1) {
                                strBuilder.append("<br/> Fecha fin:");
                                strBuilder.append(simpleDateFormat.format(o.getEnddate()));
                            }
                        }
                    }

                    strBuilder.append("</td></tr></table><br/>");

                } else {
                    strBuilder.append("<table width='100%'>");
                    strBuilder.append("<tr>");
                    strBuilder.append("<td><strong>Borrar todos los grupos: </strong></td><td>");
                    strBuilder.append(r.getDeleteAllGroup() == 0 ? "NO" : "SI");
                    strBuilder.append("</td></tr><tr><td><strong>Mover a desactivados: </strong></td><td>");
                    strBuilder.append(r.getMoveToDesactive() == 0 ? "NO" : "SI");
                    strBuilder.append("</td></tr></table><br/>");

                }

                /*strBuilder.append(" | <a href='");
            if (System.getProperty("custom.enviroment.oim").equals("TEST")) {
                strBuilder.append("http://oim-test.mh.gob.sv/custom-app/dga/form/ApproveByMail?hashcode=");
            } else {
                strBuilder.append("http://identidad.mh.gob.sv/custom-app/dga/form/ApproveByMail?hashcode=");
            }
            strBuilder.append(r.getHashcode());
            strBuilder.append("&formId=");
            strBuilder.append(form.getId());
            strBuilder.append("&action=reject'>Rechazar</a>");*/
                strBuilder.append("<br/><hr/><br/>");
            }

            template = template.replaceAll("\\{solicitudes\\}", strBuilder.toString());
            Email email = new Email();

            if (System.getProperty("custom.enviroment.oim").equals("TEST")) {
                title = "[TEST] - Solicitud de servicios inform�ticos para accesos - " + form.getId() + " - Finalizaci�n de formulario ";
            } else {
                title = "Solicitud de servicios inform�ticos para accesos - " + form.getId() + " - Finalizaci�n de formulario ";
            }

            email.setMessage(template);
            email.setTitle(title);
            email.setTo(requestflow.getRequesteremail());

            oimService.sendMail(email);

            email.setTo(requestflow.getDeveloperemail());

            oimService.sendMail(email);

            eventLogFacade.insertInAudit(request.getForm().getId(), request.getId(), "Se envio el correo exitosamente del paso.: ",
                    "WORKFLOW", "");

            utx.commit();

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    public Login flowLogin(Login login) {

        if (!oimService.validarUser(login)) {
            return null;
        }

        List<String> roles = oimService.getRolesByUser(login.getUser().toUpperCase());

        StringBuilder stringBuilder = new StringBuilder();

        roles.forEach(s -> {
            stringBuilder.append(s);
            stringBuilder.append(",");
        });

        login.setRole(stringBuilder.toString());

        return login;
    }

}
