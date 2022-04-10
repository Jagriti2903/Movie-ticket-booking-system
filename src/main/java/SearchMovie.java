
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddMovie
 */
@WebServlet("/SearchMovie")
public class SearchMovie extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
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
			request.getRequestDispatcher("search_movie.html").include(request,response);
		}
		
		else if(flag==2)
		{
			request.getRequestDispatcher("login.html").include(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
//		String MovieName = request.getParameter("MovieName");
//		request.setAttribute("moviename", MovieName);
//		request.getRequestDispatcher("MovieInfo.jsp").include(request, response);
		
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
		
		String MovieName = request.getParameter("MovieName");
		
	    Movie obj_movie = new Movie();
		
		obj_movie.setName(MovieName);
		
		Movie ret_movie_obj = new Movie();
		ret_movie_obj = obj_movie.search_movie();
		
		if(ret_movie_obj==null){
			
			outp_print.println("<h1 id='error'>Movie not found!</h1>");
			request.getRequestDispatcher("search_movie.html").include(request,response);
			
		}
		
		else
		{
			// outp_print.println("<center>");
			outp_print.println("<div>");
			outp_print.println("<p>Movie: "+ret_movie_obj.name+"</p>");
			outp_print.println("<p>Language: "+ret_movie_obj.getLanguage()+"</p>");
			outp_print.println("<p>Duration: "+ret_movie_obj.getLength()+"</p>"); 
			outp_print.println("<p>Release date: "+ret_movie_obj.getReleaseDate()+"</p>");
			outp_print.println("<p>Certification: "+ret_movie_obj.getCertification()+"</p>");
			outp_print.println(" <a href = 'view_shows?id="+ret_movie_obj.getid()+" ' ><button type = 'button'>View shows</button></a>");
			
			outp_print.println("</div>");
			 //outp_print.println("</center>");
			
//			request.getRequestDispatcher("MovieInfo").include(request, response);
			
		}
		
		
		outp_print.println("</body></html>");
		
		outp_print.close();
				
	}

}
