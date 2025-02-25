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
@Table(name = "CAT_TYPEREQUEST")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatTyperequest.findAll", query = "SELECT c FROM CatTyperequest c")
    , @NamedQuery(name = "CatTyperequest.findById", query = "SELECT c FROM CatTyperequest c WHERE c.id = :id")
    , @NamedQuery(name = "CatTyperequest.findByName", query = "SELECT c FROM CatTyperequest c WHERE c.name = :name")
    , @NamedQuery(name = "CatTyperequest.findByStatus", query = "SELECT c FROM CatTyperequest c WHERE c.status = :status")})
public class CatTyperequest implements Serializable {

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
    @Column(name = "NAME")
    private String name;
    
    @Size(min = 1, max = 255)
    @Column(name = "CATEGORYID")
    private String categoryId;
    
    @Size(max = 255)
    @Column(name = "STATUS")
    private Integer status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "typerequest")
    private Collection<Request> requestCollection;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    
    public CatTyperequest() {
    }

    public CatTyperequest(String id) {
        this.id = id;
    }

    public CatTyperequest(String id, String name) {
        this.id = id;
        this.name = name;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    

    @XmlTransient
    public Collection<Request> getRequestCollection() {
        return requestCollection;
    }

    public void setRequestCollection(Collection<Request> requestCollection) {
        this.requestCollection = requestCollection;
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
        if (!(object instanceof CatTyperequest)) {
            return false;
        }
        CatTyperequest other = (CatTyperequest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.aduana.entity.CatTyperequest[ id=" + id + " ]";
    }
    
}
