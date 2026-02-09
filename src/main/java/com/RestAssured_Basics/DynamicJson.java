package com.RestAssured_Basics;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Utils.API_Payloads;
import com.Utils.API_ReuseableMethods;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson

{
	
	
	@Test(dataProvider = "BooksData")
	public void add_Book(String isbn, String aisle)

	{
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		String response = given().log().all().header("Content-Type", "application/json")
				.body(API_Payloads.add_Book(isbn, aisle)).when().post("/Library/Addbook.php").then().log().all()
				.statusCode(200).extract().response().asString();

		JsonPath jp = API_ReuseableMethods.rawToJson(response);
		System.out.println("Response starts " + jp.toString() + " ends");
		String id = jp.get("ID");
		System.out.println(id);

	}

	@Test(dataProvider = "BooksData")

	public void delete_Book(String isbn, String aisle)

	{
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String res1 = given().log().all().header("Content-Type", "application/json")
				.body(API_Payloads.delete_Book(isbn, aisle)).when().post("/Library/DeleteBook.php").then().log().all()
				.statusCode(200).extract().response().asString();
	}

	@DataProvider(name = "BooksData")

	public Object[][] getData()

	{
		Object[][] data = { { "Kuvempu", "25896" }, 
							{ "Bendre" , "89745" },
							{ "shivram", "78958" } };
		return data;
	}

}
