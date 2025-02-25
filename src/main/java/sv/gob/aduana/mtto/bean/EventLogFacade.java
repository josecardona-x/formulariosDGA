/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.aduana.mtto.bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sv.gob.aduana.entity.EventLog;
import sv.gob.aduana.entity.GeneratorId;

/**
 *
 * @author Datum-Redsoft
 */
@Stateless
public class EventLogFacade extends AbstractFacade<EventLog> {

    @PersistenceContext(unitName = "sv.gob.aduana_dga-be-form_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EventLogFacade() {
        super(EventLog.class);
    }

    public void writeInLog(String level,String event){
        SimpleDateFormat simple = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        
        System.out.println("<" + simple.format(new Date())
                + "> <FORM-DGA> <LOG> <"+level+"> <" + event + ">");

    }
    
    public void insertInAudit(String form, String request, String event, String type, String loginUser) {
        String id = UUID.randomUUID().toString() + "-" + System.currentTimeMillis();
        SimpleDateFormat simple = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        EventLog eventLog = new EventLog();

        eventLog.setId(id);
        eventLog.setForm(form);
        eventLog.setRequest(request);
        eventLog.setType(type);
        eventLog.setEvent(event);
        eventLog.setLoginUser(loginUser);
        eventLog.setLogDate(new Date());

        System.out.println("<" + simple.format(new Date())
                + "> <FORM-DGA> <FORMID-" + form + "> "
                + "<REQID-" + request + "> "
                + "<" + type + "> <LOGIN:" + loginUser + "> <" + event + ">");

        create(eventLog);
    }

}
