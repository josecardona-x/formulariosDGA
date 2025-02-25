/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.aduana.util;

import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.MultipartBuilder;
import java.io.IOException;
import sv.gob.mh.dto.Category;
import sv.gob.mh.dto.Group;
import sv.gob.mh.dto.Item;
import sv.gob.mh.dto.Request;
import sv.gob.mh.dto.Requester;
import sv.gob.mh.dto.ServiceCategory;
import sv.gob.mh.dto.Site;
import sv.gob.mh.dto.SolicitudMesa;
import sv.gob.mh.dto.Status;
import sv.gob.mh.dto.Subcategory;
import sv.gob.mh.dto.Template;
import sv.gob.mh.dto.UdfFields;

/**
 *
 * @author Datum-Redsoft
 */
public class CrearJsonSolicitud {
    WriteLog log;

    public CrearJsonSolicitud() {
        log = new WriteLog();
    }
    

    public String test(String x) {
        x = "Hola " + x;
        return x;
    }

    public SolicitudMesa solicitudCreacion(String html, String titulo, String cat, String reqName, String correo_requester, String nombre_requester) {

        String msjCadena = cat + " - " + reqName + " - " + correo_requester + " - " + nombre_requester;
        log.printLog("INFO", "CrearJsonSolicitud", "solicitudCreacion", "Parametros para la cadena JSON " + msjCadena);
        
        SolicitudMesa solicitud = new SolicitudMesa();
        String itemId;
        String itemName;
        
        
// SE ARMA EL OBJETO DE REQUEST, SON TODOS LOS CAMPOS QUE SOLICITA LA MESA DE SERVICIO PARA LA PETICION
        Request request = new Request();
        Requester requester = new Requester();
        UdfFields udfFields = new UdfFields();
        Template template = new Template();
        Site site = new Site();
        Group group = new Group();
        ServiceCategory template_serviceCategory = new ServiceCategory();
        ServiceCategory serviceCategory = new ServiceCategory();
        Category category = new Category();
        Subcategory subcategory = new Subcategory();
        Item item = new Item();
        Status status = new Status();
        
        //SE VERIFICA A QUE TIPO DE ACCION SE DEBE ASIGNAR, LA VARIABLE cat CONTIENE EL ID IDENTIFICADOR DEL TIPO

        requester.setName("Asistencia Tecnologica DGA");
        switch (cat) {
            case "242":
                itemId = "1208";
                itemName = "Creacion";              
                requester.setEmail_id("mantenimiento.sirhoim@mh.gob.sv");
                break;

            case "246":
                itemId = "1213";
                itemName = "Modificacion";
                requester.setEmail_id(correo_requester);
                break;

            case "244":
                itemId = "1210";
                itemName = "Desactivacion";
                requester.setEmail_id(correo_requester);
                break;

            case "245":
                if (reqName.contains("temporal")) {
                    itemId = "1216";
                    itemName = "Traslado temporal";
                    requester.setEmail_id(correo_requester);
                    break;
                } else {
                    itemId = "1215";
                    itemName = "Traslado definitivo";
                    requester.setEmail_id(correo_requester);
                    break;
                }

            default:
                itemId = "1204";
                itemName = "Activacion";
                requester.setEmail_id(correo_requester);
                break;
        }

        
        udfFields.setUdf_pick_602("Tecnologia");
        udfFields.setUdf_pick_3302("Solicitud de Servicio");
        udfFields.setUdf_pick_99("DGA");
        
        template_serviceCategory.setId("3601");
        
        template.setIs_service_template(false);
        template.setService_category(template_serviceCategory);
        template.setName("Gestión de Acceso / Usuarios |Activación / Actualización / Biométricos /  Contraseñas / Creación / Depuración /  Desactivación / Desbloqueo / Formulación /  Modificación / Revisión / Traslados|");
        template.setId("3990");
        
        site.setId("1203");
        site.setName("DGA TECNOLOGIA");
        
        group.setId("2708");
        group.setName("DGA GESTION USUARIO");
        group.setSite(site);
        
        serviceCategory.setId("3601");
        serviceCategory.setName("Gestión de Acceso");
        serviceCategory.setCiid("67801");
        
        category.setName("Gestión de Acceso");
        category.setId("3301");
        
        subcategory.setName("Identidades");
        subcategory.setId("3003");
        
        item.setName(itemName);
        item.setId(itemId);
        
        status.setName("Asignado");
        
        request.setSubject(titulo);
        request.setDescription(html);
        request.setRequester(requester);
        request.setUdf_fields(udfFields);
        request.setTemplate(template);
        request.setSite(site);
        request.setGroup(group);
        request.setService_category(serviceCategory);
        request.setCategory(category);
        request.setSubcategory(subcategory);
        request.setItem(item);
        request.setStatus(status);

        solicitud.setRequest(request);

        return solicitud;
    }

}
