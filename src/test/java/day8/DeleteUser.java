package day8;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class DeleteUser {

	@Test
	void test_deletUser(ITestContext context)
	{
		
		String bearerToken = "130ab5a13e8112294603bc832473f755d6ca8072b248094106bb86b78ae2a8cd";
		
		//int id = (Integer) context.getAttribute("user_id");
		int id=(Integer) context.getSuite().getAttribute("user_id");
	 
		given()
			.header("Authorization","Bearer "+bearerToken)
			.pathParam("id", id)
			
		.when()
			.delete("https://gorest.co.in/public/v2/users/{id}")
		
		.then()
			.statusCode(204)
			.log().all();
	}
}
