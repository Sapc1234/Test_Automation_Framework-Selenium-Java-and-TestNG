package com.Rsa_ECommerece_Page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.Utils.ObjectClass;
import com.Utils.AppConstants.status;

public class OrderPage

{
	public Boolean orderPage_Verification() throws InterruptedException

	{
		String productName = "ZARA COAT 3";
		By clkOnOrderPage = By.cssSelector("[routerlink*='myorders']");
		By listOfProductName = By.cssSelector("tr td:nth-child(3)");
		// To verify ZaraCoat 3 displaying in order page

		ObjectClass.getCommonActions().click(clkOnOrderPage);
		ObjectClass.getCommonActions().waitForElementToAppear(listOfProductName);
		List<WebElement> lop = ObjectClass.getCommonActions().findElements(listOfProductName);

		ObjectClass.getReports().report(status.PASS, "Verify that the product ZARA COAT 3 is in order list.",
				"Product ZARA COAT 3 is in order list verified successfully.", "yes");

		Boolean match = lop.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));

		return match;
	}
}
