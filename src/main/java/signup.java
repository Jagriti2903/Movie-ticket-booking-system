

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.io.*;
	

/**
 * Servlet implementation class signup
 */
@WebServlet("/signup")
public class signup extends HttpServlet {
//	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
	//	request.getRequestDispatcher("signup.html").include(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter outp_print = response.getWriter();
		
		outp_print.println("<style>"
				+ "h1{text-align: center; size:1rem;}"
				+ "#error{color:red;}"
				+ "</style>");
		
		String name = request.getParameter("name");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirm_password = request.getParameter("confirm_password");
		
		if(!password.equals(confirm_password))
		{
			outp_print.println("<h1 id='error'>Password and confirm password should be same! </h1>");
			request.getRequestDispatcher("signup.html").include(request, response);
			
		}
		
		else
		{
			User obj_user = new User();
			obj_user.set_name(name);
			obj_user.set_username(username);
			obj_user.set_password(confirm_password);
			
			obj_user.signup();
			request.getRequestDispatcher("login.html").include(request, response);
		}
		
		
	}

}
