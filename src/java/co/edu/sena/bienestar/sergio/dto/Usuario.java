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
public class Usuario {
    
    private int id;
    private String nombre;
    private String apellido;
    private String email;
    private String pass;
    private String perfil;
    private String estatus;

    private Perfil perfilO;
    
    public Usuario() {
    }

    public Usuario(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }

    
    
    public Usuario(String nombre, String apellido, String email, String pass, String perfil, String estatus) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.pass = pass;
        this.perfil = perfil;
        this.estatus = estatus;
    }

    public Usuario(int id, String nombre, String apellido, String email, String pass, String perfil, String estatus) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.pass = pass;
        this.perfil = perfil;
        this.estatus = estatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Perfil getPerfilO() {
        return perfilO;
    }

    public void setPerfilO(Perfil perfilO) {
        this.perfilO = perfilO;
    }
    
    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", pass=" + pass + ", perfil=" + perfil + ", estatus=" + estatus + ", perfilO=" + perfilO + '}';
    }
    
}
