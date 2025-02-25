/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.aduana.form;

import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import java.io.IOException;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import com.google.gson.reflect.TypeToken;
import javax.transaction.UserTransaction;
import sv.gob.aduana.entity.Other;
import sv.gob.aduana.mtto.bean.ApplicantFacade;
import sv.gob.aduana.mtto.bean.FormuFacade;
import sv.gob.aduana.mtto.bean.GeneratorIdFacade;
import sv.gob.aduana.mtto.bean.OtherFacade;
import sv.gob.aduana.mtto.bean.ProfileFacade;
import sv.gob.aduana.mtto.bean.RequestFacade;
import sv.gob.aduana.mtto.bean.RequestflowFacade;
import sv.gob.aduana.mtto.bean.SystemFacade;
import sv.gob.mh.oim.pojo.HelpDeskResponse;
import java.lang.reflect.Type;
import java.util.List;
import sv.gob.aduana.mtto.bean.EventLogFacade;

/**
 *
 * @author Datum-Redsoft
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class OtherService {

    @Resource
    private UserTransaction utx;

    @EJB
    protected GeneratorIdFacade generatorIdFacade;

    @EJB
    protected FormuFacade formuFacade;

    @EJB
    protected RequestFacade requestFacade;

    @EJB
    protected ProfileFacade profileFacade;

    @EJB
    protected SystemFacade systemFacade;

    @EJB
    protected OtherFacade otherFacade;

    @EJB
    protected ApplicantFacade applicantFacade;

    @EJB
    protected RequestflowFacade requestflowFacade;
    
    
    @EJB
    protected EventLogFacade eventLogFacade;

    public Other createServiceDesk(Other other, String requestId) {

        try {
            utx.begin();
            Gson gson = new Gson();
            String helpDeskId = null;
            sv.gob.aduana.entity.Request request = requestFacade.find(requestId);
        

            if(other.getHelpdesk() != null)
                return other;
            
            
            if(!other.getStatus().equals("PENDIENTE DE ASIGNAR") && !other.getStatus().equals("PENDIENTE DE BORRAR")){
                return other;
            }
            
            Integer customerId = generatorIdFacade.find("CUSTOMERID").getCounter();
            Integer authId = generatorIdFacade.find("AUTHID").getCounter();
            Integer retId = generatorIdFacade.find("RETID").getCounter();
            Integer groupId = generatorIdFacade.find(other.getSendto()).getCounter();
            String categoryId = request.getTyperequest().getCategoryId();
            
            String loginBody = callLoginUser();
            
            String typeRequestName = request.getTyperequest().getName();
            
            
            Type customType = new TypeToken<ArrayList<HelpDeskResponse>>() {
            }.getType(); 
 
            List<HelpDeskResponse> temp = gson.fromJson(loginBody, customType);
            
            String sessionId = null;
            
            for(HelpDeskResponse hp : temp){
                if(hp.getField().equals("sessionId")){
                    sessionId = hp.getValue();
                }
            }
            
            String responseBody = callCreateCase(customerId,authId,retId,groupId,categoryId,other,sessionId,request,typeRequestName);
            
            
            temp = gson.fromJson(responseBody, customType);
            
              
            for(HelpDeskResponse hp : temp){
                if(hp.getField().equals("composedItemId")){
                    helpDeskId = hp.getValue();
                }
            }
            
            other.setHelpdesk(helpDeskId);
            other.setStatus("Enviado a Helpdesk con código : "+helpDeskId);
            
            otherFacade.edit(other);
            
            

            eventLogFacade.insertInAudit(request.getForm().getId(), request.getId(), "Se creo exitosamente el caso de soporte en Help Desk para otro acceso "+other.getId()+", con código "+helpDeskId,
                     "FORM", request.getForm().getCreateby());
             
            utx.commit();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return other;
    }
    
    
    private String callLoginUser() throws IOException {

        Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();

        String bodyData = "[ \n " +
                    "{\"Field\":\"Tipo_autenticacion\",\"Value\":\"cliente externo\"}," +
                    "{\"Field\":\"username\",\"Value\":\"oim_user\"}, \n" +
                    "{\"Field\":\"password\",\"Value\":\"H@cienda#2022\"} \n" +
                    "] ";
        
        String url=null;
        if (System.getProperty("custom.enviroment.oim").equals("TEST")) {
            url="http://172.26.1.155/";
        }else{
            url="https://mesadeserviciosmh.mh.gob.sv/";
        }
        
        Request request = new Request.Builder()
                .url(url+"ASDKAPI/api/v8.6/user/login")
                .post(RequestBody.create(MediaType.parse("application/json"), bodyData))
                .build();

        
        Response response = client.newCall(request).execute();


        String body = response.body().string();
        
        response.body().close();
        
        
        return body;

    }
    
    
    
    private String callCreateCase(Integer customerId, Integer authId, Integer retId, Integer groupId, String categoryId, 
            Other other, String sessionId, sv.gob.aduana.entity.Request r,String requestName) throws IOException {

        Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append("<br/>Solicitud: ");
        stringBuilder.append(r.getId());
        stringBuilder.append("<br/>Nombre de usuario: ");
        stringBuilder.append(r.getPerson().getFullname());
        stringBuilder.append("<br/>Usuario: ");
        stringBuilder.append(r.getPerson().getLogin());
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
        
        stringBuilder.append("<br/>Dirección a solicitar: ");
        stringBuilder.append(other.getSendto());
        stringBuilder.append("<br/>Solicitud de accesos para otra dirección: ");
        String data = "Plataforma: "+other.getPlatform()+"<br/><br/>"+
                "Solicitud: "+other.getData()+"<br/><br/>"+
                "Formulario DGA: "+r.getForm().getId()+"<br/>"+
                "Código de seguimiento DGA: "+r.getId()+"<br/>";
       
        stringBuilder.append(data);
        
        
        String title = "Solicitud de "+requestName+" para formulario de acceso para "+other.getPlatform();
        
        String bodyData = "[  \n" +
                "  {\"Field\":\"AuthorId\",\"Value\":"+authId+"}, \n" +
                "  {\"Field\":\"CategoryId\",\"Value\":"+categoryId+"}, \n" +
                "  {\"Field\":\"CustomerId\",\"Value\":"+customerId+"}, \n" +
                "  {\"Field\":\"GroupId\",\"Value\":"+groupId+"}, \n" +
                "  {\"Field\":\"Subject\",\"Value\":\""+title+"}, \n" +
                "  {\"Field\":\"Description\",\"Value\":\""+data
                        .trim()
                        .replaceAll("\"", "'")
                        .replaceAll("\n", "<br/>")
                        .replaceAll("\\\\", "\\\\\\\\")+"\"}, \n" +
                "  {\"Field\":\"ProjectId\",\"Value\":3}, \n" +
                "  {\"Field\":\"RegistryTypeId\",\"Value\":"+2+"}, \n" +
                "  {\"Field\":\"ServiceId\",\"Value\":6}, \n" + 
                "  {\"Field\":\"UrgencyId\",\"Value\":3}, \n" + 
                "  {\"Field\":\"SlaId\",\"Value\":8} \n" +
                "] ";
        
        String url=null;
        if (System.getProperty("custom.enviroment.oim").equals("TEST")) {
            url="http://172.26.1.155/";
        }else{
            url="https://mesadeserviciosmh.mh.gob.sv/";
        }
        
        Request request = new Request.Builder()
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
