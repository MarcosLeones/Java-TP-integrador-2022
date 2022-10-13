package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.ObraSocial;
import entities.Persona;
import logic.ABMCObraSocial;

/**
 * Servlet implementation class ModificacionObraSocial
 */
@WebServlet("/ModificacionObraSocial")
public class ModificacionObraSocial extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificacionObraSocial() {
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
		
		ABMCObraSocial abmcOS = new ABMCObraSocial();
		ObraSocial os = new ObraSocial();
		
		switch (modo) {
			case("alta"):
				request.setAttribute("modo", "alta");
				request.getRequestDispatcher("WEB-INF/formularioObraSocial.jsp").forward(request, response);
				break;
			
			case("modificacion"):							
				os.setId(Integer.parseInt(request.getParameter("seleccion")));
				os = abmcOS.consultaUno(os);
		
				request.setAttribute("modo", "modificacion");
				request.setAttribute("obra", os);
				request.getRequestDispatcher("WEB-INF/formularioObraSocial.jsp").forward(request, response);
				break;
				
			case("baja"):
				os.setId(Integer.parseInt(request.getParameter("seleccion")));
				abmcOS.baja(os);
			
				request.getRequestDispatcher("WEB-INF/altaExitosa.jsp").forward(request, response);
				break;		
		
		}

		
	}

}
