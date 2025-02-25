/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.aduana.mtto.bean;

import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import sv.gob.aduana.entity.Formu;

/**
 *
 * @author Datum-Redsoft
 */
@Stateless
public class FormuFacade extends AbstractFacade<Formu> {

    
    @PersistenceContext(unitName = "sv.gob.aduana_dga-be-form_war_1.0.0PU")
    private EntityManager em;
    
    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FormuFacade() {
        super(Formu.class);
    }
   
    
    public List<Formu> getFormuByRoleAndStatus(String role,String status){
        
        Integer closed = status.equals("FINISH") ? 1 : 0;
        
        if(role.contains("SOLICITANTE"))
            role = "NOT_ALLOWED"; 
        
        List results = em.createNamedQuery("Formu.findByStatusAndClosed")
            .setParameter("status", status)
            .setParameter("closed", closed)
            .setParameter("role", role)
            .getResultList();
    
        return results;
    }
    
    
    public List<Formu> getFormByUserAndStatus(String user,String status){
        Integer closed = status.equals("FINISH") ? 1 : 0;
        
        List results = em.createNamedQuery("Formu.findByUserStatusAndClose")
            .setParameter("status", status)
            .setParameter("closed", closed)
            .setParameter("user", user)
            .setParameter("applicantviewer", user)
            .getResultList();
    
        return results;
    }

    public List<Formu> getFormByRoleStep(String role) {
          List results = em.createNamedQuery("Formu.findByRoleStep")
            .setParameter("roleStep", role)
            .getResultList();
    
        return results;
    }
    
}
