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
import logic.ABMCEspecialidad;
import logic.ABMCObraSocial;
import logic.ABMCPersona;

/**
 * Servlet implementation class ModificacionPersona
 */
@WebServlet("/ModificacionPersona")
public class ModificacionPersona extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificacionPersona() {
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
		
				
		String modo = request.getParameter("modo");
		
		ABMCPersona abmcPer = new ABMCPersona();
		Persona per = new Persona();
		
		ABMCObraSocial abmcOS = new ABMCObraSocial();
		ArrayList<ObraSocial> obras;		
		ABMCEspecialidad abmcEsp = new ABMCEspecialidad();
		ArrayList<Especialidad> especialidades;
		
		
		switch (modo) {
			case("alta"):
				obras = abmcOS.consultaTodos();
				especialidades = abmcEsp.consultaTodos();		
				request.setAttribute("obras", obras);
				request.setAttribute("especialidades", especialidades);
				request.setAttribute("modo", "alta");
				request.getRequestDispatcher("WEB-INF/formularioPersona.jsp").forward(request, response);
				break;
			
			case("modificacion"):							
				per.setLegajo(Integer.parseInt(request.getParameter("seleccion")));
				per = abmcPer.consultaUno(per);
				obras = abmcOS.consultaTodos();
				especialidades = abmcEsp.consultaTodos();
				request.setAttribute("obras", obras);
				request.setAttribute("especialidades", especialidades);
				request.setAttribute("modo", "modificacion");
				request.setAttribute("persona", per);			
				request.getRequestDispatcher("WEB-INF/formularioPersona.jsp").forward(request, response);
				break;
				
			case("baja"):
				per.setLegajo(Integer.parseInt(request.getParameter("seleccion")));
				abmcPer.baja(per);
			
				request.getRequestDispatcher("WEB-INF/altaExitosa.jsp").forward(request, response);
				break;		
		
		}
		
	}

}
