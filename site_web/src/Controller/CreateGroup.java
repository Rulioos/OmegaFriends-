package Controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Database;
import model.Register;
import model.User;

/**
 * Servlet implementation class CreateGroup
 */
@WebServlet("/CreateGroup")
public class CreateGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateGroup() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		if(session!=null) {
			User user=(User)session.getAttribute("user");
			String name=request.getParameter("uname");
			List<String> interests=Arrays.asList(request.getParameter("interests").split(","));
			Register.register_group(name,user, interests);
			session.setAttribute("user",Database.findUserByEmail(user.getEmail()) );
			session.setAttribute("groups", Database.getAllGroups().toArray());
			//request.getRequestDispatcher("/acceuil.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath()+ "/acceuil.jsp");
		}else {
			//request.getRequestDispatcher("/connexion.html").forward(request, response);
			response.sendRedirect(request.getContextPath()+ "/connexion.html");
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
