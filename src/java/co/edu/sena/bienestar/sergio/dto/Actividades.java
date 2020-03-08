/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.bienestar.sergio.dto;

import java.sql.Date;

/**
 *
 * @author serfin
 */
public class Actividades {
    
    private int idRealActividad;
    
    private String id_actividades;
    private String nombre_actividad;
    private String tipo_actividad;
    private Date fecha_inicio;
    private Date fecha_fin;
    private ActividadesAprendiz actividadesAprendiz;
    
    private String responsable;
    
    private String cantidad;
    
    private String programa;
    
    private String coor;

    public Actividades() {
    }

    public Actividades(String nombre_actividad) {
        this.nombre_actividad = nombre_actividad;
    }

  

    
    
    public Actividades(String nombre_actividad, String tipo_actividad, Date fecha_inicio, Date fecha_fin) {
        this.nombre_actividad = nombre_actividad;
        this.tipo_actividad = tipo_actividad;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
    }

    public String getId_actividades() {
        return id_actividades;
    }

    public void setId_actividades(String id_actividades) {
        this.id_actividades = id_actividades;
    }

    public String getNombre_actividad() {
        return nombre_actividad;
    }

    public void setNombre_actividad(String nombre_actividad) {
        this.nombre_actividad = nombre_actividad;
    }

    public String getTipo_actividad() {
        return tipo_actividad;
    }

    public void setTipo_actividad(String tipo_actividad) {
        this.tipo_actividad = tipo_actividad;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = Date.valueOf(fecha_inicio);
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = Date.valueOf(fecha_fin);
    }

  

    public ActividadesAprendiz getActividadesAprendiz() {
        return actividadesAprendiz;
    }

    public void setActividadesAprendiz(ActividadesAprendiz actividadesAprendiz) {
        this.actividadesAprendiz = actividadesAprendiz;
    }

    @Override
    public String toString() {
        return "Actividades{" + "idRealActividad=" + idRealActividad + ", id_actividades=" + id_actividades + ", nombre_actividad=" + nombre_actividad + ", tipo_actividad=" + tipo_actividad + ", fecha_inicio=" + fecha_inicio + ", fecha_fin=" + fecha_fin + ", actividadesAprendiz=" + actividadesAprendiz + ", responsable=" + responsable + ", cantidad=" + cantidad + ", programa=" + programa + ", coor=" + coor + '}';
    }

    

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getCoor() {
        return coor;
    }

    public void setCoor(String coor) {
        this.coor = coor;
    }

    public int getIdRealActividad() {
        return idRealActividad;
    }

    public void setIdRealActividad(int idRealActividad) {
        this.idRealActividad = idRealActividad;
    }
    
    
    
    
    
}
