<%-- 
    Document   : principal
    Created on : 26/02/2017, 01:39:34 AM
    Author     : ceasar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="Template/title.html" %>
    </head>
    <body>
        <div id="loader-wrapper">
            <div id="loader"></div>
        </div>
        <nav>
            <%@include file="Template/menuPrincipal.jsp" %>    
        </nav>
        
        <!--<form action="logout" method="POST">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="submit" entity="Logout">
        </form>-->
        <section>
            <article>
                <label entity="Welcome"></label>
            </article>
        </section>
        
        <%@include file="Template/footer.jsp" %>
    </body>
</html>
