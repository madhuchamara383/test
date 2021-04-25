package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Customer {

	
	
	//A common method to connect to the DB
		private Connection connect()
		 {
		 Connection con = null;
		 try
		 {
		 Class.forName("com.mysql.cj.jdbc.Driver");

		 //Provide the correct details: DBServer/DBName, username, password
		 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gb", "root", "");
		 
		 System.out.print("Successfully connected to the Database");
		 }
		 catch (Exception e)
		 {e.printStackTrace();}
		 return con;
		 }
		
		
			
		//insert
		public String insertCustomer(String name, String email, int pno, String address,String nic,String password)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for inserting."; }
		 
		 
		 // create a prepared statement
		 String query = " insert into customermanage (customerID,cName,cEmail,cPhoneno,cAddress,cNic,cPassword)"
		 + " values (?, ?, ?, ?, ? , ? , ? )";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 
		 
		 // binding values
		 preparedStmt.setInt(1,0);
		 preparedStmt.setString(2, name);
		 preparedStmt.setString(3, email);
		 preparedStmt.setInt(4,pno);
		 preparedStmt.setString(5, address);
		 preparedStmt.setString(6, nic);
		 preparedStmt.setString(7, password);
		 
		 
		// execute the statement
		//System.out.println(code);
		 preparedStmt.execute();
		 con.close();
		 output = "Inserted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while inserting the customer.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
		
		
		
		//views
		public String readCustomers()
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for reading."; }
		 
		 
		 
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>Customer Name</th><th>Customer Email</th>" +
		 "<th>Contact Number</th>" +
		 "<th>Address</th>" +
		 "<th>NIC Number</th>" +
		 "<th>Password</th>" +
		 "<th>Update</th><th>Remove</th></tr>";

		 String query = "select * from customermanage";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 
		 
		 
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
		 String customerID = Integer.toString(rs.getInt("customerID"));
		 String cName = rs.getString("cName");
		 String cEmail = rs.getString("cEmail");
		 String cPhoneno = Integer.toString(rs.getInt("cPhoneno"));
		 String cAddress = rs.getString("cAddress");
		 String cNic = rs.getString("cNic");
		 String cPassword = rs.getString("cPassword");
		 
		 
		 // Add into the html table
		 output += "<tr><td>" + cName+ "</td>";
		 output += "<td>" + cEmail + "</td>";
		 output += "<td>" + cPhoneno + "</td>";
		 output += "<td>" + cAddress + "</td>";
		 output += "<td>" + cNic + "</td>";
		 output += "<td>" + cPassword  + "</td>";
		 
		 
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
		 + "<td><form method='post' action='items.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
		 + "<input name='itemID' type='hidden' value='" + customerID
		 + "'>" + "</form></td></tr>";
		 }
		 con.close();
		 
		 
		 // Complete the html table
		 output += "</table>";
		 }
		 catch (Exception e)
		 {
		 output = "Error while reading the customer.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
		
		

		//update
		
			public String updateCustomer(String ID, String name, String email, String pno, String address,String nic,String password)
			//4
			 {
			 String output = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for updating."; }
			 
			 
			 // create a prepared statement
			 String query = "UPDATE customermanage SET cName=?,cEmail=?,cPhoneno=?,cAddress=?,cNic=?,cPassword=? WHERE customerID=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
		     // binding values
			 preparedStmt.setString(1, name);
			 preparedStmt.setString(2, email);
		     //preparedStmt.setInt(3, Integer.parseInt(pno));
			 preparedStmt.setString(3, pno);
			 preparedStmt.setString(4, address);
			 preparedStmt.setString(5, nic);
			 preparedStmt.setString(6, password);
			 preparedStmt.setInt(7, Integer.parseInt(ID));
			 
			 
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 output = "Updated successfully";
			 }
			 catch (Exception e)
			 {
			 output = "Error while updating the customer.";
			 System.err.println(e.getMessage());
			 }
			 return output;
			 }
			
			
		
		
      //delete
		public String deleteCustomer(String customerID)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for deleting."; }
		 
		 // create a prepared statement
		 String query = "delete from customermanage where customerID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 
		 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(customerID));
		 
		 
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Deleted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while deleting the customer.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		 
		
		
}
