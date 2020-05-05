/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.bienestar.sergio.dao;

import co.edu.sena.bienestar.sergio.dto.Actividades;
import co.edu.sena.bienestar.sergio.dto.Aprendiz;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author serfin
 */
public class AprendizDAO {

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public AprendizDAO(Connection conn) {
        this.conn = conn;
    }
    
    public boolean updateCoor(Aprendiz aprendiz) {
            try {
           
            String sql = "UPDATE Aprendiz set Coordinacion = ? WHERE NombrePrograma = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, aprendiz.getCoordinacion());
            ps.setString(2, aprendiz.getNombrePrograma());
            
            int rows = ps.executeUpdate();
            boolean estado = rows > 0;
            return estado;
        } catch (Exception ex) {
            System.out.println("Error edit " + ex.getMessage());
            return false;
        }
    }
    
     public Aprendiz getIdAprendiz(Aprendiz apren) {

        try {
            String sql = "SELECT idAprendiz FROM Aprendiz "
                    + "WHERE Documento_aprendiz = ? "
                    + "AND Ficha = ? LIMIT 1";
            ps = conn.prepareStatement(sql);
            ps.setString(1, apren.getDocumento_aprendiz());
            ps.setString(2, apren.getFicha());
            
            rs = ps.executeQuery();
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
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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
            rs = ps.getGeneratedKeys();
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

     public ArrayList<?> getStatiticsByActivity(String acti) {
        try {
            String sql = "SELECT ap.NombrePrograma, count(*) cantidad " +
                        "FROM Actividades ac  " +
                        "INNER JOIN Actividades_Aprendiz aa " +
                        "ON ac.Id_actividad=aa.Cod_actividad " +
                        "INNER JOIN Aprendiz ap ON aa.Cod_aprendiz = ap.idAprendiz " +
                        "WHERE ac.Id_actividad = ?" +
                        "group by(ap.NombrePrograma)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, acti);
            rs = ps.executeQuery();
            List<Aprendiz> list = new ArrayList<>();
            Aprendiz aprendiz;
            while (rs.next()) {
                aprendiz = new Aprendiz();
                aprendiz.setNombrePrograma(rs.getString("NombrePrograma"));
                aprendiz.setParticipaciones(rs.getString("cantidad"));
                list.add(aprendiz);
            }
            return (ArrayList<?>) list;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
    
    public ArrayList<?> getProgramsWithOutCoor() {
        try {
            String sql = "SELECT ap.Nivel_formacion, ap.NombrePrograma, count(*) cantidad "
                    + "FROM Actividades ac INNER JOIN Actividades_Aprendiz aa "
                    + "ON ac.Id_actividad=aa.Cod_actividad INNER JOIN Aprendiz ap "
                    + "ON aa.Cod_aprendiz = ap.idAprendiz WHERE ap.Coordinacion = 'n/a' "
                    + "group by(ap.NombrePrograma) order by(cantidad) desc";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            List<Aprendiz> list = new ArrayList<>();
            Aprendiz aprendiz;
            while (rs.next()) {
                aprendiz = new Aprendiz();
                aprendiz.setNivelFormacion(rs.getString("Nivel_formacion"));
                aprendiz.setNombrePrograma(rs.getString("NombrePrograma"));
                aprendiz.setParticipaciones(rs.getString("cantidad"));
                list.add(aprendiz);
            }
            return (ArrayList<?>) list;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
    
    
    
    
      public ArrayList<?> getByActivity(String acti) {
        try {
            String sql = "SELECT ap.*  " +
                        "FROM Actividades ac  " +
                        "INNER JOIN Actividades_Aprendiz aa  " +
                        "ON ac.Id_actividad=aa.Cod_actividad " +
                        "INNER JOIN Aprendiz ap ON aa.Cod_aprendiz = ap.idAprendiz " +
                        "WHERE ac.Id_actividad = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, acti);
            rs = ps.executeQuery();
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
                list.add(aprendiz);
            }
            return (ArrayList<?>) list;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
        public ArrayList<?> getAllByDate(Aprendiz aprendiz) {
        try {
            String sql = "SELECT ap.Documento_aprendiz, ap.Nombres_aprendiz, ap.idAprendiz, ap.NombrePrograma, count(aa.Cod_aprendiz) 'cantidad' "
                    + "FROM Actividades ac INNER JOIN Actividades_Aprendiz aa "
                    + "ON ac.Id_actividad=aa.Cod_actividad INNER JOIN Aprendiz "
                    + "ap ON aa.Cod_aprendiz = ap.idAprendiz WHERE "
                    + "ac.Fecha_inicio BETWEEN ? AND ? "
                    + "AND ac.Fecha_fin BETWEEN ? AND ? "
                    + "GROUP BY(ap.idAprendiz) ORDER BY `cantidad` DESC";
            ps = conn.prepareStatement(sql);
           
            ps.setDate(1, aprendiz.getActividades().getFecha_inicio());
            ps.setDate(2, aprendiz.getActividades().getFecha_fin());
            ps.setDate(3, aprendiz.getActividades().getFecha_inicio());
            ps.setDate(4, aprendiz.getActividades().getFecha_fin());
             
        
            rs = ps.executeQuery();
            List<Aprendiz> list = new ArrayList<>();
            Aprendiz apren;
            Actividades acti;
            while (rs.next()) {
                apren = new Aprendiz();
                acti = new Actividades();
                apren.setDocumento_aprendiz(rs.getString("Documento_aprendiz"));
                apren.setNombre_aprendiz(rs.getString("Nombres_aprendiz"));
                apren.setId_aprendiz(rs.getInt("idAprendiz"));
                apren.setNombrePrograma(rs.getString("NombrePrograma"));
                
                acti.setCantidad(rs.getString("cantidad"));
                apren.setActividades(acti);
                list.add(apren);
            }
            return (ArrayList<?>) list;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    public ArrayList<?> getAllByNumber(Aprendiz aprendiz) {
        try {
            String sql = "SELECT ap.Documento_aprendiz, ap.Nombres_aprendiz, ap.idAprendiz, ap.NombrePrograma, count(aa.Cod_aprendiz) 'cantidad' "
                    + "FROM Actividades ac INNER JOIN Actividades_Aprendiz aa "
                    + "ON ac.Id_actividad=aa.Cod_actividad INNER JOIN Aprendiz "
                    + "ap ON aa.Cod_aprendiz = ap.idAprendiz WHERE "
                    + "ac.Fecha_inicio BETWEEN ? AND ? "
                    + "AND ac.Fecha_fin BETWEEN ? AND ? "
                    + "AND ap.Documento_aprendiz = ? OR "
                    + "ac.Fecha_inicio BETWEEN ? AND ? "
                    + "AND ac.Fecha_fin BETWEEN ? AND ? "
                    + "AND ap.Nombres_aprendiz LIKE ? "
                    + "GROUP BY(ap.idAprendiz) ORDER BY `cantidad` DESC";
            ps = conn.prepareStatement(sql);
           
            ps.setDate(1, aprendiz.getActividades().getFecha_inicio());
            ps.setDate(2, aprendiz.getActividades().getFecha_fin());
            ps.setDate(3, aprendiz.getActividades().getFecha_inicio());
            ps.setDate(4, aprendiz.getActividades().getFecha_fin());
            ps.setString(5, aprendiz.getDocumento_aprendiz());
            
               ps.setDate(6, aprendiz.getActividades().getFecha_inicio());
            ps.setDate(7, aprendiz.getActividades().getFecha_fin());
            ps.setDate(8, aprendiz.getActividades().getFecha_inicio());
            ps.setDate(9, aprendiz.getActividades().getFecha_fin());
              ps.setString(10, "%"+aprendiz.getNombre_aprendiz()+"%");
            
            rs = ps.executeQuery();
            List<Aprendiz> list = new ArrayList<>();
            Aprendiz apren;
            Actividades acti;
            while (rs.next()) {
                apren = new Aprendiz();
                acti = new Actividades();
                apren.setDocumento_aprendiz(rs.getString("Documento_aprendiz"));
                apren.setNombre_aprendiz(rs.getString("Nombres_aprendiz"));
                apren.setId_aprendiz(rs.getInt("idAprendiz"));
                apren.setNombrePrograma(rs.getString("NombrePrograma"));
                
                acti.setCantidad(rs.getString("cantidad"));
                apren.setActividades(acti);
                list.add(apren);
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
            ps = conn.prepareStatement(sql);
            ps.setString(1, aprendiz.getFicha());
            
            ps.setDate(2, aprendiz.getActividades().getFecha_inicio());
            ps.setDate(3, aprendiz.getActividades().getFecha_fin());
            ps.setDate(4, aprendiz.getActividades().getFecha_inicio());
            ps.setDate(5, aprendiz.getActividades().getFecha_fin());
            
            rs = ps.executeQuery();
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
            ps = conn.prepareStatement(sql);
            ps.setString(1, apren.getNombrePrograma());
            ps.setDate(2, apren.getActividades().getFecha_inicio());
            ps.setDate(3, apren.getActividades().getFecha_fin());
            ps.setDate(4, apren.getActividades().getFecha_inicio());
            ps.setDate(5, apren.getActividades().getFecha_fin());
            rs = ps.executeQuery();
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

     
       

      
     public ArrayList<?> getCoordinacion() {
        try {
            String sql = "SELECT A.Coordinacion FROM Aprendiz A group by(A.Coordinacion)";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
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
     
      public ArrayList<?> getPrograms() {
        try {
            String sql = "SELECT A.NombrePrograma FROM Aprendiz A WHERE A.Coordinacion != 'VIRTUALIZACION' group by(A.NombrePrograma)";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
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
    
       
        public ArrayList<?> getForProgramaDate(Actividades actividades) {
        try {
            String sql = "SELECT A.Documento_aprendiz, A.Nombres_aprendiz, A.Genero, A.Ficha, A.NombrePrograma, A.Coordinacion, " +
                        "count(*) 'participo' FROM Aprendiz A " +
                        "INNER JOIN Actividades_Aprendiz AA ON A.Documento_aprendiz=AA.Cod_aprendiz " +
                        "INNER JOIN Actividades ac ON AA.Cod_actividad=ac.Id_actividad " +
                        "WHERE A.NombrePrograma = ? AND ac.Fecha_inicio BETWEEN ? AND ? "  +
                        "AND ac.Fecha_fin BETWEEN ? AND ? " +
                        "group by(A.Ficha) ORDER BY count(*) DESC";
            ps = conn.prepareStatement(sql);
            ps.setString(1, actividades.getPrograma());
            ps.setDate(2, actividades.getFecha_inicio());
            ps.setDate(3, actividades.getFecha_fin());
            ps.setDate(4, actividades.getFecha_inicio());
            ps.setDate(5, actividades.getFecha_fin());
            rs = ps.executeQuery();
            
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
            ps = conn.prepareStatement(sql);
            ps.setString(1, actividades.getPrograma());
            ps.setDate(2, actividades.getFecha_inicio());
            ps.setDate(3, actividades.getFecha_fin());
            ps.setDate(4, actividades.getFecha_inicio());
            ps.setDate(5, actividades.getFecha_fin());
            rs = ps.executeQuery();
            
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
     
                 public ArrayList<?> getByYearCoor(Actividades actividades) {
        try {
            String sql = "SELECT TIMESTAMPDIFF(YEAR,A.FechaNacimiento_Aprendiz,CURDATE()) AS edad, "
                    + "count(*) valor FROM Aprendiz A INNER JOIN Actividades_Aprendiz AA "
                    + "ON A.idAprendiz=AA.Cod_aprendiz INNER JOIN Actividades ac ON "
                    + "AA.Cod_actividad=ac.Id_actividad WHERE A.Coordinacion = ? "
                    + "AND ac.Fecha_inicio BETWEEN ? AND ? "
                    + "AND ac.Fecha_fin BETWEEN ? AND ? group BY(edad)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, actividades.getCoor());
            ps.setDate(2, actividades.getFecha_inicio());
            ps.setDate(3, actividades.getFecha_fin());
            ps.setDate(4, actividades.getFecha_inicio());
            ps.setDate(5, actividades.getFecha_fin());
            rs = ps.executeQuery();
            
            List<Aprendiz> list = new ArrayList<>();
            Aprendiz aprendiz;
            while (rs.next()) {
                aprendiz = new Aprendiz();
                aprendiz.setLabel(rs.getString("edad"));
                aprendiz.setY(rs.getString("valor"));
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
            ps = conn.prepareStatement(sql);
            ps.setString(1, actividades.getCoor());
            ps.setDate(2, actividades.getFecha_inicio());
            ps.setDate(3, actividades.getFecha_fin());
            ps.setDate(4, actividades.getFecha_inicio());
            ps.setDate(5, actividades.getFecha_fin());
            rs = ps.executeQuery();
            
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
            ps = conn.prepareStatement(sql);
            ps.setString(1, actividades.getCoor());
            ps.setDate(2, actividades.getFecha_inicio());
            ps.setDate(3, actividades.getFecha_fin());
            ps.setDate(4, actividades.getFecha_inicio());
            ps.setDate(5, actividades.getFecha_fin());
            rs = ps.executeQuery();
            
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
        
    
       public void CloseAll(){
        Conexion.close(conn);
        Conexion.close(ps);
        Conexion.close(rs);
    } 
        
        
}
