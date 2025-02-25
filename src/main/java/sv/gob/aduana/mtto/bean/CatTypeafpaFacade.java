/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.aduana.mtto.bean;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sv.gob.aduana.entity.CatTypeafpa;

/**
 *
 * @author Datum-Redsoft
 */
@Stateless
public class CatTypeafpaFacade extends AbstractFacade<CatTypeafpa> {

    @PersistenceContext(unitName = "sv.gob.aduana_dga-be-form_war_1.0.0PU")
    private EntityManager em;
    
    @EJB
    protected EventLogFacade eventLogFacade;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CatTypeafpaFacade() {
        super(CatTypeafpa.class);
    }

    public List getFindByEnabled() {
        //CatLvltwo.findByStatus
        List results = em.createNamedQuery("CatTypeafpa.findByStatus")
                .setParameter("status", 1)
                .getResultList();

        return results;
    }

    public CatTypeafpa getCatalogTypeAFPAByName(String name, String code) {
        //CatLvltwo.findByStatus
        CatTypeafpa catTypeafpa = new CatTypeafpa();

        List<CatTypeafpa> results = em.createNamedQuery("CatTypeafpa.findByStatus")
                .setParameter("status", 1)
                .getResultList();

        String codeCleaner = code.replaceAll("-", "").replaceAll("[0-9]", "");

        
        for (CatTypeafpa s : results) {
            int distance = computeEditDistance(s.getName() + " " + codeCleaner, name);
            if (distance < 5) {
                eventLogFacade.writeInLog("DEBUG", "computeEditDistance Found : "+s.getName());
                catTypeafpa = s;
            }
        }

        return catTypeafpa;
    }

    public static int computeEditDistance(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        int[] costs = new int[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            int lastValue = i;
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0) {
                    costs[j] = j;
                } else {
                    if (j > 0) {
                        int newValue = costs[j - 1];
                        if (s1.charAt(i - 1) != s2.charAt(j - 1)) {
                            newValue = Math.min(Math.min(newValue, lastValue),
                                    costs[j]) + 1;
                        }
                        costs[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }
            if (i > 0) {
                costs[s2.length()] = lastValue;
            }
        }
        return costs[s2.length()];
    }

}
