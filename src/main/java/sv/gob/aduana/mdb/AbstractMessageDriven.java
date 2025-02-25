/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.aduana.mdb;

import sv.gob.mh.oim.pojo.MessageQueue;

/**
 *
 * @author Datum-Redsoft
 */
public abstract class AbstractMessageDriven {
    
    
    public abstract void startup(MessageQueue msj);
    public abstract void error(MessageQueue msj);
    public abstract void complete(MessageQueue msj);
    
    
    protected boolean isFormIntern(String formType) {
        return formType.contains("INT_");
    }
    
    protected boolean isRequestNew(String requestType) {
        return requestType.contains("TYREQ-1");
    }

    protected boolean isRequestEdit(String requestType) {
        return requestType.contains("TYREQ-2") ||
                requestType.contains("TYREQ-5") ||
                requestType.contains("TYREQ-6");
    }

    protected boolean isRequestDisabled(String requestType) {
        return requestType.contains("TYREQ-3");
    }

    protected boolean isRequestEnabled(String requestType) {
        return requestType.contains("TYREQ-4");
    }
}
