/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.aduana.form;

import io.swagger.model.Applicant;
import io.swagger.model.CatalogAttribute;
import io.swagger.model.CatalogCustoms;
import io.swagger.model.CatalogExternalApplicantType;
import io.swagger.model.CatalogGroup;
import io.swagger.model.CatalogLevelFour;
import io.swagger.model.CatalogLevelOne;
import io.swagger.model.CatalogLevelThree;
import io.swagger.model.CatalogLevelTwo;
import io.swagger.model.CatalogPosition;
import io.swagger.model.CatalogProfile;
import io.swagger.model.CatalogRequestType;
import io.swagger.model.CatalogSystem;
import io.swagger.model.CatalogTypeAFPA;
import io.swagger.model.Flow;
import io.swagger.model.Form;
import io.swagger.model.Other;
import io.swagger.model.Person;
import io.swagger.model.Profile;
import io.swagger.model.Request;
import io.swagger.model.System;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import sv.gob.aduana.util.ClientDGII;
import sv.gob.mh.oim.pojo.AttributeAccount;
import sv.gob.mh.oim.pojo.Identity;
import sv.gob.mh.oim.pojo.UsuarioDGIIResponse;

/**
 *
 * @author Datum-Redsoft
 */
public class FormTranslator {

    public static Form dbToAPI(sv.gob.aduana.entity.Formu t) {
        if (t == null) {
            return null;
        }

        return dbToAPI(t, Boolean.FALSE);
    }

    public static Form dbToAPI(sv.gob.aduana.entity.Formu t, Boolean complete) {
        if (t == null) {
            return null;
        }

        Form f = new Form();

        f.setApplicantViewer(t.getApplicantviewer());
        f.setClosed((t.getClosed() != 0));
        f.setComment(t.getComment());
        f.setCreatedBy(t.getCreateby());
        f.setCreatedName(t.getCreatedname());
        if (t.getCreatedon() != null) {
            f.setCreatedOn(String.valueOf(t.getCreatedon().getTime()));
        }
        f.setFormType(t.getFormType());
        f.setId(t.getId());
        f.setModifiedBy(t.getModifiedby());

        if (t.getModifiedon() != null) {
            f.setModifiedOn(String.valueOf(t.getModifiedon().getTime()));
        }

        f.setApplicant(FormTranslator.dbToAPI(t.getApplicant()));
        f.setStep(t.getStep());
        f.setRoleStep(t.getRoleStep());

        //Files
        f.setFile1(t.getFile1());
        f.setFile2(t.getFile2());
        f.setFile3(t.getFile3());
        f.setFile4(t.getFile4());
        f.setFile5(t.getFile5());
        f.setFile6(t.getFile6());

        if (complete) {
            f.setRequests(new ArrayList<>());
            for (sv.gob.aduana.entity.Request rt : t.getRequestCollection()) {
                f.getRequests().add(FormTranslator.dbToAPI(rt));
            }
        }

        f.setStatus(t.getStatus());
        f.setStep(t.getStep());

        return f;
    }

    public static CatalogTypeAFPA dbToAPI(sv.gob.aduana.entity.CatTypeafpa t) {
        if (t == null) {
            return null;
        }

        CatalogTypeAFPA c = new CatalogTypeAFPA();

        c.setId(t.getId());
        c.setValue(t.getName());
        c.setStatus(t.getStatus() == 0 ? "DISABLED" : "ENABLED");

        return c;
    }

    public static CatalogExternalApplicantType dbToAPI(sv.gob.aduana.entity.CatExtapptype t) {
        if (t == null) {
            return null;
        }

        CatalogExternalApplicantType c = new CatalogExternalApplicantType();

        c.setId(t.getId());
        c.setValue(t.getName());
        c.setStatus(t.getStatus() == 0 ? "DISABLED" : "ENABLED");

        return c;

    }

    public static CatalogProfile dbToAPI(sv.gob.aduana.entity.CatProfile t) {
        if (t == null) {
            return null;
        }

        CatalogProfile c = new CatalogProfile();

        c.setId(t.getId());
        c.setValue(t.getName());
        c.setStatus(t.getStatus() == 0 ? "DISABLED" : "ENABLED");

        return c;
    }

    public static CatalogSystem dbToAPI(sv.gob.aduana.entity.CatSystem t) {
        if (t == null) {
            return null;
        }

        CatalogSystem c = new CatalogSystem();

        c.setId(t.getId());
        c.setValue(t.getName());
        c.setStatus(t.getStatus() == 0 ? "DISABLED" : "ENABLED");

        return c;
    }

    public static CatalogAttribute dbToAPI(sv.gob.aduana.entity.CatAttribute t) {
        if (t == null) {
            return null;
        }

        CatalogAttribute c = new CatalogAttribute();

        c.setId(t.getId());
        c.setValue(t.getName());
        c.setStatus(t.getStatus() == 0 ? "DISABLED" : "ENABLED");

        return c;

    }

    public static CatalogGroup dbToAPI(sv.gob.aduana.entity.CatGroup t) {
        if (t == null) {
            return null;
        }

        CatalogGroup c = new CatalogGroup();

        c.setId(t.getId());
        c.setValue(t.getFriendlyname());
        c.setStatus(t.getStatus() == 0 ? "DISABLED" : "ENABLED");

        return c;
    }

