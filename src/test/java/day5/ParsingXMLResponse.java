package day5;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ParsingXMLResponse {

	//@Test
	void textXMLResponse() 
	{
	//Approch 1
		
		/*given()
			
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1")
		.then()
			.statusCode(200)
			.header("Content-Type", "application/xml; charset=utf-8")
			.body("TravelerinformationResponse.page", equalTo("1"))
			.body("TravelerinformationResponse.travelers.Travelerinformation[0].name", equalTo("Developer")); */
			
			
		//Approach 2
		
		Response res = given()
		
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.header("content-type"), "application/xml; charset=utf-8");
		
		String pageno=res.xmlPath().get("TravelerinformationResponse.page").toString();
		Assert.assertEquals(pageno, "1");
		
		String travername=res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].name").toString();
		Assert.assertEquals(travername, "Developer");
		
		
		
		
	}
	
	@Test
	void textXMLResponseBody() 
	{			
		//Approach 2
		
		Response res = given()
		
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		XmlPath xmlobj = new XmlPath(res.asString());
		
		//Verify total number of travellers
		List<String> travellers=xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation");
		Assert.assertEquals(travellers.size(), 10);
		
		//verify traveller name is present in response
		List<String> traveller_names=xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
		
		boolean status=false;
		for(String travellername:traveller_names)
		{
			if(travellername.equals("AS")) {
				status= true;
				break;
			}
		}
		
		Assert.assertEquals(status, true);
		
	}
}