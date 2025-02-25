/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.aduana.mdb;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.transaction.UserTransaction;
import sv.gob.aduana.entity.AccountAffected;
import sv.gob.aduana.entity.CatAppsidentity;
import sv.gob.aduana.entity.CatProfiledetail;
import sv.gob.aduana.entity.Formu;
import sv.gob.aduana.entity.Other;
import sv.gob.aduana.entity.Profile;
import sv.gob.aduana.entity.Request;
import sv.gob.aduana.form.FormTranslator;
import sv.gob.aduana.mtto.bean.AccountAffectedFacade;
import sv.gob.aduana.mtto.bean.FormuFacade;
import sv.gob.aduana.mtto.bean.OtherFacade;
import sv.gob.aduana.mtto.bean.ProfileFacade;
import sv.gob.aduana.mtto.bean.RequestFacade;
import sv.gob.aduana.mtto.bean.SystemFacade;
import sv.gob.mh.oim.pojo.AttributeAccount;
import sv.gob.mh.oim.pojo.ChildTableAccount;
import sv.gob.mh.oim.pojo.Entitlement;
import sv.gob.mh.oim.pojo.GroupEntitlement;
import sv.gob.mh.oim.pojo.MessageQueue;
import sv.gob.mh.oim.pojo.TransferAccount;
import sv.gob.mh.oim.pojo.TransferEntitlement;

/**
 *
 * @author Datum-Redsoft
 */
@MessageDriven(
        activationConfig = {
            @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
            ,
@ActivationConfigProperty(propertyName = "connectionFactoryJndiName", propertyValue = "jms/CustomFormDGA")
            ,
@ActivationConfigProperty(propertyName = "destinationJndiName", propertyValue = "jms/entitlementResponseQueue")
        },
        mappedName = "jms/entitlementResponseQueue"
)
public class EntitlementResponseMessageDriven extends AbstractMessageDriven implements MessageListener {

    @Resource
    private UserTransaction utx;

    @EJB
    protected RequestFacade requestFacade;
    
    @EJB
    protected ProfileFacade profileFacade;
    
    @EJB
    protected SystemFacade systemFacade;
    
    @EJB
    protected OtherFacade otherFacade;
    

    @EJB
    protected FormuFacade formuFacade;

    @EJB
    protected FormQueue formQueue;

    @EJB
    protected IdentityRequestQueue identityQueue;

    @EJB
    protected AccountRequestQueue accountRequestQueue;

    @EJB
    protected EntitlementResponseQueue entitlementResponseQueue;

    @EJB
    protected EntitlementRequestQueue entitlementRequestQueue;
    
    @EJB
    protected AccountAffectedFacade accountAffectedFacade;

