/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Command;

import BLL.AnuncioBLL;
import BLL.HorarioBLL;
import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lidia Baixauli de la Villa 
 */
public class AddAnuncioCommand extends ICommand{

    @Override
    
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
         int resultadoAlta=0;
         String anuncio = (request.getParameter("anuncioTxt")).trim();
         //codificar el anuncio a utf-8
        byte[] ptext = anuncio.getBytes(ISO_8859_1); 
        String anuncioUtf = new String(ptext, UTF_8); 
         
         if(anuncioUtf!=null && !anuncioUtf.equals("")){
             AnuncioBLL anuncioBLL = new AnuncioBLL();
             resultadoAlta = anuncioBLL.addAnuncio(anuncioUtf);
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
