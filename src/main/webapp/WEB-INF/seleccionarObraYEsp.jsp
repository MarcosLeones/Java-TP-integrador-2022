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
	
	for(Especialidad e : especialidades){
	%>
		<p><%=e.getNombre()%></p>
	<%}%>
	
	
	<select name="esp">
    	<c:forEach items="${especialidades}" var="Especialidad">
        	<option value="${Especialidad.id}"
            	<c:if test="${especialidad.id eq selectedEspId}">selected="selected"</c:if>>${especialiad.nombre}
            </option>
        </c:forEach>
    </select>
	
	<h2>Seleccione una Obra Social</h2>
	<%
	java.util.ArrayList<ObraSocial> obras = (java.util.ArrayList<ObraSocial>)request.getAttribute("obras");
	
	for(ObraSocial os : obras){
	%>
		<p><%=os.getNombre()%></p>
	<%}%>
	
	<select name="os">
    	<c:forEach items="${obras}" var="obra">
        	<option value="${obra.id}"
            	<c:if test="${obra.id eq selectedOSId}">selected="selected"</c:if>>${obra.nombre}
            </option>
        </c:forEach>
    </select>
	
	
	<form action="MostrarProfesionales" method="post">
		    <button type="submit">Buscar Profesionales</button>
	</form>
	
</body>
</html>