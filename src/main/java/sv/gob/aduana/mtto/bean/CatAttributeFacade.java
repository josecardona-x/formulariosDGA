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
import sv.gob.aduana.entity.CatAttribute;
import sv.gob.aduana.entity.CatLvlfour;

/**
 *
 * @author Datum-Redsoft
 */
@Stateless
public class CatAttributeFacade extends AbstractFacade<CatAttribute> {

    @PersistenceContext(unitName = "sv.gob.aduana_dga-be-form_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CatAttributeFacade() {
        super(CatAttribute.class);
    }

    public List<CatAttribute> getFindByEnabled() {
        //CatLvltwo.findByStatus
        List results = em.createNamedQuery("CatAttribute.findByStatus")
                .setParameter("status", 1)
                .getResultList();

        return results;
    }

    public CatAttribute getAttribute(String name) {
   
         CatAttribute c = new CatAttribute();
         
        try {
            c = (CatAttribute) em.createNamedQuery("CatAttribute.findByName")
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
