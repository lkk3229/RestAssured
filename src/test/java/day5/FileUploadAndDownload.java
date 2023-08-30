package day5;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.annotations.Test;

public class FileUploadAndDownload {

	@Test(priority=1)
	void singleFileUpload()
	{
		File myfile = new File("C:\\node files\\Test11.txt");
		
		given()
			.multiPart("file",myfile)
			.contentType("multiPart/form-data")
		.when()
			.post("http://localhost:8080/uploadFile")
		.then()
			.statusCode(200)
			.body("filename", equalTo("Test11.txt"))
			.log().all();
	}
	
	//@Test
	void multipleFileUpload()
	{
		File myfile1 = new File("C:\\node files\\Test11.txt");
		File myfile2 = new File("C:\\node files\\Test11 - Copy.txt");
		
		given()
			.multiPart("file",myfile1)
			.multiPart("file",myfile2)
			.contentType("multiPart/form-data")
		.when()
			.post("http://localhost:8080/uploadMultipleFiles")
		.then()
			.statusCode(200)
			.body("[0].filename", equalTo("Test11.txt"))
			.body("[1].filename", equalTo("Test11 - Copy.txt"))
			.log().all();
	}
	
	//@Test
	void multipleFileUpload2()  //wont work for all kinds of API
	{
		File myfile1 = new File("C:\\node files\\Test11.txt");
		File myfile2 = new File("C:\\node files\\Test11 - Copy.txt");
		
		File filearr[]= {myfile1,myfile2};
		given()
			.multiPart("file",filearr)
			.contentType("multiPart/form-data")
		.when()
			.post("http://localhost:8080/uploadMultipleFiles")
		.then()
			.statusCode(200)
			.body("[0].filename", equalTo("Test11.txt"))
			.body("[1].filename", equalTo("Test11 - Copy.txt"))
			.log().all();
	}
	
	@Test(priority=2)
	void fileDownload()
	{
	
		given()
		
		.when()
			.get("http://localhost:8080/downloadFile/Test11.txt")
			
		.then()
			.statusCode(200)
			.log().body();
		
	}
}
