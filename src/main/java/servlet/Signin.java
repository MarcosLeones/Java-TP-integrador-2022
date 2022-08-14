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
		// TODO Auto-generated method stub
		//response.getWriter().append("POST - Served at: ").append(request.getContextPath());
		
		//response.getWriter().append(request.getParameter("email")).append(request.getParameter("password"));
		
		
		
		Persona per = new Persona();
		Login ctrlLogin = new Login();
		
		per.setEmail(request.getParameter("email"));
		per.setPassword(request.getParameter("password"));
		
		per = ctrlLogin.validate(per);
		
		
		if (per instanceof Paciente) {
			request.getSession().setAttribute("usuario", per);
			request.getRequestDispatcher("WEB-INF/menuPaciente.html").forward(request, response);
		}
		else if (per instanceof Profesional) {
				request.getSession().setAttribute("usuario", per);
				request.getRequestDispatcher("WEB-INF/menuProfesional.jsp").forward(request, response);;
		}
		
		else {
			request.getRequestDispatcher("WEB-INF/loginError.html").forward(request, response);;
		}

		
		
	}

}
