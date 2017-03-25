<%-- 
    Document   : login
    Created on : 13/04/2016, 03:50:16 PM
    Author     : Carlos Cesar Rosas<face_less@hotmail.com>
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Template</title>
        <link rel="stylesheet" type="text/css" href="resources/css/index.css"/>
        <link rel="stylesheet" type="text/css" href="resources/css/libs/jquery.fancybox.min.css"/>
        <script src="//code.jquery.com/jquery-3.1.1.min.js"></script>
        <script src="resources/js/libs/jquery.i18n.properties.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="resources/js/libs/jquery.fancybox.min.js"></script>
        <script src="resources/js/global.js" type="text/javascript"></script>
        <style>
            .fancybox-close-small{
                display: none !important;
            }
        </style>
    </head>
    <body>
        <div id="loader-wrapper">
            <div id="loader"></div>
        </div>
        <div id="central">
            <form action="login" method='POST'>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <table class="marco">
                    <tr>
                        <td entity="User"></td>
                    </tr>
                    <tr>
                        <td><input type='text' name='username' placeholder="" entity="UserPlaceHolder" value=''></td>
                    <tr>
                        <td entity="Pass"></td>
                    </tr>
                    <tr>
                        <td><input type='password' name='password' placeholder="" entity="PassPlaceHolder" /></td>
                    </tr>
                    <tr>
                        <td><input entity="Submit" name="submit" type="submit" /></td>
                    </tr>
                </table>
            </form>
        </div>
        <c:if test="${Error != 'none'}" >
            <div id="messegerError${Error}" style="display: none">
                <label entity="Error${Error}"></label>
            </div>
            <script>$.fancybox.open({
                    src: '#messegerError${Error}',
                    type: 'inline',
                    opts: {
                        onComplete: function () {
                            console.info('done!');
                        }
                    }
                });</script>
            </c:if>
    </body>
</html>
