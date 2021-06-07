/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entidades.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Lidia Baixauli de la Villa
 */
public class UsuarioDAO {
    ResultSet rs = null;
    PreparedStatement stmt = null;
    String sql;
    
    private void obtenFilaUsuario(ResultSet rs, Usuario user)throws SQLException{
        user.setNombre(rs.getString("nombre"));
        user.setEmail(rs.getString("email"));
        user.setTelefono(rs.getInt("telefono"));
        user.setPassword(rs.getString("pass"));
        user.setRol(rs.getInt("rol"));
      
    }
    
        private void obtenFilaUsuarioParcela(ResultSet rs, HashMap user)throws SQLException{
            user.put("nombre", rs.getString("nombre"));
            user.put("email", rs.getString("email"));
            user.put("parcela", rs.getString("parce"));
            user.put("telefono", rs.getInt("telefono"));
            user.put("rol", rs.getInt("rol"));
//        user.setNombre(rs.getString("nombre"));
//        user.setEmail(rs.getString("email"));
//        user.setTelefono(rs.getInt("telefono"));
//        user.setPassword(rs.getString("pass"));
//        user.setRol(rs.getInt("rol"));
        
        
    }
    
    public Usuario buscaEmail(Connection con, Usuario user) throws Exception{
        Usuario userDB = null;
        //rs = null;
        //PreparedStatement stmt = null;
        
        try{
           sql = "SELECT * FROM usuarios WHERE email=?" ;
           stmt = con.prepareStatement(sql);
           stmt.setString(1, user.getEmail());
           rs = stmt.executeQuery();
           while (rs.next()){
               userDB = new Usuario();
               obtenFilaUsuario(rs, userDB);
           }
            
        }catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al buscar el cliente por Nick "+ex.getMessage());
        } finally
        {
            if (rs != null) rs.close(); //Cerramos el resulset
            if (stmt != null) stmt.close();//Cerramos el Statement 
        }
    
        return userDB;
    
    }
    
    public int  altaNueva(Connection con, Usuario user)throws Exception{
        int exito=0;
        //ResultSet rs = null;
        //PreparedStatement stmt = null;
        try{
           sql = "INSERT INTO usuarios VALUES (?,?,?,?,?)" ;
           stmt = con.prepareStatement(sql);
           stmt.setString(1, user.getEmail());
           stmt.setString(2, user.getNombre());
           stmt.setString(3, user.getPassword());
           stmt.setInt(4, user.getTelefono());
           stmt.setInt(5, user.getRol());
           //exito= stmt.execute();
           exito =stmt.executeUpdate();
           
           
            
        }catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al dar de alta al usuario "+ex.getMessage());
        } finally
        {
            if (rs != null) rs.close(); //Cerramos el resulset
            if (stmt != null) stmt.close();//Cerramos el Statement 
        }
        return exito;
    
    }
    
    public List<Usuario> arrendatarios(Connection con) throws Exception{
        List<Usuario> listaUsuarios = new ArrayList();
        try{
            sql = "SELECT * FROM usuarios WHERE rol = 1"; //WHERE rol != 0";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();            
            Usuario user = null;
            
            while(rs.next()){
                user = new Usuario();
                obtenFilaUsuario(rs,user);
                listaUsuarios.add(user);
            }        
        
        }catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al buscar las parcelas disponibles "+ex.getMessage());
        } finally
        {
            if (rs != null) rs.close(); //Cerramos el resulset
            if (stmt != null) stmt.close();//Cerramos el Statement 
        }
        return listaUsuarios;
    
    }
    
        public List<Usuario> listaUsuarios(Connection con) throws Exception{
        List<Usuario> listaUsuarios = new ArrayList();
        try{
            sql = "SELECT * FROM usuarios"; //WHERE rol != 0";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();            
            Usuario user = null;
            
            while(rs.next()){
                user = new Usuario();
                obtenFilaUsuario(rs,user);
                listaUsuarios.add(user);
            }        
        
        }catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al buscar las parcelas disponibles "+ex.getMessage());
        } finally
        {
            if (rs != null) rs.close(); //Cerramos el resulset
            if (stmt != null) stmt.close();//Cerramos el Statement 
        }
        return listaUsuarios;
    
    }
        
     public List<HashMap> listaUsuariosParcela(Connection con) throws Exception{
        List<HashMap> listaUsuarios = new ArrayList<>();
        try{
            sql = "SELECT u.nombre,u.email,u.telefono,u.rol,\n" +
                  "(SELECT p.id from parcelas p WHERE u.email=p.usuario) as parce\n" +
                  "FROM usuarios u ORDER BY u.rol DESC,1";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();            
            HashMap user = null;
            
            while(rs.next()){
                user = new HashMap();
                obtenFilaUsuarioParcela(rs,user);
                listaUsuarios.add(user);
                //System.out.println(user.get("parcela"));
                //System.out.println(user.get("email"));
            } 
            //System.out.println(listaUsuarios.size());
        
        }catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al listar usuarios y sus parcelas "+ex.getMessage());
        } finally
        {
            if (rs != null) rs.close(); //Cerramos el resulset
            if (stmt != null) stmt.close();//Cerramos el Statement 
        }
        return listaUsuarios;
    
    }
    
    /*borrar usuario*/
    public int bajaUsuarioEmail(Connection con, String email)throws Exception{
        int borrado = 0;
        try{
           sql = "DELETE FROM usuarios WHERE email=?" ;
           stmt = con.prepareStatement(sql);
           stmt.setString(1, email);
           borrado = stmt.executeUpdate();
          
            
        }catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al borrar el usuario "+ex.getMessage());
        } finally
        {
            if (rs != null) rs.close(); //Cerramos el resulset
            if (stmt != null) stmt.close();//Cerramos el Statement 
        }

        return borrado;
    }
    
    
    
    
}//FIN CLASE DAO
