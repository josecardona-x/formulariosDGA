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
@Table(name = "REQUESTFLOW")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Requestflow.findAll", query = "SELECT r FROM Requestflow r")
    , @NamedQuery(name = "Requestflow.findById", query = "SELECT r FROM Requestflow r WHERE r.id = :id")
    , @NamedQuery(name = "Requestflow.findByRequestId", query = "SELECT r FROM Requestflow r WHERE r.request.id = :id")
    , @NamedQuery(name = "Requestflow.findByResult", query = "SELECT r FROM Requestflow r WHERE r.result = :result")
    , @NamedQuery(name = "Requestflow.findRequestById", query = "SELECT r.request FROM Requestflow r WHERE r.id = :id")
    , @NamedQuery(name = "Requestflow.findByUser", query = "SELECT r FROM Requestflow r WHERE r.user = :user")
    , @NamedQuery(name = "Requestflow.findByStartdate", query = "SELECT r FROM Requestflow r WHERE r.startdate = :startdate")
    , @NamedQuery(name = "Requestflow.findByEnddate", query = "SELECT r FROM Requestflow r WHERE r.enddate = :enddate")
    , @NamedQuery(name = "Requestflow.findByStatus", query = "SELECT r FROM Requestflow r WHERE r.status = :status")
    , @NamedQuery(name = "Requestflow.findMinStep", query = "SELECT MIN(r.flow.step) FROM Requestflow r WHERE r.request.id = :id and r.status = 'NOTSTARTED'")
    , @NamedQuery(name = "Requestflow.findLastStep", query = "SELECT r FROM Requestflow r WHERE r.request.id = :id and r.flow.step = :step")
    , @NamedQuery(name = "Requestflow.findByDeveloperemail", query = "SELECT r FROM Requestflow r WHERE r.developeremail = :developeremail")
    , @NamedQuery(name = "Requestflow.findByRequesteremail", query = "SELECT r FROM Requestflow r WHERE r.requesteremail = :requesteremail")})
public class Requestflow implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ID")
    private String id;
    @Size(max = 255)
    @Column(name = "RESULT_ACTION")
    private String result;
    @Size(max = 255)
    @Column(name = "USR_RESULT")
    private String user;
    @Column(name = "STARTDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startdate;
    @Column(name = "ENDDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date enddate;
    @Size(max = 255)
    @Column(name = "STATUS")
    private String status;
    @Size(max = 255)
    @Column(name = "DEVELOPEREMAIL")
    private String developeremail;
    @Size(max = 4000)
    @Column(name = "COMM")
    private String comment;
    @Size(max = 255)
    @Column(name = "REQUESTEREMAIL")
    private String requesteremail;
    @JoinColumn(name = "FLOW", referencedColumnName = "ID")
    @ManyToOne
    private Flow flow;
    @JoinColumn(name = "REQUEST", referencedColumnName = "ID")
    @ManyToOne
    private Request request;

    public Requestflow() {
    }

    public Requestflow(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeveloperemail() {
        return developeremail;
    }

    public void setDeveloperemail(String developeremail) {
        this.developeremail = developeremail;
    }

    public String getRequesteremail() {
        return requesteremail;
    }

    public void setRequesteremail(String requesteremail) {
        this.requesteremail = requesteremail;
    }

    public Flow getFlow() {
        return flow;
    }

    public void setFlow(Flow flow) {
        this.flow = flow;
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
        if (!(object instanceof Requestflow)) {
            return false;
        }
        Requestflow other = (Requestflow) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Requestflow{" + "id=" + id + ", result=" + result + ", user=" + user + ", startdate=" + startdate + ", enddate=" + enddate + ", status=" + status + ", developeremail=" + developeremail + ", comment=" + comment + ", requesteremail=" + requesteremail + ", flow=" + flow + ", request=" + request.getId() + '}';
    }

    

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    
}
