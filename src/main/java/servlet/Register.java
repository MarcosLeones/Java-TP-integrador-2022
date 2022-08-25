package servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Documento;
import entities.ObraSocial;
import entities.Paciente;
import entities.Persona;
import logic.ABMCPaciente;

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
		int telefono = Integer.parseInt(request.getParameter("telefono"));
		String password = request.getParameter("password");
		String repeatPassword = request.getParameter("repeatPassword");
		
		if (password.equals(repeatPassword)) {
		
			Paciente paciente = new Paciente();
			Documento documento = new Documento();
			LinkedList<ObraSocial> obrasSociales = new LinkedList<ObraSocial>();
			
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
			
			//FECHA DE NACIMIENTO
			/*
			DateFormat df = new SimpleDateFormat("dd/MM/yyy");
			java.util.Date utilDate = null;
			try {
				utilDate = df.parse(request.getParameter("fechaNac"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			paciente.setFechaNacimiento(sqlDate);
			*/
			//
			
			
			
			ABMCPaciente alta = new ABMCPaciente();
			alta.alta(paciente);
			
			Persona per = paciente;
			request.getSession().setAttribute("usuario", per);
			request.getRequestDispatcher("WEB-INF/menuPaciente.jsp").forward(request, response);
			
		}
		
		
	}

}
