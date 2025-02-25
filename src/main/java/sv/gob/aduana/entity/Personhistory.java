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
@Table(name = "PERSONHISTORY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personhistory.findAll", query = "SELECT p FROM Personhistory p")
    , @NamedQuery(name = "Personhistory.findById", query = "SELECT p FROM Personhistory p WHERE p.id = :id")
    , @NamedQuery(name = "Personhistory.findByLogin", query = "SELECT p FROM Personhistory p WHERE p.login = :login")})
public class Personhistory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name = "ID")
    @Size(max = 255)
    private String id;
    @Size(max = 255)
    @Column(name = "LOGIN")
    private String login;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personhistory")
    private Collection<Systemhistory> systemhistoryCollection;
    @OneToMany(mappedBy = "personhistory")
    private Collection<Profilehistory> profilehistoryCollection;

    public Personhistory() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

   
    

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @XmlTransient
    public Collection<Systemhistory> getSystemhistoryCollection() {
        return systemhistoryCollection;
    }

    public void setSystemhistoryCollection(Collection<Systemhistory> systemhistoryCollection) {
        this.systemhistoryCollection = systemhistoryCollection;
    }

    @XmlTransient
    public Collection<Profilehistory> getProfilehistoryCollection() {
        return profilehistoryCollection;
    }

    public void setProfilehistoryCollection(Collection<Profilehistory> profilehistoryCollection) {
        this.profilehistoryCollection = profilehistoryCollection;
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
        if (!(object instanceof Personhistory)) {
            return false;
        }
        Personhistory other = (Personhistory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.aduana.entity.Personhistory[ id=" + id + " ]";
    }
    
}
