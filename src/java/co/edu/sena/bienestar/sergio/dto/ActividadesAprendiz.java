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
public class ActividadesAprendiz {
    
    private String idActividadAprendiz;
    private int codActividad;
    private int codAprendiz;
    private Date codAprendizFecha;

    public ActividadesAprendiz() {
    }

    public Date getCodAprendizFecha() {
        return codAprendizFecha;
    }

    public void setCodAprendizFecha(Date codAprendizFecha) {
        this.codAprendizFecha = codAprendizFecha;
    }

    public int getCodActividad() {
        return codActividad;
    }

    public void setCodActividad(int codActividad) {
        this.codActividad = codActividad;
    }

    
    
   

    public String getIdActividadAprendiz() {
        return idActividadAprendiz;
    }

    public void setIdActividadAprendiz(String idActividadAprendiz) {
        this.idActividadAprendiz = idActividadAprendiz;
    }

    

    public int getCodAprendiz() {
        return codAprendiz;
    }

    public void setCodAprendiz(int codAprendiz) {
        this.codAprendiz = codAprendiz;
    }

    @Override
    public String toString() {
        return "ActividadesAprendiz{" + "idActividadAprendiz=" + idActividadAprendiz + ", codActividad=" + codActividad + ", codAprendiz=" + codAprendiz + ", codAprendizFecha=" + codAprendizFecha + '}';
    }
    
    
    
}
