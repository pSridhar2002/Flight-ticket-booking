package crud;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import pojo.Students;

/**
 * Servlet implementation class Fetch
 */
@WebServlet("/AdvanceSearch")
public class AdvanceSearch extends HttpServlet  {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdvanceSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		ArrayList<Students> ALLStudents =new ArrayList<Students>();
		try {
			String source = request.getParameter("source");
			String dest = request.getParameter("dest");
			
			
			
			Crud con=new Crud();
			 Connection conn = con.getConnection();
			 String sql_query="SELECT * from flight where leave_from='"+source+"' and going_to='"+dest+"' ";
			 PreparedStatement pst = conn.prepareStatement(sql_query);
			 //System.out.println(pst);
			 
			 ResultSet rs = pst.executeQuery();
			
			 while(rs.next())
			 {
					Students inv = new Students();
				 	
					
					//System.out.println("ID is "+id+" "+"Name is "+Name);
					
					inv.setSl_no(rs.getInt("sl_no"));
					inv.setDate(rs.getString("DATE"));
					inv.setDest(rs.getString("leave_from"));
					inv.setSource(rs.getString("going_to"));
					inv.setS(rs.getString("state_sold "));
					
					ALLStudents.add(inv);
					
					
			 }
			 
			 for(Students stud: ALLStudents)
			 {
				 System.out.println(stud.toString());
			 }
			 
			
		Gson gson = new Gson();
		String invoices  = gson.toJson(ALLStudents);
		response.setHeader("Access-Control-Allow-Origin","*");
		response.setContentType("application/json");
		try {
			response.getWriter().write(invoices);//getWriter() returns a PrintWriter object that can send character text to the client. 
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		rs.close();
		
		conn.close();
		
	}
	catch(SQLException e) {
		e.printStackTrace();
	}


			
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
