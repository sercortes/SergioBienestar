/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.bienestar.sergio.controller;

import co.edu.sena.bienestar.sergio.dao.ActividadDAO;
import co.edu.sena.bienestar.sergio.dao.AprendizDAO;
import co.edu.sena.bienestar.sergio.dao.Conexion;
import co.edu.sena.bienestar.sergio.dao.PerfilDAO;
import co.edu.sena.bienestar.sergio.dto.Actividades;
import co.edu.sena.bienestar.sergio.dto.Usuario;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author serfin
 */
public class Select extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String direccion = request.getRequestURI();

        switch (direccion) {

           

            case "/bienestar/getFichas":

                getFichas(request, response);

                break;

            case "/bienestar/getCoor":

                getCoor(request, response);

                break;
                
                case "/bienestar/getYears":

                getYears(request, response);

                break;
                
                case "/bienestar/getPerfil":

                getPerfil(request, response);

                break;
                
                case "/bienestar/getUser":

                getUser(request, response);

                break;


        }
    }

    private void getCoor(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
        request.setCharacterEncoding("UTF-8");

        Conexion conexion = new Conexion();
        AprendizDAO actividadDAO = new AprendizDAO(conexion.getConnection());

        ArrayList<?> programas = actividadDAO.getCoordinacion();

        response.setContentType("application/json");

        actividadDAO.CloseAll();
        new Gson().toJson(programas, response.getWriter());
    }

    private void getFichas(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {

        request.setCharacterEncoding("UTF-8");

        String acti = request.getParameter("id");

        Actividades actividades = new Actividades();
        actividades.setFecha_inicio(request.getParameter("fechaInicial"));
        actividades.setFecha_fin(request.getParameter("fechaFinal"));
        actividades.setPrograma(acti);

        Conexion conexion = new Conexion();
        AprendizDAO actividadDAO = new AprendizDAO(conexion.getConnection());

        ArrayList<?> aprendices = actividadDAO.getForProgramaDate(actividades);

        response.setContentType("application/json");
        actividadDAO.CloseAll();
        new Gson().toJson(aprendices, response.getWriter());

    }


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

    private void getYears(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        Conexion conexion = new Conexion();
        ActividadDAO actividadDAO = new ActividadDAO(conexion.getConnection());

        ArrayList<?> aprendices = actividadDAO.getEveryYear();

        response.setContentType("application/json");
        actividadDAO.CloseAll();
        new Gson().toJson(aprendices, response.getWriter());
        
    }

    private void getPerfil(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        Conexion conexion = new Conexion();
        PerfilDAO perfilDAO = new PerfilDAO(conexion.getConnection());

        ArrayList<?> perfiles = perfilDAO.getPerfiles();

        response.setContentType("application/json");
        perfilDAO.CloseAll();
        new Gson().toJson(perfiles, response.getWriter());
        
    }

    private void getUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        request.setCharacterEncoding("UTF-8");
         request.getSession();
        
        Usuario usuario = (Usuario) request.getSession().getAttribute("USER");

        response.setContentType("application/json");

        new Gson().toJson(usuario.getEmail(), response.getWriter());
        
    }

}
