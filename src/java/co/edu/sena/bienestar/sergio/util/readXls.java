/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.bienestar.sergio.util;

import co.edu.sena.bienestar.sergio.dao.ActividadDAO;
import co.edu.sena.bienestar.sergio.dao.Conexion;
import co.edu.sena.bienestar.sergio.dto.Actividades;
import co.edu.sena.bienestar.sergio.dto.Aprendiz;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.http.Part;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author serfin
 */
public class readXls {


    public ArrayList<Aprendiz> readingXls(Part file) throws FileNotFoundException, IOException {

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
        
        // instanciar la clase conexion y actividadDao para consultar si ya esta en la bd
        Conexion conexion = new Conexion();
        ActividadDAO actividadDAO = new ActividadDAO(conexion);

        // lectura el archivo xls
        for (Row row : sheet1) {
            aprendiz = new Aprendiz();
            actividades = new Actividades();
            if (row.getRowNum() != 2) {
                for (Cell cell : row) {

                    switch (formulaEvaluator.evaluateInCell(cell).getCellType()) {

                        // lectura de campos de tipo caracter
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
                                        // separando el campo programa de formación para obtener la ficha
                                        String[] ficha = cell.getStringCellValue().split("-");
                                        aprendiz.setFicha(ficha[0].toString().substring(0, ficha[0].length() - 1));
                                        aprendiz.setNombrePrograma(ficha[1].replaceFirst(" ", ""));

                                        // asignando cordinación por el programa de formación
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

                                        // asignando responsable deacuerdo al nombre de la actividad
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

                // validaciones con fecha, para que no se repitan datos en la bd
                Actividades actividad = actividadDAO.getLastDate(actividades);
                
                // retorna verdadero si esta en la bd
                boolean existe = actividad.getIdRealActividad() != 0;
               
                //validación si la fila de excel ya esta en el rango de la bd, no se agrega a la lista
                //si existe no se agrega
                if (existe) {

                } else {
                    // agregando objetos a la lista
                    aprendiz.setActividades(actividades);
                    lista.add(aprendiz);
                }

            }
        }
        
        // limpiando memoria por lectura del archivo
        formulaEvaluator.clearAllCachedResultValues();
        conexion.disconnectDb();
        
        // retornando los valores del archivo en una lista
        return lista;
    
    } // cierre del metodo readingXls

}
