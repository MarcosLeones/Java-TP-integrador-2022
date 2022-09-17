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
import entities.ObraSocial;
import entities.Paciente;
import entities.Persona;
import logic.ABMCPersona;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
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
		// TODO Auto-generated method stub
		
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
		
		
		if (password.equals(repeatPassword)) {
		
			Paciente paciente = new Paciente();
			Documento documento = new Documento();
			ArrayList<ObraSocial> obrasSociales = new ArrayList<ObraSocial>();
			
			documento.setTipo(tipoDoc);
			documento.setNro(nroDoc);		
			paciente.setDocumento(documento);
			paciente.setObrasSociales(obrasSociales);
			paciente.setNombre(nombre);
			paciente.setApellido(apellido);
			paciente.setEmail(email);
			paciente.setSexo(sexo);
			paciente.setDomicilio(domicilio);
			paciente.setTelefono(telefono);
			paciente.setPassword(password);
			paciente.setRol("paciente");
			
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    LocalDate fechaNac = LocalDate.parse(fechaNacStr, dateFormat);
			paciente.setFechaNacimiento(fechaNac);
			
			try {
				ABMCPersona alta = new ABMCPersona();
				alta.alta(paciente);
			
				Persona per = paciente;
				request.getSession().setAttribute("usuario", per);
				request.getRequestDispatcher("WEB-INF/menuPaciente.jsp").forward(request, response);
				
			} catch(Exception ex) {
				String msg = ex.getMessage();
				request.setAttribute("mensaje", msg);
				request.getRequestDispatcher("WEB-INF/registerError.jsp").forward(request, response);
			}
			
			
		}
		
		
	}

}
