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
@Table(name = "CAT_LVLTWO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatLvltwo.findAll", query = "SELECT c FROM CatLvltwo c")
    , @NamedQuery(name = "CatLvltwo.findById", query = "SELECT c FROM CatLvltwo c WHERE c.id = :id")
    , @NamedQuery(name = "CatLvltwo.findByName", query = "SELECT c FROM CatLvltwo c WHERE c.name = :name")
    , @NamedQuery(name = "CatLvltwo.findByStatus", query = "SELECT c FROM CatLvltwo c WHERE c.status = :status")
    , @NamedQuery(name = "CatLvltwo.findByStatusAndLevel", query = "SELECT c FROM CatLvltwo c WHERE c.status = :status AND c.lvlone.id = :lvlone")})
public class CatLvltwo implements Serializable {

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
    @JoinColumn(name = "LVLONE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private CatLvlone lvlone;

    public CatLvltwo() {
    }

    public CatLvltwo(String id) {
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

  

    public CatLvlone getLvlone() {
        return lvlone;
    }

    public void setLvlone(CatLvlone lvlone) {
        this.lvlone = lvlone;
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
        if (!(object instanceof CatLvltwo)) {
            return false;
        }
        CatLvltwo other = (CatLvltwo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.aduana.entity.CatLvltwo[ id=" + id + " ]";
    }
    
}
