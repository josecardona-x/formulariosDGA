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
@Table(name = "SYSTEMHISTORY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Systemhistory.findAll", query = "SELECT s FROM Systemhistory s")
    , @NamedQuery(name = "Systemhistory.findById", query = "SELECT s FROM Systemhistory s WHERE s.id = :id")
    , @NamedQuery(name = "Systemhistory.findByEnddate", query = "SELECT s FROM Systemhistory s WHERE s.enddate = :enddate")
    , @NamedQuery(name = "Systemhistory.findByTemporal", query = "SELECT s FROM Systemhistory s WHERE s.temporal = :temporal")
    , @NamedQuery(name = "Systemhistory.findByStatus", query = "SELECT s FROM Systemhistory s WHERE s.status = :status")
    , @NamedQuery(name = "Systemhistory.findByType", query = "SELECT s FROM Systemhistory s WHERE s.type = :type")
    , @NamedQuery(name = "Systemhistory.findByCustom", query = "SELECT s FROM Systemhistory s WHERE s.custom = :custom")})
public class Systemhistory implements Serializable {

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
    private Integer temporal;
    @Column(name = "STATUS")
    private Long status;
    @Column(name = "TYPE")
    private Long type;
    @Column(name = "CUSTOM")
    private Long custom;
    @JoinColumn(name = "SYSTEM_DATA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private CatGroup system;
    @JoinColumn(name = "PERSONHISTORY", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Personhistory personhistory;

    public Systemhistory() {
    }

    public Systemhistory(String id) {
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

    public Integer getTemporal() {
        return temporal;
    }

    public void setTemporal(Integer temporal) {
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

    public CatGroup getSystem() {
        return system;
    }

    public void setSystem(CatGroup system) {
        this.system = system;
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
        if (!(object instanceof Systemhistory)) {
            return false;
        }
        Systemhistory other = (Systemhistory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.aduana.entity.Systemhistory[ id=" + id + " ]";
    }
    
}
