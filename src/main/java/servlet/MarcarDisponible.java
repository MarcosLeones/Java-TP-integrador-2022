package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Turno;
import logic.ABMCTurno;

/**
 * Servlet implementation class MarcarDisponible
 */
@WebServlet("/MarcarDisponible")
public class MarcarDisponible extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MarcarDisponible() {
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
		
		String[] strTurnos = request.getParameterValues("turnos");
		ArrayList<Turno> arTurnos = new ArrayList<Turno>();
		for (int i=0; i<strTurnos.length; i++) {
			int id = Integer.parseInt(strTurnos[i]);
			arTurnos.add(new Turno());
			arTurnos.get(i).setId(id);
			arTurnos.get(i).setEstado("disponible");
		}
		
		ABMCTurno abmcTurno = new ABMCTurno();
		abmcTurno.registrarTurnosDisponibles(arTurnos);
		request.getRequestDispatcher("WEB-INF/altaExitosa.jsp").forward(request, response);
		return;
	}

}
