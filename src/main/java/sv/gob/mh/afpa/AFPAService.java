/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mh.afpa;

import io.swagger.model.CatalogTypeAFPA;
import io.swagger.model.Person;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import sv.gob.aduana.entity.CatTypeafpa;
import sv.gob.aduana.mtto.bean.CatTypeafpaFacade;
import sv.gob.aduana.mtto.bean.EventLogFacade;
import sv.gob.aduana.util.ClientDGII;
import sv.gob.mh.oim.pojo.AuthDGII;
import sv.gob.mh.oim.pojo.DataRequest;
import sv.gob.mh.oim.pojo.TokenDGII;
import sv.gob.mh.oim.pojo.UsuarioDGIIRequest;
import sv.gob.mh.oim.pojo.UsuarioDGIIResponse;

/**
 *
 * @author Datum-Redsoft
 */
@Stateless
public class AFPAService {

    @EJB
    protected EventLogFacade eventLogFacade;

    @EJB
    protected CatTypeafpaFacade catTypeafpaFacade;
    
    private ClientDGII clientDgii;

    public AFPAService() {
        clientDgii = new ClientDGII();
    }

    public Person getAFPA(String document, Person person, String code) {
        Person personAFPA = loadAFPA(document, code);

        person.setApproveAFPA(personAFPA.getApproveAFPA());
        person.setCodeDeclarant(personAFPA.getCodeDeclarant());
        person.setIsAFPA(personAFPA.isIsAFPA());
        person.setResolution(personAFPA.getResolution());
        person.setTypeAFPA(personAFPA.getTypeAFPA());

        return person;
    }

    public Person loadAFPA(String document, String code) {
        CatTypeafpa catTypeafpa = null;

        if (!code.isEmpty()) {
            catTypeafpa = catTypeafpaFacade.find(code);
        }

        return loadAFPA(document, catTypeafpa);
    }

    public HashSet<CatTypeafpa> loadTypesAFPA(String document) {
        HashSet<CatTypeafpa> afpaUniqueList = new HashSet<>();

        List<CatTypeafpa> afpaList = loadTypeAuxiliares(document);

        afpaList.forEach((c) -> {
            afpaUniqueList.add(c);
        });

        afpaList = loadTypeTransportista(document);

        afpaList.forEach((c) -> {
            afpaUniqueList.add(c);
        });

        /*
        afpaList = loadTypeCourier(document);
        
        afpaList.forEach((c) -> {
            afpaUniqueList.add(c);
        });
        
         
        afpaList = loadTypeAsistente(document);
        
        afpaList.forEach((c) -> {
            afpaUniqueList.add(c);
        });
         */
        return afpaUniqueList;
    }

    public Person loadAFPA(String document, CatTypeafpa afpa) {
        Person p = loadAFPAAuxiliares(document, afpa);

        if (p != null) {
            return p;
        }
        /*
        p = loadAFPAAsistente(document, afpa);

        if (p != null) {
            return p;
        }

        p = loadAFPCourier(document, afpa);

        if (p != null) {
            return p;
        }*/

        p = loadAFPATransportistas(document, afpa);

        if (p != null) {
            return p;
        }

        return new Person();
    }

    public boolean existUser(String document, String code) {
        CatTypeafpa catTypeafpa = null;

        if (!code.isEmpty()) {
            catTypeafpa = catTypeafpaFacade.find(code);
        }

        return existUser(document, catTypeafpa);
    }

    public boolean existUser(String document, CatTypeafpa afpa) {
        //  printAllTypes();
        Person p = loadAFPAAuxiliares(document, afpa);

        if (p != null) {
            return true;
        }
        /*
        p = loadAFPAAsistente(document, afpa);

        if (p != null) {
            return true;
        }

        p = loadAFPCourier(document, afpa);

        if (p != null) {
            return true;
        }
         */
        p = loadAFPATransportistas(document, afpa);

        if (p != null) {
            return true;
        }

        return false;
    }

