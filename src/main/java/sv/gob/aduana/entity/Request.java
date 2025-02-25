/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.aduana.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Datum-Redsoft
 */
@Entity
@Table(name = "REQUEST")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Request.findAll", query = "SELECT r FROM Request r")
    , @NamedQuery(name = "Request.findById", query = "SELECT r FROM Request r WHERE r.id = :id")
    , @NamedQuery(name = "Request.findByFormId", query = "SELECT r FROM Request r WHERE r.form.id = :id")
    , @NamedQuery(name = "Request.findByApprovedate", query = "SELECT r FROM Request r WHERE r.approvedate = :approvedate")
    , @NamedQuery(name = "Request.findByCreateby", query = "SELECT r FROM Request r WHERE r.createby = :createby")
    , @NamedQuery(name = "Request.findByCreateon", query = "SELECT r FROM Request r WHERE r.createon = :createon")
    , @NamedQuery(name = "Request.findByState", query = "SELECT r FROM Request r WHERE r.state = :state")
    , @NamedQuery(name = "Request.findByPerson", query = "SELECT r.id FROM Request r WHERE r.person.id = :personid")
    , @NamedQuery(name = "Request.findByHashcode", query = "SELECT r FROM Request r WHERE r.hashcode = :hashcode")})
public class Request implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ID")
    private String id;
    @Column(name = "APPROVEDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date approvedate;
    @Size(max = 255)
    @Column(name = "CREATEBY")
    private String createby;
    @Column(name = "CREATEON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createon;
    @Size(max = 255)
    @Column(name = "STATE")
    private String state;
    @Size(max = 4000)
    @Column(name = "HASHCODE")
    private String hashcode;
    @Size(max = 4000)
    @Column(name = "CURRENTFLOWID")
    private String currentFlowId;
    
    @Column(name = "DELETEALLGROUP")
    private Integer deleteAllGroup;
    @Column(name = "MOVETODESACTIVE")
    private Integer moveToDesactive;
    @Size(max = 255)
    @Column(name = "helpDeskId")
    private String helpDeskId;
    
    
    @OneToMany(mappedBy = "request")
    private List<System> systemCollection;
    @OneToMany(mappedBy = "request")
    private List<Profile> profileCollection;
    @OneToMany(mappedBy = "request")
    private List<Other> otherCollection;
    @JoinColumn(name = "TYPEREQUEST", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private CatTyperequest typerequest;
    @JoinColumn(name = "form", referencedColumnName = "ID")
    @ManyToOne
    private Formu form;
    @JoinColumn(name = "PERSON", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Person person;
    @OneToMany(mappedBy = "request")
    private List<Requestflow> requestflowCollection;

     
    @Size(max = 255)
    @Column(name = "PASSWORD")
    private String password;
   
    
    
    public Request() {
    }

    public Request(String id) {
        this.id = id;
    }

    public String getHelpDeskId() {
        return helpDeskId;
    }

    public void setHelpDeskId(String helpDeskId) {
        this.helpDeskId = helpDeskId;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCurrentFlowId() {
        return currentFlowId;
    }

    public void setCurrentFlowId(String currentFlowId) {
        this.currentFlowId = currentFlowId;
    }

    
    public Date getApprovedate() {
        return approvedate;
    }

    public void setApprovedate(Date approvedate) {
        this.approvedate = approvedate;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby;
    }

    public Date getCreateon() {
        return createon;
    }

    public void setCreateon(Date createon) {
        this.createon = createon;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getHashcode() {
        return hashcode;
    }

    public void setHashcode(String hashcode) {
        this.hashcode = hashcode;
    }

    @XmlTransient
    public List<System> getSystemCollection() {
        return systemCollection;
    }

    public void setSystemCollection(List<System> systemCollection) {
        this.systemCollection = systemCollection;
    }

    @XmlTransient
    public List<Profile> getProfileCollection() {
        return profileCollection;
    }

    public void setProfileCollection(List<Profile> profileCollection) {
        this.profileCollection = profileCollection;
    }

    @XmlTransient
    public List<Other> getOtherCollection() {
        return otherCollection;
    }

    public void setOtherCollection(List<Other> otherCollection) {
        this.otherCollection = otherCollection;
    }

    public CatTyperequest getTyperequest() {
        return typerequest;
    }

    public void setTyperequest(CatTyperequest typerequest) {
        this.typerequest = typerequest;
    }

    public Formu getForm() {
        return form;
    }

    public void setForm(Formu form) {
        this.form = form;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @XmlTransient
    public List<Requestflow> getRequestflowCollection() {
        return requestflowCollection;
    }

    public void setRequestflowCollection(List<Requestflow> requestflowCollection) {
        this.requestflowCollection = requestflowCollection;
    }

    public Integer getDeleteAllGroup() {
        return deleteAllGroup;
    }

    public void setDeleteAllGroup(Integer deleteAllGroup) {
        this.deleteAllGroup = deleteAllGroup;
    }

    public Integer getMoveToDesactive() {
        return moveToDesactive;
    }

    public void setMoveToDesactive(Integer moveToDesactive) {
        this.moveToDesactive = moveToDesactive;
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
        if (!(object instanceof Request)) {
            return false;
        }
        Request other = (Request) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Request{" + "id=" + id + ", approvedate=" + approvedate + ", createby=" + createby + ", createon=" + createon + ", state=" + state + ", hashcode=" + hashcode + ", currentFlowId=" + currentFlowId + ", systemCollection=" + systemCollection + ", profileCollection=" + profileCollection + ", otherCollection=" + otherCollection + ", typerequest=" + typerequest + ", form=" + form.getId() + ", person=" + person + ", requestflowCollection=" + requestflowCollection + '}';
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    
}
