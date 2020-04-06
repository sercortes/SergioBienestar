/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.bienestar.sergio.dao;

import co.edu.sena.bienestar.sergio.dto.ActividadesAprendiz;
import co.edu.sena.bienestar.sergio.dto.Responsables;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author serfin
 */
public class ResponsableDAO {
    
       private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public ResponsableDAO(Connection conn) {
        this.conn = conn;
    }
    public boolean updateRes(Responsables responsables) {
            try {
           
            String sql = "UPDATE Responsables set nombre = ?, codigo = ?, year = ? WHERE idResponsable = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, responsables.getNombre());
            ps.setString(2, responsables.getCodigo());
            ps.setString(3, responsables.getYear());
            ps.setString(4, responsables.getId());
            
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
            String sql = "DELETE FROM Responsables WHERE idResponsable = ?";
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

  
    public boolean insertReturn(Responsables responsables){
        String sql = "INSERT INTO Responsables (nombre, codigo, year) "
                + "VALUES (?, ?, ?)";
        
        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1, responsables.getNombre());
            ps.setString(2, responsables.getCodigo());
            ps.setString(3, responsables.getYear());
            
            ps.executeUpdate();
          
            return true;   
        } catch(MySQLIntegrityConstraintViolationException e){
            System.out.println(e);
            return false;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    
     public ArrayList<Responsables> getResponsables() {
        try {
            String sql = "SELECT * FROM Responsables";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            List<Responsables> list = new ArrayList<Responsables>();
            Responsables respon;
            while (rs.next()) {
                respon = new Responsables();
                respon.setId(rs.getString("idResponsable"));
                respon.setNombre(rs.getString("nombre"));
                respon.setCodigo(rs.getString("codigo"));
                respon.setYear(rs.getString("year"));
                list.add(respon);
            }
            return (ArrayList<Responsables>) list;
        } catch (Exception e) {
            return null;
        }
    }
   
      public void CloseAll(){
        Conexion.close(conn);
        Conexion.close(ps);
    } 
        
    
}
