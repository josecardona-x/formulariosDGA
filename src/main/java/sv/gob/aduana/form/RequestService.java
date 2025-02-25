/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.aduana.form;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import io.swagger.model.Account;
import io.swagger.model.Other;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import sv.gob.aduana.entity.AccountAffected;
import sv.gob.aduana.entity.CatAppsidentity;
import sv.gob.aduana.entity.Formu;
import sv.gob.aduana.entity.GeneratorId;
import sv.gob.aduana.mdb.FormQueue;
import sv.gob.aduana.mdb.IdentityResponseQueue;
import sv.gob.aduana.mtto.bean.AccountAffectedFacade;
import sv.gob.aduana.mtto.bean.CatAppsidentityFacade;
import sv.gob.aduana.mtto.bean.EventLogFacade;
import sv.gob.aduana.mtto.bean.FormuFacade;
import sv.gob.aduana.mtto.bean.GeneratorIdFacade;
import sv.gob.aduana.mtto.bean.OtherFacade;
import sv.gob.aduana.mtto.bean.PersonFacade;
import sv.gob.aduana.mtto.bean.ProfileFacade;
import sv.gob.aduana.mtto.bean.RequestFacade;
import sv.gob.aduana.mtto.bean.SystemFacade;
import sv.gob.aduana.util.ClienteMesa;
import sv.gob.aduana.util.CrearJsonSolicitud;
import sv.gob.aduana.util.WriteLog;
import sv.gob.mh.dto.SolicitudMesa;
import sv.gob.mh.oim.OIMService;
import sv.gob.mh.oim.pojo.Email;
import sv.gob.mh.oim.pojo.HelpDeskResponse;
import sv.gob.mh.oim.pojo.MessageQueue;
import sv.gob.mh.oim.pojo.ServiceDeskResponse;
import sv.gob.mh.oim.pojo.TransferAccount;

