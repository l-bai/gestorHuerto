/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Command;

import BLL.UsuarioBLL;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lidia Baixauli de la Villa
 */
public class ListarUsuariosCommand extends ICommand{

    @Override
    public void initPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
         //listado de usuarios
         UsuarioBLL userBLL = new UsuarioBLL();
         request.setAttribute("listaUsuariosParcela", userBLL.listaUsuariosParcela());
     
     }
     
    
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "/mostrarUsuarios.jsp";
    }
    
}
