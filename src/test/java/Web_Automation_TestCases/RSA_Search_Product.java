package Web_Automation_TestCases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utils.*;
import utils.AppConstants.status;

public class RSA_Search_Product {

	@BeforeClass
	public void setup()

	{
		ObjectClass.getCommonActions().initializeBrowser();
		ObjectClass.getReports().startReport(this.getClass().getSimpleName());
		ObjectClass.getLogin().launchApplication();
		ObjectClass.getLogin().RSASignIn();

	}

	@Test(priority = 1,enabled = false)
	public void verifySearchProduct() throws InterruptedException

	{
		ObjectClass.getReports().setTestcaseName("--Test Case Starts--");
		ObjectClass.getReports().startTestCase();

		try

		{
			ObjectClass.getSearchProducts().search_The_Product_Asper_Requirement();
			ObjectClass.getReports().report(status.PASS, "Verify search product ",
					"Search Browse product verification Pass", "yes");
		}

		catch (Exception e)

		{
			ObjectClass.getReports().report(status.FAIL, "Verify search product ",
					"Search Browse product verification Failed" + ObjectClass.getCommonActions().getStackTrace(e),
					"yes");
		}

	}

	@AfterClass
	public void tearDown()

	{
		//ObjectClass.getLogin().RSALogout();
		ObjectClass.getReports().endReport();
		ObjectClass.getCommonActions().quit();
	}
}