    public static CatalogRequestType dbToAPI(sv.gob.aduana.entity.CatTyperequest t) {
        if (t == null) {
            return null;
        }

        CatalogRequestType c = new CatalogRequestType();

        c.setId(t.getId());
        c.setValue(t.getName());
        c.setStatus(t.getStatus() == 0 ? "DISABLED" : "ENABLED");

        return c;

    }

    public static Applicant dbToAPI(sv.gob.aduana.entity.Applicant t) {
        if (t == null) {
            return null;
        }

        Applicant applicant = new Applicant();

        applicant.setAttribute(FormTranslator.dbToAPI(t.getAttribute()));
        applicant.setDocument(t.getDocument());
        applicant.setExternalCodeDeclarant(t.getExternalcodedecla());
        applicant.setExternalName(t.getExternalname());
        applicant.setExternalRepLegal(t.getExternalreplegal());
        applicant.setExternalType(FormTranslator.dbToAPI(t.getExtapptype()));
        applicant.setId(t.getId());
        applicant.setMail(t.getMail());
        applicant.setName(t.getName());
        applicant.setPosition(FormTranslator.dbToAPI(t.getPosition()));

        return applicant;
    }

    public static Request dbToAPI(sv.gob.aduana.entity.Request t) {
        if (t == null) {
            return null;
        }

        Request r = new Request();

        if (t.getApprovedate() != null) {
            r.setApproveDate(String.valueOf(t.getApprovedate().getTime()));
        }

        r.setCreateBy(t.getCreateby());
        r.setCreateOn(String.valueOf(t.getCreateon().getTime()));

        r.setFlow(new ArrayList<>());
        r.setPassword(t.getPassword());

        t.getRequestflowCollection().forEach((rf) -> {
            r.getFlow().add(FormTranslator.dbToAPI(rf));
        });

        r.setHashCode(t.getHashcode());
        r.setId(t.getId());
        r.setOthers(new ArrayList<>());

        t.getOtherCollection().forEach((o) -> {
            r.getOthers().add(FormTranslator.dbToAPI(o));
        });

        r.setPerson(FormTranslator.dbToAPI(t.getPerson()));
        r.setProfiles(new ArrayList<>());

        t.getProfileCollection().forEach((p) -> {
            r.getProfiles().add(FormTranslator.dbToAPI(p));
        });

        r.setState(t.getState());
        r.setSystems(new ArrayList<>());

        t.getSystemCollection().forEach((p) -> {
            r.getSystems().add(FormTranslator.dbToAPI(p));
        });

        t.getRequestflowCollection().forEach((p) -> {

        });

        r.setTypeRequest(FormTranslator.dbToAPI(t.getTyperequest()));

        r.setDeleteAllGroups(t.getDeleteAllGroup() == 1);
        r.setMoveToDesactive(t.getMoveToDesactive() == 1);
        r.setHelpDeskId(t.getHelpDeskId());

        return r;
    }

    public static sv.gob.aduana.entity.Requestflow apiToDB(Flow t) {
        if (t == null) {
            return null;
        }

        sv.gob.aduana.entity.Requestflow requestFlow = new sv.gob.aduana.entity.Requestflow();

        requestFlow.setComment(t.getComment());

        if (t.getEndDate() != null) {
            requestFlow.setEnddate(new Date(Long.valueOf(t.getEndDate())));
        }

        requestFlow.setId(t.getId());
        requestFlow.setResult(t.getResult());

        if (t.getStartDate() != null) {
            requestFlow.setStartdate(new Date(Long.valueOf(t.getStartDate())));
        }

        requestFlow.setStatus(t.getStatus());
        requestFlow.setUser(t.getUser());

        return requestFlow;
    }

    public static sv.gob.aduana.entity.Applicant apiToDB(Applicant t) {
        if (t == null) {
            return null;
        }

        sv.gob.aduana.entity.Applicant applicant = new sv.gob.aduana.entity.Applicant();

        applicant.setAttribute(FormTranslator.apiToDB(t.getAttribute()));
        applicant.setDocument(t.getDocument());
        applicant.setExtapptype(FormTranslator.apiToDB(t.getExternalType()));
        applicant.setExternalcodedecla(t.getExternalCodeDeclarant());
        applicant.setExternalname(t.getExternalName());
        applicant.setId(t.getId());
        applicant.setMail(t.getMail());
        applicant.setName(t.getName());
        applicant.setPosition(FormTranslator.apiToDB(t.getPosition()));

        return applicant;
    }

    public static sv.gob.aduana.entity.Formu apiToDB(Form t) {
        return apiToDB(t, Boolean.FALSE);
    }

