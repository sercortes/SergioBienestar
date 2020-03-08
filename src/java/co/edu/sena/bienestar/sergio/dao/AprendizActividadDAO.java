/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.bienestar.sergio.dao;

import co.edu.sena.bienestar.sergio.dto.ActividadesAprendiz;
import co.edu.sena.bienestar.sergio.dto.Aprendiz;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author serfin
 */
public class AprendizActividadDAO implements InterfaceCRUD{

    private Conexion conn;
    private String error;

    public AprendizActividadDAO(Conexion conn) {
        this.conn = conn;
    }
  
    public boolean insertReturn(ActividadesAprendiz actividadesAprendiz){
        String sql = "INSERT INTO Actividades_Aprendiz (Cod_actividad, Cod_aprendiz) "
                + "VALUES (?, ?)";
        
        try{
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ps.setInt(1, actividadesAprendiz.getCodActividad());
            ps.setString(2, actividadesAprendiz.getCodAprendiz());
            
            ps.executeUpdate();
          
            return true;   
        } catch(MySQLIntegrityConstraintViolationException e){
            System.out.println(":( "+e);
            return false;
        }catch(Exception e){
            System.out.println(":( "+e);
            return false;
        }
    }
    
    @Override
    public boolean insert(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getByID(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<?> getSomeByID(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<?> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<?> getByWord(String keyword) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
