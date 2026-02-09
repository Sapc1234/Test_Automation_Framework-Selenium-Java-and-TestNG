package com.Web_Automation_TestCases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.Utils.ObjectClass;
import com.Utils.Rerun;
import com.Utils.AppConstants.status;

public class Ecomm_ProdErrorValidationTest

{

	@BeforeClass
	public void setup()

	{
		ObjectClass.getCommonActions().initializeBrowser();
		ObjectClass.getReports().startReport(this.getClass().getSimpleName());
		ObjectClass.getEcommAppLogin().launchApplication();
		ObjectClass.getEcommAppLogin().EcommAppSignIn();

	}

	@Test(priority = 1, retryAnalyzer = Rerun.class)
	public void verifySubmitOrderTest() throws InterruptedException

	{
		ObjectClass.getReports().setTestcaseName("Product Error validation Test Case Starts");
		ObjectClass.getReports().startTestCase();

		try

		{
			ObjectClass.getErrorValidation().errorValidationOfEcomerceApp();
			;

		}

		catch (Exception e)

		{
			ObjectClass.getReports().report(status.FAIL,
					"	Check if the added item is identical to the original selection. ",
					"Check if the added item is identical to the original selection verification Failed"
							+ ObjectClass.getCommonActions().getStackTrace(e),
					"yes");
		}

		ObjectClass.getReports().endTestcase();

	}

	@AfterClass
	public void tearDown()

	{
		ObjectClass.getEcommAppLogin().EcommAppLogOut();
		ObjectClass.getReports().endReport();
		ObjectClass.getCommonActions().quit();
	}
}
