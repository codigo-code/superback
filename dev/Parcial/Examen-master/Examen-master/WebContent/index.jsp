<%@page import="com.utn.entities.Tarjeta"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="js/scripts.js"></script>
</head>
<body>
	<form action="inicio" method="post">
		<table>
			<tr>
				<td>Marca de Tarjeta:</td>
				<td><select name="marca">
						<option value="squa">SQUA</option>
						<option value="sco">SCO</option>
						<option value="pere">PERE</option>
				</select></td>
			</tr>
			<tr>
				<td>Numero de serie:</td>
				<td><input type="number" name="numero"></td>
			</tr>
			<tr>
				<td>Tirular de la Tarjeta:</td>
				<td><input type="text" name="cardholder"></td>
			</tr>
			<tr>
				<td>Fecha de vencimiento</td>
				<td><input type="date" name="fechaVencimiento"></td>
			</tr>
			<tr>
				<td>Total de consumo</td>
				<td><input type="number" name="consumo"></td>
			</tr>
			<tr>
				<td><input type="submit"></td>
			</tr>
		</table>
	</form>
	
	<%
		Tarjeta t = (Tarjeta) request.getAttribute("tarjeta");
		if (t != null) {
	%>
	<div style="text-align: center;">
		<h1 style="color: green;">Consumo exitoso</h1>
	</div>
	<%
		out.print(t.toString());
		}
	%>
</body>
</html>