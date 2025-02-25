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
@Table(name = "FLOW")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Flow.findAll", query = "SELECT f FROM Flow f")
    , @NamedQuery(name = "Flow.findById", query = "SELECT f FROM Flow f WHERE f.id = :id")
    , @NamedQuery(name = "Flow.findByProcess", query = "SELECT f FROM Flow f WHERE f.process = :process")
    , @NamedQuery(name = "Flow.findByStep", query = "SELECT f FROM Flow f WHERE f.step = :step")
    , @NamedQuery(name = "Flow.findByRole", query = "SELECT f FROM Flow f WHERE f.role = :role")
    , @NamedQuery(name = "Flow.findBySendtorole", query = "SELECT f FROM Flow f WHERE f.sendtorole = :sendtorole")
    , @NamedQuery(name = "Flow.findByTo", query = "SELECT f FROM Flow f WHERE f.to = :to")
    , @NamedQuery(name = "Flow.findBySubject", query = "SELECT f FROM Flow f WHERE f.subject = :subject")
    , @NamedQuery(name = "Flow.findByMessage", query = "SELECT f FROM Flow f WHERE f.message = :message")
    , @NamedQuery(name = "Flow.findBySendstatusmail", query = "SELECT f FROM Flow f WHERE f.sendstatusmail = :sendstatusmail")})
public class Flow implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ID")
    private String id;
    @Size(max = 255)
    @Column(name = "PROCESS")
    private String process;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STEP")
    private long step;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ROLE")
    private String role;
    @Column(name = "SENDTOROLE")
    private Integer sendtorole;
    @Size(max = 255)
    @Column(name = "TO_MAIL")
    private String to;
    @Size(max = 255)
    @Column(name = "SUBJECT_MAIL")
    private String subject;
    @Size(max = 4000)
    @Column(name = "MESSAGE")
    private String message;
    @Column(name = "SENDSTATUSMAIL")
    private Integer sendstatusmail;
    @Column(name = "approveByMail")
    private Integer approveByMail;
    @OneToMany(mappedBy = "flow")
    private Collection<Requestflow> requestflowCollection;
    @Size(max = 255)
    @Column(name = "NAMESTEP")
    private String nameStep;

    public Flow() {
    }

    public Flow(String id) {
        this.id = id;
    }

    public Flow(String id, long step, String role) {
        this.id = id;
        this.step = step;
        this.role = role;
    }

    public String getNameStep() {
        return nameStep;
    }

    public void setNameStep(String nameStep) {
        this.nameStep = nameStep;
    }

    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public long getStep() {
        return step;
    }

    public void setStep(long step) {
        this.step = step;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getSendtorole() {
        return sendtorole;
    }

    public void setSendtorole(Integer sendtorole) {
        this.sendtorole = sendtorole;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Integer getApproveByMail() {
        return approveByMail;
    }

    public void setApproveByMail(Integer approveByMail) {
        this.approveByMail = approveByMail;
    }

  
    
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getSendstatusmail() {
        return sendstatusmail;
    }

    public void setSendstatusmail(Integer sendstatusmail) {
        this.sendstatusmail = sendstatusmail;
    }

    @XmlTransient
    public Collection<Requestflow> getRequestflowCollection() {
        return requestflowCollection;
    }

    public void setRequestflowCollection(Collection<Requestflow> requestflowCollection) {
        this.requestflowCollection = requestflowCollection;
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
        if (!(object instanceof Flow)) {
            return false;
        }
        Flow other = (Flow) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.aduana.entity.Flow[ id=" + id + " ]";
    }
    
}
