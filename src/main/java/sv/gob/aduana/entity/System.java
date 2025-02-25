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
@Table(name = "SYSTEM_DATA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "System.findAll", query = "SELECT s FROM System s")
    , @NamedQuery(name = "System.findById", query = "SELECT s FROM System s WHERE s.id = :id")
    , @NamedQuery(name = "System.findByRequestId", query = "SELECT s FROM System s WHERE s.request.id = :id")
    , @NamedQuery(name = "System.findByEnddate", query = "SELECT s FROM System s WHERE s.enddate = :enddate")
    , @NamedQuery(name = "System.findByTemporal", query = "SELECT s FROM System s WHERE s.temporal = :temporal")
    , @NamedQuery(name = "System.findByType", query = "SELECT s FROM System s WHERE s.type = :type")
    , @NamedQuery(name = "System.findExpired", query = "SELECT s FROM System s WHERE s.enddate  >= :startDate AND s.enddate <= :endDate")
    , @NamedQuery(name = "System.findStarting", query = "SELECT s FROM System s WHERE s.startDate  >= :startDate AND s.startDate <= :endDate")
    , @NamedQuery(name = "System.findByStatus", query = "SELECT s FROM System s WHERE s.status = :status")
    , @NamedQuery(name = "System.findByCustom", query = "SELECT s FROM System s WHERE s.custom = :custom")})
public class System implements Serializable {

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
    @Column(name = "TYPE")
    private String type;
    @Size(max = 255)
    @Column(name = "STATUS")
    private String status;
    @Size(max = 255)
    @Column(name = "CUSTOM")
    private String custom;
    @JoinColumn(name = "GROUP_SYSTEM", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private CatGroup group1;
    @JoinColumn(name = "REQUEST", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Request request;

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
    
   
    
    public System() {
    }

    public System(String id) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    public CatGroup getGroup1() {
        return group1;
    }

    public void setGroup1(CatGroup group1) {
        this.group1 = group1;
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
        if (!(object instanceof System)) {
            return false;
        }
        System other = (System) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.aduana.entity.System[ id=" + id + " ]";
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    
    
}
