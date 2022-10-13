<%@page import="entities.Persona"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Personas</title>
</head>
<body>

	<h1>Personas</h1>

	<form action="ModificacionPersona" method="post">
	<%
		java.util.ArrayList<Persona> personas = (java.util.ArrayList<Persona>)request.getAttribute("personas");
		for(Persona p : personas){
	%>
			<input type="radio" name="seleccion" value=<%=p.getLegajo()%>><label for="seleccion"><%=p.getApellido()%>, <%=p.getNombre()%>  - <%=p.getRol()%></label><br/>
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