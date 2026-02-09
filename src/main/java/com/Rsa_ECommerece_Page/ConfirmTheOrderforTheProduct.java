package com.Rsa_ECommerece_Page;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.Utils.ObjectClass;
import com.Utils.AppConstants.status;

public class ConfirmTheOrderforTheProduct

{

	By products = By.cssSelector(".mb-3");
	By clkOnAddToCart = By.cssSelector(".card-body button:last-of-type");
	By clkOnCartPage = By.xpath("//button[@routerlink='/dashboard/cart']");
	By cartPageProducts = By.cssSelector(".cartSection h3");
	By clkOnCheckOut = By.xpath("//button[normalize-space()='Checkout']");
	By selCountry = By.xpath("//input[@placeholder='Select Country']");
	By clkOnPlaceOrder = By.xpath("//a[normalize-space()='Place Order']");
	By orederMsg = By.cssSelector(".hero-primary");

	String productName = "ZARA COAT 3";

	public static String generateRandomString()

	{
		String alphabets = "1234567890";
		Random random = new Random();
		int strlength = 3; // specifies generated string should be 3 characters long
		StringBuilder sb = new StringBuilder();// StringBuilder is used to efficiently append characters

		for (int i = 0; i < strlength; i++)

		{
			int index = random.nextInt(alphabets.length()); // Get a random index
			char randomChar = alphabets.charAt(index); // Extract character
			sb.append(randomChar);// Append to the string
		}

		String randomString = sb.toString();// convert StringBuilder to string and return
		System.out.println("Random String is: " + randomString);
		return randomString;
	}

	public void submitTheOrder() throws InterruptedException

	{
		ObjectClass.getCommonActions().verifyElementVisibility(products, Duration.ofSeconds(10));
		
		List<WebElement> lop = ObjectClass.getCommonActions().findElements(products);

		ObjectClass.getReports().report(status.PASS, "Verify that all the products are visible on the Home Page",
				"All the products are visible on the Home Page successfully", "yes");

		WebElement prod = lop.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);

		prod.findElement(clkOnAddToCart).click();

		ObjectClass.getCommonActions().verifyElementVisibility(By.cssSelector("#toast-container"),
				Duration.ofSeconds(10));

		ObjectClass.getCommonActions().verifyElementInVisibility(By.cssSelector(".ng-animating"),
				Duration.ofSeconds(10));

		ObjectClass.getCommonActions().click(clkOnCartPage);

		ObjectClass.getCommonActions().verifyElementVisibility(clkOnCartPage, Duration.ofSeconds(10));

		ObjectClass.getReports().report(status.PASS, "Verify that the product can be added to the cart.",
				"Product added to cart verified successfully.", "yes");

		List<WebElement> cartProducts = ObjectClass.getCommonActions().findElements(cartPageProducts);

		Boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));

		Assert.assertTrue(match);

		ObjectClass.getCommonActions().click(clkOnCheckOut);

		// Using Actions
		WebElement countryName = ObjectClass.getCommonActions().findElement(selCountry);
		ObjectClass.getCommonActions().enterTextHere(countryName, "India");
		ObjectClass.getCommonActions().verifyElementVisibility(
				By.xpath("//section[@class='ta-results list-group ng-star-inserted']"), Duration.ofSeconds(10));
		ObjectClass.getCommonActions().click(By.xpath("(//button[contains(@type,'button')])[2]"));

		// Using for each
		/*
		 * ObjectClass.getCommonActions().enterText(selCountry, "Ind");
		 * ObjectClass.getCommonActions().verifyElementVisibility(
		 * By.xpath("//section[@class='ta-results list-group ng-star-inserted']"),
		 * Duration.ofSeconds(10)); List<WebElement> options =
		 * ObjectClass.getCommonActions()
		 * .findElements(By.cssSelector(".ta-item.list-group-item.ng-star-inserted"));
		 * 
		 * for (WebElement opt : options)
		 * 
		 * { if (opt.getText().equalsIgnoreCase("India"))
		 * 
		 * { opt.click(); break; } }
		 */

		ObjectClass.getReports().report(status.PASS, "Verify that the product checkout page.",
				"Product checkout page  verified successfully.", "yes");
		ObjectClass.getCommonActions().click(clkOnPlaceOrder);

		String confirmMsg = ObjectClass.getCommonActions().getText(orederMsg);
		String expectedMsg = "THANKYOU FOR THE ORDER.";
		// Assert.assertEquals(confirmMsg, expectedMsg);
		
		Assert.assertTrue(confirmMsg.equalsIgnoreCase(expectedMsg));
		ObjectClass.getReports().report(status.PASS, "Verify Confirm the order for the product.",
				"Confirm the order for the product verified successfully.", "yes");

	}
}
