package day7;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Authetications {
	
	@Test(priority=1)
	void testBasicAuthentication()
	{
		given()
			.auth().basic("postman","password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated",equalTo(true))
			.log().all();
		
	}

	@Test(priority=2)
	void testDigestAuthentication()
	{
		given()
			.auth().digest("postman","password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated",equalTo(true))
			.log().all();
		
	}
	
	@Test(priority=3)
	void testPreemptiveAuthentication()
	{
		given()
			.auth().preemptive().basic("postman","password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated",equalTo(true))
			.log().all();
		
	}
	
	@Test
	void testBearerTokenAuthentication()
	{
		//string
	}
}
