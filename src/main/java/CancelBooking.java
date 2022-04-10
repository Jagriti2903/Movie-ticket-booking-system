
import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CancelBooking
 */
@WebServlet("/CancelBooking")
public class CancelBooking extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelBooking() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter outp_print = response.getWriter();
		
		//PrintWriter outp_print = response.getWriter();
		 
	     outp_print.println("<html>"
	     		+ "<head>"
	     		+ "<style>"
	     	//	+ "div{ border:2px solid rgb(92, 61, 167);border-radius:15px;background-color:rgb(194, 162, 245); width : 50%; text-align: center; margin : 8px 8px 8px 8px;margin-bottom:12px;  padding : 2px 2px 2px 2px;}"
	     	//	+ lg{float :right}"
		 		+ "body{background-color: rgb(180, 241, 241);}"
		 		+ "#error{text-align:center ; size:1rem;color:red;}"
		 		+ "#success{text-align:center;size:1rem; color : green;}"
		 		//+ "button{padding : 2px 2px 2px 2px ; margin : 2px 2px 2px 2px;  margin-bottom: 6px; cursor : pointer;  background-color: rgb(91, 243, 154);}"
	     		+ "</style>"
	     		+ "</head>"
	     		+ "<body>");
	     
	    // outp_print.println("<a id='lg' href=\"logout\"><button> Logout </button> </a>");
		int booking_id = Integer.parseInt(request.getParameter("id"));
		
		Bookings obj_booking = new Bookings();
		obj_booking.setid(booking_id);
		
		int cancel_status = obj_booking.cancel_booking();
		
		if(cancel_status==0)
		{
			outp_print.println("<h1 id='error'>Can't be cancelled</h1>");
		}
		
		else
		{
			outp_print.println("<h1 id='success'>Booking cancelled successfully</h1>");
		}
		
		request.getRequestDispatcher("ViewBookings").include(request,response);
		
		outp_print.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
