<%@page import="entities.Turno"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registrar Atención</title>
</head>
<body>
	<h1>Historia Clínica</h1>
	<ul>
		<%
		java.util.ArrayList<Turno> turnos = (java.util.ArrayList<Turno>)request.getAttribute("turnos");
		entities.Turno turno_actual = (entities.Turno)request.getAttribute("turno_actual");	
		for(Turno t : turnos){
		%>
			<li><%=t.getFecha().toString()%> - <%=t.getHistoriaClinica() == null ? "" : t.getHistoriaClinica() %></li>
		<%}%>
	</ul>
	
	<form action="GuardarAtencion" method="post">
		<input type="hidden" name="turno_actual" value="<%=turno_actual.getId()%>">
		<label for="historia_clinica"><b>Nueva Entrada: </b></label>
		<input type="text" name="historia_clinica">
		<label for="estado"><b>Estado del turno: </b></label>
		<select name="estado" required>
			<option value="atendido">Atendido</option>
  			<option value="cancelado">Cancelado</option>
		</select>
		<button type="submit">Guardar</button>	    
	</form>

</body>
</html>