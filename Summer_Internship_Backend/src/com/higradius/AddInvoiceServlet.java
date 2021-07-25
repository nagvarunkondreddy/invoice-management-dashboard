package com.higradius;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/addinvoice")
public class AddInvoiceServlet extends HttpServlet{
	
	//credentials 
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/highradius?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "";
    Connection connection = null;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AddInvoiceServlet() {
		super();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		try {
			
		        Class.forName("com.mysql.cj.jdbc.Driver");
			    connection = DriverManager.getConnection(DB_URL,USER,PASS);
			    
			   
			    //getting params to be added in database
		        String name_cust = req.getParameter("name_cust");
		        String cust_no = req.getParameter("cust_no");
		        Double amount = Double.parseDouble(req.getParameter("amount"));
		        String invoice_id = req.getParameter("invoice_id");
		        String due_date = req.getParameter("due_date");
		        String notes = req.getParameter("notes");
		        

		        //parsing dates in required format
		        
		        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		        java.util.Date date = sdf1.parse(due_date);
		        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		        
		        
		        String x ="";
		        
		        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		        java.util.Date date2 = sdf2.parse("01-01-1999");
		        Timestamp clearDate = new java.sql.Timestamp(date2.getTime());
		        
		        
//		        java.util.Date date3 = sdf2.parse("01-01-1999");
//		        Date clearDate3 = new java.sql.Date(date3.getDate());
		        String clearDate3 = "1999-01-01 00:00:00.000000000";
		        
		        //sql statement to be executed
		        
		        PreparedStatement statement = connection.prepareStatement("INSERT INTO invoice_details_2 (cust_number,name_customer,clear_date,due_in_date,total_open_amount,invoice_id,notes)  VALUES (?,?,?,?,?,?,?)");
		        
		        statement.setString(1,cust_no);
		        statement.setString(2,name_cust);
		        statement.setString(3, clearDate3);
		        statement.setDate(4,sqlDate);
		        statement.setDouble(5,amount);
		        statement.setString(6,invoice_id);
		        statement.setString(7,notes);
		       
		        
		       
//		        statement.setDate(8,(java.sql.Date) clearDate3);
//		        statement.setString(9,x);
//		        statement.setTimestamp(5,clearDate);
//		        statement.setString(11,x);
		       
//		        statement.setString(13,x);
//		        statement.setString(14,x);
//		        statement.setInt(15,invoice_id);
//		        statement.setString(16,x);
//		        statement.setInt(17,0);
//		        statement.setInt(18,0);
//		        statement.setString(19,notes);
//		        statement.setString(20,x);
//		        statement.setString(21,x);
//		        statement.setInt(22,0);
//		        statement.setDouble(23,0.0);
//		        statement.setInt(24,0);
//		        statement.setInt(25,0);
//		        statement.setInt(26,0);
//		        statement.setInt(27,0);
//		        statement.setInt(28,0);
//		        statement.setInt(29,0);
//		        statement.setInt(30,0);
//		        statement.setInt(31,0);
//		        statement.setString(32,x);
//		        statement.setString(33,x);
//		        statement.setString(34,x);
//		        statement.setDouble(35,0.0);
//		        statement.setDouble(36,0.0);
		        
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
