/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.aduana.entity;

/**
 *
 * @author Datum-Redsoft
 */
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "ACCOUNTAFFECTED")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccountAffected.findAll", query = "SELECT a FROM AccountAffected a"),
    @NamedQuery(name = "AccountAffected.findByRequest", query = "SELECT a FROM AccountAffected a where a.request = :request"),
    @NamedQuery(name = "AccountAffected.findByRequestAndIdentity", query = "SELECT a FROM AccountAffected a where a.request = :request and a.appIdentity= :appidentity")})
public class AccountAffected  implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ID")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "REQUEST")
    private String request;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "APPIDENTITY")
    private String appIdentity;
    
    @Column(name = "ACCOUNT")
    private String account;    
    
    @Column(name = "CREATEDBY")
    private String createdBy;
    
    @Column(name = "CREATEDON")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date createdOn;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getAppIdentity() {
        return appIdentity;
    }

    public void setAppIdentity(String appIdentity) {
        this.appIdentity = appIdentity;
    }

  

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

   
    
    
    
}
