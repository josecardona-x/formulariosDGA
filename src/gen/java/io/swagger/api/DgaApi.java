package io.swagger.api;

import io.swagger.model.Account;
import io.swagger.model.Applicant;
import io.swagger.model.CatalogAttribute;
import io.swagger.model.CatalogCustoms;
import io.swagger.model.CatalogExternalApplicantType;
import io.swagger.model.CatalogFormType;
import io.swagger.model.CatalogGroup;
import io.swagger.model.CatalogLevelFour;
import io.swagger.model.CatalogLevelOne;
import io.swagger.model.CatalogLevelThree;
import io.swagger.model.CatalogLevelTwo;
import io.swagger.model.CatalogPosition;
import io.swagger.model.CatalogProfile;
import io.swagger.model.CatalogProfileExternal;
import io.swagger.model.CatalogRequestType;
import io.swagger.model.CatalogResource;
import io.swagger.model.CatalogSystem;
import io.swagger.model.CatalogSystemExternal;
import io.swagger.model.CatalogTypeAFPA;
import io.swagger.model.Email;
import io.swagger.model.EventLog;
import io.swagger.model.Flow;
import io.swagger.model.Form;
import io.swagger.model.Login;
import io.swagger.model.Other;
import io.swagger.model.Person;
import io.swagger.model.Profile;
import io.swagger.model.Request;
import io.swagger.model.Resource;
import io.swagger.model.System;
import io.swagger.api.DgaApiService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import io.swagger.annotations.*;
import java.io.InputStream;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;

import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
@Path("/dga")
@RequestScoped

@Api(description = "the dga API")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJAXRSCXFCDIServerCodegen", date = "2022-02-21T16:30:37.574Z")

public class DgaApi  {

  @Context SecurityContext securityContext;

  @Inject DgaApiService delegate;


