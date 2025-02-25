/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.aduana.mtto.bean;

import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sv.gob.aduana.entity.Request;
import sv.gob.aduana.entity.Requestflow;

/**
 *
 * @author Datum-Redsoft
 */
@Stateless
public class RequestflowFacade extends AbstractFacade<Requestflow> {

    @PersistenceContext(unitName = "sv.gob.aduana_dga-be-form_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RequestflowFacade() {
        super(Requestflow.class);
    }

    public List<Requestflow> getByRequestId(String requestId) {
         
        List results = em.createNamedQuery("Requestflow.findByRequestId")
            .setParameter("id", requestId)
            .getResultList();
        
        
        return results;
    }

    public Request getRequestByRequestFlow(String requestFlowId) {
        
        Request request = null;
        
        
        request = (Request) em.createNamedQuery("Requestflow.findRequestById")
            .setParameter("id", requestFlowId)
            .getSingleResult();
        
        
        return request;
    }

    public Long findMinStep(String requestId) {
         Long request = null;
        
        
        request = (Long) em.createNamedQuery("Requestflow.findMinStep")
            .setParameter("id", requestId)
            .getSingleResult();
        
        
        return request;
    }

    
    public Requestflow getLastStep(String id, Long step) {
          Requestflow request = null;
        
        
        request = (Requestflow) em.createNamedQuery("Requestflow.findLastStep")
            .setParameter("id", id)
            .setParameter("step", step)
            .getSingleResult();
        
        
        return request;
    }
    
}
