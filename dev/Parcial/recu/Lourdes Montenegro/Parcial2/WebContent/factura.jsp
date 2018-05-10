<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3 style="color: grey">Fecha: </h3>
<script type="text/javascript">
var dt = new Date();

var month = dt.getMonth()+1;
var day = dt.getDate();
var year = dt.getFullYear();
document.write(month + '-' + day + '-' + year);

</script>
<%
Object puesto=request.getAttribute("puesto");
Object combo=request.getAttribute("pedido");
Object precio=request.getAttribute("precio");
%>
<h3 style="color: grey">Puesto: <%out.print(puesto);  %></h3><br>
<br>
<h3 style="color: grey">Combo pedido: <%out.print(combo);%></h3><br>
 <br>
<h3 style="color: grey">Precio: <%out.print(precio); %> australes</h3>


</body>
</html>