/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.aduana.quartz;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
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
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.transaction.UserTransaction;
import sv.gob.aduana.entity.Formu;
import sv.gob.aduana.entity.Other;
import sv.gob.aduana.entity.Person;
import sv.gob.aduana.entity.Profile;
import sv.gob.aduana.entity.Request;
import sv.gob.aduana.form.RequestTypeService;
import sv.gob.aduana.mtto.bean.FormuFacade;
import sv.gob.aduana.mtto.bean.GeneratorIdFacade;
import sv.gob.aduana.mtto.bean.OtherFacade;
import sv.gob.aduana.mtto.bean.PersonFacade;
import sv.gob.aduana.mtto.bean.ProfileFacade;
import sv.gob.aduana.mtto.bean.RequestFacade;
import sv.gob.aduana.mtto.bean.SystemFacade;
import sv.gob.mh.oim.OIMService;
import sv.gob.mh.oim.pojo.Email;
import sv.gob.mh.oim.pojo.HelpDeskResponse;
import sv.gob.mh.oim.pojo.MessageQueue;

/**
 *
 * @author Datum-Redsoft
 */
@Startup
@Singleton
public class ScheduledDateJob {

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

    //@Schedule(second = "*/30", minute = "*", hour = "*", info = "MyTimer", persistent = false)
    @Schedule(second = "0", minute = "0", hour = "8", info = "MyTimer", persistent = false)
    public void execute() {
        //Envio de vencidos
        // System.out.println("Startup lookup Scheduled...");

        Set<String> requestsIdsExpired = new HashSet<>();
        Set<String> requestsIdsStarting = new HashSet<>();
        

        List<Person> persons = personFacade.findExpired();
        List<Profile> profiles = profileFacade.findExpired();
        List<sv.gob.aduana.entity.System> systems = systemFacade.findExpired();
        List<Other> others = otherFacade.findExpired();

        if (persons != null) {
            persons.forEach((p) -> {
                requestsIdsExpired.add(requestFacade.getRequestIdByPerson(p.getId()));
            });
        }

        if (profiles != null) {
            profiles.forEach((p) -> {
                requestsIdsExpired.add(p.getRequest().getId());
            });
        }

        if (systems != null) {
            systems.forEach((p) -> {
                requestsIdsExpired.add(p.getRequest().getId());
            });
        }

        if (others != null) {
            others.forEach((p) -> {
                requestsIdsExpired.add(p.getRequest().getId());
            });
        }

        if (!requestsIdsExpired.isEmpty()) {
            //  System.out.println("Solicitudes a vencer : " + requestsIdsExpired.size());
            sendEmailToExpired(requestsIdsExpired);
        }

        persons = personFacade.findStarting();
        profiles = profileFacade.findStarting();
        systems = systemFacade.findStarting();
        others = otherFacade.findStarting();
        
        if (persons != null) {
            persons.forEach((p) -> {
                requestsIdsStarting.add(requestFacade.getRequestIdByPerson(p.getId()));
            });
        }
        
        
        if (profiles != null) {
            profiles.forEach((p) -> {
                requestsIdsStarting.add(p.getRequest().getId());
            });
        }

        if (systems != null) {
            systems.forEach((p) -> {
                requestsIdsStarting.add(p.getRequest().getId());
            });
        }

        if (others != null) {
            others.forEach((p) -> {
                requestsIdsStarting.add(p.getRequest().getId());
            });
        }

        if (!requestsIdsStarting.isEmpty()) {
            sendEmailToStarting(requestsIdsStarting);
        }

    }

    private void sendEmailToExpired(Set<String> requestsIdsExpired) {
        String template = readAllFile("servicedesk.html");
        Boolean isAllowedToSend = false;
        
        Date fecha = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        StringBuilder stringBuilder = new StringBuilder();
        template = template.replaceAll("\\{title\\}", "Formulario de DGA - Correo de recordatorio pera permisos a vencer - " + format.format(fecha));
        stringBuilder.append("<h3>Listado de permisos a dar de baja:</h3> <br/><br/><br/>");

        requestsIdsExpired.forEach(s -> {
            
            Request r = requestFacade.find(s);
            Formu f = formuFacade.find(r.getForm().getId());
            
            if(f.getClosed() == 1){
                String helpDeskId = createServiceDesk(s, "Formulario de DGA - Identidades y Permisos a vencer - "+ r.getId() + " - " + format.format(fecha));
                stringBuilder.append(createMail(r, helpDeskId));
               
            }

        });
        
        if(stringBuilder.toString().contains("Formulario de DGA")){
            isAllowedToSend=true;
        }
        
        template = template.replaceAll("\\{solicitudes\\}", stringBuilder.toString());
        Email email = new Email();

        email.setMessage(template);
        if (System.getProperty("custom.enviroment.oim").equals("TEST")) {
            email.setTitle("[TEST] Formulario de DGA - Correo de recordatorio pera permisos a vencer - " + format.format(fecha));
            email.setTo("edson.artiga@datumredsoft.com");
        } else {
            email.setTitle("Formulario de DGA  Correo de recordatorio pera permisos a vencer - " + format.format(fecha));
            email.setTo("asistencia@aduana.gob.sv");
        }
        //  email.setTo("edson.artiga@datumredsoft.com");
        if(isAllowedToSend)
            oimService.sendMail(email);

    }

