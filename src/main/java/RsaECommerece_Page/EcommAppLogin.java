package RsaECommerece_Page;

import java.time.Duration;

import org.openqa.selenium.By;

import utils.AppConstants;
import utils.ObjectClass;
import utils.AppConstants.status;

public class EcommAppLogin

{
	// Ecomm Login
	By userEmail = By.cssSelector("#userEmail");
	By userPwd = By.xpath("//input[@id='userPassword']");
	By clkOnLogin = By.xpath("//input[@id='login']");

	// Ecomm Logout
	By moveToUser = By.xpath("//button[normalize-space()='Sign Out']");
	By clkOnLogout = By.xpath("//button[normalize-space()='Sign Out']");

	public final String env = (System.getProperty("environment") == null)
			? ObjectClass.getCommonActions().properties.getProperty("environment").toUpperCase()
			: System.getProperty("environment").toUpperCase();

	public void EcommAppSignIn()

	{

		ObjectClass.getReports().setTestcaseName("Login to RSA E-commerce poratl - " + env);
		ObjectClass.getReports().setTestDescription("");
		ObjectClass.getReports().startTestCase();

		try

		{
			//ObjectClass.getCommonActions().waitForPageLoad();
			ObjectClass.getCommonActions().enterText(userEmail,AppConstants.valueOf(env + "_UserName").getValue());
			ObjectClass.getCommonActions().enterText(userPwd,AppConstants.valueOf(env + "_Password").getValue());
			ObjectClass.getCommonActions().click(clkOnLogin);

			ObjectClass.getCommonActions().verifyElementVisibility(By.cssSelector("#toast-container"),
					Duration.ofSeconds(10));
			ObjectClass.getReports().report(status.PASS, "Verify login to the RSA E-commerce application.",
					"Successfully logged in to the RSA E-commerce application.", "yes");

		}

		catch (Exception e)

		{
			ObjectClass.getReports().report(status.ERROR, "Verify login to the RSA E-commerce application. ",
					"Login to the E-commerce application was unsuccessful -" + e, "yes");
		}

		finally

		{
			ObjectClass.getReports().endTestcase();
		}

	}

	public void EcommAppLogOut()

	{
		ObjectClass.getReports().setTestcaseName("Verify log out of the RSA E-commerce application.");
		ObjectClass.getReports().startTestCase();
		try

		{

			// WebElement moveToSignOut =
			// ObjectClass.getCommonActions().findElement(moveToUser);
			// ObjectClass.getCommonActions().mouseOver(moveToSignOut);
			ObjectClass.getCommonActions().click(clkOnLogout);
			ObjectClass.getCommonActions().waitForPageLoad();
			ObjectClass.getReports().report(status.PASS,
					"Verify that the user can successfully log out of the RSA E-commerce application.",
					"RSA E-commerce application logged out successfully", "yes");
			ObjectClass.getReports().endTestcase();
		}

		catch (Exception e)

		{
			ObjectClass.getReports().report(status.ERROR,
					"Verify that the user can successfully log out of the RSA E-commerce application.",
					"RSA E-commerce application logged out failed -" + e, "yes");
		}

		finally

		{
			ObjectClass.getReports().endTestcase();
		}
	}

	public void launchApplication()

	{
		try

		{
			ObjectClass.getReports().setTestcaseName("Launch RSA E-commerce web application ");
			ObjectClass.getReports().setTestDescription("");
			ObjectClass.getReports().startTestCase();
			ObjectClass.getCommonActions().launchApp(AppConstants.valueOf(env + "_URL").getValue());
			//Thread.sleep(5000);
			ObjectClass.getReports().report(status.PASS, "Launch RSA E-commerce web application",
					"RSA E-commerce application launched Successfully", "yes");

		}

		catch (Exception e)

		{
			ObjectClass.getReports().report(status.ERROR, "Launch RSA E-commerce web application",
					"Failed to Lauch RSA E-commerce application", "yes");
		}

		finally

		{
			ObjectClass.getReports().endTestcase();
		}
	}

}
