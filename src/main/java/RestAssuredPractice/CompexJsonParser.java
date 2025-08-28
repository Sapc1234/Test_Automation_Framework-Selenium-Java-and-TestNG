package RestAssuredPractice;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class CompexJsonParser {

	public static void main(String[] args)

	{

		JsonPath jp = new JsonPath(Payload.coursePrice());

		// 1. Print No of courses returned by API
		int count = jp.getInt("courses.size()");
		System.out.println(" No of courses returned by API : " + count);

		// 2.Print Purchase Amount
		int purAmt = jp.getInt("dashboard.purchaseAmount");
		System.out.println("Total Purchase Amount :" + purAmt);

		// 3. Print Title of the first course
		String tof = jp.get("courses[0].title");
		System.out.println("Title of the first course :" + tof);

		// 4. Print All course titles and their respective Prices
		for (int i = 0; i < count; i++)

		{
			String coursesTitles = jp.get("courses[" + i + "].title");
			System.out.println("All course titles : " + coursesTitles);

			int coursePrice = jp.getInt("courses[" + i + "].price");
			System.out.println(coursePrice);

			int courseCopies = jp.getInt("courses[" + i + "].copies");
			System.out.println(courseCopies);

			// 5. Print no of copies sold by RPA Course

			if (coursesTitles.equalsIgnoreCase("RPA"))

			{
				int RpaCopies = jp.getInt("courses[" + i + "].copies");
				System.out.println("No of copies sold by RPA Course :" + RpaCopies);

			}

		

		}

		
	}

}
