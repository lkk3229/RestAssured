package day6;

import org.testng.annotations.Test;

import io.restassured.matcher.RestAssuredMatchers;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

// xml to xsd convertot 
// https://www.convertsimple.com/convert-xml-to-xsd-xml-schema/
public class XMLSchemaValidation {

	@Test
	void xmlschemavalidation() 
	{
		given()
		
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler")
			
		.then()
		.assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("traveler.xsd"));
	}
}
