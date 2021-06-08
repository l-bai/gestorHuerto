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
public class AddHorarioRiegoCommand extends ICommand{

    @Override

    
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
       int resultadoAlta=0;
       String horario = (request.getParameter("horario")).trim();
       byte[] ptext = horario.getBytes(ISO_8859_1); 
       String horarioUtf = new String(ptext, UTF_8); 
       
       if(horarioUtf!=null && !horarioUtf.equals("")){//si no en blanco
           HorarioBLL horarioBLL = new HorarioBLL();
           resultadoAlta = horarioBLL.addHorarioRiego(horarioUtf);           
       }
       if(resultadoAlta==1){
           request.setAttribute("addRiegoOk", "ok");
       }else{
           request.setAttribute("errorAddRiego", "error");
       }
       //volver a cargar el listado
       HorarioBLL horaBLL = new HorarioBLL();
       request.setAttribute("listaHorarioRiego", horaBLL.listaHorarios());
       //listado anuncios
        AnuncioBLL anuncioBLL= new AnuncioBLL();
        request.setAttribute("listaAnuncios", anuncioBLL.listaAnuncios());
       return "tablon/editarTablon.jsp";
    }
    
}//fin clase
