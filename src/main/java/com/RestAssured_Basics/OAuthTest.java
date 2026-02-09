package com.RestAssured_Basics;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.Rest_Courses_Pojo.Api;
import com.Rest_Courses_Pojo.GetCourse;
import com.Rest_Courses_Pojo.WebAutomation;
import com.Utils.API_ReuseableMethods;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OAuthTest

{

	@Test
	public void authoraizationServer()

	{

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		String response = given().log().all()
				.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").formParam("grant_type", "client_credentials")
				.formParam("scope", "trust").when().log().all().post("oauthapi/oauth2/resourceOwner/token").then().log()
				.all().statusCode(200).extract().response().asString();

		JsonPath jp = API_ReuseableMethods.rawToJson(response);
		String accessToken = jp.getString("access_token");
		System.out.println(accessToken);

		// String r2 = given().log().all().queryParams("access_token",
		// accessToken).when().get("oauthapi/getCourseDetails").asString();
		// System.out.println(r2);

		GetCourse get_Response = given().queryParams("access_token", accessToken).when().log().all()
				.get("oauthapi/getCourseDetails").as(GetCourse.class);
		System.out.println(get_Response.getLinkedIn());
		System.out.println(get_Response.getInstructor());

		System.out.println(get_Response.getCourses().getApi().get(1).getCourseTitle());

		List<Api> apiCourses = get_Response.getCourses().getApi();

		for (int i = 0; i < apiCourses.size(); i++)

		{
			if (apiCourses.get(i).getCourseTitle().equalsIgnoreCase("Rest Assured Automation using Java"))

			{
				System.out.println(apiCourses.get(i).getPrice());
			}
		}

		String[] ct = { "Selenium Webdriver Java", "Cypress", "Protractor" };

		ArrayList<String> a = new ArrayList<String>();
		// Get all the course title of web automation

		List<WebAutomation> webCourses = get_Response.getCourses().getWebAutomation();

		for (int j = 0; j < webCourses.size(); j++)

		{
			System.out.println(webCourses.get(j).getCourseTitle());
			a.add(webCourses.get(j).getCourseTitle());
		}

		List<String> et = Arrays.asList(ct);
		Assert.assertTrue(a.equals(et));

	}
}
