/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.bienestar.sergio.dao;

import co.edu.sena.bienestar.sergio.dto.Actividades;
import co.edu.sena.bienestar.sergio.dto.Aprendiz;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author serfin
 */
public class AprendizDAO implements InterfaceCRUD{

    private Conexion conn;
    private String error;

    public AprendizDAO(Conexion conn) {
        this.conn = conn;
    }
     public Aprendiz getIdAprendiz(Aprendiz apren) {

        try {
            String sql = "SELECT idAprendiz FROM Aprendiz "
                    + "WHERE Documento_aprendiz = ? "
                    + "AND Ficha = ? ";
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, apren.getDocumento_aprendiz());
            ps.setString(2, apren.getFicha());
            
            ResultSet rs = ps.executeQuery();
            Aprendiz aprendiz = new Aprendiz();
            while (rs.next()) {

                aprendiz.setId_aprendiz(rs.getInt("idAprendiz"));

            }
            return aprendiz;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    
    public int insertReturn(Aprendiz aprendiz){
        int idAprendiz = 0;
        String sql = "INSERT INTO Aprendiz (Documento_aprendiz, Tipo_documento, Nombres_aprendiz, Email_aprendiz, Municipio_aprendiz, Genero, FechaNacimiento_Aprendiz, Tipo_poblacion, Eps, Estrato, Ficha, NombrePrograma, Nivel_formacion, Coordinacion)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try{
            PreparedStatement ps = conn.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, aprendiz.getDocumento_aprendiz());
            ps.setString(2, aprendiz.getTipo_documento());
            ps.setString(3, aprendiz.getNombre_aprendiz());
            ps.setString(4, aprendiz.getEmail_aprendiz());
            ps.setString(5, aprendiz.getMunicipio());
            ps.setString(6, aprendiz.getGenero());
            ps.setDate(7, aprendiz.getFecha_nacimiento());
            ps.setString(8, aprendiz.getTipo_poblacion());
            ps.setString(9, aprendiz.getEps());
            ps.setString(10, aprendiz.getEstrato());
            ps.setString(11, aprendiz.getFicha());
            ps.setString(12, aprendiz.getNombrePrograma());
            ps.setString(13, aprendiz.getNivelFormacion());
            ps.setString(14, aprendiz.getCoordinacion());
            
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                idAprendiz = rs.getInt(1);
            }
            return idAprendiz;
        }catch(MySQLIntegrityConstraintViolationException e){
            System.out.println(e);
            return 0;
        }
        catch(Exception e){
            System.out.println(e);
            return 0;
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

      public ArrayList<?> getByActivity(String acti) {
        try {
            String sql = "SELECT ap.*, ac.Nombre_actividad  " +
                        "FROM Actividades ac  " +
                        "INNER JOIN Actividades_Aprendiz aa  " +
                        "ON ac.Id_actividad=aa.Cod_actividad " +
                        "INNER JOIN Aprendiz ap ON aa.Cod_aprendiz = ap.idAprendiz " +
                        "WHERE ac.Id_actividad = ?";
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, acti);
            ResultSet rs = ps.executeQuery();
            List<Aprendiz> list = new ArrayList<>();
            Aprendiz aprendiz;
            while (rs.next()) {
                aprendiz = new Aprendiz();
                aprendiz.setId_aprendiz(rs.getInt("idAprendiz"));
                aprendiz.setDocumento_aprendiz(rs.getString("Documento_aprendiz"));
                aprendiz.setNombre_aprendiz(rs.getString("Nombres_aprendiz"));
                aprendiz.setFicha(rs.getString("Ficha"));
                aprendiz.setNombrePrograma(rs.getString("NombrePrograma"));
                aprendiz.setCoordinacion(rs.getString("Coordinacion"));
                aprendiz.setActividades(new Actividades(rs.getString("Nombre_actividad")));
                list.add(aprendiz);
            }
            return (ArrayList<?>) list;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
      
       public ArrayList<?> getByFicha(Aprendiz aprendiz) {
        try {
            String sql = "SELECT ap.*, count(aa.Cod_aprendiz) 'cantidad' "
                    + "FROM Actividades ac INNER JOIN Actividades_Aprendiz aa "
                    + "ON ac.Id_actividad=aa.Cod_actividad INNER JOIN Aprendiz ap "
                    + "ON aa.Cod_aprendiz = ap.idAprendiz "
                    + "WHERE ap.Ficha = ? "
                    + "AND ac.Fecha_inicio BETWEEN ? AND ? "
                    + "AND ac.Fecha_fin BETWEEN ? AND ? "
                    + "GROUP BY(ap.idAprendiz) "
                    + "ORDER BY `cantidad` DESC";
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, aprendiz.getFicha());
            
            ps.setDate(2, aprendiz.getActividades().getFecha_inicio());
            ps.setDate(3, aprendiz.getActividades().getFecha_fin());
            ps.setDate(4, aprendiz.getActividades().getFecha_inicio());
            ps.setDate(5, aprendiz.getActividades().getFecha_fin());
            
            ResultSet rs = ps.executeQuery();
            List<Aprendiz> list = new ArrayList<>();
            Aprendiz aprendizLista;
            while (rs.next()) {
                aprendizLista = new Aprendiz();
                aprendizLista.setId_aprendiz(rs.getInt("idAprendiz"));
                aprendizLista.setDocumento_aprendiz(rs.getString("Documento_aprendiz"));
                aprendizLista.setNombre_aprendiz(rs.getString("Nombres_aprendiz"));
                aprendizLista.setFicha(rs.getString("Ficha"));
                aprendizLista.setNombrePrograma(rs.getString("NombrePrograma"));
                aprendizLista.setCoordinacion(rs.getString("Coordinacion"));
                aprendizLista.setParticipaciones(rs.getString("cantidad"));
                list.add(aprendizLista);
            }
            return (ArrayList<?>) list;
        } catch (Exception e) {
            return null;
        }
    }

        public ArrayList<?> getByProg(Aprendiz apren) {
        try {
            String sql = "SELECT ap.Ficha, ap.Coordinacion, count(ap.Ficha) 'participo' "
                    + "FROM Actividades ac INNER JOIN Actividades_Aprendiz aa "
                    + "ON ac.Id_actividad=aa.Cod_actividad INNER JOIN Aprendiz ap "
                    + "ON aa.Cod_aprendiz = ap.idAprendiz "
                    + "WHERE ap.NombrePrograma = ? "
                    + "AND ac.Fecha_inicio BETWEEN ? AND ? "
                    + "AND ac.Fecha_fin "
                    + "BETWEEN ? AND ? "
                    + "group by(ap.Ficha) ORDER BY count(ap.Ficha) DESC";
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, apren.getNombrePrograma());
            ps.setDate(2, apren.getActividades().getFecha_inicio());
            ps.setDate(3, apren.getActividades().getFecha_fin());
            ps.setDate(4, apren.getActividades().getFecha_inicio());
            ps.setDate(5, apren.getActividades().getFecha_fin());
            ResultSet rs = ps.executeQuery();
            List<Aprendiz> list = new ArrayList<>();
            Aprendiz aprendiz;
            while (rs.next()) {
                aprendiz = new Aprendiz();
                aprendiz.setFicha(rs.getString("Ficha"));
                aprendiz.setCoordinacion(rs.getString("Coordinacion"));
                aprendiz.setParticipaciones(rs.getString("participo"));
                list.add(aprendiz);
            }
            return (ArrayList<?>) list;
        } catch (Exception e) {
            return null;
        }
    }

     
           
    
      public ArrayList<?> getPrograms() {
        try {
            String sql = "SELECT A.NombrePrograma FROM Aprendiz A group by(A.NombrePrograma)";
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Aprendiz> list = new ArrayList<>();
            Aprendiz aprendiz;
            while (rs.next()) {
                aprendiz = new Aprendiz();
                aprendiz.setNombrePrograma(rs.getString("NombrePrograma"));
                list.add(aprendiz);
            }
            return (ArrayList<?>) list;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

      
        public ArrayList<?> getCoordinacion() {
        try {
            String sql = "SELECT A.Coordinacion FROM Aprendiz A group by(A.Coordinacion)";
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Aprendiz> list = new ArrayList<>();
            Aprendiz aprendiz;
            while (rs.next()) {
                aprendiz = new Aprendiz();
                aprendiz.setCoordinacion(rs.getString("Coordinacion"));
                list.add(aprendiz);
            }
            return (ArrayList<?>) list;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
           
           
           
    @Override
    public ArrayList<?> getByWord(String keyword) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<?> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
       public ArrayList<?> getForPrograma(String acti) {
        try {
            String sql = "SELECT A.Documento_aprendiz, A.Nombres_aprendiz, A.Genero, A.Ficha, A.NombrePrograma, A.Coordinacion, " +
                        "count(*) 'participo' FROM Aprendiz A " +
                        "INNER JOIN Actividades_Aprendiz AA ON A.Documento_aprendiz=AA.Cod_aprendiz " +
                        "INNER JOIN Actividades ac ON AA.Cod_actividad=ac.Nombre_actividad " +
                        "WHERE A.NombrePrograma = ? " +
                        "group by(A.Documento_aprendiz) ORDER BY count(*) DESC";
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, acti);
            ResultSet rs = ps.executeQuery();
            List<Aprendiz> list = new ArrayList<>();
            Aprendiz aprendiz;
            while (rs.next()) {
                aprendiz = new Aprendiz();
                aprendiz.setDocumento_aprendiz(rs.getString("Documento_aprendiz"));
                aprendiz.setNombre_aprendiz(rs.getString("Nombres_aprendiz"));
                aprendiz.setFicha(rs.getString("Ficha"));
                aprendiz.setNombrePrograma(rs.getString("NombrePrograma"));
                aprendiz.setCoordinacion(rs.getString("Coordinacion"));
                aprendiz.setParticipaciones(rs.getString("participo"));
                list.add(aprendiz);
            }
            return (ArrayList<?>) list;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    
       
        public ArrayList<?> getForProgramaDate(Actividades actividades) {
        try {
            String sql = "SELECT A.Documento_aprendiz, A.Nombres_aprendiz, A.Genero, A.Ficha, A.NombrePrograma, A.Coordinacion, " +
                        "count(*) 'participo' FROM Aprendiz A " +
                        "INNER JOIN Actividades_Aprendiz AA ON A.Documento_aprendiz=AA.Cod_aprendiz " +
                        "INNER JOIN Actividades ac ON AA.Cod_actividad=ac.Id_actividad " +
                        "WHERE A.NombrePrograma = ? AND ac.Fecha_inicio BETWEEN ? AND ? "  +
                        "AND ac.Fecha_fin BETWEEN ? AND ? " +
                        "group by(A.Ficha) ORDER BY count(*) DESC";
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, actividades.getPrograma());
            ps.setDate(2, actividades.getFecha_inicio());
            ps.setDate(3, actividades.getFecha_fin());
            ps.setDate(4, actividades.getFecha_inicio());
            ps.setDate(5, actividades.getFecha_fin());
            ResultSet rs = ps.executeQuery();
            
            List<Aprendiz> list = new ArrayList<>();
            Aprendiz aprendiz;
            while (rs.next()) {
                aprendiz = new Aprendiz();
                aprendiz.setDocumento_aprendiz(rs.getString("Documento_aprendiz"));
                aprendiz.setNombre_aprendiz(rs.getString("Nombres_aprendiz"));
                aprendiz.setFicha(rs.getString("Ficha"));
                aprendiz.setNombrePrograma(rs.getString("NombrePrograma"));
                aprendiz.setCoordinacion(rs.getString("Coordinacion"));
                aprendiz.setParticipaciones(rs.getString("participo"));
                list.add(aprendiz);
            }
            return (ArrayList<?>) list;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
         
        public ArrayList<?> getForProgramaDateStudent(Actividades actividades) {
        try {
            String sql = "SELECT A.Documento_aprendiz, A.Nombres_aprendiz, A.Genero, A.Ficha, A.NombrePrograma, A.Coordinacion, " +
                        "count(*) 'participo' FROM Aprendiz A " +
                        "INNER JOIN Actividades_Aprendiz AA ON A.Documento_aprendiz=AA.Cod_aprendiz " +
                        "INNER JOIN Actividades ac ON AA.Cod_actividad=ac.Id_actividad " +
                        "WHERE A.NombrePrograma = ? AND ac.Fecha_inicio BETWEEN ? AND ? "  +
                        "AND ac.Fecha_fin BETWEEN ? AND ? " +
                        "group by(A.Documento_aprendiz) ORDER BY count(*) DESC";
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, actividades.getPrograma());
            ps.setDate(2, actividades.getFecha_inicio());
            ps.setDate(3, actividades.getFecha_fin());
            ps.setDate(4, actividades.getFecha_inicio());
            ps.setDate(5, actividades.getFecha_fin());
            ResultSet rs = ps.executeQuery();
            
            List<Aprendiz> list = new ArrayList<>();
            Aprendiz aprendiz;
            while (rs.next()) {
                aprendiz = new Aprendiz();
                aprendiz.setDocumento_aprendiz(rs.getString("Documento_aprendiz"));
                aprendiz.setNombre_aprendiz(rs.getString("Nombres_aprendiz"));
                aprendiz.setFicha(rs.getString("Ficha"));
                aprendiz.setNombrePrograma(rs.getString("NombrePrograma"));
                aprendiz.setCoordinacion(rs.getString("Coordinacion"));
                aprendiz.setParticipaciones(rs.getString("participo"));
                list.add(aprendiz);
            }
            return (ArrayList<?>) list;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
        public ArrayList<?> getForCoorDate(Actividades actividades) {
        try {
            String sql = "SELECT A.Documento_aprendiz, A.Nombres_aprendiz, A.Genero, A.Ficha, A.NombrePrograma, A.Coordinacion, " +
                        "count(*) 'participo' FROM Aprendiz A " +
                        "INNER JOIN Actividades_Aprendiz AA ON A.idAprendiz=AA.Cod_aprendiz " +
                        "INNER JOIN Actividades ac ON AA.Cod_actividad=ac.Id_actividad " +
                        "WHERE A.Coordinacion = ? AND ac.Fecha_inicio BETWEEN ? AND ? "  +
                        "AND ac.Fecha_fin BETWEEN ? AND ? " +
                        "group by(A.NombrePrograma) ORDER BY count(*) DESC";
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, actividades.getCoor());
            ps.setDate(2, actividades.getFecha_inicio());
            ps.setDate(3, actividades.getFecha_fin());
            ps.setDate(4, actividades.getFecha_inicio());
            ps.setDate(5, actividades.getFecha_fin());
            ResultSet rs = ps.executeQuery();
            
            List<Aprendiz> list = new ArrayList<>();
            Aprendiz aprendiz;
            while (rs.next()) {
                aprendiz = new Aprendiz();
                aprendiz.setDocumento_aprendiz(rs.getString("Documento_aprendiz"));
                aprendiz.setNombre_aprendiz(rs.getString("Nombres_aprendiz"));
                aprendiz.setFicha(rs.getString("Ficha"));
                aprendiz.setNombrePrograma(rs.getString("NombrePrograma"));
                aprendiz.setCoordinacion(rs.getString("Coordinacion"));
                aprendiz.setParticipaciones(rs.getString("participo"));
                list.add(aprendiz);
            }
            return (ArrayList<?>) list;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
        
     public ArrayList<?> getForCoorDateStudent(Actividades actividades) {
        try {
            String sql = "SELECT A.Documento_aprendiz, A.Nombres_aprendiz, A.Genero, A.Ficha, A.NombrePrograma, A.Coordinacion, " +
                        "count(*) 'participo' FROM Aprendiz A " +
                        "INNER JOIN Actividades_Aprendiz AA ON A.Documento_aprendiz=AA.Cod_aprendiz " +
                        "INNER JOIN Actividades ac ON AA.Cod_actividad=ac.Id_actividad " +
                        "WHERE A.Coordinacion = ? AND ac.Fecha_inicio BETWEEN ? AND ? "  +
                        "AND ac.Fecha_fin BETWEEN ? AND ? " +
                        "group by(A.Documento_aprendiz) ORDER BY count(*) DESC";
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, actividades.getCoor());
            ps.setDate(2, actividades.getFecha_inicio());
            ps.setDate(3, actividades.getFecha_fin());
            ps.setDate(4, actividades.getFecha_inicio());
            ps.setDate(5, actividades.getFecha_fin());
            ResultSet rs = ps.executeQuery();
            
            List<Aprendiz> list = new ArrayList<>();
            Aprendiz aprendiz;
            while (rs.next()) {
                aprendiz = new Aprendiz();
                aprendiz.setDocumento_aprendiz(rs.getString("Documento_aprendiz"));
                aprendiz.setNombre_aprendiz(rs.getString("Nombres_aprendiz"));
                aprendiz.setFicha(rs.getString("Ficha"));
                aprendiz.setNombrePrograma(rs.getString("NombrePrograma"));
                aprendiz.setCoordinacion(rs.getString("Coordinacion"));
                aprendiz.setParticipaciones(rs.getString("participo"));
                list.add(aprendiz);
            }
            return (ArrayList<?>) list;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
        
    
        
        
        
}
