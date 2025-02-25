/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.aduana.mdb;

import com.google.gson.Gson;
import java.util.Hashtable;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import sv.gob.mh.oim.pojo.MessageQueue;

/**
 *
 * @author Datum-Redsoft
 */
public abstract class AbstractQueue<T> {
    
    public abstract boolean persist(T t);
    
    
    public boolean saveInQueue(T t, String jndi, String factory, String queue) {
        Gson gson = new Gson();
        
        String message = gson.toJson(t);
        
        Boolean saved = sendQueue(queue, factory, jndi, message);
        
        return saved;
    }
    
    public  InitialContext getInitialContext(String url,String jndi)
            throws NamingException {
        Hashtable<String, String> env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, jndi);
        env.put(Context.PROVIDER_URL, url);
        return new InitialContext(env);
    }
    
    protected boolean sendQueue(String q,String factory,String jndi,String message) {
        Boolean saved = false;
    
        try {
            //Sent to Queue
            InitialContext namingContext = getInitialContext("t3://oimserver:17005",jndi);

            //Queue
            Queue queue = (Queue) namingContext.lookup(q);

            //Connection Factory 
            QueueConnectionFactory queueConnectionFactory
                    = (QueueConnectionFactory) namingContext.lookup(factory);

            QueueConnection conn = queueConnectionFactory.createQueueConnection();

            QueueSession session = conn.createQueueSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);

            TextMessage queueMessage = session.createTextMessage(message);
            conn.start();

            session.createSender(queue).send(queueMessage);
            conn.close();
            
            saved = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return saved;
    }
}
