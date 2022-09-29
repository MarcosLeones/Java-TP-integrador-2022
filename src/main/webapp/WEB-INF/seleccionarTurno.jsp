<%@page import="entities.Turno"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Seleccione un turno</h1>
	<%
	java.util.ArrayList<Turno> turnos = (java.util.ArrayList<Turno>)request.getAttribute("turnos");
	
	for(Turno t : turnos){
	%>
		<p> *** <%=t.getFecha().toString()%>   <%=t.getHora().toString() %></p>
	<%}%>
	
	<form action="RegistrarReserva" method="post">
		    <button type="submit">Reservar</button>
	</form>
</body>
</html>