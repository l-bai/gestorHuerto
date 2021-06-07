/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Command;

import BLL.UsuarioBLL;
import Entidades.Usuario;
import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lidia Baixauli de la Villa
 */
public class LoginCommand extends ICommand{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
       Usuario user = new Usuario();
       //user.setNombre(request.getParameter("nombre"));
       
       //encriptar contraseña
       String sha256hex = Hashing.sha256().hashString(request.getParameter("pass"), StandardCharsets.UTF_8).toString();
       
       user.setEmail(request.getParameter("email"));
       user.setPassword(sha256hex);
       //System.out.println("cadena"+sha256hex);
       
       UsuarioBLL userBLL = new UsuarioBLL();
       user = userBLL.validaUsuario(user);
       //guarda usurio en sesión
       request.getSession().setAttribute("usuarioSesion", user); 
       if(user==null){
           //dispatcher redirecciona a index
           request.setAttribute("errorLog", "error");
           
           //return "index.jsp";
           
       }else{
           if(user.getRol()==0){//es admin
               return "menus/menuGestionAdmin.jsp";
           }else{//es arrendatario
               //redirecciona al tablón anuncios o gestor usuario
              return "menus/menuGestionArrendador.jsp";
              //return "tablon/verTablon.jsp";
           }
       }
       
       
       return "menus/menuGestionAdmin.jsp";
    }
    
}
