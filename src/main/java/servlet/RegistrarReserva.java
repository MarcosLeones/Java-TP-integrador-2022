package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Persona;
import entities.Turno;
import logic.ReservarTurno;

/**
 * Servlet implementation class RegistrarReserva
 */
@WebServlet("/RegistrarReserva")
public class RegistrarReserva extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrarReserva() {
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
		
//TURNO DE PRUEBA ///////
		Turno turno = new Turno();
		turno.setId(1);
////////////////
		
		Persona paciente = (Persona)request.getSession().getAttribute("usuario");
		if (paciente.getRol() != "paciente") {
			request.getRequestDispatcher("WEB-INF/sinPermiso.html").forward(request, response);
			return;
		}
		
		//Turno turno = (Turno)request.getSession().getAttribute("turno");
		
		ReservarTurno rt = new ReservarTurno();
		try {
			rt.registrarReserva(turno, paciente);
		}
		catch (Exception ex) {
			request.setAttribute("mensajeError", ex.getMessage());
			request.getRequestDispatcher("WEB-INF/errorInesperado.jsp").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("WEB-INF/reservaConfirmada.jsp").forward(request, response);
		return;
	}

}
