<%@page import="entities.Especialidad"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Especialidades</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  	<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
  	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
  	<link rel="stylesheet" href="style/styles1.css">
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
		<h1>Especialidades</h1>
		
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
	</div>
	
</body>
</html>