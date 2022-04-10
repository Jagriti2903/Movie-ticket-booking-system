
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class create_booking
 */
@WebServlet("/create_booking")
public class create_booking extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public create_booking() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
//		PrintWriter outp_print = response.getWriter();
//		   outp_print.println("<html>"
//		     		+ "<head>"
//		     		+ "<style>"
//		     		+ " #lg{float: right;}"
//		     		+ "</style>"
//		     		+ "<head>"
//		     		+ "<body>");
//		     
//		     outp_print.println("<a id='lg' href=\"logout\"><button> Logout </button> </a>");
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		int  flag = 2;
		
		try {
			
		
		Cookie array_cookie[] = request.getCookies();
		
		for(Cookie cookie_x : array_cookie)
		{
			if(cookie_x.getName().equals("username"))
			{
				flag = 1;
			}
		}
		}
		catch(Exception e)
		{
			request.getRequestDispatcher("login.html").include(request,response);
		    flag=3;	
		}
		if(flag==1)
		{
			request.getRequestDispatcher("create_booking").include(request,response);
		}
		
		else if(flag==2)
		{
			request.getRequestDispatcher("login.html").include(request,response);
		}
		// outp_print.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		PrintWriter outp_print = response.getWriter();
		
		outp_print.println("<style>"
				+ "h1{text-align: center; size:1rem;}"
				+ "#success{color:green;}"
				+ "</style>");
		int id = Integer.parseInt(request.getParameter("id"));
		Cookie array[] = request.getCookies();
		String username = "";
		int price=0,no_of_tickets=0;
		
		for(Cookie x: array )
		{		
			if(x.getName().equals("username"))
			{
				username = x.getValue();
			}
			
			else if(x.getName().equals("price"))
			{
				price = Integer.parseInt(x.getValue());
			}
			
			else if(x.getName().equals("no_of_tickets"))
			{
				no_of_tickets = Integer.parseInt(x.getValue());
			}
			
		}
		
		System.out.println(username + price + no_of_tickets);
		
		User obj_user = new User();
		int user_id = obj_user.get_id_using_username(username);
		System.out.println(user_id);
		
		Bookings obj_booking = new Bookings();
		
		obj_booking.set_showid(id);
		obj_booking.set_ticketsNo(no_of_tickets);
		obj_booking.set_userid(user_id);
		
		obj_booking.create_booking();
		
		outp_print.println("<h1 id='success'>Booking successful</h1>");
	    request.getRequestDispatcher("user.html").include(request,response);
		
		
	
	}

}
