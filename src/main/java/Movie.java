import java.sql.*;

public class Movie {
	
   String name,language,certification,length,releasedate;
   int id;
   
   public Movie()
   {
	   super();
   }
	
  
	public Movie(int id,String name, String language, String certification, String length,String releaseDate) {
		
	     super();
		//this.id = id;
		    this.id = id;
	        this.language = language;
	        this.certification = certification;
	        this.name = name;
	        this.length = length;
	        this.releasedate = releaseDate;
	        
	}
	
	public int getid() {
		
		return this.id ;
	}
	
	public void setid(int id) {
		
		this.id = id;
	}
	
	public String getName() {
		
		return this.name;
	}
	
	public void setName(String name) {
		
		this.name = name;
	}
	
	public String getLanguage() {
		
		return this.language;
	}
	
	public void setLanguage(String language) {
		
		this.language = language;
	}
	
	public String getCertification() {
		
		return this.certification;
	}
	
	public void setCertification(String certification) {
		
		this.certification = certification;
	}
	
	public String getLength() {
		
		return this.length;
	}
	
	public void setLength(String length) {
		
		this.length = length;
	}
	
	public String getReleaseDate() {
		
		return this.releasedate;
	}
	
	public void setReleaseDate(String releasedate) {
		
		this.releasedate = releasedate;
		
	}
	
	public int add_movie()
	{
		int ret_status = 0;
		try{
			
			Connection get_connection = ConnectDB.get_dbconnection();
			PreparedStatement stat1 = get_connection.prepareStatement("insert into movies_table(movie_name,movie_language,certification,movie_length,releaseDate) values(?,?,?,?,?)");

			stat1.setString(1, this.name);
			stat1.setString(2, this.language);
			stat1.setString(3, this.certification);
            stat1.setString(4, this.length);
			stat1.setString(5, this.releasedate);
			//ps.setInt(6,0);
			ret_status=stat1.executeUpdate();
			
			get_connection.close();
			
			//get_connection.close();
			
			
		}
		
		catch(Exception exc)
		{
			System.out.println(exc);
			System.out.println("Error in add movie");
			
		}
		
		return ret_status;
	}
	
	public Movie search_movie()
	{
		Movie ret_movie_obj = null;
		ResultSet res = null;
		try{
			
			String str = this.getName();
			Connection get_connection = ConnectDB.get_dbconnection();
			PreparedStatement stat1 = get_connection.prepareStatement("select* from movies_table where movie_name=?");

			stat1.setString(1, str);
			res = stat1.executeQuery();
			
			if(res.next())
			{
				int movie_id = res.getInt("id");
				String movie_name = res.getString("movie_name");
				String language = res.getString("movie_language");
				String certification = res.getString("certification");
				String length = res.getString("movie_length");
				String releaseDate = res.getString("releaseDate");
				
				ret_movie_obj = new Movie(movie_id,movie_name, language, certification, length,releaseDate);
				
				
				
			}
			
			get_connection.close();
			
			//get_connection.close();
			
		}
		
		catch(Exception exc)
		{
			System.out.println(exc);
			System.out.println("Error in add movie");
			
		}
		
		return ret_movie_obj;
		
	}
	
	public ResultSet list_movies()
	{
		ResultSet res = null;
		try{
			
			Connection get_connection = ConnectDB.get_dbconnection();
			PreparedStatement stat1 = get_connection.prepareStatement("select* from movies_table");
			
		    res = stat1.executeQuery();
			
//			get_connection.close();
			
			//get_connection.close();
				
		}
		
		catch(Exception exc)
		{
			System.out.println(exc);
			System.out.println("Error in list movies");
			
		}
		
		return res;
		
	}
	

}
