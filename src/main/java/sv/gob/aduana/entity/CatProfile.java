/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.aduana.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "CAT_PROFILE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatProfile.findAll", query = "SELECT c FROM CatProfile c")
    , @NamedQuery(name = "CatProfile.findById", query = "SELECT c FROM CatProfile c WHERE c.id = :id")
    , @NamedQuery(name = "CatProfile.findByName", query = "SELECT c FROM CatProfile c WHERE c.name = :name")
    , @NamedQuery(name = "CatProfile.findByStatus", query = "SELECT c FROM CatProfile c WHERE c.status = :status")
    , @NamedQuery(name = "CatProfile.findByStatusAndRole", query = "SELECT c FROM CatProfile c WHERE c.status = :status AND c.role LIKE :role ")})
public class CatProfile implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ID")
    private String id;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @Column(name = "STATUS")
    private Integer status;
    @Column(name = "ROLE")
    private String role;
    @OneToMany(mappedBy = "profile")
    private List<Profile> profileList;
    @OneToMany(mappedBy = "profile")
    private List<CatProfiledetail> catProfiledetailList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profile")
    private List<Profilehistory> profilehistoryList;

    public CatProfile() {
    }

    public CatProfile(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @XmlTransient
    public List<Profile> getProfileList() {
        return profileList;
    }

    public void setProfileList(List<Profile> profileList) {
        this.profileList = profileList;
    }

    @XmlTransient
    public List<CatProfiledetail> getCatProfiledetailList() {
        return catProfiledetailList;
    }

    public void setCatProfiledetailList(List<CatProfiledetail> catProfiledetailList) {
        this.catProfiledetailList = catProfiledetailList;
    }

    @XmlTransient
    public List<Profilehistory> getProfilehistoryList() {
        return profilehistoryList;
    }

    public void setProfilehistoryList(List<Profilehistory> profilehistoryList) {
        this.profilehistoryList = profilehistoryList;
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
        if (!(object instanceof CatProfile)) {
            return false;
        }
        CatProfile other = (CatProfile) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.aduana.entity.CatProfile[ id=" + id + " ]";
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
}
