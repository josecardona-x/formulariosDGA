/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.aduana.mdb;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import sv.gob.mh.oim.pojo.Account;
import sv.gob.mh.oim.pojo.TransferAccount;


/**
 *
 * @author Datum-Redsoft
 */

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class AccountRequestQueue  extends AbstractQueue<List<TransferAccount>> {
    
    private final static String JNDI_FACTORY = "weblogic.jndi.WLInitialContextFactory";
    //JNDI name of WebLogic connection factory 
    private static String CONNECTION_FACTORY = "jms/CustomFormDGA";
    //JNDI name of WebLogic QUEUE 
    private static String QUEUE = "jms/accountRequestQueue";

    @Override
    public boolean persist(List<TransferAccount> identity) {
        return saveInQueue(identity,JNDI_FACTORY,CONNECTION_FACTORY,QUEUE);
    }

}
        
