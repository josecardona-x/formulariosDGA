/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.aduana.mdb;

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
public class EntitlementResponseQueue  extends AbstractQueue<MessageQueue> {
    
    private final static String JNDI_FACTORY = "weblogic.jndi.WLInitialContextFactory";
    //JNDI name of WebLogic connection factory 
    private static String CONNECTION_FACTORY = "jms/CustomFormDGA";
    //JNDI name of WebLogic QUEUE 
    private static String QUEUE = "jms/entitlementResponseQueue";    
    
    
  
    @Override
    public boolean persist(MessageQueue msj) {
        return saveInQueue(msj,JNDI_FACTORY,CONNECTION_FACTORY,QUEUE);
    }

        
}

