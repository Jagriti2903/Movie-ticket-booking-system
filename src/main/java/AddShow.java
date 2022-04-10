
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
@WebServlet("/AddShow")
public class AddShow extends HttpServlet {
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
			request.getRequestDispatcher("addshow.html").include(request,response);
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
          		+ "h1{text-align: center; color : rgb(17, 153, 17);size:1rem;}"
          		+ "#error{text-align:center; color:red}");
         
         outp_print.println("</style></head>");
         outp_print.println("<body>");
		
	    int movie_id = Integer.parseInt(request.getParameter("movie_id"));
		String theater_name = request.getParameter("theater_name");
		String hall_no = request.getParameter("hall_no");
		String date = request.getParameter("show_date")+"";
		String time = request.getParameter("time");
		int price = Integer.parseInt(request.getParameter("price"));
		int total_seats = Integer.parseInt(request.getParameter("total_seats"));
		
		Show obj_show = new Show();
		
		obj_show.set_movieid(movie_id);
		obj_show.set_theater_name(theater_name);
		obj_show.set_hallNo(hall_no);
		obj_show.set_date(date);
		obj_show.set_time(time);
		obj_show.set_price(price);
		obj_show.set_totalseats(total_seats);
		
		int addshow_status = obj_show.add_show();
		
		if(addshow_status > 0){
			
			outp_print.println("<h1>Show added successfully</h1>");
			
		}
		
		else
		{
			outp_print.println("<h1 id='error'>Show can't be added</h1>");
		}
		
		request.getRequestDispatcher("admin.html").include(request,response);
		
		outp_print.println("</body></html");
		
		outp_print.close();
		
				
	}

}
