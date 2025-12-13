package RestAssuredPractice;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import rest_Map_Pojo.Add_Place;
import rest_Map_Pojo.GetPlace;
import rest_Map_Pojo.Location;

public class SpecBuilderTest

{
	@Test

	public void google_Location()

	{
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		Add_Place p = new Add_Place();
		p.setName("Sapc Academy");
		p.setPhone_number("(+91) 8073048760");
		p.setAddress("44, Ksrtc layout, Austria");
		p.setWebsite("http://google.com");
		p.setLanguage("India-IN");
		p.setAccuracy(50);

		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);

		List<String> myList = new ArrayList<String>();
		myList.add("Reliance footprint");
		myList.add("shop");
		p.setTypes(myList);

		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();

		ResponseSpecification resSpec = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();

		RequestSpecification ress = given().spec(req).body(p);

		Response response = ress.when().post("/maps/api/place/add/json").then().spec(resSpec)
				.body("scope", equalTo("APP")).header("server", "Apache/2.4.52 (Ubuntu)").extract().response();

		GetPlace gp = response.as(GetPlace.class);
		String plcaeId = gp.getPlace_id();
		System.out.println(gp.getPlace_id());

		String apc = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", plcaeId).when().log()
				.all().get("maps/api/place/get/json").then().log().all().statusCode(200).extract().response()
				.asString();

		System.out.println(apc);

	}
}
