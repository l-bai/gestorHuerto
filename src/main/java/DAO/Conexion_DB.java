/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Lidia Baixauli de la Villa
 */
public class Conexion_DB {
    
    public Connection abrirConexion() throws Exception{
          System.out.println("ConeBBDD");
          Connection con=null;  // instacia una conexión
        try{
            //registro del driver
            String driver = "com.mysql.cj.jdbc.Driver";
            String jdbcUrl = "jdbc:mysql://localhost:3306/gestor_huerto";
            String usuario = "root";
            String pass = "";
            
            //Class.forName(driver).newInstance();
            Class.forName(driver);
            //System.out.println("Driver " + driver + " Registrado correctamente");
            //abrir conexión con la BBDD
            //System.out.println("Conectando con la base de datos...");
            con=(DriverManager.getConnection(jdbcUrl,usuario,pass));  //crea conexión la mia
            //connec = DriverManager.getConnection(jdbcUrl,"root","");
            System.out.println("Conexión establecida con la BBDD" + jdbcUrl);
            return con;
        }
       
        catch(Exception e){
            e.printStackTrace();
            throw new Exception("Ha sido imposible establecer la conexion"+e.getMessage());
        }
    }//fin conecta
    
    public  void CerrarConexion(Connection con) throws Exception
    {
        try {
             if (con!= null) con.close();    
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Ha sido imposible cerrar la conexion"+e.getMessage());
        }    
    } 
    
    
    
}//fin class
