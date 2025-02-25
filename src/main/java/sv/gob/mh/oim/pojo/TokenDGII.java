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
public class TokenDGII {
    private String token;
    private String nit;

    public TokenDGII() {
    }

    public TokenDGII(String token, String nit) {
        this.token = token;
        this.nit = nit;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "TokenDGII{" + "token=" + token + ", nit=" + nit + '}';
    }
    
}
