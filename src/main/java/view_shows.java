
import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class view_shows
 */
@WebServlet("/view_shows")
public class view_shows extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public view_shows() {
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
		     		+ "div{ margin:auto;border:2px solid rgb(92, 61, 167);border-radius:15px;background-color:rgb(194, 162, 245); width : 50%; text-align: center; margin-top:50px;margin-bottom:12px;  padding : 2px 2px 2px 2px;}"
		     		+ "#lg{float :right}"
		     		+ "#error{text-align:center; color:red; size:1rem;}"
			 		+ "body{background-color: rgb(180, 241, 241);}"
			 		+ "button{padding : 2px 2px 2px 2px ; margin : 2px 2px 2px 2px;  margin-bottom: 6px; cursor : pointer;  background-color: rgb(91, 243, 154);}"
		     
		     		+ "</style>"
		     		+ "<head>"
		     		+ "<body>");
		     
		    
		
		int id = Integer.parseInt(request.getParameter("id"));
	//	PrintWriter outp_print = response.getWriter();
	//    outp_print.println("<h1>"+id+"</h1>");
	    
	    Show obj_show = new Show();
	    ResultSet res = obj_show.check_shows(id);
	    
	    int flag = 0;
	   
				try {
					   boolean temp = false;
					
					while(res.next())
					{
						
						String show_date = res.getString("show_date");
						//Date present_date = new Date();
						//String curr_date_string = present_date.toString();
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				        Date date = new Date();
				        String curr_date=formatter.format(date);
				        
				        try {
				        Date showDate = formatter.parse(show_date);
				        Date currDate = formatter.parse(curr_date);
				        
				        
				        
				        
				        
				        if(showDate.compareTo(currDate)<0)
				        {
				        	continue;
//				        	System.out.println("Not possible to cancel due to date");
//				        	return 0;
				        	
				        }
				        
				        }
				        
				        catch(Exception e)
				        {
				        	System.out.println("error with the date");
				        }
				        
				        if(temp==false)
						{
							 outp_print.println("<a id='lg' href=\"logout\"><button> Logout </button> </a>");
							 temp = true;
						}
						
						flag = 1;
					     outp_print.println("<div>");
					  //   outp_print.println("<center>");
					     outp_print.println("<p>Theater: "+res.getString("theater_name")+"</p>");
					   //  outp_print.println("<p>"+res.getString("theater_name")+"</p>");
					     outp_print.println("<p>Date of Show: "+res.getString("show_date")+"</p>");
					     outp_print.println("<p>Start time: "+res.getString("show_time")+"</p>");
					     
					     outp_print.println("<a href='check_availablity?id="+res.getInt("id")+"'><button type='button'>check availablity</button></a>");
					    // outp_print.println("</center>");
					     outp_print.println("</div>");
					     
					    
					    
					}
					
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e);
					System.out.println("cant' get shows form shows_table");
					e.printStackTrace();
				}
				
				if(flag==0)
				{
					outp_print.println("<h1 id='error'>No shows avialable for this movie. Try searching another movie</h1>");
					request.getRequestDispatcher("search_movie.html").include(request,response);
					
					
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
