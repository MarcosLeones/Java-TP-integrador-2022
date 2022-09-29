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

/**
 * Servlet implementation class MostrarTurnosCreados
 */
@WebServlet("/MostrarTurnosCreados")
public class MostrarTurnosCreados extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostrarTurnosCreados() {
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

//PROFESIONAL DE PRUEBA
Persona profesional = new Persona();
profesional.setLegajo(19);
profesional.setRol("profesional");
///////////////////		
		
		ABMCTurno abmcTurno = new ABMCTurno();
		ArrayList<Turno> turnos = abmcTurno.getTurnosCreados(profesional);
		
		request.setAttribute("turnos", turnos);
		request.getRequestDispatcher("WEB-INF/marcarTurnos.jsp").forward(request, response);
		return;		
	}

}
