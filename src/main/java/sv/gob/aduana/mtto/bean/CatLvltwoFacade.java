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
import sv.gob.aduana.entity.CatLvltwo;

/**
 *
 * @author Datum-Redsoft
 */
@Stateless
public class CatLvltwoFacade extends AbstractFacade<CatLvltwo> {

    @PersistenceContext(unitName = "sv.gob.aduana_dga-be-form_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CatLvltwoFacade() {
        super(CatLvltwo.class);
    }

    public List<CatLvltwo> getFindByEnabled(String levelOneId) {
        List results = em.createNamedQuery("CatLvltwo.findByStatusAndLevel")
            .setParameter("status", 1)
            .setParameter("lvlone",levelOneId)    
            .getResultList();
    
        return results;       
    }

    public CatLvltwo getLevelTwo(String name) {
    
         CatLvltwo c = new CatLvltwo();
         
        try {
            c = (CatLvltwo) em.createNamedQuery("CatLvltwo.findByName")
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
