/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mh.dto;

/**
 *
 * @author andres
 */
public class ServiceCategory {
    private String id;
    private String name;
    private String ciid;

    public ServiceCategory() {
    }

    public ServiceCategory(String id, String name, String ciid) {
        this.id = id;
        this.name = name;
        this.ciid = ciid;
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

    public String getCiid() {
        return ciid;
    }

    public void setCiid(String ciid) {
        this.ciid = ciid;
    }

    @Override
    public String toString() {
        return "ServiceCategory{" + "id=" + id + ", name=" + name + ", ciid=" + ciid + '}';
    }
    
    
}
