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
public class Requester {
    private String email_id;
    private Object phone;
    private String name;
    private Object mobile;

    public Requester() {
    }

    public Requester(String email_id, Object phone, String name, Object mobile) {
        this.email_id = email_id;
        this.phone = phone;
        this.name = name;
        this.mobile = mobile;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public Object getPhone() {
        return phone;
    }

    public void setPhone(Object phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getMobile() {
        return mobile;
    }

    public void setMobile(Object mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "Requester{" + "email_id=" + email_id + ", phone=" + phone + ", name=" + name + ", mobile=" + mobile + '}';
    }
    
    
    
}
