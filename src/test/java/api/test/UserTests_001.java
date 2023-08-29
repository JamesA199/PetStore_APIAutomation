package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests_001 
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
		logger.info("******************running testPostUser create user*******************");
		Response response=UserEndPoints.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("status code: "+response.getStatusCode());
		logger.info("******************finished testPostUser create user*******************");
		
	}
	@Test(priority=2)
	public void testGetUserByName()
	{
		logger.info("******************running testGetUserByName*******************");
		Response response=UserEndPoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("status code: "+response.getStatusCode());
		logger.info("******************finished testGetUserByName*******************");
	}	
	@Test(priority=3)
	public void testUpdateUserByName()
	{
		logger.info("******************running testUpdateUserByName*******************");
		//update user details
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		System.out.println("running testUpdateUserByName");
		
		Response response=UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		
		//checking data after update
		Response responseAfterupdate=UserEndPoints.readUser(this.userPayload.getUsername());
		Assert.assertEquals(responseAfterupdate.getStatusCode(), 200);
		
		System.out.println("status code: "+response.getStatusCode());
		logger.info("******************finished testUpdateUserByName*******************");
	}

	@Test(priority=4)
	public void testDeleteUserByName()
	{
		logger.info("******************running testDeleteUserByName*******************");
		Response response=UserEndPoints.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("status code: "+response.getStatusCode());
		logger.info("******************finished testDeleteUserByName*******************");
	}
	
}
