<%@page import="entities.ObraSocial"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Obras Sociales</title>
</head>
<body>
	<form action="ModificacionObraSocial" method="post">
	<%
		java.util.ArrayList<ObraSocial> obras = (java.util.ArrayList<ObraSocial>)request.getAttribute("obras");
		for(ObraSocial os : obras){
	%>
			<input type="radio" name="seleccion" value=<%=os.getId()%>><label for="seleccion"><%=os.getNombre()%></label><br/>
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