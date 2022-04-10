import java.util.*;
import java.sql.*;
import java.util.Date;
import java.text.*;


public class Bookings {
	
	int id, user_id, show_id, no_of_tickets;
	boolean status;
	
	public int getid()
	{
		return this.id;
	}
	
	public void setid(int id)
	{
		this.id = id;
	}
	
	public int get_userid()
	{
		return this.user_id;
	}
	
	public void set_userid(int user_id)
	{
		this.user_id = user_id;
	}
	
	public int get_showid()
	{
		return this.show_id;
	}
	
	public void set_showid(int show_id)
	{
		this.show_id = show_id;
	}
	
	public int get_ticketsNo()
	{
		return this.no_of_tickets;
	}
	
	public void set_ticketsNo(int ticketsNum)
	{
		this.no_of_tickets = ticketsNum;
	}
	
	public boolean get_status()
	{
		return this.status;
	}
	
	public void set_status(boolean status)
	{
		this.status = status;
	}
	
	public int create_booking()
	{
		int ret_status = 0;
		try{
			
			Connection get_connection = ConnectDB.get_dbconnection();
			PreparedStatement stat1 = get_connection.prepareStatement("insert into bookings_table(user_id, show_id, no_of_tickets, booking_status) values(?,?,?,?)");

			stat1.setInt(1, this.user_id);
			stat1.setInt(2, this.show_id);
			stat1.setInt(3, this.no_of_tickets);
            stat1.setBoolean(4, true);
			
			ret_status=stat1.executeUpdate();
			
			get_connection.close();
			
			//get_connection.close();
			
			
		}
		
		catch(Exception exc)
		{
			System.out.println(exc);
			System.out.println("Error in create booking function");
			
		}
		
		return ret_status;
	}
	
	public int cancel_booking()
	{
		int ret_status = 0;
		ResultSet res= null;
	    int x =0;
		boolean status;
		try{
			
			Connection get_connection = ConnectDB.get_dbconnection();
			PreparedStatement stat1 = get_connection.prepareStatement("Select* from bookings_table where id=?");
			
			stat1.setInt(1, this.id);

			res = stat1.executeQuery();
			
			if(res.next())
			{
				status = res.getBoolean("booking_status");
				
				if(status==true)
				{
					ResultSet y = null;
					int show_id = res.getInt("show_id");
					PreparedStatement stat = get_connection.prepareStatement("Select show_date from shows_table where id=?");
					stat.setInt(1, show_id);
					
					y = stat.executeQuery();
					
					
					if(y.next())
					{
						String show_date = y.getString("show_date");
						//Date present_date = new Date();
						//String curr_date_string = present_date.toString();
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				        Date date = new Date();
				        String curr_date=formatter.format(date);
				        
				        Date showDate = formatter.parse(show_date);
				        Date currDate = formatter.parse(curr_date);
				        
				        
				        
				        if(showDate.compareTo(currDate)<=0)
				        {
				        	System.out.println("Not possible to cancel due to date");
				        	return 0;
				        	
				        }
				        	
				        
//				        String show_date_array[] = show_date.split("-");
//				        String curr_date_array[] = curr_date.split("-");
//				        
//				        int curr_year = Integer.parseInt(curr_date_array[0]);
//				        int curr_year = Integer.parseInt(curr_date_array[0]);
//				        int curr_year = Integer.parseInt(curr_date_array[0]);
//				        int curr_year = Integer.parseInt(curr_date_array[0]);
//				        int curr_year = Integer.parseInt(curr_date_array[0]);
//				        int curr_year = Integer.parseInt(curr_date_array[0]);
//				     
					}
					
					
					PreparedStatement stat2 = get_connection.prepareStatement("update bookings_table set booking_status=?,no_of_tickets=? where id=?");
					
					stat2.setBoolean(1, false);
					stat2.setInt(2, 0);
					stat2.setInt(3, this.id);
					
					x = stat2.executeUpdate();
					ret_status = 1;
				}
			}
			
			
			
		//	get_connection.close();
			
			//get_connection.close();
			
			
		}
		
		catch(Exception exc)
		{
			System.out.println(exc);
			System.out.println("Error in cancel booking function");
			
		}
		
		return ret_status;
	}
	
	

}


