package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Persona;

/**
 * Servlet implementation class VolverAlMenu
 */
@WebServlet("/VolverAlMenu")
public class VolverAlMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VolverAlMenu() {
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
		if (p.getRol() == "paciente") {
			request.getRequestDispatcher("WEB-INF/menuPaciente.jsp").forward(request, response);
			return;
		}
		else if (p.getRol() == "profesional") {
			request.getRequestDispatcher("WEB-INF/menuProfesional.jsp").forward(request, response);
			return;
		}
		else {
			request.getRequestDispatcher("index.html").forward(request, response);
			return;
		}
	}

}
