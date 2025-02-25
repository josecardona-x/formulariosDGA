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
import sv.gob.aduana.entity.CatLvlthree;

/**
 *
 * @author Datum-Redsoft
 */
@Stateless
public class CatLvlthreeFacade extends AbstractFacade<CatLvlthree> {

    @PersistenceContext(unitName = "sv.gob.aduana_dga-be-form_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CatLvlthreeFacade() {
        super(CatLvlthree.class);
    }

    public List<CatLvlthree> getFindByEnabled(String levelTwoId) {
        List results = em.createNamedQuery("CatLvlthree.findByStatusAndLevel")
            .setParameter("status", 1)
            .setParameter("lvltwo",levelTwoId)    
            .getResultList();
    
        return results;       
    }

    public CatLvlthree getLvlThree(String name) {
        
         CatLvlthree c = new CatLvlthree();
         
        try {
            c = (CatLvlthree) em.createNamedQuery("CatLvlthree.findByName")
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
