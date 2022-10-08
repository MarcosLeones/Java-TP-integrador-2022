package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Persona;
import entities.Turno;
import logic.ReservarTurno;

/**
 * Servlet implementation class MostrarTurnosDisponibles
 */
@WebServlet({ "/MostrarTurnosDisponibles", "/mostrarturnosdisponibles" })
public class MostrarTurnosDisponibles extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostrarTurnosDisponibles() {
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
//PROFESIONAL DE PRUEBA //////
		Persona profesional = new Persona();
		profesional.setLegajo(19);
//////////////////////		
*/
		
		int legajoProf = Integer.parseInt(request.getParameter("prof"));
		Persona profesional = new Persona();
		profesional.setLegajo(legajoProf);
		

		Persona paciente = (Persona)request.getSession().getAttribute("usuario");
		if (paciente.getRol() != "paciente") {
			request.getRequestDispatcher("WEB-INF/sinPermiso.html").forward(request, response);
			return;
		}
		
		//Persona profesional = (Persona) request.getSession().getAttribute("profesional");
		ReservarTurno rt = new ReservarTurno();
		ArrayList<Turno> turnos = rt.getTurnosDisponibles(profesional);
		
		if (turnos.size() == 0) {
			request.setAttribute("mensaje", "El profesional no tiene turnos disponibles.");
			request.getRequestDispatcher("WEB-INF/errorMensaje.jsp").forward(request, response);
			return;
		}
		else {
			request.setAttribute("turnos", turnos);
			request.getRequestDispatcher("WEB-INF/seleccionarTurno.jsp").forward(request, response);
			return;
		}
	}

}