    public static sv.gob.aduana.entity.Formu apiToDB(Form t, Boolean withouthChild) {
        if (t == null) {
            return null;
        }

        sv.gob.aduana.entity.Formu form = new sv.gob.aduana.entity.Formu();

        form.setApplicant(FormTranslator.apiToDB(t.getApplicant()));
        form.setApplicantviewer(t.getApplicantViewer());
        form.setClosed(t.isClosed() ? 1 : 0);
        form.setComment(t.getComment());
        form.setCreateby(t.getCreatedBy());
        form.setCreatedname(t.getCreatedName());
        if (t.getCreatedOn() != null && !t.getCreatedOn().isEmpty()) {
            form.setCreatedon(new Date(Long.valueOf(t.getCreatedOn())));
        }
        //Files
        if (t.getFile1() != null) {
            form.setFile1(t.getFile1());
        }

        if (t.getFile2() != null) {
            form.setFile2(t.getFile2());
        }

        if (t.getFile3() != null) {
            form.setFile3(t.getFile3());
        }

        if (t.getFile4() != null) {
            form.setFile4(t.getFile4());
        }

        if (t.getFile5() != null) {
            form.setFile5(t.getFile5());
        }

        if (t.getFile6() != null) {
            form.setFile6(t.getFile6());
        }

        //form.setFile1(DgaTransform.getFile(t.getFile1()));
        form.setFormType(t.getFormType());
        form.setId(t.getId());
        form.setModifiedby(t.getModifiedBy());
        if (t.getModifiedOn() != null && !t.getModifiedOn().isEmpty()) {
            form.setModifiedon(new Date(Long.valueOf(t.getModifiedOn())));
        }

        form.setRequestCollection(new ArrayList<>());

        if (!withouthChild) {
            t.getRequests().forEach((r) -> {
                form.getRequestCollection().add(FormTranslator.apiToDB(r));
            });
        }

        form.setStatus(t.getStatus());
        form.setStep(t.getStep());
        form.setStepisrole(1);
        form.setRoleStep(t.getRoleStep());

        return form;
    }

    public static sv.gob.aduana.entity.Request apiToDB(Request t) {
        if (t == null) {
            return null;
        }

        sv.gob.aduana.entity.Request request = new sv.gob.aduana.entity.Request();

        if (t.getApproveDate() != null && !t.getApproveDate().isEmpty()) {
            request.setApprovedate(new Date(Long.valueOf(t.getApproveDate())));
        }
        request.setCreateby(t.getCreateBy());
        request.setPassword(t.getPassword());

        if (t.getCreateOn() != null && !t.getCreateOn().isEmpty()) {
            request.setCreateon(new Date(Long.valueOf(t.getCreateOn())));
        }

        request.setHashcode(t.getHashCode());
        request.setId(t.getId());

        request.setOtherCollection(new ArrayList<>());

        t.getOthers().forEach((o) -> {
            request.getOtherCollection().add(FormTranslator.apiToDB(o));
        });

        request.setPerson(FormTranslator.apiToDB(t.getPerson()));

        request.setProfileCollection(new ArrayList<>());

        t.getProfiles().forEach((p) -> {
            request.getProfileCollection().add(FormTranslator.apiToDB(p));
        });

        request.setState(t.getState());

        request.setSystemCollection(new ArrayList<>());

        t.getSystems().forEach((s) -> {
            request.getSystemCollection().add(FormTranslator.apiToDB(s));
        });

        request.setTyperequest(FormTranslator.apiToDB(t.getTypeRequest()));

        if (t.isDeleteAllGroups() != null) {
            request.setDeleteAllGroup(t.isDeleteAllGroups() ? 1 : 0);
        } else {
            request.setDeleteAllGroup(0);
        }

        if (t.isMoveToDesactive() != null) {
            request.setMoveToDesactive(t.isMoveToDesactive() ? 1 : 0);
        } else {
            request.setMoveToDesactive(0);
        }

        request.setHelpDeskId(t.getHelpDeskId());

        //NOTA: Los flujos tiene su propio almaenamiento y extraccion aparte
        return request;
    }

    public static CatalogPosition dbToAPI(sv.gob.aduana.entity.CatPosition t) {
        if (t == null) {
            return null;
        }

        CatalogPosition c = new CatalogPosition();

        c.setId(t.getId());
        c.setValue(t.getName());
        c.setStatus(t.getStatus() == 0 ? "DISABLED" : "ENABLED");

        return c;
    }

    public static Flow dbToAPI(sv.gob.aduana.entity.Requestflow t) {
        if (t == null) {
            return null;
        }

        Flow c = new Flow();

        c.setComment(t.getComment());
        if (t.getEnddate() != null) {
            c.setEndDate(String.valueOf(t.getEnddate().getTime()));
        }
        c.setId(t.getId());
        c.setResult(t.getResult());
        if (t.getStartdate() != null) {
            c.setStartDate(String.valueOf(t.getStartdate().getTime()));
        }
        c.setStatus(String.valueOf(t.getStatus()));
        c.setUser(t.getUser());
        c.setFlowId(t.getFlow().getId());

        return c;
    }

    public static Other dbToAPI(sv.gob.aduana.entity.Other t) {
        if (t == null) {
            return null;
        }

        Other c = new Other();

        c.setData(t.getData());

        if (t.getEnddate() != null) {
            c.setEndDate(String.valueOf(t.getEnddate().getTime()));
        }
        
        
        if (t.getStartDate()!= null) {
            c.setStartDate(String.valueOf(t.getStartDate().getTime()));
        }

        c.setHelpDesk(t.getHelpdesk());
        c.setId(t.getId());
        c.setPlatform(t.getPlatform());
        c.setSendTo(t.getSendto());
        c.setStatus(t.getStatus());

        if (t.getTemporal() != null) {
            c.setTemporal((t.getTemporal() == 1));
        } else {
            c.setTemporal(false);
        }

        c.setType(t.getType());

        return c;
    }

