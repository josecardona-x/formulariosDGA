package sv.gob.aduana.rest;


import sv.gob.aduana.form.FormTranslator;
import io.swagger.api.*;
import io.swagger.model.Account;

import io.swagger.model.Applicant;
import io.swagger.model.CatalogAttribute;
import io.swagger.model.CatalogCustoms;
import io.swagger.model.CatalogExternalApplicantType;
import io.swagger.model.CatalogFormType;
import io.swagger.model.CatalogGroup;
import io.swagger.model.CatalogLevelFour;
import io.swagger.model.CatalogLevelOne;
import io.swagger.model.CatalogLevelThree;
import io.swagger.model.CatalogLevelTwo;
import io.swagger.model.CatalogPosition;
import io.swagger.model.CatalogProfile;
import io.swagger.model.CatalogProfileExternal;
import io.swagger.model.CatalogRequestType;
import io.swagger.model.CatalogResource;
import io.swagger.model.CatalogSystem;
import io.swagger.model.CatalogSystemExternal;
import io.swagger.model.CatalogTypeAFPA;
import io.swagger.model.Email;
import io.swagger.model.Flow;
import io.swagger.model.Form;
import io.swagger.model.Login;
import io.swagger.model.Other;
import io.swagger.model.Person;
import io.swagger.model.Profile;
import io.swagger.model.Request;
import io.swagger.model.System;
import java.util.ArrayList;
import java.util.HashSet;

import java.util.List;
import java.util.Set;

import javax.ejb.EJB;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import sv.gob.aduana.entity.CatTypeafpa;
import sv.gob.aduana.form.CatalogService;
import sv.gob.aduana.form.FlowService;
import sv.gob.aduana.form.FormService;
import sv.gob.aduana.form.OtherService;
import sv.gob.aduana.form.RequestService;
import sv.gob.aduana.form.RequestTypeService;
import sv.gob.aduana.mtto.bean.CatAttributeFacade;
import sv.gob.aduana.mtto.bean.CatLvlfourFacade;
import sv.gob.aduana.mtto.bean.CatLvloneFacade;
import sv.gob.aduana.mtto.bean.CatLvlthreeFacade;
import sv.gob.aduana.mtto.bean.CatLvltwoFacade;
import sv.gob.aduana.mtto.bean.CatPositionFacade;
import sv.gob.aduana.mtto.bean.CatTypeafpaFacade;
import sv.gob.aduana.mtto.bean.EventLogFacade;
import sv.gob.aduana.mtto.bean.PersonFacade;
import sv.gob.mh.afpa.AFPAService;
import sv.gob.mh.oim.OIMService;
import sv.gob.mh.oim.pojo.AuthDGII;
import sv.gob.mh.oim.pojo.Custom;
import sv.gob.mh.oim.pojo.DataRequest;
import sv.gob.mh.oim.pojo.Names;
import sv.gob.mh.oim.pojo.TokenDGII;
import sv.gob.mh.oim.pojo.UsuarioDGIIRequest;
import sv.gob.mh.oim.pojo.UsuarioDGIIResponse;

@RequestScoped
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJAXRSCXFCDIServerCodegen", date = "2021-12-30T20:30:53.086Z")
public class DgaApiServiceImpl implements DgaApiService {

    @EJB
    protected CatLvloneFacade lvloneFacade;

    @EJB
    protected CatLvltwoFacade lvltwoFacade;

    @EJB
    protected CatLvlthreeFacade lvlthreeFacade;

    @EJB
    protected CatLvlfourFacade lvlfourFacade;

    @EJB
    protected CatPositionFacade positionFacade;

    @EJB
    protected CatAttributeFacade attributeFacade;

    @EJB
    protected PersonFacade personFacade;

    @EJB
    protected OIMService oimService;

    @EJB
    protected AFPAService afpaService;

    @EJB
    protected CatalogService catalogService;

    @EJB
    protected RequestService requestService;

    @EJB
    protected FormService formService;

