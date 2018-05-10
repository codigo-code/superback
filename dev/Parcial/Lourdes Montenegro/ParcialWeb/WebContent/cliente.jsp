<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html 
>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1 style="color:red">Registrar cliente</h1>
<form action="/resgistrarcliente" method="post">
<input type="text" placeholder="Nombre y apellido" name="cliente" required><br>
<input type="text" placeholder="Marca" name="marca" required><br>
<input type="text" placeholder="Ingrese los numeros de su tarjeta" name="numerotarjeta" required ><br>
<input type="text" placeholder="Caldholder" name="caldholder" required > <br>
<input type="date" placeholder="Fecha de vencimiento" name="fechavencimiento" required><br>
<input type="number" placeholder="Saldo inicial" name="saldo" required><br>

<input type="submit" value="Enviar informacion">

</form>
</body>
</html>