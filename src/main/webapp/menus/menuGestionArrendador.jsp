<%-- 
    Document   : menuGestionAdmin
    Created on : 31-may-2021, 9:57:43
    Author     : Lidia Baixauli de la Villa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

      
<div class="menuGestion">
    <div class="menuAdmin">
        <div>
            <h3>Mi cuenta</h3>
            <img src="../resources/imagenes/usuarios.png" alt="menú de gestión de usuarios">
        </div>
        <div>
            <a class="noDisponible" href="#">Editar datos (No disponible)</a>
        </div>
    </div>
    <div class="menuAdmin">
         <div>
            <h3>Tablón de anuncios</h3>
            <img src="../resources/imagenes/tablon.png" alt="menú de gestión del tablón de anuncios">
        </div>
        <div>
            <a href="Controller?opID=MostrarTablon">Ver tablón de anuncios</a>
        </div>
    </div>
</div>

