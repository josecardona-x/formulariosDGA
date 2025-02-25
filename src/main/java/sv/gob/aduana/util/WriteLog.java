/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.aduana.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase para imprimir mensajes en las bitacora log
 * @author Mikel Escobar
 */
public class WriteLog {
    private String date;
    private String level;
    private String className;
    private String methodName;
    private String message;

    public WriteLog() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
    
    public void printLog(String level, String className, String methodName, String message){
        SimpleDateFormat simple = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        this.date = simple.format(new Date());
        this.level = level;
        this.className = className;
        this.methodName = methodName;
        this.message = message;
        System.out.println("<" + date + "> <BE-DGA> <" + level + "> <" + className + "> <" + methodName + "> <" + message + ">");
    }   
    
}
