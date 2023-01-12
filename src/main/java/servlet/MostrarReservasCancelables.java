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
import logic.CancelarTurno;

/**
 * Servlet implementation class MostrarReservasCancelables
 */
@WebServlet("/MostrarReservasCancelables")
public class MostrarReservasCancelables extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostrarReservasCancelables() {
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
		if (p.getRol() != "paciente") {
			request.getRequestDispatcher("WEB-INF/sinPermiso.html").forward(request, response);
			return;
		}
		
		CancelarTurno ct = new CancelarTurno();
		ArrayList<Turno> turnos = ct.getReservasCancelables(p);
		request.setAttribute("turnos", turnos);
		request.getRequestDispatcher("WEB-INF/listadoReservasCancelables.jsp").forward(request, response);
		return;
	}

}
