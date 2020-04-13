/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.bienestar.sergio.dao;

import co.edu.sena.bienestar.sergio.dto.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author serfin
 */
public class UsuarioDAO {
    
        private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public UsuarioDAO(Connection conn){
        this.conn = conn;
    }
    
      public Usuario login(Usuario usuario) {

        try {
            String sql = "SELECT * FROM Usuario WHERE email = ? AND password"
                    + " = md5(?) AND estatus = 1 limit 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, usuario.getEmail());
            ps.setString(2, usuario.getPass());
            ResultSet rs = ps.executeQuery();
            Usuario usua = new Usuario();
            while (rs.next()) {

                usua.setId(rs.getInt("idUsuario"));
                usua.setNombre(rs.getString("nombre"));
                usua.setApellido(rs.getString("apellido"));
                usua.setEmail(rs.getString("email"));
                usua.setPass(rs.getString("password"));
                usua.setPerfil(rs.getString("perfil"));
                usua.setEstatus(rs.getString("estatus"));

            }
            return usua;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }
      
 public void CloseAll(){
        Conexion.close(conn);
        Conexion.close(ps);
    } 
       
    
}
