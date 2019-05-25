package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Authentification;
import model.Database;

/**
 * Servlet implementation class Authentification
 */
@WebServlet("/Authentification")
public class AuthentificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthentificationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean success=Authentification.auhtenticate(request.getParameter("email"), 
				request.getParameter("password")
				);
		if (success) {
			HttpSession session = request.getSession(true);
			//set attribute user to session
			session.setAttribute("user",Database.findUserByEmail(request.getParameter("email")));
			request.getRequestDispatcher("/acceuil.jsp").forward(request, response);
			
		}else {
			PrintWriter out = response.getWriter();
			out.println("<h2 color='red'>Error in the login or password</h2>");
			request.getRequestDispatcher("/connexion.html").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	
	}

}
