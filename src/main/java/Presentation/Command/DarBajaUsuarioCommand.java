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
        int parceDesasignada,bajaOk;
        ParcelaBLL parcelaBLL = new ParcelaBLL();
        Usuario userBaja;
        UsuarioBLL userBLL = new UsuarioBLL();
        //comprueba si tiene parcela asignada para poner el usuario a null
        //busca parcela por email
        parceUser = parcelaBLL.buscaPorEmail(email);
        //System.out.println(parceUser);
        if(parceUser!=null){
            //desasigna Parcela
            parceDesasignada = parcelaBLL.desasignaUsuario(parceUser);
            if(parceDesasignada==1){//liberada
                request.setAttribute("pacelaLibre", parceUser);
                //da de baja al usuario
                bajaOk= userBLL.bajaUsuarioEmail(email);
                if(bajaOk ==1){
                    request.setAttribute("bajaOk", email);
                }
                
            }else{//error eliminar parcela no elimina usuario
                request.setAttribute("errorDesasigna", parceUser);
            }
            
        }else{
        //da de baja al usuario
            bajaOk= userBLL.bajaUsuarioEmail(email);
            if(bajaOk ==1){
                request.setAttribute("bajaOk", email);
            }else{
                request.setAttribute("errorBaja", email);
            }
        }
        
        
        
        return "/menus/menuGestionAdmin.jsp";
    }
    
}
