/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAO.Conexion_DB;
import DAO.HorarioDAO;
import Entidades.Horario;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lidia Baixauli de la Villa
 */
public class HorarioBLL {
    public List<Horario> listaHorarios() throws Exception{
        List<Horario> listadoHorarios = new ArrayList();
        Connection con = null;
        try{
            Conexion_DB conexion_DB = new Conexion_DB();
            con = conexion_DB.abrirConexion();// Abrimos la conexión
            HorarioDAO horarioDAO = new HorarioDAO();            
            listadoHorarios = horarioDAO.listaHorarios(con);
            //cierra conexion
            conexion_DB.CerrarConexion(con);
        }catch (Exception ex) {
            System.out.println("Excepcion ->"+ex.getMessage());
        }  
        return listadoHorarios;
    }
    
    //borrado por id
    public int borraHorario(int id) throws Exception{
        int borrado = 0;
        Connection con = null;
        try {  
            Conexion_DB conexion_DB = new Conexion_DB();
            con = conexion_DB.abrirConexion();// Abrimos la conexión
            HorarioDAO horaDAO=new HorarioDAO();
            borrado = horaDAO.borraHorario(con, id);
            conexion_DB.CerrarConexion(con);
            
        } catch (Exception ex) {
            System.out.println("Exception-> " + ex.getMessage());
        }
        return borrado;
    }
    
    //añade horario de riego
        public int addHorarioRiego(String horario)throws Exception{
        int  exito=0;
        Connection con = null;
        try {  
            Conexion_DB conexion_DB = new Conexion_DB();
            con = conexion_DB.abrirConexion();// Abrimos la conexión
            HorarioDAO horarioDAO=new HorarioDAO();
            exito = horarioDAO.addHorarioRiego(con, horario);
            System.out.println(exito);
            conexion_DB.CerrarConexion(con);
            
        } catch (Exception ex) {
            System.out.println("Exception-> " + ex.getMessage());
        }
        return exito;
    }
    
    
}//fin clase
