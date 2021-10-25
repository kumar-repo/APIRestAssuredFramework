/**
 * 
 */
package com.qa.api.gorest.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Yesh
 *
 */
public class TestUtil {

	/**
	 * 
	 * @param obj
	 * @return converted POJO object to json object (POJO object to serailized data which is stream of bytes (json,xml) )
	 * 
	 */
	public static String getSerializedJSON(Object obj) {

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(obj);
			System.out.println("JSON body payload :" +jsonString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return jsonString;
	}

}
