/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.bienestar.sergio.controller;

import co.edu.sena.bienestar.sergio.dao.ActividadDAO;
import co.edu.sena.bienestar.sergio.dao.AprendizActividadDAO;
import co.edu.sena.bienestar.sergio.dao.AprendizDAO;
import co.edu.sena.bienestar.sergio.dao.Conexion;
import co.edu.sena.bienestar.sergio.dao.LogMigrationDAO;
import co.edu.sena.bienestar.sergio.dto.Actividades;
import co.edu.sena.bienestar.sergio.dto.ActividadesAprendiz;
import co.edu.sena.bienestar.sergio.dto.Aprendiz;
import co.edu.sena.bienestar.sergio.dto.LogMigration;
import co.edu.sena.bienestar.sergio.dto.Usuario;
import co.edu.sena.bienestar.sergio.util.readXls;
import com.google.gson.Gson;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;

/**
 *
 * @author serfin
 */
@MultipartConfig
public class UploadFile extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {

        response.setContentType("text/html;charset=UTF-8");

        // obteniendo el archivo del formulario
        Part file = request.getPart("fileToUpload");

        readXls read = new readXls();

        // método para leer xls
        ArrayList<Aprendiz> lista = read.readingXls(file);

        // instancia de conexion y los DAO para la inserción
        Conexion conexion = new Conexion();
        Connection cone = conexion.getConnection();

        // desactivando en autocommit
        if (cone.getAutoCommit()) {
            cone.setAutoCommit(false);
        }
        ActividadDAO actividadDAO = new ActividadDAO(cone);
        AprendizDAO aprendizDAO = new AprendizDAO(cone);
        AprendizActividadDAO aprendizActividadDAO = new AprendizActividadDAO(cone);

        // creación de variables para el objeto actividadesAprendiz
        int idActividad = 0;
        int idAprendiz = 0;
        int contadorRegistros = 0;

        // instancia de objeto de tabla intermedia
        ActividadesAprendiz actividadesAprendiz = new ActividadesAprendiz();

        //objetos bandera
        Actividades activi;
        Aprendiz apren;

        // lectura de la lista de objetos del archivo
        for (Aprendiz acti : lista) {

            // comprobando que no existan objetos nulos
            if (acti.getDocumento_aprendiz() == null) {
                throw new Exception();
            }

            // inserción del aprendiz en la bd
            // bandera para verificar la existencia del aprendiz
            apren = aprendizDAO.getIdAprendiz(acti);

            try {

                //si el aprendiz no existe se crea uno nuevo
                if (apren.getId_aprendiz() == 0) {
                    // inserción del aprendiz
                    idAprendiz = aprendizDAO.insertReturn(acti);
                    contadorRegistros++;
                } else {
                    // de lo contrario se trae el id de la consulta
                    idAprendiz = apren.getId_aprendiz();
                }

                // bandera para verificar si la actividad existe
                activi = actividadDAO.getIdActividad(acti.getActividades());

                //comprobación del id actividad
                if (activi.getIdRealActividad() == 0) {
                    // si no existe, se inserta la actividad
                    idActividad = actividadDAO.insertReturn(acti.getActividades());
                    contadorRegistros++;
                } else {
                    // si existe, solo se trae el id pero no se agrega de nuevo
                    idActividad = activi.getIdRealActividad();
                }

                // asignando el valor del idactividad y idaprendiz
                actividadesAprendiz.setCodActividad(idActividad);
                actividadesAprendiz.setCodAprendiz(idAprendiz);

                // insertando en la tabla intermedia
                aprendizActividadDAO.insertReturn(actividadesAprendiz);

                // guardando todos lo cambios en la bd
                cone.commit();
            } catch (SQLException sql) {
                System.out.println(sql);
                try {
                    // revertir cambios en la bd
                    cone.rollback();
                } catch (SQLException sq) {
                    System.out.println(sq);
                }
            }

        }

        // imprimiendo el tamaño de la lista insertada
        new Gson().toJson(lista.size() + " :D", response.getWriter());
        
        // validación para la tabla log
        if (lista.size() > 0) {
            // para la tabla log
            contadorRegistros = contadorRegistros+lista.size();
            generateLog(request, contadorRegistros);
        }
        
        // cerrando conexión y otros componentes
        actividadDAO.CloseAll();
        aprendizDAO.CloseAll();
        aprendizActividadDAO.CloseAll();

    } // cierre del método proccessRequest

    // método para insertar en la tabla log
    public void generateLog(HttpServletRequest request, int cont){
             Usuario usuario = (Usuario) request.getSession().getAttribute("USER");
            LogMigration logMigration = new LogMigration(usuario.getEmail(), cont+"");
            Conexion connTwo = new Conexion();
            LogMigrationDAO logMigrationDAO = new LogMigrationDAO(connTwo.getConnection());
            logMigrationDAO.insertReturn(logMigration);
            logMigrationDAO.CloseAll();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(UploadFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(UploadFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
