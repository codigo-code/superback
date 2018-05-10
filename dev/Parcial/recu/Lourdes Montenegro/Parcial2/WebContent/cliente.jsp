<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="pedidocliente" method="post">
<h4>Seleccione el puesto en el que se encuentra</h4><br>
<select name="puesto">
<option value="Abasto shoping">Abasto shoping</option>
<option value="San justo shoping">San justo shoping</option>
<option value="Terrazas de mayo">Terrazas de mayo</option>
<option value="Dot baires">Dot baires</option>
<option value="Unicenter">Unicenter</option>
<option value="Plaza oeste shoping">Plaza oeste shoping</option>
</select><br>
<h4>Elija el combo</h4>
<select name="pedido">
<option value="Pumper feliz">Pumper feliz precio:1000 australes</option>
<option value="Pumper mediano">Pumper mediano precio:2000 australes</option>
<option value="Pumper grande">Pumper grande precio:4000 australes</option>
<option value="Super pumper">Super pumper precio:5000 autrales</option>
</select><br>
<input type="submit" value="Imprimir factura">
</form>

</body>
</html>