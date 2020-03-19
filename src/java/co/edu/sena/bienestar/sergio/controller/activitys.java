/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.bienestar.sergio.controller;

import co.edu.sena.bienestar.sergio.dao.ActividadDAO;
import co.edu.sena.bienestar.sergio.dao.Conexion;
import co.edu.sena.bienestar.sergio.dto.Actividades;
import co.edu.sena.bienestar.sergio.dto.Aprendiz;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author serfin
 */
public class activitys extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String direccion = request.getRequestURI();

        switch (direccion) {

            case "/bienestar/ListActivitys":
                
                ListActivitys(request, response);

                break;
                
            case "/bienestar/ListActivitysByAprendiz":
                
                listActivitysByAprendiz(request, response);
                
                break;
                
            case "/bienestar/ListStaticsBytype":
                
                ListStaticsBytype(request, response);
                
                break;

        }

    }
    
    private void ListActivitys(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
                response.setContentType("text/html;charset=UTF-8");
        
                Conexion conexion = new Conexion();
                ActividadDAO actividadDAO = new ActividadDAO(conexion);

                ArrayList<?> actividades = actividadDAO.getAll();

                response.setContentType("application/json");
                new Gson().toJson(actividades, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void listActivitysByAprendiz(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
         response.setContentType("text/html;charset=UTF-8");
        
         Aprendiz aprendiz = new Aprendiz();
        aprendiz.setDocumento_aprendiz(request.getParameter("documento"));
        Actividades actividades = new Actividades();
        actividades.setFecha_inicio(request.getParameter("fechaInicial"));
        actividades.setFecha_fin(request.getParameter("fechaFinal"));
        aprendiz.setActividades(actividades);
         
                Conexion conexion = new Conexion();
                ActividadDAO actividadDAO = new ActividadDAO(conexion);

                
                ArrayList<?> lista = actividadDAO.getActivitysByIdAPrendiz(aprendiz);

                response.setContentType("application/json");
                new Gson().toJson(lista, response.getWriter());
        
    }

    private void ListStaticsBytype(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
         response.setContentType("text/html;charset=UTF-8");
        
         Aprendiz aprendiz = new Aprendiz();
        aprendiz.setDocumento_aprendiz(request.getParameter("id"));
        Actividades actividades = new Actividades();
        actividades.setFecha_inicio(request.getParameter("fechaInicial"));
        actividades.setFecha_fin(request.getParameter("fechaFinal"));
        aprendiz.setActividades(actividades);
         
                Conexion conexion = new Conexion();
                ActividadDAO actividadDAO = new ActividadDAO(conexion);

                
                ArrayList<?> lista = actividadDAO.getStaticsByType(aprendiz);

                response.setContentType("application/json");
                new Gson().toJson(lista, response.getWriter());
    }

    
}
