/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.aduana.form;

/**
 *
 * @author Datum-Redsoft
 */
public class RequestTypeService {
    
    /**
     * Sona de manejo de permisos de las solicitudes
     * @param r
     * @return 
     */
    
    public static boolean isRequestWithPermissionsFilled(String r) {
        return r.equals("TYREQ-3");
    }
    
    
    public static boolean isRequestForNewData(String r) {
        return isNew(r);
    }
    
    public static boolean isIntern(String type){
        return type.contains("Interno");
    }
    
    public static boolean isExtern(String type){
        return type.contains("Externo");
    }
    
    public static boolean isNew(String r){
        return r.equals("TYREQ-1") ||  r.equals("TYREQ-7") ;
    }
    
    public static boolean isUpdated(String r){
        return r.equals("TYREQ-2") ||  r.equals("TYREQ-5")  || r.equals("TYREQ-6");
    }
    
    public static boolean isDisabled(String r){
        return r.equals("TYREQ-3")||  r.equals("TYREQ-8");
    }
    
    public static boolean isEnabled(String r){
        return r.equals("TYREQ-4");
    }
}