    private Person loadAFPAAuxiliares(String document, CatTypeafpa catTypeAfpa) {
        Person p = null;

        //Conectar a base de datos
        Context ctx = null;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String resolution = null, nit = null, state = null, nrc = null, typeAFPA = null;
        Date resolutionDate;
        HashMap<String, List<String>> roles = new HashMap<>();

        try {

            if (catTypeAfpa == null) {
                return p;
            }
            
            if(document.length() == 8 || document.length() == 9){
                UsuarioDGIIResponse dgii = new UsuarioDGIIResponse();
                dgii = clientDgii.uTIL0010(document);
                document = dgii.getData().getsNit();
            }

            eventLogFacade.writeInLog("DEBUG", "Iniciando AFPA Auxiliar: " + document + " " + catTypeAfpa.getName());

            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/dgaFormAFPA");
            //Ejecutar conmsulta de busqueda login
            con = ds.getConnection();

            String sqlJust
                    = " SELECT \n"
                    + " AU.RESOLUCION,AU.AUX_NIT,AU.FECHA_RESOLUCION,AU.ESTADO,AU.CODIGO,AU.TIPO_APODERADO,AT.DESC_TIPO \n"
                    + " FROM AUXFUNPUB.AUXILIARES AU"
                    + " INNER JOIN  AUXFUNPUB.AUX_TIPOS AT ON AU.COD_TIPO=AT.COD_TIPO "
                    + " WHERE  AU.VERSION = 0 and AU.aux_nit = ? and AU.estado = 'ACTIVO'";

            stmt = con.prepareStatement(sqlJust);

            stmt.setString(1, document);

            rs = stmt.executeQuery();

            while (rs.next()) {

                p = new Person();

                p.setApproveAFPA(String.valueOf(rs.getDate("FECHA_RESOLUCION").getTime()));
                p.setCodeDeclarant(rs.getString("CODIGO"));
                p.setIsAFPA(true);
                p.setResolution(rs.getString("RESOLUCION"));

                CatalogTypeAFPA catalogTypeAFPA = new CatalogTypeAFPA();

                catalogTypeAFPA.setId(rs.getString("DESC_TIPO"));
                catalogTypeAFPA.setValue(rs.getString("DESC_TIPO"));
                catalogTypeAFPA.setStatus("ENABLED");

                CatTypeafpa c = catTypeafpaFacade.getCatalogTypeAFPAByName(
                        catalogTypeAFPA.getValue(), p.getCodeDeclarant());

                if (catTypeAfpa != null) {
                    eventLogFacade.writeInLog("DEBUG", "Cargando de AFPA Auxiliar: " + c.getId() + " - " + catTypeAfpa.getId() + " - " + catalogTypeAFPA.getId());

                    if (c == null || c.getId() == null) {
                        p = null;
                        continue;
                    }

                    if (!c.getId().equals(catTypeAfpa.getId())) {
                        p = null;
                        continue;
                    }

                }

                eventLogFacade.writeInLog("DEBUG", "Asignado de AFPA Auxiliar: " + p.getCodeDeclarant() + " " + p.getResolution() + " " + catalogTypeAFPA.getId());
                /*
                System.out.println("FECHA_RESOLUCION: "+p.getApproveAFPA());
                System.out.println("CODIGO: "+p.getCodeDeclarant());
                System.out.println("RESOLUCION: "+p.getResolution());
                System.out.println("DESC_TIPO: "+catalogTypeAFPA.getValue());
                 */

                p.setTypeAFPA(catalogTypeAFPA);

                break;
            }

            rs.close();
            stmt.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return p;
    }

    private Person loadAFPCourier(String document, CatTypeafpa catTypeAfpa) {
        Person p = null;

        //Conectar a base de datos
        Context ctx = null;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String resolution = null, nit = null, state = null, nrc = null, typeAFPA = null;
        Date resolutionDate;
        HashMap<String, List<String>> roles = new HashMap<>();

        try {

            if (catTypeAfpa == null) {
                return p;
            }

            eventLogFacade.writeInLog("DEBUG", "Courier AFPA Auxiliar: " + document + " " + catTypeAfpa.getName());

            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/dgaFormAFPA");
            //Ejecutar conmsulta de busqueda login
            con = ds.getConnection();

            String sqlJust
                    = " SELECT \n"
                    + " AU.RESOLUCION,AU.NIT,AU.FECHA_RESOLUCION,AU.ESTADO,AU.CODIGO,'Courier' AS DESC_TIPO \n"
                    + " FROM AUXFUNPUB.AUX_ASISTENTES_COURIER AU"
                    + " WHERE  AU.VERSION = 0 and AU.NIT = ? and AU.estado = 'ACTIVO'";

            stmt = con.prepareStatement(sqlJust);

            stmt.setString(1, document);

            rs = stmt.executeQuery();

            while (rs.next()) {

                p = new Person();

                p.setApproveAFPA(String.valueOf(rs.getDate("FECHA_RESOLUCION").getTime()));
                p.setCodeDeclarant(rs.getString("CODIGO"));
                p.setIsAFPA(true);
                p.setResolution(rs.getString("RESOLUCION"));

                CatalogTypeAFPA catalogTypeAFPA = new CatalogTypeAFPA();

                catalogTypeAFPA.setId(rs.getString("DESC_TIPO"));
                catalogTypeAFPA.setValue(rs.getString("DESC_TIPO"));
                catalogTypeAFPA.setStatus("ENABLED");

                CatTypeafpa c = catTypeafpaFacade.getCatalogTypeAFPAByName(catalogTypeAFPA.getValue(), p.getCodeDeclarant());

                if (catTypeAfpa != null) {
                    eventLogFacade.writeInLog("DEBUG", "Cargando de AFPA Courier: " + c.getId() + " " + catTypeAfpa.getId());

                    if (c == null || c.getId() == null) {
                        p = null;
                        continue;
                    }

                    if (!c.getId().equals(catTypeAfpa.getId())) {
                        p = null;
                        continue;
                    }

                }

                eventLogFacade.writeInLog("DEBUG", "Asignado de AFPA Courier: " + p.getCodeDeclarant() + " " + p.getResolution() + " " + catalogTypeAFPA.getId());

                /*
                System.out.println("FECHA_RESOLUCION: "+p.getApproveAFPA());
                System.out.println("CODIGO: "+p.getCodeDeclarant());
                System.out.println("RESOLUCION: "+p.getResolution());
                System.out.println("DESC_TIPO: "+catalogTypeAFPA.getValue());
                 */
                p.setTypeAFPA(catalogTypeAFPA);

                break;

            }

            rs.close();
            stmt.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return p;
    }

    private Person loadAFPATransportistas(String document, CatTypeafpa catTypeAfpa) {
        Person p = null;

        //Conectar a base de datos
        Context ctx = null;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String resolution = null, nit = null, state = null, nrc = null, typeAFPA = null;
        Date resolutionDate;
        HashMap<String, List<String>> roles = new HashMap<>();

        try {

            if (catTypeAfpa == null) {
                return p;
            }
            
            if(document.length() == 8 || document.length() == 9){
                UsuarioDGIIResponse dgii = new UsuarioDGIIResponse();
                dgii = clientDgii.uTIL0010(document);
                document = dgii.getData().getsNit();
            }

            eventLogFacade.writeInLog("DEBUG", "Iniciando AFPA Transportista: " + document + " " + catTypeAfpa.getName());

            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/dgaFormAFPA");
            //Ejecutar conmsulta de busqueda login
            con = ds.getConnection();

            String sqlJust
                    = " SELECT \n"
                    + " AU.SIRT_RESOLUCION as RESOLUCION,AU.SIRT_NIT , AU.SIRT_FECHA_RESOLUCION as FECHA_RESOLUCION,"
                    + " AU.SIRT_ESTADO_REG,AU.SIRT_KEY_CODIGO_EMPRESAS as CODIGO,'Transportistas de Carga Internacional' as  DESC_TIPO \n"
                    + " FROM AUXFUNPUB.SIRT_EMPRESAS_AUTORIZAR AU"
                    + " WHERE  AU.SIRT_KEY_VERSION = 0 and AU.SIRT_NIT = ? and AU.SIRT_ESTADO_REG = 'ACTIVO'";

            stmt = con.prepareStatement(sqlJust);

            stmt.setString(1, document);

            rs = stmt.executeQuery();

            while (rs.next()) {

                p = new Person();

                try {
                    p.setApproveAFPA(String.valueOf(rs.getDate("FECHA_RESOLUCION").getTime()));
                } catch (Exception exception) {
                      p.setApproveAFPA(null);
                }
                
                p.setCodeDeclarant(rs.getString("CODIGO"));
                p.setIsAFPA(true);
                p.setResolution(rs.getString("RESOLUCION"));

                CatalogTypeAFPA catalogTypeAFPA = new CatalogTypeAFPA();

                catalogTypeAFPA.setId(rs.getString("DESC_TIPO"));
                catalogTypeAFPA.setValue(rs.getString("DESC_TIPO"));
                catalogTypeAFPA.setStatus("ENABLED");

                CatTypeafpa c = catTypeafpaFacade.getCatalogTypeAFPAByName(catalogTypeAFPA.getValue(), p.getCodeDeclarant());

                if (catTypeAfpa != null) {
                    eventLogFacade.writeInLog("DEBUG", "Cargando  AFPA Transportista: " + c.getId() + "  - " + catTypeAfpa.getId());

                    if (c == null || c.getId() == null) {
                        p = null;
                        continue;
                    }

                    if (!c.getId().equals(catTypeAfpa.getId())) {
                        p = null;
                        continue;
                    }

                }

                eventLogFacade.writeInLog("DEBUG", "Asignado de AFPA Transportista: " + p.getCodeDeclarant() + " " + p.getResolution() + " " + catalogTypeAFPA.getId());
                /*
                System.out.println("FECHA_RESOLUCION: "+p.getApproveAFPA());
                System.out.println("CODIGO: "+p.getCodeDeclarant());
                System.out.println("RESOLUCION: "+p.getResolution());
                System.out.println("DESC_TIPO: "+catalogTypeAFPA.getValue());
                 */

                p.setTypeAFPA(catalogTypeAFPA);

                break;
            }

            rs.close();
            stmt.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return p;
    }

    private Person loadAFPAAsistente(String document, CatTypeafpa catTypeAfpa) {
        Person p = null;

        //Conectar a base de datos
        Context ctx = null;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String resolution = null, nit = null, state = null, nrc = null, typeAFPA = null;
        Date resolutionDate;
        HashMap<String, List<String>> roles = new HashMap<>();

        try {

            if (catTypeAfpa == null) {
                return p;
            }

            eventLogFacade.writeInLog("DEBUG", "Iniciando AFPA Asistente: " + document + " " + catTypeAfpa.getName());

            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/dgaFormAFPA");
            //Ejecutar conmsulta de busqueda login
            con = ds.getConnection();

            String sqlJust
                    = " SELECT \n"
                    + " AU.RESOLUCION,AU.AUX_ASIS_NIT,AU.FECHA_RESOLUCION,AU.ESTADO,AU.CODIGO,AT.DESC_TIPO \n"
                    + " FROM AUXFUNPUB.AUX_ASISTENTES AU"
                    + " INNER JOIN  AUXFUNPUB.AUX_TIPOS AT ON AU.COD_TIPO_DEP=AT.COD_TIPO "
                    + " WHERE  AU.VERSION_DEP = 0 and AU.AUX_ASIS_NIT = ? and AU.estado = 'ACTIVO'";

            stmt = con.prepareStatement(sqlJust);

            stmt.setString(1, document);

            rs = stmt.executeQuery();

            while (rs.next()) {

                p = new Person();

                p.setApproveAFPA(String.valueOf(rs.getDate("FECHA_RESOLUCION").getTime()));
                p.setCodeDeclarant(rs.getString("CODIGO"));
                p.setIsAFPA(true);
                p.setResolution(rs.getString("RESOLUCION"));

                CatalogTypeAFPA catalogTypeAFPA = new CatalogTypeAFPA();

                catalogTypeAFPA.setId(rs.getString("DESC_TIPO"));
                catalogTypeAFPA.setValue(rs.getString("DESC_TIPO"));
                catalogTypeAFPA.setStatus("ENABLED");

                CatTypeafpa c = catTypeafpaFacade.getCatalogTypeAFPAByName(catalogTypeAFPA.getValue(), p.getCodeDeclarant());

                if (c == null || c.getId() == null) {
                    p = null;
                    continue;
                }

                if (catTypeAfpa != null) {
                    eventLogFacade.writeInLog("DEBUG", "Cargando de AFPA Asistente: " + c.getId() + " " + catTypeAfpa.getId() + " " + catalogTypeAFPA.getId());

                    if (c == null || c.getId() == null) {
                        p = null;
                        continue;
                    }

                    if (!c.getId().equals(catTypeAfpa.getId())) {
                        p = null;
                        continue;
                    }

                }

                eventLogFacade.writeInLog("DEBUG", "Asignado de AFPA Asistente: " + p.getCodeDeclarant() + " " + p.getResolution() + " " + catalogTypeAFPA.getId());
                /*
                System.out.println("FECHA_RESOLUCION: "+p.getApproveAFPA());
                System.out.println("CODIGO: "+p.getCodeDeclarant());
                System.out.println("RESOLUCION: "+p.getResolution());
                System.out.println("DESC_TIPO: "+catalogTypeAFPA.getValue());
                 */

                p.setTypeAFPA(catalogTypeAFPA);

                break;

            }

            rs.close();
            stmt.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return p;
    }

    private List<CatTypeafpa> loadTypeAuxiliares(String document) {
        List<CatTypeafpa> afpaList = new ArrayList<>();

        //Conectar a base de datos
        Context ctx = null;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String codDeclarante;
        HashMap<String, List<String>> roles = new HashMap<>();

        try {
            
            if(document.length() == 8 || document.length() == 9){
                UsuarioDGIIResponse dgii = new UsuarioDGIIResponse();
                dgii = clientDgii.uTIL0010(document);
                document = dgii.getData().getsNit();
            }

            eventLogFacade.writeInLog("DEBUG", "Iniciando AFPA Auxiliar: " + document);

            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/dgaFormAFPA");
            //Ejecutar conmsulta de busqueda login
            con = ds.getConnection();

            String sqlJust
                    = " SELECT \n"
                    + " AU.RESOLUCION,AU.AUX_NIT,AU.FECHA_RESOLUCION,AU.ESTADO,AU.CODIGO,AU.TIPO_APODERADO,AT.DESC_TIPO \n"
                    + " FROM AUXFUNPUB.AUXILIARES AU"
                    + " INNER JOIN  AUXFUNPUB.AUX_TIPOS AT ON AU.COD_TIPO=AT.COD_TIPO "
                    + " WHERE  AU.VERSION = 0 and AU.aux_nit = ? and AU.estado = 'ACTIVO'";

            stmt = con.prepareStatement(sqlJust);

            stmt.setString(1, document);

            rs = stmt.executeQuery();

            while (rs.next()) {

                codDeclarante = rs.getString("CODIGO");

                CatalogTypeAFPA catalogTypeAFPA = new CatalogTypeAFPA();

                catalogTypeAFPA.setId(rs.getString("DESC_TIPO"));
                catalogTypeAFPA.setValue(rs.getString("DESC_TIPO"));
                catalogTypeAFPA.setStatus("ENABLED");

                CatTypeafpa c = catTypeafpaFacade.getCatalogTypeAFPAByName(
                        catalogTypeAFPA.getValue(), codDeclarante);

                eventLogFacade.writeInLog("DEBUG", "Cargando de AFPA Auxiliar: " + c.getId() + " - " + codDeclarante + " - " + catalogTypeAFPA.getId());

                if (c == null || c.getId() == null) {
                    continue;
                }

                afpaList.add(c);

            }

            rs.close();
            stmt.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return afpaList;
    }

    private List<CatTypeafpa> loadTypeCourier(String document) {
        List<CatTypeafpa> afpaList = new ArrayList<>();

        //Conectar a base de datos
        Context ctx = null;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String codDeclarante = null;
        Date resolutionDate;
        HashMap<String, List<String>> roles = new HashMap<>();

        try {

            eventLogFacade.writeInLog("DEBUG", "Iniciando AFPA Courier: " + document);

            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/dgaFormAFPA");
            //Ejecutar conmsulta de busqueda login
            con = ds.getConnection();

            String sqlJust
                    = " SELECT \n"
                    + " AU.RESOLUCION,AU.NIT,AU.FECHA_RESOLUCION,AU.ESTADO,AU.CODIGO,'Courier' AS DESC_TIPO \n"
                    + " FROM AUXFUNPUB.AUX_ASISTENTES_COURIER AU"
                    + " WHERE  AU.VERSION = 0 and AU.NIT = ? and AU.estado = 'ACTIVO'";

            stmt = con.prepareStatement(sqlJust);

            stmt.setString(1, document);

            rs = stmt.executeQuery();

            while (rs.next()) {

                codDeclarante = rs.getString("CODIGO");

                CatalogTypeAFPA catalogTypeAFPA = new CatalogTypeAFPA();

                catalogTypeAFPA.setId(rs.getString("DESC_TIPO"));
                catalogTypeAFPA.setValue(rs.getString("DESC_TIPO"));
                catalogTypeAFPA.setStatus("ENABLED");

                CatTypeafpa c = catTypeafpaFacade.getCatalogTypeAFPAByName(catalogTypeAFPA.getValue(), codDeclarante);

                if (c == null || c.getId() == null) {
                    continue;
                }

                eventLogFacade.writeInLog("DEBUG", "Asignado de AFPA Courier: " + codDeclarante + " " + c.getName() + " " + catalogTypeAFPA.getId());

                afpaList.add(c);

            }

            rs.close();
            stmt.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return afpaList;
    }

    private List<CatTypeafpa> loadTypeTransportista(String document) {
        List<CatTypeafpa> afpaList = new ArrayList<>();

        //Conectar a base de datos
        Context ctx = null;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String codDeclarante = null;
        HashMap<String, List<String>> roles = new HashMap<>();

        try {
            
            if(document.length() == 8 || document.length() == 9){
                UsuarioDGIIResponse dgii = new UsuarioDGIIResponse();
                dgii = clientDgii.uTIL0010(document);
                document = dgii.getData().getsNit();
            }

            eventLogFacade.writeInLog("DEBUG", "Iniciando AFPA Transportista: " + document);

            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/dgaFormAFPA");
            //Ejecutar conmsulta de busqueda login
            con = ds.getConnection();

            String sqlJust
                    = " SELECT \n"
                    + " AU.SIRT_RESOLUCION as RESOLUCION,AU.SIRT_NIT , AU.SIRT_FECHA_RESOLUCION as FECHA_RESOLUCION,"
                    + " AU.SIRT_ESTADO_REG,AU.SIRT_KEY_CODIGO_EMPRESAS as CODIGO,'Transportistas de Carga Internacional' as  DESC_TIPO \n"
                    + " FROM AUXFUNPUB.SIRT_EMPRESAS_AUTORIZAR AU"
                    + " WHERE  AU.SIRT_KEY_VERSION = 0 and AU.SIRT_NIT = ? and AU.SIRT_ESTADO_REG = 'ACTIVO'";

            stmt = con.prepareStatement(sqlJust);

            stmt.setString(1, document);

            rs = stmt.executeQuery();

            while (rs.next()) {

                codDeclarante = rs.getString("CODIGO");

                CatalogTypeAFPA catalogTypeAFPA = new CatalogTypeAFPA();

                catalogTypeAFPA.setId(rs.getString("DESC_TIPO"));
                catalogTypeAFPA.setValue(rs.getString("DESC_TIPO"));
                catalogTypeAFPA.setStatus("ENABLED");

                CatTypeafpa c = catTypeafpaFacade.getCatalogTypeAFPAByName(catalogTypeAFPA.getValue(), codDeclarante);

                eventLogFacade.writeInLog("DEBUG", "Cargando de AFPA Transportista: " + c.getId());

                if (c == null || c.getId() == null) {
                    continue;
                }

                afpaList.add(c);

            }

            rs.close();
            stmt.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return afpaList;
    }

    private List<CatTypeafpa> loadTypeAsistente(String document) {
        List<CatTypeafpa> afpaList = new ArrayList<>();

        //Conectar a base de datos
        Context ctx = null;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String codDeclarante = null;
        HashMap<String, List<String>> roles = new HashMap<>();

        try {

            eventLogFacade.writeInLog("DEBUG", "Iniciando AFPA Asistente: " + document);

            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/dgaFormAFPA");
            //Ejecutar conmsulta de busqueda login
            con = ds.getConnection();

            String sqlJust
                    = " SELECT \n"
                    + " AU.RESOLUCION,AU.AUX_ASIS_NIT,AU.FECHA_RESOLUCION,AU.ESTADO,AU.CODIGO,AT.DESC_TIPO \n"
                    + " FROM AUXFUNPUB.AUX_ASISTENTES AU"
                    + " INNER JOIN  AUXFUNPUB.AUX_TIPOS AT ON AU.COD_TIPO_DEP=AT.COD_TIPO "
                    + " WHERE  AU.VERSION_DEP = 0 and AU.AUX_ASIS_NIT = ? and AU.estado = 'ACTIVO'";

            stmt = con.prepareStatement(sqlJust);

            stmt.setString(1, document);

            rs = stmt.executeQuery();

            while (rs.next()) {
                codDeclarante = rs.getString("CODIGO");

                CatalogTypeAFPA catalogTypeAFPA = new CatalogTypeAFPA();

                catalogTypeAFPA.setId(rs.getString("DESC_TIPO"));
                catalogTypeAFPA.setValue(rs.getString("DESC_TIPO"));
                catalogTypeAFPA.setStatus("ENABLED");

                CatTypeafpa c = catTypeafpaFacade.getCatalogTypeAFPAByName(catalogTypeAFPA.getValue(), codDeclarante);

                if (c == null || c.getId() == null) {
                    continue;
                }

                eventLogFacade.writeInLog("DEBUG", "Cargando de AFPA Asistente: " + c.getId() + " " + catalogTypeAFPA.getId());

                afpaList.add(c);

            }

            rs.close();
            stmt.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return afpaList;
    }

    private void printAllTypes() {
        Person p = new Person();

        //Conectar a base de datos
        Context ctx = null;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String resolution = null, nit = null, state = null, nrc = null, typeAFPA = null;
        Date resolutionDate;
        HashMap<String, List<String>> roles = new HashMap<>();

        try {

            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/dgaFormAFPA");
            //Ejecutar conmsulta de busqueda login
            con = ds.getConnection();

            String sqlJust
                    = " SELECT DESC_TIPO, COD_TIPO FROM AUXFUNPUB.AUX_TIPOS";

            stmt = con.prepareStatement(sqlJust);

            rs = stmt.executeQuery();

            while (rs.next()) {
                resolution = rs.getString("DESC_TIPO");
                nit = rs.getString("COD_TIPO");

            }

            rs.close();
            stmt.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return;
    }


}
