import java.sql.*;


public class ConnectDB {
	
	public static Connection get_dbconnection()
	{
		Connection get_con = null;
		
		String url_string = "jdbc:mysql://localhost:3306/movie_ticket_booking_system?useSSL=false";
		String username = "root";
		String password = "Pasta$1348";
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			get_con = DriverManager.getConnection(url_string, username, password);
			
		}
		
		catch(Exception exc)
		{
			System.out.println(exc);
			System.out.println("Unable to connect to database");
		}
		
		
		return get_con;
	}

}
