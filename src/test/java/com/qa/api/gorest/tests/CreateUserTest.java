/**
 * 
 */
package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.api.gorest.pojo.User;
import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.ExcelUtil;

import io.restassured.response.Response;

/**
 * @author Yesh
 *
 */
public class CreateUserTest {

	String baseURI = "https://gorest.co.in";
	String basePath = "/public-api/users";
	String token = "ef670a8f857504b9c1ad8c048bc0d560d5c99b81021cafae35cb831e6a9c9e33";

	@DataProvider
	public Object getUserData() {
		Object userData[][] = ExcelUtil.getTestData("usertestdata");
		return userData;	
	}
	
	@Test (dataProvider="getUserData")
	public void createUserAPIPOSTTest(String name, String gender, String email, String status ) 
	{
		User user = new User(name,gender,email,status);
		//User user = new User("yeshwish", "male", "yeshwishtestmail@gmail.com", "active");
		Map<String, String> accessTokenMap = new HashMap<String, String>();
		accessTokenMap.put("Authorization", "Bearer "+token);
		Response response = RestClient.doPost("JSON", baseURI, basePath, accessTokenMap, null, true, user);
		System.out.println(response.getContentType());
		System.out.println(response.prettyPrint());
		System.out.println("==========================================================================");

	}

}
