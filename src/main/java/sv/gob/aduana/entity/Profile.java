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
@Table(name = "PROFILE_DATA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profile.findAll", query = "SELECT p FROM Profile p")
    , @NamedQuery(name = "Profile.findById", query = "SELECT p FROM Profile p WHERE p.id = :id")
    , @NamedQuery(name = "Profile.findByRequestId", query = "SELECT p FROM Profile p WHERE p.request.id = :id")
    , @NamedQuery(name = "Profile.findByEnddate", query = "SELECT p FROM Profile p WHERE p.enddate = :enddate")
    , @NamedQuery(name = "Profile.findByTemporal", query = "SELECT p FROM Profile p WHERE p.temporal = :temporal")
    , @NamedQuery(name = "Profile.findExpired", query = "SELECT p FROM Profile p WHERE p.enddate  >= :startDate AND p.enddate <= :endDate")
    , @NamedQuery(name = "Profile.findStarting", query = "SELECT p FROM Profile p WHERE p.startDate  >= :startDate AND p.startDate <= :endDate")
    , @NamedQuery(name = "Profile.findByStatus", query = "SELECT p FROM Profile p WHERE p.status = :status")
    , @NamedQuery(name = "Profile.findByType", query = "SELECT p FROM Profile p WHERE p.type = :type")
    , @NamedQuery(name = "Profile.findByCustom", query = "SELECT p FROM Profile p WHERE p.custom = :custom")})
public class Profile implements Serializable {

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
    
    @Column(name = "STARTDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    
    @Column(name = "TEMPORAL")
    private Integer temporal;
    @Size(max = 255)
    @Column(name = "STATUS")
    private String status;
    @Size(max = 255)
    @Column(name = "TYPE")
    private String type;
    @Size(max = 255)
    @Column(name = "CUSTOM")
    private String custom;
    
    @JoinColumn(name = "PROFILE", referencedColumnName = "ID")
    @ManyToOne
    private CatProfile profile;
    
    @JoinColumn(name = "REQUEST", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Request request;

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
    
    
    
    
    public Profile() {
    }

    public Profile(String id) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
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
        if (!(object instanceof Profile)) {
            return false;
        }
        Profile other = (Profile) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.aduana.entity.Profile[ id=" + id + " ]";
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    
    
}
