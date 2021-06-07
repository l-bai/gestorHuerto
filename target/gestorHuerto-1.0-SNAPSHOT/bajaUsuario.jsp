<%-- 
    Document   : bajaUsuario
    Created on : 01-jun-2021, 12:54:26
    Author     : Lidia Baixauli de la Villa
--%>

<%@page import="Entidades.Usuario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

        <h2>Baja usuarios</h2>
        <h3>Seleccione el usuario a dar de baja</h3>
        
        <!--CARGA COMBO CON LOS USUARIOS-->
        <form name="formBaja" action="Controller?opID=DarBajaUsuario" method="POST">
           <fieldset>
                <legend>Seleccionar usuario</legend>
                <!--será un combo cargado con las parcelas-->
                <% List listaUsuarios = (List) request.getAttribute("listaUsuarios");
                    if(listaUsuarios.size()==0){
                %>
                <p class="error">Actualmente no hay usuarios</p>
                <%
                    }
                %>
                <select name="usuario">
                    
                    <%
                       
                        for ( int i=0;i<listaUsuarios.size();i++){
                            Usuario user = (Usuario)listaUsuarios.get(i);                           
                       %>
                        <option value="<%=user.getEmail()%>"> 
                            <%=user.getEmail()%>                        
                        </option>
                       <%
                        }
                               
                    %>   
                    
                </select>
            </fieldset>
            <input type="submit" value="Dar de baja"> 
            
        </form>
                    
        <p>Sólo está autorizado a borrar arrendatarios. Para la gestión de administradores debe habilitar el módulo de gestión avanzada </p>            

