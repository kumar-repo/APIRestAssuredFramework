package com.qa.api.gorest.util;

import java.util.HashMap;
import java.util.Map;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;

/**
 * 
 * @author Yesh
 *
 */

public class GenerateToken {

	/**
	 * 
	 * web site : https://apidocs.imgur.com/#3f80c836-8f49-4fb1-95a7-a4b058265d72
	 * 
	 * application name: getMyImgurToken client ID: ef1439208ea9b39 client secret:
	 * 223d0e36331be4948f4ca210789c51977aa2e70e Callback URL :
	 * https://www.getpostman.com/oauth2/callback AUTH URL :
	 * https://api.imgur.com/oauth2/authorize Access Token URL :
	 * https://api.imgur.com/oauth2/token Access Token :
	 * 8b124aacc69b291b5fc473200e44abac1211e472 refresh_token :
	 * 8aa93f02ebfd101cf3ea1e77e1cb5ca051c879c5 account_id: 156209229
	 * account_username : kumarrvv scope : null Token Name: MyImgurToken
	 * 
	 * 
	 */
	
	public static Map<Object, Object> appTokenMap;
	public static Map<String,String> tokenMap = new HashMap<String,String>();
	public static String clientId = "ef1439208ea9b39";
	 
	public static Map<Object, Object> getAccessToken() {

		Map<String, String> formParams = new HashMap<String, String>();

		formParams.put("refresh_token", "8aa93f02ebfd101cf3ea1e77e1cb5ca051c879c5");
		formParams.put("client_id", "ef1439208ea9b39");
		formParams.put("client_secret", "223d0e36331be4948f4ca210789c51977aa2e70e");
		formParams.put("grant_type", "refresh_token");

		JsonPath responseTokenJson = given().formParams(formParams)
		.when()
		.post("https://api.imgur.com/oauth2/token")
		.then()
		.extract().jsonPath();
		
		System.out.println(responseTokenJson.getMap(""));	
		
		appTokenMap = responseTokenJson.getMap("");
		
		return appTokenMap;
	}
	
	/**
	 * 
	 * @return token map which as bearer header with access token
	 * example = Authorization : "Bearer c63b32d7a13355ed71f11cd4ca0e7d3ae191a50f"
	 */
	public static Map<String,String> getAuthToken()
	{		
		String authToken = appTokenMap.get("access_token").toString();
		System.out.println("AccessToken===>"+authToken);		
		tokenMap.put("Authorization","Bearer "+authToken);	
		return tokenMap;
	}
	
	public static Map<String,String> getClientId(){
		
		System.out.println("Client ID ====>" +clientId);
		tokenMap.put("Authorization", "Client_ID "+clientId);
		return tokenMap;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
