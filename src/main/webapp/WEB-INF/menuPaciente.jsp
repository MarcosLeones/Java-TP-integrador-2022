<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Menú principal</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  	<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
  	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<div class="navbar-nav">
			<form class="nav-item" action="MostrarObrasYEsp" method="post">
				    <button class="btn btn-primary mx-2" type="submit">Reservar Turno</button>
			</form>
			
			<form class="nav-item" action="MostrarReservas" method="post">
				    <button class="btn btn-primary mx-2" type="submit">Mis Turnos Reservados</button>
			</form>
			
			<form class="nav-item" action="MostrarReservasCancelables" method="post">
				    <button class="btn btn-primary mx-2" type="submit">Cancelar Reserva de Turno</button>
			</form>
			
			<form class="nav-item" action="Signout" method="post">
					<button class="btn btn-primary mx-2" type="submit">Salir</button>  
			</form>
		</div>
	</nav>
	<h1>Menú paciente</h1>
</body>
</html>