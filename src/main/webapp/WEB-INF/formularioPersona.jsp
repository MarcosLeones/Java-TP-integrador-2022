<%@page import="entities.*"%>
<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form action="GuardarPersona" method="post">
	
	<%
	Persona p = null;
	String modo = (String)request.getAttribute("modo");
	
	String tipoDoc = "";
	int nroDoc = 0;
	String nombre = "";
	String apellido = "";
	String email = "";
	String password = "";
	String telefono = "";
	String domicilio = "";
	LocalDate fechaNac = LocalDate.now();
	String sexo = "";
	String rol = "";
	
	
	if (modo.equals("alta")){
		%>
		<input type="hidden" name="modo" value="alta"/>
		<%
	}
	
	if (modo.equals("modificacion")){
		p = (Persona)request.getAttribute("persona");
		tipoDoc = p.getDocumento().getTipo();
		nroDoc = p.getDocumento().getNro();
		nombre = p.getNombre();
		apellido = p.getApellido();
		email = p.getEmail();
		password = p.getPassword();
		telefono = p.getTelefono();
		domicilio = p.getDomicilio();
		fechaNac = p.getFechaNacimiento();
		sexo = p.getSexo();
		rol = p.getRol();
		%>
		<input type="hidden" name="modo" value="modificacion"/>
		<input type="hidden" name="legajo" value="<%=p.getLegajo()%>"/>
		<%
	}
%>
	
	
	
	
		<label for="email"><b>Email: </b></label>
		<input type="email" placeholder="Ingrese e-mail" name="email" value="<%=email %>" required>
		
		<label for="nombre"><b>Nombre: </b></label>
		<input type="text" placeholder="Ingrese nombre" name="nombre" value="<%=nombre %>" required>
		
		<label for="apellido"><b>Apellido: </b></label>
		<input type="text" placeholder="Ingrese apellido" name="apellido" value="<%=apellido %>" required>
		
		<label for="tipoDoc"><b>Tipo Documento: </b></label>
		<select name="tipoDoc" required>
  			<option value="dni" <%if (tipoDoc.equals("dni")){%> selected<%}%>>D.N.I.</option>
  			<option value="pasaporte" <%if (tipoDoc.equals("pasaporte")){%> selected<%}%>>Pasaporte</option>
  			<option value="libretaCivica" <%if (tipoDoc.equals("libretaCivica")){%> selected<%}%>>Libreta Cívica</option>
  			<option value="libretaEnrrolamiento" <%if (tipoDoc.equals("libretaEnrrolamiento")){%> selected<%}%>>Libreta de Enrrolamiento</option>
		</select>
		
		<label for="nroDoc"><b>Nro. Documento: </b></label>
		<input type="number" placeholder="Ingrese número de documento" name="nroDoc" value="<%=nroDoc %>" required>
		
		<label for="fechaNacimiento"><b>Fecha de nacimiento: </b></label>
		<input type="date" placeholder="Ingrese fecha de nacimiento" name="fechaNac" value="<%=fechaNac %>" required>
		
		<label for="sexo"><b>Sexo: </b></label>
		<select name="sexo" required>
			<option value="f" <%if (sexo.equals("f")){%> selected<%}%>>Femenino</option>
  			<option value="m" <%if (sexo.equals("m")){%> selected<%}%>>Masculino</option>
		</select>
		
		<label for="domicilio"><b>Domicilio: </b></label>
		<input type="text" placeholder="Ingrese Domicilio" name="domicilio" value="<%=domicilio %>" required>
		
		<label for="telefono"><b>Teléfono: </b></label>
		<input type="tel" placeholder="Ingrese número de teléfono" name="telefono" value="<%=telefono %>" required>
		
		<label for="password"><b>Contraseña: </b></label>
		<input type="password" placeholder="Ingrese contraseña" name="password" value="<%=password %>" required>
		
		<label for="repeatPassword"><b>Repetir Contraseña: </b></label>
		<input type="password" placeholder="Repetir contraseña" name="repeatPassword" value="<%=password %>" required>
		
		<label for="rol"><b>Rol: </b></label>
		<select name="rol" required>
			<option value="paciente" <%if (rol.equals("paciente")){%> selected<%}%>>Paciente</option>
  			<option value="profesional" <%if (rol.equals("profesional")){%> selected<%}%>>Profesional</option>
		</select>
	
	
	<h2>Obras Sociales</h2>
		<ul>
		<%
		java.util.ArrayList<ObraSocial> obras = (java.util.ArrayList<ObraSocial>)request.getAttribute("obras");
		
		for(ObraSocial os : obras){
			boolean check = false;
				if (modo.equals("modificacion")){
					for(ObraSocial osPer : p.getObrasSociales()){
						if (osPer.getId()== os.getId()){
							check = true;
							break;
						}
					}
				}
		%>
			<li>
				<input type="checkbox" name="obras" value="<%=os.getId()%>" <%if (check) {%> checked <%}%> ><%=os.getNombre()%>
			</li>
		<%}%>
		</ul>
		
		
		
		<%
		if (rol.equals("profesional")){		
		%>
		<h2>Especialidades</h2>
		<ul>
		<%			
		java.util.ArrayList<Especialidad> especialidades = (java.util.ArrayList<Especialidad>)request.getAttribute("especialidades");
		
		for(Especialidad esp : especialidades){
			boolean check = false;
				if (modo.equals("modificacion")){
					for(Especialidad espPer : p.getEspecialidades()){
						if (espPer.getId()== esp.getId()){
							check = true;
							break;
						}
					}
				}
		%>
			<li>
				<input type="checkbox" name="especialidades" value="<%=esp.getId()%>" <%if (check) {%> checked <%}%> ><%=esp.getNombre()%>
			</li>
		<%}}%>
		</ul>
		
		<button type="submit">Guardar</button>	
	
	</form>




</body>
</html>