package com;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

import model.Customer;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document; 


@Path("/Customers")
public class CustomerService {

	
	
	Customer customerObj = new Customer();
	
	//view
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readCustomers()
	 {
	return customerObj.readCustomers();
	 }
	
	
	//insert
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertCustomer(
	 @FormParam("cName") String cName,
	 @FormParam("cEmail") String cEmail,
	 @FormParam("cPhoneno") int cPhoneno,
	 @FormParam("cAddress") String cAddress,
	 @FormParam("cNic") String cNic,
	 @FormParam("cPassword") String cPassword)
	
	
	{
	 String output = customerObj.insertCustomer(cName, cEmail, cPhoneno, cAddress,cNic,cPassword);
	return output;
	}
	
	
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCustomer(String customerData)
	{
	//Convert the input string to a JSON object
	 JsonObject customerObject = new JsonParser().parse(customerData).getAsJsonObject();
	//Read the values from the JSON object
	 String customerID = customerObject.get("customerID").getAsString();
	 String cName = customerObject.get("cName").getAsString();
	 String cEmail = customerObject.get("cEmail").getAsString();
	 String cPhoneno = customerObject.get("cPhoneno").getAsString();
	 String cAddress = customerObject.get("cAddress").getAsString();
	 String cNic = customerObject.get("cNic").getAsString();
	 String cPassword = customerObject.get("cPassword").getAsString();
	 String output = customerObj.updateCustomer(customerID, cName ,cEmail,cPhoneno, cAddress,cNic,cPassword);
	return output;
	}
	
	
	
	
  //delete
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCustomer(String customerData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(customerData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String customerID = doc.select("customerID").text();
	 String output = customerObj.deleteCustomer(customerID);
	return output;
	}
	
	
}
