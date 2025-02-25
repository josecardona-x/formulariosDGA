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
public class ResponseStatus {
    
    @SerializedName("status_code")
    private int status_code;

    public ResponseStatus(int status_code) {
        this.status_code = status_code;
    }

    public ResponseStatus() {
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    @Override
    public String toString() {
        return "ResponseStatus{" + "status_code=" + status_code + '}';
    }
    
    
}
