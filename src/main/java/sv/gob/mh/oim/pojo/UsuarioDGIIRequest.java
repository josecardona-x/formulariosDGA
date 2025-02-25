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
public class UsuarioDGIIRequest {
    private int codigo;
    private String mensaje;
    private DataRequest data;

    public UsuarioDGIIRequest() {
    }

    public UsuarioDGIIRequest(int codigo, String mensaje, DataRequest data) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.data = data;
    }

    public DataRequest getData() {
        return data;
    }

    public void setData(DataRequest data) {
        this.data = data;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "UsuarioDGIIRequest{" + "codigo=" + codigo + ", mensaje=" + mensaje + ", data=" + data + '}';
    }
    
}
