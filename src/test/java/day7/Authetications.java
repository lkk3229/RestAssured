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
	
	@Test(priority=4)
	void testBearerTokenAuthentication()
	{
		String bearerToken = "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";
		
		given()
			.header("Authorization","Bearer "+bearerToken)
			
		.when()
			.get("https://api.github.com/user/repos")
			
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test
	void  testOAuth1Authentication()
	{
		given()
			.auth().oauth("consumerKey","consumerSecrat","accessstoken","tokensecrate") //this is for OAuth1.0 authentication
		.when()
			.get("url")
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test
	void testOauth2Authentication()
	{
		given()
		.auth().oauth2("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") //this is for OAuth2.0 authentication
	.when()
		.get("https://github.com/users/repos")
	.then()
		.statusCode(200)
		.log().all();
	}
	
	@Test
	void testAPIKeyAuthentication()
	{
		//Method 1
		/*
		 * given() .queryParam("aapid","95233acecb0551f0505371c56c287da4") //appid is
		 * APIKey .when() .get(
		 * "https://api.openweathermap.org/data/2.5/forecast/daily?q=Delhi&units=metric&cnt=7")
		 * .then() .statusCode(200) .log().all();
		 */
		
		//Method 2
		
		given()
			.queryParam("aapid","95233acecb0551f0505371c56c287da4")
			
			.pathParam("mypath", "data/2.5/forecast/daily")
			
			.queryParam("q", "Delhi")
			
			.queryParam("units", "metric")
			
			.queryParam("cnt", "7")
			
		.when()
			.get("https://api.openweathermap.org/{mypath}")
		
		.then()
			.statusCode(200)
			.log().all();
		
		
	}
	
	
	
}