    public static Person dbToAPI(sv.gob.aduana.entity.Person t) {
        if (t == null) {
            return null;
        }

        Person c = new Person();

        c.setApproveAFPA(t.getApproveafpa());
        c.setAttribute(FormTranslator.dbToAPI(t.getAttribute()));
        c.setCodeDeclarant(t.getCodedeclarant());
        c.setDocument(t.getDocument());
        c.setState(t.getState());

        if (t.getEnddate() != null) {
            c.setEndDate(String.valueOf(t.getEnddate().getTime()));
        }

        c.setFullName(String.valueOf(t.getFullname()));
        c.setId(t.getId());
        if (t.getIsafpa() != null) {
            c.setIsAFPA(t.getIsafpa() == 1);
        } else {
            c.setIsAFPA(Boolean.FALSE);
        }

        c.setLastName(t.getLastname());
        c.setLevelFour(FormTranslator.dbToAPI(t.getLevelfour()));
        c.setLevelThree(FormTranslator.dbToAPI(t.getLevelthree()));
        c.setLevelTwo(FormTranslator.dbToAPI(t.getLeveltwo()));
        c.setLevelOne(FormTranslator.dbToAPI(t.getLevelone()));
        c.setMail(t.getMail());
        c.setPosition(FormTranslator.dbToAPI(t.getPosition()));
        c.setResolution(t.getResolution());

        if (t.getStartdate() != null) {
            c.setStartDate(String.valueOf(t.getStartdate().getTime()));
        }

        c.setSurName(t.getSurname());
        c.setTypeAFPA(FormTranslator.dbToAPI(t.getCatTypeafpaid()));
        c.setUid(t.getLogin());

        c.setUserType(t.getUserType());
        c.setPhoneNumber(t.getPhoneNumber());
        c.setAlternativeMail(t.getAlternativeMail());
        c.setMobile(t.getMobile());
        c.setOrganizationCode(t.getOrganizationName());

        c.setUserCodDuca(t.getUserCodDuca());
        c.setUserCodVPN(t.getUserCodVPN());
        c.setUserSiduneaPlus(t.getUserSiduneaPlus());
        c.setUserSiduneaWorld(t.getUserSiduneaWorld());

        return c;
    }

    public static Profile dbToAPI(sv.gob.aduana.entity.Profile t) {
        if (t == null) {
            return null;
        }

        Profile c = new Profile();

        c.setCustom(FormTranslator.dbToAPI(t.getCustom()));
        
        if (t.getEnddate() != null) {
            c.setEndDate(String.valueOf(t.getEnddate().getTime()));
        }
        
        if (t.getStartDate()!= null) {
            c.setStartDate(String.valueOf(t.getStartDate().getTime()));
        }

        
        c.setId(t.getId());
        c.setProfile(FormTranslator.dbToAPI(t.getProfile()));
        c.setStatus(t.getStatus());
        c.setTemporal(t.getTemporal() == 1);
        c.setType(t.getType());

        return c;
    }

    public static System dbToAPI(sv.gob.aduana.entity.System t) {
        if (t == null) {
            return null;
        }

        System s = new System();

        s.setCustom(FormTranslator.dbToAPI(t.getCustom()));

        if (t.getEnddate() != null) {
            s.setEndDate(String.valueOf(t.getEnddate().getTime()));
        }
        
        
        if (t.getStartDate()!= null) {
            s.setStartDate(String.valueOf(t.getStartDate().getTime()));
        }


        s.setGroup(FormTranslator.dbToAPI(t.getGroup1()));
        s.setId(t.getId());
        s.setStatus(t.getStatus());

        if (t.getTemporal() != null) {
            s.setTemporal(t.getTemporal() == 1);
        }

        s.setType(t.getType());

        return s;
    }

    public static sv.gob.aduana.entity.CatAttribute apiToDB(CatalogAttribute t) {
        if (t == null) {
            return null;
        }

        sv.gob.aduana.entity.CatAttribute c = new sv.gob.aduana.entity.CatAttribute();

        if (t.getId() == null) {
            c.setId("NA");
            c.setName("N/A");
            c.setStatus(1);
        } else {
            c.setId(t.getId());
            c.setName(t.getValue());
            if (t.getStatus() != null) {
                c.setStatus(t.getStatus().equals("ENABLED") ? 1 : 0);
            } else {
                c.setStatus(1);
            }
        }

        return c;
    }

    public static sv.gob.aduana.entity.CatExtapptype apiToDB(CatalogExternalApplicantType t) {
        if (t == null) {
            return null;
        }

        sv.gob.aduana.entity.CatExtapptype c = new sv.gob.aduana.entity.CatExtapptype();

        if (t.getId() == null) {
            c.setId("NA");
            c.setName("N/A");
            c.setStatus(1);
        } else {
            c.setId(t.getId());
            c.setName(t.getValue());
            if (t.getStatus() != null) {
                c.setStatus(t.getStatus().equals("ENABLED") ? 1 : 0);
            } else {
                c.setStatus(1);
            }
        }

        return c;

    }

