package co.edu.sena.bienestar.sergio.controller;

import co.edu.sena.bienestar.sergio.dao.Conexion;
import co.edu.sena.bienestar.sergio.dao.UsuarioDAO;
import co.edu.sena.bienestar.sergio.dto.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class login extends HttpServlet {



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           
        String userParam = request.getParameter("email");
        String passParam = request.getParameter("pass");

        Usuario UsuarioParam = new Usuario(userParam, passParam);

        String msg = "";

        HttpSession session = request.getSession();

        Conexion conn = new Conexion();
        UsuarioDAO userdao = new UsuarioDAO(conn.getConnection());

        Usuario usuario = userdao.login(UsuarioParam);

        userdao.CloseAll();

        if (usuario.getId() > 0) {

            session.setAttribute("USER", usuario);
            response.sendRedirect("/bienestar/Home");
//            intentos = 0;

        } else {

            msg = "Usuario o password incorrectos";
            session.setAttribute("MESSAGE", msg);
            response.sendRedirect("/bienestar/Start");
//            intentos++;

        }
        
//        session.setAttribute("INTENTOS", intentos);
//        if (intentos >= 2) {
//            System.out.println(getIp(request)+" :D");
//        }

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

    public String getIp(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }

    private Map<String, String> getRequestHeadersInMap(HttpServletRequest request) {

        Map<String, String> result = new HashMap<>();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            result.put(key, value);
        }

        return result;
    }
}