package RsaECommerece_Page;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import utils.ObjectClass;
import utils.AppConstants.status;

public class ErrorValidation

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

	public void errorValidationOfEcomerceApp() throws InterruptedException

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
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase("ZARA COAT 33"));
		Assert.assertFalse(match);
		//Assert.assertTrue(match);
		
	ObjectClass.getReports().report(status.PASS, "Check if the added item is identical to the original selection.","The added product does not match verified successfully.", "yes");

	}
}
