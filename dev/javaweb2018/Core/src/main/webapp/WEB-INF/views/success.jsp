<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url value="/resources/img/cumple.jpg" var="cumple"></spring:url>
<spring:url value="/resources/css/style.css" var="stilos"></spring:url>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="${stilos}"></link>
</head>
<body>${valido}
	<br>
	<img src="${cumple}" width="50%" height="50%" />
	<br>

	<form action="formularioarchivo.html" method="post" enctype="multipart/form-data">
		<input type="file" name="archivo"> <input type="submit"
			value="enviar">
	</form>

</body>
</html>