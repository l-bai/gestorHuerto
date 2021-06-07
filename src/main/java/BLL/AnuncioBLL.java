/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAO.Conexion_DB;
import DAO.AnuncioDAO;
import Entidades.Anuncio;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lidia Baixauli de la Villa
 */
public class AnuncioBLL {
        public List<Anuncio> listaAnuncios() throws Exception{
        List<Anuncio> listadoAnuncios = new ArrayList();
        Connection con = null;
        try{
            Conexion_DB conexion_DB = new Conexion_DB();
            con = conexion_DB.abrirConexion();// Abrimos la conexión
            AnuncioDAO anuncioDAO = new AnuncioDAO();            
            listadoAnuncios = anuncioDAO.listaAnuncios(con);
            //cierra conexion
            conexion_DB.CerrarConexion(con);
        }catch (Exception ex) {
            System.out.println("Excepcion ->"+ex.getMessage());
        }  
        return listadoAnuncios;
    }
     
        //borrado por id
    public int borraAnuncio(int id) throws Exception{
        int borrado = 0;
        Connection con = null;
        try {  
            Conexion_DB conexion_DB = new Conexion_DB();
            con = conexion_DB.abrirConexion();// Abrimos la conexión
            AnuncioDAO anunDAO=new AnuncioDAO();
            borrado = anunDAO.borraAnuncio(con, id);
            conexion_DB.CerrarConexion(con);
            
        } catch (Exception ex) {
            System.out.println("Exception-> " + ex.getMessage());
        }
        return borrado;
    }
    
    //añade anuncio de riego
        public int addAnuncio(String anuncio)throws Exception{
        int  exito=0;
        Connection con = null;
        try {  
            Conexion_DB conexion_DB = new Conexion_DB();
            con = conexion_DB.abrirConexion();// Abrimos la conexión
            AnuncioDAO anuncioDAO=new AnuncioDAO();
            exito = anuncioDAO.addAnuncio(con, anuncio);
            System.out.println(exito);
            conexion_DB.CerrarConexion(con);
            
        } catch (Exception ex) {
            System.out.println("Exception-> " + ex.getMessage());
        }
        return exito;
    }
        
        
        
}//fin clase
