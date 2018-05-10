<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="js/script.js"></script>
</head>
<body>
	<form action="RegistroTarjeta" id="formulario" method="post"
		onsubmit="return validarFormulario()">
		<table
			style="background-color: Lightgreen; margin-left: 20px; margin-right: 20px;">
			<tr>
				<td><h3 style="color: red">Registro Tarjeta</h3></td>
				<td></td>
			</tr>
			<tr>
				<td>Nombre:</td>
				<td><input type="text" name="cardholder" placeholder="Nombre"
					required></td>
			</tr>
			<tr>
				<td>Edad:</td>
				<td><input type="number" name="edad" placeholder="Edad"
					required></td>
			</tr>
			<tr>
				<td>Marca:</td>
				<td><select name="marca" required>
						<option>SQUA</option>
						<option>SCO</option>
						<option>PERE</option>
				</select></td>
			</tr>
			<tr>
				<td>F. Vencimiento:</td>
				<td><input type="date" name="vencimiento" min="2000-01-02"></td>
			</tr>
			<tr>
				<td>Consumo:</td>
				<td><input type="number" name="consumo" placeholder="Consumo"
					required></td>
			</tr>
			<tr>
				<td><input type="submit" name="submit" value="registrar"></td>
			</tr>
		</table>
	</form>
</body>
</html>