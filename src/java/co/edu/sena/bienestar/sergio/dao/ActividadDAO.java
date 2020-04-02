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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author serfin
 */
public class ActividadDAO {

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    
    public ActividadDAO(Connection conn) {
        this.conn = conn;
    }

    public int insertReturn(Actividades actividades) {
        int idActividad = 0;
        String sql = "INSERT INTO Actividades (Nombre_actividad, Tipo_actividad, Fecha_inicio, Fecha_fin, responsable)"
                + "VALUES (?, ?, ?, ?, ?)";

        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, actividades.getNombre_actividad());
            ps.setString(2, actividades.getTipo_actividad());
            ps.setDate(3, actividades.getFecha_inicio());
            ps.setDate(4, actividades.getFecha_fin());
            ps.setString(5, actividades.getResponsable());

            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                idActividad = rs.getInt(1);
            }
            return idActividad;
        } catch (MySQLIntegrityConstraintViolationException e) {
            System.out.println(e);
            return 0;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }

    }
    
    public ArrayList<?> getAllByword(Actividades activi) {
        try {
            String sql = "SELECT ac.*, count(aa.Cod_aprendiz) 'cantidad' FROM Actividades ac "
                    + "INNER JOIN Actividades_Aprendiz aa ON ac.Id_actividad=aa.Cod_actividad "
                    + "INNER JOIN Aprendiz ap ON aa.Cod_aprendiz = ap.idAprendiz "
                    + "WHERE ac.Nombre_actividad LIKE ? AND "
                    + "ac.Tipo_actividad LIKE ? "
                    + "AND ac.Fecha_inicio BETWEEN ? AND ? "
                    + "AND ac.Fecha_fin BETWEEN ? AND ? "
                    + "group by ac.Id_actividad, ac.Tipo_actividad "
                    + "ORDER BY ac.Fecha_inicio ASC";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + activi.getKeyWord() + "%");
            ps.setString(2, "%" + activi.getTipo_actividad() + "%");
            ps.setDate(3, activi.getFecha_inicio());
            ps.setDate(4, activi.getFecha_fin());
            ps.setDate(5, activi.getFecha_inicio());
            ps.setDate(6, activi.getFecha_fin());
            rs = ps.executeQuery();
            List<Actividades> list = new ArrayList<>();
            Actividades actividades;
            while (rs.next()) {
                actividades = new Actividades();
                actividades.setIdRealActividad(rs.getInt("Id_actividad"));
                actividades.setNombre_actividad(rs.getString("Nombre_actividad"));
                actividades.setTipo_actividad(rs.getString("Tipo_actividad"));
                actividades.setFecha_inicio(rs.getString("Fecha_Inicio"));
                actividades.setFecha_fin(rs.getString("Fecha_fin"));
                actividades.setResponsable(rs.getString("responsable"));
                actividades.setCantidad(rs.getString("cantidad"));
                list.add(actividades);
            }
            return (ArrayList<?>) list;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
    
    public Actividades getIdActividad(Actividades actividades) {

        try {
            String sql = "SELECT Id_actividad FROM Actividades "
                    + "WHERE Nombre_actividad = ? "
                    + "AND Tipo_actividad = ? "
                    + "AND Fecha_inicio = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, actividades.getNombre_actividad());
            ps.setString(2, actividades.getTipo_actividad());
            ps.setDate(3, actividades.getFecha_inicio());
            rs = ps.executeQuery();
            Actividades acti = new Actividades();
            while (rs.next()) {

                acti.setIdRealActividad(rs.getInt("Id_actividad"));

            }
            return acti;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }
  public ArrayList<?> getStaticsByTypeEveryYear(Actividades actividades) {
        try {
            String sql = "SELECT ac.Tipo_actividad, YEAR(ac.Fecha_inicio) 'year', count(*) 'cantidad' " +
                    "FROM Actividades ac " +
                    "INNER JOIN Actividades_Aprendiz aa ON ac.Id_actividad=aa.Cod_actividad " +
                    "INNER JOIN Aprendiz ap ON aa.Cod_aprendiz = ap.idAprendiz " +
                    "WHERE ac.Tipo_actividad = ? " +
                    "AND YEAR(ac.Fecha_inicio) BETWEEN ? AND ? " +
                    "AND YEAR(ac.Fecha_fin) BETWEEN ? AND ? " +
                    "GROUP BY YEAR(ac.Fecha_inicio)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, actividades.getTipo_actividad());
            
            ps.setString(2, actividades.getYearStar());
            ps.setString(3, actividades.getYearFinish());
            ps.setString(4, actividades.getYearStar());
            ps.setString(5, actividades.getYearFinish());
            
            rs = ps.executeQuery();
            List<Actividades> list = new ArrayList<>();
            Actividades activi;
            while (rs.next()) {
                activi = new Actividades();
                activi.setLabel(rs.getString("year"));
                activi.setY(rs.getString("cantidad"));

                list.add(activi);
            }
            return (ArrayList<?>) list;
        } catch (Exception e) {
            return null;
        }
    }

   
    public ArrayList<?> getStaticsByTypeFicha(Aprendiz aprendiz) {
        try {
            String sql = "SELECT ac.Tipo_actividad, (count(ac.Tipo_actividad) * 100/ "
                    + "(select count(*) from Aprendiz ap INNER JOIN Actividades_Aprendiz aa "
                    + "ON ap.idAprendiz=aa.Cod_aprendiz "
                    + "INNER JOIN Actividades ac ON aa.Cod_actividad = ac.Id_actividad "
                    + "WHERE ap.Ficha = ? AND ac.Fecha_inicio BETWEEN ? AND ? AND ac.Fecha_fin BETWEEN ? AND ?)) 'cantidad' FROM Actividades ac "
                    + "INNER JOIN Actividades_Aprendiz aa ON ac.Id_actividad=aa.Cod_actividad "
                    + "INNER JOIN Aprendiz ap ON aa.Cod_aprendiz = ap.idAprendiz "
                    + "WHERE ap.Ficha = ? "
                    + "AND ac.Fecha_inicio BETWEEN ? AND ? "
                    + "AND ac.Fecha_fin BETWEEN ? AND ? "
                    + "GROUP BY(ac.Tipo_actividad)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, aprendiz.getFicha());
            ps.setDate(2, aprendiz.getActividades().getFecha_inicio());
            ps.setDate(3, aprendiz.getActividades().getFecha_fin());
            ps.setDate(4, aprendiz.getActividades().getFecha_inicio());
            ps.setDate(5, aprendiz.getActividades().getFecha_fin());
            ps.setString(6, aprendiz.getFicha());
            ps.setDate(7, aprendiz.getActividades().getFecha_inicio());
            ps.setDate(8, aprendiz.getActividades().getFecha_fin());
            ps.setDate(9, aprendiz.getActividades().getFecha_inicio());
            ps.setDate(10, aprendiz.getActividades().getFecha_fin());
            rs = ps.executeQuery();
            List<Actividades> list = new ArrayList<>();
            Actividades actividades;
            while (rs.next()) {
                actividades = new Actividades();
                actividades.setTipo_actividad(rs.getString("Tipo_actividad"));
                actividades.setCantidad(rs.getString("cantidad"));

                list.add(actividades);
            }
            return (ArrayList<?>) list;
        } catch (Exception e) {
            return null;
        }
    }

    public ArrayList<?> getStaticsByType(Aprendiz aprendiz) {
        try {
            String sql = "SELECT ac.Tipo_actividad, "
                    + "(count(ac.Tipo_actividad) * 100/(select count(*) from Actividades_Aprendiz aa INNER JOIN Actividades ac ON aa.Cod_actividad=ac.Id_actividad "
                    + "WHERE aa.Cod_aprendiz = ? AND ac.Fecha_inicio BETWEEN ? AND ? AND ac.Fecha_fin BETWEEN ? AND ?)) 'cantidad' "
                    + "FROM Actividades ac "
                    + "INNER JOIN Actividades_Aprendiz aa "
                    + "ON ac.Id_actividad=aa.Cod_actividad "
                    + "INNER JOIN Aprendiz ap ON aa.Cod_aprendiz = ap.idAprendiz "
                    + "WHERE ap.idAprendiz = ? "
                    + "AND ac.Fecha_inicio BETWEEN ? AND ? "
                    + "AND ac.Fecha_fin BETWEEN ? AND ? "
                    + "GROUP BY(ac.Tipo_actividad)";
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, aprendiz.getDocumento_aprendiz());
            ps.setDate(2, aprendiz.getActividades().getFecha_inicio());
            ps.setDate(3, aprendiz.getActividades().getFecha_fin());
            ps.setDate(4, aprendiz.getActividades().getFecha_inicio());
            ps.setDate(5, aprendiz.getActividades().getFecha_fin());
            
            ps.setString(6, aprendiz.getDocumento_aprendiz());
            ps.setDate(7, aprendiz.getActividades().getFecha_inicio());
            ps.setDate(8, aprendiz.getActividades().getFecha_fin());
            ps.setDate(9, aprendiz.getActividades().getFecha_inicio());
            ps.setDate(10, aprendiz.getActividades().getFecha_fin());
            
            rs = ps.executeQuery();
            List<Actividades> list = new ArrayList<>();
            Actividades actividades;
            while (rs.next()) {
                actividades = new Actividades();
                actividades.setTipo_actividad(rs.getString("Tipo_actividad"));
                actividades.setCantidad(rs.getString("cantidad"));

                list.add(actividades);
            }
            return (ArrayList<?>) list;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public ArrayList<?> getActivitysByIdAPrendiz(Aprendiz aprendiz) {
        try {
            String sql = "SELECT ac.*, ap.*, count(ac.Id_actividad) 'cantidad', TIMESTAMPDIFF(YEAR,ap.FechaNacimiento_Aprendiz,CURDATE()) AS edad "
                    + "FROM Actividades ac INNER JOIN Actividades_Aprendiz aa "
                    + "ON ac.Id_actividad=aa.Cod_actividad "
                    + "INNER JOIN Aprendiz ap "
                    + "ON aa.Cod_aprendiz = ap.idAprendiz "
                    + "WHERE ap.idAprendiz = ? "
                    + "AND ac.Fecha_inicio BETWEEN ? AND ? "
                    + "AND ac.Fecha_fin BETWEEN ? AND ? "
                    + "GROUP BY(ac.Id_actividad)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, aprendiz.getDocumento_aprendiz());
            ps.setDate(2, aprendiz.getActividades().getFecha_inicio());
            ps.setDate(3, aprendiz.getActividades().getFecha_fin());
            ps.setDate(4, aprendiz.getActividades().getFecha_inicio());
            ps.setDate(5, aprendiz.getActividades().getFecha_fin());
            rs = ps.executeQuery();
            List<Aprendiz> list = new ArrayList<>();
            Aprendiz apren;
            Actividades actividades;
            while (rs.next()) {
                apren = new Aprendiz();
                actividades = new Actividades();
                actividades.setNombre_actividad(rs.getString("Nombre_actividad"));
                actividades.setTipo_actividad(rs.getString("Tipo_actividad"));
                actividades.setFecha_inicio(rs.getString("Fecha_Inicio"));
                actividades.setFecha_fin(rs.getString("Fecha_fin"));
                actividades.setResponsable(rs.getString("responsable"));
                actividades.setCantidad(rs.getString("cantidad"));
                actividades.setIdRealActividad(rs.getInt("Id_actividad"));

                apren.setDocumento_aprendiz(rs.getString("Documento_aprendiz"));
                apren.setNombre_aprendiz(rs.getString("Nombres_aprendiz"));
                apren.setNombrePrograma(rs.getString("NombrePrograma"));
                apren.setTipo_documento(rs.getString("Tipo_documento"));
                apren.setEmail_aprendiz(rs.getString("Email_aprendiz"));
                apren.setMunicipio(rs.getString("Municipio_aprendiz"));
                apren.setFecha_nacimiento(rs.getString("FechaNacimiento_Aprendiz"));
                apren.setTipo_poblacion(rs.getString("Tipo_poblacion"));
                apren.setEstrato(rs.getString("Estrato"));
                apren.setNivelFormacion(rs.getString("Nivel_formacion"));
                apren.setY(rs.getString("edad"));

                apren.setActividades(actividades);
                list.add(apren);
            }
            return (ArrayList<?>) list;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public ArrayList<?> getActivitysByTypes(Actividades acti) {
        try {
            String sql = "SELECT ac.*, count(aa.Cod_aprendiz) 'cantidad' "
                    + "FROM Actividades ac "
                    + "INNER JOIN Actividades_Aprendiz aa "
                    + "ON ac.Id_actividad=aa.Cod_actividad "
                    + "INNER JOIN Aprendiz ap ON aa.Cod_aprendiz = ap.idAprendiz "
                    + "WHERE ac.Tipo_actividad = ? AND ac.Fecha_inicio BETWEEN ? AND ? "
                    + "AND ac.Fecha_fin BETWEEN ? AND ? "
                    + "group by(aa.Cod_actividad) ORDER BY count(aa.Cod_aprendiz) desc";
            ps = conn.prepareStatement(sql);
            ps.setString(1, acti.getTipo_actividad());
            ps.setDate(2, acti.getFecha_inicio());
            ps.setDate(3, acti.getFecha_fin());
            ps.setDate(4, acti.getFecha_inicio());
            ps.setDate(5, acti.getFecha_fin());
            ResultSet rs = ps.executeQuery();
            List<Actividades> list = new ArrayList<>();
            Actividades actividades;
            while (rs.next()) {
                actividades = new Actividades();
                actividades.setNombre_actividad(rs.getString("Nombre_actividad"));
                actividades.setTipo_actividad(rs.getString("Tipo_actividad"));
                actividades.setFecha_inicio(rs.getString("Fecha_Inicio"));
                actividades.setFecha_fin(rs.getString("Fecha_fin"));
                actividades.setResponsable(rs.getString("responsable"));
                actividades.setCantidad(rs.getString("cantidad"));
                actividades.setIdRealActividad(rs.getInt("Id_actividad"));
                list.add(actividades);
            }
            return (ArrayList<?>) list;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public ArrayList<?> getByCoor(Actividades actividades) {
        try {
            String sql = "SELECT ac.*, count(aa.Cod_aprendiz) 'cantidad' "
                    + "FROM Actividades ac "
                    + "INNER JOIN Actividades_Aprendiz aa "
                    + "ON ac.Id_actividad=aa.Cod_actividad "
                    + "INNER JOIN Aprendiz ap ON aa.Cod_aprendiz = ap.Documento_aprendiz "
                    + "WHERE ac.Tipo_actividad = ? "
                    + "group by(aa.Cod_actividad) ORDER BY count(aa.Cod_aprendiz) desc";
            ps = conn.prepareStatement(sql);
            ps.setString(1, actividades.getCoor());
            rs = ps.executeQuery();
            List<Actividades> list = new ArrayList<>();
            Actividades acti;
            while (rs.next()) {
                acti = new Actividades();
                acti.setNombre_actividad(rs.getString("Nombre_actividad"));
                acti.setTipo_actividad(rs.getString("Tipo_actividad"));
                acti.setFecha_inicio(rs.getString("Fecha_Inicio"));
                acti.setFecha_fin(rs.getString("Fecha_fin"));
                acti.setResponsable(rs.getString("responsable"));
                acti.setCantidad(rs.getString("cantidad"));
                list.add(acti);
            }
            return (ArrayList<?>) list;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public ArrayList<?> getEveryYear() {
        try {
            String sql = "SELECT YEAR(A.Fecha_inicio) year FROM Actividades A group by YEAR(A.Fecha_inicio)";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            List<Actividades> list = new ArrayList<>();
            Actividades actividades;
            while (rs.next()) {
                actividades = new Actividades();
                actividades.setY(rs.getString("year"));
                list.add(actividades);
            }
            return (ArrayList<?>) list;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
    
    public ArrayList<?> getByTypeActivity() {
        try {
            String sql = "SELECT A.Tipo_actividad FROM Actividades A group by(A.Tipo_actividad)";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            List<Actividades> list = new ArrayList<>();
            Actividades actividades;
            while (rs.next()) {
                actividades = new Actividades();
                actividades.setTipo_actividad(rs.getString("Tipo_actividad"));
                list.add(actividades);
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
