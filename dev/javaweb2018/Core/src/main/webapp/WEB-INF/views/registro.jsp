<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>

<style>
.error {
	color: #ff0000;
	font-style: italic;
	font-weight: bold;
}
</style>

</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Compu Mundo Hiper Mega Red</a>
			</div>
		</div>
	</nav>


	<div class="jumbotron">
		<div class="container">
			<h1>Registro a Compumundo!</h1>
		</div>
	</div>

	<form:form commandName="registroUsuario" method="POST">
		<div class="form-group">

			<form:input path="nombre" type="text" name="nombre"
				placeholder="nombre" />
			<form:errors path="nombre" cssClass="error" />
		</div>
		<div class="form-group">
			<form:input name="apellido" path="apellido" placeholder="apellido" />
			<form:errors path="apellido" cssClass="error" />
		</div>

		<div class="form-group">
			<form:password path="password" name="password" placeholder="password" />
			<form:errors path="password" cssClass="error" />

		</div>

		<div class="form-group">
			<form:input path="mail" name="mail" placeholder="e-mail" />
			<form:errors path="mail" cssClass="error" />
		</div>
		<button type="submit" class="btn btn-default">registar</button>
	</form:form>
	
	<sec:authentication property="name"/>
	<sec:authorize ifAnyGranted="USE_ROLE">
	
	</sec:authorize>

</body>
</html>