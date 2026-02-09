package com.Utils;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverFactory

{

	private WebDriverFactory()

	{

	}

	private static WebDriverFactory instance = new WebDriverFactory();

	public static WebDriverFactory getInstance()

	{
		return instance;
	}

	ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>()

	{
		@Override
		protected WebDriver initialValue()

		{
			// If condition is true 2nd argument will execute
			// If condition is false 3rd argument will execute

			String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
					: ObjectClass.getCommonActions().properties.getProperty("browser").toLowerCase();
			switch (browserName)

			{

			case "chrome":

				// ChromeDriver will be automatically downloaded your system based on browser
				// version
				WebDriverManager.chromedriver().setup();

				// Set the path to ChromeDriver (replace with your actual path)
				// System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+
				// "\\src\\main\\resources\\chromedriver.exe");

				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
				chromeOptions.setAcceptInsecureCerts(true);

				if (ObjectClass.getCommonActions().properties.getProperty("--headless=new").equalsIgnoreCase("yes"))

					return new ChromeDriver(chromeOptions);

				else

					return new ChromeDriver(chromeOptions);

			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				return new FirefoxDriver();
			case "edge":
				WebDriverManager.edgedriver().setup();
				return new EdgeDriver();
			default:
				return null;
			}
		}
	};

	public WebDriver getDriver()

	{
		return driver.get();
	}

	public void killInstance()

	{
		driver.remove();
	}
}
