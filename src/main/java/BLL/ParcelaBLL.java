/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAO.Conexion_DB;
import DAO.ParcelaDAO;
import Entidades.Parcela;
import Entidades.Usuario;
import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lidia Baixauli de la Villa
 */
public class ParcelaBLL {
    Connection con = null;
    public List<Parcela> listaParcelasDisp() throws Exception{
        
        List<Parcela> listadoParce = new ArrayList();
        try{
            Conexion_DB conexion_DB = new Conexion_DB();
            con = conexion_DB.abrirConexion();// Abrimos la conexión
            ParcelaDAO parcelaDAO = new ParcelaDAO();
            //recoge todas las parcelas disponibles
            listadoParce = parcelaDAO.disponibles(con);
            //cierra conexion
            conexion_DB.CerrarConexion(con);
        }catch (Exception ex) {
            System.out.println("Excepcion ->"+ex.getMessage());
        }  
        
        return listadoParce;
                
    }
    
    public int asignaUsuario(String parcela, Usuario user) throws Exception{
        int  exito=0;
        
        try {  
            Conexion_DB conexion_DB = new Conexion_DB();
            con = conexion_DB.abrirConexion();// Abrimos la conexión            
            ParcelaDAO parcelaDAO=new ParcelaDAO();
            exito = parcelaDAO.asignaUsuario(con, parcela, user);
            conexion_DB.CerrarConexion(con);
            
        } catch (Exception ex) {
            System.out.println("Exception-> " + ex.getMessage());
        }
        return exito;
    }
    
    public Parcela buscaPorEmail(String email) throws Exception{
        //Parcela parcela = new Parcela();
        Parcela parcela =null;
        try {  
            Conexion_DB conexion_DB = new Conexion_DB();
            con = conexion_DB.abrirConexion();// Abrimos la conexión            
            ParcelaDAO parcelaDAO=new ParcelaDAO();
            parcela = parcelaDAO.buscaPorEmail(con, email);
            conexion_DB.CerrarConexion(con);
            
        } catch (Exception ex) {
            System.out.println("Exception-> " + ex.getMessage());
        }
        return parcela;
        
    }
    
    public int desasignaUsuario(Parcela parcela) throws Exception{
        int  exito=0;       
        try {  
            Conexion_DB conexion_DB = new Conexion_DB();
            con = conexion_DB.abrirConexion();// Abrimos la conexión            
            ParcelaDAO parcelaDAO=new ParcelaDAO();
            exito = parcelaDAO.desasignaUsuario(con, parcela);
            conexion_DB.CerrarConexion(con);
            
        } catch (Exception ex) {
            System.out.println("Exception-> " + ex.getMessage());
        }
        return exito;
    }

}//fin class

