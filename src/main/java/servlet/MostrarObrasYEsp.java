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
 * Servlet implementation class MostrarObrasYEsp
 */
@WebServlet({ "/MostrarObrasYEsp", "/mostrarobrasyesp" })
public class MostrarObrasYEsp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostrarObrasYEsp() {
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
		
		
		Persona paciente = (Persona)request.getSession().getAttribute("usuario");
		if (paciente.getRol() != "paciente") {
			request.getRequestDispatcher("WEB-INF/sinPermiso.html").forward(request, response);
			return;
		}
		
		ReservarTurno rt = new ReservarTurno();
		ArrayList<ObraSocial> obras = rt.getObrasSociales(paciente);
		ArrayList<Especialidad> especialidades = rt.getEspecialidades();
		
		request.setAttribute("obras", obras);
		request.setAttribute("especialidades", especialidades);
		request.getRequestDispatcher("WEB-INF/seleccionarObraYEsp.jsp").forward(request, response);
		return;
	}

}
