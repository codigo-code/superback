<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib prefix="sec"  uri="http://www.springframework.org/security/tags" %>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<body>
		
<sec:authorize ifAnyGranted="ROLE_COSME_FULANITO">
	<i> hola ${sarasita} como estas? </i>
</sec:authorize>

<a href="login.jsp">logout</a>

<sec:authorize ifAnyGranted="ROLE_ADMIN">
	<i> hola <sec:authentication property="name"/> como estas? </i>
	<h1>Seccion de admistrador</h1>
</sec:authorize>


</body>
	</html>