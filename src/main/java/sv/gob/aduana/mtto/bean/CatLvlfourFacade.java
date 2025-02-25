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
import sv.gob.aduana.entity.CatLvlfour;

/**
 *
 * @author Datum-Redsoft
 */
@Stateless
public class CatLvlfourFacade extends AbstractFacade<CatLvlfour> {

    @PersistenceContext(unitName = "sv.gob.aduana_dga-be-form_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CatLvlfourFacade() {
        super(CatLvlfour.class);
    }

    public List<CatLvlfour> getFindByEnabled(String levelThreeId) {
        List results = em.createNamedQuery("CatLvlfour.findByStatusLevel")
            .setParameter("status", 1)
            .setParameter("lvlthree",levelThreeId)    
            .getResultList();
    
        return results;
    }

    public CatLvlfour getLvlFour(String name) {
    
         CatLvlfour c = new CatLvlfour();
         
        try {
            c = (CatLvlfour) em.createNamedQuery("CatLvlfour.findByName")
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
