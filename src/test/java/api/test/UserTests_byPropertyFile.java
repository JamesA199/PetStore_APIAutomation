package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests_byPropertyFile 
{
	Faker faker;
	User userPayload;
	
	public Logger logger;
	
	
	@BeforeClass
	public void setupData() 
	{
		faker=new Faker();
		userPayload=new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger=LogManager.getLogger(this.getClass());
		
	}
	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("******************running testPostUser create user by property file*******************");
		System.out.println("******************running testPostUser create user by property file*******************");
		Response response=UserEndPoints2.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("******************finished testPostUser create user by property file*******************");
		logger.info("******************finished testPostUser create user by property file*******************");
		
	}
	@Test(priority=2)
	public void testGetUserByName()
	{
		logger.info("******************running testGetUserByName by property file*******************");
		System.out.println("******************running testGetUserByName by property file*******************");		
		Response response=UserEndPoints2.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("******************finished testGetUserByName by property file*******************");		
		logger.info("******************finished testGetUserByName by property file*******************");
	}	
	@Test(priority=3)
	public void testUpdateUserByName()
	{
		logger.info("******************running testUpdateUserByName by property file*******************");
		System.out.println("******************running testUpdateUserByName by property file*******************");
		//update user details
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		
		Response response=UserEndPoints2.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		
		//checking data after update
		Response responseAfterupdate=UserEndPoints2.readUser(this.userPayload.getUsername());
		Assert.assertEquals(responseAfterupdate.getStatusCode(), 200);
		
		System.out.println("status code: "+response.getStatusCode());
		System.out.println("******************finished testUpdateUserByName by property file*******************");		
		logger.info("******************finished testUpdateUserByName by property file*******************");
	}

	@Test(priority=4)
	public void testDeleteUserByName()
	{
		logger.info("******************running testDeleteUserByName by property file*******************");
		System.out.println("******************running testDeleteUserByName by property file*******************");				
		Response response=UserEndPoints2.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("status code: "+response.getStatusCode());
		System.out.println("******************finished testDeleteUserByName by property file*******************");				
		logger.info("******************finished testDeleteUserByName by property file*******************");
	}
	
}
