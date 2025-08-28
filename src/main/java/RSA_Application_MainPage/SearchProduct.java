package RSA_Application_MainPage;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;

import utils.ObjectClass;
import utils.AppConstants.status;

public class SearchProduct

{

	
	By clkOnBp = By.xpath("//a[normalize-space()='Browse products']");
	By clkOnSearchProd = By.xpath("//input[@id='heap_product-search']");

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

	public void search_The_Product_Asper_Requirement() throws InterruptedException

	{
		//ObjectClass.getCommonActions().waitForPageLoad();
		//ObjectClass.getCommonActions().navigetToBrowseProd();
		//ObjectClass.getCommonActions().click(clkOnBp);
		ObjectClass.getCommonActions().waitForPageLoad();
		ObjectClass.getCommonActions().click(clkOnSearchProd);
		ObjectClass.getCommonActions().waitForPageLoad();
		
		By pdName = By.xpath("//h2[normalize-space()='All-Access Membership']");
		ObjectClass.getCommonActions().enterText(clkOnSearchProd, "All-Access Membership");
		if (ObjectClass.getCommonActions().verifyElementVisibility(pdName,Duration.ofSeconds(30)))

		{
						
			ObjectClass.getCommonActions().waitForPageLoad();
			ObjectClass.getReports().report(status.PASS, "Verify product Search verification  ",
					"Prodcut search varification  Successfully", "yes");
		}

		else

		{

			ObjectClass.getReports().report(status.FAIL, "Verify Product Search verification ",
					" Product  search varification Failed", "yes");
		}
	}

}
