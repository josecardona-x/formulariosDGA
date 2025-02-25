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
import sv.gob.aduana.entity.CatGroup;

/**
 *
 * @author Datum-Redsoft
 */
@Stateless
public class CatGroupFacade extends AbstractFacade<CatGroup> {

    @PersistenceContext(unitName = "sv.gob.aduana_dga-be-form_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CatGroupFacade() {
        super(CatGroup.class);
    }

    public List<CatGroup> getGroupBySystem(String system) {

        List results = em.createNamedQuery("CatGroup.findBySystemAndStatus")
        .setParameter("system", system)
        .setParameter("status", 1)
        .getResultList();

        return results;
    }

}
