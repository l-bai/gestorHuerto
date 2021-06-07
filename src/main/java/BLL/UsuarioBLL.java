/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAO.Conexion_DB;
import DAO.UsuarioDAO;
import Entidades.Usuario;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Lidia Baixauli de la Villa
 */
public class UsuarioBLL {
    
    public Usuario validaUsuario(Usuario user) throws Exception {
        Usuario userObtenido = null;
        Connection con = null;
        try {  
            Conexion_DB conexion_DB = new Conexion_DB();
            con = conexion_DB.abrirConexion();// Abrimos la conexión
            UsuarioDAO userDAO=new UsuarioDAO();
            userObtenido = userDAO.buscaEmail(con, user);
            conexion_DB.CerrarConexion(con);
            if(userObtenido!=null){
                //comprobar contraseña
                if(!userObtenido.getPassword().equals(user.getPassword()))
                    userObtenido=null;
            }
        } catch (Exception ex) {
            System.out.println("Exception-> " + ex.getMessage());
        }
        
        
        return userObtenido;
    }
    
    public Usuario buscaUsuarioEmail(Usuario user) throws Exception{
        Usuario userObtenido = null;
        Connection con = null;
        try {  
            Conexion_DB conexion_DB = new Conexion_DB();
            con = conexion_DB.abrirConexion();// Abrimos la conexión
            UsuarioDAO userDAO=new UsuarioDAO();
            userObtenido = userDAO.buscaEmail(con, user);
            conexion_DB.CerrarConexion(con);
            
        } catch (Exception ex) {
            System.out.println("Exception-> " + ex.getMessage());
        }
        return userObtenido;
    }
    
    public int altaNueva(Usuario user)throws Exception{
        int  exito=0;
        Connection con = null;
        try {  
            Conexion_DB conexion_DB = new Conexion_DB();
            con = conexion_DB.abrirConexion();// Abrimos la conexión
            UsuarioDAO userDAO=new UsuarioDAO();
            exito = userDAO.altaNueva(con, user);
            System.out.println(exito);
            conexion_DB.CerrarConexion(con);
            
        } catch (Exception ex) {
            System.out.println("Exception-> " + ex.getMessage());
        }
        return exito;
    }
    
    public List<Usuario> listaUsuarios() throws Exception{
        List<Usuario> listadoUsuarios = new ArrayList();
        Connection con = null;
        try{
            Conexion_DB conexion_DB = new Conexion_DB();
            con = conexion_DB.abrirConexion();// Abrimos la conexión
            UsuarioDAO userDAO = new UsuarioDAO();            
            listadoUsuarios = userDAO.listaUsuarios(con);
            //cierra conexion
            conexion_DB.CerrarConexion(con);
        }catch (Exception ex) {
            System.out.println("Excepcion ->"+ex.getMessage());
        }  
        return listadoUsuarios;
    }
    
    public List<HashMap> listaUsuariosParcela() throws Exception{
        List<HashMap> listadoUsuarios = new ArrayList<>();
        Connection con = null;
        try{
            Conexion_DB conexion_DB = new Conexion_DB();
            con = conexion_DB.abrirConexion();// Abrimos la conexión
            UsuarioDAO userDAO = new UsuarioDAO();            
            listadoUsuarios = userDAO.listaUsuariosParcela(con);
            //cierra conexion
            conexion_DB.CerrarConexion(con);
        }catch (Exception ex) {
            System.out.println("Excepcion ->"+ex.getMessage());
        }  
        return listadoUsuarios;
    }
    
    public List<Usuario> listaArrendatarios() throws Exception{
        List<Usuario> listadoUsuarios = new ArrayList();
        Connection con = null;
         try{
            Conexion_DB conexion_DB = new Conexion_DB();
            con = conexion_DB.abrirConexion();// Abrimos la conexión
            UsuarioDAO userDAO = new UsuarioDAO();            
            listadoUsuarios = userDAO.arrendatarios(con);
            //cierra conexion
            conexion_DB.CerrarConexion(con);
        }catch (Exception ex) {
            System.out.println("Excepcion ->"+ex.getMessage());
        }  
        return listadoUsuarios;
                
    }
    
        public int bajaUsuarioEmail(String email) throws Exception{
        int borrado = 0;
        Connection con = null;
        try {  
            Conexion_DB conexion_DB = new Conexion_DB();
            con = conexion_DB.abrirConexion();// Abrimos la conexión
            UsuarioDAO userDAO=new UsuarioDAO();
            borrado = userDAO.bajaUsuarioEmail(con, email);
            conexion_DB.CerrarConexion(con);
            
        } catch (Exception ex) {
            System.out.println("Exception-> " + ex.getMessage());
        }
        return borrado;
    }
}
