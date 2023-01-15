package servlet;

import java.io.IOException;
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
 * Servlet implementation class GuardarAtencion
 */
@WebServlet("/GuardarAtencion")
public class GuardarAtencion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuardarAtencion() {
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
		
		String historia_clinica = request.getParameter("historia_clinica");
		String estado = request.getParameter("estado");
		int id_turno = Integer.parseInt(request.getParameter("turno_actual"));
		ABMCTurno abmcTurno = new ABMCTurno();
		Turno t = new Turno();
		t.setId(id_turno);
		Turno turno = abmcTurno.consultaUno(t);
		turno.setEstado(estado);
		turno.setHistoriaClinica(historia_clinica);
		RegistrarAtencion ra = new RegistrarAtencion();
		ra.registrarHistoriaClinica(turno);
		request.getRequestDispatcher("WEB-INF/menuProfesional.jsp").forward(request, response);
		return;
		
	}

}