    private void sendEmailToStarting(Set<String> requestsIdsStarting) {
        String template = readAllFile("servicedesk.html");
        Boolean isAllowedToSend = false;
        
        Date fecha = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        StringBuilder stringBuilder = new StringBuilder();
        template = template.replaceAll("\\{title\\}", "Formulario de DGA - Identidades y Permisos - Correo de recordatorio para activar permisos -" + format.format(fecha));
        stringBuilder
                .append("Listado de permisos programados para activar este día: ");

        requestsIdsStarting.forEach(s -> {
            Request r = requestFacade.find(s);
            
            Formu f = formuFacade.find(r.getForm().getId());
            
            if(f.getClosed() == 1){
                String helpDeskId = createServiceDesk(s, "Formulario de DGA -  Correo de recordatorio para activar de permisos  -" + r.getId() + " - " + format.format(fecha));
                stringBuilder.append(createMail(r, helpDeskId));
            }
        });

        if(stringBuilder.toString().contains("Formulario de DGA")){
            isAllowedToSend=true;
        }
        
        template = template.replaceAll("\\{solicitudes\\}", stringBuilder.toString());
        Email email = new Email();

        email.setMessage(template);
        if (System.getProperty("custom.enviroment.oim").equals("TEST")) {
            email.setTitle("[TEST] Formulario de DGA - Correo de recordatorio para activar de permisos  - " + format.format(fecha));
            email.setTo("edson.artiga@datumredsoft.com");
        } else {
            email.setTitle("Formulario de DGA - Correo de recordatorio para activar de permisos  - " + format.format(fecha));
            email.setTo("asistencia@aduana.gob.sv");
        }
        // email.setTo("edson.artiga@datumredsoft.com");
        if(isAllowedToSend)
            oimService.sendMail(email);
    }

