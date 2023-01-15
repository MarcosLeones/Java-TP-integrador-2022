<%@page import="entities.ObraSocial"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Obras Sociales</title>
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
		<form action="GuardarObraSocial" method="post">
			
			<%
			ObraSocial os = null;
		String modo = (String)request.getAttribute("modo");
		String nombre = "";
		
		
		if (modo.equals("alta")){
			%>
			<input type="hidden" name="modo" value="alta"/>
			<%
		}
		
		if (modo.equals("modificacion")){
			os = (ObraSocial)request.getAttribute("obra");
			nombre = os.getNombre();
			%>
			<input type="hidden" name="modo" value="modificacion"/>
			<input type="hidden" name="id" value="<%=os.getId()%>"/>
			<%
		}
	%>
	
			<label for="nombre"><b>Nombre: </b></label>
			<input type="text" placeholder="Ingrese nombre de la Obra Social" name="nombre" value="<%=nombre%>" required>
			
			<button type="submit">Guardar</button>
		
		</form>
	</div>

</body>
</html>