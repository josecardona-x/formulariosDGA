/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.aduana.util;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import sv.gob.mh.oim.pojo.AuthDGII;
import sv.gob.mh.oim.pojo.DataRequest;
import sv.gob.mh.oim.pojo.TokenDGII;
import sv.gob.mh.oim.pojo.UsuarioDGIIRequest;
import sv.gob.mh.oim.pojo.UsuarioDGIIResponse;

/**
 *
 * @author jhomi
 */
public class ClientDGII {

    public UsuarioDGIIResponse uTIL0010(String documento) {
        String urlLogin = "http://appint.mh.gob.sv/dgii-api/api/login";
        String urlInfo = "http://appint.mh.gob.sv/dgii-api/infomacionDuiNit";
        UsuarioDGIIResponse dgii = new UsuarioDGIIResponse();
        TokenDGII token = new TokenDGII();
        Client cliToken = ClientBuilder.newClient();
        Client cliDGII = ClientBuilder.newClient();

        try {
            AuthDGII auth = new AuthDGII("88000801211016", "Ministerio2020");
            token = cliToken.target(urlLogin).
                    request().
                    post(Entity.entity(auth, MediaType.APPLICATION_JSON), TokenDGII.class);
            DataRequest dtReq = new DataRequest();

            dtReq.setDui(documento);

            UsuarioDGIIRequest usr = new UsuarioDGIIRequest();
            usr.setCodigo(200);
            usr.setMensaje("Exitoso");
            usr.setData(dtReq);

            dgii = cliToken.target(urlInfo).
                    request().header("X-Auth-Token", String.valueOf(token.getToken())).
                    post(Entity.entity(usr, MediaType.APPLICATION_JSON), UsuarioDGIIResponse.class);

            System.out.println(dgii);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cliToken.close();
            cliDGII.close();
        }
        return dgii;
    }
}
