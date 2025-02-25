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
public class UsuarioDGIIResponse {
    private int codigo;
    private String mensaje;
    private DataResponse data;

    public UsuarioDGIIResponse() {
    }

    public UsuarioDGIIResponse(int codigo, String mensaje, DataResponse data) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.data = data;
    }

    public DataResponse getData() {
        return data;
    }

    public void setData(DataResponse data) {
        this.data = data;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "UsuarioDGII{" + "codigo=" + codigo + ", mensaje=" + mensaje + ", data=" + data + '}';
    }
    
}
