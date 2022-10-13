<%@page import="entities.Especialidad"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Especialidades</title>
</head>
<body>
	
	<form action="ModificacionEspecialidad" method="post">
	<%
		java.util.ArrayList<Especialidad> especialidades = (java.util.ArrayList<Especialidad>)request.getAttribute("especialidades");
		for(Especialidad e : especialidades){
	%>
			<input type="radio" name="seleccion" value=<%=e.getId()%>><label for="seleccion"><%=e.getNombre()%></label><br/>
	<%}%>
	
	<br/>
	<select name="modo">
		<option value="alta">Alta</option>
		<option value="modificacion">Modificación</option>
		<option value="baja">Baja</option>
	</select>
	
		<button type="submit">Siguiente</button>
	</form>
	
</body>
</html>