    @Override
    public void startup(MessageQueue msj) {
        List<TransferAccount> accounts = new ArrayList<>();
        List<GroupEntitlement> groupEntitlement = new ArrayList<>();
        Boolean isAllLeastOneEntitlement = false;
        Request r = requestFacade.find(msj.getId());
        Formu f = formuFacade.find(r.getForm().getId());

        //Cargar todas cuentas 
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

        //No hay cuentas adicionales, llamar a cierre de form
        if (t.isEmpty()) {
            msj.setId(r.getForm().getId());
            msj.setAction("COMPLETE");
            formQueue.persist(msj);
            return;
        }

        t.forEach((c) -> {
            
            List<ChildTableAccount> childTable = new ArrayList<>();
            TransferAccount a = new TransferAccount();
            
            AccountAffected accountAffected = accountAffectedFacade.getByRequestAndIdentity(r.getId(),c.getId());
            
            
            a.setApplicationKeyName(c.getCodename());
            a.setApplicationName(c.getName());
            a.setUserLogin(r.getPerson().getLogin());
            a.setCustomUserLogin(accountAffected.getAccount());
            a.setAccountKey(String.valueOf(c.getApplicationKey()));
            a.setJustification("[REQUEST " + r.getId() + "] ENTITLEMENT "+c.getName()+
                    " - Actividad "+r.getTyperequest().getName()+" elaborado por : " +
                    f.getCreatedname() + " en " + f.getCreatedon());
            a.setItResourceCode(String.valueOf(c.getItresourcecode()));
            a.setTypeRequest(r.getTyperequest().getId());
            a.setAppIdentityId(c.getId());
            a.setRequestId(r.getId());
            a.setGroupTable(c.getGrouptable());
            
            List<AttributeAccount> attrs = FormTranslator.requestToAttributes(r,f);
            HashMap<String,String> attributes = new HashMap<>();
            
            for(AttributeAccount aa : attrs){
                attributes.put(aa.getKey(), aa.getValue());
            }
            
            a.setAttributes(attributes);
            
            accounts.add(a);
        });

        //Se tiene el listado, ahora crear los entitlements
        for (TransferAccount s : accounts) {
            Set<TransferEntitlement> ent = new HashSet<>();
            for (Profile p : r.getProfileCollection()) {
                if (p.getStatus().equals("PENDIENTE DE ASIGNAR") || p.getStatus().equals("PENDIENTE DE BORRAR")) {
                    for (CatProfiledetail pd : p.getProfile().getCatProfiledetailList()) {
                        if (s.getAppIdentityId().equals(pd.getAppsidentity().getId())) {
                            isAllLeastOneEntitlement = true;
                            
                            TransferEntitlement e = new TransferEntitlement();
                            
                            e.setApplicationKey(pd.getAppsidentity().getCodename());
                            e.setItResourceCode(String.valueOf(pd.getAppsidentity().getItresourcecode()));
                            e.setGroupcode(pd.getGroup());
                            e.setUserLogin(r.getPerson().getLogin());
                            
                            //Datos del perfil adicionales
                            e.getOthers().put("ASSIGN_CUSTOM", pd.getAssignCustom());
                            e.getOthers().put("COMPL_CUSTOM", pd.getComplementaryCustom());
                            e.getOthers().put("OTHER_CUSTOM", pd.getOtherPermission());
                            e.getOthers().put("CUSTOM", p.getCustom());
                            
                            e.setSystemCode(pd.getSystemName());
                            e.setStatus(p.getStatus());
                            e.setEntitlementId(pd.getId());
                            
                            ent.add(e);
                        }
                    }
                }
            }

            for (sv.gob.aduana.entity.System si : r.getSystemCollection()) {
                if (si.getStatus().equals("PENDIENTE DE ASIGNAR") || si.getStatus().equals("PENDIENTE DE BORRAR")) {
                    if (s.getAppIdentityId().equals(si.getGroup1().getSystem().getAppsidentity().getId())) {
                        isAllLeastOneEntitlement = true;
                        
                        TransferEntitlement e = new TransferEntitlement();

                        e.setApplicationKey(si.getGroup1().getSystem().getAppsidentity().getCodename());
                        e.setItResourceCode(String.valueOf(si.getGroup1().getSystem().getAppsidentity().getItresourcecode()));
                        e.setGroupcode(si.getGroup1().getTechname());
                        e.setUserLogin(r.getPerson().getLogin());

                        //Datos del perfil adicionales
                        e.getOthers().put("ASSIGN_CUSTOM", "");
                        e.getOthers().put("COMPL_CUSTOM", "");
                        e.getOthers().put("OTHER_CUSTOM", "");
                        e.getOthers().put("CUSTOM", si.getCustom());

                        e.setSystemCode(si.getGroup1().getSystem().getTechName());
                        e.setStatus(si.getStatus());
                        e.setEntitlementId(si.getGroup1().getId());
                            
                   
                        ent.add(e);
                    }
                }
            }

            GroupEntitlement ge = new GroupEntitlement();
            ge.setAccount(s);
            ge.setEntitlements(ent);

            groupEntitlement.add(ge);

        }

        if (t.isEmpty()) {
            msj.setId(r.getForm().getId());
            msj.setAction("COMPLETE");
            formQueue.persist(msj);
            return;
        }

        entitlementRequestQueue.persist(groupEntitlement);
    }

    @Override
    public void error(MessageQueue msj) {
          try {
            if(msj.getId() == null)
                return;
            Request r = requestFacade.find(msj.getId());

            //  utx.begin();
                r.setState(msj.getAction());
                requestFacade.edit(r);
                
            //utx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void complete(MessageQueue msj) {
        try {
            
            if(msj.getId() == null)
                return;
            
            Request r = requestFacade.find(msj.getId());

            //   utx.begin();
            r.setState("Permisos completados, Esperando aprobación de registro");
            
            r.getProfileCollection().stream().map((p) -> {
                if (p.getStatus().equals("PENDIENTE DE ASIGNAR") ) {
                    p.setStatus("ASIGNADO CORRECTAMENTE");
                }
                return p;
            }).map((p) -> {
                if (p.getStatus().equals("PENDIENTE DE BORRAR")) {
                    p.setStatus("BORRADO CORRECTAMENTE");
                }
                return p;                
            }).forEachOrdered((p) -> {
                profileFacade.edit(p);
            });
            
             
            r.getSystemCollection().stream().map((si) -> {
                if (si.getStatus().equals("PENDIENTE DE ASIGNAR") ) {
                    si.setStatus("ASIGNADO CORRECTAMENTE");
                }
                return si;
            }).map((si) -> {
                if (si.getStatus().equals("PENDIENTE DE BORRAR")) {
                    si.setStatus("BORRADO CORRECTAMENTE");
                }
                return si;                
            }).forEachOrdered((si) -> {
                systemFacade.edit(si);
            });
            
            requestFacade.edit(r);

            
            msj.setId(r.getForm().getId());
            msj.setAction("COMPLETE");
            
            formQueue.persist(msj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

    @Override
    public void onMessage(Message message) {
        Gson gson = new Gson();
        try {
            TextMessage textMessage = (TextMessage) message;

            System.out.println("EntitlementResponseMessageDriven: " + textMessage.getText());

            MessageQueue msj = gson.fromJson(textMessage.getText(), MessageQueue.class);

            if (msj.getAction().equals("STARTUP")) {
                startup(msj);
            }
            if (msj.getAction().equals("COMPLETE")) {
                complete(msj);
            }
            
            error(msj);
            

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
