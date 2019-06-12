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

import model.Adresse;
import model.Database;
import model.Register;
import model.User;

/**
 * Servlet implementation class CreateEvent
 */
@WebServlet("/CreateEvent")
public class CreateEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateEvent() {
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
			String date=request.getParameter("date");
			String num=request.getParameter("street_number");
			String street=request.getParameter("street");
			String country=request.getParameter("country");
			String city=request.getParameter("city");
			String description=request.getParameter("description");
			Adresse adresse=new Adresse(street, num, city, country);
			List<String> interests=Arrays.asList(request.getParameter("interests").split(","));
			
			Register.register_event(name,date,adresse,interests,description,
					user);
			
			session.setAttribute("events", Database.getAllEvents().toArray());
			response.sendRedirect(request.getContextPath()+ "/acceuil.jsp");
		}else {
			response.sendRedirect(request.getContextPath()+ "/connexion.html");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