/**
 *
 * @author Datum-Redsoft
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class RequestService {

    @Resource
    private UserTransaction utx;

    @EJB
    protected GeneratorIdFacade generatorIdFacade;

    @EJB
    protected RequestFacade requestFacade;

    @EJB
    protected ProfileFacade profileFacade;

    @EJB
    protected OtherFacade otherFacade;

    @EJB
    protected SystemFacade systemFacade;

    @EJB
    protected FormuFacade formuFacade;

    @EJB
    protected PersonFacade personFacade;

    @EJB
    protected OIMService oimService;

    @EJB
    protected FormQueue formQueue;

    @EJB
    protected IdentityResponseQueue identityResponseQueue;

    @EJB
    protected EventLogFacade eventLogFacade;

    @EJB
    protected AccountAffectedFacade accountAffectedFacade;

    @EJB
    protected CatAppsidentityFacade catAppsidentityFacade;
    
    WriteLog log;

    public RequestService() {
        log = new WriteLog();
    }

    public sv.gob.aduana.entity.Other insertOther(sv.gob.aduana.entity.Other other, String requestId) throws Exception {
        utx.begin();
        //Guardar el solicitante
        GeneratorId gen = generatorIdFacade.find("OTH");

        Integer counter = gen.getCounter();

        gen.setCounter(counter + 1);

        generatorIdFacade.edit(gen);

        other.setId("OTH-" + counter);
        sv.gob.aduana.entity.Request r = requestFacade.find(requestId);

        other.setRequest(r);

        otherFacade.create(other);

        utx.commit();

        return other;
    }

    public sv.gob.aduana.entity.Other deleteOther(String requestId, sv.gob.aduana.entity.Other other) throws Exception {
        utx.begin();
        //Guardar el solicitante
        sv.gob.aduana.entity.Request r = requestFacade.find(requestId);

        other.setRequest(r);
        otherFacade.edit(other);

        utx.commit();

        return other;
    }

    public sv.gob.aduana.entity.Profile insertProfile(sv.gob.aduana.entity.Profile profile, String requestId) throws Exception {
        utx.begin();
        //Guardar el solicitante
        GeneratorId gen = generatorIdFacade.find("PRO");

        Integer counter = gen.getCounter();

        gen.setCounter(counter + 1);

        generatorIdFacade.edit(gen);

        profile.setId("PRO-" + counter);
        sv.gob.aduana.entity.Request r = requestFacade.find(requestId);

        profile.setRequest(r);
        profileFacade.create(profile);

        utx.commit();

        return profile;
    }

    public sv.gob.aduana.entity.Profile deleteProfile(String requestId, sv.gob.aduana.entity.Profile profile) throws Exception {
        utx.begin();
        //Guardar el solicitante
        sv.gob.aduana.entity.Request r = requestFacade.find(requestId);

        profile.setRequest(r);
        profileFacade.edit(profile);

        utx.commit();

        return profile;
    }

    public sv.gob.aduana.entity.System insertSystem(sv.gob.aduana.entity.System system, String requestId) throws Exception {
        utx.begin();
        //Guardar el solicitante
        GeneratorId gen = generatorIdFacade.find("SYS");

        Integer counter = gen.getCounter();

        gen.setCounter(counter + 1);

        generatorIdFacade.edit(gen);

        system.setId("SYS-" + counter);
        sv.gob.aduana.entity.Request r = requestFacade.find(requestId);

        system.setRequest(r);
        systemFacade.create(system);

        utx.commit();

        return system;
    }

    public sv.gob.aduana.entity.System deleteSystem(String requestId, sv.gob.aduana.entity.System system) throws Exception {
        utx.begin();
        //Guardar el solicitante
        sv.gob.aduana.entity.Request r = requestFacade.find(requestId);

        system.setRequest(r);
        systemFacade.edit(system);

        utx.commit();

        return system;
    }

    public sv.gob.aduana.entity.Request editRequest(String formId, sv.gob.aduana.entity.Request request) throws Exception {
        sv.gob.aduana.entity.Formu form = formuFacade.find(formId);
        sv.gob.aduana.entity.Person person = request.getPerson();

        utx.begin();

        personFacade.edit(person);
        request.setForm(form);

        requestFacade.edit(request);

        eventLogFacade.insertInAudit(request.getForm().getId(), request.getId(), "Editando la solicitud",
                "REQUEST", request.getForm().getCreateby());

        utx.commit();

        return request;
    }

    public sv.gob.aduana.entity.Request insertRequest(String formId, sv.gob.aduana.entity.Request request) throws Exception {
        sv.gob.aduana.entity.Formu form = formuFacade.find(formId);
        sv.gob.aduana.entity.Person person = request.getPerson();

        request.setOtherCollection(null);
        request.setProfileCollection(null);
        request.setSystemCollection(null);
        request.setRequestflowCollection(null);
        /*
        if (RequestTypeService.isRequestWithPermissionsFilled(request.getTyperequest().getId())) {
            person.setAttribute(null);
            person.setLevelfour(null);
            person.setLevelone(null);
            person.setLevelthree(null);
            person.setLevelfour(null);
            person.setPosition(null);
            person.setCatTypeafpaid(null);
        }*/

        utx.begin();

        GeneratorId gen = generatorIdFacade.find("PER");

        Integer counter = gen.getCounter();

        gen.setCounter(counter + 1);

        generatorIdFacade.edit(gen);

        person.setId("PER-" + counter);

        personFacade.create(person);

        request.getPerson().setId(person.getId());
        request.setForm(form);

        gen = generatorIdFacade.find("REQ");

        counter = gen.getCounter();

        gen.setCounter(counter + 1);

        generatorIdFacade.edit(gen);

        request.setId("REQ-" + counter);

        //Generar Password
        Double number = Math.floor((Math.random() * 19999)) + 10000;

        String password = "Ministerio" + number.intValue();

        request.setPassword(password);

        requestFacade.create(request);

        eventLogFacade.insertInAudit(request.getForm().getId(), request.getId(), "Creando la solicitud",
                "REQUEST", request.getForm().getCreateby());

        utx.commit();

        return request;
    }

    public sv.gob.aduana.entity.Request startupOIM(String requestId) {
        sv.gob.aduana.entity.Request request = requestFacade.find(requestId);
        sv.gob.aduana.entity.Formu form = formuFacade.find(request.getForm().getId());
        Boolean existRequestAvailable = false;
        String helpDeskId = null;

        try {
            utx.begin();

            helpDeskId = request.getHelpDeskId();

            //se ha creado HelpDesk
            if (helpDeskId == null) {
                helpDeskId = "No se creo caso de soporte.";
            }

            sendEmailToStarting(request, helpDeskId);

            eventLogFacade.insertInAudit(request.getForm().getId(), request.getId(), "Se creo exitosamente el caso de soporte en Help Desk para DGA "
                    + request.getId() + ", con código " + helpDeskId,
                    "FORM", request.getForm().getCreateby());

            MessageQueue msj = new MessageQueue();

            msj.setId(requestId);
            msj.setAction("STARTUP");

            identityResponseQueue.persist(msj);

            request.setHelpDeskId(helpDeskId);
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return request;
    }

    public sv.gob.aduana.entity.Request createServiceDesk(String requestId) {

        sv.gob.aduana.entity.Request request = requestFacade.find(requestId);
        sv.gob.aduana.entity.Formu form = formuFacade.find(request.getForm().getId());
        Boolean existRequestAvailable = false;

        try {
            Gson gson = new Gson();
            String helpDeskId = null;
            List<HelpDeskResponse> temp = null;
            Type customType = null;

            if (request.getHelpDeskId() != null) {
                return request;
            }


            Integer customerId = generatorIdFacade.find("CUSTOMERID").getCounter();
            Integer authId = generatorIdFacade.find("AUTHID").getCounter();
            Integer retId = generatorIdFacade.find("RETID").getCounter();
            Integer groupId = generatorIdFacade.find("GROUP_DGA").getCounter();
            String categoryId = request.getTyperequest().getCategoryId();
            String typeRequestName = request.getTyperequest().getName();
            
//SE MANDA A CREAR EL CASO
            String responseBody = callCreateCase(customerId, authId, retId, groupId,
                    categoryId, request, requestId, typeRequestName);
            
            ServiceDeskResponse serviceDeskResponse = new ServiceDeskResponse();
            serviceDeskResponse = gson.fromJson(responseBody, ServiceDeskResponse.class);
            
            if(serviceDeskResponse.getResponse_status().getStatus_code() == 2000){
                request.setState("Se creo el helpdesk correctamente.");
                request.setHelpDeskId(serviceDeskResponse.getRequest().getId());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return request;
    }

    private String callCreateCase(Integer customerId, Integer authId, Integer retId, Integer groupId, String categoryId,
            sv.gob.aduana.entity.Request r, String requestId, String requestName) throws IOException {

//        Gson gson = new Gson();
//        OkHttpClient client = new OkHttpClient();

        String solicitudes = createMail(r, "");
        solicitudes = solicitudes.trim()
                        .replaceAll("\"", "'")
                        .replaceAll("\n", "<br/>")
                        .replaceAll("\\\\", "\\\\\\\\");
        
        String titulo = "OIM - Solicitud de " + requestName + " para formulario de acceso " + requestId;
        
        //Crear objeto del request
        CrearJsonSolicitud jsonSolicitud = new CrearJsonSolicitud();
        SolicitudMesa solicitudMesa = jsonSolicitud.solicitudCreacion(solicitudes, titulo, categoryId, requestName, r.getPerson().getMail(), r.getPerson().getFullname());
       
        
        //Envio solicitud
        ClienteMesa clienteMesa = new ClienteMesa();
        String body = clienteMesa.envioSolicitudMesa(solicitudMesa);
        
        log.printLog("INFO", "RequestService", "callCreateCase", "Finaliza la creación del caso " + r.getPerson().getLogin());
        
        return body;

    }

    private void sendEmailToStarting(sv.gob.aduana.entity.Request r, String helpDeskId) {
        String template = readAllFile("servicedesk.html");
        Date fecha = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        StringBuilder stringBuilder = new StringBuilder();
        template = template.replaceAll("\\{title\\}",
                "Formulario de DGA - Identidades y Permisos a implementar -" + r.getId() + " " + format.format(fecha));
        stringBuilder.append("Formulario que se deben implementar (Para más detalle verificar los casos de soporte en mesa de servicio): ");

        String solicitudes = createMail(r, helpDeskId);

        template = template.replaceAll("\\{solicitudes\\}", solicitudes);
        Email email = new Email();

        email.setMessage(template);

        if (System.getProperty("custom.enviroment.oim").equals("TEST")) {
            email.setTitle("[TEST] Formulario de DGA - Identidades y Permisos a implementar -" + r.getId() + " " + format.format(fecha));
            email.setTo("edson.artiga@datumredsoft.com");
        } else {
            email.setTitle("Formulario de DGA - Identidades y Permisos a implementar -" + r.getId() + " " + format.format(fecha));
            email.setTo("asistencia@aduana.gob.sv");
        }

        //email.setTo("edson.artiga@datumredsoft.com");
        oimService.sendMail(email);
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
    
    public String createHtml(sv.gob.aduana.entity.Request r, String helpDeskId){
    String formulario = r.getForm().getId();
    String solicitud = r.getId();
    String tipoSolicitud = r.getTyperequest().getName();
    String nombreUsuario = r.getPerson().getFullname();
    String usuario = r.getPerson().getLogin();
    if (r.getPerson().getStartdate() != null) {           
            String fechaInicioVigencia = r.getPerson().getStartdate().toString();
        }else{
            String fechaInicioVigencia = "N/A";
        }
    String fechaInicioVigencia = "24/11/2023 11:28:48";
    String documento = "046672243";
    String email = "luis.artiga@mh.gob.sv";
    String estado = "PENDING";
    String resolucion = "FORMULARIO 650";
    String perfil = "ADMINISTRADOR - TERRESTRE SAN BARTOLO";
    String accion = "PENDIENTE DE ASIGNAR";
    String aduanaPrincipal = "00 | Todas";
    String[][] permisos = {{"Sistema Subastas On-Line Int", "Subastador", "Aduanas: 01 | TERRESTRE SAN BARTOLO"},
        {"Sistema para administracion y consulta sobre incisos arancelarios, acuerdos y cuotas", "Consulta de Acuerdo y Cuotas", "Aduanas: 01 | TERRESTRE SAN BARTOLO"},
        {"Firma electronica para Declaraciones de Sidunea++", "Firma Electronica para AFPA", "Aduanas: 01 | TERRESTRE SAN BARTOLO"}};

    
        return "";
    }

    public String createMail(sv.gob.aduana.entity.Request r, String helpDeskId) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<table><tr><td width='33%'>Formulario</td><td width='33%'>Solicitud</td><td width='33%'>Caso Help Desk</td></tr>");
        stringBuilder.append("<tr><td>");
        stringBuilder.append(r.getForm().getId());
        stringBuilder.append("</td><td>");
        stringBuilder.append(r.getId());
        stringBuilder.append("</td><td>");
        stringBuilder.append(helpDeskId);
        stringBuilder.append("</td></tr>");
        stringBuilder.append("</table>");

        stringBuilder.append("<br/><br/>Nombre de usuario: ");
        stringBuilder.append(r.getPerson().getFullname());
        stringBuilder.append("<br/>Usuario: ");
        stringBuilder.append(r.getPerson().getLogin());
        if (r.getPerson().getStartdate() != null) {
            stringBuilder.append("<br/><strong>Fecha de inicio de vigencia: </strong>");
            stringBuilder.append(r.getPerson().getStartdate());
        }
        if (r.getPerson().getEnddate() != null) {
            stringBuilder.append("<br/><strong>Fecha de fin de vigencia: </strong>");
            stringBuilder.append(r.getPerson().getEnddate());
        }
        stringBuilder.append("<br/>Documento: ");
        stringBuilder.append(r.getPerson().getDocument());
        stringBuilder.append("<br/>Email: ");
        stringBuilder.append(r.getPerson().getMail());
        stringBuilder.append("<br/>Estado: ");
        stringBuilder.append(r.getPerson().getState());
        if (RequestTypeService.isExtern(r.getForm().getFormType())) {

            if (r.getPerson().getCatTypeafpaid() != null) {
                stringBuilder.append("<br/>Tipo AFPA: ");
                stringBuilder.append(r.getPerson().getCatTypeafpaid().getName());
            }

            stringBuilder.append("<br/>Estado: ");
            stringBuilder.append(r.getPerson().getCodedeclarant());
            stringBuilder.append("<br/>Resolución: ");
            stringBuilder.append(r.getPerson().getResolution());
        } else {
            stringBuilder.append("<br/>Nivel 1: ");
            stringBuilder.append(r.getPerson().getLevelone().getName());
            stringBuilder.append("<br/>Nivel 2: ");
            stringBuilder.append(r.getPerson().getLeveltwo().getName());
            stringBuilder.append("<br/>Nivel 3: ");
            stringBuilder.append(r.getPerson().getLevelthree().getName());
            stringBuilder.append("<br/>Nivel 4: ");
            stringBuilder.append(r.getPerson().getLevelfour().getName());

        }
        stringBuilder.append("<br/>Tipo de solicitud: ");
        stringBuilder.append(r.getTyperequest().getName());
        stringBuilder.append("<br/>Solicitud de accesos  para DGA: ");
        
        //COMENTAR

        if (r.getProfileCollection().size() > 0) {
            stringBuilder.append("<br/><br/><strong>Perfiles: </strong>");
            for (sv.gob.aduana.entity.Profile p : r.getProfileCollection()) {

                if (!p.getStatus().equals("PENDIENTE DE ASIGNAR") && !p.getStatus().equals("PENDIENTE DE BORRAR")) {
                    continue;
                }

                stringBuilder.append("<br/>Perfil: ");
                stringBuilder.append(p.getProfile().getName());
                stringBuilder.append("<br/>Acción: ");
                stringBuilder.append(p.getStatus());
                stringBuilder.append("<br/>Aduana principal: ");
                if (p.getCustom() != null) {
                    stringBuilder.append(p.getCustom());
                }
                if (p.getEnddate() != null) {
                    stringBuilder.append("<br/><strong>Fecha fin:</strong> ");
                    stringBuilder.append(p.getEnddate());
                }
                stringBuilder.append("<br/><br/>Permisos");

                stringBuilder.append("<table><tr><td width='33%'>Sistema</td><td width='33%'>Grupo</td><td width='33%'>Otros</td></tr>");
                for (sv.gob.aduana.entity.CatProfiledetail pd : p.getProfile().getCatProfiledetailList()) {

                    stringBuilder.append("<tr><td>");
                    stringBuilder.append(pd.getSystemName());
                    stringBuilder.append("</td><td>");
                    stringBuilder.append(pd.getGroup());
                    stringBuilder.append("</td><td>");
                    stringBuilder.append("<br/>Aduana principal: ");
                    if (p.getCustom() != null) {
                        stringBuilder.append(p.getCustom());
                    }
                    if (pd.getAssignCustom() != null) {
                        stringBuilder.append("<br/>Aduanas adicionales: ");
                        stringBuilder.append(pd.getAssignCustom());
                    }
                    if (pd.getComplementaryCustom() != null) {
                        stringBuilder.append("<br/>Aduanas complementarias: ");
                        stringBuilder.append(pd.getComplementaryCustom());
                    }
                    if (pd.getOtherPermission() != null) {
                        stringBuilder.append("<br/>Otros permisos: ");
                        stringBuilder.append(pd.getOtherPermission());
                    }
                    stringBuilder.append("</td></tr>");

                }
                stringBuilder.append("</table>");

            }
        }

        if (r.getSystemCollection().size() > 0) {
            stringBuilder.append("<br/><br/><strong>Sistemas: </strong>");
            for (sv.gob.aduana.entity.System si : r.getSystemCollection()) {
                if (!si.getStatus().equals("PENDIENTE DE ASIGNAR") && !si.getStatus().equals("PENDIENTE DE BORRAR")) {
                    continue;
                }

                stringBuilder.append("<br/><br/>Sistema: ");
                stringBuilder.append(si.getGroup1().getSystem().getName());
                stringBuilder.append("<br/>Grupo: ");
                stringBuilder.append(si.getGroup1().getTechname());
                stringBuilder.append("<br/>Acción: ");
                stringBuilder.append(si.getStatus());

                if (si.getCustom() != null) {
                    stringBuilder.append("<br/>Aduana principal: ");
                    stringBuilder.append(si.getCustom());
                }

                if (si.getEnddate() != null) {
                    stringBuilder.append("<br/><strong>Fecha fin:</strong> ");
                    stringBuilder.append(si.getEnddate());
                }
            }
        }

        return stringBuilder.toString();
    }

    public void createTableAccounts(String requestId) throws Exception {
        utx.begin();

        sv.gob.aduana.entity.Request request = requestFacade.find(requestId);
        Formu f = formuFacade.find(request.getForm().getId());

        for (sv.gob.aduana.entity.Request r : f.getRequestCollection()) {
            // sv.gob.aduana.entity.Request r = requestFacade.find(requestId);

            Set<CatAppsidentity> t = new HashSet<>();

            r.getProfileCollection().forEach((p) -> {
                if (p.getStatus().equals("PENDIENTE DE ASIGNAR") || p.getStatus().equals("PENDIENTE DE BORRAR")) {
                    p.getProfile().getCatProfiledetailList().forEach((cp) -> {
                        t.add(cp.getAppsidentity());
                    });
                }
            });

            r.getSystemCollection().forEach((s) -> {
                if (s.getStatus().equals("PENDIENTE DE ASIGNAR") || s.getStatus().equals("PENDIENTE DE BORRAR")) {
                    t.add(s.getGroup1().getSystem().getAppsidentity());
                }
            });

            for (CatAppsidentity c : t) {
                AccountAffected accountAffected = new AccountAffected();

                accountAffected.setId(r.getId() + "-" + c.getId());
                accountAffected.setRequest(r.getId());
                accountAffected.setAppIdentity(c.getId());
                accountAffected.setAccount("");

                accountAffectedFacade.create(accountAffected);
            }

        }
        utx.commit();
    }

    public List<Account> getAccountsUsed(String requestId) {

        List<io.swagger.model.Account> accounts = new ArrayList<>();
        List<AccountAffected> accountsTemp = accountAffectedFacade.getByRequest(requestId);

        for (AccountAffected account : accountsTemp) {
            CatAppsidentity appsidentity = catAppsidentityFacade.find(account.getAppIdentity());

            io.swagger.model.Account a = new io.swagger.model.Account();

            a.setId(account.getId());
            a.setIsNew(Boolean.FALSE);
            a.setLogin(account.getAccount());
            a.setName("");
            a.setResource(appsidentity.getName());
            a.setRequest(requestId);
            a.setRequestId(requestId);
            a.setResourceId(appsidentity.getId());
            a.setType("RESOURCE");

            accounts.add(a);
        }

        return accounts;
    }

    public List<io.swagger.model.Account> getAccoutCreatedInUser(String requestId, String accountId) {
        sv.gob.aduana.entity.Request r = requestFacade.find(requestId);
        CatAppsidentity appsidentity = catAppsidentityFacade.find(accountId);

        String userLogin = r.getPerson().getLogin();

        List<sv.gob.mh.oim.pojo.Account> allAccounts = oimService.getAccountByUser(userLogin);
        List<io.swagger.model.Account> filterAccounts = new ArrayList<>();

        for (sv.gob.mh.oim.pojo.Account a : allAccounts) {

            if (a.getApplicationKeyName().trim().toLowerCase().equals(appsidentity.getCodename().trim().toLowerCase())) {
                Account account = new Account();
                account.setLogin(a.getUserCustomLogin());
                account.setName(a.getUserLogin());
                filterAccounts.add(account);
            }
        }

        return filterAccounts;
    }

    public Account saveAccountInRequest(String requestId, Account body) {
        AccountAffected accountAffected = new AccountAffected();

        accountAffected.setAccount(body.getLogin());
        accountAffected.setAppIdentity(body.getResourceId());
        accountAffected.setRequest(body.getRequestId());
        accountAffected.setId(body.getId());
        accountAffected.setCreatedBy("");
        accountAffected.setCreatedOn(new Date());

        accountAffectedFacade.edit(accountAffected);

        return body;
    }

    public sv.gob.aduana.entity.Request getRequest(String id) {

        return requestFacade.find(id);

    }

    public String getSessionId() {
        Gson gson = new Gson();
        List<HelpDeskResponse> temp = null;
        Type customType = null;
        String sessionId = "12345";
//        String loginBody = callLoginUser();
//
//        customType = new TypeToken<ArrayList<HelpDeskResponse>>() {
//        }.getType();
//
//        temp = gson.fromJson(loginBody, customType);
//
//        for (HelpDeskResponse hp : temp) {
//            if (hp.getField().equals("sessionId")) {
//                sessionId = hp.getValue();
//            }
//        }

        return sessionId;
    }
}
