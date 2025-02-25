/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.aduana.mdb;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import sv.gob.mh.oim.pojo.Identity;

/**
 *
 * @author Datum-Redsoft
 */

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class IdentityRequestQueue extends AbstractQueue<Identity> {
    
    private final static String JNDI_FACTORY = "weblogic.jndi.WLInitialContextFactory";
    //JNDI name of WebLogic connection factory 
    private static String CONNECTION_FACTORY = "jms/CustomFormDGA";
    //JNDI name of WebLogic QUEUE 
    private static String QUEUE = "jms/identityRequestQueue";

    @Override
    public boolean persist(Identity identity) {
        return saveInQueue(identity,JNDI_FACTORY,CONNECTION_FACTORY,QUEUE);
    }

        
}
