<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html lang="ua" ng-app="crudApp">
    <head>
        <title>${title}</title>
        <link href="../../static/css/bootstrap.css" rel="stylesheet"/>
        <link href="../../static/css/app.css" rel="stylesheet"/>
        <link href="../../static/images/logo.PNG" rel="icon">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <meta charset="UTF-8">
        <style type="text/css">
            [ng\:cloak], [ng-cloak], .ng-cloak {
                display: none !important;
            }
            body{
                background-image: url("../../static/images/background.jpg");
            }
        </style>
    </head>

    <body>
    <%@include file="header.jsp" %>

        <div ui-view></div>
        <script src="../../static/js/lib/angular.min.js" ></script>
        <script src="../../static/js/lib/angular-ui-router.min.js" ></script>
        <script src="../../static/js/lib/localforage.min.js" ></script>
        <script src="../../static/js/lib/ngStorage.min.js"></script>
        <script src="../../static/js/app/app.js"></script>
        <script src="../../static/js/app/UserService.js"></script>
        <script src="../../static/js/app/UserController.js"></script>
        <script src="../../static/js/app/AlbumService.js"></script>
        <script src="../../static/js/app/AlbumController.js"></script>
    </body>
</html>