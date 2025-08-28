package Web_Automation_TestCases;

import java.util.HashMap;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utils.ObjectClass;
import utils.AppConstants.status;

public class Ecomm_SubmittOrderTest

{

	@BeforeClass
	public void setup()

	{
		ObjectClass.getCommonActions().initializeBrowser();
		ObjectClass.getReports().startReport(this.getClass().getSimpleName());
		ObjectClass.getEcommAppLogin().launchApplication();
		ObjectClass.getEcommAppLogin().EcommAppSignIn();

	}

	@Test(priority = 1)
	public void verifySubmitOrderTest() throws InterruptedException

	{
		ObjectClass.getReports().setTestcaseName("Submit Order Test Case Starts");
		ObjectClass.getReports().startTestCase();

		try

		{
			ObjectClass.getConfirmTheOrderForTheProduct().submitTheOrder();

		}

		catch (Exception e)

		{
			ObjectClass.getReports().report(status.FAIL, "Verify Confirm the order for the product. ",
					"Confirm the order for the product verification Failed"
							+ ObjectClass.getCommonActions().getStackTrace(e),
					"yes");
		}

		ObjectClass.getReports().endTestcase();

	}

	@Test(dependsOnMethods = "verifySubmitOrderTest")

	public void verifyOrderHistoryTest()

	{
		ObjectClass.getReports().setTestcaseName("Order history page test started");
		ObjectClass.getReports().startTestCase();

		try

		{
			ObjectClass.getOrderPage().orderPage_Verification();
		}

		catch (Exception e)

		{
			ObjectClass.getReports().report(status.FAIL, "Verify that the product ZARA COAT 3 is in order list. ",
					"Product ZARA COAT 3 is in order list verification Failed"
							+ ObjectClass.getCommonActions().getStackTrace(e),
					"yes");
		}

		ObjectClass.getReports().endTestcase();
	}

	@DataProvider

	public Object[][] getData()

	{
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", "sharanabasappapadashetty047@gmail.com");
		map.put("password", "Prime2025");
		map.put("product", "ZARA COAT 3");

		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("email", "sharanabasappapadashetty047@gmail.com");
		map1.put("password", "Prime2025");
		map1.put("product", "ZARA COAT 3");

		// Object[][] data = { { "sharanabasappapadashetty047@gmail.com", "Prime2025"
		// },{ "sharanabasappapadashetty047@gmail.com", "Prime2025" } };

		Object[][] data = { { map }, { map1 } };
		return data;
	}

	@AfterClass
	public void tearDown()

	{
		ObjectClass.getEcommAppLogin().EcommAppLogOut();
		ObjectClass.getReports().endReport();
		ObjectClass.getCommonActions().quit();
	}
}
