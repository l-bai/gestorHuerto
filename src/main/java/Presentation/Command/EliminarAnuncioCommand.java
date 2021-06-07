/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Command;

import BLL.AnuncioBLL;
import BLL.HorarioBLL;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lidia Baixauli de la Villa
 */
public class EliminarAnuncioCommand  extends ICommand{

    @Override
    public void initPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
         //listado riego
         HorarioBLL horaBLL = new HorarioBLL();
         request.setAttribute("listaHorarioRiego", horaBLL.listaHorarios()); 
         //listado anuncios
         AnuncioBLL anuncioBLL= new AnuncioBLL();
         request.setAttribute("listaAnuncios", anuncioBLL.listaAnuncios());
     }
    
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
       int idFila = Integer.parseInt(request.getParameter("elemento"));
        //Eliminar horario de la BBDD
        AnuncioBLL anunBLL = new AnuncioBLL();
        int borrado = anunBLL.borraAnuncio(idFila);
    
        return "/tablon/editarTablon.jsp";
    }
    
}