    @EJB
    protected FlowService flowService;

    @EJB
    protected OtherService otherService;

    @EJB
    protected CatTypeafpaFacade catTypeafpaFacade;

    @EJB
    protected EventLogFacade eventLogFacade;
    

    //Catalogo de tipos de solicitud (Interna y Externa)
    @Override
    public Response cAT0001(SecurityContext securityContext) {
        List<CatalogFormType> catalogFormTypes = catalogService.getCatalogFormTypes();

        return Response.ok().entity(catalogFormTypes).build();
    }

    //Catalogo de AFPA
    @Override
    public Response cAT0002(SecurityContext securityContext) {
        List<CatalogTypeAFPA> catalog = catalogService.getCatalogTypeAFPA();

        return Response.ok().entity(catalog).build();
    }

    //Catalogo de Aduanas
    @Override
    public Response cAT0003(SecurityContext securityContext) {

        List<Custom> customs = oimService.getCustoms();
        List<io.swagger.model.CatalogCustoms> r = new ArrayList<>();

        customs.forEach(s -> {
            CatalogCustoms c = new CatalogCustoms();
            c.setId(s.getKey());
            c.setValue(s.getValue());
            r.add(c);
        });

        CatalogCustoms cna = new CatalogCustoms();

        cna.setId("NA");
        cna.setValue("N/A");

        r.add(cna);

        return Response.ok().entity(r).build();
    }

    //Catalogo de tipos de solicitantes externos
    @Override
    public Response cAT0004(SecurityContext securityContext) {
        List<CatalogExternalApplicantType> catalog = catalogService.getCatalogExtAppType();

        return Response.ok().entity(catalog).build();
    }

    //Catalogo de tipos de perfiles externos
    @Override
    public Response cAT0005(SecurityContext securityContext) {
        String profile = "EXTERNO";
        List<CatalogProfile> catalog = catalogService.getCatalogProfileByRole(profile);

        return Response.ok().entity(catalog).build();
    }

    //Catalogo de tipos de sistemas externos
    @Override
    public Response cAT0006(SecurityContext securityContext) {
        List<CatalogSystem> catalog = catalogService.getCatalogSystem("EXTERNO");

        return Response.ok().entity(catalog).build();
    }

    @Override
    public Response cAT0007(SecurityContext securityContext) {

        List<CatalogAttribute> catalog = catalogService.getCatalogAttribute();

        return Response.ok().entity(catalog).build();

    }

    @Override
    public Response cAT0008(SecurityContext securityContext) {
        List<sv.gob.aduana.entity.CatLvlone> levels = lvloneFacade.getFindByEnabled();

        List<CatalogLevelOne> result = new ArrayList<>();

        levels.forEach(s -> {
            result.add(FormTranslator.dbToAPI(s));
        });

        return Response.ok().entity(result).build();
    }

    @Override
    public Response cAT0009(String levelOneId, SecurityContext securityContext) {
        List<sv.gob.aduana.entity.CatLvltwo> levels = lvltwoFacade.getFindByEnabled(levelOneId);

        List<CatalogLevelTwo> result = new ArrayList<>();

        levels.forEach(s -> {
            result.add(FormTranslator.dbToAPI(s));
        });

        return Response.ok().entity(result).build();

    }

    @Override
    public Response cAT0010(String levelOneId, String levelTwoId, SecurityContext securityContext) {
        List<sv.gob.aduana.entity.CatLvlthree> levels = lvlthreeFacade.getFindByEnabled(levelTwoId);

        List<CatalogLevelThree> result = new ArrayList<>();

        levels.forEach(s -> {
            result.add(FormTranslator.dbToAPI(s));
        });

        return Response.ok().entity(result).build();
    }

    @Override
    public Response cAT0011(String levelOneId, String levelTwoId, String levelThreeId, SecurityContext securityContext) {
        List<sv.gob.aduana.entity.CatLvlfour> levels = lvlfourFacade.getFindByEnabled(levelThreeId);

        List<CatalogLevelFour> result = new ArrayList<>();

        levels.forEach(s -> {
            result.add(FormTranslator.dbToAPI(s));
        });

        return Response.ok().entity(result).build();
    }