    public static sv.gob.aduana.entity.CatPosition apiToDB(CatalogPosition t) {
        if (t == null) {
            return null;
        }

        sv.gob.aduana.entity.CatPosition c = new sv.gob.aduana.entity.CatPosition();

        if (t.getId() == null) {
            c.setId("NA");
            c.setName("N/A");
            c.setStatus(1);
        } else {
            c.setId(t.getId());
            c.setName(t.getValue());
            if (t.getStatus() != null) {
                c.setStatus(t.getStatus().equals("ENABLED") ? 1 : 0);
            } else {
                c.setStatus(1);
            }
        }

        return c;
    }

    public static sv.gob.aduana.entity.Other apiToDB(Other t) {

        if (t == null) {
            return null;
        }

        sv.gob.aduana.entity.Other o = new sv.gob.aduana.entity.Other();

        o.setData(t.getData());

        if (t.getEndDate() != null && !t.getEndDate().isEmpty()) {
            o.setEnddate(new Date(Long.valueOf(t.getEndDate())));
        }
        
        
        if (t.getStartDate()!= null && !t.getStartDate().isEmpty()) {
            o.setStartDate(new Date(Long.valueOf(t.getStartDate())));
        }
        
        o.setHelpdesk(t.getHelpDesk());
        o.setId(t.getId());
        o.setPlatform(t.getPlatform());
        o.setSendto(t.getSendTo());
        o.setStatus(t.getStatus());
        if (t.isTemporal() != null) {
            o.setTemporal(t.isTemporal() ? 1 : 0);
        } else {
            o.setTemporal(0);
        }

        o.setType(t.getType());

        return o;
    }

    public static sv.gob.aduana.entity.Person apiToDB(Person t) {

        if (t == null) {
            return null;
        }

        sv.gob.aduana.entity.Person p = new sv.gob.aduana.entity.Person();

        p.setApproveafpa(t.getApproveAFPA());
        p.setAttribute(FormTranslator.apiToDB(t.getAttribute()));
        p.setCatTypeafpaid(FormTranslator.apiToDB(t.getTypeAFPA()));
        p.setCodedeclarant(t.getCodeDeclarant());
        p.setDocument(t.getDocument());
        p.setState(t.getState());
        if (t.getEndDate() != null && !t.getEndDate().isEmpty()) {
            p.setEnddate(new Date(Long.valueOf(t.getEndDate())));
        }
        p.setFullname(t.getFullName());
        p.setId(t.getId());

        if (t.isIsAFPA() != null) {
            p.setIsafpa(t.isIsAFPA() ? 1 : 0);
        }

        p.setLastname(t.getLastName());
        p.setLevelfour(FormTranslator.apiToDB(t.getLevelFour()));
        p.setLevelthree(FormTranslator.apiToDB(t.getLevelThree()));
        p.setLeveltwo(FormTranslator.apiToDB(t.getLevelTwo()));
        p.setLevelone(FormTranslator.apiToDB(t.getLevelOne()));
        p.setLogin(t.getUid());
        p.setMail(t.getMail());
        p.setPosition(FormTranslator.apiToDB(t.getPosition()));
        p.setResolution(t.getResolution());

        if (t.getStartDate() != null && !t.getStartDate().isEmpty()) {
            p.setStartdate(new Date(Long.valueOf(t.getStartDate())));
        }

        p.setSurname(t.getSurName());
        p.setUserType(t.getUserType());
        p.setPhoneNumber(t.getPhoneNumber());
        p.setAlternativeMail(t.getAlternativeMail());
        p.setMobile(t.getMobile());
        p.setOrganizationName(t.getOrganizationCode());

        p.setUserCodDuca(t.getUserCodDuca());
        p.setUserCodVPN(t.getUserCodVPN());
        p.setUserSiduneaPlus(t.getUserSiduneaPlus());
        p.setUserSiduneaWorld(t.getUserSiduneaWorld());

        return p;
    }

    public static sv.gob.aduana.entity.Profile apiToDB(Profile t) {

        if (t == null) {
            return null;
        }

        sv.gob.aduana.entity.Profile p = new sv.gob.aduana.entity.Profile();

        p.setCustom(FormTranslator.apiToDB(t.getCustom()));

        if (t.getEndDate() != null && !t.getEndDate().isEmpty()) {
            p.setEnddate(new Date(Long.valueOf(t.getEndDate())));
        }
        
        
        if (t.getStartDate()!= null && !t.getStartDate().isEmpty()) {
            p.setStartDate(new Date(Long.valueOf(t.getStartDate())));
        }

        p.setId(t.getId());
        p.setProfile(FormTranslator.apiToDB(t.getProfile()));
        p.setStatus(t.getStatus());
        if (t.isTemporal() != null) {
            p.setTemporal(t.isTemporal() ? 1 : 0);
        } else {
            p.setTemporal(0);
        }

        p.setType(t.getType());

        return p;
    }