    public String createMail(Request r, String helpDeskId) {
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
        stringBuilder.append("<br/>Tipo de solicitud: ");
        stringBuilder.append(r.getTyperequest().getName());
        stringBuilder.append("<br/>Documento: ");
        stringBuilder.append(r.getPerson().getDocument());
        stringBuilder.append("<br/>Email: ");
        stringBuilder.append(r.getPerson().getMail());
        stringBuilder.append("<br/>Estado: ");
        stringBuilder.append(r.getPerson().getState());
        if(RequestTypeService.isExtern(r.getForm().getFormType())){
            stringBuilder.append("<br/>Tipo AFPA: ");
            stringBuilder.append(r.getPerson().getCatTypeafpaid().getName());
            stringBuilder.append("<br/>Estado: ");
            stringBuilder.append(r.getPerson().getCodedeclarant());
            stringBuilder.append("<br/>Resolución: ");
            stringBuilder.append(r.getPerson().getResolution());
        }else{
            stringBuilder.append("<br/>Nivel 1: ");
            stringBuilder.append(r.getPerson().getLevelone().getName());
            stringBuilder.append("<br/>Nivel 2: ");
            stringBuilder.append(r.getPerson().getLeveltwo().getName());
            stringBuilder.append("<br/>Nivel 3: ");
            stringBuilder.append(r.getPerson().getLevelthree().getName());
            stringBuilder.append("<br/>Nivel 4: ");
            stringBuilder.append(r.getPerson().getLevelfour().getName());
            
        }
        stringBuilder.append("<br/>Solicitud de accesos  para DGA: ");

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
                stringBuilder.append(p.getCustom());
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
                    stringBuilder.append(p.getCustom());
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

    public String createServiceDesk(String requestId, String title) {

        sv.gob.aduana.entity.Request request = requestFacade.find(requestId);
        sv.gob.aduana.entity.Formu form = formuFacade.find(request.getForm().getId());
        Boolean existRequestAvailable = false;
        String helpDeskId = null;

        try {
            Gson gson = new Gson();

            if (request.getHelpDeskId() != null) {
                return null;
            }

            Integer customerId = generatorIdFacade.find("CUSTOMERID").getCounter();
            Integer authId = generatorIdFacade.find("AUTHID").getCounter();
            Integer retId = generatorIdFacade.find("RETID").getCounter();
            Integer groupId = generatorIdFacade.find("GROUP_DGA").getCounter();
            String categoryId = request.getTyperequest().getCategoryId();

            String loginBody = callLoginUser();

            //System.out.println(loginBody);
            String typeRequestName = request.getTyperequest().getName();

            Type customType = new TypeToken<ArrayList<HelpDeskResponse>>() {
            }.getType();

            List<HelpDeskResponse> temp = gson.fromJson(loginBody, customType);

            String sessionId = null;

            for (HelpDeskResponse hp : temp) {
                if (hp.getField().equals("sessionId")) {
                    sessionId = hp.getValue();
                }
            }

            String responseBody = callCreateCase(customerId, authId, retId, groupId, categoryId, request, sessionId, requestId, typeRequestName, title);

            // System.out.println(responseBody);
            temp = gson.fromJson(responseBody, customType);

            for (HelpDeskResponse hp : temp) {
                if (hp.getField().equals("composedItemId")) {
                    helpDeskId = hp.getValue();
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return helpDeskId;
    }

    private String callLoginUser() {

        Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();
        String body = null;

        try {
            String bodyData = "[ \n "
                    + "{\"Field\":\"Tipo_autenticacion\",\"Value\":\"cliente externo\"},"
                    + "{\"Field\":\"username\",\"Value\":\"oim_user\"}, \n"
                    + "{\"Field\":\"password\",\"Value\":\"H@cienda#2022\"} \n"
                    + "] ";

            String url = null;
            if (System.getProperty("custom.enviroment.oim").equals("TEST")) {
                url = "http://172.26.1.155/";
            } else {
                url = "https://mesadeserviciosmh.mh.gob.sv/";
            }

            com.squareup.okhttp.Request request = new com.squareup.okhttp.Request.Builder()
                    .url(url+"ASDKAPI/api/v8.6/user/login")
                    .post(RequestBody.create(MediaType.parse("application/json"), bodyData))
                    .build();

            Response response = client.newCall(request).execute();

            body = response.body().string();

            response.body().close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return body;

    }

    private String callCreateCase(Integer customerId, Integer authId, Integer retId, Integer groupId, String categoryId,
            sv.gob.aduana.entity.Request r, String sessionId, String requestId, String requestName, String title) throws IOException {

        Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();
        
        String solicitud = createMail(r, "");

        String bodyData = "[  \n"
                + "  {\"Field\":\"AuthorId\",\"Value\":" + authId + "}, \n"
                + "  {\"Field\":\"CategoryId\",\"Value\":" + categoryId + "}, \n"
                + "  {\"Field\":\"CustomerId\",\"Value\":" + customerId + "}, \n"
                + "  {\"Field\":\"GroupId\",\"Value\":" + groupId + "}, \n"
                + "  {\"Field\":\"Subject\",\"Value\":\"" + title + "\"}, \n"
                + "  {\"Field\":\"Description\",\"Value\":\"" + solicitud.trim()
                        .replaceAll("\n", "<br/>")
                        .replaceAll("\"", "'")
                        .replaceAll("\\\\", "\\\\\\\\") + "\"}, \n"
                + "  {\"Field\":\"ProjectId\",\"Value\":3}, \n"
                + "  {\"Field\":\"RegistryTypeId\",\"Value\":2}, \n"
                + "  {\"Field\":\"ServiceId\",\"Value\":6}, \n"
                + "  {\"Field\":\"UrgencyId\",\"Value\":3}, \n"
                + "  {\"Field\":\"SlaId\",\"Value\":8} \n"
                + "] ";

        
        String url = null;
        if (System.getProperty("custom.enviroment.oim").equals("TEST")) {
            url = "http://172.26.1.155/";
        } else {
            url = "https://mesadeserviciosmh.mh.gob.sv/";
        }

        //System.out.println(bodyData);
        com.squareup.okhttp.Request request = new com.squareup.okhttp.Request.Builder()
                .url(url+"ASDKAPI/api/v8.6/item/add/4")
                .header("Authorization", sessionId)
                .post(RequestBody.create(MediaType.parse("application/json"), bodyData))
                .build();

        Response response = client.newCall(request).execute();

        String body = response.body().string();

        response.body().close();

        return body;

    }

}
