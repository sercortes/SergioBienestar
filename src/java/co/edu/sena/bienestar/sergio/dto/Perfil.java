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
public class Perfil {
    
    private int idPerfil;
    private String nombre;

    public Perfil() {
    }

    public Perfil(int idPerfil, String nombre) {
        this.idPerfil = idPerfil;
        this.nombre = nombre;
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
