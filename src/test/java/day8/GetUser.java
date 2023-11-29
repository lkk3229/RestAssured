package day8;

import org.testng.ITestContext;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class GetUser {

	@Test
	void test_getUser(ITestContext context)
	{
		int id=(Integer) context.getSuite().getAttribute("user_id");  //this should come from create user request
				
		String bearerToken="130ab5a13e8112294603bc832473f755d6ca8072b248094106bb86b78ae2a8cd";
		
		given()
			.headers("Authorization","Bearer "+bearerToken)
			.pathParam("id",id)
			
		.when()
			.get("https://gorest.co.in/public/v2/users/{id}")
		
		.then()
			.statusCode(200)
			.log().all();
	}
}
