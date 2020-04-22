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

            case "DESARROLLO DE APLICACIONES PARA DISPOSITIVOS MOVILES.":
                    return "TELEINFORMÁTICA";
            
            case "ANALISIS Y DESARROLLO DE SISTEMAS DE INFORMACION":
                return "TELEINFORMÁTICA";
                
            case "ANÁLISIS Y DESARROLLO DE SISTEMAS DE INFORMACIÓN":
                return "TELEINFORMÁTICA";

            case "GESTION DE REDES DE DATOS":
                return "TELEINFORMÁTICA";
                
            case "GESTIÓN DE REDES DE DATOS":
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
            
            case "PRODUCCIÓN DE MEDIOS AUDIOVISUALES DIGITALES":
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
                
            case "OPERACIÓN DE EVENTOS":
                return "LOGÍSTICA";
                
            case "LOGÍSTICA EN ALMACENES DE CADENA":
                return "LOGÍSTICA";
                
            case "SERVICIOS DE APOYO LOGISTICO":
                return "LOGÍSTICA";
                
            case "DISTRIBUCIÓN FÍSICA INTERNACIONAL":
                return "LOGÍSTICA";
                
             case "OPERACIONES COMERCIALES EN ALMACENES DE CADENA":
                return "LOGÍSTICA"; 
                
            case "DESARROLLO DE OPERACIONES LOGÍSTICAS EN LA CADENA DE ABASTECIMIENTO":
                return "LOGÍSTICA"; 
                
                
            case "DIRECCIÓN DE VENTAS":
                return "MERCADEO";
                
            case "COMERCIALIZACIÓN DE PRODUCTOS MASIVOS":
                return "MERCADEO";
                 
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
                
            case "GESTIÓN COMERCIAL Y TELEMERCADEO EN CONTACT CENTER":
                return "MERCADEO";

            case "BILINGUAL EXPERT ON BUSINESS PROCESS OUTSOURCING":
                return "MERCADEO";
                
            case "ASESORIA COMERCIAL":
                    return "MERCADEO";
                    
                    
            case "EXCEL BASICO":
                    return "VIRTUALIZACIÓN";
                    
            case "ENGLISH B2 FOCUSED ON THE BUSINESS PROCESSES INDUSTRY	":
                    return "VIRTUALIZACIÓN";
                    
            case "ENGLISH B1 FOCUSED ON THE BUSINESS PROCESSES INDUSTRY":
                    return "VIRTUALIZACIÓN";
                
            case "EMPRENDEDOR EN ESTRATEGIAS DE COMERCIALIZACIÓN PARA LA PRODUCTIVIDAD RURAL":
                    return "VIRTUALIZACIÓN";
                
            case "AUTOCAD 2D":
                    return "VIRTUALIZACIÓN";
                
            case "MANEJO DE 3D STUDIO MAX DESIGN CONCEPTOS GENERALES":
                    return "VIRTUALIZACIÓN";
                
            case "MANEJO Y ADMINISTRACIÓN DE DATOS CON LAS HERRAMIENTAS BÁSICAS DE ACCESS MS":
                    return "VIRTUALIZACIÓN";
                
            case "APLICACION DE TECNICAS DE DIAGNOSTICO EN CIBERSEGURIDAD":
                    return "VIRTUALIZACIÓN";
                
            case "DISEÑAR PÁGINAS WEB CON HTML Y JAVASCRIP":
                    return "VIRTUALIZACIÓN";
                    
                    
            default:
                return "n/a";
        }

    }
       
     public static String generateUrl(String url) {
        String[] parts = url.split(",");

        String url1 = parts[1];
        String[] parts2 = url1.split("=");

        return parts2[1];
    }
 
     
}