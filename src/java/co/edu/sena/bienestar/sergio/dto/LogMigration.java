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
public class LogMigration {
   
    private String id;
    private String email;
    private String registros;
    private Date fechaModif;

    public LogMigration() {
    }

    public LogMigration(String email, String registros) {
        this.email = email;
        this.registros = registros;
    }

    public LogMigration(String id, String email, String registros, Date fechaModif) {
        this.id = id;
        this.email = email;
        this.registros = registros;
        this.fechaModif = fechaModif;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegistros() {
        return registros;
    }

    public void setRegistros(String registros) {
        this.registros = registros;
    }

    public Date getFechaModif() {
        return fechaModif;
    }

    public void setFechaModif(Date fechaModif) {
        this.fechaModif = fechaModif;
    }

    @Override
    public String toString() {
        return "LogMigration{" + "id=" + id + ", email=" + email + ", registros=" + registros + ", fechaModif=" + fechaModif + '}';
    }
    
    
    
}
