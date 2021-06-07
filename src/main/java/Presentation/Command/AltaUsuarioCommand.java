/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Command;

import BLL.ParcelaBLL;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lidia Baixauli de la Villa
 */
public class AltaUsuarioCommand extends ICommand{

    @Override
     public void initPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
         //listado parcelas disponibles
         ParcelaBLL parcelaBLL = new ParcelaBLL();
         request.setAttribute("listaParcelasDisp", parcelaBLL.listaParcelasDisp());
     
     }
     
     
     
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "/altaUsuario.jsp";
    }
    
}
