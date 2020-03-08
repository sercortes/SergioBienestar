
package co.edu.sena.bienestar.sergio.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    static String bd = "Bienestar";
    static String user = "root";
    static String pass = "";
    static String url = "jdbc:mysql://localhost/" + bd +"?useUnicode=true&amp;characterEncoding=utf8";
    
    Connection conn = null;
    
    public Conexion(){
       try{
           
           Class.forName("com.mysql.jdbc.Driver");
           conn = DriverManager.getConnection(url, user, pass);
           
         
           
       }catch(SQLException e){
           e.getMessage();
       }catch(ClassNotFoundException e){
           e.getMessage();
       }
       
    }
    
    public Connection getConnection(){
       return conn; 
    }
    
    public void disconnectDb(){
        
        if (conn != null) {
            try{
               conn.close();
            }catch(SQLException e){
                e.getMessage();
            }
        }
        
    }
    
}
