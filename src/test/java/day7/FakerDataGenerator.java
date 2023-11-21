package day7;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class FakerDataGenerator {

	@Test
	void testGenerateDummyData()
	{
		Faker faker = new Faker();

		String fullname = faker.name().fullName(); // Miss Samanta Schmidt
		String firstname = faker.name().firstName(); // Emory
		String lastname = faker.name().lastName(); // Barton
		
		String username=faker.name().username();
		String password=faker.internet().password();
		
		String phoneno=faker.phoneNumber().cellPhone();
		
		String email=faker.internet().safeEmailAddress();
		
		String streetAddress = faker.address().streetAddress(); // 60018 Sawayn Brooks Suite 449
	
	System.out.println("Full Name:"+fullname);
	System.out.println("First Name:"+firstname);
	System.out.println("Last Name:"+lastname);
	System.out.println("UserName:"+username);
	System.out.println("Password:"+password);
	System.out.println("Phone No:"+phoneno);
	System.out.println("Email Id:"+email);
	System.out.println("Address:"+streetAddress);
	
	
	}
}
