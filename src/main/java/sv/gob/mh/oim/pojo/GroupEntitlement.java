/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mh.oim.pojo;

import java.util.Set;

/**
 *
 * @author Datum-Redsoft
 */
public class GroupEntitlement {
    private TransferAccount account;
    private Set<TransferEntitlement> entitlements;

    public TransferAccount getAccount() {
        return account;
    }

    public void setAccount(TransferAccount account) {
        this.account = account;
    }

  
    public Set<TransferEntitlement> getEntitlements() {
        return entitlements;
    }

    public void setEntitlements(Set<TransferEntitlement> entitlements) {
        this.entitlements = entitlements;
    }
    
} 


