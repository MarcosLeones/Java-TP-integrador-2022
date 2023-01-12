<%@page import="entities.Turno"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Turnos reservados</title>
</head>
<body>

	<h1>Seleccione el turno que desea cancelar</h1>
	<form action="CancelarReserva" method="post">
	<table>
		<tr>
			<th>Seleccionar</th>
			<th>Día</th>
			<th>Hora</th>
			<th>Profesional</th>
		</tr>
	<%
		java.util.ArrayList<Turno> turnos = (java.util.ArrayList<Turno>)request.getAttribute("turnos");
		
		for (Turno t : turnos){
	%>
		<tr>
			<td><%=t.getFecha()%></td>
			<td><%=t.getHora()%></td>
			<td><%=t.getProfesional().getNombre()%> <%=t.getProfesional().getApellido()%></td>
			<td><input type="radio" name="turno" value="<%=t.getId()%>"/></td>
		</tr>
	<%}%>
	</table>
	<button type="submit">Cancelar Reserva</button>
	</form>
		
			    

</body>
</html>