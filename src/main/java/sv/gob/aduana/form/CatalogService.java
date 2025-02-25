/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.aduana.form;

import io.swagger.model.CatalogAttribute;
import io.swagger.model.CatalogExternalApplicantType;
import io.swagger.model.CatalogFormType;
import io.swagger.model.CatalogGroup;
import io.swagger.model.CatalogProfile;
import io.swagger.model.CatalogRequestType;
import io.swagger.model.CatalogSystem;
import io.swagger.model.CatalogTypeAFPA;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import sv.gob.aduana.mtto.bean.CatAttributeFacade;
import sv.gob.aduana.mtto.bean.CatExtapptypeFacade;
import sv.gob.aduana.mtto.bean.CatGroupFacade;
import sv.gob.aduana.mtto.bean.CatProfileFacade;
import sv.gob.aduana.mtto.bean.CatSystemFacade;
import sv.gob.aduana.mtto.bean.CatTypeafpaFacade;
import sv.gob.aduana.mtto.bean.CatTyperequestFacade;

/**
 *
 * @author Datum-Redsoft
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN) 
public class CatalogService {
    
    
    @EJB
    protected CatTypeafpaFacade typeAfpaFacade;
    
    
    @EJB
    protected CatExtapptypeFacade extapptypeFacade;


    @EJB
    protected CatProfileFacade catalogProfileFacade;
    
    @EJB
    protected CatSystemFacade catalogSystemFacade;
    
    @EJB
    protected CatAttributeFacade catalogAttributeFacade;
    
    @EJB
    protected CatGroupFacade catalogGroupFacade;
    
    @EJB
    protected CatTyperequestFacade catalogTyperequestFacade;
    
    public List<CatalogFormType> getCatalogFormTypes() {
        //TODO = Aca se deben agregar nuevos tipos de formularios
        List<CatalogFormType> catalogFormTypes = new ArrayList<>();
        CatalogFormType c = new CatalogFormType();
        c.setId("Interno");
        c.setValue("Formulario Interno");
        c.setStatus("ENABLED");
        catalogFormTypes.add(c);
        c.setId("Externo");
        c.setValue("Formulario Externo");
        c.setStatus("ENABLED");
        catalogFormTypes.add(c);
        return catalogFormTypes;
    }
    
    
    public List<CatalogTypeAFPA> getCatalogTypeAFPA() {
        List<sv.gob.aduana.entity.CatTypeafpa> typeAfpaListDB = typeAfpaFacade.getFindByEnabled();
        List<CatalogTypeAFPA> catalog = new ArrayList<>();
        typeAfpaListDB.forEach((t) -> {
            catalog.add(FormTranslator.dbToAPI(t));
        });
        return catalog;
    }
    
    
    public List<CatalogExternalApplicantType> getCatalogExtAppType() {
        List<sv.gob.aduana.entity.CatExtapptype> extAppTypesDB = extapptypeFacade.getFindByEnabled();
        List<CatalogExternalApplicantType> catalog = new ArrayList<>();
        extAppTypesDB.forEach((t) -> {
            catalog.add(FormTranslator.dbToAPI(t));
        });
        return catalog;
    }
    
    
    public List<CatalogProfile> getCatalogProfileByRole(String profile) {
        List<sv.gob.aduana.entity.CatProfile> catProfileDB = catalogProfileFacade.getProfileByRole(profile);
        List<CatalogProfile> catalog = new ArrayList<>();
        catProfileDB.forEach((t) -> {
            catalog.add(FormTranslator.dbToAPI(t));
        });
        return catalog;
    }
    
    
    public List<CatalogAttribute> getCatalogAttribute() {
        List<sv.gob.aduana.entity.CatAttribute> attributesDB = catalogAttributeFacade.getFindByEnabled();
        List<CatalogAttribute> catalog = new ArrayList<>();
        attributesDB.forEach((t) -> {
            catalog.add(FormTranslator.dbToAPI(t));
        });
        return catalog;
    }

    
    public List<CatalogSystem> getCatalogSystem(String role) {
        List<sv.gob.aduana.entity.CatSystem> catSystemDB = catalogSystemFacade.getSystemByRole(role);
        List<CatalogSystem> catalog = new ArrayList<>();
        catSystemDB.forEach((t) -> {
            catalog.add(FormTranslator.dbToAPI(t));
        });
        return catalog;
    }
    
     public List<CatalogGroup> getCatalogGroup(String system) {
        List<sv.gob.aduana.entity.CatGroup> catGroupDB = catalogGroupFacade.getGroupBySystem(system);
        List<CatalogGroup> catalog = new ArrayList<>();
        catGroupDB.forEach((t) -> {
            catalog.add(FormTranslator.dbToAPI(t));
        });
        return catalog;
    }
     
     
    public List<CatalogRequestType> getCatalogTypeRequest() {
        List<sv.gob.aduana.entity.CatTyperequest> catTypeRequestDB = catalogTyperequestFacade.getFindByEnabled();
        List<CatalogRequestType> catalog = new ArrayList<>();
        catTypeRequestDB.forEach((t) -> {
            catalog.add(FormTranslator.dbToAPI(t));
        });
        return catalog;
    }
}
