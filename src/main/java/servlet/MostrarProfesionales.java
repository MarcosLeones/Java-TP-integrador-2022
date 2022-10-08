package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Especialidad;
import entities.ObraSocial;
import entities.Persona;
import logic.ReservarTurno;

/**
 * Servlet implementation class MostrarProfesionales
 */
@WebServlet("/MostrarProfesionales")
public class MostrarProfesionales extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostrarProfesionales() {
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
		
		/*
//ESPECIALIDAD Y OBRA DE PRUEBA///////////////////		
		Especialidad e = new Especialidad();
		e.setId(1);
		ObraSocial os = new ObraSocial();
		os.setId(5);
/////////////////
		*/
		
		int idEsp = Integer.parseInt(request.getParameter("esp"));
		int idOS = Integer.parseInt(request.getParameter("os"));		
		Especialidad e = new Especialidad();
		e.setId(idEsp);
		ObraSocial os = new ObraSocial();
		os.setId(idOS);
		
		
		Persona paciente = (Persona)request.getSession().getAttribute("usuario");
		if (paciente.getRol() != "paciente") {
			request.getRequestDispatcher("WEB-INF/sinPermiso.html").forward(request, response);
			return;
		}
		
		
		ReservarTurno rt = new ReservarTurno();
		ArrayList<Persona> profesionales = rt.getProfesionales(e, os);
		
		if (profesionales.size() == 0) {
			request.setAttribute("mensaje", "No se encontraron profesionales.");
			request.getRequestDispatcher("WEB-INF/errorMensaje.jsp").forward(request, response);
			return;
		}
		else {
			request.setAttribute("profesionales", profesionales);
			request.getRequestDispatcher("WEB-INF/seleccionarProfesional.jsp").forward(request, response);
			return;
		}
		
	}

}
