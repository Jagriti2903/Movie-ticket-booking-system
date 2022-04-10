import java.sql.*;

public class Person {
	
	String id;
	String name;
	String username;
	String password;
	
	public String validate(String pass, String uname)
	{
		ResultSet res_set1 = null;
		ResultSet res_set2 = null;
		String s1 = "admin";
		String s2 = "user";
		String s3 = "none";
		
		try {
			
			Connection get_con = ConnectDB.get_dbconnection();
			
			PreparedStatement stat1 = get_con.prepareStatement("select* from admin where username=? and admin_password=?");
			PreparedStatement stat2 = get_con.prepareStatement("select* from user where username=? and user_password=?");
			
			stat1.setString(1, uname);
			stat1.setString(2,pass);
			
			stat2.setString(1, uname);
			stat2.setString(2, pass);
			
			res_set1 = stat1.executeQuery();
			res_set2 = stat2.executeQuery();
			
			if(res_set1.next())
				return s1;
			
			else if(res_set2.next())
				return s2;
			
			else
				return s3;
				
		}
		
		catch(Exception exc)
		{
			System.out.println(exc);
			System.out.println("Error from validate function");
		}
		
		return s3;
	}
	
	public String login()
	{
		String username = this.username;
		String password = this.password;
		
		String str = this.validate(password, username);
		
		return str;
	}
	
	public void set_id(String person_id)
	{
		this.id = person_id;
	}
	
	public void set_name(String person_name)
	{
		this.name = person_name;
	
	}
	
	public void set_password(String person_password)
	{
		this.password = person_password;
	}
	
	public void set_username(String person_username)
	{
		this.username = person_username;
	}
	
	public String get_username()
	{
		return this.username;
	}
	
	public String get_id()
	{
		return this.id;
	}
	
	
	public String get_name()
	{
		return this.name;
	}
	
	public String get_password()
	{
		return this.password;
	}

}

