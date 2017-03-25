<%-- 
    Document   : usuarios
    Created on : 26/02/2017, 09:50:07 PM
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
            <%@include file="Template/menuAcciones.jsp" %>
        </nav>
        <section>

            <article>
                <table id="table" class="display" width="100%" cellspacing="0" data-page-length="6">
                    <thead>                        
                        <tr>
                            <th class="no-sort"><label>Seleccionar</label></th>
                            <th>Id</th>
                            <th>Rol</th>
                            <th>Descripcion</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th></th>
                            <th>Id</th>
                            <th>Rol</th>
                            <th>Descripcion</th>
                        </tr>
                    </tfoot>
                    <tbody>
                    <c:if test="${not empty roles}">
                        <c:forEach var="profValue" items="${roles}">
                            <tr>
                                <td valign="top"><input name="usuario" type="checkbox" style="margin-left:30%" onchange="editarOcultar()" value="${profValue.idUsuario}"></td>
                                <td valign="top">${profValue.idUsuario}</td>
                                <td valign="top">${profValue.usuario}</td>
                                <td valign="top">${profValue.datosGenerales.email}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>
                </table>
            </article>

            <article id="Form-create" style="display: none;">                                    
                <div id="encabezadoNaranja">
                    <img class="logo-encabezado-naranja" src="../../resources/img/logo_blanco-61.svg"/>                   
                    <div class="separacion-encabezado"></div>
                    <label class="texto-encabezado-naranja">Actualizacion de datos</label>
                </div>
                <div class="contenido" style="background-color: #fff;">
                    <form  style="padding: 1%" id="form-profesor" method="POST" action="../profesores/create.do">
                        <input class="id-usuarioc" type="hidden" value="" /><br />
                        <table>
                            <tr class="popup">
                                <td>
                                    <span>Usuario</span><br />
                                    <input type="text" value="" name="usuario"/>
                                </td>
                                <td>
                                    <span>Correo</span><br />
                                    <input type="text" name="email" subobject="datosGenerales"/>
                                </td>
                            </tr>
                            <tr class="popup">
                                <td>
                                    <span>Nombre</span><br />
                                    <input type="text" name="nombre" subobject="datosGenerales"/>
                                </td>
                                <td>
                                    <span>Apellido Paterno</span><br />
                                    <input type="text" name="apellidoP" subobject="datosGenerales"/>
                                </td>
                            </tr>
                            <tr class="popup">
                                <td>
                                    <span>Apellido Materno</span><br />
                                    <input type="text" name="apellidoM" subobject="datosGenerales"/>
                                </td>
                                <td>
                                    <span>Contraseña</span><br />
                                    <input type="password" name="contrasena"/>
                                </td>
                            </tr>
                            <tr class="popup">
                                <td>
                                    <span>Telefono</span><br />
                                    <input type="text" name="telefono" subobject="datosGenerales"/>
                                </td>
                                <td>
                                    <span>Activo</span><br />
                                    <input type="checkbox" value="" name="activo"/>
                                </td>
                            </tr>
                        </table>
                        <!--<input id="crear-user" type="submit" id="btnActualizarGuardar" value="Agregar" class="popup"/>-->
                        <input type="button" id="btnActualizarGuardar" value="Agregar" class="popup" onclick="createCall('form-profesor', alerta)"/>
                        <div  class="msg-status-perfil"></div>  
                        <!-- 
                        <div class="msg-status"></div>
                        -->
                    </form>
                </div>
            </article>
        </section>
        <%@include file="Template/footer.jsp" %>
    </body>
</html>
