package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Product {
	
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", ""); //connecting to the database
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 }
	
	
	
	

	public String insertItem(String code, String name, String price, String desc) //insert(post) function
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 String query = " insert into products(`productId`,`productCode`,`productName`,`productPrice`,`productDesc`)" + " values (?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, code);                      //set the values
	 preparedStmt.setString(3, name);
	 preparedStmt.setDouble(4, Double.parseDouble(price));
	 preparedStmt.setString(5, desc);
	// execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while inserting the products.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	
	
	
	public String readItems()        //read function (get)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Product Code</th><th>Product Name</th>" +
	 "<th>Product Price</th>" +
	 "<th>Product Description</th>" +
	 "<th>Update</th><th>Remove</th></tr>";

	 String query = "select * from products";    //query
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String itemID = Integer.toString(rs.getInt("productId"));
	 String itemCode = rs.getString("productCode");
	 String itemName = rs.getString("productName");
	 String itemPrice = Double.toString(rs.getDouble("productPrice"));
	 String itemDesc = rs.getString("productDesc");
	 // Add into the html table
	 output += "<tr><td>" + itemCode + "</td>";
	 output += "<td>" + itemName + "</td>";             
	 output += "<td>" + itemPrice + "</td>";
	 output += "<td>" + itemDesc + "</td>";
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' "
			 + "class='btnUpdate btn btn-secondary' data-itemid='" + itemID + "'></td>"
			 + "<td><input name='btnRemove' type='button' value='Remove' "
			 + "class='btnRemove btn btn-danger' data-itemid='" + itemID + "'></td></tr>";                 //pass the values to the database
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the products.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	
	
	
	
	public String updateItem(String ID, String code, String name, String price, String desc)  //update function
	{
	String output = "";
	try
	{
	Connection con = connect();
	if (con == null)
	{return "Error while connecting to the database for updating."; }
	// create a prepared statement
	String query = "UPDATE products SET productCode=?,productName=?,productPrice=?,productDesc=?WHERE productId=?";
	PreparedStatement preparedStmt = con.prepareStatement(query);
	// binding values
	preparedStmt.setString(1, code);
	preparedStmt.setString(2, name);
	preparedStmt.setDouble(3, Double.parseDouble(price));
	preparedStmt.setString(4, desc);
	preparedStmt.setInt(5, Integer.parseInt(ID));
	// execute the statement
	preparedStmt.execute();
	con.close();
	
	String newItems = readItems();
	 output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
	}
	catch (Exception e)
	{
	output = "{\"status\":\"error\", \"data\":\"Error while updating the item.\"}";
	System.err.println(e.getMessage());
	}
	return output;
	}
	
	
	
	public String deleteItem(String itemID)   //delete function
	{
	String output = "";
	try
	{
	Connection con = connect();
	if (con == null)
	{return "Error while connecting to the database for deleting."; }
	// create a prepared statement
	String query = "delete from products where productId=?";
	PreparedStatement preparedStmt = con.prepareStatement(query);
	// binding values
	preparedStmt.setInt(1, Integer.parseInt(itemID));
	// execute the statement
	preparedStmt.execute();
	con.close();
	
	String newItems = readItems();
	 output = "{\"status\":\"success\", \"data\": \"" +newItems + "\"}";
	}
	
	catch (Exception e)
	{
		output = "{\"status\":\"error\", \"data\":\"Error while deleting the item.\"}";
	System.err.println(e.getMessage());
	}
	return output;
	}
	
}