    @Override
    public Response cAT0012(SecurityContext securityContext) {
        List<sv.gob.aduana.entity.CatPosition> positions = positionFacade.getFindByEnabled();

        List<CatalogPosition> result = new ArrayList<>();

        positions.forEach(s -> {
            result.add(FormTranslator.dbToAPI(s));
        });

        return Response.ok().entity(result).build();
    }

    @Override
    public Response cAT0013(String role, SecurityContext securityContext) {
        List<CatalogProfile> catalog = new ArrayList<>();
        if (role == null || role.equals("NA")) {
            return Response.ok().entity(catalog).build();
        }

        catalog = catalogService.getCatalogProfileByRole(role);

        return Response.ok().entity(catalog).build();
    }

    @Override
    public Response cAT0014(String role, SecurityContext securityContext) {
        // do some magic!
        return Response.ok().entity("magic!").build();
    }

    @Override
    public Response cAT0015(String role, SecurityContext securityContext) {
        List<CatalogSystem> catalog = catalogService.getCatalogSystem(role);

        return Response.ok().entity(catalog).build();

    }

    @Override
    public Response cAT0016(String system, SecurityContext securityContext) {
        List<CatalogGroup> catalog = catalogService.getCatalogGroup(system);

        return Response.ok().entity(catalog).build();
    }

    @Override
    public Response cAT0018(SecurityContext securityContext) {
        List<CatalogRequestType> catalog = catalogService.getCatalogTypeRequest();

        return Response.ok().entity(catalog).build();
    }

    @Override
    public Response cRUD0007(CatalogProfileExternal body, SecurityContext securityContext) {
        // do some magic!
        return Response.ok().entity("magic!").build();
    }

    @Override
    public Response cRUD0008(CatalogProfileExternal body, SecurityContext securityContext) {
        // do some magic!
        return Response.ok().entity("magic!").build();
    }

    @Override
    public Response cRUD0009(CatalogSystemExternal body, SecurityContext securityContext) {
        // do some magic!
        return Response.ok().entity("magic!").build();
    }

    @Override
    public Response cRUD0010(CatalogSystemExternal body, SecurityContext securityContext) {
        // do some magic!
        return Response.ok().entity("magic!").build();
    }

    @Override
    public Response cRUD0011(String role, CatalogProfile body, SecurityContext securityContext) {
        // do some magic!
        return Response.ok().entity("magic!").build();
    }

    @Override
    public Response cRUD0012(String role, CatalogProfile body, SecurityContext securityContext) {
        // do some magic!
        return Response.ok().entity("magic!").build();
    }

    @Override
    public Response cRUD0013(String role, CatalogResource body, SecurityContext securityContext) {
        // do some magic!
        return Response.ok().entity("magic!").build();
    }

    @Override
    public Response cRUD0014(String role, CatalogResource body, SecurityContext securityContext) {
        // do some magic!
        return Response.ok().entity("magic!").build();
    }

    @Override
    public Response cRUD0015(String role, CatalogSystem body, SecurityContext securityContext) {
        // do some magic!
        return Response.ok().entity("magic!").build();
    }

    @Override
    public Response cRUD0016(String role, CatalogSystem body, SecurityContext securityContext) {
        // do some magic!
        return Response.ok().entity("magic!").build();
    }

    @Override
    public Response cRUD0017(String system, CatalogGroup body, SecurityContext securityContext) {
        // do some magic!
        return Response.ok().entity("magic!").build();
    }

    @Override
    public Response cRUD0018(String system, CatalogGroup body, SecurityContext securityContext) {
        // do some magic!
        
        return Response.ok().entity("magic!").build();
    }

