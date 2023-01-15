<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Ha ocurrido un error</title>
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
			<h1>Error</h1>
			<p>${mensaje}</p>
		</div>
</body>
</html>