<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cobranza</title>
</head>
<body>
	obtener informacion de una targeta:
	<form action="/informacion" method="post" name="formularioinformacion">
		<input type="text" name="numerotargeta" placeholden="numero de targeta">
		<input type="submit" value="consultar">
	</form>
	<br>
	<br>
	validar una operacion:
	<form action="/validaroperacion" method="post" name="formulariovalidaroperacion">
		<input type="enum" name="monto" placeholden="ingrese el monto">
		<input type="submit" value="consultar">
	</form>
	<br>
	<br>
validartargeta:	
	<form action="/validartargeta" method="post" name="formulariovalidartargeta">
		<input type="enum" name="numerotargeta" placeholden="ingrese el munero de la targeta">
		<input type="submit" value="consultar">
	</form>
	<br>
	<br>
</body>
</html>