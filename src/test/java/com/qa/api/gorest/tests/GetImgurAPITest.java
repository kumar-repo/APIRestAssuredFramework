package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.GenerateToken;

import io.restassured.response.Response;

public class GetImgurAPITest {
	
	public Map<Object, Object> tokenMap;
	public Map<String, String> accessTokenMap;
	public String accountUserName;
	public String refreshToken;
	public String baseURI ="https://api.imgur.com";
	
	/**
	 * get token and other details from GenerateToken class
	 */
	
	@BeforeMethod
	public void setUp() {	
		tokenMap = GenerateToken.getAccessToken();
		//accessToken = tokenMap.get("access_token").toString();
		//accessToken = GenerateToken.getAuthToken();
		accessTokenMap = GenerateToken.getAuthToken();
		accountUserName = tokenMap.get("account_username").toString();
		refreshToken = tokenMap.get("refresh_token").toString();		
	}
	
	@Test
	public void getAccountBlockStatusTest() 
	{		
		Response response = RestClient.doGet(null, baseURI,"/account/v1/"+accountUserName+"/block", accessTokenMap, null, true);		
		//System.out.println(response.statusCode());
		System.out.println(response.prettyPrint());	
	}
	
	
	@Test
	public void getAccountImagesTest() 
	{		
		Response response = RestClient.doGet(null, baseURI,"/3/account/me/images", accessTokenMap, null, true);	
		//System.out.println(response.statusCode());
		System.out.println(response.prettyPrint());	
	}
	
	@Test
	public void uploadImageTest() 
	{		
		Map<String, String> clientIdMap = GenerateToken.getClientId();
		Map<String, String> formMap = new HashMap<String, String>();
		formMap.put("title", "test title API");
		formMap.put("description", "Test description");	
		Response response = RestClient.doPost("multipart", baseURI, "/3/upload", clientIdMap, null, true, formMap);	
		//System.out.println(response.statusCode());
		System.out.println(response.prettyPrint());	
	}
	
	
	
}
