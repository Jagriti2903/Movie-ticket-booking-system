import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;



import com.mysql.cj.protocol.a.authentication.CachingSha2PasswordPlugin.AuthStage;

public class Show {
	
	int id,movie_id;
   String theater_name,hall_no,date,time;
   int price,total_seats;
 
//	public Movie( String name, String language, String certification, String length,String releaseDate) {
//		super();
//		//this.id = id;
//	        this.language = language;
//	        this.certification = certification;
//	        this.name = name;
//	        this.length = length;
//	        this.releasedate = releaseDate;
//	}
	public int getid() {
		
		return this.id ;
	}
	
	public void setid() {
		
		this.id = id;
	}
	
    public int get_movieid() {
		
		return this.id ;
	}
	
	public void setid(int id) {
		
		this.id = id;
	}
	
	public int getName() {
		
		return this.movie_id;
	}
	
	public void set_movieid(int movie_id) {
		
		this.movie_id = movie_id;
	}
	
	public String get_theater_name() {
		
		return this.theater_name;
	}
	
	public void set_theater_name(String name_of_theater) {
		
		this.theater_name = name_of_theater;
	}
	
	public String get_hallNo() {
		
		return this.hall_no;
	}
	
	public void set_hallNo(String hall_no) {
		
		this.hall_no = hall_no;
	}
	
	public String get_date() {
		
		return this.date;
	}
	
	public void set_date(String show_date) {
		
		this.date = show_date;
	}
	
	public String get_time() {
		
		return this.time;
	}
	
	public void set_time(String show_time) {
		
		this.time = show_time;
		
	}
	
	public int get_price()
	{
		ResultSet res = null;
		int id = this.getid();
		int price =0;
		
		try{
			
			Connection get_connection = ConnectDB.get_dbconnection();
			PreparedStatement stat1 = get_connection.prepareStatement("SELECT* from shows_table where id=?");
			
			stat1.setInt(1, id);
			res = stat1.executeQuery();
			
			if(res.next())
			{
				price = res.getInt("show_price");
				this.set_price(price);
			}
							
		}
		
		catch(Exception exc)
		{
			System.out.println(exc);
			System.out.println("Error in check shows");
			
		}
		
		return price;
		
		
	}
	
	public void set_price(int show_price)
	{
		this.price = show_price;
	}
	
	public int get_totalseats()
	{
		return this.total_seats;
	}
	
	public void set_totalseats(int total_seats)
	{
		this.total_seats = total_seats;
	}
	
	public int add_show()
	{
		int ret_status = 0;
		int count = 0;
		ResultSet res= null;
		try{
			
			
			
			Connection get_connection = ConnectDB.get_dbconnection();
			PreparedStatement stat = get_connection.prepareStatement("select* from shows_table where theater_name=? and show_date=?");
			stat.setString(1, this.theater_name);
			stat.setString(2,this.date);
			
			res = stat.executeQuery();
			
			while(res.next())
			{
				count++;
			}
			
			if(count>=2)
			{
				return 0;
			}
				
			
			else {
			
	
			PreparedStatement stat1 = get_connection.prepareStatement("insert into shows_table(movie_id,theater_name,hall_no,show_date,show_time,show_price,total_seats) values(?,?,?,?,?,?,?)");

			stat1.setInt(1, this.movie_id);
			stat1.setString(2, this.theater_name);
			stat1.setString(3, this.hall_no);
            stat1.setString(4, this.date);
			stat1.setString(5, this.time);
			stat1.setInt(6, this.price);
			stat1.setInt(7, this.total_seats);
			//ps.setInt(6,0);
			ret_status=stat1.executeUpdate();
			
			get_connection.close();
			
			//get_connection.close();
			
			}
				
		}
		
		catch(Exception exc)
		{
			System.out.println(exc);
			System.out.println("Error in add show");
			
		}
		
		return ret_status;
	}
	
	public ResultSet check_shows(int movie_id)
	{
		ResultSet res = null;
		try{
			
			Connection get_connection = ConnectDB.get_dbconnection();
			PreparedStatement stat1 = get_connection.prepareStatement("select* from shows_table where movie_id=?");

			stat1.setInt(1, movie_id);
			
			//ps.setInt(6,0);
		    res = stat1.executeQuery();
		    
		    
			
//			get_connection.close();
			
			//get_connection.close();
				
		}
		
		catch(Exception exc)
		{
			System.out.println(exc);
			System.out.println("Error in check show of the given movie");
			
		}
		
		return res;
		
	}
	
	
	public ResultSet check_shows()
	{
		ResultSet res = null;
		try{
			
			Connection get_connection = ConnectDB.get_dbconnection();
			
			PreparedStatement stat1 = get_connection.prepareStatement("SELECT shows_table.id as showid,shows_table.theater_name, shows_table.show_date, shows_table.show_time, shows_table.show_price, movies_table.movie_name, movies_table.movie_language,movies_table.certification, movies_table.movie_length, movies_table.releaseDate  \r\n"
					+ "FROM shows_table   \r\n"
					+ "INNER JOIN movies_table  \r\n"
					+ "ON shows_table.movie_id = movies_table.id");
		    res = stat1.executeQuery();
							
		}
		
		catch(Exception exc)
		{
			System.out.println(exc);
			System.out.println("Error in check shows");
			
		}
		
		return res;
		
	}
	
	
	public int check_seat_availability()
	{
		
		ResultSet res1 = null;
		ResultSet res2 = null;
		int show_id = this.getid();
		int total_seats = 0;
		int available_seats = 0;
		int total_tickets_booked = 0;
		
		try{
			
			Connection get_connection = ConnectDB.get_dbconnection();
			PreparedStatement stat1 = get_connection.prepareStatement("select* from shows_table where id=?");

			stat1.setInt(1, show_id);
		    res1 = stat1.executeQuery();
		    
		    try {
		    	
		    	if(res1.next())
			    {
			    	total_seats = res1.getInt("total_seats");
			    }
		    }
		    
		    catch(Exception exc)
		    {
		    	System.out.println(exc);
		    	System.out.println("error in getting total no of seats for this show");
		    }
		    
		    
		    PreparedStatement stat2 = get_connection.prepareStatement("select* from bookings_table where show_id=?");
		    
		    stat2.setInt(1,show_id);
		    res2 = stat2.executeQuery();
		    
		    try {
		    	
		    	while(res2.next())
			    {
		    		//if(res2.getInt("no_of_tickets")!=null)
		    		total_tickets_booked += res2.getInt("no_of_tickets");
			    }
		    	
		    }
		    
		    catch(Exception exc)
		    {
		    	System.out.println(exc);
		    	System.out.println("error in getting total no of seats for this show from database");
		    }
		    
		    
		    
		    
		   		
		}
		
		catch(Exception exc)
		{
			System.out.println(exc);
			System.out.println("Error in check_availaility function");
			
		}
		
		available_seats = total_seats - total_tickets_booked;
		
		return available_seats;
		
	}
	
//	public Movie get_movie_of_a_show(int show_id)
//	{
//		Movie movie_obj = new Movie();
//		ResultSet res = null;
//		try{
//			
//			Connection get_connection = ConnectDB.get_dbconnection();
//			PreparedStatement stat1 = get_connection.prepareStatement("select* from movies_table where ");
//		    res = stat1.executeQuery();
//							
//		}
//		
//		catch(Exception exc)
//		{
//			System.out.println(exc);
//			System.out.println("Error in check show of the given movie");
//			
//		}
//		
//		return res;
//		
//	}
	
	

}
