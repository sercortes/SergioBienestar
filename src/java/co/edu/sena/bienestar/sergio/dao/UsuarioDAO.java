/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.bienestar.sergio.dao;

import co.edu.sena.bienestar.sergio.dto.Perfil;
import co.edu.sena.bienestar.sergio.dto.Usuario;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
      
      
      public boolean updateUser(Usuario usuario) {
            try {
           
            String sql = "UPDATE Usuario set nombre = ?, apellido = ?, email = ?, perfil = ?, estatus = ? "
                    + "WHERE idUsuario = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getPerfil());
            ps.setString(5, usuario.getEstatus());
            ps.setInt(6, usuario.getId());
            
            int rows = ps.executeUpdate();
            boolean estado = rows > 0;
            return estado;
        } catch (Exception ex) {
            System.out.println("Error edit " + ex.getMessage());
            return false;
        }
    }
      
      public boolean delete(String id) {
        try {
            String sql = "DELETE FROM Usuario WHERE idUsuario = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            int rows = ps.executeUpdate();
            boolean estado = rows > 0;
            return estado;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
      
      public int insertUser(Usuario usuario) throws SQLException{
        String sql = "INSERT INTO Usuario (nombre, apellido, email, password, perfil, estatus) "
                + "VALUES (?, ?, ?, md5(?), ?, ?)";
        
        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getPass());
            ps.setString(5, usuario.getPerfil());
            ps.setString(6, usuario.getEstatus());
            
            ps.executeUpdate();
          
            return 1;   
        }catch(MySQLIntegrityConstraintViolationException e){
            System.out.println("SERGIO");
            System.out.println(e);
            return 2;
        }
    }
      
      public ArrayList<Usuario> getUsers(int id) {
        try {
            String sql = "SELECT u.idUsuario, u.nombre, u.apellido, u.email, u.perfil, u.estatus, p.idPerfil, p.nombrePerfil "
                    + "FROM Usuario u INNER JOIN perfil p ON u.perfil=p.idPerfil WHERE u.idUsuario <> ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            List<Usuario> list = new ArrayList<Usuario>();
            Usuario user;
            Perfil perfil;
            while (rs.next()) {
                user = new Usuario();
                perfil = new Perfil();
                user.setId(rs.getInt("idUsuario"));
                user.setNombre(rs.getString("nombre"));
                user.setApellido(rs.getString("apellido"));
                user.setEmail(rs.getString("email"));
                user.setEstatus(rs.getString("estatus"));
                
                perfil.setIdPerfil(rs.getInt("idPerfil"));
                perfil.setNombre(rs.getString("nombrePerfil"));
                user.setPerfilO(perfil);
                list.add(user);
            }
            return (ArrayList<Usuario>) list;
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
