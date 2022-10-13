<%@page import="entities.ObraSocial"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

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

</body>
</html>