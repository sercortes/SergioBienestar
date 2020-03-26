
package co.edu.sena.bienestar.sergio.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {

    private static String bd = "Bienestar";
    private static String user = "root";
    private static String pass = "";
    private static String url = "jdbc:mysql://localhost/" + bd +"?useUnicode=true&amp;characterEncoding=utf8";
    private Connection conn = null;
    
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
    
     public static void close(ResultSet rs){
        try {
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public static void close(PreparedStatement stmt){
        try {
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public static void close(Connection conn){
        try {
            conn.close();
        } catch (SQLException ex) {
           ex.printStackTrace(System.out);
        }
    }
    
    
}
