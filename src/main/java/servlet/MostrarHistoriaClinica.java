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
import logic.ABMCTurno;
import logic.RegistrarAtencion;

/**
 * Servlet implementation class MostrarHistoriaClinica
 */
@WebServlet("/MostrarHistoriaClinica")
public class MostrarHistoriaClinica extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostrarHistoriaClinica() {
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
		
		int id_turno = Integer.parseInt(request.getParameter("turno"));
		ABMCTurno abmcTurno = new ABMCTurno();
		Turno t = new Turno();
		t.setId(id_turno);
		Turno turno = abmcTurno.consultaUno(t);
		RegistrarAtencion ra = new RegistrarAtencion();
		ArrayList<Turno> turnos = ra.getHistoriasClinica(turno.getPaciente());
		request.setAttribute("turnos", turnos);
		request.setAttribute("turno_actual", turno);
		request.getRequestDispatcher("WEB-INF/formularioAtencion.jsp").forward(request, response);
		return;
		
	}

}
