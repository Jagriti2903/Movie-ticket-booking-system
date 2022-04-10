<%@ page  import = "java.io.*" import= "java.util.*"   language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Movie info</title>
</head>
<body>
     
     <%
     PrintWriter outp_print = response.getWriter();
		
		String MovieName = (String)request.getAttribute("moviename");
		System.out.println(MovieName);
	     Movie obj_movie = new Movie();
		
		obj_movie.setName(MovieName);
		
		Movie ret_movie_obj = new Movie();
		ret_movie_obj = obj_movie.search_movie();
		
		if(ret_movie_obj==null){
			
			outp_print.println("<h1>Movie not found</h1>");
			request.getRequestDispatcher("search_movie.html").include(request,response);
			
		}
		
		else
		{
			//request.getRequestDispatcher("MovieInfo").include(request, response);
			
			
		
		
		
		//outp_print.close();
    
      %>
      
      <center>
      <P><%=  ret_movie_obj.name %></P>
      <P><%=  ret_movie_obj.language %></P>
      <P><%=  ret_movie_obj.releasedate %></P>
      <P><%=  ret_movie_obj.length %></P>
       <P><%=  ret_movie_obj.certification %></P>
     
       <a href = '/view_shows?=<%=ret_movie_obj.getid()%>' ><button type = "button">View shows</button></a>
       </center>
       
       <%
       
		}
       
       %>
      

</body>
</html>