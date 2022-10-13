<%@page import="entities.Turno"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form action="MarcarDisponible" method="post">
		<h1>Turnos</h1>
		<ul>
		<%
		java.util.ArrayList<Turno> turnos = (java.util.ArrayList<Turno>)request.getAttribute("turnos");
		
		for(Turno t : turnos){
		%>	<li>
				<input type="checkbox" name="turnos" value="<%=t.getId()%>"><%=t.getFecha()%> - <%=t.getHora()%>
			</li>
		<%}%>
		</ul>
		<button type="submit">Guardar</button>
	</form>
</body>
</html>