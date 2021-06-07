/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entidades.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lidia Baixauli de la Villa
 */
public class Dispatcher {
    public void procesa(HttpServletRequest request,HttpServletResponse response,String nextPage)
            throws ServletException, IOException{
      response.setContentType("text/html;charset=UTF-8");
  //    request.getRequestDispatcher(nextPage).forward(request,response);
      request.setAttribute("paginaprincipal",nextPage);
      //si no hay usuario logueado vamos al  índice
      Usuario usuarioSesion = (Usuario)request.getSession().getAttribute("usuarioSesion");
      if(usuarioSesion!=null){
        request.getRequestDispatcher("/panelGestion.jsp").forward(request,response);
      }else{
        request.getRequestDispatcher("/index.jsp").forward(request,response);
      }
      
      
    }
}
