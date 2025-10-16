package RestAssuredPractice;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import com.aventstack.extentreports.gherkin.model.Given;

import files.Payload;
import files.ReuseableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Basics2

{

	public static void main(String[] args) throws IOException

	{

		// validate if Add Place API is workimg as expected
		// Add place-> Update Place with New Address -> Get Place to validate if New
		// address is present in response

		// given - all input details
		// when - Submit the API -resource,http method
		// Then - validate the response

		// content of the file to string ->content of the file can convert into byte
		// ->Byte data to string

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(Payload.addPlace()).when().post("maps/api/place/add/json").then().log().all().assertThat()
				.statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)").extract()
				.asString();

		// System.out.println(response);

		JsonPath js = new JsonPath(response);
		String place_Id = js.get("place_id");
		System.out.println(place_Id);

		// Update place
		String newAddress = "70 Eco space, India";

		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + "\"place_id\":\"" + place_Id + "\",\r\n" + "\"address\":\"" + newAddress + "\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n" + "}")
				.when().put("maps/api/place/update/json").then().log().all().assertThat().statusCode(200)
				.body("msg", equalTo("Address successfully updated"));

		// Get Place

		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", place_Id)
				.when().get("maps/api/place/get/json").then().log().all().assertThat().statusCode(200)
				.body("phone_number", equalTo("(+91)9845610338")).extract().response().asString();

		// JsonPath js1 = new JsonPath(getPlaceResponse);
		JsonPath js1 = ReuseableMethods.rawToJson(getPlaceResponse);
		String actualAddress = js1.getString("address");

		System.out.println(actualAddress);
		Assert.assertEquals(actualAddress, newAddress);

	}

	public static String generateSrtingResource(String path) throws IOException

	{
		String ab = new String(Files.readAllBytes(Paths.get(path)));

		return ab;
	}
}
