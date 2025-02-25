/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mh.oim.pojo;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Datum-Redsoft
 */
public class Request {
    
    @SerializedName("id")
    private String id;

    public Request() {
        
    }

    public Request(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Request{" + "id=" + id + '}';
    }
    
}
