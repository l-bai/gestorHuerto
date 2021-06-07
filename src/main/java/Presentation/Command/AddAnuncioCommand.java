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
public class AddAnuncioCommand extends ICommand{

    @Override
//    public void initPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
//         //listado riego
//         HorarioBLL horaBLL = new HorarioBLL();
//         request.setAttribute("listaHorarioRiego", horaBLL.listaHorarios()); 
//         //listado anuncios
//         AnuncioBLL anuncioBLL= new AnuncioBLL();
//         request.setAttribute("listaAnuncios", anuncioBLL.listaAnuncios());
//    }
    
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
         int resultadoAlta=0;
         String anuncio = (request.getParameter("anuncioTxt")).trim();
         System.out.println(anuncio);
         if(anuncio!=null && !anuncio.equals("")){
             AnuncioBLL anuncioBLL = new AnuncioBLL();
             resultadoAlta = anuncioBLL.addAnuncio(anuncio);
        }
        if(resultadoAlta==1){
           request.setAttribute("addAnuncio", "ok");
       }else{
           request.setAttribute("errorAddAnuncio", "error");
       }
         
         //volver a cargar el listado
        HorarioBLL horaBLL = new HorarioBLL();
       request.setAttribute("listaHorarioRiego", horaBLL.listaHorarios());
       //listado anuncios
        AnuncioBLL anuncioBLL= new AnuncioBLL();
        request.setAttribute("listaAnuncios", anuncioBLL.listaAnuncios());
         
         return "tablon/editarTablon.jsp";
    }
    
}
