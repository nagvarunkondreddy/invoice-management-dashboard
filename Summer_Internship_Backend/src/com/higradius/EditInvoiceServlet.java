package com.higradius;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/editinvoice")
public class EditInvoiceServlet extends HttpServlet{
	//credentials 
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/highradius?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "";
    
    Connection connection = null;
    
    private static final long serialVersionUID = 1L;

	public EditInvoiceServlet() {
		super();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

		
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		
	     try {
	    	 
	    	Class.forName("com.mysql.cj.jdbc.Driver");
		    connection = DriverManager.getConnection(DB_URL,USER,PASS);
		    
		    Double amount=Double.parseDouble(req.getParameter("amount"));
		    String notes=req.getParameter("notes");
		    String invoice_id=req.getParameter("invoice_id");
		    
		    // sql query to be executed
		    
	         String sqlQuery;
	         System.out.println(invoice_id);
	         System.out.println(amount);
	         System.out.println("UPDATE invoice_details_2  SET total_open_amount="+ amount +" , notes ="+ notes + " WHERE invoice_id ="+invoice_id );
	         sqlQuery="UPDATE invoice_details_2  SET total_open_amount="+ amount +" , notes =(?)" + " WHERE invoice_id ="+invoice_id ;
	    	 PreparedStatement statement=connection.prepareStatement(sqlQuery);
//	    	 statement.setDouble(1,amount);
	    	 statement.setString(1,notes);
//	    	 statement.setString(3,invoice_id);
	    	 statement.executeUpdate();
	         resp.setStatus(200);

	     }     
       catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}