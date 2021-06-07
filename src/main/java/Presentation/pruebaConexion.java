/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Lidia Baixauli de la Villa
 */
public class pruebaConexion {

    public pruebaConexion() {
    }
    
    private Connection connec = null;
    private Statement sentenciaSQL = null;
    private ResultSet rs = null;

     public void conecta() throws SQLException{
          System.out.println("ConeBBDD");
        try{
            //registro del driver
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver).newInstance();
            System.out.println("Driver " + driver + " Registrado correctamente");
            //abrir conexión con la BBDD
            System.out.println("Conectando con la base de datos...");
            String jdbcUrl = "jdbc:mysql://localhost:3306/alquiler";
            connec = DriverManager.getConnection(jdbcUrl,"root","");
            System.out.println("Conexión establecida con la BBDD");
        
        }
        catch(SQLException e){
            //errores JDBC
            e.printStackTrace();
        }catch(Exception e){
            //errores class for name
            e.printStackTrace();
        }
    }
}
