package com.Utils;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Reports {

	String testCaseName;
	String testDescription;
	String category;
	String authors = "Sapc Automation Team";
	String reportLocation;
	SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyy_hh_mm_ss.SSS");
	String timeInFormat = formater.format(Calendar.getInstance().getTime());
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
	private static ThreadLocal<ExtentReports> extentReport = new ThreadLocal<>();
	private static ThreadLocal<String> reportName = new ThreadLocal<>();

	public void startReport(String reportNme) 
	
	{
		reportName.set(reportNme);
		reportLocation = System.getProperty("user.dir") + "/reportingOutput/" + reportNme + "_" + timeInFormat
				+ "/Test Results_" + timeInFormat + ".html";
		extentReport.set(new ExtentReports(reportLocation));
		extentReport.get().loadConfig(new File(System.getProperty("user.dir") + "/src/test/resources/extent-config.xml"));
	}

	public void report(AppConstants.status status, String name, String details, String takeScreenshot) 
	
	{
		if (status.name().equals("PASS"))

		{
			String screen = null;
			screen = ObjectClass.getCommonActions().getScreenShot(name, timeInFormat, takeScreenshot, reportName.get());
			if (takeScreenshot.equals("yes")) 
			
			{
				details = details + extentTest.get().addScreenCapture(screen);
			}
			extentTest.get().log(LogStatus.PASS, name, details);
		}
		if (status.name().equals("FAIL"))
		
		{
			System.out.println(details);
			String screen = null;
			screen = ObjectClass.getCommonActions().getScreenShot(name, timeInFormat, takeScreenshot, reportName.get());
			if (takeScreenshot.equals("yes")) 
			
			{
				details = details + extentTest.get().addScreenCapture(screen);
			}
			extentTest.get().log(LogStatus.FAIL, name, details);
		}
		if (status.name().equals("ERROR"))

		{
			System.out.println(details);
			String screen = null;
			screen = ObjectClass.getCommonActions().getScreenShot(name, timeInFormat, takeScreenshot, reportName.get());
			if (takeScreenshot.equals("yes")) 
			
			{
				details = details + extentTest.get().addScreenCapture(screen);
			}
			extentTest.get().log(LogStatus.ERROR, name, details);
			ObjectClass.getReports().endReport();
			ObjectClass.getCommonActions().quit();
			Assert.assertTrue(false);
		}
		if (status.name().equals("INFO"))

		{
			extentTest.get().log(LogStatus.INFO, name, "");
		}
	}

	public void setTestcaseName(String name) 
	
	{
		System.out.println(name);
		testCaseName = name;
	}

	public void setTestDescription(String desc)
	
	{
		testDescription = desc;
	}

	public void startTestCase() 
	
	{
		extentTest.set(extentReport.get().startTest(testCaseName, testDescription));
		extentTest.get().assignAuthor(authors);
	}

	public void endTestcase() 
	
	{
		extentReport.get().endTest(extentTest.get());
	}

	public void endReport() 
	
	{
		
		extentReport.get().flush();
		try 
		
		{
			Desktop.getDesktop().browse(new File(reportLocation).toURI());
		} 
		
		catch (Exception e)

		{
			e.printStackTrace();
		}
		extentTest.remove();
		reportName.remove();
		extentReport.remove();
	}
}
