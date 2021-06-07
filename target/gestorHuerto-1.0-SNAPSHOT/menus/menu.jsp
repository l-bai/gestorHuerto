<%-- 
    Document   : menu
    Created on : 31-may-2021, 8:41:23
    Author     : Lidia Baixauli de la Villa
--%>

<%@page import="Entidades.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
        
      <%  Usuario user = (Usuario) request.getSession().getAttribute("usuarioSesion");
        if(user!= null) if(user!= null) {%>
        
        <nav><ul id="menuPpal">
<!--            mostrar panel-->
            <!--<li><a href="panelGestion.jsp">Menú de gestión</a>-->
            <li><a href="Controller?opID=MostrarPanelGestion">Menú de gestión</a>
        <% 
           
            if(user.getRol()==0){
        %>            
            <li><a href="#">Usuarios</a>
                <ul>
                    <li><a href="Controller?opID=AltaUsuario">Alta nueva</a></li>
                    <li><a href="Controller?opID=BajaUsuario"">Baja</a></li>
                    <li><a href="Controller?opID=ListarUsuarios">Listar usuarios</a></li>                    
                </ul>
            </li>
            <li><a href="#">Tablón</a>
                <ul>
                    <li><a href="Controller?opID=MostrarTablon">Ver tablón</a></li>
                    <li><a href="Controller?opID=EditarTablon">Editar tablón</a></li>
                </ul>
            </li>
        <%           
            }if(user.getRol()==1){//menu arrendatario
        %>
            <li><a href="Controller?opID=MostrarTablon">Tablón de anuncios</a></li> 
        <%}%>
        
            <li><a href="Controller?opID=CerrarSesion">Cerrar sesión</a></li>
        </ul></nav>
        <%}%>
        <!--munu responsive-->
