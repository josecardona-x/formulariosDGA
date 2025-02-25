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
public class Template {
    private boolean is_service_template;
    private ServiceCategory service_category;
    private String name;
    private String id;

    public Template() {
    }

    public Template(boolean is_service_template, ServiceCategory service_category, String name, String id) {
        this.is_service_template = is_service_template;
        this.service_category = service_category;
        this.name = name;
        this.id = id;
    }

    public boolean isIs_service_template() {
        return is_service_template;
    }

    public void setIs_service_template(boolean is_service_template) {
        this.is_service_template = is_service_template;
    }

    public ServiceCategory getService_category() {
        return service_category;
    }

    public void setService_category(ServiceCategory service_category) {
        this.service_category = service_category;
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
        return "Template{" + "is_service_template=" + is_service_template + ", service_category=" + service_category + ", name=" + name + ", id=" + id + '}';
    }
    
    
    
}
