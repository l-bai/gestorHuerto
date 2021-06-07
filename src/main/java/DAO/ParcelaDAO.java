/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entidades.Parcela;
import Entidades.Usuario;
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
public class ParcelaDAO {
    ResultSet rs = null;
    PreparedStatement stmt = null;
    String sql;
    
    private void obtenDatosParcela(ResultSet rs,Parcela parcela) throws SQLException{
        parcela.setId(rs.getString("id"));
        //se añade solo el email del usuario
        //parcela.getUsuario().setEmail(rs.getString("usuario"));
        parcela.setSuperficie(rs.getFloat("superficie"));

    }
    
    public List<Parcela> disponibles(Connection con) throws Exception{
        List<Parcela> listaParcelas = new ArrayList();
        try{
            sql = "SELECT * FROM parcelas WHERE usuario is NULL"; //WHERE usuario != "null"
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();            
            Parcela parcela = null;
            
            while(rs.next()){
                parcela = new Parcela();
                obtenDatosParcela(rs,parcela);
                listaParcelas.add(parcela);
            }        
        
        }catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al buscar las parcelas disponibles "+ex.getMessage());
        } finally
        {
            if (rs != null) rs.close(); //Cerramos el resulset
            if (stmt != null) stmt.close();//Cerramos el Statement 
        }
        return listaParcelas;
    }
    
    public int asignaUsuario(Connection con, String parcela, Usuario user) throws Exception{
        int exito=0;
        //ResultSet rs = null;
        //PreparedStatement stmt = null;
        try{
           sql = "UPDATE parcelas SET usuario = ? "
                   + "WHERE id = ?" ;
           // System.out.println(sql);
           stmt = con.prepareStatement(sql);
           stmt.setString(1, user.getEmail());
           stmt.setString(2, parcela);           
           exito= stmt.executeUpdate();
   
        }catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al ASIGNAR la parcela al usuario "+ex.getMessage());
        } finally
        {
            if (rs != null) rs.close(); //Cerramos el resulset
            if (stmt != null) stmt.close();//Cerramos el Statement 
        }
        return exito;    
    }
    
    public Parcela buscaPorEmail(Connection con,String email) throws Exception{
        //Parcela parcela = new Parcela();
        Parcela parcela =null;
        try{
            sql = "SELECT * FROM parcelas WHERE usuario = ?"; //WHERE usuario != "null"
            stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            rs = stmt.executeQuery();            
            //parcela = null;
            
            while(rs.next()){
                parcela = new Parcela();
                obtenDatosParcela(rs,parcela);
               
            }        
        
        }catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al buscar la parcela "+ex.getMessage());
        } finally
        {
            if (rs != null) rs.close(); //Cerramos el resulset
            if (stmt != null) stmt.close();//Cerramos el Statement 
        }
        return parcela;
        
    }
    
    public int desasignaUsuario(Connection con, Parcela parcela) throws Exception{
        int exito=0;
        //ResultSet rs = null;
        //PreparedStatement stmt = null;
        try{
           sql = "UPDATE parcelas SET usuario = null "
                   + "WHERE id = ?" ;
           // System.out.println(sql);
           stmt = con.prepareStatement(sql);
           stmt.setString(1, parcela.getId());           
           exito= stmt.executeUpdate();
   
        }catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al ASIGNAR la parcela al usuario "+ex.getMessage());
        } finally
        {
            if (rs != null) rs.close(); //Cerramos el resulset
            if (stmt != null) stmt.close();//Cerramos el Statement 
        }
        return exito;    
    }
    
}//fin clase
