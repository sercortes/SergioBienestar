/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.bienestar.sergio.controller;

import co.edu.sena.bienestar.sergio.dao.Conexion;
import co.edu.sena.bienestar.sergio.dao.UsuarioDAO;
import co.edu.sena.bienestar.sergio.dto.Usuario;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author serfin
 */
public class User extends HttpServlet {

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, UnsupportedEncodingException {
        try {
            String direccion = request.getRequestURI();
            
            switch(direccion){
                
                case "/bienestar/SelectUsers":
                    
                    SelectUsers(request, response);
                    
                    break;
                    
                case "/bienestar/addUser":
                    
                    addUser(request, response);
                    
                    break;
                    
                    case "/bienestar/deluser":
                    
                    deluser(request, response);
                    
                    break;
                case "/bienestar/editUser":
                    
                    editUser(request, response);
                    
                    break;
                    
                case "/bienestar/updatePass":
                    
                    updatePass(request, response);
                    
                    break;
                    
                    case "/bienestar/updatePassIndividual":
                    
               updatePassIndividual(request, response);
                    
                    break;
                    
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
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

    private void SelectUsers(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        
        Usuario usuario = (Usuario) request.getSession().getAttribute("USER");
        
        Conexion conexion = new Conexion();
        UsuarioDAO usuariodao = new UsuarioDAO(conexion.getConnection());

        ArrayList<?> lista = usuariodao.getUsers(usuario.getId());

        usuariodao.CloseAll();
        response.setContentType("application/json");
        new Gson().toJson(lista, response.getWriter());
        
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException, SQLException {
        
        request.setCharacterEncoding("UTF-8");
        
        Conexion conexion = new Conexion();
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexion.getConnection());
        Usuario usuario = new Usuario();
        usuario.setNombre(request.getParameter("nombre"));
        usuario.setApellido(request.getParameter("apellido"));
        usuario.setEmail(request.getParameter("correo"));
        usuario.setPass(request.getParameter("contra"));
        usuario.setPerfil(request.getParameter("perfil"));
        usuario.setEstatus(request.getParameter("estado"));

       int estado = usuarioDAO.insertUser(usuario);

        usuarioDAO.CloseAll();
        response.setContentType("application/json");
        new Gson().toJson(estado, response.getWriter());
        
    }

    private void deluser(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        
        Conexion conexion = new Conexion();
        UsuarioDAO usuariodao = new UsuarioDAO(conexion.getConnection());

        boolean estado = usuariodao.delete(request.getParameter("id"));

        usuariodao.CloseAll();
        response.setContentType("application/json");
        new Gson().toJson(estado, response.getWriter());
        
    }

    private void editUser(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        
        request.setCharacterEncoding("UTF-8");
        
        Conexion conexion = new Conexion();
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexion.getConnection());
        Usuario usuario = new Usuario();
        usuario.setId(Integer.parseInt(request.getParameter("id")));
        usuario.setNombre(request.getParameter("nombre"));
        usuario.setApellido(request.getParameter("apellido"));
        usuario.setEmail(request.getParameter("correo"));
        usuario.setPass(request.getParameter("contra"));
        usuario.setPerfil(request.getParameter("perfil"));
        usuario.setEstatus(request.getParameter("estado"));

       boolean estado = usuarioDAO.updateUser(usuario);

        usuarioDAO.CloseAll();
        response.setContentType("application/json");
        new Gson().toJson(estado, response.getWriter());
        
    }

    private void updatePass(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        
        Conexion conexion = new Conexion();
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexion.getConnection());
        Usuario usuario = new Usuario();
        usuario.setId(Integer.parseInt(request.getParameter("id")));
        usuario.setPass(request.getParameter("pass"));

       boolean estado = usuarioDAO.updatePass(usuario);

        usuarioDAO.CloseAll();
        response.setContentType("application/json");
        new Gson().toJson(estado, response.getWriter());
        
    }

    private void updatePassIndividual(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        
        Conexion conexion = new Conexion();
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexion.getConnection());
        Usuario usuarioSe = (Usuario) request.getSession().getAttribute("USER");
        Usuario usuario = new Usuario();
        
        usuario.setId(usuarioSe.getId());
        usuario.setPass(request.getParameter("pass"));

       boolean estado = usuarioDAO.updatePass(usuario);

        usuarioDAO.CloseAll();
        response.setContentType("application/json");
        new Gson().toJson(estado, response.getWriter());
        
    }

}
