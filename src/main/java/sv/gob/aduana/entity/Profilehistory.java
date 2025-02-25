/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.aduana.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Datum-Redsoft
 */
@Entity
@Table(name = "PROFILEHISTORY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profilehistory.findAll", query = "SELECT p FROM Profilehistory p")
    , @NamedQuery(name = "Profilehistory.findById", query = "SELECT p FROM Profilehistory p WHERE p.id = :id")
    , @NamedQuery(name = "Profilehistory.findByEnddate", query = "SELECT p FROM Profilehistory p WHERE p.enddate = :enddate")
    , @NamedQuery(name = "Profilehistory.findByTemporal", query = "SELECT p FROM Profilehistory p WHERE p.temporal = :temporal")
    , @NamedQuery(name = "Profilehistory.findByStatus", query = "SELECT p FROM Profilehistory p WHERE p.status = :status")
    , @NamedQuery(name = "Profilehistory.findByType", query = "SELECT p FROM Profilehistory p WHERE p.type = :type")
    , @NamedQuery(name = "Profilehistory.findByCustom", query = "SELECT p FROM Profilehistory p WHERE p.custom = :custom")})
public class Profilehistory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ID")
    private String id;
    @Column(name = "ENDDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date enddate;
    @Column(name = "TEMPORAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date temporal;
    @Column(name = "STATUS")
    private Long status;
    @Column(name = "TYPE")
    private Long type;
    @Column(name = "CUSTOM")
    private Long custom;
    @JoinColumn(name = "PROFILE_DATA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private CatProfile profile;
    @JoinColumn(name = "PERSONHISTORY", referencedColumnName = "ID")
    @ManyToOne
    private Personhistory personhistory;

    public Profilehistory() {
    }

    public Profilehistory(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Date getTemporal() {
        return temporal;
    }

    public void setTemporal(Date temporal) {
        this.temporal = temporal;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getCustom() {
        return custom;
    }

    public void setCustom(Long custom) {
        this.custom = custom;
    }

    public CatProfile getProfile() {
        return profile;
    }

    public void setProfile(CatProfile profile) {
        this.profile = profile;
    }

    public Personhistory getPersonhistory() {
        return personhistory;
    }

    public void setPersonhistory(Personhistory personhistory) {
        this.personhistory = personhistory;
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
        if (!(object instanceof Profilehistory)) {
            return false;
        }
        Profilehistory other = (Profilehistory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.aduana.entity.Profilehistory[ id=" + id + " ]";
    }
    
}
