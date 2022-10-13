package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.ObraSocial;
import entities.Persona;
import logic.ABMCObraSocial;

/**
 * Servlet implementation class ObrasSociales
 */
@WebServlet("/ObrasSociales")
public class ObrasSociales extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ObrasSociales() {
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
		
		ABMCObraSocial abmcOS = new ABMCObraSocial();
		ArrayList<ObraSocial> obras = abmcOS.consultaTodos();
		request.setAttribute("obras", obras);
		
		request.getRequestDispatcher("WEB-INF/consultaObrasSociales.jsp").forward(request, response);
		return;

		
	}

}
