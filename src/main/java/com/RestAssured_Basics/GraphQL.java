package com.RestAssured_Basics;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import com.Utils.API_Payloads;
import com.Utils.API_ReuseableMethods;

public class GraphQL

{

	public static void main(String[] args)

	{
		// query
		RestAssured.baseURI = "https://rahulshettyacademy.com/";

		String response = given().log().all().header("content-type", "application/json")
				.body(API_Payloads.gQuery(16342,16342)).when().post("gq/graphql").then().extract().asString();
		System.out.println(response);
		JsonPath js = API_ReuseableMethods.rawToJson(response);
		String character_Name = js.getString("data.character.name");
		System.out.println(character_Name);
		int loc_Id = js.getInt("data.location.id");
		System.out.println(loc_Id);

		// Mutation
		String mutationResponse = given().log().all().header("content-type", "application/json").body(API_Payloads.gMutation("South","268"))
				.when().post("gq/graphql").then().extract().asString();
		System.out.println(mutationResponse);
		JsonPath mjs = API_ReuseableMethods.rawToJson(mutationResponse);
		int createLoc_Id = mjs.getInt("data.createLocation.id");
		System.out.println(createLoc_Id);

	}

}
