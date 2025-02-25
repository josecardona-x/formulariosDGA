/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.aduana.mtto.bean;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sv.gob.aduana.entity.Flow;

/**
 *
 * @author Datum-Redsoft
 */
@Stateless
public class FlowFacade extends AbstractFacade<Flow> {

    @PersistenceContext(unitName = "sv.gob.aduana_dga-be-form_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FlowFacade() {
        super(Flow.class);
    }
    
    
    public List<Flow> getFlowByProcess(String processId) {
        
        List results = em.createNamedQuery("Flow.findByProcess")
            .setParameter("process", processId)
            .getResultList();
    
        return results;
    }
    
}
