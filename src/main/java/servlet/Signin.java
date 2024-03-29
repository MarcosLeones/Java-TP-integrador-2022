package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import entities.*;
import logic.*;

/**
 * Servlet implementation class Signin
 */
@WebServlet({ "/Signin", "/signin", "/SIGNIN", "/signIn", "/SignIn" })
public class Signin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("GET - Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

/*		
		Persona per = new Persona();
		Login ctrlLogin = new Login();
		
		per.setEmail(request.getParameter("email"));
		per.setPassword(request.getParameter("password"));
		
		Persona p = ctrlLogin.validate(per);
		
		if (p.getRol() == null || p.getEmail() == null || p.getLegajo() == 0) {
			request.getRequestDispatcher("WEB-INF/loginError.jsp").forward(request, response);
			return;
		}
		else {
			if (p.getRol().equals("profesional")) {
				p.setRol("profesional");
				request.getSession().setAttribute("usuario", p);
				request.getRequestDispatcher("WEB-INF/menuProfesional.jsp").forward(request, response);
				return;
			}
			else if (p.getRol().equals("paciente")) {
				p.setRol("paciente");
				request.getSession().setAttribute("usuario", p);
				request.getRequestDispatcher("WEB-INF/menuPaciente.jsp").forward(request, response);
				return;
			}
			else {
				request.getRequestDispatcher("WEB-INF/loginError.jsp").forward(request, response);
				return;
			}
		}
*/		

/////////////////////
String rol = request.getParameter("rol");
if (rol.equals("paciente")) {
/////USURARIO DE PRUEBA	PACIENTE//
		Persona prueba = new Persona();
		prueba.setLegajo(17);
		prueba.setRol("paciente");
		request.getSession().setAttribute("usuario", prueba);
////////////////////////////		
		//request.getSession().setAttribute("usuario", per);
		
		request.getRequestDispatcher("WEB-INF/menuPaciente.jsp").forward(request, response);
		return;
}
else {
		
/////USURARIO DE PRUEBA	PROFESIONAL//
		Persona prueba = new Persona();
		prueba.setLegajo(18);
		prueba.setRol("profesional");
		request.getSession().setAttribute("usuario", prueba);
////////////////////////////	
 
		//request.getSession().setAttribute("usuario", per);
		request.getRequestDispatcher("WEB-INF/menuProfesional.jsp").forward(request, response);
		return;
}		

	}

}
