package RestAssuredPractice;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import files.ReuseableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Basics

{

	public static void main(String[] args) throws IOException

	{

		// validate if Add Place API is workimg as expected
		// Add place-> Update Place with New Address -> Get Place to validate if New
		// address is present in response

		// given - all input details
		// when - Submit the API -resource,http method
		// Then - validate the response

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(generateStringFromResource(
						"F:\\Sharan_Automation\\SeleniumFrameworkDesign\\resources\\files\\add_Place.json"))
				.when().post("maps/api/place/add/json").then().log().all().assertThat().statusCode(200)
				.body("scope", equalTo("APP")).header("server", "Apache/2.4.52 (Ubuntu)").extract().asString();

		System.out.println(response);

		JsonPath jp = new JsonPath(response);// for parsing json

		String placeId = jp.getString("place_id");

		System.out.println(placeId);

		// Update Place

		String newAddress = "29, summer walk layout, South Africa";

		given().log().all().queryParam("key", "qaclick123").header("Content-Typ", "application/json")
				.body("{\r\n" + "\"place_id\":\"" + placeId + "\",\r\n" + "\"address\":\"" + newAddress + "\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n" + "}")
				.when().put("maps/api/place/update/json").then().assertThat().statusCode(200)
				.body("msg", equalTo("Address successfully updated"));

		// Get Place

		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
				.when().get("maps/api/place/get/json").then().log().all().assertThat().statusCode(200).extract()
				.asString();

		// JsonPath jp1 = new JsonPath(getPlaceResponse);
		JsonPath js1 = ReuseableMethods.rawToJson(getPlaceResponse);
		String actualAddress = js1.getString("address");
		System.out.println(actualAddress);

		Assert.assertEquals(actualAddress, newAddress);

	}

	public static String generateStringFromResource(String path) throws IOException

	{
		// content of the file to string ->content of the file can convert into byte
		// ->Byte data to string
		String ab = new String(Files.readAllBytes(Paths.get(path)));
		return ab;
	}

}
