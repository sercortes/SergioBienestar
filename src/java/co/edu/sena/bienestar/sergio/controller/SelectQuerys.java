/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.bienestar.sergio.controller;

import co.edu.sena.bienestar.sergio.dao.ActividadDAO;
import co.edu.sena.bienestar.sergio.dao.AprendizDAO;
import co.edu.sena.bienestar.sergio.dao.Conexion;
import co.edu.sena.bienestar.sergio.dto.Actividades;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author serfin
 */
public class SelectQuerys extends HttpServlet {

    
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String direccion = request.getRequestURI();
        
        switch(direccion){
        
            case "/bienestar/SelectYears":
                
                SelectYearsByCoor(request, response);
                
                break;
                
            case "/bienestar/SelectByTypesbyYear":
                
                SelectByTypesbyYear(request, response);
                
                break;
                
            case "/bienestar/SelectStatiticsByActivity":
                
                SelectStatiticsByActivity(request, response);
                
                break;
            
        }
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void SelectYearsByCoor(HttpServletRequest request, HttpServletResponse response) throws IOException {
            request.setCharacterEncoding("UTF-8");

          
        String acti = request.getParameter("id");
        Actividades actividades = new Actividades();
        actividades.setFecha_inicio(request.getParameter("fechaInicial"));
        actividades.setFecha_fin(request.getParameter("fechaFinal"));
        actividades.setCoor(acti);

        Conexion conexion = new Conexion();
        AprendizDAO aprendizDAO = new AprendizDAO(conexion.getConnection());

        ArrayList<?> activid = aprendizDAO.getByYearCoor(actividades);

        aprendizDAO.CloseAll();
        response.setContentType("application/json");
        new Gson().toJson(activid, response.getWriter());
    }

    private void SelectByTypesbyYear(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
   request.setCharacterEncoding("UTF-8");
   

        Actividades actividades = new Actividades();
        actividades.setYearStar(request.getParameter("yearStart"));
        actividades.setYearFinish(request.getParameter("finishYear"));
        actividades.setTipo_actividad(request.getParameter("tipo"));
        
        Conexion conexion = new Conexion();
        ActividadDAO actividadDAO = new ActividadDAO(conexion.getConnection());

        ArrayList<?> activid = actividadDAO.getStaticsByTypeEveryYear(actividades);

        actividadDAO.CloseAll();
        response.setContentType("application/json");
        new Gson().toJson(activid, response.getWriter());

    }

    private void SelectStatiticsByActivity(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        request.setCharacterEncoding("UTF-8");

                String acti = request.getParameter("id");

                Conexion conexion = new Conexion();
                AprendizDAO actividadDAO = new AprendizDAO(conexion.getConnection());

                ArrayList<?> aprendices = actividadDAO.getStatiticsByActivity(acti);

                actividadDAO.CloseAll();
                response.setContentType("application/json");
                new Gson().toJson(aprendices, response.getWriter());
        
    }

}
