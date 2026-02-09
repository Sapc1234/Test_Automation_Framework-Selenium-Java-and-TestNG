package com.RestAssured_Basics;

import org.testng.Assert;

import com.Utils.API_Payloads;

import io.restassured.path.json.JsonPath;

public class SumValidation {

	public static void main(String[] args)

	{
		JsonPath jp = new JsonPath(API_Payloads.coursePrice());

		int sum = 0;
		int count = jp.getInt("courses.size()");
		// 6. Verify if Sum of all Course prices matches with Purchase Amount

		for (int i = 0; i < count; i++)

		{
			int priceOfTheCourse = jp.getInt("courses[" + i + "].price");
			int noOfCopies = jp.getInt("courses[" + i + "].copies");
			int amount = priceOfTheCourse * noOfCopies;
			System.out.println(amount);
			sum = sum + amount;

		}
		System.out.println("Sum of all Course prices :" + sum);

		int actualPurchaseTotalAmt = jp.getInt("dashboard.purchaseAmount");

		Assert.assertEquals(actualPurchaseTotalAmt, sum);
	}

}
