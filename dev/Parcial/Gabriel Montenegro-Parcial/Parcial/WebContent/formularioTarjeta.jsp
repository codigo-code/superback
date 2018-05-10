<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/formularioTarjeta.css">
</head>
<body>
	<h3>Ingrese la informacion de su tarjeta de credito</h3>
	<form method="post" action="tarjeta">
		<input type="text" name="marca" placeholder="SQUA, SCO, PERE" /> <input
			type="text" name="cardholder" placeholder="Card holder" /> <input
			type="date" name="fechaVencimiento"
			placeholder="ingrese fecha de vencimiento" /> <input type="number"
			name="numeroTarjeta"
			placeholder="ingrese el numero de la tarjeta de credito">
			<button type="submit">Validar</button>
	</form>

	<%
		if (request.getAttribute("mensajeError") != null) {
	%>
	<p>
		<%
			out.print((String) request.getAttribute("mensajeError"));
		%>
	</p>
	<%
		}
	%>
</body>
</html>