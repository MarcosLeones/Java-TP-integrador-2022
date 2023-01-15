<%@page import="entities.ObraSocial"%>
<%@page import="entities.Especialidad"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Reservar Turno</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  	<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
  	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<div class="navbar-nav ">
			<form class="nav-item mx-2" action="VolverAlMenu" method="post">
				    <button class="btn btn-primary" type="submit">Volver al menú</button>  
			</form>
		
			<form class="nav-item mx-2" class="form-inline mr-auto" action="Signout" method="post">
				    <button class="btn btn-primary" id="btn-salir" type="submit">Salir</button>  
			</form>
		</div>
	</nav>	
	
	<div class="container">
	
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
	</div>
	
</body>
</html>