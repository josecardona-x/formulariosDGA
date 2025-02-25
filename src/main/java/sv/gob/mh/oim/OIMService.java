/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mh.oim;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import io.swagger.model.Applicant;
import io.swagger.model.CatalogAttribute;
import io.swagger.model.CatalogLevelFour;
import io.swagger.model.CatalogLevelOne;
import io.swagger.model.CatalogLevelThree;
import io.swagger.model.CatalogLevelTwo;
import io.swagger.model.CatalogPosition;
import io.swagger.model.CatalogTypeAFPA;
import io.swagger.model.Login;
import io.swagger.model.Person;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import sv.gob.aduana.form.RequestTypeService;
import sv.gob.mh.oim.pojo.Account;
import sv.gob.mh.oim.pojo.Custom;
import sv.gob.mh.oim.pojo.Email;
import sv.gob.mh.oim.pojo.Identity;
import sv.gob.mh.oim.pojo.Names;
import sv.gob.mh.oim.pojo.User;

/**
 *
 * @author Datum-Redsoft
 */
@Stateless
public class OIMService {

    public boolean validarUser(Login login) {
        Gson gson = new Gson();
        
        User user = new User();
        user.setToken(login.getPassword());
        user.setUserName(login.getUser());
        String response = null;
        
        try {
            response = callLogin(user);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        
        return response != null;       
    }
    
    

    public void sendMail(Email email) {
        try {
            callSendMail(email);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public List<String> getRolesByUser(String user) {
        Gson gson = new Gson();
        List<String> temp = new ArrayList<>();
        List<String> roles = new ArrayList<>();

        try {
            String rolesStr = callGetRolesByUser(user);
            Type customType = new TypeToken<ArrayList<String>>() {
            }.getType();

            temp = gson.fromJson(rolesStr, customType);

            for (String t : temp) {

                if (t.equals("DGA_FORM_SERVICIO_CLIENTE")) {
                    roles.add("SERVICIO_AL_CLIENTE");
                }

                if (t.equals("DGA_FORM_SOLICITANTE")) {
                    roles.add("SOLICITANTE");
                }

                if (t.equals("DGA_FORM_APROBADOR")) {
                    roles.add("APROBADOR");
                }

                if (t.equals("DGA_FORM_SOLICITANTE_JUR")) {
                    roles.add("SOLICITANTE_JURIDICO");
                }

                if (t.equals("DGA_FORM_ELABORADOR_JUR")) {
                    roles.add("ELABORADOR_JURIDICO");
                }

            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return roles;
    }

    public List<String> getMailsByRole(String role) {
        Gson gson = new Gson();
        List<String> users = new ArrayList<>();

        try {
            String customStr = callGetMailsByRole(role);
            Type customType = new TypeToken<ArrayList<String>>() {
            }.getType();

            users = gson.fromJson(customStr, customType);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return users;
    }

    public List<Custom> getCustoms() {
        Gson gson = new Gson();
        List<Custom> customs = new ArrayList<>();

        try {
            String customStr = callCustoms();
            Type customType = new TypeToken<ArrayList<Custom>>() {
            }.getType();

            customs = gson.fromJson(customStr, customType);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return customs;
    }
    
    public Names getNames(String document) {
        Names name =  new Names();
         
        try {
            Gson gson = new Gson();

            String response = callGenerateIntUserLogin(document);

            if (response == null) {
                return name;
            }

            name = gson.fromJson(response, Names.class);


        } catch (IOException iOException) {
            iOException.printStackTrace();
        }

        return name;
    }

    public boolean existUser(String document) {
        try {
            Gson gson = new Gson();

            String response = callGenerateIntUserLogin(document);

            if (response == null) {
                return false;
            }

            Names name = gson.fromJson(response, Names.class);

            response = callVerifyNIT(document);

            if (response == null) {
                return false;
            }

            Identity identity = gson.fromJson(response, Identity.class);

            return identity.getEmail() != null;

        } catch (IOException iOException) {
            iOException.printStackTrace();
        }

        return false;
    }

    public Person generateUser(String document) {
        Person person = new Person();

        try {
            Gson gson = new Gson();

            String response = callGenerateIntUserLogin(document);

            if (response == null) {
                return null;
            }

            Names name = gson.fromJson(response, Names.class);

            person.setDocument(document);
            person.setFullName(name.getNombre() + " " + name.getApellidos());
            person.setLastName(name.getApellidos());
            person.setMail(name.getUid() + "@mh.gob.sv");
            person.setSurName(name.getNombre());
            person.setUid(name.getUid());

            person.setAttribute(new CatalogAttribute());
            person.setLevelFour(new CatalogLevelFour());
            person.setLevelOne(new CatalogLevelOne());
            person.setLevelThree(new CatalogLevelThree());
            person.setLevelTwo(new CatalogLevelTwo());
            person.setTypeAFPA(new CatalogTypeAFPA());
            person.setPosition(new CatalogPosition());

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return person;
    }
    
    public List<Account> getAccountByUser(String userLogin){
        List<Account> accounts = new ArrayList<>();
         Gson gson = new Gson();
         
        try {
            String accountStr = callAccountsFromUser(userLogin);
            
            Type localVarReturnType = new TypeToken<List<Account>>() {
            }.getType();
            
            accounts = gson.fromJson(accountStr, localVarReturnType);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return accounts;
    }

    public Person loadUser(String document,String requestType) {
        Person person = new Person();

        try {
            Gson gson = new Gson();

            String response = callUserFromOIMByNIT(document);

            if (response == null) {
                return null;
            }
            
            Identity identity = gson.fromJson(response, Identity.class);
            
            if(RequestTypeService.isEnabled(requestType)){
                if(identity.getEstado().equals("Active")){
                    return null;
                }
            }
            
            if(RequestTypeService.isDisabled(requestType)){
                if(identity.getEstado().equals("Disabled")){
                    return null;
                }
            }
            
            
            

            identityToPerson(person, identity);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return person;
    }

    public Applicant getApplicant(String userLogin) {
        Applicant applicant = new Applicant();
        try {
            Gson gson = new Gson();

            String response = callUserFromOIMByUserLogin(userLogin);

            
            if (response == null) {
                return null;
            }

            Identity identity = gson.fromJson(response, Identity.class);

            identityToApplicant(applicant, identity);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return applicant;
    }

    public Applicant getApplicantByDocument(String document) {
        Applicant applicant = new Applicant();
        try {
            Gson gson = new Gson();

            String response = callUserFromOIMByNIT(document);

            if (response == null) {
                return null;
            }

            Identity identity = gson.fromJson(response, Identity.class);

            identityToApplicant(applicant, identity);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return applicant;
    }

    private String callGenerateIntUserLogin(String document) throws IOException {
        Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://oimserver:17005/oimapps-rest-1.0/atribute/generate/int/userLogin/" + document)
                .get()
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            return null;
        }

        String body = response.body().string();

        response.body().close();

        return body;
    }
    
     private String callAccountsFromUser(String userLogin) throws IOException {

        Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://oimserver:17005/oimapps-rest-1.0/account/" + userLogin)
                .get()
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            return null;
        }

        String body = response.body().string();
        response.body().close();

        return body;
    }

    private String callVerifyNIT(String document) throws IOException {

        Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://oimserver:17005/oimapps-rest-1.0/atribute/verify/NIT/" + document)
                .get()
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            return null;
        }

        String body = response.body().string();
        response.body().close();

        return body;
    }

    private String callSendMail(Email e) throws IOException {

        Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();

        String emailStr = gson.toJson(e);

        Request request = new Request.Builder()
                .url("http://oimserver:17005/oimapps-rest-1.0/util/email")
                .post(RequestBody.create(MediaType.parse("application/json"), emailStr))
                .build();

        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) {
            return null;
        }

        String body = response.body().string();
        response.body().close();

        return body;
    }
    
      private String callLogin(User e) throws IOException {

        Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();

        String emailStr = gson.toJson(e);

        Request request = new Request.Builder()
                .url("http://oimserver:17005/oimapps-rest-1.0/login")
                .post(RequestBody.create(MediaType.parse("application/json"), emailStr))
                .build();

        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) {
            return null;
        }

        String body = response.body().string();
        response.body().close();

        return body;
    }

    private String callUserFromOIMByNIT(String document) throws IOException {

        Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://oimserver:17005/oimapps-rest-1.0/identity/findby/NIT/" + document)
                .get()
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            return null;
        }

        String body = response.body().string();
        response.body().close();

        return body;
    }

    private String callUserFromOIMByUserLogin(String userLogin) throws IOException {

        Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://oimserver:17005/oimapps-rest-1.0/identity/findby/userLogin/" + userLogin)
                .get()
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            return null;
        }

        String body = response.body().string();
        response.body().close();

        return body;
    }

    private String callCustoms() throws IOException {

        Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://oimserver:17005/oimapps-rest-1.0/atribute/lookup?lookup=Lookup.dga.form.customs")
                .get()
                .build();
        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) {
            return null;
        }

        String body = response.body().string();
        response.body().close();

        return body;

    }

    private String callGetMailsByRole(String role) throws IOException {

        Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://oimserver:17005/oimapps-rest-1.0/role/func/" + role)
                .get()
                .build();
        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) {
            return null;
        }

        String body = response.body().string();
        response.body().close();

        return body;

    }

    private String callGetRolesByUser(String user) throws IOException {

        Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://oimserver:17005/oimapps-rest-1.0/role/admin/" + user)
                .get()
                .build();
        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) {
            return null;
        }

        String body = response.body().string();
        response.body().close();

        return body;

    }

    private void identityToPerson(Person person, Identity identity) {
        
        
        person.setUid(identity.getUserLogin());
        person.setSurName(identity.getFirstName());
        person.setLastName(identity.getLastName());

        CatalogLevelOne levelOne = new CatalogLevelOne();
        levelOne.setId(identity.getNivel1());
        levelOne.setValue(identity.getNivel1());
        levelOne.setStatus("ENABLED");

        person.setLevelOne(levelOne);

        CatalogLevelTwo levelTwo = new CatalogLevelTwo();
        levelTwo.setId(identity.getNivel2());
        levelTwo.setValue(identity.getNivel2());
        levelTwo.setStatus("ENABLED");

        person.setLevelTwo(levelTwo);

        CatalogLevelThree levelThree = new CatalogLevelThree();
        levelThree.setId(identity.getNivel3());
        levelThree.setValue(identity.getNivel3());
        levelThree.setStatus("ENABLED");

        person.setLevelThree(levelThree);

        CatalogLevelFour levelFour = new CatalogLevelFour();
        levelFour.setId(identity.getNivel4());
        levelFour.setValue(identity.getNivel4());
        levelFour.setStatus("ENABLED");

        person.setLevelFour(levelFour);

        person.setFullName(identity.getFirstName() + " " + identity.getLastName());
        person.setMail(identity.getEmail());

        CatalogPosition position = new CatalogPosition();
        position.setId(identity.getCargo());
        position.setValue(identity.getCargo());
        position.setStatus("ENABLED");

        person.setPosition(position);

        CatalogAttribute attribute = new CatalogAttribute();
        attribute.setValue(identity.getPerfilUsuario());
        attribute.setId(identity.getPerfilUsuario());
        attribute.setStatus("ENABLED");

        person.setAttribute(attribute);

        person.setAlternativeMail(identity.getCorreoAlternativo());
        person.setPhoneNumber(identity.getHomePhone());
        person.setMobile(identity.getMobile());
        person.setOrganizationCode(identity.getOrganizationName());
        person.setUserType(identity.getTipoUsuario());
        person.setDocument(identity.getNit());
        person.setState(identity.getEstado());
        
        //person.setStartDate(identity.getStartDate());
    }

    private void identityToApplicant(Applicant applicant, Identity identity) {
        applicant.setDocument(identity.getNit());
        applicant.setMail(identity.getEmail());
        applicant.setName(identity.getFirstName() + " " + identity.getLastName());

        CatalogPosition position = new CatalogPosition();

        position.setId(identity.getCargo());
        position.setValue(identity.getCargo());
        position.setStatus("ENABLED");

        applicant.setPosition(position);

        CatalogAttribute attribute = new CatalogAttribute();
        attribute.setValue(identity.getPerfilUsuario());
        attribute.setId(identity.getPerfilUsuario());
        attribute.setStatus("ENABLED");

        applicant.setAttribute(attribute);
    }
}
