package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Especialidad;
import entities.Persona;
import logic.ABMCEspecialidad;

/**
 * Servlet implementation class UpdateEspecialidad
 */
@WebServlet({"/UpdateEspecialidad", "/ModificacionEspecialidad"})
public class ModificacionEspecialidad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificacionEspecialidad() {
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
		
		ABMCEspecialidad abmcEsp = new ABMCEspecialidad();
		Especialidad esp = new Especialidad();
		
		switch (modo) {
			case("alta"):
				request.setAttribute("modo", "alta");
				request.getRequestDispatcher("WEB-INF/formularioEspecialidad.jsp").forward(request, response);
				break;
			
			case("modificacion"):							
				esp.setId(Integer.parseInt(request.getParameter("seleccion")));
				esp = abmcEsp.consultaUno(esp);
		
				request.setAttribute("modo", "modificacion");
				request.setAttribute("especialidad", esp);
				request.getRequestDispatcher("WEB-INF/formularioEspecialidad.jsp").forward(request, response);
				break;
				
			case("baja"):
				esp.setId(Integer.parseInt(request.getParameter("seleccion")));
				abmcEsp.baja(esp);
			
				request.getRequestDispatcher("WEB-INF/altaExitosa.jsp").forward(request, response);
				break;		
		
		}
		
		
	}

}
