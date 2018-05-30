<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Керування зображеннями</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	<link href="../../static/images/logo.PNG" rel="icon">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
<%@include file="header.jsp" %>
<div class="generic-container">
	<div class="panel panel-default">
	<div class="panel-heading"><span class="lead">Завантажити нове фото</span></div>
		<div class="uploadcontainer">
			<form:form method="POST" modelAttribute="fileBucket" enctype="multipart/form-data" class="form-horizontal">
				<div class="row">
					<div class="form-group col-md-12">
							<div class="input-group">
							<label class="input-group-btn">
								<div class="upload">
								<img src="../../static/images/image-icon.png"/>
									<div class="col-md-6">
										<form:input type="file" path="file" id="file"
													class="form-control " multiple="" style="display:none"/>
										<div class="has-error">
										<form:errors path="file" class="help-inline"/>
										</div>
									</div>
								</div>
							</label>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-12">
						<label class="col-md-3 control-lable" for="file">Опис</label>
						<div class="col-md-7">
							<form:input type="text" path="description" id="description"
										class="form-control"/>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-actions floatRight">
						<input type="submit" value="Завантажити фото" class="btn btn-primary">
					</div>
				</div>

			</form:form>
		</div>
	</div>

	<div class="well">
		Go to <a href="<c:url value='/#/albums' />">Альбоми</a>
	</div>


		<div class="panel panel-default">
			  <!-- Default panel contents -->
		  	<div class="panel-heading"><span class="lead">Список зображень </span></div>
		  	<div class="tablecontainer">
				<table class="table table-hover">
		    		<thead>
			      		<tr>
					        <th>No.</th>
					        <th>Ім'я файлу</th>
					        <th>Тип</th>
					        <th>Опис</th>
					        <th width="100"></th>
					        <th width="100"></th>
						</tr>
			    	</thead>
		    		<tbody>
					<c:forEach items="${images}" var="image" varStatus="counter">
						<tr>
							<td>${counter.index + 1}</td>
							<td>${image.name}</td>
							<td>${image.type}</td>
							<td>${image.description}</td>
							<td><a href="<c:url value='/download-image}-${image.id}' />" class="btn btn-success ">завантажити</a></td>
							<td><a href="<c:url value='/delete-document-${image.id}' />" class="btn btn-danger">видалити</a></td>
						</tr>
					</c:forEach>
		    		</tbody>
		    	</table>
		    </div>
		</div>
	</div>
</body>
</html>