    public static sv.gob.aduana.entity.System apiToDB(io.swagger.model.System t) {

        if (t == null) {
            return null;
        }

        sv.gob.aduana.entity.System s = new sv.gob.aduana.entity.System();

        s.setCustom(FormTranslator.apiToDB(t.getCustom()));

        if (t.getEndDate() != null && !t.getEndDate().isEmpty()) {
            s.setEnddate(new Date(Long.valueOf(t.getEndDate())));
        }
        
        
        if (t.getStartDate()!= null && !t.getStartDate().isEmpty()) {
            s.setStartDate(new Date(Long.valueOf(t.getStartDate())));
        }

        s.setGroup1(FormTranslator.apiToDB(t.getGroup()));
        s.setId(t.getId());
        s.setStatus(t.getStatus());
        if (t.isTemporal() != null) {
            s.setTemporal(t.isTemporal() ? 1 : 0);
        } else {
            s.setTemporal(0);
        }
        s.setType(t.getType());

        return s;
    }

    public static sv.gob.aduana.entity.CatTyperequest apiToDB(CatalogRequestType t) {
        if (t == null) {
            return null;
        }

        sv.gob.aduana.entity.CatTyperequest c = new sv.gob.aduana.entity.CatTyperequest();

        if (t.getId() == null) {
            c.setId("NA");
            c.setName("N/A");
            c.setStatus(1);
        } else {
            c.setId(t.getId());
            c.setName(t.getValue());
            if (t.getStatus() != null) {
                c.setStatus(t.getStatus().equals("ENABLED") ? 1 : 0);
            } else {
                c.setStatus(1);
            }
        }

        return c;
    }

    public static CatalogLevelFour dbToAPI(sv.gob.aduana.entity.CatLvlfour t) {
        if (t == null) {
            return null;
        }

        CatalogLevelFour c = new CatalogLevelFour();

        c.setId(t.getId());
        c.setValue(t.getName());
        c.setStatus(t.getStatus() == 0 ? "DISABLED" : "ENABLED");

        return c;
    }

    public static CatalogLevelThree dbToAPI(sv.gob.aduana.entity.CatLvlthree t) {
        if (t == null) {
            return null;
        }

        CatalogLevelThree c = new CatalogLevelThree();

        c.setId(t.getId());
        c.setValue(t.getName());
        c.setStatus(t.getStatus() == 0 ? "DISABLED" : "ENABLED");

        return c;
    }

    public static CatalogLevelTwo dbToAPI(sv.gob.aduana.entity.CatLvltwo t) {
        if (t == null) {
            return null;
        }

        CatalogLevelTwo c = new CatalogLevelTwo();

        c.setId(t.getId());
        c.setValue(t.getName());
        c.setStatus(t.getStatus() == 0 ? "DISABLED" : "ENABLED");

        return c;
    }

    public static CatalogLevelOne dbToAPI(sv.gob.aduana.entity.CatLvlone t) {
        if (t == null) {
            return null;
        }

        CatalogLevelOne c = new CatalogLevelOne();

        c.setId(t.getId());
        c.setValue(t.getName());
        c.setStatus(t.getStatus() == 0 ? "DISABLED" : "ENABLED");

        return c;
    }

    public static sv.gob.aduana.entity.CatTypeafpa apiToDB(CatalogTypeAFPA t) {
        if (t == null) {
            return null;
        }

        sv.gob.aduana.entity.CatTypeafpa c = new sv.gob.aduana.entity.CatTypeafpa();

        if (t.getId() == null) {
            c.setId("NA");
            c.setName("N/A");
            c.setStatus(1);
        } else {
            c.setId(t.getId());
            c.setName(t.getValue());
            if (t.getStatus() != null) {
                c.setStatus(t.getStatus().equals("ENABLED") ? 1 : 0);
            } else {
                c.setStatus(1);
            }
        }

        return c;
    }

    public static sv.gob.aduana.entity.CatLvlfour apiToDB(CatalogLevelFour t) {
        if (t == null) {
            return null;
        }

        sv.gob.aduana.entity.CatLvlfour c = new sv.gob.aduana.entity.CatLvlfour();

        if (t.getId() == null) {
            c.setId("NA");
            c.setName("N/A");
            c.setStatus(1);
        } else {
            c.setId(t.getId());
            c.setName(t.getValue());
            if (t.getStatus() != null) {
                c.setStatus(t.getStatus().equals("ENABLED") ? 1 : 0);
            } else {
                c.setStatus(1);
            }
        }

        return c;
    }

    public static sv.gob.aduana.entity.CatLvlthree apiToDB(CatalogLevelThree t) {
        if (t == null) {
            return null;
        }

        sv.gob.aduana.entity.CatLvlthree c = new sv.gob.aduana.entity.CatLvlthree();

        if (t.getId() == null) {
            c.setId("NA");
            c.setName("N/A");
            c.setStatus(1);
        } else {
            c.setId(t.getId());
            c.setName(t.getValue());
            if (t.getStatus() != null) {
                c.setStatus(t.getStatus().equals("ENABLED") ? 1 : 0);
            } else {
                c.setStatus(1);
            }
        }

        return c;
    }

    public static sv.gob.aduana.entity.CatLvltwo apiToDB(CatalogLevelTwo t) {
        if (t == null) {
            return null;
        }

        sv.gob.aduana.entity.CatLvltwo c = new sv.gob.aduana.entity.CatLvltwo();

        if (t.getId() == null) {
            c.setId("NA");
            c.setName("N/A");
            c.setStatus(1);
        } else {
            c.setId(t.getId());
            c.setName(t.getValue());
            if (t.getStatus() != null) {
                c.setStatus(t.getStatus().equals("ENABLED") ? 1 : 0);
            } else {
                c.setStatus(1);
            }
        }

        return c;
    }

