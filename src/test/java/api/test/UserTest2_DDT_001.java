package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class UserTest2_DDT_001 
{
	@Test(priority=1, dataProvider="UserData", dataProviderClass=DataProviders.class)
	public void testDDTPostUser(String userID, String userName, String fname, String lname, String useremail, String pwd, String ph )
	{
		System.out.println("running testDDTPostUser");	
		User userPayload=new User();
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
		Response response=UserEndPoints.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("Finished running testDDTPostUser");	
	}
	
	@Test(priority=2, dataProvider="UserNames", dataProviderClass=DataProviders.class)
	public void testDDTDeleteUser(String userName)
	{
		System.out.println("running testDDTDeleteUser");	
		Response response=UserEndPoints.deleteUser(userName);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("status code: "+response.getStatusCode());
		System.out.println("finished running testDDTDeleteUser");	
	}
}
