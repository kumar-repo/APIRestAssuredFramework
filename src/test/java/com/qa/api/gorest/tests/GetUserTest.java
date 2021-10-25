/**
 * 
 */
package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yesh
 *
 */

import org.testng.annotations.Test;
import com.qa.api.gorest.restclient.RestClient;
import io.restassured.response.Response;

public class GetUserTest {
	String baseURI= "https://gorest.co.in";
	String basePath="/public-api/users";
	String token="ef670a8f857504b9c1ad8c048bc0d560d5c99b81021cafae35cb831e6a9c9e33";
	
	@Test
	public void getAllUsersTest() 
	{	
		Map<String, String> accessTokenMap = new HashMap<String, String>();
		accessTokenMap.put("Authorization", "Bearer "+token);
		
		Response response = RestClient.doGet("JSON", baseURI, basePath, accessTokenMap, null, true);
		System.out.println(response.getContentType());
		System.out.println(response.prettyPrint());	
	}
	
	@Test
	public void getAllUsersWithParamsTest() 
	{	
		Map<String,String> params = new HashMap<String,String>();
		
		Map<String, String> accessTokenMap = new HashMap<String, String>();
		accessTokenMap.put("Authorization", "Bearer "+token);
		
		params.put("name", "raghu");
		params.put("gender","male");
		
		Response response = RestClient.doGet("JSON", baseURI, basePath, accessTokenMap, params, true);
		System.out.println(response.getContentType());
		System.out.println(response.prettyPrint());	
	}

}
