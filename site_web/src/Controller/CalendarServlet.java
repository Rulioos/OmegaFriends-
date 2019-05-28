package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Calendar;
import model.VkEvent;

/**
 * Servlet implementation class CalendarServlet
 */
@WebServlet("/CalendarServlet")
public class CalendarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalendarServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Servlet activated");
		String date = (String) request.getParameter("selected");
        String[] date_format = date.split("/");
        
        switch (date_format[1]){
        case "Janvier":
        	date_format[1] = "01";
            break;

        case "Fevrier":
        	date_format[1] = "02";
            break;

        case "Mars":
        	date_format[1] = "03";
            break;

        case "Avril":
        	date_format[1] = "04";
            break;

        case "Mai":
        	date_format[1] = "05";
            break;

        case "Juin":
        	date_format[1] = "06";
            break;


        case "Juillet":
        	date_format[1] = "07";
            break;

        case "Aout":
        	date_format[1] = "08";
            break;

        case "Septembre":
        	date_format[1] = "09";
            break;

        case "Octobre":
        	date_format[1] = "10";
            break;

        case "Novembre":
        	date_format[1] = "11";
            break;

        case "Decembre":
        	date_format[1] = "12";
            break;
           
        }
        
        date = date_format[0]+"/"+date_format[1]+"/"+date_format[2];
		System.out.println(date);
		
		Calendar cal = new Calendar();
		 ArrayList<VkEvent> events =  cal.getEventOfTheDay(date);
		request.setAttribute("calendar", events);
		
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/Res_events.jsp");
		dispatcher.include(request, response);
	}

}