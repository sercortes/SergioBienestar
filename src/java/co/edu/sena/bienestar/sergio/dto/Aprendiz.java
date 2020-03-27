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
public class Aprendiz {
    
    private int id_aprendiz;
    private String tipo_documento;
    private String documento_aprendiz;
    private String nombre_aprendiz;
    private String email_aprendiz;
    private String municipio;
    private String genero;
    private Date fecha_nacimiento;
    private String tipo_poblacion;
    private String eps;
    private String estrato;
    private String ficha;
    private String nombrePrograma;
    private String nivelFormacion;

    private Actividades actividades;
    private ActividadesAprendiz actividadesAprendiz;
    
    private String coordinacion;
    private String participaciones;
    
    private String label;
    private String y;
    
    

    public String getCoordinacion() {
        return coordinacion;
    }

    public void setCoordinacion(String coordinacion) {
        this.coordinacion = coordinacion;
    }
    
    public Aprendiz() {
    
    }

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public int getId_aprendiz() {
        return id_aprendiz;
    }

    public void setId_aprendiz(int id_aprendiz) {
        this.id_aprendiz = id_aprendiz;
    }
    
  

  

    public String getDocumento_aprendiz() {
        return documento_aprendiz;
    }

    public void setDocumento_aprendiz(String documento_aprendiz) {
        this.documento_aprendiz = documento_aprendiz;
    }

    public String getNombre_aprendiz() {
        return nombre_aprendiz;
    }

    public void setNombre_aprendiz(String nombre_aprendiz) {
        this.nombre_aprendiz = nombre_aprendiz;
    }

    public String getEmail_aprendiz() {
        return email_aprendiz;
    }

    public void setEmail_aprendiz(String email_aprendiz) {
        this.email_aprendiz = email_aprendiz;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha) {
        this.fecha_nacimiento = Date.valueOf(fecha);
    }

    public String getTipo_poblacion() {
        return tipo_poblacion;
    }

    public void setTipo_poblacion(String tipo_poblacion) {
        this.tipo_poblacion = tipo_poblacion;
    }

    public String getEps() {
        return eps;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }

    public String getEstrato() {
        return estrato;
    }

    public void setEstrato(String estrato) {
        this.estrato = estrato;
    }

    public String getFicha() {
        return ficha;
    }

    public void setFicha(String ficha) {
        this.ficha = ficha;
    }

    public String getNombrePrograma() {
        return nombrePrograma;
    }

    public void setNombrePrograma(String nombrePrograma) {
        this.nombrePrograma = nombrePrograma;
    }

    public String getNivelFormacion() {
        return nivelFormacion;
    }

    public void setNivelFormacion(String nivelFormacion) {
        this.nivelFormacion = nivelFormacion;
    }

    public Actividades getActividades() {
        return actividades;
    }

    public void setActividades(Actividades actividades) {
        this.actividades = actividades;
    }

    public ActividadesAprendiz getActividadesAprendiz() {
        return actividadesAprendiz;
    }

    public void setActividadesAprendiz(ActividadesAprendiz actividadesAprendiz) {
        this.actividadesAprendiz = actividadesAprendiz;
    }

 

    public String getParticipaciones() {
        return participaciones;
    }

    public void setParticipaciones(String participaciones) {
        this.participaciones = participaciones;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Aprendiz{" + "id_aprendiz=" + id_aprendiz + ", tipo_documento=" + tipo_documento + ", documento_aprendiz=" + documento_aprendiz + ", nombre_aprendiz=" + nombre_aprendiz + ", email_aprendiz=" + email_aprendiz + ", municipio=" + municipio + ", genero=" + genero + ", fecha_nacimiento=" + fecha_nacimiento + ", tipo_poblacion=" + tipo_poblacion + ", eps=" + eps + ", estrato=" + estrato + ", ficha=" + ficha + ", nombrePrograma=" + nombrePrograma + ", nivelFormacion=" + nivelFormacion + ", actividades=" + actividades + ", actividadesAprendiz=" + actividadesAprendiz + ", coordinacion=" + coordinacion + ", participaciones=" + participaciones + ", label=" + label + ", y=" + y + '}';
    }

 

    
    
}
