
import java.sql.*;
import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class check_availablity
 */
@WebServlet("/check_availablity")
public class check_availablity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public check_availablity() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
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
		     		+ "#error{text-align: center; color:red;}"
		     		+ "body{background-color: rgb(180, 241, 241);}"
		     		+ "button{padding : 2px 2px 2px 2px ; margin : 2px 2px 2px 2px;  margin-bottom: 6px; cursor : pointer;  background-color: rgb(91, 243, 154);}"
		     		+ "</style>"
		     		+ "<head>"
		     		+ "<body>");
		     
		    
		     
		
		
		int id = Integer.parseInt(request.getParameter("id"));
	//	PrintWriter outp_print = response.getWriter();
	//    outp_print.println("<h1>"+id+"</h1>");
	    
	    Show obj_show = new Show();
	    obj_show.setid(id);
	    int seats_available = obj_show.check_seat_availability();
	    
	    if(seats_available>0)
	    {
	    	
	    	 outp_print.println("<a id='lg' href=\"logout\"><button> Logout </button> </a>");
	    	outp_print.println("<p>Available seats : "+seats_available+"</p>");
	    
	    	outp_print.println("<form method='post' action='booking?id="+id+"'>");
	    	outp_print.println("Enter No of tickets to be booked: <input type='text' name='no_of_tickets' placeholder='enter no of tickets' required>");
	    	outp_print.println("<button type = 'submit'>Proceed</button>");
	    	outp_print.println("</form>");
	    	
	    //	outp_print.println("<a href='create_booking?id="+id+"'><button type='button'>Book tickets</button></a>");
	    	
	    }
	    
	    else
	    {
	    	outp_print.println("<h1 id='error'>This show is full, select another show</h1>");
	    	request.getRequestDispatcher("CheckShows").include(request, response);	    	//request.getRequestDispatcher((String)request.getAttribute("javax.servlet.forward.request_uri")).include(request, response);
	    	
	  
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