    @GET
    @Path("/form/list/type")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Catalogo de tipos de solicitud (Interna y Externa)", notes = "finish", response = CatalogFormType.class, responseContainer = "List", tags={ "catalog",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Carga catalogo de tipos de formulario", response = CatalogFormType.class, responseContainer = "List") })
    public Response cAT0001() {
        return delegate.cAT0001(securityContext);
    }

    @GET
    @Path("/form/request/list/afpa/type")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Catalogo de AFPA", notes = "finish", response = CatalogTypeAFPA.class, responseContainer = "List", tags={ "catalog",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Carga catalogo de AFPA", response = CatalogTypeAFPA.class, responseContainer = "List") })
    public Response cAT0002() {
        return delegate.cAT0002(securityContext);
    }

    @GET
    @Path("/form/request/list/customs")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Catalogo de Aduanas", notes = "", response = CatalogCustoms.class, responseContainer = "List", tags={ "catalog",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Carga catalogo de Aduanas", response = CatalogCustoms.class, responseContainer = "List") })
    public Response cAT0003() {
        return delegate.cAT0003(securityContext);
    }

    @GET
    @Path("/form/request/list/ext/applicant/type")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Catalogo de tipos de solicitantes externos", notes = "finish", response = CatalogExternalApplicantType.class, responseContainer = "List", tags={ "catalog",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Carga catalogo de tipos de solicitantes externos", response = CatalogExternalApplicantType.class, responseContainer = "List") })
    public Response cAT0004() {
        return delegate.cAT0004(securityContext);
    }

    @GET
    @Path("/form/request/list/ext/profile")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Catalogo de tipos de perfiles externos", notes = "finish", response = CatalogProfileExternal.class, responseContainer = "List", tags={ "catalog",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Carga catalogo de perfiles externos", response = CatalogProfileExternal.class, responseContainer = "List") })
    public Response cAT0005() {
        return delegate.cAT0005(securityContext);
    }

    @GET
    @Path("/form/request/list/ext/system")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Catalogo de tipos de sistemas externos", notes = "finish", response = CatalogSystemExternal.class, responseContainer = "List", tags={ "catalog",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Carga catalogo de sistemas externos", response = CatalogSystemExternal.class, responseContainer = "List") })
    public Response cAT0006() {
        return delegate.cAT0006(securityContext);
    }

    @GET
    @Path("/form/request/list/level/attribute")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Catalogo de Atributos para internos", notes = "finish", response = CatalogAttribute.class, responseContainer = "List", tags={ "catalog",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Carga catalogo de Atributos para internos", response = CatalogAttribute.class, responseContainer = "List") })
    public Response cAT0007() {
        return delegate.cAT0007(securityContext);
    }

    @GET
    @Path("/form/request/list/level/one")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Catalogo de Nivel 1", notes = "finish", response = CatalogLevelOne.class, responseContainer = "List", tags={ "catalog",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Catalogo de Nivel 1", response = CatalogLevelOne.class, responseContainer = "List") })
    public Response cAT0008() {
        return delegate.cAT0008(securityContext);
    }

    @GET
    @Path("/form/request/list/level/two/{levelOneId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Catalogo de Nivel 2", notes = "finish", response = CatalogLevelTwo.class, responseContainer = "List", tags={ "catalog",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Catalogo de Nivel 2", response = CatalogLevelTwo.class, responseContainer = "List") })
    public Response cAT0009(@ApiParam(value = "",required=true) @PathParam("levelOneId") String levelOneId) {
        return delegate.cAT0009(levelOneId, securityContext);
    }

    @GET
    @Path("/form/request/list/level/three/{levelOneId}/{levelTwoId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Catalogo de Nivel 3", notes = "finish", response = CatalogLevelThree.class, responseContainer = "List", tags={ "catalog",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Catalogo de Nivel 3", response = CatalogLevelThree.class, responseContainer = "List") })
    public Response cAT0010(@ApiParam(value = "",required=true) @PathParam("levelOneId") String levelOneId, @ApiParam(value = "",required=true) @PathParam("levelTwoId") String levelTwoId) {
        return delegate.cAT0010(levelOneId, levelTwoId, securityContext);
    }

    @GET
    @Path("/form/request/list/level/four/{levelOneId}/{levelTwoId}/{levelThreeId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Catalogo de Nivel 4", notes = "finish", response = CatalogLevelFour.class, responseContainer = "List", tags={ "catalog",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Catalogo de Nivel 4", response = CatalogLevelFour.class, responseContainer = "List") })
    public Response cAT0011(@ApiParam(value = "",required=true) @PathParam("levelOneId") String levelOneId, @ApiParam(value = "",required=true) @PathParam("levelTwoId") String levelTwoId, @ApiParam(value = "",required=true) @PathParam("levelThreeId") String levelThreeId) {
        return delegate.cAT0011(levelOneId, levelTwoId, levelThreeId, securityContext);
    }

    @GET
    @Path("/form/request/list/position")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Catalogo de cargos", notes = "", response = CatalogPosition.class, responseContainer = "List", tags={ "catalog",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Carga catalogo de posiciones", response = CatalogPosition.class, responseContainer = "List") })
    public Response cAT0012() {
        return delegate.cAT0012(securityContext);
    }

    @GET
    @Path("/form/request/list/profile/{role}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Catalogo de perfiles internos", notes = "finish", response = CatalogProfile.class, responseContainer = "List", tags={ "catalog",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Carga catalogo de perfiles internos", response = CatalogProfile.class, responseContainer = "List") })
    public Response cAT0013(@ApiParam(value = "",required=true) @PathParam("role") String role) {
        return delegate.cAT0013(role, securityContext);
    }

    @GET
    @Path("/form/request/list/resource/{role}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Catalogo de recursos internos", notes = "", response = CatalogResource.class, responseContainer = "List", tags={ "catalog",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Carga catalogo de recursos internos", response = CatalogResource.class, responseContainer = "List") })
    public Response cAT0014(@ApiParam(value = "",required=true) @PathParam("role") String role) {
        return delegate.cAT0014(role, securityContext);
    }

    @GET
    @Path("/form/request/list/system/{role}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Catalogo de sistemas internos", notes = "finish", response = CatalogSystem.class, responseContainer = "List", tags={ "catalog",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Carga catalogo de sistemas internos", response = CatalogSystem.class, responseContainer = "List") })
    public Response cAT0015(@ApiParam(value = "",required=true) @PathParam("role") String role) {
        return delegate.cAT0015(role, securityContext);
    }

    @GET
    @Path("/form/request/list/system/group/{system}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Catalogo de grupos internos", notes = "finish", response = CatalogGroup.class, responseContainer = "List", tags={ "catalog",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Carga catalogo de grupos internos", response = CatalogGroup.class, responseContainer = "List") })
    public Response cAT0016(@ApiParam(value = "",required=true) @PathParam("system") String system) {
        return delegate.cAT0016(system, securityContext);
    }

    @GET
    @Path("/form/request/list/type")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Catalogo de tipos de tipos de solicitud", notes = "finish", response = CatalogRequestType.class, responseContainer = "List", tags={ "catalog",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Carga catalogo de tipos de solicitud", response = CatalogRequestType.class, responseContainer = "List") })
    public Response cAT0018() {
        return delegate.cAT0018(securityContext);
    }

    @POST
    @Path("/form/request/list/ext/profile")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Guardar el  tipo de perfiles externos", notes = "", response = CatalogProfileExternal.class, tags={ "catalog",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Guardar el tipo de perfiles externos", response = CatalogProfileExternal.class) })
    public Response cRUD0007(@ApiParam(value = "" ,required=true) CatalogProfileExternal body) {
        return delegate.cRUD0007(body, securityContext);
    }

    @PATCH
    @Path("/form/request/list/ext/profile")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Actualizar el tipo de perfiles externos", notes = "", response = CatalogProfileExternal.class, tags={ "catalog",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Actualizar el tipo de perfiles externos", response = CatalogProfileExternal.class) })
    public Response cRUD0008(@ApiParam(value = "" ,required=true) CatalogProfileExternal body) {
        return delegate.cRUD0008(body, securityContext);
    }

    @POST
    @Path("/form/request/list/ext/system")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Guardar el  tipo de sistemas externos", notes = "", response = CatalogSystemExternal.class, tags={ "catalog",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Guardar el tipo de sistemas externos", response = CatalogSystemExternal.class) })
    public Response cRUD0009(@ApiParam(value = "" ,required=true) CatalogSystemExternal body) {
        return delegate.cRUD0009(body, securityContext);
    }

    @PATCH
    @Path("/form/request/list/ext/system")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Actualizar el tipo de sistemas externos", notes = "", response = CatalogSystemExternal.class, tags={ "catalog",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Actualizar el tipo de sistemas externos", response = CatalogSystemExternal.class) })
    public Response cRUD0010(@ApiParam(value = "" ,required=true) CatalogSystemExternal body) {
        return delegate.cRUD0010(body, securityContext);
    }

    @POST
    @Path("/form/request/list/profile/{role}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Guardar el  tipo de perfiles internos", notes = "", response = CatalogProfile.class, tags={ "catalog",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Guardar el tipo de perfiles internos", response = CatalogProfile.class) })
    public Response cRUD0011(@ApiParam(value = "",required=true) @PathParam("role") String role, @ApiParam(value = "" ,required=true) CatalogProfile body) {
        return delegate.cRUD0011(role, body, securityContext);
    }

    @PATCH
    @Path("/form/request/list/profile/{role}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Actualizar el tipo de perfiles internos", notes = "", response = CatalogProfile.class, tags={ "catalog",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Actualizar el tipo de perfiles internos", response = CatalogProfile.class) })
    public Response cRUD0012(@ApiParam(value = "",required=true) @PathParam("role") String role, @ApiParam(value = "" ,required=true) CatalogProfile body) {
        return delegate.cRUD0012(role, body, securityContext);
    }

    @POST
    @Path("/form/request/list/resource/{role}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Guardar el  tipo de recursos internos", notes = "", response = CatalogResource.class, tags={ "catalog",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Guardar el tipo de recursos internos", response = CatalogResource.class) })
    public Response cRUD0013(@ApiParam(value = "",required=true) @PathParam("role") String role, @ApiParam(value = "" ,required=true) CatalogResource body) {
        return delegate.cRUD0013(role, body, securityContext);
    }

    @PATCH
    @Path("/form/request/list/resource/{role}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Actualizar el tipo de recursos internos", notes = "", response = CatalogResource.class, tags={ "catalog",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Actualizar el tipo de recursos internos", response = CatalogResource.class) })
    public Response cRUD0014(@ApiParam(value = "",required=true) @PathParam("role") String role, @ApiParam(value = "" ,required=true) CatalogResource body) {
        return delegate.cRUD0014(role, body, securityContext);
    }

    @POST
    @Path("/form/request/list/system/{role}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Guardar el  tipo de sistemas internos", notes = "", response = CatalogSystem.class, tags={ "catalog",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Guardar el tipo de sistemas internos", response = CatalogSystem.class) })
    public Response cRUD0015(@ApiParam(value = "",required=true) @PathParam("role") String role, @ApiParam(value = "" ,required=true) CatalogSystem body) {
        return delegate.cRUD0015(role, body, securityContext);
    }

    @PATCH
    @Path("/form/request/list/system/{role}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Actualizar el tipo de sistemas internos", notes = "", response = CatalogSystem.class, tags={ "catalog",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Actualizar el tipo de sistemas internos", response = CatalogSystem.class) })
    public Response cRUD0016(@ApiParam(value = "",required=true) @PathParam("role") String role, @ApiParam(value = "" ,required=true) CatalogSystem body) {
        return delegate.cRUD0016(role, body, securityContext);
    }

    @POST
    @Path("/form/request/list/system/group/{system}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Guardar el  tipo de grupos internos", notes = "", response = CatalogGroup.class, tags={ "catalog",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Guardar el tipo de grupos internos", response = CatalogGroup.class) })
    public Response cRUD0017(@ApiParam(value = "",required=true) @PathParam("system") String system, @ApiParam(value = "" ,required=true) CatalogGroup body) {
        return delegate.cRUD0017(system, body, securityContext);
    }

    @PATCH
    @Path("/form/request/list/system/group/{system}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Actualizar el tipo de grupos internos", notes = "", response = CatalogGroup.class, tags={ "catalog",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Actualizar el tipo de grupos internos", response = CatalogGroup.class) })
    public Response cRUD0018(@ApiParam(value = "",required=true) @PathParam("system") String system, @ApiParam(value = "" ,required=true) CatalogGroup body) {
        return delegate.cRUD0018(system, body, securityContext);
    }

    @POST
    @Path("/form/request/list/afpa/type")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Listado de AFPA filtrado por documento", notes = "Listado de AFPA", response = CatalogTypeAFPA.class, responseContainer = "List", tags={ "catalog",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Listado de AFPA filtrado", response = CatalogTypeAFPA.class, responseContainer = "List") })
    public Response cat0019(@ApiParam(value = "" )@HeaderParam("document") String document) {
        return delegate.cat0019(document, securityContext);
    }

    @GET
    @Path("/dashboard/finish/{role}/{uid}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Obtener todas los formularios finalizados los ultimos 3 meses", notes = "finish", response = Form.class, responseContainer = "List", tags={ "dashboard",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Listado de todas los formularios finalizados", response = Form.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "No existen formularios", response = String.class) })
    public Response dGADASHFIN001(@ApiParam(value = "",required=true) @PathParam("role") String role, @ApiParam(value = "",required=true) @PathParam("uid") String uid) {
        return delegate.dGADASHFIN001(role, uid, securityContext);
    }

    @GET
    @Path("/dashboard/pending/{role}/{uid}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Obtener todas los formularios pendientes ordenados por fecha de creación descendente", notes = "finish", response = Form.class, responseContainer = "List", tags={ "dashboard",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Listado de todas los formularios pendientes", response = Form.class, responseContainer = "List") })
    public Response dGADASHFIN002(@ApiParam(value = "",required=true) @PathParam("role") String role, @ApiParam(value = "",required=true) @PathParam("uid") String uid) {
        return delegate.dGADASHFIN002(role, uid, securityContext);
    }

    @GET
    @Path("/dashboard/form/load/{id}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Carga toda la información del formulario", notes = "finish", response = Form.class, tags={ "dashboard",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Carga toda la información del formulario", response = Form.class),
        @ApiResponse(code = 403, message = "El formulario no se puede cargar", response = Void.class),
        @ApiResponse(code = 404, message = "No encontrado", response = Void.class) })
    public Response dGADASHFIN003(@ApiParam(value = "",required=true) @PathParam("id") String id) {
        return delegate.dGADASHFIN003(id, securityContext);
    }

    @GET
    @Path("/form/request/other/{requestId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Obtener todos los Otros definidos en las tablas (Todos menos en estado a borrar)", notes = "", response = Other.class, responseContainer = "List", tags={ "request",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Listado de Objetos de Otras solicitudes", response = Other.class, responseContainer = "List") })
    public Response dGAOTHER001(@ApiParam(value = "",required=true) @PathParam("requestId") String requestId) {
        return delegate.dGAOTHER001(requestId, securityContext);
    }

    @POST
    @Path("/form/request/other/{requestId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Crear una nueva solicitud de Otro", notes = "", response = Other.class, tags={ "request",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Se confirma como almacenado", response = Other.class) })
    public Response dGAOTHER002(@ApiParam(value = "",required=true) @PathParam("requestId") String requestId, @ApiParam(value = "Guardar un nuevo objeto profile" ,required=true) Other body) {
        return delegate.dGAOTHER002(requestId, body, securityContext);
    }

    @DELETE
    @Path("/form/request/other/{requestId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Marca como para borrar la solicitud", notes = "", response = Other.class, tags={ "request",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Respuesta correcta de marcado para borrar", response = Other.class) })
    public Response dGAOTHER003(@ApiParam(value = "",required=true) @PathParam("requestId") String requestId, @ApiParam(value = "" ,required=true) Other body) {
        return delegate.dGAOTHER003(requestId, body, securityContext);
    }

    @PATCH
    @Path("/form/request/other/{requestId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Marcar como finalizado", notes = "", response = Other.class, tags={ "request",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Marcar como finalizado", response = Other.class) })
    public Response dGAOTHER005(@ApiParam(value = "",required=true) @PathParam("requestId") String requestId, @ApiParam(value = "" ,required=true) Other body) {
        return delegate.dGAOTHER005(requestId, body, securityContext);
    }

    @OPTIONS
    @Path("/form/request/other/{requestId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Realizar integracion para enviar hacia tercero", notes = "", response = Other.class, tags={ "request",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Realizar integracion para enviar hacia tercero", response = Other.class) })
    public Response dGAPROF004(@ApiParam(value = "",required=true) @PathParam("requestId") String requestId, @ApiParam(value = "" ,required=true) Other body) {
        return delegate.dGAPROF004(requestId, body, securityContext);
    }

    @GET
    @Path("/form/request/profile/{requestId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Obtener todos los profiles definidos en las tablas (Todos menos en estado a borrar)", notes = "", response = Profile.class, responseContainer = "List", tags={ "request",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Listado de Objetos de Perfiles", response = Profile.class, responseContainer = "List") })
    public Response dGAPROFI001(@ApiParam(value = "",required=true) @PathParam("requestId") String requestId) {
        return delegate.dGAPROFI001(requestId, securityContext);
    }

    @POST
    @Path("/form/request/profile/{requestId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Crear una nueva solicitud de Profile", notes = "", response = Profile.class, tags={ "request",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Se confirma como almacenado", response = Profile.class) })
    public Response dGAPROFI002(@ApiParam(value = "",required=true) @PathParam("requestId") String requestId, @ApiParam(value = "Guardar un nuevo objeto profile" ,required=true) Profile body) {
        return delegate.dGAPROFI002(requestId, body, securityContext);
    }

    @DELETE
    @Path("/form/request/profile/{requestId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Marca como para borrar la solicitud", notes = "", response = Profile.class, tags={ "request",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Respuesta correcta de marcado para borrar", response = Profile.class) })
    public Response dGAPROFI003(@ApiParam(value = "",required=true) @PathParam("requestId") String requestId, @ApiParam(value = "" ,required=true) Profile body) {
        return delegate.dGAPROFI003(requestId, body, securityContext);
    }

    @OPTIONS
    @Path("/form/request/profile/{requestId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Realizar integracion para enviar hacia tercero", notes = "", response = Profile.class, tags={ "request",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Realizar integracion para enviar hacia tercero", response = Profile.class) })
    public Response dGAPROFI004(@ApiParam(value = "",required=true) @PathParam("requestId") String requestId, @ApiParam(value = "" ,required=true) Profile body) {
        return delegate.dGAPROFI004(requestId, body, securityContext);
    }

    @PATCH
    @Path("/form/request/profile/{requestId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Marcar como finalizado", notes = "", response = Profile.class, tags={ "request",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Marcar como finalizado", response = Profile.class) })
    public Response dGAPROFI005(@ApiParam(value = "",required=true) @PathParam("requestId") String requestId, @ApiParam(value = "" ,required=true) Profile body) {
        return delegate.dGAPROFI005(requestId, body, securityContext);
    }

    @GET
    @Path("/form/request/resource/{requestId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Obtener todos los resources definidos en las tablas (Todos menos en estado a borrar)", notes = "", response = Resource.class, responseContainer = "List", tags={ "request",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Listado de Objetos de Resources", response = Resource.class, responseContainer = "List") })
    public Response dGARESO001(@ApiParam(value = "",required=true) @PathParam("requestId") String requestId) {
        return delegate.dGARESO001(requestId, securityContext);
    }

    @POST
    @Path("/form/request/resource/{requestId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Crear una nueva solicitud de resource", notes = "", response = Resource.class, tags={ "request",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Se confirma como almacenado", response = Resource.class) })
    public Response dGARESO002(@ApiParam(value = "",required=true) @PathParam("requestId") String requestId, @ApiParam(value = "Guardar un nuevo objeto resource" ,required=true) Resource body) {
        return delegate.dGARESO002(requestId, body, securityContext);
    }

    @DELETE
    @Path("/form/request/resource/{requestId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Marca como para borrar la solicitud", notes = "", response = Resource.class, tags={ "request",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Respuesta correcta de marcado para borrar", response = Resource.class) })
    public Response dGARESO003(@ApiParam(value = "",required=true) @PathParam("requestId") String requestId, @ApiParam(value = "" ,required=true) Resource body) {
        return delegate.dGARESO003(requestId, body, securityContext);
    }

    @GET
    @Path("/form/request/system/{requestId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Obtener todos los sistemas definidos en las tablas (Todos menos en estado a borrar)", notes = "", response = System.class, responseContainer = "List", tags={ "request",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Listado de Objetos de sistemas", response = System.class, responseContainer = "List") })
    public Response dGASYST001(@ApiParam(value = "",required=true) @PathParam("requestId") String requestId) {
        return delegate.dGASYST001(requestId, securityContext);
    }

    @POST
    @Path("/form/request/system/{requestId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Crear una nueva solicitud de sistema", notes = "", response = System.class, tags={ "request",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Se confirma como almacenado", response = System.class) })
    public Response dGASYST002(@ApiParam(value = "",required=true) @PathParam("requestId") String requestId, @ApiParam(value = "Guardar un nuevo objeto sistema" ,required=true) System body) {
        return delegate.dGASYST002(requestId, body, securityContext);
    }

    @DELETE
    @Path("/form/request/system/{requestId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Marca como para borrar la solicitud", notes = "", response = System.class, tags={ "request",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Respuesta correcta de marcado para borrar", response = System.class) })
    public Response dGASYST003(@ApiParam(value = "",required=true) @PathParam("requestId") String requestId, @ApiParam(value = "" ,required=true) System body) {
        return delegate.dGASYST003(requestId, body, securityContext);
    }

    @DELETE
    @Path("/flow")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Actualizar Flujo (Rechaza el paso)", notes = "", response = Flow.class, tags={ "flow",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Devuelve el siguiente paso", response = Flow.class) })
    public Response fLOW0001(@ApiParam(value = "" ,required=true) Flow body) {
        return delegate.fLOW0001(body, securityContext);
    }

    @PUT
    @Path("/flow")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Actualizar Flujo (Aprobar el paso)", notes = "", response = Flow.class, tags={ "flow",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Devuelve el siguiente paso", response = Flow.class) })
    public Response fLOW0002(@ApiParam(value = "Objeto a guardar sin id" ,required=true) Flow body) {
        return delegate.fLOW0002(body, securityContext);
    }

    @GET
    @Path("/flow")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Obtiene el paso que es el activo", notes = "", response = Flow.class, tags={ "flow",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Devuelve el mismo objeto pero con ID", response = Flow.class) })
    public Response fLOW0003(@ApiParam(value = "Objeto a guardar sin id" ,required=true) Request body) {
        return delegate.fLOW0003(body, securityContext);
    }

    @POST
    @Path("/flow")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Crear flujo", notes = "", response = Flow.class, tags={ "flow",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Devuelve el siguiente paso", response = Flow.class) })
    public Response fLOW0004( @ApiParam(value = "")  @QueryParam("requestId") String requestId,  @ApiParam(value = "")  @QueryParam("processId") String processId,  @ApiParam(value = "")  @QueryParam("roleId") String roleId) {
        return delegate.fLOW0004(requestId, processId, roleId, securityContext);
    }

    @POST
    @Path("/form")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Crea un nuevo formulario (Solo la tabla principal)", notes = "", response = Form.class, tags={ "form",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Devuelve el mismo objeto pero con ID", response = Form.class) })
    public Response fORM0001(@ApiParam(value = "Objeto a guardar sin id" ,required=true) Form body) {
        return delegate.fORM0001(body, securityContext);
    }

    @PUT
    @Path("/form")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Actualizar formulario (Solo la tabla principal)", notes = "", response = Form.class, tags={ "form",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Devuelve el objeto como quedo", response = Form.class) })
    public Response fORM0002(@ApiParam(value = "" ,required=true) Form body) {
        return delegate.fORM0002(body, securityContext);
    }

    @PUT
    @Path("/form/request/{formId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Actualizar Solicitud (Solo la tabla principal)", notes = "", response = Request.class, tags={ "form",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Devuelve el objeto como quedo", response = Request.class) })
    public Response fORM0003(@ApiParam(value = "",required=true) @PathParam("formId") String formId, @ApiParam(value = "" ,required=true) Request body) {
        return delegate.fORM0003(formId, body, securityContext);
    }

    @POST
    @Path("/form/request/{formId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Crea una nueva Solcitud (Solo la tabla principal)", notes = "", response = Request.class, tags={ "form",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Devuelve el mismo objeto pero con ID", response = Request.class) })
    public Response fORM0004(@ApiParam(value = "",required=true) @PathParam("formId") String formId, @ApiParam(value = "Objeto a guardar sin id" ,required=true) Request body) {
        return delegate.fORM0004(formId, body, securityContext);
    }

    @GET
    @Path("/util/account/{requestId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Obtener cuentas que se utilizan en el formulario", notes = "Carga el listado de identidad", response = Account.class, responseContainer = "List", tags={ "util",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Listado de cuentas", response = Account.class, responseContainer = "List") })
    public Response iDE001(@ApiParam(value = "",required=true) @PathParam("requestId") String requestId) {
        return delegate.iDE001(requestId, securityContext);
    }

    @OPTIONS
    @Path("/util/account/{requestId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Listado de identidades", notes = "", response = Account.class, responseContainer = "List", tags={ "util",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Listado de cuentas", response = Account.class, responseContainer = "List") })
    public Response iDE002(@ApiParam(value = "",required=true) @PathParam("requestId") String requestId) {
        return delegate.iDE002(requestId, securityContext);
    }

    @POST
    @Path("/util/account/{requestId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Guardar la solicitud", notes = "", response = Account.class, tags={ "util",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Cuenta guardada", response = Account.class) })
    public Response iDE003(@ApiParam(value = "",required=true) @PathParam("requestId") String requestId, @ApiParam(value = "" ,required=true) Account body) {
        return delegate.iDE003(requestId, body, securityContext);
    }

    @DELETE
    @Path("/util/account/{requestId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Borrar cuenta", notes = "", response = Account.class, tags={ "util",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Cuenta borrada", response = Account.class) })
    public Response iDE004(@ApiParam(value = "",required=true) @PathParam("requestId") String requestId, @ApiParam(value = "" ,required=true) Account body) {
        return delegate.iDE004(requestId, body, securityContext);
    }

    @PUT
    @Path("/util/account/{requestId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Obtener cuentas disponibles dentro del recurso", notes = "", response = Account.class, responseContainer = "List", tags={ "util",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Listado de cuentas disponibles en recurso", response = Account.class, responseContainer = "List") })
    public Response iDE005(@ApiParam(value = "",required=true) @PathParam("requestId") String requestId,  @ApiParam(value = "")  @QueryParam("accountId") String accountId) {
        return delegate.iDE005(requestId, accountId, securityContext);
    }

    @GET
    @Path("/util/logs/{formId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Listado de eventos de logs", notes = "", response = EventLog.class, responseContainer = "List", tags={ "util",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Listado de eventos de formulario", response = EventLog.class, responseContainer = "List") })
    public Response lOG001(@ApiParam(value = "",required=true) @PathParam("formId") String formId) {
        return delegate.lOG001(formId, securityContext);
    }

    @POST
    @Path("/flow/login")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Login para verificar que es el usuario", notes = "", response = Login.class, tags={ "flow",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Login exitoso", response = Login.class) })
    public Response lOGIN0001(@ApiParam(value = "" ,required=true) Login body) {
        return delegate.lOGIN0001(body, securityContext);
    }

    @GET
    @Path("/form/request/user/load/{formType}/{requestType}/{document}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Carga la información de RUC del usuario y sus datos en OIM", notes = "", response = Person.class, tags={ "request",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Carga la información de RUC del usuario y sus datos en OIM", response = Person.class),
        @ApiResponse(code = 404, message = "No se encontro el usuario", response = String.class),
        @ApiResponse(code = 406, message = "El usuario ya esta creado", response = String.class),
        @ApiResponse(code = 412, message = "No tiene los datos creados de forma correcta: No esta creado en OIM No esta creado en AFPA (Externos)", response = Void.class) })
    public Response rEQ0001(@ApiParam(value = "",required=true) @PathParam("formType") String formType, @ApiParam(value = "",required=true) @PathParam("requestType") String requestType, @ApiParam(value = "",required=true) @PathParam("document") String document) {
        return delegate.rEQ0001(formType, requestType, document, securityContext);
    }

    @GET
    @Path("/form/request/applicant/{formType}/{document}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Carga la información de RUC del usuario y sus datos en OIM", notes = "", response = Applicant.class, tags={ "request",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Carga la información de RUC del usuario y sus datos en OIM", response = Applicant.class),
        @ApiResponse(code = 404, message = "No se encontro el usuario", response = String.class),
        @ApiResponse(code = 406, message = "El usuario ya esta creado", response = String.class),
        @ApiResponse(code = 412, message = "No tiene los datos creados de forma correcta: No esta creado en OIM No esta creado en AFPA (Externos)", response = Void.class) })
    public Response rEQ0002(@ApiParam(value = "",required=true) @PathParam("formType") String formType, @ApiParam(value = "",required=true) @PathParam("document") String document) {
        return delegate.rEQ0002(formType, document, securityContext);
    }

    @GET
    @Path("/flow/email")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Envia el email segun el paso", notes = "", response = Void.class, tags={ "util",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Correo enviado exitosamente", response = Void.class) })
    public Response uTIL0002( @ApiParam(value = "")  @QueryParam("requestId") String requestId) {
        return delegate.uTIL0002(requestId, securityContext);
    }

    @POST
    @Path("/util/email")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Envio de correo electronico", notes = "", response = Void.class, tags={ "util" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Se deposito en la cola de API OIM para su envio", response = Void.class) })
    public Response uTIL001(@ApiParam(value = "" ,required=true) Email body) {
        return delegate.uTIL001(body, securityContext);
    }
}
