/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mh.oim.pojo;

import java.util.HashMap;

/**
 *
 * @author Datum-Redsoft
 */
public class TransferEntitlement {
    private String applicationKey;
    private String itResourceCode;
    private String groupcode;
    private String userLogin;
    private HashMap<String,String> others;
    private String systemCode;
    private String status;
    private String entitlementId;

    
    public TransferEntitlement() {
        others = new HashMap<>();
    }
    
    public String getEntitlementId() {
        return entitlementId;
    }

    public void setEntitlementId(String entitlementId) {
        this.entitlementId = entitlementId;
    }


    

    
    public String getItResourceCode() {
        return itResourceCode;
    }

    public void setItResourceCode(String itResourceCode) {
        this.itResourceCode = itResourceCode;
    }

    public String getApplicationKey() {
        return applicationKey;
    }

    public void setApplicationKey(String applicationKey) {
        this.applicationKey = applicationKey;
    }

    public String getGroupcode() {
        return groupcode;
    }

    public void setGroupcode(String groupcode) {
        this.groupcode = groupcode;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public HashMap<String, String> getOthers() {
        return others;
    }

    public void setOthers(HashMap<String, String> others) {
        this.others = others;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
    
}
