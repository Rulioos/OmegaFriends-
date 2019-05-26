package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.Crypt;

import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;
import model.Database;
import model.User;

/**
 * Servlet implementation class ModificationProfil
 */
@WebServlet("/ModifProfil")
public class ModificationProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModificationProfil() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		boolean sucess;
		String password = Crypt.crypt(request.getParameter("password"), "$6$xxxx");
		switch (request.getParameter("form_type")) {
		case "mail":
			sucess=Database.updateUser(user, "email", request.getParameter("mail"), password);
			if (sucess) {
				user=Database.findUserByEmail(request.getParameter("mail"));
			}
			break;
		case "phone":
			Database.updateUser(user, "phone", request.getParameter("phone"), password);
			user=Database.findUserByEmail(user.getEmail());
			break;
		case "password":
			String new_password = request.getParameter("new_pwd");
			String confirm_new = request.getParameter("confirm_pwd");
			if (new_password.equals(confirm_new)) {
				Database.updateUser(user, "password", Crypt.crypt(new_password, "$6$xxxx"), password);
			}
			user=Database.findUserByEmail(user.getEmail());
			
			break;
		default:
			break;
		}
		
		session.setAttribute("user", user);
		request.getRequestDispatcher("/profil.jsp").forward(request, response);

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
