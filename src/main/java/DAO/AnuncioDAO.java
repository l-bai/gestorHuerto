/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entidades.Anuncio;
import Entidades.Anuncio;
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
public class AnuncioDAO {
    ResultSet rs = null;
    PreparedStatement stmt = null;
    String sql;
    
    private void obtenFilaAnuncio(ResultSet rs, Anuncio anuncio)throws SQLException{
       anuncio.setId(rs.getInt("id"));
       anuncio.setDescripcion(rs.getString("descripcion"));
      // rs.getDate("fecha").toString();     
       anuncio.setFecha(rs.getDate("fecha").toString());
      
    }
    
    public List<Anuncio> listaAnuncios(Connection con) throws Exception{
        List<Anuncio> listaAnuncios = new ArrayList();
        try{
            sql = "SELECT * FROM anuncios order by fecha DESC"; //WHERE rol != 0";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();            
            Anuncio horario = null;

            while(rs.next()){
                horario = new Anuncio();
                obtenFilaAnuncio(rs,horario);
                listaAnuncios.add(horario);
            }        

        }catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al buscar los anuncios "+ex.getMessage());
        } finally
        {
            if (rs != null) rs.close(); //Cerramos el resulset
            if (stmt != null) stmt.close();//Cerramos el Statement 
        }
        return listaAnuncios;

    }
    
    //borra anuncio por id
    public int borraAnuncio(Connection con, int id)throws Exception{
        int borrado = 0;
        try{
           sql = "DELETE FROM anuncios WHERE id=?" ;
           stmt = con.prepareStatement(sql);
           stmt.setInt(1, id);
           borrado = stmt.executeUpdate();
          
            
        }catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al borrar el anuncio "+ex.getMessage());
        } finally
        {
            if (rs != null) rs.close(); //Cerramos el resulset
            if (stmt != null) stmt.close();//Cerramos el Statement 
        }

        return borrado;
    }
    
    //añade anuncio por descripcion con fecha actual
    public int  addAnuncio(Connection con, String anuncio)throws Exception{
        int exito=0;
        try{
           sql = "INSERT INTO anuncios (id, descripcion,fecha) VALUES (NULL, ?,CURRENT_DATE)" ;
           stmt = con.prepareStatement(sql);
           stmt.setString(1, anuncio);
           
           //exito= stmt.execute();
           exito =stmt.executeUpdate();
     
        }catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al añadir el nuevo anuncio "+ex.getMessage());
        } finally
        {
            if (rs != null) rs.close(); //Cerramos el resulset
            if (stmt != null) stmt.close();//Cerramos el Statement 
        }
        return exito;
    
    }
    
    
    
}//fin clase
