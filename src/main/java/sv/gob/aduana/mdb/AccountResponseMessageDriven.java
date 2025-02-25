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
import sv.gob.aduana.entity.Formu;
import sv.gob.aduana.entity.Request;
import sv.gob.aduana.form.FormTranslator;
import sv.gob.aduana.mtto.bean.AccountAffectedFacade;
import sv.gob.aduana.mtto.bean.FormuFacade;
import sv.gob.aduana.mtto.bean.ProfileFacade;
import sv.gob.aduana.mtto.bean.RequestFacade;
import sv.gob.mh.oim.pojo.AttributeAccount;
import sv.gob.mh.oim.pojo.ChildTableAccount;
import sv.gob.mh.oim.pojo.MessageQueue;
import sv.gob.mh.oim.pojo.TransferAccount;

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
@ActivationConfigProperty(propertyName = "destinationJndiName", propertyValue = "jms/accountResponseQueue")
        },
        mappedName = "jms/accountResponseQueue"
)
public class AccountResponseMessageDriven extends AbstractMessageDriven implements MessageListener {

    @Resource
    private UserTransaction utx;

    @EJB
    protected RequestFacade requestFacade;

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
    protected ProfileFacade profileFacade;
    
    @EJB
    protected AccountAffectedFacade accountAffectedFacade;
    
    

    @Override
    public void startup(MessageQueue msj) {
        //Comenzar a verificar todos los que se deben crear/actualizar
        Request r = requestFacade.find(msj.getId());
        Formu f = formuFacade.find(r.getForm().getId());

        List<TransferAccount> accounts = new ArrayList<>();

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
            
            AccountAffected accountAffected = accountAffectedFacade.getByRequestAndIdentity(r.getId(),c.getId());
            
            TransferAccount a = new TransferAccount();
            a.setApplicationKeyName(c.getCodename());
            a.setApplicationName(c.getName());
            a.setUserLogin(r.getPerson().getLogin());
            a.setCustomUserLogin(accountAffected.getAccount());
            a.setAccountKey(String.valueOf(c.getApplicationKey()));
            a.setJustification("[ " + r.getId() + "] ACCOUNT " + c.getName());
            a.setItResourceCode(String.valueOf(c.getItresourcecode()));
            a.setTypeRequest(r.getTyperequest().getId());
            a.setAppIdentityId(c.getId());
            a.setRequestId(r.getId());
            a.setGroupTable(c.getGrouptable());

            List<AttributeAccount> attrs = FormTranslator.requestToAttributes(r, f);
            HashMap<String, String> attributes = new HashMap<>();

            for (AttributeAccount aa : attrs) {
                attributes.put(aa.getKey(), aa.getValue());
            }

            a.setAttributes(attributes);

            accounts.add(a);
        });

        accountRequestQueue.persist(accounts);
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
                r.setState("Cuentas modificadas, iniciando permisos");

            requestFacade.edit(r);

            msj.setAction("STARTUP");

            entitlementResponseQueue.persist(msj);

            //    utx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onMessage(Message message) {
        Gson gson = new Gson();
        try {
            TextMessage textMessage = (TextMessage) message;

            System.out.println("AccountResponseMessageDriven: " + textMessage.getText());

            MessageQueue msj = gson.fromJson(textMessage.getText(), MessageQueue.class);

            if (msj.getAction().equals("STARTUP")) {
                startup(msj);
                return;
            }
            if (msj.getAction().equals("COMPLETE")) {
                complete(msj);
                return;
            }

            error(msj);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
