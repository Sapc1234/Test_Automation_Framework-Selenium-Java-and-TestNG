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
		System.out.println("Jira Create bug api test case started");
		RestAssured.baseURI = "https://sharanpadashetty2079-1760704015964.atlassian.net/";

		String creteResponse = given().log().all().header("Content-Type", "application/json").header("Authorization",
				"Basic c2hhcmFucGFkYXNoZXR0eTIwNzlAZ21haWwuY29tOkFUQVRUM3hGZkdGMEtNRW50QV9pN2lhdHZ2ajlCY0R4SHhzbDEwWUlUa1RrQ2tYcF9zQllYTGlKNlc3ZnhYVGhCQkcyb2NEeDBsUDRGNXNzVkxsaDF6bkxvemZ6Z183NnVYVDFLQU95T3Z6aURac0FQZHBzV1V5dGRNeVBLcjU1THhWUHViVExoVkkzVlVGblF2bWFBMk9nMGhZeUl4U3NybFprYmVfVW5WQmFNOWoxUkZ3b0U5ST1GQ0Q1OUUxNA==")
				.body(Payload.createJiraBug()).when().post("rest/api/3/issue").then().log().all().statusCode(201)
				.extract().response().asString();
		System.out.println(creteResponse);

		JsonPath js = ReuseableMethods.rawToJson(creteResponse);

		String issueId = js.get("id");

		System.out.println(issueId);

	
		System.out.println("Jira Add Attachements api test case started");
		String fillAtt = given().log().all().pathParam("key", issueId).header("Authorization",
				"Basic c2hhcmFucGFkYXNoZXR0eTIwNzlAZ21haWwuY29tOkFUQVRUM3hGZkdGMEtNRW50QV9pN2lhdHZ2ajlCY0R4SHhzbDEwWUlUa1RrQ2tYcF9zQllYTGlKNlc3ZnhYVGhCQkcyb2NEeDBsUDRGNXNzVkxsaDF6bkxvemZ6Z183NnVYVDFLQU95T3Z6aURac0FQZHBzV1V5dGRNeVBLcjU1THhWUHViVExoVkkzVlVGblF2bWFBMk9nMGhZeUl4U3NybFprYmVfVW5WQmFNOWoxUkZ3b0U5ST1GQ0Q1OUUxNA==")

				.header("X-Atlassian-Token", "no-check")
				.multiPart("file",
						new File(System.getProperty("user.dir") + "\\src\\main\\resources\\Mobile Image.jpg"))
				.post("rest/api/3/issue/{key}/attachments").then().log().all().statusCode(200).extract().response()
				.asString();
		JsonPath js1 = ReuseableMethods.rawToJson(fillAtt);
		String filePathaAme = js1.getString("filename");
		System.out.println(filePathaAme);
		String emailId = js1.getString("author.emailAddress");
		System.out.println(emailId);

		System.out.println("Jira get issue api test case started");
		String getIssueRes = given().log().all().pathParam("key", issueId).header("Authorization",
				"Basic c2hhcmFucGFkYXNoZXR0eTIwNzlAZ21haWwuY29tOkFUQVRUM3hGZkdGMEtNRW50QV9pN2lhdHZ2ajlCY0R4SHhzbDEwWUlUa1RrQ2tYcF9zQllYTGlKNlc3ZnhYVGhCQkcyb2NEeDBsUDRGNXNzVkxsaDF6bkxvemZ6Z183NnVYVDFLQU95T3Z6aURac0FQZHBzV1V5dGRNeVBLcjU1THhWUHViVExoVkkzVlVGblF2bWFBMk9nMGhZeUl4U3NybFprYmVfVW5WQmFNOWoxUkZ3b0U5ST1GQ0Q1OUUxNA==")
				.get("rest/api/3/issue/{key}").then().log().all().statusCode(200).extract().response().asString();
		JsonPath jso = ReuseableMethods.rawToJson(getIssueRes);
		String proName = jso.getString("fields.project.name");
		System.out.println(proName);

		System.out.println("Jira edit issue api test case started");
		String editIssueRes = given().log().all().header("Content-Type", "application/json").header("Authorization",
				"Basic c2hhcmFucGFkYXNoZXR0eTIwNzlAZ21haWwuY29tOkFUQVRUM3hGZkdGMEtNRW50QV9pN2lhdHZ2ajlCY0R4SHhzbDEwWUlUa1RrQ2tYcF9zQllYTGlKNlc3ZnhYVGhCQkcyb2NEeDBsUDRGNXNzVkxsaDF6bkxvemZ6Z183NnVYVDFLQU95T3Z6aURac0FQZHBzV1V5dGRNeVBLcjU1THhWUHViVExoVkkzVlVGblF2bWFBMk9nMGhZeUl4U3NybFprYmVfVW5WQmFNOWoxUkZ3b0U5ST1GQ0Q1OUUxNA==")
				.pathParam("key", issueId)
				.body("{\n" + "  \"fields\": {\n" + "    \"summary\": \"All login page buttons are not working\"\n"
						+ "  }\n" + "}")
				.when().put("rest/api/3/issue/{key}").then().log().all().statusCode(204).extract().response()
				.asString();

		System.out.println(editIssueRes);

		System.out.println("Jira delete issue api test case started");
		
		String deleteIssue = given().log().all().queryParam("deleteSubtasks", "false").pathParam("key", issueId).header("Authorization",
				"Basic c2hhcmFucGFkYXNoZXR0eTIwNzlAZ21haWwuY29tOkFUQVRUM3hGZkdGMEtNRW50QV9pN2lhdHZ2ajlCY0R4SHhzbDEwWUlUa1RrQ2tYcF9zQllYTGlKNlc3ZnhYVGhCQkcyb2NEeDBsUDRGNXNzVkxsaDF6bkxvemZ6Z183NnVYVDFLQU95T3Z6aURac0FQZHBzV1V5dGRNeVBLcjU1THhWUHViVExoVkkzVlVGblF2bWFBMk9nMGhZeUl4U3NybFprYmVfVW5WQmFNOWoxUkZ3b0U5ST1GQ0Q1OUUxNA==")
				
		.delete("rest/api/3/issue/{key}").then().log().all().statusCode(204).extract().response().asString();
		
		System.out.println(deleteIssue);

	}
}
