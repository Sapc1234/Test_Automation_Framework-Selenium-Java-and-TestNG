package RestAssuredPractice;

import org.testng.annotations.Test;

import files.Payload;
import files.ReuseableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

public class JiraTest {

	@Test

	public void create_Bug()

	{
		RestAssured.baseURI = "https://sharanpadashetty2079.atlassian.net";

		String createIssueResponse = given().log().all().header("Content-Type", "application/json").header(
				"Authorization",
				"Basic c2hhcmFucGFkYXNoZXR0eTIwNzlAZ21haWwuY29tOkFUQVRUM3hGZkdGMGw0NDJ0eWFVa3JxWDZ4UEd4ME9iTUFrMm41UHFfZmdHd2lrS2dQRzBEWWZkbDdWNTlFYTBocktXYVg4UThLQnZlM1RudUt6T2VwLWtYYktXaThFQkJfUzAzcVdUYVRGTHhrZm1OdjVTLVg3UGxibDA1OEZDRUMxS3RkdjJtZEdYbVByNjEyaG9vdDl2UlZJR1B4VGFQYUJFSUlmZ0lrZ0N4QTVJdlpYNWtZND1BNUNGODg2Qw==")
				.body(Payload.createJiraBug()).when().post("rest/api/2/issue").then().log().all().statusCode(201)
				.extract().response().asString();

		JsonPath jp = ReuseableMethods.rawToJson(createIssueResponse);
		String issueId = jp.getString("id");
		System.out.println(issueId);

		String fillAtt = given().log().all().pathParam("key", issueId).header("X-Atlassian-Token", "no-check").header(
				"Authorization",
				"Basic c2hhcmFucGFkYXNoZXR0eTIwNzlAZ21haWwuY29tOkFUQVRUM3hGZkdGMGw0NDJ0eWFVa3JxWDZ4UEd4ME9iTUFrMm41UHFfZmdHd2lrS2dQRzBEWWZkbDdWNTlFYTBocktXYVg4UThLQnZlM1RudUt6T2VwLWtYYktXaThFQkJfUzAzcVdUYVRGTHhrZm1OdjVTLVg3UGxibDA1OEZDRUMxS3RkdjJtZEdYbVByNjEyaG9vdDl2UlZJR1B4VGFQYUJFSUlmZ0lrZ0N4QTVJdlpYNWtZND1BNUNGODg2Qw==")
				.multiPart("file", new File("F:/sharanu padashetty Docs/SAP photos/backgrounds/Sapc.jpg"))
				.post("rest/api/2/issue/{key}/attachments").then().log().all().statusCode(200).extract().response()
				.asString();

		JsonPath js = ReuseableMethods.rawToJson(fillAtt);
		String filePathaAme = js.getString("filename");
		System.out.println(filePathaAme);
		String emailId = js.getString("author.emailAddress");
		System.out.println(emailId);

		String getIssueRes = given().log().all().pathParam("key", issueId).header("Authorization",
				"Basic c2hhcmFucGFkYXNoZXR0eTIwNzlAZ21haWwuY29tOkFUQVRUM3hGZkdGMGw0NDJ0eWFVa3JxWDZ4UEd4ME9iTUFrMm41UHFfZmdHd2lrS2dQRzBEWWZkbDdWNTlFYTBocktXYVg4UThLQnZlM1RudUt6T2VwLWtYYktXaThFQkJfUzAzcVdUYVRGTHhrZm1OdjVTLVg3UGxibDA1OEZDRUMxS3RkdjJtZEdYbVByNjEyaG9vdDl2UlZJR1B4VGFQYUJFSUlmZ0lrZ0N4QTVJdlpYNWtZND1BNUNGODg2Qw==")
				.when().get("rest/api/2/issue/{key}").then().log().all().statusCode(200).extract().response()
				.asString();
		JsonPath jso = ReuseableMethods.rawToJson(getIssueRes);
		String proName = jso.getString("fields.project.name");
		System.out.println(proName);

	}
}
