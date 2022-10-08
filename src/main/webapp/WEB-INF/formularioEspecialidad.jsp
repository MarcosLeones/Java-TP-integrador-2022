<%@page import="entities.Especialidad"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form action="GuardarEspecialidad" method="post">
<%
	Especialidad e = null;
	String modo = (String)request.getAttribute("modo");
	String nombre = "";
	
	
	if (modo.equals("alta")){
		%>
		<input type="hidden" name="modo" value="alta"/>
		<%
	}
	
	if (modo.equals("modificacion")){
		e = (Especialidad)request.getAttribute("especialidad");
		nombre = e.getNombre();
		%>
		<input type="hidden" name="modo" value="modificacion"/>
		<input type="hidden" name="id" value="<%=e.getId()%>"/>
		<%
	}
%>

		<label for="nombre"><b>Nombre: </b></label>
		<input type="text" placeholder="Ingrese nombre de la Especialidad" name="nombre" value="<%=nombre%>" required>
		
		<button type="submit">Guardar</button>
	
	</form>
	
</body>
</html>