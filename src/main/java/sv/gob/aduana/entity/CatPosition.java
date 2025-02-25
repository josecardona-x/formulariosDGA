/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.aduana.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
@Table(name = "CAT_POSITION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatPosition.findAll", query = "SELECT c FROM CatPosition c")
    , @NamedQuery(name = "CatPosition.findById", query = "SELECT c FROM CatPosition c WHERE c.id = :id")
    , @NamedQuery(name = "CatPosition.findByName", query = "SELECT c FROM CatPosition c WHERE c.name = :name")
    , @NamedQuery(name = "CatPosition.findByStatus", query = "SELECT c FROM CatPosition c WHERE c.status = :status")})
public class CatPosition implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Column(name = "STATUS")
    private int status;
    @OneToMany(mappedBy = "position")
    private Collection<Person> personCollection;
    @OneToMany(mappedBy = "position")
    private Collection<Applicant> applicantCollection;

    public CatPosition() {
    }

    public CatPosition(String id) {
        this.id = id;
    }

    public CatPosition(String id, String name, int status) {
        this.id = id;
        this.name = name;
        this.status = status;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<Person> getPersonCollection() {
        return personCollection;
    }

    public void setPersonCollection(Collection<Person> personCollection) {
        this.personCollection = personCollection;
    }

    @XmlTransient
    public Collection<Applicant> getApplicantCollection() {
        return applicantCollection;
    }

    public void setApplicantCollection(Collection<Applicant> applicantCollection) {
        this.applicantCollection = applicantCollection;
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
        if (!(object instanceof CatPosition)) {
            return false;
        }
        CatPosition other = (CatPosition) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.aduana.entity.CatPosition[ id=" + id + " ]";
    }
    
}
