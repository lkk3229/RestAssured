package day8;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;

public class UpdateUser {

	@Test
	void test_updateUser(ITestContext context)
	{
        Faker faker = new Faker();
		
		JSONObject data = new JSONObject();
		
		data.put("name", faker.name().fullName());
		data.put("gender", "Male");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "active");
		
		String bearerToken = "130ab5a13e8112294603bc832473f755d6ca8072b248094106bb86b78ae2a8cd";
		
		//int id=(Integer) context.getAttribute("user_id");
		int id=(Integer) context.getSuite().getAttribute("user_id");
		
		given()
			.headers("Authorization","Bearer "+bearerToken)
			.contentType("application/json")
			.pathParam("id",id)
			.body(data.toString())
		
		.when()
			.put("https://gorest.co.in/public/v2/users/{id}")
		
		.then()
			.statusCode(200)
			.log().all();
			
	}
}
