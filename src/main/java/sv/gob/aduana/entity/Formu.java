/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.aduana.entity;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Clob;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import static javax.persistence.FetchType.LAZY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "FORMU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Formu.findAll", query = "SELECT f FROM Formu f")
    , @NamedQuery(name = "Formu.findById", query = "SELECT f FROM Formu f WHERE f.id = :id")
    , @NamedQuery(name = "Formu.findByCreatedon", query = "SELECT f FROM Formu f WHERE f.createdon = :createdon")
    , @NamedQuery(name = "Formu.findByCreateby", query = "SELECT f FROM Formu f WHERE f.createby = :createby")
    , @NamedQuery(name = "Formu.findByModifiedby", query = "SELECT f FROM Formu f WHERE f.modifiedby = :modifiedby")
    , @NamedQuery(name = "Formu.findByModifiedon", query = "SELECT f FROM Formu f WHERE f.modifiedon = :modifiedon")
    , @NamedQuery(name = "Formu.findByClosed", query = "SELECT f FROM Formu f WHERE f.closed = :closed")
    , @NamedQuery(name = "Formu.findByStep", query = "SELECT f FROM Formu f WHERE f.step = :step")
    , @NamedQuery(name = "Formu.findByRoleStep", query = "SELECT f FROM Formu f WHERE f.roleStep = :roleStep")
    , @NamedQuery(name = "Formu.findByApplicantviewer", query = "SELECT f FROM Formu f WHERE f.applicantviewer = :applicantviewer")
    , @NamedQuery(name = "Formu.findByStepisrole", query = "SELECT f FROM Formu f WHERE f.stepisrole = :stepisrole")
    , @NamedQuery(name = "Formu.findByStatus", query = "SELECT f FROM Formu f WHERE f.status = :status")
    , @NamedQuery(name = "Formu.findByUserStatusAndClose", query = "SELECT f FROM Formu f WHERE f.status = :status AND (f.createby= :user OR  f.applicantviewer = :applicantviewer) AND f.closed=:closed  ")
    , @NamedQuery(name = "Formu.findByStatusAndClosed", query = "SELECT f FROM Formu f WHERE f.status = :status AND ( f.roleStep = :role ) AND f.closed=:closed  ")
    , @NamedQuery(name = "Formu.findByCreatedname", query = "SELECT f FROM Formu f WHERE f.createdname = :createdname")})
public class Formu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ID")
    private String id;
    @Column(name = "CREATEDON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdon;
    @Size(max = 255)
    @Column(name = "CREATEBY")
    private String createby;
    @Size(max = 255)
    @Column(name = "MODIFIEDBY")
    private String modifiedby;
    @Column(name = "MODIFIEDON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedon;
    @Column(name = "CLOSED")
    private Integer closed;
    @Size(max = 255)
    @Column(name = "STEP")
    private String step;
    @Size(max = 255)
    @Column(name = "APPLICANTVIEWER")
    private String applicantviewer;
    @Column(name = "STEPISROLE")
    private Integer stepisrole;
    @Lob 
    @Column(name = "FILE1")
    private String file1;
    @Lob 
    @Column(name = "FILE2")
    private String file2;
    @Lob 
    @Column(name = "FILE3")
    private String file3;
    @Lob 
    @Column(name = "FILE4")
    private String file4;
    @Lob 
    @Column(name = "FILE5")
    private String file5;
    @Lob 
    @Column(name = "FILE6")
    private String file6;
    @Size(max = 255)
    @Column(name = "STATUS")
    private String status;
    @Size(max = 255)
    @Column(name = "CREATEDNAME")
    private String createdname;
    @Size(max = 4000)
    @Column(name = "COMM")
    private String comment;
    @Size(max = 255)
    @Column(name = "FORMTYPE")
    private String formType;
    @Size(max = 255)
    @Column(name = "ROLESTEP")
    private String roleStep;
    @JoinColumn(name = "APPLICANT", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Applicant applicant;
    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL)
    private List<Request> requestCollection;
    @Size(max = 255)
    @Column(name = "HASHCODE")
    private String hashCode;

    public Formu() {
    }

    public Formu(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }
    
    

    public Date getCreatedon() {
        return createdon;
    }

    public void setCreatedon(Date createdon) {
        this.createdon = createdon;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby;
    }

    public String getModifiedby() {
        return modifiedby;
    }

    public void setModifiedby(String modifiedby) {
        this.modifiedby = modifiedby;
    }

    public Date getModifiedon() {
        return modifiedon;
    }

    public void setModifiedon(Date modifiedon) {
        this.modifiedon = modifiedon;
    }

    public Integer getClosed() {
        return closed;
    }

    public void setClosed(Integer closed) {
        this.closed = closed;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getRoleStep() {
        return roleStep;
    }

    public void setRoleStep(String roleStep) {
        this.roleStep = roleStep;
    }
    
    

    public String getApplicantviewer() {
        return applicantviewer;
    }

    public void setApplicantviewer(String applicantviewer) {
        this.applicantviewer = applicantviewer;
    }

    public Integer getStepisrole() {
        return stepisrole;
    }

    public void setStepisrole(Integer stepisrole) {
        this.stepisrole = stepisrole;
    }

   
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedname() {
        return createdname;
    }

    public void setCreatedname(String createdname) {
        this.createdname = createdname;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    @XmlTransient
    public List<Request> getRequestCollection() {
        return requestCollection;
    } 

    public void setRequestCollection(List<Request> requestCollection) {
        this.requestCollection = requestCollection;
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
        if (!(object instanceof Formu)) {
            return false;
        }
        Formu other = (Formu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Formu{" + "id=" + id + ", createdon=" + createdon + ", createby=" + createby + ", modifiedby=" + modifiedby + ", modifiedon=" + modifiedon + ", closed=" + closed + ", step=" + step + ", applicantviewer=" + applicantviewer + ", stepisrole=" + stepisrole + ", file1=" + file1 + ", file2=" + file2 + ", file3=" + file3 + ", file4=" + file4 + ", file5=" + file5 + ", file6=" + file6 + ", status=" + status + ", createdname=" + createdname + ", comment=" + comment + ", formType=" + formType + ", applicant=" + applicant + ", requestCollection=" + requestCollection + ", hashCode=" + hashCode + '}';
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFile1() {
        return file1;
    }

    public void setFile1(String file1) {
        this.file1 = file1;
    }

    public String getFile2() {
        return file2;
    }

    public void setFile2(String file2) {
        this.file2 = file2;
    }

    public String getFile3() {
        return file3;
    }

    public void setFile3(String file3) {
        this.file3 = file3;
    }

    public String getFile4() {
        return file4;
    }

    public void setFile4(String file4) {
        this.file4 = file4;
    }

    public String getFile5() {
        return file5;
    }

    public void setFile5(String file5) {
        this.file5 = file5;
    }

    public String getFile6() {
        return file6;
    }

    public void setFile6(String file6) {
        this.file6 = file6;
    }

   
   
    
    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

    
    
}
