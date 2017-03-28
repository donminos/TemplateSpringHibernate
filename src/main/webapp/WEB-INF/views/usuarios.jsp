<%-- 
    Document   : usuarios
    Created on : 26/02/2017, 09:50:07 PM
    Author     : ceasar
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
            <%@include file="Template/menuAcciones.jsp" %>
        </nav>
        <section>

            <article>
                <table id="table" class="display" width="100%" cellspacing="0" data-page-length="10">
                    <thead>                        
                        <tr>
                            <th class="no-sort"><label>Seleccionar</label></th>
                            <th>Id</th>
                            <th>Usuario</th>
                            <th>Rol</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th></th>
                            <th>Id</th>
                            <th>Usuario</th>
                            <th>Rol</th>
                        </tr>
                    </tfoot>
                    <tbody id="body-users">
                        <c:if test="${not empty users}">
                            <c:forEach var="value" items="${users}">
                                <tr>
                                    <td valign="top"><input name="usuario" type="checkbox" style="margin-left:30%" onchange="showHidden(this)" value="${value.idUser}"></td>
                                    <td valign="top">${value.idUser}</td>
                                    <td valign="top">${value.user}</td>
                                    <td valign="top">
                                        <ul>
                                            <c:if test="${not empty value.roles}">
                                                <c:forEach var="rol" items="${value.roles}">
                                                    <li>${rol}</li>
                                                    </c:forEach>
                                                </c:if>
                                        </ul>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                    </tbody>
                </table>
            </article>

            <article id="form-create" style="display: none;">                                    
                <div id="encabezadoNaranja">
                    <img class="logo-encabezado-naranja" src="../../resources/img/logo_blanco-61.svg"/>                   
                    <div class="separacion-encabezado"></div>
                    <label class="texto-encabezado-naranja">Actualizacion de datos</label>
                </div>
                <div class="contenido" style="background-color: #fff;">
                    <form  style="padding: 1%" id="form-send" method="POST" action="usuarios/CreateUpdate.do">
                        <input class="id" type="hidden" name="idUser" value="" />
                        <table>
                            <tr class="popup">
                                <td>
                                    <span>Usuario</span><br />
                                    <input type="text" value="" name="user"/>
                                </td>
                            </tr>
                            <tr class="popup">
                                <td>
                                    <span>Contraseña</span><br />
                                    <input type="password" value="" name="password"/>
                                </td>
                            </tr>
                            <tr class="popup">
                                <td>
                                    <c:if test="${not empty users}">
                                        <c:forEach var="value" items="${Roles}">
                                            <input name="roles" list="true" type="checkbox" value="${value.idRol}">${value.description}
                                        </c:forEach>
                                    </c:if>
                                </td>
                            </tr>
                        </table>
                        <!--<input id="crear-user" type="submit" id="btnActualizarGuardar" value="Agregar" class="popup"/>-->
                        <input type="button" id="btnActualizarGuardar" value="Agregar" class="popup" onclick="createCall('form-send', successSaved)"/>
                        <div  class="msg-status-perfil"></div>  
                        <!-- 
                        <div class="msg-status"></div>
                        -->
                    </form>
                </div>
            </article>
        </section>
        <%@include file="Template/footer.jsp" %>
        <script src="resources/js/usuarios.js" type="text/javascript"></script>
    </body>
</html>
