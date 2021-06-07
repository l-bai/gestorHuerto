<%-- 
    Document   : verTablon
    Created on : 31-may-2021, 12:57:02
    Author     : Lidia Baixauli de la Villa
--%>

<%@page import="Entidades.Anuncio"%>
<%@page import="Entidades.Horario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

        <h2>Tablón de anuncios</h2>
        <h3>Consulta aquí toda la información de nuestro huerto</h3>
        
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
                <ul>
                <%      
                    for ( int i=0;i<listaHorarios.size();i++){
                        Horario horario = (Horario)listaHorarios.get(i);                          
                %>    
                <li><%=horario.getDescripcion()%></li>

                 <%}%>   
                </ul>
                <%}%>
            </div>

            <div id="anuncios">
                <h4>Novedades importantes</h4>
                <% List listaAnuncios = (List) request.getAttribute("listaAnuncios");
                if(listaAnuncios.size()==0){            
                %>
                <p class="error">Actualmente no hay horarios de riego</p>
                <%
                }else{
                %>
                <table>
                  <thead>
                    <tr>
                        <th>Debes saber que...</th>
                        <th>Fecha</th>                    
                    </tr>
                </thead>
                <tbody>  

                <%      
                    for ( int i=0;i<listaAnuncios.size();i++){
                        Anuncio anun = (Anuncio)listaAnuncios.get(i);                          
                %> 
                <tr>
                    <td><%=anun.getDescripcion()%></td>
                    <td class="fecha"><%=anun.getFecha()%></td>
                </tr>           

                 <%}%>   
                </table>
                <%}%>           
             </div>
        </div>
