<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Menú Principal</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  	<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
  	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
  	 <link rel="stylesheet" href="style/styles1.css">
</head>
<body>

	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<div class="navbar-nav">
			<form class="nav-item" action="Especialidades" method="post">
				    <button class="btn btn-primary mx-2" type="submit">Especialidades</button>  
			</form>
			
			<form class="nav-item" action="ObrasSociales" method="post">
				    <button class=" btn btn-primary mx-2" type="submit">Obras Sociales</button>  
			</form>
			
			<form class="nav-item" action="Personas" method="post">
				    <button class="btn btn-primary mx-2" type="submit">Personas</button>  
			</form>
			
			<form class="nav-item" action="CrearTurnos" method="post">
				<button class="btn btn-primary mx-2" type="submit">Crear turnos</button>  
			</form>
			
			<form class="nav-item" action="MostrarTurnosCreados" method="post">
				<button class="btn btn-primary mx-2" type="submit">Marcar turnos disponibles</button>  
			</form>
			
			<form class="nav-item" action="MostrarTurnosDelDia" method="post">
				    <button class=" btn btn-primary mx-2" type="submit">Registrar Atención</button>  
			</form>
		
			<form class="nav-item" action="Signout" method="post">
			    <button class=" btn btn-primary mx-2"  type="submit">Salir</button>  
			</form>
		</div>
	</nav>
		
	<h1>Menú Profesional</h1>

	
</body>
</html>