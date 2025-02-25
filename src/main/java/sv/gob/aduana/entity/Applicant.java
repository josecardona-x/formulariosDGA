/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.aduana.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "APPLICANT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Applicant.findAll", query = "SELECT a FROM Applicant a")
    , @NamedQuery(name = "Applicant.findById", query = "SELECT a FROM Applicant a WHERE a.id = :id")
    , @NamedQuery(name = "Applicant.findByDocument", query = "SELECT a FROM Applicant a WHERE a.document = :document")
    , @NamedQuery(name = "Applicant.findByExternalcodedecla", query = "SELECT a FROM Applicant a WHERE a.externalcodedecla = :externalcodedecla")
    , @NamedQuery(name = "Applicant.findByExternalname", query = "SELECT a FROM Applicant a WHERE a.externalname = :externalname")
    , @NamedQuery(name = "Applicant.findByExternalreplegal", query = "SELECT a FROM Applicant a WHERE a.externalreplegal = :externalreplegal")
    , @NamedQuery(name = "Applicant.findByName", query = "SELECT a FROM Applicant a WHERE a.name = :name")
    , @NamedQuery(name = "Applicant.findByMail", query = "SELECT a FROM Applicant a WHERE a.mail = :mail")})
public class Applicant implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ID")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "DOCUMENT")
    private String document;
    @Size(max = 255)
    @Column(name = "EXTERNALCODEDECLA")
    private String externalcodedecla;
    @Size(max = 255)
    @Column(name = "EXTERNALNAME")
    private String externalname;
    @Size(max = 255)
    @Column(name = "EXTERNALREPLEGAL")
    private String externalreplegal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "NAME")
    private String name;
    @Size(max = 512)
    @Column(name = "MAIL")
    private String mail;
    @JoinColumn(name = "ATTRIBUTE", referencedColumnName = "ID")
    @ManyToOne
    private CatAttribute attribute;
    @JoinColumn(name = "EXTAPPTYPE", referencedColumnName = "ID")
    @ManyToOne
    private CatExtapptype extapptype;
    @JoinColumn(name = "POSITION", referencedColumnName = "ID")
    @ManyToOne
    private CatPosition position;
  
    public Applicant() {
    }

    public Applicant(String id) {
        this.id = id;
    }

    public Applicant(String id, String document, String name) {
        this.id = id;
        this.document = document;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getExternalcodedecla() {
        return externalcodedecla;
    }

    public void setExternalcodedecla(String externalcodedecla) {
        this.externalcodedecla = externalcodedecla;
    }

    public String getExternalname() {
        return externalname;
    }

    public void setExternalname(String externalname) {
        this.externalname = externalname;
    }

    public String getExternalreplegal() {
        return externalreplegal;
    }

    public void setExternalreplegal(String externalreplegal) {
        this.externalreplegal = externalreplegal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public CatAttribute getAttribute() {
        return attribute;
    }

    public void setAttribute(CatAttribute attribute) {
        this.attribute = attribute;
    }

    public CatExtapptype getExtapptype() {
        return extapptype;
    }

    public void setExtapptype(CatExtapptype extapptype) {
        this.extapptype = extapptype;
    }

    public CatPosition getPosition() {
        return position;
    }

    public void setPosition(CatPosition position) {
        this.position = position;
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
        if (!(object instanceof Applicant)) {
            return false;
        }
        Applicant other = (Applicant) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Applicant{" + "id=" + id + ", document=" + document + ", externalcodedecla=" + externalcodedecla + ", externalname=" + externalname + ", externalreplegal=" + externalreplegal + ", name=" + name + ", mail=" + mail + ", attribute=" + attribute + ", extapptype=" + extapptype + ", position=" + position + '}';
    }

    
    
}
