<%-- 
    Document   : altaUsuario
    Created on : 31-may-2021, 8:52:35
    Author     : Lidia Baixauli de la Villa
--%>

<%@page import="Entidades.Usuario"%>
<%@page import="Entidades.Parcela"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

        <h2>Alta usuarios</h2>
        <% 
            Object errorNom =request.getAttribute("errorNom");            
            Object errorEmail =request.getAttribute("errorEmail");            
            Object errorPass =request.getAttribute("errorPass");            
            Object errorTlf =request.getAttribute("errorTlf");
            Usuario user = (Usuario) request.getAttribute("datos");
            if(request.getAttribute("errorYaRegistrado")!=null){
        %>
                <p class="error">Usuario ya registrado</p>
            <%
        }
        %>
        <form name="formAlta" action="Controller?opID=DarAltaUsuario" method="POST">
            <fieldset>
                <legend>Rol del Usuario</legend>
                <div class="camposForm">
                    <div><input type="radio" name="roles" value="0" id="admin"/><label for="admin">Administrador</label></div>
                    <div><input type="radio" name="roles" value="1" id="arr" checked="checked" /><label for="arr">Arrendador</label></div>
                </div>
            </fieldset>
            <fieldset>
                <legend>Datos personales</legend>
                <div class="camposForm">
                    <label for="nombre">Nombre*</label>
                    <input type="text" name="nombre" value="<%= user!=null?user.getNombre():""%>"/>
                    <p class=<%=errorNom!=null?"error":"oculta_error"%>>Nombre no válido</p>
                </div>
                <div class="camposForm">
                    <label for="email">Email*</label>
                    <input type="text" name="email" value="<%= user!=null?user.getEmail():""%>"/>
                    <p class=<%=errorEmail!=null?"error":"oculta_error"%>>Formato de correo no válido</p>
                </div>
                <div class="camposForm">
                    <label for="telefono">Teléfono</label>
                    <input type="number" name="telefono"/>
                    <p class=<%=errorTlf!=null?"error":"oculta_error"%>>Teléfono no válido. Debe contener 9 dígitos</p>
                </div>
                <div class="camposForm">
                    <label for="pass">Contraseña*</label>
                    <input type="password" name="pass"/>
                    <p class=<%=errorPass!=null?"error":"oculta_error"%>>Contraseña no válida. Debe contener 6 caracteres y ,al menos, una letra y un número</p>
                </div>
            </fieldset>
            <fieldset>
                <legend>Asignar parcela</legend>
                <!--será un combo cargado con las parcelas-->
                <% List listaParcelas = (List) request.getAttribute("listaParcelasDisp");
                    if(listaParcelas.size()==0){
                %>
                <p class="error">Actualmente no hay parcelas disponibles</p>
                <%
                    }
                %>
                <select name="parcela">
                    <option value="null">Sin parcela</option>
                    <%
                        //List listaParcelas = (List) request.getAttribute("listaParcelasDisp");
                        for ( int i=0;i<listaParcelas.size();i++){
                            Parcela parcela = (Parcela)listaParcelas.get(i);                           
                       %>
                        <option value="<%=parcela.getId()%>"> 
                            <%=parcela.getId()%>                        
                        </option>
                       <%
                        }
                               
                    %>   
                    
                </select>
            </fieldset>
            <input type="submit" value="Dar de alta">
            
        </form>

