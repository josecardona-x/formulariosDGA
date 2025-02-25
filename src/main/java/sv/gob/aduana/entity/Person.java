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
@Table(name = "PERSON")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p")
    , @NamedQuery(name = "Person.findById", query = "SELECT p FROM Person p WHERE p.id = :id")
    , @NamedQuery(name = "Person.findByApproveafpa", query = "SELECT p FROM Person p WHERE p.approveafpa = :approveafpa")
    , @NamedQuery(name = "Person.findByCodedeclarant", query = "SELECT p FROM Person p WHERE p.codedeclarant = :codedeclarant")
    , @NamedQuery(name = "Person.findByEnddate", query = "SELECT p FROM Person p WHERE p.enddate = :enddate")
    , @NamedQuery(name = "Person.findByFullname", query = "SELECT p FROM Person p WHERE p.fullname = :fullname")
    , @NamedQuery(name = "Person.findByIsafpa", query = "SELECT p FROM Person p WHERE p.isafpa = :isafpa")
    , @NamedQuery(name = "Person.findByLastname", query = "SELECT p FROM Person p WHERE p.lastname = :lastname")
    , @NamedQuery(name = "Person.findByDocument", query = "SELECT p FROM Person p WHERE p.document = :document")
    , @NamedQuery(name = "Person.findByDocumentSiduneaWorld", query = "SELECT p FROM Person p WHERE p.document = :document and p.userSiduneaWorld is not null")
    , @NamedQuery(name = "Person.findByDocumentSiduneaPlus", query = "SELECT p FROM Person p WHERE p.document = :document and p.userSiduneaPlus is not null")
    , @NamedQuery(name = "Person.findByDocumentDuca", query = "SELECT p FROM Person p WHERE p.document = :document and p.userCodDuca is not null")
    , @NamedQuery(name = "Person.findByDocumentVPN", query = "SELECT p FROM Person p WHERE p.document = :document and p.userCodVPN is not null")
    , @NamedQuery(name = "Person.findByMail", query = "SELECT p FROM Person p WHERE p.mail = :mail")
    , @NamedQuery(name = "Person.findBySurname", query = "SELECT p FROM Person p WHERE p.surname = :surname")
    , @NamedQuery(name = "Person.findExpired", query = "SELECT p FROM Person p WHERE p.enddate  >= :startDate AND p.enddate <= :endDate ")
    , @NamedQuery(name = "Person.findStarting", query = "SELECT p FROM Person p WHERE p.startdate  >= :startDate AND p.enddate <= :endDate ")
    , @NamedQuery(name = "Person.findByStartdate", query = "SELECT p FROM Person p WHERE p.startdate = :startdate")
    , @NamedQuery(name = "Person.findByLogin", query = "SELECT p FROM Person p WHERE p.login = :login")})
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ID")
    private String id;
    @Size(max = 255)
    @Column(name = "APPROVEAFPA")
    private String approveafpa;
    @Size(max = 255)
    @Column(name = "CODEDECLARANT")
    private String codedeclarant;
    @Column(name = "ENDDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date enddate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "FULLNAME")
    private String fullname;
    @Column(name = "ISAFPA")
    private Integer isafpa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "LASTNAME")
    private String lastname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "DOCUMENT")
    private String document;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "MAIL")
    private String mail;
    @Size(max = 255)
    @Column(name = "RESOLUTION")
    private String resolution;
    
    
    @Size(max = 255)
    @Column(name = "userSiduneaWorld")
    private String userSiduneaWorld;
    @Size(max = 255)
    @Column(name = "userSiduneaPlus")
    private String userSiduneaPlus;
    @Size(max = 255)
    @Column(name = "userCodDuca")
    private String userCodDuca;
    @Size(max = 255)
    @Column(name = "userCodVPN")
    private String userCodVPN;
    
    @Size(max = 255)
    @Column(name = "SURNAME")
    private String surname;
    @Basic(optional = false)
    @Column(name = "STARTDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startdate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "LOGIN")
    private String login;
    @JoinColumn(name = "ATTRIBUTE", referencedColumnName = "ID")
    @ManyToOne
    private CatAttribute attribute;
    @JoinColumn(name = "LEVELFOUR", referencedColumnName = "ID")
    @ManyToOne
    private CatLvlfour levelfour;
    @JoinColumn(name = "LEVELONE", referencedColumnName = "ID")
    @ManyToOne
    private CatLvlone levelone;
    @JoinColumn(name = "LEVELTHREE", referencedColumnName = "ID")
    @ManyToOne
    private CatLvlthree levelthree;
    @JoinColumn(name = "LEVELTWO", referencedColumnName = "ID")
    @ManyToOne
    private CatLvltwo leveltwo;
    @JoinColumn(name = "POSITION", referencedColumnName = "ID")
    @ManyToOne
    private CatPosition position;
    @JoinColumn(name = "CAT_TYPEAFPAID", referencedColumnName = "ID")
    @ManyToOne
    private CatTypeafpa catTypeafpaid;

    //Edson Artiga 100121 Nuevos campos de persona
    @Size(max = 255)
    @Column(name = "USERTYPE")
    private String userType;
    
    @Size(max = 255)
    @Column(name = "PHONENUMBER")
    private String phoneNumber;
    
    @Size(max = 255)
    @Column(name = "ALTERNATIVEMAIL")
    private String alternativeMail;
    
    @Size(max = 255)
    @Column(name = "MOBILE")
    private String mobile;
    
    @Size(max = 255)
    @Column(name = "ORGANIZATIONNAME")
    private String organizationName;
    
    @Size(max = 255)
    @Column(name = "STATEUSER")
    private String state;
    
    
    
    
    public Person() {
    }

    public Person(String id) {
        this.id = id;
    }

    public Person(String id, String fullname, String lastname, String document, String mail, Date startdate, String login) {
        this.id = id;
        this.fullname = fullname;
        this.lastname = lastname;
        this.document = document;
        this.mail = mail;
        this.startdate = startdate;
        this.login = login;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApproveafpa() {
        return approveafpa;
    }

    public void setApproveafpa(String approveafpa) {
        this.approveafpa = approveafpa;
    }

    public String getCodedeclarant() {
        return codedeclarant;
    }

    public void setCodedeclarant(String codedeclarant) {
        this.codedeclarant = codedeclarant;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Integer getIsafpa() {
        return isafpa;
    }

    public void setIsafpa(Integer isafpa) {
        this.isafpa = isafpa;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public CatAttribute getAttribute() {
        return attribute;
    }

    public void setAttribute(CatAttribute attribute) {
        this.attribute = attribute;
    }

    public CatLvlfour getLevelfour() {
        return levelfour;
    }

    public void setLevelfour(CatLvlfour levelfour) {
        this.levelfour = levelfour;
    }

    public CatLvlone getLevelone() {
        return levelone;
    }

    public void setLevelone(CatLvlone levelone) {
        this.levelone = levelone;
    }

    public CatLvlthree getLevelthree() {
        return levelthree;
    }

    public void setLevelthree(CatLvlthree levelthree) {
        this.levelthree = levelthree;
    }

    public CatLvltwo getLeveltwo() {
        return leveltwo;
    }

    public void setLeveltwo(CatLvltwo leveltwo) {
        this.leveltwo = leveltwo;
    }

    public CatPosition getPosition() {
        return position;
    }

    public void setPosition(CatPosition position) {
        this.position = position;
    }

    public CatTypeafpa getCatTypeafpaid() {
        return catTypeafpaid;
    }

    public void setCatTypeafpaid(CatTypeafpa catTypeafpaid) {
        this.catTypeafpaid = catTypeafpaid;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAlternativeMail() {
        return alternativeMail;
    }

    public void setAlternativeMail(String alternativeMail) {
        this.alternativeMail = alternativeMail;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
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
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", approveafpa=" + approveafpa + ", codedeclarant=" + codedeclarant + ", enddate=" + enddate + ", fullname=" + fullname + ", isafpa=" + isafpa + ", lastname=" + lastname + ", document=" + document + ", mail=" + mail + ", resolution=" + resolution + ", surname=" + surname + ", startdate=" + startdate + ", login=" + login + ", attribute=" + attribute + ", levelfour=" + levelfour + ", levelone=" + levelone + ", levelthree=" + levelthree + ", leveltwo=" + leveltwo + ", position=" + position + ", catTypeafpaid=" + catTypeafpaid + ", userType=" + userType + ", phoneNumber=" + phoneNumber + ", alternativeMail=" + alternativeMail + ", mobile=" + mobile + ", organizationName=" + organizationName + '}';
    }

    public String getUserSiduneaWorld() {
        return userSiduneaWorld;
    }

    public void setUserSiduneaWorld(String userSiduneaWorld) {
        this.userSiduneaWorld = userSiduneaWorld;
    }

    public String getUserSiduneaPlus() {
        return userSiduneaPlus;
    }

    public void setUserSiduneaPlus(String userSiduneaPlus) {
        this.userSiduneaPlus = userSiduneaPlus;
    }

    public String getUserCodDuca() {
        return userCodDuca;
    }

    public void setUserCodDuca(String userCodDuca) {
        this.userCodDuca = userCodDuca;
    }

    public String getUserCodVPN() {
        return userCodVPN;
    }

    public void setUserCodVPN(String userCodVPN) {
        this.userCodVPN = userCodVPN;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    
    
    
    
}
