/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.bienestar.sergio.dao;

import co.edu.sena.bienestar.sergio.dto.LogMigration;
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
public class LogMigrationDAO {
    
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    
    public LogMigrationDAO(Connection conn){
        this.conn = conn;
    }
    
    public boolean insertReturn(LogMigration logMigration){
        String sql = "INSERT INTO LogMigration (email, registros) "
                + "VALUES (?, ?)";
        
        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1, logMigration.getEmail());
            ps.setString(2, logMigration.getRegistros());
            
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
    public ArrayList<LogMigration> getLogs() {
        try {
            String sql = "SELECT email, registros, modificado FROM LogMigration";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            List<LogMigration> list = new ArrayList<LogMigration>();
            LogMigration logMigration;
            while (rs.next()) {
                logMigration = new LogMigration();
                logMigration.setEmail(rs.getString("email"));
                logMigration.setRegistros(rs.getString("registros"));
                logMigration.setFechaModif(rs.getTimestamp("modificado"));
                
                list.add(logMigration);
            }
            return (ArrayList<LogMigration>) list;
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
