package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//userendpoints.java
//created to perform CRUD operations

public class UserEndPoints 
{
	public static Response createUser(User payload)
	{
		Response response=given()
				.accept(ContentType.JSON) //header application / json
				.contentType(ContentType.JSON) //header content type application / json
				.body(payload)
		.when() //send the request
				.post(Routes.post_url);
		return response;		
	}
	
	public static Response readUser(String username)
	{
		Response response=given()
				.pathParam("username", username)
		.when() //send the request
				.get(Routes.get_url);
		return response;		
	}
	public static Response updateUser(String userName, User payload)
	{
		Response response=given()
				.accept(ContentType.JSON) //header application / json
				.contentType(ContentType.JSON) //header content type application / json				
				.pathParam("username", userName)
				.body(payload)				
		.when() //send the request
				.get(Routes.update_url);
		return response;		
	}	
	
	public static Response deleteUser(String userName)
	{
		Response response=given()
				.pathParam("username", userName)
		.when() //send the request
				.get(Routes.delete_url);
		return response;		
	}		
}
