/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.bienestar.sergio.dto;

/**
 *
 * @author serfin
 */
public class Responsables {
  
    private String nombre;
    private String codigo;
    private String year;

    public Responsables() {
    }
    
    public Responsables(String nombre, String codigo, String year) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.year = year;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
    
    
    
}
