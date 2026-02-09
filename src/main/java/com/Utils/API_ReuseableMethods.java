package com.Utils;

import io.restassured.path.json.JsonPath;

public class API_ReuseableMethods 

{
	public static JsonPath rawToJson(String response)
	
	{
		JsonPath js = new JsonPath(response);
		
		return js;
	}
}
