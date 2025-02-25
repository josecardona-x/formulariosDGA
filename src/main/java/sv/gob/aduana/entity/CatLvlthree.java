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
@Table(name = "CAT_LVLTHREE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatLvlthree.findAll", query = "SELECT c FROM CatLvlthree c")
    , @NamedQuery(name = "CatLvlthree.findById", query = "SELECT c FROM CatLvlthree c WHERE c.id = :id")
    , @NamedQuery(name = "CatLvlthree.findByName", query = "SELECT c FROM CatLvlthree c WHERE c.name = :name")
    , @NamedQuery(name = "CatLvlthree.findByStatus", query = "SELECT c FROM CatLvlthree c WHERE c.status = :status")
    , @NamedQuery(name = "CatLvlthree.findByStatusAndLevel", query = "SELECT c FROM CatLvlthree c WHERE c.status = :status AND c.lvltwo.id = :lvltwo")})
public class CatLvlthree implements Serializable {

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
    @JoinColumn(name = "LVLTWO", referencedColumnName = "ID")
    @ManyToOne
    private CatLvltwo lvltwo;
  
    public CatLvlthree() {
    }

    public CatLvlthree(String id) {
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

    public CatLvltwo getLvltwo() {
        return lvltwo;
    }

    public void setLvltwo(CatLvltwo lvltwo) {
        this.lvltwo = lvltwo;
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
        if (!(object instanceof CatLvlthree)) {
            return false;
        }
        CatLvlthree other = (CatLvlthree) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.aduana.entity.CatLvlthree[ id=" + id + " ]";
    }
    
}
