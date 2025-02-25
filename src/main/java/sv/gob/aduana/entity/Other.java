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
@Table(name = "OTHER_DATA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Other.findAll", query = "SELECT o FROM Other o")
    , @NamedQuery(name = "Other.findById", query = "SELECT o FROM Other o WHERE o.id = :id")
    , @NamedQuery(name = "Other.findByRequestId", query = "SELECT o FROM Other o WHERE o.request.id = :id")
    , @NamedQuery(name = "Other.findByEnddate", query = "SELECT o FROM Other o WHERE o.enddate = :enddate")
    , @NamedQuery(name = "Other.findByHelpdesk", query = "SELECT o FROM Other o WHERE o.helpdesk = :helpdesk")
    , @NamedQuery(name = "Other.findExpired", query = "SELECT o FROM Other o WHERE o.enddate  >= :startDate AND o.enddate <= :endDate")
    , @NamedQuery(name = "Other.findStarting", query = "SELECT o FROM Other o WHERE o.startDate  >= :startDate AND o.startDate <= :endDate")
    , @NamedQuery(name = "Other.findByPlatform", query = "SELECT o FROM Other o WHERE o.platform = :platform")
    , @NamedQuery(name = "Other.findBySendto", query = "SELECT o FROM Other o WHERE o.sendto = :sendto")
    , @NamedQuery(name = "Other.findByStatus", query = "SELECT o FROM Other o WHERE o.status = :status")
    , @NamedQuery(name = "Other.findByTemporal", query = "SELECT o FROM Other o WHERE o.temporal = :temporal")
    , @NamedQuery(name = "Other.findByType", query = "SELECT o FROM Other o WHERE o.type = :type")})
public class Other implements Serializable {

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
    
    @Size(max = 255)
    @Column(name = "HELPDESK")
    private String helpdesk;
    @Size(max = 1024)
    @Column(name = "PLATFORM")
    private String platform;
    @Size(max = 255)
    @Column(name = "SENDTO")
    private String sendto;
    @Size(max = 255)
    @Column(name = "STATUS")
    private String status;
    @Column(name = "TEMPORAL")
    private Integer temporal;
    @Size(max = 255)
    @Column(name = "TYPE")
    private String type;
    @Size(max = 255)
    @Column(name = "DATA")
    private String data;
    @JoinColumn(name = "request", referencedColumnName = "ID")
    @ManyToOne
    private Request request;

    public Other() {
    }

    public Other(String id) {
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

    
  
    public String getHelpdesk() {
        return helpdesk;
    }

    public void setHelpdesk(String helpdesk) {
        this.helpdesk = helpdesk;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getSendto() {
        return sendto;
    }

    public void setSendto(String sendto) {
        this.sendto = sendto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
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
        if (!(object instanceof Other)) {
            return false;
        }
        Other other = (Other) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.aduana.entity.Other[ id=" + id + " ]";
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    
}