    public static sv.gob.aduana.entity.CatLvlone apiToDB(CatalogLevelOne t) {
        if (t == null) {
            return null;
        }

        sv.gob.aduana.entity.CatLvlone c = new sv.gob.aduana.entity.CatLvlone();

        if (t.getId() == null) {
            c.setId("NA");
            c.setName("N/A");
            c.setStatus(1);
        } else {
            c.setId(t.getId());
            c.setName(t.getValue());
            if (t.getStatus() != null) {
                c.setStatus(t.getStatus().equals("ENABLED") ? 1 : 0);
            } else {
                c.setStatus(1);
            }
        }

        return c;
    }

    public static sv.gob.aduana.entity.CatProfile apiToDB(CatalogProfile t) {
        if (t == null) {
            return null;
        }

        sv.gob.aduana.entity.CatProfile c = new sv.gob.aduana.entity.CatProfile();

        c.setId(t.getId());
        c.setName(t.getValue());
        if (t.getStatus() != null) {
            c.setStatus(t.getStatus().equals("ENABLED") ? 1 : 0);
        } else {
            c.setStatus(1);
        }

        return c;
    }

    public static String apiToDB(CatalogCustoms t) {
        if (t == null) {
            return null;
        }
        
        if(t.getValue() == null)
            return null;

        return t.getId() + " | " + t.getValue();
    }

    public static CatalogCustoms dbToAPI(String t) {
        CatalogCustoms c = new CatalogCustoms();
       

        if (t == null || !t.contains("|")) {
            c.setId("N/A");
            c.setValue("N/A");
            c.setStatus("");

            return c;
        }

        String[] custom = t.split("\\|");

        c.setId(custom[0]);
        c.setValue(custom[1]);
        c.setStatus("ENABLED");

        return c;
    }

    public static sv.gob.aduana.entity.CatGroup apiToDB(CatalogGroup t) {
        if (t == null) {
            return null;
        }

        sv.gob.aduana.entity.CatGroup c = new sv.gob.aduana.entity.CatGroup();

        c.setId(t.getId());
        c.setFriendlyname(t.getValue());
        if (t.getStatus() != null) {
            c.setStatus(t.getStatus().equals("ENABLED") ? 1 : 0);
        } else {
            c.setStatus(1);
        }

        return c;
    }

    public static Identity personToIdentity(sv.gob.aduana.entity.Request r) {
        Identity identity = new Identity();
        
        String nit = "00000000000000";

        if (r.getForm().getModifiedon() != null) {
            identity.setActualizadoEn(String.valueOf(r.getForm().getModifiedon().getTime()));
        }

        identity.setCargo(r.getPerson().getPosition().getName());
        identity.setChangePassword(Boolean.FALSE);
        identity.setCommonName(r.getPerson().getFullname());
        identity.setCorreoAlternativo(r.getPerson().getAlternativeMail());
        identity.setCreadoEn(String.valueOf(r.getCreateon().getTime()));
        identity.setCreadoPor(r.getCreateby());
        identity.setDependencia(r.getPerson().getOrganizationName());
        identity.setEmail(r.getPerson().getMail());
        if (RequestTypeService.isDisabled(r.getTyperequest().getId())) {
            identity.setEstado("Disabled");
        } else {
            identity.setEstado("Enabled");
        }
        identity.setExtension("");
        identity.setFirstName(r.getPerson().getSurname());
        identity.setHomePhone(r.getPerson().getPhoneNumber());
        identity.setId("-");
        identity.setInstitucion(r.getPerson().getOrganizationName());
        identity.setLastName(r.getPerson().getLastname());
        identity.setMobile(r.getPerson().getMobile());
        identity.setDui(r.getPerson().getDocument());
        if(r.getPerson().getDocument().length() == 8 || r.getPerson().getDocument().length() == 9){
            ClientDGII cdgii = new ClientDGII();
            UsuarioDGIIResponse res = cdgii.uTIL0010(r.getPerson().getDocument());
            nit = res.getData().getsNit();
            identity.setNit(nit);
        } else {
            identity.setNit(r.getPerson().getDocument());
        }
        identity.setNivel1(r.getPerson().getLevelone().getName());
        identity.setNivel2(r.getPerson().getLeveltwo().getName());
        identity.setNivel3(r.getPerson().getLevelthree().getName());
        identity.setNivel4(r.getPerson().getLevelfour().getName());
        identity.setOrganizationName(r.getPerson().getOrganizationName());
        identity.setPassword(r.getPassword());
        identity.setPerfilUsuario(r.getPerson().getAttribute().getName());
        
        if(r.getPerson().getStartdate() != null)
            identity.setStartDate(String.valueOf(r.getPerson().getStartdate().getTime()));
        
        if(r.getPerson().getEnddate()!= null)
            identity.setEndDate(String.valueOf(r.getPerson().getEnddate().getTime()));
        
        identity.setTipoUsuario(r.getPerson().getUserType());
        identity.setUserLogin(r.getPerson().getLogin());
        

        return identity;
    }

