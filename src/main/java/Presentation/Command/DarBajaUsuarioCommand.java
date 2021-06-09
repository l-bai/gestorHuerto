/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Command;

import BLL.ParcelaBLL;
import BLL.UsuarioBLL;
import Entidades.Parcela;
import Entidades.Usuario;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lidia Baixauli de la Villa
 */
public class DarBajaUsuarioCommand extends ICommand{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        Parcela parceUser;
        String email = request.getParameter("usuario");
        int bajaOk;
        ParcelaBLL parcelaBLL = new ParcelaBLL();
        Usuario userBaja = new Usuario();
        UsuarioBLL userBLL = new UsuarioBLL();
        //ver el rol del usuario
        userBaja.setEmail(email);
        //si es administrador, se comprueba cuántos hay
        if(userBLL.buscaUsuarioEmail(userBaja).getRol()==0){
           //ver si es el único administrador
            if(userBLL.obtenNumAdministradores()==1){//solo hay uno y no se puede borrar
                request.setAttribute("errorBaja", email);
                request.setAttribute("adminUnico", "MOTIVO: es el único administrador");
            }else{//si hay más de uno
                //busca parcela
                parceUser = parcelaBLL.buscaPorEmail(email);
                bajaOk = eliminaUsuario(email, parceUser);
                if(bajaOk==1){
                    if(parceUser!=null){
                    request.setAttribute("pacelaLibre", parceUser);
                }
                    request.setAttribute("bajaOk", email);
                }else{
                    request.setAttribute("errorDesasigna", parceUser);
                }

            }
 
        }else{//es arrendatario
           //busca parcela
            parceUser = parcelaBLL.buscaPorEmail(email);
           
            bajaOk = eliminaUsuario(email, parceUser);
            if(bajaOk==1){
                if(parceUser!=null){
                    request.setAttribute("pacelaLibre", parceUser);
                }
                request.setAttribute("bajaOk", email);
            }else{
                request.setAttribute("errorDesasigna", parceUser);
            }    
        }      
        
        return "/menus/menuGestionAdmin.jsp";
    }
    
   private int eliminaUsuario(String email,Parcela parceUser) throws Exception{
       int bajaUsuario = 0,parceDesasignada;
       ParcelaBLL parcelaBLL = new ParcelaBLL();
       UsuarioBLL userBLL = new UsuarioBLL();
       //comprobar parcela
       if(parceUser!=null){
           //desasigna Parcela
            parceDesasignada = parcelaBLL.desasignaUsuario(parceUser);
            if(parceDesasignada==1){//liberada               
                //da de baja al usuario
                bajaUsuario= userBLL.bajaUsuarioEmail(email);
            }    
    
           
       }else{//si ok devuelve 1
           bajaUsuario= userBLL.bajaUsuarioEmail(email);
       }
       return bajaUsuario;
   }
    
}
