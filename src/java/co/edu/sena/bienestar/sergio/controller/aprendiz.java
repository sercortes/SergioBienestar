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
import co.edu.sena.bienestar.sergio.dto.Aprendiz;
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
public class aprendiz extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String direccion = request.getRequestURI();

        switch (direccion) {

            case "/bienestar/ListAprendices":

                listAprendices(request, response);

                break;

            case "/bienestar/ListAprendicesByFicha":

                getAprendicesByficha(request, response);

                break;

            case "/bienestar/ListAprendicesByCoor":

                getAprendicesByCoor(request, response);

                break;

            case "/bienestar/getTypesActivitys":

                getTypesActivitys(request, response);

                break;

            case "/bienestar/ListActivitysByType":

                getActivitysByTypes(request, response);

                break;

            case "/bienestar/getByCoor":
                
                getByCoor(request, response);
                
                break;

        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void getByCoor(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("UTF-8");

        String acti = request.getParameter("id");

        Actividades actividades = new Actividades();
        actividades.setFecha_inicio(request.getParameter("fechaInicial"));
        actividades.setFecha_fin(request.getParameter("fechaFinal"));
        actividades.setCoor(acti);

        Conexion conexion = new Conexion();
        AprendizDAO aprendizDAO = new AprendizDAO(conexion.getConnection());

        ArrayList<?> activid = aprendizDAO.getForCoorDate(actividades);

        aprendizDAO.CloseAll();
        response.setContentType("application/json");
        new Gson().toJson(activid, response.getWriter());

    }

    private void getTypesActivitys(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        Conexion conexion = new Conexion();
        ActividadDAO actividadDAO = new ActividadDAO(conexion.getConnection());

        ArrayList<?> tipos = actividadDAO.getByTypeActivity();

        actividadDAO.CloseAll();
        response.setContentType("application/json");
        new Gson().toJson(tipos, response.getWriter());
    }

    private void getActivitysByTypes(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
        request.setCharacterEncoding("UTF-8");

        String acti = request.getParameter("id");

        Actividades actividades = new Actividades();
        actividades.setFecha_inicio(request.getParameter("fechaInicial"));
        actividades.setFecha_fin(request.getParameter("fechaFinal"));
        actividades.setTipo_actividad(acti);

        Conexion conexion = new Conexion();
        ActividadDAO actividadDAO = new ActividadDAO(conexion.getConnection());

        ArrayList<?> activid = actividadDAO.getActivitysByTypes(actividades);

        actividadDAO.CloseAll();
        response.setContentType("application/json");
        new Gson().toJson(activid, response.getWriter());
    }

    private void getAprendicesByficha(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {

        request.setCharacterEncoding("UTF-8");

     
        Aprendiz aprendiz = new Aprendiz();
        aprendiz.setFicha(request.getParameter("id"));
        Actividades actividades = new Actividades();
        actividades.setFecha_inicio(request.getParameter("fechaInicial"));
        actividades.setFecha_fin(request.getParameter("fechaFinal"));
        aprendiz.setActividades(actividades);

        Conexion conexion = new Conexion();
        AprendizDAO actividadDAO = new AprendizDAO(conexion.getConnection());

        ArrayList<?> aprendices = actividadDAO.getByFicha(aprendiz);

        actividadDAO.CloseAll();
        response.setContentType("application/json");
        new Gson().toJson(aprendices, response.getWriter());

    }

    private void getAprendicesByCoor(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {

        request.setCharacterEncoding("UTF-8");

      
        Aprendiz aprendiz = new Aprendiz();
        aprendiz.setNombrePrograma(request.getParameter("programa"));
        
        Actividades actividades = new Actividades(request.getParameter("fechaInicial"), request.getParameter("fechaFinal"));
        aprendiz.setActividades(actividades);
        
        Conexion conexion = new Conexion();
        AprendizDAO actividadDAO = new AprendizDAO(conexion.getConnection());

        ArrayList<?> aprendices = actividadDAO.getByProg(aprendiz); 

        actividadDAO.CloseAll();
        response.setContentType("application/json");
        new Gson().toJson(aprendices, response.getWriter());

    }

    private void listAprendices(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {

        
                request.setCharacterEncoding("UTF-8");

                String acti = request.getParameter("id");

                Conexion conexion = new Conexion();
                AprendizDAO actividadDAO = new AprendizDAO(conexion.getConnection());

                ArrayList<?> aprendices = actividadDAO.getByActivity(acti);

                actividadDAO.CloseAll();
                response.setContentType("application/json");
                new Gson().toJson(aprendices, response.getWriter());

    }

}