    public static List<AttributeAccount> requestToAttributes(sv.gob.aduana.entity.Request r,sv.gob.aduana.entity.Formu f) {
        List<AttributeAccount> attributes = new ArrayList<>();

        attributes.add(new AttributeAccount("FORM_ID",f.getId()));
        attributes.add(new AttributeAccount("FORM_CREATED_NAME",f.getCreatedname()));
        attributes.add(new AttributeAccount("FORM_TYPE",f.getFormType()));
        attributes.add(new AttributeAccount("APPLICANT_DOCUMENT",f.getApplicant().getDocument()));
        attributes.add(new AttributeAccount("APPLICANT_NAME",f.getApplicant().getName()));
        attributes.add(new AttributeAccount("APPLICANT_MAIL",f.getApplicant().getMail()));
        attributes.add(new AttributeAccount("APPLICANT_EXTERNAL_NAME",f.getApplicant().getExternalname()));
        attributes.add(new AttributeAccount("APPLICANT_EXTERNAL_LEGAL",f.getApplicant().getExternalreplegal()));
        
        
        //Request
        attributes.add(new AttributeAccount("CREATED_BY", r.getCreateby()));
        attributes.add(new AttributeAccount("FLOW_ID", r.getCurrentFlowId()));
        attributes.add(new AttributeAccount("HASH_ID", r.getHashcode()));
        attributes.add(new AttributeAccount("HELPDESK_ID", r.getHelpDeskId()));
        attributes.add(new AttributeAccount("ID", r.getId()));
        attributes.add(new AttributeAccount("PASSWORD", r.getPassword()));
        attributes.add(new AttributeAccount("STATE", r.getState()));
        attributes.add(new AttributeAccount("PERSON_ALT_MAIL", r.getPerson().getAlternativeMail()));
        attributes.add(new AttributeAccount("PERSON_AFPA_APPROVE_DATE", r.getPerson().getApproveafpa()));
        attributes.add(new AttributeAccount("PERSON_AFPA_COD_DECLA", r.getPerson().getCodedeclarant()));
        attributes.add(new AttributeAccount("PERSON_DOCUMENT", r.getPerson().getDocument()));
        attributes.add(new AttributeAccount("PERSON_FULL_NAME", r.getPerson().getFullname()));
        attributes.add(new AttributeAccount("PERSON_ID", r.getPerson().getId()));
        attributes.add(new AttributeAccount("PERSON_LAST_NAME", r.getPerson().getLastname()));
        attributes.add(new AttributeAccount("PERSON_UID", r.getPerson().getLogin()));
        attributes.add(new AttributeAccount("PERSON_MAIL", r.getPerson().getMail()));
        attributes.add(new AttributeAccount("PERSON_MOBILE", r.getPerson().getMobile()));
        attributes.add(new AttributeAccount("PERSON_ORGANIZATION", r.getPerson().getOrganizationName()));
        attributes.add(new AttributeAccount("PERSON_PHONE_NUMBER", r.getPerson().getPhoneNumber()));
        attributes.add(new AttributeAccount("PERSON_AFPA_RESOLUTION", r.getPerson().getResolution()));
        attributes.add(new AttributeAccount("PERSON_FIRST_NAME", r.getPerson().getSurname()));
        attributes.add(new AttributeAccount("PERSON_USER_DUCA", r.getPerson().getUserCodDuca()));
        attributes.add(new AttributeAccount("PERSON_USER_VPN", r.getPerson().getUserCodVPN()));
        attributes.add(new AttributeAccount("PERSON_USER_SIDUNEA_WORLD", r.getPerson().getUserSiduneaWorld()));
        attributes.add(new AttributeAccount("PERSON_USER_SIDUNEA_PLUS", r.getPerson().getUserSiduneaPlus()));
        attributes.add(new AttributeAccount("PERSON_USER_TYPE", r.getPerson().getUserType()));

        attributes.add(new AttributeAccount("PERSON_ROLE", r.getPerson().getAttribute().getName()));
        
        if(r.getPerson().getCatTypeafpaid() != null)
            attributes.add(new AttributeAccount("PERSON_AFPA_TYPE", r.getPerson().getCatTypeafpaid().getName()));

        if (r.getPerson().getEnddate() != null) {
            attributes.add(new AttributeAccount("PERSON_END_DATE", String.valueOf(r.getPerson().getEnddate().getTime())));
        }

        attributes.add(new AttributeAccount("PERSON_AFPA_IS", String.valueOf(r.getPerson().getIsafpa())));
        attributes.add(new AttributeAccount("PERSON_LEVEL_FOUR", r.getPerson().getLevelfour().getName()));
        attributes.add(new AttributeAccount("PERSON_LEVEL_THREE", r.getPerson().getLevelthree().getName()));
        attributes.add(new AttributeAccount("PERSON_LEVEL_TWO", r.getPerson().getLeveltwo().getName()));
        attributes.add(new AttributeAccount("PERSON_LEVEL_ONE", r.getPerson().getLevelone().getName()));

        attributes.add(new AttributeAccount("PERSON_POSITION", r.getPerson().getPosition().getName()));

        if (r.getPerson().getStartdate() != null) {
            attributes.add(new AttributeAccount("PERSON_START_DATE", String.valueOf(r.getPerson().getStartdate().getTime())));
        }

        return attributes;
    }

}
