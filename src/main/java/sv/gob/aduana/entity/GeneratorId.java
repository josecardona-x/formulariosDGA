/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.aduana.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Datum-Redsoft
 */
@Entity
@Table(name = "GENERATORID")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GeneratorId.findAll", query = "SELECT c FROM GeneratorId c")
    , @NamedQuery(name = "GeneratorId.findById", query = "SELECT c FROM GeneratorId c WHERE c.id = :id")})
public class GeneratorId {
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ID")
    private String id;
    
    @Column(name = "COUNTER")
    private Integer counter;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }
    
    
}
