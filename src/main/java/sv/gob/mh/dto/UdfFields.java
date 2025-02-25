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
public class UdfFields {
    public String udf_pick_602;
    public String udf_pick_3302;
    public String udf_pick_99;

    public UdfFields() {
    }

    public UdfFields(String udf_pick_602, String udf_pick_3302, String udf_pick_99) {
        this.udf_pick_602 = udf_pick_602;
        this.udf_pick_3302 = udf_pick_3302;
        this.udf_pick_99 = udf_pick_99;
    }

    public String getUdf_pick_602() {
        return udf_pick_602;
    }

    public void setUdf_pick_602(String udf_pick_602) {
        this.udf_pick_602 = udf_pick_602;
    }

    public String getUdf_pick_3302() {
        return udf_pick_3302;
    }

    public void setUdf_pick_3302(String udf_pick_3302) {
        this.udf_pick_3302 = udf_pick_3302;
    }

    public String getUdf_pick_99() {
        return udf_pick_99;
    }

    public void setUdf_pick_99(String udf_pick_99) {
        this.udf_pick_99 = udf_pick_99;
    }

    @Override
    public String toString() {
        return "UdfFields{" + "udf_pick_602=" + udf_pick_602 + ", udf_pick_3302=" + udf_pick_3302 + ", udf_pick_99=" + udf_pick_99 + '}';
    }
    
    
}
