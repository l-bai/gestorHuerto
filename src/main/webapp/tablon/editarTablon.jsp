<%--  
    Document   : verTablon
    Created on : 31-may-2021, 12:57:02
    Author     : Lidia Baixauli de la Villa
--%>

<%@page import="Entidades.Anuncio"%>
<%@page import="java.util.List"%>
<%@page import="Entidades.Horario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tablon Anuncios</title>
        <script src="https://code.jquery.com/jquery-latest.js"></script>
        <script src="../resources/js/tablon.js"></script>        
    </head>
    <body id="editaTablon">
        <h2>Tablón de anuncios</h2>
        <h3>Edición del tablón</h3>
        <p id="infoHorario"></p><!--mensajes de ok eliminar-->
        <!--mensaje horario añadido-->
        <%if(request.getAttribute("addRiegoOk")!=null){
            %>
            <p class="exito">Nuevo horario de riego añadido</p>
            <%}
        if(request.getAttribute("errorAddRiego")!=null){
            %>
            <p class="error">No se ha añadido el horario</p>
        <%} %>
        
        <!--mensaje anuncio añadido-->
        <%if(request.getAttribute("addAnuncio")!=null){
            %>
            <p class="exito">Nuevo anuncio añadido</p>
            <%}
        if(request.getAttribute("errorAddAnuncio")!=null){
            %>
            <p class="error">No se ha añadido el anuncio</p>
        <%} %>
        

        <div id="tablon">
            
            <div id="riego">
                <h4>Horarios de riego</h4>
                <% List listaHorarios = (List) request.getAttribute("listaHorarioRiego");
                if(listaHorarios.size()==0){            
                %>
                <p class="error">Actualmente no hay horarios de riego</p>
                <%
                }else{
                %>
                <ul id="listaRiego">
                    <%      
                    for ( int i=0;i<listaHorarios.size();i++){
                        Horario horario = (Horario)listaHorarios.get(i);                          
                    %>    
                    <li id="<%=horario.getId()%>"><%=horario.getDescripcion()%><span> Eliminar</span></li>

                 <%}%>   
                </ul>
                <%}%>

              </div> 

            <!--formulario para añadir horarios "-->
            <form name="formAlta" action="Controller?opID=AddHorarioRiego" method="POST">
                <div class="camposForm">
                    <label for="horario">Nuevo horario</label>
                    <input type="text" name="horario" id="horario" required/>                    
                </div>   
                <input type="submit" value="Añadir horario">
            </form>
            


        
        <!--edicion de  los anuncios-->    
            <div id="anuncios">
                <h4>Novedades importantes</h4>
                <% List listaAnuncios = (List) request.getAttribute("listaAnuncios");
                if(listaAnuncios.size()==0){            
                %>
                <p class="error">Actualmente no hay horarios de riego</p>
                <%
                }else{
                %>
                <div class="tablaResponsive">
                    <table>
                      <thead>
                        <tr>
                            <th>Debes saber que...</th>
                            <th>Fecha</th>
                            <th>Eliminar</th>
                        </tr>
                    </thead>
                    <tbody>  

                    <%      
                        for ( int i=0;i<listaAnuncios.size();i++){
                            Anuncio anun = (Anuncio)listaAnuncios.get(i);                          
                    %> 
                    <tr id="<%=anun.getId()%>">
                        <td><%=anun.getDescripcion()%></td>
                        <td class="fecha"><%=anun.getFecha()%></td>
                        <td class="delete">Eliminar</td>
                    </tr>           

                     <%}%>   
                    </table>
                </div>
                <%}%> 
                
            </div>  
                
            <!--formulario para añadir comentarios/anuncios "-->
            <form name="formAlta" id="addAnun" action="Controller?opID=AddAnuncio" method="POST">
                <div class="camposForm">
                    <h4>Añade nuevo anuncio</h4>
                    
                    <textarea name="anuncioTxt" id="anuncioTxt" ></textarea>                  
<!--                    <input type="text" name="horario" id="horario" required/>                    -->
                </div>   
                <input type="submit" value="Añadir anuncio">
                <input type="reset" value="Borrar contenido">
            </form>
            
        </div>
    </body>
</html>
