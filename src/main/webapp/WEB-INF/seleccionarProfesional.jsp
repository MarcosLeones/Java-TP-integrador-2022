<%@page import="entities.Persona"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Seleccionar profesional</h1>
	<%
	java.util.ArrayList<Persona> profesionales = (java.util.ArrayList<Persona>)request.getAttribute("profesionales");
	
	for(Persona p : profesionales){
	%>
		<p><%=p.getNombre()%> <%=p.getApellido() %></p>
	<%}%>
	
	<form action="MostrarTurnosDisponibles" method="post">
		    <button type="submit">Buscar Turnos</button>
	</form>
</body>
</html>