/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.aduana.mtto.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sv.gob.aduana.entity.Other;
import sv.gob.aduana.entity.System;

/**
 *
 * @author Datum-Redsoft
 */
@Stateless
public class SystemFacade extends AbstractFacade<System> {

    @PersistenceContext(unitName = "sv.gob.aduana_dga-be-form_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SystemFacade() {
        super(System.class);
    }
    
    
    public List<System> findExpired(){
        List results = null;   
        
         
        Long currentTime = java.lang.System.currentTimeMillis();
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatDateTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        
        String simple=formatDate.format(new Date(currentTime));
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = formatDateTime.parse(simple + " 00:00:00");
            endDate = formatDateTime.parse(simple + " 23:59:59");
            
        
        
        results = em.createNamedQuery("System.findExpired")
            .setParameter("startDate", startDate)
            .setParameter("endDate", endDate)
            .getResultList();
    
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        
        
        return results;
    }
    
    
    public List<System> findStarting(){
        List results = null;   
        
         
        Long currentTime = java.lang.System.currentTimeMillis();
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatDateTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        
        String simple=formatDate.format(new Date(currentTime));
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = formatDateTime.parse(simple + " 00:00:00");
            endDate = formatDateTime.parse(simple + " 23:59:59");
            
        
        
        results = em.createNamedQuery("System.findStarting")
            .setParameter("startDate", startDate)
            .setParameter("endDate", endDate)
            .getResultList();
    
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        
        
        return results;
    }
    
    
    public List<System> getByRequestId(String requestId){
        
        List results = em.createNamedQuery("System.findByRequestId")
            .setParameter("id", requestId)
            .getResultList();
    
        return results;
    }
}
