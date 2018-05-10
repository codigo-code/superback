<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<select name="producto">
	<option value="Cortina">Cortina</option>
	<option value="Puerta">Puerta</option>
	<option value="Mesa">Mesa</option>
	<option value="Silla">Silla</option>
	<option value="Pepito">Pepito</option>
</select>
<input type="text" name="cantidad" onchange="calcularConsumo()"  placeholder="Cantidad a comprar"/>
<input type="number" name="consumo"  disabled="disabled"/>
<input type="date" name="fechaVencimiento" placeholder="ingrese fecha de vencimiento"/>
<input type="number" name="numeroTarjeta" placeholder="ingrese el numero de la tarjeta de credito">
<script type="text/javascript" src="js/formularioOperacion.js"></script>
</body>
</html>