package day2;

import org.json.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

/*
 Different ways of to create POST request body
 1) Post request body using HashMap
 2) Post request body using org.JSON
 3) Post request body using POJO class
 4) Post request body using json file data
 */

public class DiffWaysToCreatePostRequestBody {

// 1) Post request body using HashMap
	
	//@Test(priority=1)
	void testPostusingHashMap() {
		
		HashMap data = new HashMap();
		
		data.put("name", "Scott");
		data.put("location", "France");
		data.put("phone", "1323454");
		
		String courcesArr[]= {"C","C++"};
		
		data.put("cources", courcesArr);
		
		given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.post("http://localhost:3000/students")
		
		
		.then()
			.statusCode(201)
			.body("name",equalTo("Scott"))
			.body("location",equalTo("France"))
			.body("phone",equalTo("1323454"))
			.body("cources[0]",equalTo("C"))
			.body("cources[1]",equalTo("C++"))
			.header("Content-Type","application/json; charset=utf-8")
			.log().all();
		
	}
	
// 2) Post request body using org.JSON
	
	@Test(priority=1)
	void testPostusingJsonLibrary() {
		
		JSONObject data = new JSONObject();
		
		data.put("name", "Scott");
		data.put("location", "France");
		data.put("phone", "1323454");
		
		String courcesArr[]= {"C","C++"};
		
		data.put("cources", courcesArr);
		
		given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.post("http://localhost:3000/students")
		
		
		.then()
			.statusCode(201)
			.body("name",equalTo("Scott"))
			.body("location",equalTo("France"))
			.body("phone",equalTo("1323454"))
			.body("cources[0]",equalTo("C"))
			.body("cources[1]",equalTo("C++"))
			.header("Content-Type","application/json; charset=utf-8")
			.log().all();
		
	}
	
	//deleting student record
	@Test(priority=2)
	void testDelete() {
		
		given()
		
		.when()
		.delete("http://localhost:3000/students/4")
		
		.then()
			.statusCode(200);
	}
	
}
