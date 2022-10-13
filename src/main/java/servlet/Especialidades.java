package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Especialidad;
import entities.Persona;
import logic.ABMCEspecialidad;

/**
 * Servlet implementation class ConsultaEspecialidades
 */
@WebServlet("/Especialidades")
public class Especialidades extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Especialidades() {
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
		
		ABMCEspecialidad abmcEsp = new ABMCEspecialidad();
		ArrayList<Especialidad> especialidades = abmcEsp.consultaTodos();
		request.setAttribute("especialidades", especialidades);
		
		request.getRequestDispatcher("WEB-INF/consultaEspecialidades.jsp").forward(request, response);
		return;
	}

}
