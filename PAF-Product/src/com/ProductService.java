package com;

import model.Product;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Products")
public class ProductService {
	Product ProductObj = new Product();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readProduct() {
		return ProductObj.readProduct();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertProduct(@FormParam("pname") String pname, @FormParam("pdate") String pdate,
			@FormParam("pdes") String pdes) {
		String output = ProductObj.insertProduct(pname, pdate, pdes);
		return output;

	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updateProduct(String ProductData) {
		// Convert the input string to a JSON object
		JsonObject ProductObject = new JsonParser().parse(ProductData).getAsJsonObject();

		// Read the values from the JSON object
		String pid = ProductObject.get("pid").getAsString();
		String pname = ProductObject.get("pname").getAsString();
		String pdate = ProductObject.get("pdate").getAsString();
		String pdes = ProductObject.get("pdes").getAsString();

		String output = ProductObj.updateProduct(pid, pname, pdate, pdes);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteProduct(String ProductData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(ProductData, "", Parser.xmlParser());

		// Read the value from the element <itemID>
		String pid = doc.select("pid").text();
		String output = ProductObj.deleteProduct(pid);
		return output;
	}
}
