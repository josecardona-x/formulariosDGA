/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.aduana.mdb;

import com.google.gson.Gson;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import sv.gob.mh.oim.pojo.MessageQueue;

/**
 *
 * @author Datum-Redsoft
 */

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class FormQueue extends AbstractQueue<MessageQueue> {
    
    private final static String JNDI_FACTORY = "weblogic.jndi.WLInitialContextFactory";
    //JNDI name of WebLogic connection factory 
    private final static String CONNECTION_FACTORY = "jms/CustomFormDGA";
    //JNDI name of WebLogic QUEUE 
    private final static String QUEUE = "jms/formQueue";

    @Override
    public boolean persist(MessageQueue msj) {
        return saveInQueue(msj,JNDI_FACTORY,CONNECTION_FACTORY,QUEUE);
    }

        
}
