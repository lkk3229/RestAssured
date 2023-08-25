package day4;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;


public class ParsingJSONResponseData {

	//@Test(priority=1)
	void testJsonResponse()
	{
		//Approch 1
		
		/*given()
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/store")
		.then()
			.statusCode(200)
			.header("Content-type","application/json; charset=utf-8")
			.body("book[3].title",equalTo("The Lord of the Rings"));*/
		
		//Approch 2
		
		Response res =given()
			.contentType(ContentType.JSON)
			
		.when()
			.get("http://localhost:3000/store");
		
		Assert.assertEquals(res.getStatusCode(),200);    //validation 1
		Assert.assertEquals(res.header("Content-Type"),"application/json; charset=utf-8");   //validation 2 
		
		String bookname=res.jsonPath().get("book[3].title").toString();
		Assert.assertEquals(bookname, "The Lord of the Rings");					//validation 3
	}
	
	
	@Test(priority=2)
	void testJsonResponseBodyData()
	{
		Response res =given()
			.contentType(ContentType.JSON)
			
		.when()
			.get("http://localhost:3000/store");
			
		//using JSONObject class
		
		JSONObject jo= new JSONObject(res.asString());   //converting response to JSON object type
		
		//print all titles of books
		
		//serach for title of book in json  - validation 1
		boolean status=false;
		for(int i=0;i<jo.getJSONArray("book").length();i++)
		{
			String bookTitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			//System.out.println(bookTitle);	
			if(bookTitle.equals("The Lord of the Rings"))
			{
				status=true;
				break;
			}
		}
		
		Assert.assertEquals(status, true);
		
		//validate total price of books
		
		double totalprice=0;
		for(int i=0;i<jo.getJSONArray("book").length();i++)
		{
			String price = jo.getJSONArray("book").getJSONObject(i).get("price").toString();
		
			totalprice=totalprice+Double.parseDouble(price);
		
		}
		System.out.println("total price of books is: " +totalprice);
		Assert.assertEquals(totalprice, 526.0);
	}
}
