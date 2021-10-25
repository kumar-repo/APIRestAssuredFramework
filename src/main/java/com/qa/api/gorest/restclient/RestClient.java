package com.qa.api.gorest.restclient;

import java.io.File;
/**
 * @author Yesh
 * Wrapper for all hHTTP methods GET,POST,PUT,DELETE
 *
 */
import java.util.Map;

import com.qa.api.gorest.util.TestUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {

	/**
	 * 
	 * @param contentType
	 * @param baseURI
	 * @param basePath
	 * @param token
	 * @param paramsMap
	 * @param log
	 * @return Response for GET call
	 */

	public static Response doGet(String contentType, String baseURI, String basePath, Map<String, String> token,
			Map<String, String> paramsMap, boolean log) {
		setBaseURI(baseURI);
		RequestSpecification request = createrequest(contentType, token, paramsMap, log);
		return getResponse("GET", request, basePath);

	}

	/**
	 * 
	 * @param contentType
	 * @param baseURI
	 * @param basePath
	 * @param token
	 * @param paramsMap
	 * @param log
	 * @param obj
	 * @return response for POST call
	 */

	public static Response doPost(String contentType, String baseURI, String basePath, Map<String, String> token,
			Map<String, String> paramsMap, boolean log, Object obj) {

		if (setBaseURI(baseURI)) {
			RequestSpecification request = createrequest(contentType, token, paramsMap, log);
			addRequestPayload(request, obj);
			return getResponse("POST", request, basePath);
		}
		return null;
	}

	public static void addRequestPayload(RequestSpecification request, Object obj) {
		
		if (obj instanceof Map)
		{
			request.formParams((Map<String, String>)obj);
		}
		else
		{
			String jsonPayLoad = TestUtil.getSerializedJSON(obj);
			request.body(jsonPayLoad);	
		}
	}

//	private static void setBaseURI(String baseURI) {
//		RestAssured.baseURI = baseURI;
//	}
// we can add exception handling to setBaseURI method as below or we can simply use above methos

	private static boolean setBaseURI(String baseURI) {

		if (baseURI == null || baseURI.isEmpty()) {
			System.out.println("Please pass valid or check for empty baseURI............. ");
			return false;
		}
		try {
			RestAssured.baseURI = baseURI;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
	}

	/*
	 * Creating request with following params String contentType, String token,
	 * Map<String, String> paramsMap, boolean log
	 * 
	 */

	private static RequestSpecification createrequest(String contentType, Map<String, String> token, Map<String, String> paramsMap,
			boolean log) {
		RequestSpecification request;

		if (log) {
			request = RestAssured.given().log().all();
		} else {
			request = RestAssured.given();
		}

		if (token.size()>0)
		{
			request.headers(token);
		}
		
//		if (!(token == null)) {
//			request.header("Authorization", "Bearer " + token);
//		}
		
		if (!(paramsMap == null)) {
			request.queryParams(paramsMap);
		}

		if (contentType != null) {
			
			if (contentType.equalsIgnoreCase("JSON")) {
				request.contentType(ContentType.JSON);
			}

			else if (contentType.equalsIgnoreCase("XML")) {
				request.contentType(ContentType.XML);
			}

			else if (contentType.equalsIgnoreCase("TEXT")) {
				request.contentType(ContentType.TEXT);
			}
			else if (contentType.equalsIgnoreCase("multipart")) {
				request.multiPart("image",new File("./src/main/java/com/qa/api/imgur/images/diamond hands.jpg"));
			}
		}

		return request;
	}

	/*
	 * Return response based on the REST call put,post,get,delete
	 */

	private static Response getResponse(String httpMethod, RequestSpecification request, String basePath) {
		return executeAPI(httpMethod, request, basePath);
	}

	/*
	 * Select HTTP method based on request and collects response
	 */

	private static Response executeAPI(String httpMethodValue, RequestSpecification request, String basePath) {
		Response response = null;

		switch (httpMethodValue) {
		case "GET":
			response = request.get(basePath);
			break;
		case "POST":
			response = request.post(basePath);
			break;
		case "PUT":
			response = request.put(basePath);
			break;
		case "DELETE":
			response = request.delete(basePath);
			break;
		default:
			System.out.println("Please enter the valid http method......");
			break;
		}

		return response;
	}
}
