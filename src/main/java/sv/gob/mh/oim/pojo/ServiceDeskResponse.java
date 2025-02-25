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
public class ServiceDeskResponse {

    @SerializedName("request")
    private Request request;
    
    @SerializedName("response_status")
    private ResponseStatus response_status;

    public ServiceDeskResponse() {
    }

    public ServiceDeskResponse(Request request, ResponseStatus response_status) {
        this.request = request;
        this.response_status = response_status;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public ResponseStatus getResponse_status() {
        return response_status;
    }

    public void setResponse_status(ResponseStatus response_status) {
        this.response_status = response_status;
    }

    @Override
    public String toString() {
        return "ServiceDeskResponse{" + "request=" + request + ", response_status=" + response_status + '}';
    }
    

}
