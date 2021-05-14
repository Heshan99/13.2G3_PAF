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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Funds;
@Path("/Funds")
public class FundService {
	Funds fundObj = new Funds();
	 
	 
	 @GET
	 @Path("/")
	 @Produces(MediaType.TEXT_HTML)
	 public String readItems()
	  	{
		 return fundObj.readItems();
	  	}
	 
	 @POST
	 @Path("/")
	 @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	 @Produces(MediaType.TEXT_PLAIN)
	 public String insertItem(@FormParam("FundingProjectName") String itemCode,
	 @FormParam("Amount") String itemPrice,
	 @FormParam("FundEstimatedBy") String itemDesc)
	 	{
		 String output = fundObj.insertItem(itemCode, itemPrice, itemDesc);
		 return output;
	 	}
	 
	 
	 @PUT
	 @Path("/")
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.TEXT_PLAIN)
	 public String updateItem(String itemData)
	 {
		 //Convert the input string to a JSON object
		 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
		 //Read the values from the JSON object
	 
		 String itemID = itemObject.get("FundID").getAsString();
		 String itemCode = itemObject.get("FundingProjectName").getAsString();
		 String itemPrice = itemObject.get("Amount").getAsString();
		 String itemDesc = itemObject.get("FundEstimatedBy").getAsString();
		 String output = fundObj.updateItem(itemID, itemCode, itemPrice, itemDesc);
	 
		 return output;
	 }
	 
	 
	 @DELETE
	 @Path("/")
	 @Consumes(MediaType.APPLICATION_XML)
	 @Produces(MediaType.TEXT_PLAIN)
	 public String deleteItem(String itemData)
	 
	 {
	 
		 //Convert the input string to an XML document
		 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());
	 
		 //Read the value from the element <itemID>
		 String itemID = doc.select("FundID").text();
	 	 String output = fundObj.deleteItem(itemID);
	 
	 	 return output;
	 }
}
