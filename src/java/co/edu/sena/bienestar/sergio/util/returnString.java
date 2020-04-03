/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.bienestar.sergio.util;

/**
 *
 * @author serfin
 */
public class returnString {
    
       public static String getCoordinacion(String programa) {
        switch (programa) {

            case "ANALISIS Y DESARROLLO DE SISTEMAS DE INFORMACION":
                return "TELEINFORMÁTICA";

            case "GESTION DE REDES DE DATOS":
                return "TELEINFORMÁTICA";

            case "MANTENIMIENTO DE EQUIPOS DE COMPUTO, DISEÑO E INSTALACION DE CABLEADO ESTRUCTURADO":
                return "TELEINFORMÁTICA";

            case "DESARROLLO DE VIDEOJUEGOS":
                return "TELEINFORMÁTICA";

            case "SISTEMAS":
                return "TELEINFORMÁTICA";

            case "MANTENIMIENTO DE EQUIPOS DE COMPUTO.":
                return "TELEINFORMÁTICA";

            case "PROGRAMACION DE SOFTWARE":
                return "TELEINFORMÁTICA";

            case "ANIMACION 3D":
                return "INDUSTRIAS CREATIVAS";

            case "ESCRITURA PARA PRODUCTOS AUDIOVISUALES":
                return "INDUSTRIAS CREATIVAS";

            case "OPERACIÓN DE CÁMARAS Y LUCES DE TELEVISIÓN":
                return "INDUSTRIAS CREATIVAS";

            case "PRODUCCION DE MEDIOS AUDIOVISUALES DIGITALES":
                return "INDUSTRIAS CREATIVAS";

            case "PRODUCCION DE MULTIMEDIA":
                return "INDUSTRIAS CREATIVAS";

            case "DISEÑO E INTEGRACION DE MULTIMEDIA":
                return "INDUSTRIAS CREATIVAS";

            case "GESTIÓN LOGÍSTICA":
                return "LOGÍSTICA";

            case "COMERCIO INTERNACIONAL":
                return "LOGÍSTICA";

            case "APOYO LOGÍSTICO EN EVENTOS Y SERVICIOS EMPRESARIALES":
                return "LOGÍSTICA";
                
            case "DESARROLLO DE OPERACIONES LOGÍSTICA EN LA CADENA DE ABASTECIMIENTO":
                return "LOGÍSTICA";
                
            case "ALMACENAMIENTO,EMPAQUE Y EMBALAJE DE OBJETOS.":
                return "LOGÍSTICA";
                
            case "LOGÍSTICA EMPRESARIAL":
                return "LOGÍSTICA";
                
            case "LOGÍSTICA DEL TRANSPORTE":
                return "LOGÍSTICA";
                
            case "DISEÑO Y DESARROLLO DE INVESTIGACIONES DE MERCADO":
                return "MERCADEO";
                
            case "NEGOCIACIÓN INTERNACIONAL":
                return "MERCADEO";

            case "COMUNICACIÓN COMERCIAL":
                return "MERCADEO";

            case "GESTIÓN DE MERCADOS":
                return "MERCADEO";

            case "OPERACIONES COMERCIALES":
                return "MERCADEO";

            case "VENTA DE PRODUCTOS Y SERVICIOS":
                return "MERCADEO";

            case "GESTION COMERCIAL Y TELEMERCADEO EN CONTACT CENTER":
                return "MERCADEO";

            case "BILINGUAL EXPERT ON BUSINESS PROCESS OUTSOURCING":
                return "MERCADEO";

            default:
                return "****";
        }

    }

    public static String getResponsable(String res) {

        String start= res.substring(0, 13);
        
        if (start.indexOf("P01") != -1) {
            return "VIVIANA PARDO";

        } else if (start.indexOf("P02") != -1) {
            return "VIVIANA SIERRA";

        } else if (start.indexOf("P03") != -1) {
            return "Elizabeth Díaz";

        } else if (start.indexOf("P04") != -1) {
            return "Angie Estepa";

        } else if (start.indexOf("P05") != -1) {
            return "Teódulo Quintero";

        } else if (start.indexOf("P06") != -1) {
            return "Gina Duarte";

        } else if (start.indexOf("P07") != -1) {
            return "Solanyhi Rodriguez";

        } else if (start.indexOf("P08") != -1) {
            return "Sebastián Urrea";

        } else if (start.indexOf("P09") != -1) {
            return "Cesar Díaz";

        } else if (start.indexOf("P10") != -1) {
            return "Daniel Galeano";

        } else if (start.indexOf("P11") != -1) {
            return "Edilma Silva";

        } else if (start.indexOf("P12") != -1) {
            return "Floralba Ramos";

        } else if (start.indexOf("P13") != -1) {
            return "Pilar Duarte";

        } else if (start.indexOf("P14") != -1) {
            return "Christian Riveros";

        } else if (start.indexOf("P15") != -1) {
            return "Esmeralda Clavijo";

        } else if (start.indexOf("P16") != -1) {
            return "Alejandra Torres";

        } else if (start.indexOf("P17") != -1) {
            return "Masivo";

        } else {
            return " n/a";

        }
    }

    
     public static String generateUrl(String url) {
        String[] parts = url.split(",");

        String url1 = parts[1];
        String[] parts2 = url1.split("=");

        return parts2[1];
    }
    
}