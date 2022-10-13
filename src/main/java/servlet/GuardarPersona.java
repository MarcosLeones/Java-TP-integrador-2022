package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Documento;
import entities.Especialidad;
import entities.ObraSocial;
import entities.Persona;
import logic.ABMCPersona;

/**
 * Servlet implementation class GuardarPersona
 */
@WebServlet("/GuardarPersona")
public class GuardarPersona extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuardarPersona() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Persona p = (Persona)request.getSession().getAttribute("usuario");
		if (p.getRol() != "profesional") {
			request.getRequestDispatcher("WEB-INF/sinPermiso.html").forward(request, response);
			return;
			
		}
		
		String modo = request.getParameter("modo");
		
		String tipoDoc = request.getParameter("tipoDoc");
		int nroDoc = Integer.parseInt(request.getParameter("nroDoc"));
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String email = request.getParameter("email");
		String sexo = request.getParameter("sexo");
		String domicilio = request.getParameter("domicilio");
		String telefono = request.getParameter("telefono");
		String fechaNacStr = request.getParameter("fechaNac");
		String password = request.getParameter("password");
		String repeatPassword = request.getParameter("repeatPassword");
		String rol = request.getParameter("rol");		
		String[] strObras = request.getParameterValues("obras");
		
		
		if (password.equals(repeatPassword)) {
			
			Persona persona = new Persona();
			Documento documento = new Documento();
			documento.setTipo(tipoDoc);
			documento.setNro(nroDoc);		
			persona.setDocumento(documento);
			persona.setNombre(nombre);
			persona.setApellido(apellido);
			persona.setEmail(email);
			persona.setSexo(sexo);
			persona.setDomicilio(domicilio);
			persona.setTelefono(telefono);
			persona.setPassword(password);
			persona.setRol(rol);
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    LocalDate fechaNac = LocalDate.parse(fechaNacStr, dateFormat);
			persona.setFechaNacimiento(fechaNac);
			
			ArrayList<ObraSocial> obras = new ArrayList<ObraSocial>();
			for (int i=0; i<strObras.length; i++) {
				int id = Integer.parseInt(strObras[i]);
				obras.add(new ObraSocial());
				obras.get(i).setId(id);
			}
			persona.setObrasSociales(obras);
			
			ArrayList<Especialidad> especialidades = new ArrayList<Especialidad>();
			if (rol.equals("profesional")) {
				String[] strEspecialidades = request.getParameterValues("especialidades");
				int length = 0;
				if (strEspecialidades != null) {
					length = strEspecialidades.length;
				}
				for (int i=0; i<length; i++) {
					int id = Integer.parseInt(strEspecialidades[i]);
					especialidades.add(new Especialidad());
					especialidades.get(i).setId(id);
				}	
			}
			persona.setEspecialidades(especialidades);
		
			ABMCPersona abmcPer = new ABMCPersona();
		
			if (modo.equals("alta")) {

				try {
					abmcPer.alta(persona);
				} catch (Exception ex) {
					request.setAttribute("mensaje", ex.getMessage());
					request.getRequestDispatcher("WEB-INF/errorMensaje.jsp").forward(request, response);
					return;
				}
				request.getRequestDispatcher("WEB-INF/altaExitosa.jsp").forward(request, response);
				return;
			}

			if (modo.equals("modificacion")) {
				int legajo = Integer.parseInt(request.getParameter("legajo"));
				persona.setLegajo(legajo);
				try {
					abmcPer.modificacion(persona);
				} catch (Exception ex) {
					ex.printStackTrace();
					request.setAttribute("mensaje", ex.getMessage());
					request.getRequestDispatcher("WEB-INF/errorMensaje.jsp").forward(request, response);
					return;
				}
				request.getRequestDispatcher("WEB-INF/altaExitosa.jsp").forward(request, response);
				return;
			}
		}
		
		else {
			request.setAttribute("mensaje", "Las contraseñas no coinciden");
			request.getRequestDispatcher("WEB-INF/errorMensaje.jsp").forward(request, response);
			return;
		}
		
	}

}
