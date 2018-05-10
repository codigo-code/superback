<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tarjeta de Credito</title>
</head>
<body>

<form name="formularioPedido" method="post" action="cobrar">
		<input type="text" name="marca" placeholder="marca" required /> </br>
		<input type="number" name="numero" placeholder="numero de la tarjeta" required /> </br>
		<input type="text" name="cliente" placeholder="nombre del propietario" required /> </br>
		<input type="Date" name="fechaVenc" placeholder=" DD/MM/AAAA" required /></br>
		<input type="number" name="monto" placeholder="Monto" required /></br>
		
		<input type="submit" value="enviar pedido" />
	</form>

</body>
</html>