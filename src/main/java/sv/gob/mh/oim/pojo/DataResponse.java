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
public class DataResponse {
    private String s1ApeRasoc;
    private String sB2ApeAbrev;
    private String sNombres;
    private String sClase;
    private String sNit;
    private String sDui;

    public DataResponse() {
    }

    public DataResponse(String s1ApeRasoc, String sB2ApeAbrev, String sNombres, String sClase, String sNit, String sDui) {
        this.s1ApeRasoc = s1ApeRasoc;
        this.sB2ApeAbrev = sB2ApeAbrev;
        this.sNombres = sNombres;
        this.sClase = sClase;
        this.sNit = sNit;
        this.sDui = sDui;
    }

    public String getsDui() {
        return sDui;
    }

    public void setsDui(String sDui) {
        this.sDui = sDui;
    }

    public String getS1ApeRasoc() {
        return s1ApeRasoc;
    }

    public void setS1ApeRasoc(String s1ApeRasoc) {
        this.s1ApeRasoc = s1ApeRasoc;
    }

    public String getsB2ApeAbrev() {
        return sB2ApeAbrev;
    }

    public void setsB2ApeAbrev(String sB2ApeAbrev) {
        this.sB2ApeAbrev = sB2ApeAbrev;
    }

    public String getsNombres() {
        return sNombres;
    }

    public void setsNombres(String sNombres) {
        this.sNombres = sNombres;
    }

    public String getsClase() {
        return sClase;
    }

    public void setsClase(String sClase) {
        this.sClase = sClase;
    }

    public String getsNit() {
        return sNit;
    }

    public void setsNit(String sNit) {
        this.sNit = sNit;
    }

    @Override
    public String toString() {
        return "Data{" + "s1ApeRasoc=" + s1ApeRasoc + ", sB2ApeAbrev=" + sB2ApeAbrev + ", sNombres=" + sNombres + ", sClase=" + sClase + ", sNit=" + sNit + ", sDui=" + sDui + '}';
    }
    
}
