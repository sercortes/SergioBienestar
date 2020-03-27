/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.bienestar.sergio.dao;

import co.edu.sena.bienestar.sergio.dto.ActividadesAprendiz;
import co.edu.sena.bienestar.sergio.dto.Aprendiz;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author serfin
 */
public class AprendizActividadDAO{


    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public AprendizActividadDAO(Connection conn) {
        this.conn = conn;
    }
  
    public boolean insertReturn(ActividadesAprendiz actividadesAprendiz){
        String sql = "INSERT INTO Actividades_Aprendiz (Cod_actividad, Cod_aprendiz) "
                + "VALUES (?, ?)";
        
        try{
            ps = conn.prepareStatement(sql);
            ps.setInt(1, actividadesAprendiz.getCodActividad());
            ps.setInt(2, actividadesAprendiz.getCodAprendiz());
            
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
    
      public void CloseAll(){
        Conexion.close(conn);
        Conexion.close(ps);
    } 
        
    
}
