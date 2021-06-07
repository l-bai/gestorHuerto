<%-- 
    Document   : mostrarUsuarios
    Created on : 01-jun-2021, 19:53:09
    Author     : Lidia Baixauli de la Villa
--%>

<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

        <h2>Listado de usuarios</h2>
        <div class="tablaResponsive">
            <table>
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Email</th>
                        <th>Teléfono</th>
                        <th>Parcela</th>
                        <th>Rol</th>
                    </tr>
                </thead>
                <tbody>
                    <% List listaUsuarios = (List) request.getAttribute("listaUsuariosParcela");
                    //recorre lista con mapa
                    for ( int i=0;i<listaUsuarios.size();i++){
                        HashMap usuario = (HashMap) listaUsuarios.get(i);
                        String parcela = (String)usuario.get("parcela");
                        int rol = (int) usuario.get("rol");
                    %> 
                    <tr>
                        <td><%=usuario.get("nombre")%></td>
                        <td><%=usuario.get("email")%></td>
                        <td><%=usuario.get("telefono")%></td>
                        <td><%=parcela!=null?parcela:"No asignada"%></td>
                        <td><%=rol==1?"Arrendatario":"Administrador"%></td>
                    </tr>
                    <%   
                    }                
                    %>

                </tbody>
            </table>
        </div>

