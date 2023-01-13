<%@page import="entities.Turno"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Turnos del día</title>
</head>
<body>
	<h1>Seleccione un turno</h1>
	<form action="MostrarHistoriaClinica" method="post">
		<ul>
			<%
			java.util.ArrayList<Turno> turnos = (java.util.ArrayList<Turno>)request.getAttribute("turnos");
			
			for(Turno t : turnos){
			%>
				<li><input type="radio" name="turno" value="<%=t.getId()%>"/> <%=t.getHora().toString()%> - <%=t.getPaciente().getApellido()%>, <%=t.getPaciente().getNombre()%></li>
			<%}%>
	
		</ul>
		<button type="submit">Mostrar Historia Clínica</button>	    
	</form>
</body>
</html>