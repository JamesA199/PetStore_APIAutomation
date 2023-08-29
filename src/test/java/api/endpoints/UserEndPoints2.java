package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//userendpoints2.java using properties file routes.properties
//created to perform CRUD operations

public class UserEndPoints2 
{
	//method getting urls from properties file 
	static ResourceBundle getURL()
	{
		ResourceBundle routes=ResourceBundle.getBundle("routes"); //load properties files
		return routes;
	}

	
	public static Response createUser(User payload)
	{
		
		String post_url = getURL().getString("post_url");
		Response response=given()
				.accept(ContentType.JSON) //header application / json
				.contentType(ContentType.JSON) //header content type application / json
				.body(payload)
		.when() //send the request
				.post(post_url);
		return response;		
	}
	
	static ResourceBundle readUser()
	{
		ResourceBundle routes=ResourceBundle.getBundle("routes"); //load properties files
		return routes;
	}
	
	
	public static Response readUser(String username)
	{
		String get_url = readUser().getString("get_url");
		Response response=given()
				.pathParam("username", username)
		.when() //send the request
				.get(get_url);
		return response;		
	}
	
	static ResourceBundle updateUser()
	{
		ResourceBundle routes=ResourceBundle.getBundle("routes"); //load properties files
		return routes;
	}
	
	
	public static Response updateUser(String userName, User payload)
	{
		String update_url = updateUser().getString("update_url");
		Response response=given()
				.accept(ContentType.JSON) //header application / json
				.contentType(ContentType.JSON) //header content type application / json				
				.pathParam("username", userName)
				.body(payload)				
		.when() //send the request
				.get(update_url);
		return response;		
	}	
	
	static ResourceBundle deleteUser()
	{
		ResourceBundle routes=ResourceBundle.getBundle("routes"); //load properties files
		return routes;
	}
	
	public static Response deleteUser(String userName)
	{
		String delete_url = deleteUser().getString("delete_url");
				Response response=given()
				.pathParam("username", userName)
		.when() //send the request
				.get(delete_url);
		return response;		
	}		
}
