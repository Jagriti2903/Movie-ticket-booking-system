import java.sql.*;


public class User extends Person {
	
	String name;
	
	public String get_name()
	{
		return this.name;
	}
	
	public void set_name(String name_of_user)
	{
		this.name = name_of_user;
	}
	
	public int get_id_using_username(String username)
	{
		ResultSet res = null;
		int id=0;
				
		try {
			
			Connection get_connection = ConnectDB.get_dbconnection();
			PreparedStatement stat1 = get_connection.prepareStatement("Select* from user where username=?");
			
			stat1.setString(1, username);
			res = stat1.executeQuery();
			
			if(res.next())
			{
				return res.getInt("id");
			}
		}
		
		catch(Exception exc)
		{
			System.out.println(exc);
			System.out.println("error in get_id_using_username function");
		}
		
		return id;
		
	}
	
	public void signup()
	{
		
		try {
			
			Connection get_connection = ConnectDB.get_dbconnection();
			
			PreparedStatement stat1 = get_connection.prepareStatement("insert into user(name_of_user,username,user_password) values (?,?,?)");
			
			stat1.setString(1, this.name);
			stat1.setString(2, this.username);
			stat1.setString(3,this.password);
			
			int result = stat1.executeUpdate();
			get_connection.close();
					
		}
		
		catch(Exception exc)
		{
			System.out.println("error in signup");
			System.out.println(exc);
			
		}
		
		
	}
	
	public ResultSet view_bookings()
	{
		ResultSet res1 = null;
		ResultSet res2 = null;
		int user_id = 0;
		
		try{
			
			Connection get_connection = ConnectDB.get_dbconnection();
			PreparedStatement stat1 = get_connection.prepareStatement("select id from user where username=?");

			stat1.setString(1, this.username);
			res1=stat1.executeQuery();
			
			if(res1.next())
			{
				user_id = res1.getInt("id");
			}
			
			PreparedStatement stat2 = get_connection.prepareStatement("SELECT bookings_table.id as bookingid,bookings_table.user_id,bookings_table.show_id ,bookings_table.no_of_tickets, bookings_table.booking_status, shows_table.movie_id, shows_table.theater_name,shows_table.show_date, shows_table.show_time, shows_table.show_price  \r\n"
					+ "FROM bookings_table   \r\n"
					+ "INNER JOIN shows_table  \r\n"
					+ "ON bookings_table.show_id = shows_table.id where bookings_table.user_id=?");
			
			stat2.setInt(1, user_id);
			res2 = stat2.executeQuery();
			//get_connection.close();
			
			//get_connection.close();
			
			
		}
		
		catch(Exception exc)
		{
			System.out.println(exc);
			System.out.println("Error in view booking function");
			
		}
		
		return res2;
	}

}
