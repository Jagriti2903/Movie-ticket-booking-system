
import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewBookings
 */
@WebServlet("/ViewBookings")
public class ViewBookings extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewBookings() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		int  flag1 = 2;
		
		try {
			
		
		Cookie array_cookie[] = request.getCookies();
		
		for(Cookie cookie_x : array_cookie)
		{
			if(cookie_x.getName().equals("username"))
			{
				flag1 = 1;
			}
		}
		}
		catch(Exception e)
		{
			request.getRequestDispatcher("login.html").include(request,response);
		    flag1=3;	
		}
		if(flag1==2)
		{
			request.getRequestDispatcher("login.html").include(request,response);
			
		}
		
		else if(flag1==1)
		{
			
		
		
		 PrintWriter outp_print = response.getWriter();
		 
		     outp_print.println("<html>"
		     		+ "<head>"
		     		+ "<style>"
		     		+ " #lg{float: right;}"
		     		+ "div{ margin:auto;border:2px solid rgb(92, 61, 167);border-radius:15px;background-color:rgb(194, 162, 245); width : 50%; text-align: center; margin-top:50px;margin-bottom:12px;  padding : 2px 2px 2px 2px;}"
		     		+ "#error{text-align:center;size:1rem; color:red}"
			 		+ "body{background-color: rgb(180, 241, 241);}"
			 		+ "button{padding : 2px 2px 2px 2px ; margin : 2px 2px 2px 2px;  margin-bottom: 6px; cursor : pointer;  background-color: rgb(91, 243, 154);}"
		     
		     		+ "</style>"
		     		+ "<head>"
		     		+ "<body>");
		     
		     outp_print.println("<a id='lg' href=\"logout\"><button> Logout </button> </a>");
		     
		
		  //  PrintWriter outp_print = response.getWriter();
		    
		    Cookie array[] = request.getCookies();
			String username = "";
			
			for(Cookie x: array )
			{		
				if(x.getName().equals("username"))
				{
					username = x.getValue();
				}
				
			}
			
		    User obj_user = new User();
		    obj_user.set_username(username);
		    ResultSet res = obj_user.view_bookings();
		    
		    int flag = 0;
		   
					try {
						
						
						while(res.next())
						{
							String movie_name = "";
							ResultSet res2 = null;
							int movie_id = res.getInt("movie_id");
							Connection get_connection = ConnectDB.get_dbconnection();
							PreparedStatement stat1 = get_connection.prepareStatement("select movie_name from movies_table where id=?");
							stat1.setInt(1,movie_id);
							res2 = stat1.executeQuery();
							
							if(res2.next())
							{
								movie_name = res2.getString("movie_name");
							}
							flag = 1;
						     outp_print.println("<div>");
						     boolean curr_status =res.getBoolean("booking_status");
						     
						
						     outp_print.println("<p>Movie: "+movie_name+"</p>");
						     outp_print.println("<p>Theater: "+res.getString("theater_name")+"</p>");
						     outp_print.println("<p>Date: "+res.getString("show_date")+"</p>");
						     outp_print.println("<p>Time : "+res.getString("show_time")+"</p>");
						   //  outp_print.println("<p>"+res.getString("theater_name")+"</p>");
						     outp_print.println("<p>price per ticket: "+res.getInt("show_price")+"</p>");
						     outp_print.println("<p>No of tickets  : "+res.getInt("no_of_tickets")+"</p>");
						     
						     if(curr_status==false)
						     {
						    	 outp_print.println("<p>Booking status : Cancelled</p>");
						     }
						     
						     else
						     {
						    	 outp_print.println("<p>Booking status : Active</p>");
						     }
						    	
						     
						     
						     outp_print.println("<a href='CancelBooking?id="+res.getInt("bookingid")+"'><button type='button'>Cancel Booking</button></a>");
						    
						     outp_print.println("</div>");
						     
						    
						    
						}
						
						
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						System.out.println(e);
						System.out.println("cant' get bookings");
						e.printStackTrace();
					}
					
					if(flag==0)
					{
						outp_print.println("<h1 id='error'>No bookings found</h1>");
						request.getRequestDispatcher("user.html").include(request,response);
						
						
					}
					
					outp_print.println("</body></html>");
					
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
