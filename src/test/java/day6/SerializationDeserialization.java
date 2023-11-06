package day6;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


//POJO --- Serilize --> JSON Object ---de-serilizae ---> POJO

public class SerializationDeserialization {

	//POJO ---> json (Serilization)
	//@Test
	void convertPojo2Json() throws JsonProcessingException
	{
		//created java object using POJO class
		Student stupojo = new Student();    //pojo
		
		stupojo.setName("Scott");
		stupojo.setLocation("France");
		stupojo.setPhone("123456");
	
		String courcesArr[]= {"C","C++"};
		stupojo.setCources(courcesArr);
		
		//convert java object ---> json object (serilization)
		ObjectMapper objMapper = new ObjectMapper();
	
		String jsondata = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(objMapper);
		System.out.println(jsondata);
	
	
	}
	
	//json ---> POJO (De-Serilization)
		@Test
		void convertJson2Pojo() throws JsonProcessingException
		{
			String jsondata= "{\r\n"
					+ " \"name\" : \"Scott\",\r\n"
					+ " \"location\" : \"France\",\r\n"
					+ " \"phone\" : \"123456\",\r\n"
					+ " \"cources\" : [\"c\", \"c++\" ]\r\n"
					+ "}";
			
			//converting json data --> POJO Object
			
			ObjectMapper objMapper = new ObjectMapper();
			
			Student stupojo = objMapper.readValue(jsondata,Student.class);
		
			System.out.println("Name:"+stupojo.getName());
			System.out.println("Location:"+stupojo.getLocation());
			System.out.println("Phone:"+stupojo.getPhone());
			System.out.println("Course 1:"+stupojo.getCources()[0]);
			System.out.println("Course 2:"+stupojo.getCources()[1]);
			
		}
}
