<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form name="formularioPedido" method="post" action="pedido/guardar">
		<input type="text" name="nombre" maxlength="100" placeholder="nombre del pedido" required /> </br>
		<input type="number" name="monto" placeholder="monto del pedido" required /> </br>
		<input type="number" name="descuento" placeholder="descuento del pedido" required /> </br>
		<input type="submit" value="enviar pedido" />
	</form>
	
</body>
</html>