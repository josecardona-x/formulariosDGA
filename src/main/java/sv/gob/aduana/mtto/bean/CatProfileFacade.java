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
import sv.gob.aduana.entity.CatProfile;

/**
 *
 * @author Datum-Redsoft
 */
@Stateless
public class CatProfileFacade extends AbstractFacade<CatProfile> {

    @PersistenceContext(unitName = "sv.gob.aduana_dga-be-form_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CatProfileFacade() {
        super(CatProfile.class);
    }
 
    public List<CatProfile> getProfileByRole(String rol) {
        
        
        List results = em.createNamedQuery("CatProfile.findByStatusAndRole")
                .setParameter("status", 1)
                .setParameter("role","%"+rol+"%")
                .getResultList();

        return results;
    }

}
