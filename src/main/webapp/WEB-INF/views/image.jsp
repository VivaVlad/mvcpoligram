<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Зображення</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
    <link href="../../static/images/logo.PNG" rel="icon">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style type="text/css">
        body{
            background-image: url("../../static/images/background.jpg");
        }
    </style>
    <script>
        function goBack() {
            window.history.back();
        }
    </script>
</head>
    <body>
    <%@include file="header.jsp" %>

    <div class="background"></div>
        <a onclick="goBack()"><div class="image" style="background-image: url('/image?id=${image.id}');">
    </div>
        </a>
    </body>
</html>