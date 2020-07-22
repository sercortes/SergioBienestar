/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.bienestar.sergio.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author serfin
 */
public class Routes extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String direccion = request.getRequestURI();
        RequestDispatcher rd;

        switch (direccion) {
            case "/bienestar/Logout":
                request.getSession().removeAttribute("MESSAGE");
                request.getSession().invalidate();
                response.sendRedirect("/bienestar/Start");
                break;
            case "/bienestar/Start":
                rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
                break;
            case "/bienestar/Home":
                rd = request.getRequestDispatcher("/views/home/home.jsp");
                rd.forward(request, response);
                break;
            case "/bienestar/Upload":
                rd = request.getRequestDispatcher("/views/system/input.jsp");
                rd.forward(request, response);
                break;
            case "/bienestar/Activitys":
                rd = request.getRequestDispatcher("/views/system/activity.jsp");
                rd.forward(request, response);
                break;
            case "/bienestar/Aprendices":
                rd = request.getRequestDispatcher("/views/system/students.jsp");
                rd.forward(request, response);
                break;
            case "/bienestar/Statistics":
                rd = request.getRequestDispatcher("/views/system/statistics.jsp");
                rd.forward(request, response);
                break;
            case "/bienestar/StatisticsDimensions":
                rd = request.getRequestDispatcher("/views/system/StatisticsDimensions/StatisticsDimensions.jsp");
                rd.forward(request, response);
                break;
            case "/bienestar/Responsables":
                rd = request.getRequestDispatcher("/views/responsables/resp.jsp");
                rd.forward(request, response);
                break;
            case "/bienestar/Users":
                rd = request.getRequestDispatcher("/views/users/user.jsp");
                rd.forward(request, response);
                break;

            case "/bienestar/Profile":
                rd = request.getRequestDispatcher("/views/system/profile/user.jsp");
                rd.forward(request, response);
                break;

            case "/bienestar/Logs":
                rd = request.getRequestDispatcher("/views/system/logs/logs.jsp");
                rd.forward(request, response);
                break;
            case "/bienestar/Error404":
                rd = request.getRequestDispatcher("/views/error/error404.jsp");
                rd.forward(request, response);
                break;
            case "/bienestar/Error500":
                rd = request.getRequestDispatcher("/views/error/error500.jsp");
                rd.forward(request, response);
                break;
        }

    }

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
        processRequest(request, response);
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
        processRequest(request, response);
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

}
