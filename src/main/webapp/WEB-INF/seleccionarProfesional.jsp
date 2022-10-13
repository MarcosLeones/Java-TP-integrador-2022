<%@page import="entities.Persona"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
	%>
	
	<form action="MostrarTurnosDisponibles" method="post">
		<select name="prof">
    		<c:forEach items="${profesionales}" var="profesional">
        		<option value="${profesional.legajo}">
        		${profesional.nombre} ${profesional.apellido}
            	</option>
        	</c:forEach>
    	</select>
		 <button type="submit">Buscar Turnos</button>
	</form>
</body>
</html>