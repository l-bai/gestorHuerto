<%-- 
    Author     : Lidia Baixauli de la Villa
--%>
<%@page import="Entidades.Usuario"%>
<!--imports-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Huerto Mandroca</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <!--<link rel="stylesheet" type="text/css" href="style.css"/>-->
        <link rel="stylesheet" type="text/css" href="resources/css/style.css"/>
        <!--FUENTES GOOGLE-->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Dosis&family=Roboto&display=swap" rel="stylesheet">
    </head>
    <header><jsp:include page="menus/menu.jsp" /></header>
    <body>
        <main id="gestor">
        <h1>Panel de gestión</h1>

            <% String paginaprincipal=(String) request.getAttribute("paginaprincipal");
                if(paginaprincipal!=null){ %>
                <jsp:include page='<%=(String)request.getAttribute("paginaprincipal")%>'/>
                <%}else{
                    Usuario usuarioSesion = (Usuario)request.getSession().getAttribute("usuarioSesion");
                        if(usuarioSesion!=null){
                            if(usuarioSesion.getRol()==0){
                            %>
                                <jsp:include page='menus/menuGestionAdmin.jsp'/>
                            <%}else{//es arrendador
                            %>
                                <jsp:include page='menus/menuGestionArrendador.jsp'/>
                            <%}
                        }else{
                          %>
                                <jsp:include page='/'/>
                           <%}
                }%>

        </main>
    </body>
</html>
