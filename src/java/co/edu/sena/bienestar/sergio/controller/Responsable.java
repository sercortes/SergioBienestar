/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.bienestar.sergio.controller;

import co.edu.sena.bienestar.sergio.dao.Conexion;
import co.edu.sena.bienestar.sergio.dao.ResponsableDAO;
import co.edu.sena.bienestar.sergio.dto.Responsables;
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
public class Responsable extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
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
        String direccion = request.getRequestURI();
        
        switch(direccion){
        
            case "/bienestar/SelectResponsables":
                
                SelectResponsables(request, response);
                
                break;
                
            case "/bienestar/addResponsables":
                
                addResponsables(request, response);
                
                break;
            
        }
        
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

    private void SelectResponsables(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        
        Conexion conexion = new Conexion();
        ResponsableDAO responsableDAO = new ResponsableDAO(conexion.getConnection());

        ArrayList<?> lista = responsableDAO.getResponsables();

        responsableDAO.CloseAll();
        response.setContentType("application/json");
        new Gson().toJson(lista, response.getWriter());


    }

    private void addResponsables(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        request.setCharacterEncoding("UTF-8");
        
        Conexion conexion = new Conexion();
        ResponsableDAO responsableDAO = new ResponsableDAO(conexion.getConnection());
        Responsables responsable = new Responsables();
        responsable.setNombre(request.getParameter("nombre"));
        responsable.setCodigo(request.getParameter("codigo"));
        responsable.setYear(request.getParameter("year"));

       boolean estado = responsableDAO.insertReturn(responsable);

        responsableDAO.CloseAll();
        response.setContentType("application/json");
        new Gson().toJson(estado, response.getWriter());
        
    }

}
