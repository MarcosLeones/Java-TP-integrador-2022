<%@page import="entities.ObraSocial"%>
<%@page import="entities.Especialidad"%>
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
	
	<h2>Seleccione una Especialidad</h2>
	<%
	java.util.ArrayList<Especialidad> especialidades = (java.util.ArrayList<Especialidad>)request.getAttribute("especialidades");	
	%>
	
<form action="MostrarProfesionales" method="post">
	
	<select name="esp">
    	<c:forEach items="${especialidades}" var="especialidad">
        	<option value="${especialidad.id}">
        		${especialidad.nombre}
            </option>
        </c:forEach>
    </select>
	
	<h2>Seleccione una Obra Social</h2>
	<%
	java.util.ArrayList<ObraSocial> obras = (java.util.ArrayList<ObraSocial>)request.getAttribute("obras");
	%>
	
	<select name="os">
    	<c:forEach items="${obras}" var="obra">
        	<option value="${obra.id}">
            	${obra.nombre}
            </option>
        </c:forEach>
    </select>
	
	
	
	<button type="submit">Buscar Profesionales</button>
</form>
	
</body>
</html>