/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.aduana.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Datum-Redsoft
 */
@Entity
@Table(name = "CAT_APPSIDENTITY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatAppsidentity.findAll", query = "SELECT c FROM CatAppsidentity c")
    , @NamedQuery(name = "CatAppsidentity.findById", query = "SELECT c FROM CatAppsidentity c WHERE c.id = :id")
    , @NamedQuery(name = "CatAppsidentity.findByName", query = "SELECT c FROM CatAppsidentity c WHERE c.name = :name")
    , @NamedQuery(name = "CatAppsidentity.findByCodename", query = "SELECT c FROM CatAppsidentity c WHERE c.codename = :codename")
    , @NamedQuery(name = "CatAppsidentity.findByItresourcecode", query = "SELECT c FROM CatAppsidentity c WHERE c.itresourcecode = :itresourcecode")
    , @NamedQuery(name = "CatAppsidentity.findByRevokecode", query = "SELECT c FROM CatAppsidentity c WHERE c.revokecode = :revokecode")
    , @NamedQuery(name = "CatAppsidentity.findByEntitlement", query = "SELECT c FROM CatAppsidentity c WHERE c.entitlement = :entitlement")
    , @NamedQuery(name = "CatAppsidentity.findByDisconnect", query = "SELECT c FROM CatAppsidentity c WHERE c.disconnect = :disconnect")
    , @NamedQuery(name = "CatAppsidentity.findByGrouptable", query = "SELECT c FROM CatAppsidentity c WHERE c.grouptable = :grouptable")})
public class CatAppsidentity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ID")
    private String id;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @Size(max = 255)
    @Column(name = "CODENAME")
    private String codename;
    @Column(name = "ITRESOURCECODE")
    private Long itresourcecode;
    @Column(name = "REVOKECODE")
    private Long revokecode;
    @Column(name = "ENTITLEMENT")
    private Integer entitlement;
    @Column(name = "DISCONNECT")
    private Integer disconnect;
    @Column(name = "GROUPTABLE")
    private String grouptable;
    @Column(name = "APPLICATIONKEY")
    private Long applicationKey;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "appsidentity")
    private Collection<CatSystem> catSystemCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "appsidentity")
    private Collection<CatProfiledetail> catProfiledetailCollection;

    public CatAppsidentity() {
    }

    public CatAppsidentity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodename() {
        return codename;
    }

    public void setCodename(String codename) {
        this.codename = codename;
    }

    public Long getItresourcecode() {
        return itresourcecode;
    }

    public void setItresourcecode(Long itresourcecode) {
        this.itresourcecode = itresourcecode;
    }

    public Long getRevokecode() {
        return revokecode;
    }

    public void setRevokecode(Long revokecode) {
        this.revokecode = revokecode;
    }

    public Integer getEntitlement() {
        return entitlement;
    }

    public void setEntitlement(Integer entitlement) {
        this.entitlement = entitlement;
    }

    public Integer getDisconnect() {
        return disconnect;
    }

    public void setDisconnect(Integer disconnect) {
        this.disconnect = disconnect;
    }

    public String getGrouptable() {
        return grouptable;
    }

    public void setGrouptable(String grouptable) {
        this.grouptable = grouptable;
    }

  
    @XmlTransient
    public Collection<CatSystem> getCatSystemCollection() {
        return catSystemCollection;
    }

    public void setCatSystemCollection(Collection<CatSystem> catSystemCollection) {
        this.catSystemCollection = catSystemCollection;
    }

    @XmlTransient
    public Collection<CatProfiledetail> getCatProfiledetailCollection() {
        return catProfiledetailCollection;
    }

    public void setCatProfiledetailCollection(Collection<CatProfiledetail> catProfiledetailCollection) {
        this.catProfiledetailCollection = catProfiledetailCollection;
    }

    public Long getApplicationKey() {
        return applicationKey;
    }

    public void setApplicationKey(Long applicationKey) {
        this.applicationKey = applicationKey;
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
        if (!(object instanceof CatAppsidentity)) {
            return false;
        }
        CatAppsidentity other = (CatAppsidentity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.aduana.entity.CatAppsidentity[ id=" + id + " ]";
    }
    
}
