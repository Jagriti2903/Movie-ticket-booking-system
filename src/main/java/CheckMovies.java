
import java.io.*;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckMovies
 */
@WebServlet("/CheckMovies")
public class CheckMovies extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckMovies() {
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
		     		+ "div{ margin:auto; border:2px solid rgb(92, 61, 167);border-radius:15px;background-color:rgb(194, 162, 245); width : 50%; text-align: center; margin-top:50px;margin-bottom:12px;  padding : 2px 2px 2px 2px;}"
		     		+ "#lg{float :right}"
			 		+ "body{background-color: rgb(180, 241, 241);}"
			 		+ "#error{text-align:center;size:1rem;color:red}"
			 		+ "button{padding : 2px 2px 2px 2px ; margin : 2px 2px 2px 2px;  margin-bottom: 6px; cursor : pointer;  background-color: rgb(91, 243, 154);}"
		     		+ "</style>"
		     		+ "</head>"
		     		+ "<body>");
		     
		     outp_print.println("<a id='lg' href=\"logout\"><button> Logout </button> </a>");
		     
		
		    Movie obj_movie = new Movie();
		    ResultSet res = obj_movie.list_movies();
		    
		    int flag = 0;
		   
					try {
						
						
						while(res.next())
						{
							flag = 1;
							
							             
						     outp_print.println("<div>");
						     
						     outp_print.println("<p>Movie: "+res.getString("movie_name")+"</p>");
						     outp_print.println("<p>Language: "+res.getString("movie_language")+"</p>");
						     outp_print.println("<p>Certificaion : "+res.getString("certification")+"</p>");
						   //  outp_print.println("<p>"+res.getString("theater_name")+"</p>");
						     outp_print.println("<p>Duration: "+res.getString("movie_length")+"</p>");
						     outp_print.println("<p>Release Date  : "+res.getString("releaseDate")+"</p>");
						     
						     outp_print.println("<a href='view_shows?id="+res.getInt("id")+"'><button type='button'>View Shows</button></a>");
						    
						     outp_print.println("</div>");
						     outp_print.println("</body></html>");
						     
						    
						    
						}
						
						
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						System.out.println(e);
						System.out.println("cant' get movies from movies_table");
						e.printStackTrace();
					}
					
					if(flag==0)
					{
						outp_print.println("<h1 id='error'>No movies avialable</h1>");
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
