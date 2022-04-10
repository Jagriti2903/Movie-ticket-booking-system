

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class login extends HttpServlet {


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter outp=response.getWriter();
		
		outp.println("<style>"
				+ "h1{text-align: center; size:1rem;}"
				+ "#error{color:red;}"
				+ "</style>");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Person obj_person = new Person();
		
		obj_person.set_username(username);
		obj_person.set_password(password);
		
		
		String str = obj_person.login();
		
		if(str.equals("admin")) {
			
			Cookie cookie_obj = new Cookie("username",username);
			response.addCookie(cookie_obj);
			request.getRequestDispatcher("admin.html").include(request, response);
		}
		else if(str.equals("user")) {
			
			Cookie cookie_obj = new Cookie("username",username);
			response.addCookie(cookie_obj);
			request.getRequestDispatcher("user.html").include(request, response);
		}
		else {
			outp.println("<h1 id='error'>Please enter correct username and password</h1>");
			request.getRequestDispatcher("login.html").include(request,response);
		}
		
		
		
	}

}
