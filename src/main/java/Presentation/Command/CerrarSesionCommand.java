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
public class CerrarSesionCommand extends ICommand{

    @Override
    //ellimina el usuario de sesión
    public void initPage(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
       Usuario user = new Usuario();
       user=null;
       request.getSession().setAttribute("usuarioSesion", user);
    }
    
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "";
    }
    
}
