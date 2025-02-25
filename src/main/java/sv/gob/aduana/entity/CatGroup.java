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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "CAT_GROUP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatGroup.findAll", query = "SELECT c FROM CatGroup c")
    , @NamedQuery(name = "CatGroup.findById", query = "SELECT c FROM CatGroup c WHERE c.id = :id")
    , @NamedQuery(name = "CatGroup.findByTechname", query = "SELECT c FROM CatGroup c WHERE c.techname = :techname")
    , @NamedQuery(name = "CatGroup.findByFriendlyname", query = "SELECT c FROM CatGroup c WHERE c.friendlyname = :friendlyname")
    , @NamedQuery(name = "CatGroup.findByRequirecustom", query = "SELECT c FROM CatGroup c WHERE c.requirecustom = :requirecustom")
    , @NamedQuery(name = "CatGroup.findBySystemAndStatus", query = "SELECT c FROM CatGroup c WHERE c.system.id = :system  AND c.status = :status ")
    , @NamedQuery(name = "CatGroup.findByType", query = "SELECT c FROM CatGroup c WHERE c.type = :type")})
public class CatGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ID")
    private String id;
    @Size(max = 255)
    @Column(name = "TECHNAME")
    private String techname;
    @Size(max = 255)
    @Column(name = "FRIENDLYNAME")
    private String friendlyname;
    @Column(name = "REQUIRECUSTOM")
    private Integer requirecustom;
    @Size(max = 255)
    @Column(name = "TYPE")
    private String type;
    @Column(name = "STATUS")
    private Integer status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group1")
    private Collection<System> systemCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "system")
    private Collection<Systemhistory> systemhistoryCollection;
    @JoinColumn(name = "SYSTEM", referencedColumnName = "ID")
    @ManyToOne
    private CatSystem system;

    public CatGroup() {
    }

    public CatGroup(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTechname() {
        return techname;
    }

    public void setTechname(String techname) {
        this.techname = techname;
    }

    public String getFriendlyname() {
        return friendlyname;
    }

    public void setFriendlyname(String friendlyname) {
        this.friendlyname = friendlyname;
    }

    public Integer getRequirecustom() {
        return requirecustom;
    }

    public void setRequirecustom(Integer requirecustom) {
        this.requirecustom = requirecustom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    public Collection<System> getSystemCollection() {
        return systemCollection;
    }

    public void setSystemCollection(Collection<System> systemCollection) {
        this.systemCollection = systemCollection;
    }

    @XmlTransient
    public Collection<Systemhistory> getSystemhistoryCollection() {
        return systemhistoryCollection;
    }

    public void setSystemhistoryCollection(Collection<Systemhistory> systemhistoryCollection) {
        this.systemhistoryCollection = systemhistoryCollection;
    }

    public CatSystem getSystem() {
        return system;
    }

    public void setSystem(CatSystem system) {
        this.system = system;
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
        if (!(object instanceof CatGroup)) {
            return false;
        }
        CatGroup other = (CatGroup) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.aduana.entity.CatGroup[ id=" + id + " ]";
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    
}
