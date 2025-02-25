/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.aduana.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Datum-Redsoft
 */
@Entity
@Table(name = "CAT_PROFILEDETAIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatProfiledetail.findAll", query = "SELECT c FROM CatProfiledetail c")
    , @NamedQuery(name = "CatProfiledetail.findById", query = "SELECT c FROM CatProfiledetail c WHERE c.id = :id")
    , @NamedQuery(name = "CatProfiledetail.findByGroup", query = "SELECT c FROM CatProfiledetail c WHERE c.group = :group")
    , @NamedQuery(name = "CatProfiledetail.findByRequiredcustom", query = "SELECT c FROM CatProfiledetail c WHERE c.requiredcustom = :requiredcustom")})
public class CatProfiledetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ID")
    private String id;
    @Size(max = 255)
    @Column(name = "groupValue")
    private String group;
    @Column(name = "ASSIGNCUSTOM")
    private String assignCustom;
    
    @Column(name = "COMPLEMENTARYCUSTOM")
    private String complementaryCustom;
    
    @Column(name = "OTHERPERMISSION")
    private String otherPermission;
    
    @Column(name = "SYSTEMNAME")
    private String systemName;
    
    @Column(name = "REQUIREDCUSTOM")
    private Integer requiredcustom;
    @JoinColumn(name = "APPSIDENTITY", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private CatAppsidentity appsidentity;
    @JoinColumn(name = "PROFILE", referencedColumnName = "ID")
    @ManyToOne
    private CatProfile profile;

    public CatProfiledetail() {
    }

    public CatProfiledetail(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Integer getRequiredcustom() {
        return requiredcustom;
    }

    public void setRequiredcustom(Integer requiredcustom) {
        this.requiredcustom = requiredcustom;
    }

    public CatAppsidentity getAppsidentity() {
        return appsidentity;
    }

    public void setAppsidentity(CatAppsidentity appsidentity) {
        this.appsidentity = appsidentity;
    }

    public CatProfile getProfile() {
        return profile;
    }

    public void setProfile(CatProfile profile) {
        this.profile = profile;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatProfiledetail)) {
            return false;
        }
        CatProfiledetail other = (CatProfiledetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.aduana.entity.CatProfiledetail[ id=" + id + " ]";
    }

    public String getAssignCustom() {
        return assignCustom;
    }

    public void setAssignCustom(String assignCustom) {
        this.assignCustom = assignCustom;
    }

    public String getComplementaryCustom() {
        return complementaryCustom;
    }

    public void setComplementaryCustom(String complementaryCustom) {
        this.complementaryCustom = complementaryCustom;
    }

    public String getOtherPermission() {
        return otherPermission;
    }

    public void setOtherPermission(String otherPermission) {
        this.otherPermission = otherPermission;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }
    
    
}
