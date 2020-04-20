/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.bienestar.sergio.controller;

import co.edu.sena.bienestar.sergio.dao.Conexion;
import co.edu.sena.bienestar.sergio.dao.LogMigrationDAO;
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
public class EventsLogs extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String direccion = request.getRequestURI();
        
        switch(direccion){
            case "/bienestar/getEventsLogs":
                getEventsLogs(request, response);
                break;
        }
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void getEventsLogs(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        
        Conexion conexion = new Conexion();
        LogMigrationDAO logMigrationDAO = new LogMigrationDAO(conexion.getConnection());

        ArrayList<?> lista = logMigrationDAO.getLogs();

        logMigrationDAO.CloseAll();
        response.setContentType("application/json");
        new Gson().toJson(lista, response.getWriter());
        
    }

}
