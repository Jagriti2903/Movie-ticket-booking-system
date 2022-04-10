
import javax.servlet.http.Cookie;
import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class booking
 */
@WebServlet("/booking")
public class booking extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public booking() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
	    
	    
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 PrintWriter outp_print = response.getWriter();
		 
		
		 
	     outp_print.println("<html>"
	     		+ "<head>"
	     		+ "<style>"
	     		+ "div{ margin:auto;border:2px solid rgb(92, 61, 167);border-radius:15px;background-color:rgb(194, 162, 245); width : 50%; text-align: center; margin-top:50px;margin-bottom:12px;  padding : 2px 2px 2px 2px;}"
	     		+ "#lg{float :right}"
	     		+ "p{text-align:center;}"
		 		+ "body{background-color: rgb(180, 241, 241);}"
		 		+ "#error{text-align:center; size:1rem; color:red}"
		 		+ "button{padding : 2px 2px 2px 2px ; margin : 2px 2px 2px 2px;  margin-bottom: 6px; cursor : pointer;  background-color: rgb(91, 243, 154);}"
	     		+ "</style>"
	     		+ "</head>"
	     		+ "<body>");
	     
	    
//		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
//		
//		int  flag1 = 2;
//		
//		try {
//			
//		
//		Cookie array_cookie[] = request.getCookies();
//		
//		for(Cookie cookie_x : array_cookie)
//		{
//			if(cookie_x.getName().equals("username"))
//			{
//				flag1 = 1;
//			}
//		}
//		}
//		catch(Exception e)
//		{
//			request.getRequestDispatcher("login.html").include(request,response);
//		    flag1=3;	
//		}
//		if(flag1==2)
//		{
//			request.getRequestDispatcher("login.html").include(request,response);
//			
//		}
//		
//		else if(flag1==1)
//		{
//			
//		
//		
//		// PrintWriter outp_print = response.getWriter();
//		 
//		     outp_print.println("<html>"
//		     		+ "<head>"
//		     		+ "<style>"
//		     		+ " #lg{float: right;}"
//		     		+ "</style>"
//		     		+ "<head>"
//		     		+ "<body>");
//		     
//		     outp_print.println("<a id='lg' href=\"logout\"><button> Logout </button> </a>");
//		     
		
		//PrintWriter outp_print = response.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));
		int no_of_tickets_to_book=0;
		try {
	          no_of_tickets_to_book = Integer.parseInt(request.getParameter("no_of_tickets"));
		
		
		
		
	  //  outp_print.println("<p>"+ no_of_tickets_to_book+"</p>");
	    
	    //outp_print.println("<p>"+id+"</p>");
	    
	    Show obj_show = new Show();
	    
	    obj_show.setid(id);
	    
	    int available_seats = obj_show.check_seat_availability();
	    
	    if(no_of_tickets_to_book>0 && no_of_tickets_to_book<=available_seats)
	    {
	    	// outp_print.println("<a id='lg' href=\"logout\"><button> Logout </button> </a>");
	    	int price = obj_show.get_price();
	    	Cookie obj_cookie = new Cookie("price",price*no_of_tickets_to_book + "");
	    	response.addCookie(obj_cookie);
	    	
	    	Cookie cookie_obj = new Cookie("no_of_tickets",no_of_tickets_to_book+"");
	    	response.addCookie(cookie_obj);
	    	
	    	
	    	
	    	//outp_print.println("<p>available </p>");
	    	outp_print.println("<div>");
	    	outp_print.println("<form method='post' action='create_booking?id="+id+"'>");
	    	outp_print.println("<p>");
	    	outp_print.println("Enter card No: <input type='text' name = 'card_no' placeholder='enter card no' required>");
	    	outp_print.println("</p>");
	    	outp_print.println("<p>");
	    	outp_print.println("Enter CVV: <input type='text' name = 'cvv' placeholder='enter cvv' required>");
	    	outp_print.println("</p>");
	    	outp_print.println("<p>");
	    	outp_print.println("Enter card expiry date: <input type='text' name = 'expiry_date' placeholder='enter expiry date' required>");
	    	outp_print.println("</p>");
	    	outp_print.println("<p>");
	    	outp_print.println("<button type = 'submit'>Proceed</button>");
	    	outp_print.println("</p>");
	    	outp_print.println("</form>");
	    	outp_print.println("</div>");
	    }
	    
	    else
	    {
	    	outp_print.println("<h1 id='error'>Please re enter no of tickets to be booked!</h1>");
	    	request.getRequestDispatcher("check_availablity?id="+id+"").include(request,response);
	    }
	    
		}
		
		catch(Exception e)
		{
			outp_print.println("<h1 id='error'>Please enter a valid number!</h1>");
	    	request.getRequestDispatcher("check_availablity?id="+id+"").include(request,response);
		}
	    
	    outp_print.println("</body></html>");
	    
	    
	}
		//outp_print.println("</body></html>");
	//}	

}
