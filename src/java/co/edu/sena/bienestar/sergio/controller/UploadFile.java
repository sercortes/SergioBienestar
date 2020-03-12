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
import co.edu.sena.bienestar.sergio.dto.Actividades;
import co.edu.sena.bienestar.sergio.dto.ActividadesAprendiz;
import co.edu.sena.bienestar.sergio.dto.Aprendiz;
import co.edu.sena.bienestar.sergio.util.readXls;
import com.google.gson.Gson;
import java.io.IOException;
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
            ActividadDAO actividadDAO = new ActividadDAO(conexion);
            AprendizDAO aprendizDAO = new AprendizDAO(conexion);
            AprendizActividadDAO aprendizActividadDAO = new AprendizActividadDAO(conexion);
            
            // creación de variables para el objeto actividadesAprendiz
            int idActividad = 0;
            String idAprendiz = "";
            
            // instancia de objeto de tabla intermedia
            ActividadesAprendiz actividadesAprendiz = new ActividadesAprendiz();

            // lectura de la lista de objetos del archivo
            for (Aprendiz acti : lista) {

                // comprobando que no existan objetos nulos
                if (acti.getDocumento_aprendiz() == null) {
                    throw new Exception();
                }

                // inserción del aprendiz en la bd
                idAprendiz = aprendizDAO.insertReturn(acti);
                
                // bandera para verificar si la actividad existe
                Actividades activi = actividadDAO.getIdActividad(acti.getActividades());
                
                //comprobación del id actividad
                if (activi.getIdRealActividad() == 0) {
                    // si no existe, se inserta la actividad
                    idActividad = actividadDAO.insertReturn(acti.getActividades());
                }else{
                    // si existe, solo se trae el id pero no se agrega de nuevo
                    idActividad = activi.getIdRealActividad();
                }
               
                // asignando el valor del idactividad y idaprendiz
                actividadesAprendiz.setCodActividad(idActividad);
                actividadesAprendiz.setCodAprendiz(idAprendiz);
                
                // insertando en la tabla intermedia
                aprendizActividadDAO.insertReturn(actividadesAprendiz);

            }
            
            // imprimiendo el tamaño de la lista insertada
            new Gson().toJson(lista.size()+ " :D", response.getWriter());
            // desconectando bd
            conexion.disconnectDb();
            
    } // cierre del método proccessRequest

 
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