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
public class TransferAccount {
    
    //@JsonProperty("userLogin")
  private String userLogin = null;

  private String customUserLogin = null;

  //@JsonProperty("accountKey")
  private String accountKey = null;

  //@JsonProperty("applicationName")
  private String applicationName = null;

  //@JsonProperty("applicationKeyName")
  private String applicationKeyName = null;
    
  //@JsonProperty("applicationKeyName")
  private String itResourceCode = null;

  //@JsonProperty("childsTables")
  private HashMap<String,String> attributes = null;

  //@JsonProperty("justification")
  private String justification = null;

  //@JsonProperty("accountId")
  private String typeRequest = null;
  
  //@JsonProperty("accountId")
  private String requestId = null;
  
  //@JsonProperty("accountId")
  private String appIdentityId = null;
  
  private String groupTable = null;

    public String getGroupTable() {
        return groupTable;
    }

    public void setGroupTable(String groupTable) {
        this.groupTable = groupTable;
    }

    public String getCustomUserLogin() {
        return customUserLogin;
    }

    public void setCustomUserLogin(String customUserLogin) {
        this.customUserLogin = customUserLogin;
    }
  
  

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getAccountKey() {
        return accountKey;
    }

    public void setAccountKey(String accountKey) {
        this.accountKey = accountKey;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationKeyName() {
        return applicationKeyName;
    }

    public void setApplicationKeyName(String applicationKeyName) {
        this.applicationKeyName = applicationKeyName;
    }

    public String getItResourceCode() {
        return itResourceCode;
    }

    public void setItResourceCode(String itResourceCode) {
        this.itResourceCode = itResourceCode;
    }

    public HashMap<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(HashMap<String, String> attributes) {
        this.attributes = attributes;
    }

   

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public String getTypeRequest() {
        return typeRequest;
    }

    public void setTypeRequest(String typeRequest) {
        this.typeRequest = typeRequest;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getAppIdentityId() {
        return appIdentityId;
    }

    public void setAppIdentityId(String appIdentityId) {
        this.appIdentityId = appIdentityId;
    }
    
    
}
  
