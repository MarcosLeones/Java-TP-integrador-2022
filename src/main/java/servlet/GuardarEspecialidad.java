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
 * Servlet implementation class RegistrarEspecialidad
 */
@WebServlet("/GuardarEspecialidad")
public class GuardarEspecialidad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuardarEspecialidad() {
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
		String nombreEsp = request.getParameter("nombre");

		ABMCEspecialidad abmcEsp = new ABMCEspecialidad();
		
		if (modo.equals("alta")) {
			Especialidad esp = new Especialidad();
			esp.setNombre(nombreEsp);
			try {
				abmcEsp.alta(esp);
			} catch (Exception ex) {
				request.setAttribute("mensaje", ex.getMessage());
				request.getRequestDispatcher("WEB-INF/errorMensaje.jsp").forward(request, response);
				return;
			}
			request.getRequestDispatcher("WEB-INF/altaExitosa.jsp").forward(request, response);
			return;
		}

		if (modo.equals("modificacion")) {
			int idEsp = Integer.parseInt(request.getParameter("id"));
			Especialidad esp = new Especialidad();
			esp.setNombre(nombreEsp);
			esp.setId(idEsp);
			try {
				abmcEsp.modificacion(esp);
			} catch (Exception ex) {
				request.setAttribute("mensaje", ex.getMessage());
				request.getRequestDispatcher("WEB-INF/errorMensaje.jsp").forward(request, response);
				return;
			}
			request.getRequestDispatcher("WEB-INF/altaExitosa.jsp").forward(request, response);
			return;
		}
	}

}
