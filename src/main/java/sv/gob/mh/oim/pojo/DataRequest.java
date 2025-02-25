/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mh.oim.pojo;

/**
 *
 * @author jhomi
 */
public class DataRequest {
    private String dui;
    private String nit;

    public DataRequest() {
    }

    public DataRequest(String dui, String nit) {
        this.dui = dui;
        this.nit = nit;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    @Override
    public String toString() {
        return "DataRequest{" + "dui=" + dui + ", nit=" + nit + '}';
    }
    
}
