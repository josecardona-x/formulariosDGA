/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.aduana.form;

import io.swagger.model.Form;
import io.swagger.model.Other;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.UserTransaction;
import sv.gob.aduana.entity.Formu;
import sv.gob.aduana.entity.GeneratorId;
import sv.gob.aduana.mtto.bean.ApplicantFacade;
import sv.gob.aduana.mtto.bean.CatProfileFacade;
import sv.gob.aduana.mtto.bean.CatProfiledetailFacade;
import sv.gob.aduana.mtto.bean.EventLogFacade;
import sv.gob.aduana.mtto.bean.FormuFacade;
import sv.gob.aduana.mtto.bean.GeneratorIdFacade;
import sv.gob.aduana.mtto.bean.OtherFacade;
import sv.gob.aduana.mtto.bean.ProfileFacade;
import sv.gob.aduana.mtto.bean.RequestFacade;
import sv.gob.aduana.mtto.bean.RequestflowFacade;
import sv.gob.aduana.mtto.bean.SystemFacade;

/**
 *
 * @author Datum-Redsoft
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class FormService {

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
    protected CatProfileFacade catProfileFacade;
    
    @EJB
    protected CatProfiledetailFacade catProfiledetailFacade;
    
    @EJB
    protected EventLogFacade eventLogFacade;
    
    

    public Set<Form> getFormPending(String role, String uid) {
        Set<Form> forms = new HashSet<>();
        String status = "PENDING";
        getFormStatus(role, uid, status, forms);

        return forms;
    }

    public Set<Form> getFormFinished(String role, String uid) {
        Set<Form> forms = new HashSet<>();
        String status = "FINISH";
        getFormStatus(role, uid, status, forms);
        
        return forms;
    }

    public void getFormStatus(String role, String uid, String status, Set<Form> forms) {
        List<String> roles = new ArrayList<>();
        
        if (role.contains(",")) {
            String[] rolesTemp = role.split(",");
            for (int i = 0; i < rolesTemp.length; i++) {
                roles.add(rolesTemp[i]);
            }
        } else {
            roles.add(role);
        }

        List<Formu> formDB = formuFacade.getFormByUserAndStatus(uid, status);

        formDB.forEach((s) -> {
            forms.add(FormTranslator.dbToAPI(s, Boolean.FALSE));
        });

        for (String r : roles) {
            formDB = formuFacade.getFormuByRoleAndStatus(r, status);

            formDB.forEach((s) -> {
                forms.add(FormTranslator.dbToAPI(s, Boolean.FALSE));
            });
        }
    }

    public Formu getFormAll(String id) {
        sv.gob.aduana.entity.Formu formDB = formuFacade.find(id);

        if (formDB == null) {
            return null;
        }

        formDB.setRequestCollection(requestFacade.getRequestsById(id));

        for (int i = 0; i < formDB.getRequestCollection().size(); i++) {
            formDB.getRequestCollection().get(i)
                    .setProfileCollection(
                            profileFacade.getByRequestId(
                                    formDB.getRequestCollection().get(i).getId()));

            
            formDB.getRequestCollection().get(i)
                    .setSystemCollection(
                            systemFacade.getByRequestId(
                                    formDB.getRequestCollection().get(i).getId()));

            
            formDB.getRequestCollection().get(i)
                    .setOtherCollection(
                            otherFacade.getByRequestId(
                                    formDB.getRequestCollection().get(i).getId()));

            
            formDB.getRequestCollection().get(i)
                    .setRequestflowCollection(
                            requestflowFacade.getByRequestId(
                                    formDB.getRequestCollection().get(i).getId()));
            
        }

        return formDB;
    }

    public Formu createForm(Form f) throws Exception {

        utx.begin();

        sv.gob.aduana.entity.Applicant applicant = FormTranslator.apiToDB(f.getApplicant());
        sv.gob.aduana.entity.Formu form = FormTranslator.apiToDB(f, Boolean.TRUE);

        //Guardar el solicitante
        GeneratorId gen = generatorIdFacade.find("APP");

        Integer counter = gen.getCounter();

        gen.setCounter(counter + 1);

        generatorIdFacade.edit(gen);

        applicant.setId("APP-" + counter);

        applicantFacade.create(applicant);

        //Guardar formulario
        gen = generatorIdFacade.find("FRM");

        counter = gen.getCounter();

        gen.setCounter(counter + 1);

        generatorIdFacade.edit(gen);

        form.setId("DGA-" + counter);

        form.setApplicant(applicant);

        formuFacade.create(form);
        
        
        eventLogFacade.insertInAudit(form.getId(), "", "Creando un nuevo formulario",
                 "FORM", form.getCreateby());

        utx.commit();

        return form;
    }

    public Formu editForm(sv.gob.aduana.entity.Applicant applicant, Formu form) throws Exception {
        utx.begin();
        applicantFacade.edit(applicant);

        form.setApplicant(applicant);
        formuFacade.edit(form);
        
        
        eventLogFacade.insertInAudit(form.getId(), "", "Editando formulario",
                 "FORM", form.getModifiedby());

        
        utx.commit();

        return form;
    }

    public Set<Form> getByRoleStep(String role) {
        Set<Form> forms = new HashSet<>();
        
        List<Formu> formsToDB = formuFacade.getFormByRoleStep(role);
            
        
        formsToDB.forEach((s) -> {
            forms.add(FormTranslator.dbToAPI(s, Boolean.FALSE));
        });
        
        return forms;
    }

}
