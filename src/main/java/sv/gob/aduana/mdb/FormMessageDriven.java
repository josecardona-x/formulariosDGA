/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.aduana.mdb;

import com.google.gson.Gson;
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
import sv.gob.aduana.form.FormService;
import sv.gob.aduana.form.RequestTypeService;
import sv.gob.aduana.mtto.bean.FlowFacade;
import sv.gob.aduana.mtto.bean.FormuFacade;
import sv.gob.aduana.mtto.bean.GeneratorIdFacade;
import sv.gob.aduana.mtto.bean.RequestFacade;
import sv.gob.aduana.mtto.bean.RequestflowFacade;
import sv.gob.mh.oim.OIMService;
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
@ActivationConfigProperty(propertyName = "destinationJndiName", propertyValue = "jms/formQueue")
        },
        mappedName = "jms/formQueue"
)
public class FormMessageDriven extends AbstractMessageDriven implements MessageListener {

    @Resource
    private UserTransaction utx;

    @EJB
    protected GeneratorIdFacade generatorIdFacade;

    @EJB
    protected RequestFacade requestFacade;

    @EJB
    protected FormuFacade formuFacade;

    @EJB
    protected FlowFacade flowFacade;

    @EJB
    protected RequestflowFacade requestflowFacade;

    @EJB
    protected OIMService oimService;

    @EJB
    protected FormService formWrapper;

    @EJB
    protected IdentityResponseQueue identityResponseQueue;

    @Override
    public void startup(MessageQueue msj) {
        //Verificar en OIM la accion que se debe realizar con la identidad
        Formu form = formuFacade.find(msj.getId());
        String prefix = "";
        try {

            //Cambiar a inicio de actividades con step
            if (RequestTypeService.isIntern(form.getFormType())) {
                form.setStep("Validando las actividades a realizar en formulario Interno");
                prefix = "INT";
            }

            if (RequestTypeService.isExtern(form.getFormType())) {
                form.setStep("Validando las actividades a realizar en formulario Externo");
                prefix = "EXT";
            }

            formuFacade.edit(form);

            for (int i = 0; i < form.getRequestCollection().size(); i++) {
                Request r = form.getRequestCollection().get(i);

                r.setState("Iniciando procesamiento - Fase 1 : Asignación de identidades.");

                requestFacade.edit(r);

                MessageQueue mq = new MessageQueue();

                mq.setId(r.getId());
                mq.setAction("STARTUP");

                identityResponseQueue.persist(mq);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onMessage(Message message) {
        Gson gson = new Gson();
        try {
            TextMessage textMessage = (TextMessage) message;

            System.out.println("FormMessageDriven: " + textMessage.getText());

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

    @Override
    public void error(MessageQueue msj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void complete(MessageQueue msj) {
        try {
            Formu form = formuFacade.find(msj.getId());
            Boolean allRequestCompleted = true;
            for (Request r : form.getRequestCollection()) {
                if (!r.getState().equals("Permisos completados, Esperando aprobación de registro")) {
                    allRequestCompleted = false;
                }
            }

            if (allRequestCompleted) {
                form.setStep("Procesamiento finalizado correctamente, esperando aprobación de solicitud del Servicio al Cliente.");

                formuFacade.edit(form);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
