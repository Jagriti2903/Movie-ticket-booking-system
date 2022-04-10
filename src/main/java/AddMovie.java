
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
@WebServlet("/AddMovie")
public class AddMovie extends HttpServlet {
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
			request.getRequestDispatcher("addmovie.html").include(request,response);
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
		
         PrintWriter outp_print = response.getWriter();
         
         outp_print.println("<html><head><style>"
         		+ "h1{text-align: center; color : rgb(17, 153, 17);size:1rem;}");
         
         outp_print.println("</style></head>");
         outp_print.println("<body>");
		
		String MovieName = request.getParameter("MovieName");
		String Language = request.getParameter("Language");
		String Certification = request.getParameter("Certification");
		String Length = request.getParameter("Length");
		String ReleaseDate = request.getParameter("ReleaseDate")+"";
		
		Movie obj_movie = new Movie();
		
		obj_movie.setName(MovieName);
		obj_movie.setLanguage(Language);
		obj_movie.setCertification(Certification);
		obj_movie.setLength(Length);
		obj_movie.setReleaseDate(ReleaseDate);
		
		int addmovie_status = obj_movie.add_movie();
		
		if(addmovie_status > 0){
			
			outp_print.println("<h1>Movie added successfully</h1>");
			
		}
		
		request.getRequestDispatcher("admin.html").include(request,response);
		
		outp_print.println("</body></html");
		
		outp_print.close();
		
		
		
				
	}

}
