package com.RestAssured_Basics;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.Rest_Courses_Pojo.Api;
import com.Rest_Courses_Pojo.GetCourse;
import com.Rest_Courses_Pojo.Mobile;

import io.restassured.path.json.JsonPath;

public class DesrilaizeTestTest {

	@Test

	public void oTest()

	{
		String[] webCourseTitles = { "Selenium Webdriver Java", "Cypress", "Protractor" };
		String[] apiCourseTitles = { "Rest Assured Automation using Java", "SoapUI Webservices testing" };

		String response = given()
				.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").formParam("grant_type", "client_credentials")
				.formParam("scope", "trust").when().log().all()
				.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
		System.out.println(response);

		JsonPath jsonPath = new JsonPath(response);
		String accessToken = jsonPath.getString("access_token");

		GetCourse gc = given().queryParam("access_token", accessToken).when().log().all()
				.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourse.class);
		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getInstructor());

		System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
		// System.out.println(gc.getCourses().getApi().get(1).getPrice());

		System.out.println("*******************Get the course names of Api***********************");
		List<Api> apiCourses = gc.getCourses().getApi();
		ArrayList<String> a = new ArrayList<String>();

		for (int i = 0; i < apiCourses.size(); i++)

		{

			System.out.println(apiCourses.get(i).getPrice());
			System.out.println(apiCourses.get(i).getCourseTitle());
			a.add(apiCourses.get(i).getCourseTitle());

		}

		List<String> expectedListApi = Arrays.asList(apiCourseTitles);
		Assert.assertTrue(a.equals(expectedListApi));

		System.out
				.println("*************************Get the course names of Mobile***********************************");
		List<Mobile> m = gc.getCourses().getMobile();

		for (int k = 0; k < m.size(); k++)

		{
			if (m.get(k).getCourseTitle().equalsIgnoreCase("Appium-Mobile Automation using Java"))

			{
				System.out.println(m.get(k).getCourseTitle());
				System.out.println(m.get(k).getPrice());
			}
		}
	}

}
