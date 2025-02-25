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
import sv.gob.aduana.entity.AccountAffected;
import sv.gob.aduana.entity.Applicant;

/**
 *
 * @author Datum-Redsoft
 */
@Stateless
public class AccountAffectedFacade extends AbstractFacade<AccountAffected> {

    @PersistenceContext(unitName = "sv.gob.aduana_dga-be-form_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AccountAffectedFacade() {
        super(AccountAffected.class);
    }
    
    
    public List<AccountAffected> getByRequest(String requestId){
        //CatLvltwo.findByStatus
        List  results = em.createNamedQuery("AccountAffected.findByRequest")
            .setParameter("request", requestId)
            .getResultList();
    
        return results;       
    }
    
      
    
    public AccountAffected getByRequestAndIdentity(String requestId,String resourceId){
        //CatLvltwo.findByStatus
        AccountAffected account = (AccountAffected) em.createNamedQuery("AccountAffected.findByRequestAndIdentity")
            .setParameter("request", requestId)
            .setParameter("appidentity", resourceId)
            .getSingleResult();
    
        return account;       
    }
    
}
