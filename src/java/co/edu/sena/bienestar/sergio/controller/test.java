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
import co.edu.sena.bienestar.sergio.util.returnString;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

import javax.servlet.annotation.MultipartConfig;

/**
 *
 * @author serfin
 */
@MultipartConfig
public class test extends HttpServlet {

   

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        
        response.setContentType("text/html;charset=UTF-8");

        // obteniendo el archivo del formulario
        Part file = request.getPart("fileToUpload");

        // convitiendo el archivo a fileInputStream
        FileInputStream fi1 = new FileInputStream(new File(returnString.generateUrl(file.toString())));

        // instancia de los objetos para poder leer archivos excel
        HSSFWorkbook wb1 = new HSSFWorkbook(fi1);
        HSSFSheet sheet1 = wb1.getSheetAt(0);
        FormulaEvaluator formulaEvaluator = wb1.getCreationHelper().createFormulaEvaluator();
        
        //instancia de la lista para ser llenada de objetos aprendiz y actividades
        ArrayList<Aprendiz> lista = new ArrayList<>();
        Aprendiz aprendiz;
        Actividades actividades;

        // lectura el archivo xls
        for (Row row : sheet1) {
            aprendiz = new Aprendiz();
            actividades = new Actividades();
            if (row.getRowNum() != 2) {
                for (Cell cell : row) {

                    switch (formulaEvaluator.evaluateInCell(cell).getCellType()) {

                        case Cell.CELL_TYPE_STRING:
                            if (cell.getColumnIndex() == 0 || cell.getColumnIndex() == 1
                                    || cell.getColumnIndex() == 2) {
                            } else {

                                switch (cell.getColumnIndex()) {
                                    case 3:
                                        // separando tipo de documento y documento
                                        String[] documento = cell.getStringCellValue().split(" ");
                                        aprendiz.setTipo_documento(documento[0]);
                                        aprendiz.setDocumento_aprendiz(documento[1]);

                                        break;
                                    case 5:
                                        aprendiz.setNombre_aprendiz(cell.getStringCellValue());
                                        break;
                                    case 6:
                                        aprendiz.setEmail_aprendiz(cell.getStringCellValue());
                                        break;
                                    case 7:
                                        aprendiz.setMunicipio(cell.getStringCellValue());
                                        break;
                                    case 8:
                                        aprendiz.setGenero(cell.getStringCellValue());
                                        break;
                                    case 9:
                                        // cambiando formato de fecha
                                        String[] fecha = cell.getStringCellValue().split("/");
                                        aprendiz.setFecha_nacimiento(fecha[2] + "-" + fecha[1] + "-" + fecha[0]);
                                        break;
                                    case 10:
                                        aprendiz.setTipo_poblacion(cell.getStringCellValue());
                                        break;
                                    case 11:
                                        aprendiz.setEps(cell.getStringCellValue());
                                        break;
                                    case 13:
                                        String[] ficha = cell.getStringCellValue().split("-");
                                        aprendiz.setFicha(ficha[0].toString().substring(0, ficha[0].length() - 1));
                                        aprendiz.setNombrePrograma(ficha[1].replaceFirst(" ", ""));

                                        aprendiz.setCoordinacion(returnString.getCoordinacion(aprendiz.getNombrePrograma()));

                                        break;
                                    case 14:
                                        aprendiz.setNivelFormacion(cell.getStringCellValue());

                                        break;
                                    case 15:
                                        actividades.setTipo_actividad(cell.getStringCellValue());
                                        break;
                                    case 16:
                                        String nombre = cell.getStringCellValue();
                                        actividades.setNombre_actividad(nombre);

                                        actividades.setResponsable(returnString.getResponsable(nombre));

                                        break;
                                    case 17:
                                        String[] fechaN = cell.getStringCellValue().split("/");
                                        actividades.setFecha_inicio(fechaN[2] + "-" + fechaN[1] + "-" + fechaN[0]);
                                        break;
                                    case 18:
                                        String[] fechaF = cell.getStringCellValue().split("/");
                                        actividades.setFecha_fin(fechaF[2] + "-" + fechaF[1] + "-" + fechaF[0]);
                                        break;
                                }
                            }
                            break;
                            // lectura de campos enteros
                        case Cell.CELL_TYPE_NUMERIC:
                        case 12:
                            int estrato = (int) cell.getNumericCellValue();
                            aprendiz.setEstrato(Integer.toString(estrato));
                            break;
                    }
                }
                // agregando objetos a la lista
                aprendiz.setActividades(actividades);
                lista.add(aprendiz);
            }
        }
            // limpiando memoria en la lectura del archivo
            formulaEvaluator.clearAllCachedResultValues();
       
            // instancia de conexion y los DAO para la inserción
            Conexion conexion = new Conexion();
            ActividadDAO actividadDAO = new ActividadDAO(conexion);
            AprendizDAO aprendizDAO = new AprendizDAO(conexion);
            AprendizActividadDAO aprendizActividadDAO = new AprendizActividadDAO(conexion);
            
            // creación de variables para el restorno 
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
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
