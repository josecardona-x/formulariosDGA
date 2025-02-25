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
public class Subcategory {
    public String name;
    public String id;

    public Subcategory() {
    }

    public Subcategory(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Subcategory{" + "name=" + name + ", id=" + id + '}';
    }
    
    
}
