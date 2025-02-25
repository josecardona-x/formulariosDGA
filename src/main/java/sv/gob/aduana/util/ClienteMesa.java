/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.aduana.util;

import com.google.gson.Gson;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import java.io.IOException;
import sv.gob.mh.dto.SolicitudMesa;

/**
 *
 * @author Datum-Redsoft
 * Conexion al cliente de la mesa de servicio
 * @version creado - 01/12/2023 - Andres Quijada
 * @version Actualizado - 08/01/2024 - Mikel Escobar
 */
public class ClienteMesa {
    
    public WriteLog log;

    public static String urlMesa = System.getProperty("custom.enviroment.oim.urlMesa");        
    public static String tokenMesa = System.getProperty("custom.enviroment.oim.tokenMesa");

    public ClienteMesa() {
        log = new WriteLog();
    }
    

    /**
     * 
     * @param solicitud con la información para la creación del caso en la mesa de servicios
     * @return cadena con la respuesta del servicio
     */
    public String envioSolicitudMesa(SolicitudMesa solicitud) {
        // Crear un cliente OkHttp
        OkHttpClient client = new OkHttpClient();
        log.printLog("INFO", "ClienteMesa", "envioSolicitudMesa", "Inicia la consulta del servicio web de la mesa para " + solicitud.getRequest().getRequester().getName());
        
        // Crear un objeto Gson para convertir objetos Java a JSON
        Gson gson = new Gson();
        String jsonSolicitud = gson.toJson(solicitud);
        String jsonConvertido = jsonSolicitud.replace("\\u003c", "<").replace("\\u003e", ">").replace("\\u0026", "&").replace("\\u003d", "=");

        MultipartBuilder builder = new MultipartBuilder().type(MultipartBuilder.FORM);
        log.printLog("INFO", "ClienteMesa", "envioSolicitudMesa", jsonConvertido);
        builder.addFormDataPart("input_data", jsonConvertido);
        RequestBody requestBody = builder.build();
        
        String url = "https://"+urlMesa+"/api/v3/requests";

        com.squareup.okhttp.Request request = new com.squareup.okhttp.Request.Builder()
                .url(url)
                .addHeader("authtoken", tokenMesa)
                .post(requestBody)
                .build();
        String responseData = "";
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                // Manejar la respuesta aquí
                responseData = response.body().string();
                response.body().close();
                log.printLog("INFO", "ClienteMesa", "envioSolicitudMesa", "Finaliza la creación del caso de soporte en mesa de servicios para " + solicitud.getRequest().getRequester().getName());
            } else {
                response.body().close();
                // Manejar errores si la respuesta no es exitosa
                log.printLog("ERROR", "ClienteMesa", "envioSolicitudMesa", response.body().string());
            }
        } catch (IOException e) {
            log.printLog("ERROR", "ClienteMesa", "envioSolicitudMesa", e.getMessage());
            e.printStackTrace();
        }
        return responseData;
    }

}
