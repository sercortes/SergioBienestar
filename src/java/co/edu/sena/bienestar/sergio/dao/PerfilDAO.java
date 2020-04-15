/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.bienestar.sergio.dao;

import co.edu.sena.bienestar.sergio.dto.Perfil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author serfin
 */
public class PerfilDAO {
    
     private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public PerfilDAO(Connection conn) {
        this.conn = conn;
    }

    public ArrayList<Perfil> getPerfiles() {
        try {
            String sql = "SELECT idPerfil, nombrePerfil FROM perfil";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            List<Perfil> list = new ArrayList<Perfil>();
            Perfil perfil;
            while (rs.next()) {
                perfil = new Perfil();
                perfil.setIdPerfil(rs.getInt("idPerfil"));
                perfil.setNombre(rs.getString("nombrePerfil"));
                list.add(perfil);
            }
            return (ArrayList<Perfil>) list;
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
