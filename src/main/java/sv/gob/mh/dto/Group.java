/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mh.dto;

/**
 *
 * @author Datum - Redsoft
 */
public class Group {
    private String id;
    private String name;
    private Site site;

    public Group() {
    }

    public Group(String id, String name, Site site) {
        this.id = id;
        this.name = name;
        this.site = site;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
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
    
}
