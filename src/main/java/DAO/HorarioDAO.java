/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Entidades.Horario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lidia Baixauli de la Villa
 */
public class HorarioDAO {
    ResultSet rs = null;
    PreparedStatement stmt = null;
    String sql;
    
    private void obtenFilaHorario(ResultSet rs, Horario horario)throws SQLException{
       horario.setId(rs.getInt("id"));
       horario.setDescripcion(rs.getString("descripcion"));
      
    }
    
    public List<Horario> listaHorarios(Connection con) throws Exception{
        List<Horario> listaHorarios = new ArrayList();
        try{
            sql = "SELECT * FROM riego"; //WHERE rol != 0";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();            
            Horario horario = null;
            
            while(rs.next()){
                horario = new Horario();
                obtenFilaHorario(rs,horario);
                listaHorarios.add(horario);
            }        
        
        }catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al buscar los horarios de riego "+ex.getMessage());
        } finally
        {
            if (rs != null) rs.close(); //Cerramos el resulset
            if (stmt != null) stmt.close();//Cerramos el Statement 
        }
        return listaHorarios;
    
    }
    
    //borra horario por id
    public int borraHorario(Connection con, int id)throws Exception{
        int borrado = 0;
        try{
           sql = "DELETE FROM riego WHERE id=?" ;
           stmt = con.prepareStatement(sql);
           stmt.setInt(1, id);
           borrado = stmt.executeUpdate();
          
            
        }catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al borrar el horario "+ex.getMessage());
        } finally
        {
            if (rs != null) rs.close(); //Cerramos el resulset
            if (stmt != null) stmt.close();//Cerramos el Statement 
        }

        return borrado;
    }
    
    
    
    
    //añade horario por descripcion
    public int  addHorarioRiego(Connection con, String horario)throws Exception{
        int exito=0;
        try{
           sql = "INSERT INTO riego (id, descripcion) VALUES (NULL, ?)" ;
           stmt = con.prepareStatement(sql);
           stmt.setString(1, horario);
           
           //exito= stmt.execute();
           exito =stmt.executeUpdate();
     
        }catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al añadir el nuevo horario "+ex.getMessage());
        } finally
        {
            if (rs != null) rs.close(); //Cerramos el resulset
            if (stmt != null) stmt.close();//Cerramos el Statement 
        }
        return exito;
    
    }
    
    
}//FIN CLASS
