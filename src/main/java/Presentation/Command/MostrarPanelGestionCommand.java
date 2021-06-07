/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Command;

import Entidades.Usuario;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lidia Baixauli de la Villa
 */
public class MostrarPanelGestionCommand extends ICommand
{
    //se crea para que no pueda accederse al panel de gestión al volver hacia atrás en el navegador después de cerrrar sesión
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String paginaPpal=null; 
       Usuario user = (Usuario) request.getSession().getAttribute("usuarioSesion");
       if(user==null){
           paginaPpal = "/";//va al índice
       }else{
           if(user.getRol()==0){
               paginaPpal = "menus/menuGestionAdmin.jsp";
           }else{
            paginaPpal = "menus/menuGestionArrendador.jsp";
           }
       }
        return paginaPpal;
    }
    
}
