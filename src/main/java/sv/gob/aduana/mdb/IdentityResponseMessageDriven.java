/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.aduana.mdb;

import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.transaction.UserTransaction;
import sv.gob.aduana.entity.Formu;
import sv.gob.aduana.entity.Request;
import sv.gob.aduana.form.FormTranslator;
import sv.gob.aduana.form.RequestTypeService;
import sv.gob.aduana.mtto.bean.FormuFacade;
import sv.gob.aduana.mtto.bean.RequestFacade;
import sv.gob.mh.oim.pojo.Identity;
import sv.gob.mh.oim.pojo.MessageQueue;

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
@ActivationConfigProperty(propertyName = "destinationJndiName", propertyValue = "jms/identityResponseQueue")
        },
        mappedName = "jms/identityResponseQueue"
)
public class IdentityResponseMessageDriven extends AbstractMessageDriven implements MessageListener {

    @Resource
    private UserTransaction utx;

    @EJB
    protected RequestFacade requestFacade;

    @EJB
    protected FormuFacade formuFacade;

    @EJB
    protected FormQueue formQueue;

    @EJB
    protected IdentityRequestQueue identityRequestQueue;
    
    @EJB
    protected AccountResponseQueue accountResponseQueue;

    @Override
    public void startup(MessageQueue msj) {
        try {
            SimpleDateFormat simple = new SimpleDateFormat("dd/MM/yyyy");
            Request r = requestFacade.find(msj.getId());
            Formu f = formuFacade.find(r.getForm().getId());
            Identity identity = null;
            identity = FormTranslator.personToIdentity(r);
            
            if (RequestTypeService.isNew(r.getTyperequest().getId())) {
                identity.setJustification("[" + r.getId() + "] NEW ");
                identity.setComentarios(r.getTyperequest().getId());
                identity.setActualizadoPor(r.getId());
            }
            
            if (RequestTypeService.isUpdated(r.getTyperequest().getId())) {
                identity.setJustification("[" + r.getId() + "] UPDATE ");
                identity.setComentarios(r.getTyperequest().getId());
                identity.setActualizadoPor(r.getId());
            }
            
            if (RequestTypeService.isEnabled(r.getTyperequest().getId())) {
                identity.setJustification("[" + r.getId() + "] ENABLE ");
                identity.setComentarios(r.getTyperequest().getId());
                identity.setActualizadoPor(r.getId());
            }
            
            if (RequestTypeService.isDisabled(r.getTyperequest().getId())) {
                String action = "";
                if (r.getDeleteAllGroup() == 1) {
                    action += "_DLG_";
                }
                if (r.getMoveToDesactive() == 1) {
                    action += "_MD_";
                }
                
                identity.setJustification("[" + r.getId() + "] DISABLED "+action);
                identity.setComentarios(r.getTyperequest().getId());
                identity.setActualizadoPor(r.getId());
            }
            
            identityRequestQueue.persist(identity);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

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

            //  utx.begin();
                r.setState("Identidad modificada, iniciando cuentas");
                requestFacade.edit(r);
                
                msj.setAction("STARTUP");
                
                accountResponseQueue.persist(msj);
            //utx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void onMessage(Message message) {
        Gson gson = new Gson();
        try {
            TextMessage textMessage = (TextMessage) message;

            System.out.println("IdentityResponseMessageDriven: " + textMessage.getText());

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