    @Override
    public Response dGADASHFIN001(String role, String uid, SecurityContext securityContext) {
        Set<Form> forms = formService.getFormFinished(role, uid);

        return Response.ok().entity(forms).build();
    }

    @Override
    public Response dGADASHFIN002(String role, String uid, SecurityContext securityContext) {
        Set<Form> forms = null;
            
        if(uid.equals("*")){
            forms = formService.getByRoleStep(role);
        }else{
            forms = formService.getFormPending(role, uid);
        }
        
        return Response.ok().entity(forms).build();
    }

    @Override
    public Response dGADASHFIN003(String id, SecurityContext securityContext) {
        // do some magic!
        String formId = null;
    
        if (id.contains("REQ-")) {
            sv.gob.aduana.entity.Request r = requestService.getRequest(id);

            if (r == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            
            formId = r.getForm().getId();
            
            try {
                 String sessionId = requestService.getSessionId();
                 eventLogFacade.writeInLog("DEBUG", "La mesa de servicio responde con: "+sessionId);
             } catch (Exception e) {
                 e.printStackTrace();
             }
        
            
        }else{
            formId=id;
        }

        sv.gob.aduana.entity.Formu formDB = null;

        
            
     
        
        formDB = formService.getFormAll(formId);

        if (formDB == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Form f = FormTranslator.dbToAPI(formDB, true);

        return Response.ok().entity(f).build();

    }

    @Override
    public Response dGAOTHER001(String requestId, SecurityContext securityContext) {
        // do some magic!
        return Response.ok().entity("magic!").build();
    }

    @Override
    public Response dGAOTHER002(String requestId, Other body, SecurityContext securityContext) {
        // do some magic!
        sv.gob.aduana.entity.Other other = FormTranslator.apiToDB(body);

        try {
            other = requestService.insertOther(other, requestId);

        } catch (Exception ex) {
            return handleError(ex);
        }

        Other o = FormTranslator.dbToAPI(other);

        return Response.ok().entity(o).build();
    }

    @Override
    public Response dGAOTHER003(String requestId, Other body, SecurityContext securityContext) {
        // do some magic!  // do some magic!
        sv.gob.aduana.entity.Other other = FormTranslator.apiToDB(body);

        try {
            other = requestService.deleteOther(requestId, other);

        } catch (Exception ex) {
            return handleError(ex);
        }

        Other o = FormTranslator.dbToAPI(other);

        return Response.ok().entity(o).build();
    }

    @Override
    public Response dGAOTHER005(String requestId, Other body, SecurityContext securityContext) {
        // do some magic!
        return Response.ok().entity("magic!").build();
    }

    //Invocar al servicio de DGA
    @Override
    public Response dGAPROF004(String requestId, Other other, SecurityContext securityContext) {
        // do some magic!
        sv.gob.aduana.entity.Other o = FormTranslator.apiToDB(other);

        o = otherService.createServiceDesk(o, requestId);

        other = FormTranslator.dbToAPI(o);

        return Response.ok().entity(other).build();
    }

    @Override
    public Response dGAPROFI001(String requestId, SecurityContext securityContext) {
        // do some magic!
        return Response.ok().entity("magic!").build();
    }

    @Override
    public Response dGAPROFI002(String requestId, Profile body, SecurityContext securityContext) {
        // do some magic!
        sv.gob.aduana.entity.Profile profile = FormTranslator.apiToDB(body);

        try {
            profile = requestService.insertProfile(profile, requestId);

        } catch (Exception ex) {
            return handleError(ex);
        }

        Profile p = FormTranslator.dbToAPI(profile);

        return Response.ok().entity(p).build();
    }

    @Override
    public Response dGAPROFI003(String requestId, Profile body, SecurityContext securityContext) {
        // do some magic!
        sv.gob.aduana.entity.Profile profile = FormTranslator.apiToDB(body);

        try {
            profile = requestService.deleteProfile(requestId, profile);

        } catch (Exception ex) {
            return handleError(ex);
        }

        Profile p = FormTranslator.dbToAPI(profile);

        return Response.ok().entity(p).build();
    }

    @Override
    public Response dGAPROFI004(String requestId, Profile body, SecurityContext securityContext) {
        // do some magic!
        return Response.ok().entity("magic!").build();
    }

    @Override
    public Response dGAPROFI005(String requestId, Profile body, SecurityContext securityContext) {
        // do some magic!
        return Response.ok().entity("magic!").build();
    }

    @Override
    public Response dGARESO001(String requestId, SecurityContext securityContext) {
        // do some magic!
        return Response.ok().entity("magic!").build();
    }

    @Override
    public Response dGARESO002(String requestId, io.swagger.model.Resource body, SecurityContext securityContext) {
        // do some magic!
        return Response.ok().entity("magic!").build();
    }

    @Override
    public Response dGARESO003(String requestId, io.swagger.model.Resource body, SecurityContext securityContext) {
        // do some magic!
        return Response.ok().entity("magic!").build();
    }

    @Override
    public Response dGASYST001(String requestId, SecurityContext securityContext) {
        // do some magic!
        return Response.ok().entity("magic!").build();
    }

    @Override
    public Response dGASYST002(String requestId, System body, SecurityContext securityContext) {
        // do some magic!
        sv.gob.aduana.entity.System system = FormTranslator.apiToDB(body);

        try {
            system = requestService.insertSystem(system, requestId);
        } catch (Exception ex) {
            return handleError(ex);
        }

        System s = FormTranslator.dbToAPI(system);

        return Response.ok().entity(s).build();
    }

    @Override
    public Response dGASYST003(String requestId, System body, SecurityContext securityContext) {
        // do some magic!
        sv.gob.aduana.entity.System system = FormTranslator.apiToDB(body);

        try {
            system = requestService.deleteSystem(requestId, system);

        } catch (Exception ex) {
            return handleError(ex);
        }

        System s = FormTranslator.dbToAPI(system);

        return Response.ok().entity(s).build();
    }

    @Override
    public Response fLOW0001(Flow flow, SecurityContext securityContext) {
        //En este se van a manejar los dos escenarios
        try {

            String requestId = flowService.addStep(flow);

            if (flowService.allPendingAreFinished(requestId)) {
                if (flowService.allRequestIsFinished(requestId)) {
                    flowService.closeFlow(requestId);
                } else {
                    flowService.moveStep(requestId);

                    if (flowService.isStillRequestToApprove(requestId)) {
                        flowService.sendStep(requestId);
                    } else {
                        flowService.rejectFlow(requestId);
                        flowService.closeFlow(requestId);
                    }

                }
            }

            return Response.ok().entity(flow).build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Response.serverError().build();

    }

    @Override
    public Response fLOW0002(Flow body, SecurityContext securityContext) {
        return fLOW0001(body, securityContext);
    }

    @Override
    public Response fLOW0003(Request body, SecurityContext securityContext) {
        // do some magic!
        return Response.ok().entity(body).build();
    }

    @Override
    public Response fLOW0004(String requestId, String processId, String roleId, SecurityContext securityContext) {
        try {

            flowService.createFlow(requestId);
            
            
            requestService.createTableAccounts(requestId);

        } catch (Exception ex) {
            return handleError(ex);
        }

        return Response.ok().build();
    }

    @Override
    public Response fORM0001(Form body, SecurityContext securityContext) {
        //Solo se va guardar el applicante y el formulario
        sv.gob.aduana.entity.Formu form = null;

        try {
            form = formService.createForm(body);

        } catch (Exception ex) {
            return handleError(ex);
        }

        Form f = FormTranslator.dbToAPI(form);

        return Response.ok().entity(f).build();
    }

    @Override
    public Response fORM0002(Form body, SecurityContext securityContext) {

        sv.gob.aduana.entity.Applicant applicant = FormTranslator.apiToDB(body.getApplicant());
        sv.gob.aduana.entity.Formu form = FormTranslator.apiToDB(body);

        try {
            form = formService.editForm(applicant, form);

        } catch (Exception ex) {
            return handleError(ex);
        }

        Form f = FormTranslator.dbToAPI(form);

        return Response.ok().entity(f).build();
    }

    @Override
    public Response fORM0003(String formId, Request body, SecurityContext securityContext) {

        sv.gob.aduana.entity.Request request = FormTranslator.apiToDB(body);
        try {
            request = requestService.getRequest(request.getId());
            String formLoadId = request.getForm().getId();

            if(formId.contains("createCaseHelpDesk")){
               // request = requestService.createServiceDesk(request.getId());
               // request = requestService.editRequest(formLoadId, request);
                request.setHelpDeskId("DEMO-123-213-123");
            }else{
                request = requestService.startupOIM(request.getId());
                request = requestService.editRequest(formLoadId, request);
            }
            
        } catch (Exception ex) {
            return handleError(ex);
        }

        Request r = FormTranslator.dbToAPI(request);

        return Response.ok().entity(r).build();
    }

    @Override
    public Response fORM0004(String formId, Request body, SecurityContext securityContext) {

        sv.gob.aduana.entity.Request request = FormTranslator.apiToDB(body);

        try {

            request = requestService.insertRequest(formId, request);

        } catch (Exception ex) {
            return handleError(ex);
        }

        Request r = FormTranslator.dbToAPI(request);

        return Response.ok().entity(r).build();
    }

    @Override
    public Response lOGIN0001(Login body, SecurityContext securityContext) {

        Login response = flowService.flowLogin(body);

        if (response == null) {
            return Response.serverError().build();
        }

        return Response.ok().entity(response).build();
    }

    @Override
    public Response rEQ0001(String formType, String requestType, String document, SecurityContext securityContext) {
        // do some magic!
        Person person = new Person();
        Integer status = 400;

        eventLogFacade.writeInLog("DEBUG", "req0001 : " + formType + " " + requestType + " " + document);

        if (RequestTypeService.isIntern(formType) || formType.equals("SolicitudNoAFPA")) {
            if (RequestTypeService.isRequestForNewData(requestType)) {
                if (oimService.existUser(document)) {
                    status = 406;
                } else {
                    person = oimService.generateUser(document);

                    if (formType.contains("SolicitudNoAFPA")) {
                        person.setMail(null);
                    }

                    status = 200;
                }
            } else {
                if (!oimService.existUser(document)) {
                    status = 404;
                } else {
                    person = oimService.loadUser(document, requestType);

                    if (person == null) {
                        status = 416;
                    } else {

                        //Cargar nombres actualizados
                        Names names = oimService.getNames(person.getDocument());

                        person.setFullName(names.getNombre() + " " + names.getApellidos());
                        person.setLastName(names.getApellidos());
                        person.setSurName(names.getNombre());

                        sv.gob.aduana.entity.CatPosition p = positionFacade.getPosition(person.getPosition().getValue());
                        CatalogPosition position = FormTranslator.dbToAPI(p);
                        person.setPosition(position);

                        sv.gob.aduana.entity.CatLvlone p1 = lvloneFacade.getLevelOne(person.getLevelOne().getValue());
                        CatalogLevelOne levelOne = FormTranslator.dbToAPI(p1);
                        person.setLevelOne(levelOne);

                        sv.gob.aduana.entity.CatLvltwo p2 = lvltwoFacade.getLevelTwo(person.getLevelTwo().getValue());
                        CatalogLevelTwo levelTwo = FormTranslator.dbToAPI(p2);
                        person.setLevelTwo(levelTwo);

                        sv.gob.aduana.entity.CatLvlthree p3 = lvlthreeFacade.getLvlThree(person.getLevelThree().getValue());
                        CatalogLevelThree levelThree = FormTranslator.dbToAPI(p3);
                        person.setLevelThree(levelThree);

                        sv.gob.aduana.entity.CatLvlfour p4 = lvlfourFacade.getLvlFour(person.getLevelFour().getValue());
                        CatalogLevelFour levelFour = FormTranslator.dbToAPI(p4);
                        person.setLevelFour(levelFour);

                        sv.gob.aduana.entity.CatAttribute p5 = attributeFacade.getAttribute(person.getAttribute().getValue());
                        CatalogAttribute catAttr = FormTranslator.dbToAPI(p5);
                        person.setAttribute(catAttr);

                        status = 200;
                    }
                }

            }

        }

        if (formType.contains("LoadPersonByAFPA")) {
            
            String[] afpaType = formType.split("%");
            
            String idAfpaType = afpaType[1].trim();
            
            
            eventLogFacade.writeInLog("DEBUG", "Cargando el listado de AFPA: " + document + " " + idAfpaType);

            if (!afpaService.existUser(document,idAfpaType)) {
                status = 412;

            } else {

                if (RequestTypeService.isRequestForNewData(requestType)) {
                    if (oimService.existUser(document)) {
                        status = 406;
                    } else {
                        person = oimService.generateUser(document);
                        
                        person.setMail(null);
                          

                        person = afpaService.getAFPA(document, person,idAfpaType);

                        CatTypeafpa c = catTypeafpaFacade.getCatalogTypeAFPAByName(
                                person.getTypeAFPA().getValue(),
                                person.getCodeDeclarant());

                        CatalogTypeAFPA t = FormTranslator.dbToAPI(c);

                        person.setTypeAFPA(t);

                        status = 200;
                    }
                } else {
                    if (!oimService.existUser(document)) {
                        status = 404;
                    } else {
                        person = oimService.loadUser(document, requestType);

                        if (person == null) {
                            status = 416;
                        } else {

                            person = afpaService.getAFPA(document, person,idAfpaType);

                            CatTypeafpa c = catTypeafpaFacade.getCatalogTypeAFPAByName(
                                    person.getTypeAFPA().getValue(),
                                    person.getCodeDeclarant());

                            CatalogTypeAFPA t = FormTranslator.dbToAPI(c);

                            person.setTypeAFPA(t);

                            status = 200;
                        }
                    }
                }
            }

        }
        
        
        

        eventLogFacade.writeInLog("DEBUG", "req0001 result : " + status + " Person: " + person.getUid());

        if (status == 406) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        if (status == 404) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        if (status == 400) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (status == 501) {
            return Response.status(Response.Status.NOT_IMPLEMENTED).build();
        }
        if (status == 412) {
            return Response.status(Response.Status.PRECONDITION_FAILED).build();
        }
        if (status == 416) {
            return Response.status(Response.Status.REQUESTED_RANGE_NOT_SATISFIABLE).build();
        }

        return Response.ok().entity(person).build();
    }

    @Override
    public Response rEQ0002(String formType, String document,
            SecurityContext securityContext
    ) {       
        
        //Carga de Usuario
        Applicant applicant = null;

        if (formType.contains("Aplicante")) {
            applicant = new Applicant();
            Boolean isNumeric = false;
            try {
                if (Long.valueOf(document) > 0) {
                    isNumeric = true;
                }
            } catch (Exception e) {
            }

            if (!isNumeric) {
                applicant = oimService.getApplicant(document);
            } else {
                applicant = oimService.getApplicantByDocument(document);
            }

            if (applicant == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

        }

        if (formType.contains("AgenteAFPA")) {
            applicant = null;
            
            HashSet<CatTypeafpa> afpaList = afpaService.loadTypesAFPA(document);
            
            if (afpaList !=null && !afpaList.isEmpty()) {
                Names name = oimService.getNames(document);

                Person person = new Person();
                String code = null;
                
                for(CatTypeafpa c : afpaList){
                    code = c.getId();
                }
                
                person = afpaService.getAFPA(document, person, code);

                applicant = new Applicant();

                applicant.setExternalType(new CatalogExternalApplicantType());
                applicant.setExternalCodeDeclarant(person.getCodeDeclarant());

                applicant.setName(name.getNombre() + " " + name.getApellidos());
            }

            if (applicant == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

        }

        if (formType.contains("PersonalNoAFPA")) {
            applicant = new Applicant();

            Names name = oimService.getNames(document);

            applicant.setExternalType(new CatalogExternalApplicantType());

            applicant.setName(name.getNombre() + " " + name.getApellidos());

        }

        if (formType.contains("Elaborador")) {
            applicant = oimService.getApplicant(document);

            if (applicant == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }

        if (RequestTypeService.isIntern(formType)) {
            //Validar Si existe en oim 
            applicant = oimService.getApplicantByDocument(document);

            if (applicant == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }

        if (RequestTypeService.isExtern(formType)) {
            //Validar si existe en AFPA

            //Validar Si existe en oim 
            applicant = oimService.getApplicantByDocument(document);

            if (applicant == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }

        try {
            sv.gob.aduana.entity.CatPosition p = positionFacade.getPosition(applicant.getPosition().getValue());
            CatalogPosition position = FormTranslator.dbToAPI(p);
            applicant.setPosition(position);
        } catch (Exception e) {
            CatalogPosition position = new CatalogPosition();
            position.setId("NA");
            position.setValue("N/A");
            position.setStatus("ENABLED");
            applicant.setPosition(position);
        }

        try {
            sv.gob.aduana.entity.CatAttribute c = attributeFacade.getAttribute(applicant.getAttribute().getValue());
            CatalogAttribute attribute = FormTranslator.dbToAPI(c);
            applicant.setAttribute(attribute);
        } catch (Exception e) {
            CatalogAttribute attribute = new CatalogAttribute();
            attribute.setId("NA");
            attribute.setValue("N/A");
            attribute.setStatus("ENABLED");
            applicant.setAttribute(attribute);
        }

        return Response.ok().entity(applicant).build();
    }

    @Override
    public Response uTIL0002(String requestId, SecurityContext securityContext
    ) {
        // Cargar el formulario
        try {
            flowService.sendStep(requestId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Realizar el envio Logo
        return Response.ok().build();
    }

    @Override
    public Response uTIL001(Email body, SecurityContext securityContext
    ) {
        // do some magic!
        return Response.ok().entity("magic!").build();
    }

    @Override
    public Response iDE001(String requestId, SecurityContext securityContext) {
        List<io.swagger.model.Account> accounts = requestService.getAccountsUsed(requestId);

        return Response.ok().entity(accounts).build();
    }

    @Override
    public Response iDE002(String requestId, SecurityContext securityContext) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Response iDE003(String requestId, Account body, SecurityContext securityContext) {

        if (body.getLogin().equals("NUEVA")) {
            body.setLogin(body.getName());
        }

        requestService.saveAccountInRequest(requestId, body);

        return Response.ok().entity(body).build();
    }

    @Override
    public Response iDE004(String requestId, Account body, SecurityContext securityContext) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Response iDE005(String requestId, String accountId, SecurityContext securityContext) {
        List<io.swagger.model.Account> filterAccounts = new ArrayList<>();

        try {
            filterAccounts = requestService.getAccoutCreatedInUser(requestId, accountId);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Response.ok().entity(filterAccounts).build();
    }

    @Override
    public Response lOG001(String formId, SecurityContext securityContext) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Response handleError(Exception ex) {
        ex.printStackTrace();
        return Response.serverError().entity(ex).build();
    }

    @Override
    public Response cat0019(String document, SecurityContext securityContext) {
        Set<sv.gob.aduana.entity.CatTypeafpa> afpaUniqueList = afpaService.loadTypesAFPA(document);
        List<CatalogTypeAFPA> afpaList = new ArrayList<>();
        
        afpaUniqueList.forEach( s -> {
            afpaList.add(FormTranslator.dbToAPI(s));
        });
        
        return Response.ok().entity(afpaList).build();
    }

}
