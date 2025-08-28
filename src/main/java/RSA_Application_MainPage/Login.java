package RSA_Application_MainPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utils.AppConstants;
import utils.ObjectClass;
import utils.AppConstants.status;

public class Login

{
	// RSA Login
	By userName = By.xpath("//input[@id='email']");
	By pwd = By.xpath("//input[@id='password']");
	By clkOnLogin = By.xpath("//input[@name='commit']");

	// RSA Logout
	By moveToUser = By.xpath("//button[@id='radix-:Rdqkafkq:']/span");
	By clkOnLogout = By.xpath("//a[normalize-space()='Log out']");

	By signInBtn = By.xpath("//input[@id='email']");
	By sig = By.xpath("//a[@title='Sign In']");
	By nam = By.xpath("//input[@name='email']");
	By welbilt = By.xpath("//input[@class='btn btn-info idpButton-customizable']");
	By nam1 = By.xpath("//input[@id='userNameInput'] ");
	By pass = By.xpath("//input[@id='passwordInput'] ");
	By si = By.xpath("//span[@id='submitButton'] ");
	By log = By.xpath("//button[@class='btn btn-secondary signOutBtn']");
	By out = By.xpath("//a[@class='btn btn-secondary signOutYesBtn']");
	By grid = By.xpath("//div[@class='innerWrapperTable'][1]");
	By userProfile = By.xpath("//a[@class='nav-link navUser']");

	public final String env = (System.getProperty("environment") == null)
			? ObjectClass.getCommonActions().properties.getProperty("environment").toUpperCase()
			: System.getProperty("environment").toUpperCase();

	public void RSASignIn()

	{

		ObjectClass.getReports().setTestcaseName("Login to Rahulshetty academy poratl - " + env);
		ObjectClass.getReports().setTestDescription("");
		ObjectClass.getReports().startTestCase();

		try

		{

			// ObjectClass.getCommonActions().click(sig);
			// ObjectClass.getCommonActions().enterText(nam,
			// ApplicationConstants.valueOf(env + "_USERNAME").getValue());
			// ObjectClass.getCommonActions().click(signInBtn);
			// ObjectClass.getCommonActions().click(welbilt);

			ObjectClass.getCommonActions().enterText(userName,
					AppConstants.valueOf(env + "_USERNAME").getValue());
			ObjectClass.getCommonActions().enterText(pwd, AppConstants.valueOf(env + "_PASSWORD").getValue());
			ObjectClass.getCommonActions().click(clkOnLogin);
			ObjectClass.getCommonActions().waitForSpinner();

			ObjectClass.getReports().report(status.PASS, "Login to RSA Academy",
					"Logged in RSA learning portal successfully", "yes");
			// authToken =
			// ObjectClass.getCommonActions().getValueFromCookie("Access-Token");
			// ObjectClass.getApiUtils().getCustomerDetails();

		}

		catch (Exception e)

		{
			ObjectClass.getReports().report(status.ERROR, "Login RSA ", "Failed to login- " + e, "yes");
		}

		finally

		{
			ObjectClass.getReports().endTestcase();
		}

	}

	public void RSALogout()

	{
		ObjectClass.getReports().setTestcaseName("Logout RSA Academy application");
		ObjectClass.getReports().startTestCase();
		try

		{

			WebElement moveToProfileIcon = ObjectClass.getCommonActions().findElement(moveToUser);
			ObjectClass.getCommonActions().mouseOver(moveToProfileIcon);

			// ObjectClass.getCommonActions().click(userProfile);

			ObjectClass.getCommonActions().click(clkOnLogout);
			// ObjectClass.getCommonActions().click(out);
			ObjectClass.getReports().report(status.PASS, "Logout RSA Academy application", "Logged out successfully",
					"no");
			ObjectClass.getReports().endTestcase();
		}

		catch (Exception e)

		{
			ObjectClass.getReports().report(status.ERROR, "Logout RSA Application", "Logout failed -" + e, "yes");
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
			ObjectClass.getReports().setTestcaseName("Launch Web Application");
			ObjectClass.getReports().setTestDescription("");
			ObjectClass.getReports().startTestCase();
			ObjectClass.getCommonActions().launchApp(AppConstants.valueOf(env + "_URL").getValue());
			ObjectClass.getReports().report(status.PASS, "Lauch RSA  Application",
					"RSA Application launched Successfully", "yes");

		}

		catch (Exception e)

		{
			ObjectClass.getReports().report(status.ERROR, "Lauch RSA Application", "Failed to Launch Application",
					"yes");
		}

		finally

		{
			ObjectClass.getReports().endTestcase();
		}
	}

}
