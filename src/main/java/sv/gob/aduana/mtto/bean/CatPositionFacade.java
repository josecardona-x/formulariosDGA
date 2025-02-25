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
import sv.gob.aduana.entity.CatPosition;

/**
 *
 * @author Datum-Redsoft
 */
@Stateless
public class CatPositionFacade extends AbstractFacade<CatPosition> {

    @PersistenceContext(unitName = "sv.gob.aduana_dga-be-form_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CatPositionFacade() {
        super(CatPosition.class);
    }

    public List<CatPosition> getFindByEnabled() {
         List results = em.createNamedQuery("CatPosition.findByStatus")
            .setParameter("status", 1) 
            .getResultList();
    
        return results;
    }
      
    public CatPosition getPosition(String name){
        CatPosition c = new CatPosition();
         
        try {
            c = (CatPosition) em.createNamedQuery("CatPosition.findByName")
                    .setParameter("name", name)
                    .getSingleResult();
            
        } catch (Exception e) {
            c.setId("NA");
            c.setName("N/A");
            c.setStatus(1);
        }
        
        return c;
            
    }
    
}
