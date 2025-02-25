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
import sv.gob.aduana.entity.CatSystem;

/**
 *
 * @author Datum-Redsoft
 */
@Stateless
public class CatSystemFacade extends AbstractFacade<CatSystem> {

    @PersistenceContext(unitName = "sv.gob.aduana_dga-be-form_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CatSystemFacade() {
        super(CatSystem.class);
    }

    public List<CatSystem> getFindByEnabled() {
        //CatLvltwo.findByStatus
        List results = em.createNamedQuery("CatSystem.findByStatus")
                .setParameter("status", 1)
                .getResultList();

        return results;
    }

    public List<CatSystem> getSystemByRole(String role) {
     List results = em.createNamedQuery("CatSystem.findByStatusAndRole")
                .setParameter("status", 1)
                .setParameter("role","%"+ role+"%")
                .getResultList();

        return results;
    }

    
}
