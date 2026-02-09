package com.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;

/**
 * @author aswin.l
 *
 */
public class CommonActions

{

	public final Properties properties = new Properties();
	public Map<String, Map<String, String>> unitDetails = new HashMap<>();
	String currentDir = "user.dir";
	WebDriverWait wait;
	LocalDate today = LocalDate.now();
	Random random = new Random();

	// RSA

	By navigateToBrowseProduct = By.xpath("(//a[normalize-space()='Browse products'])[1]");

	public void loadPropertyFile()

	{
		try (FileInputStream fs = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\resources\\GlobalData.properties"))

		{
			properties.load(fs);
		}

		catch (Exception e)

		{
			e.printStackTrace();
		}
	}

	public void initializeBrowser()

	{
		try

		{
			loadPropertyFile();
			WebDriverFactory.getInstance().getDriver().manage().window().maximize();
		}

		catch (Exception e)

		{
			e.printStackTrace();
		}
	}

	public String getPageSource()

	{
		return WebDriverFactory.getInstance().getDriver().getPageSource();
	}

	public void launchApp(String url)

	{
		WebDriverFactory.getInstance().getDriver().get(url);
	}

	public String getUrl()

	{
		return WebDriverFactory.getInstance().getDriver().getCurrentUrl();
	}

	@SuppressWarnings("deprecation")
	public WebElement findElement(By byElement)

	{
		try

		{
			Wait<WebDriver> fluWait = new FluentWait<WebDriver>(WebDriverFactory.getInstance().getDriver())
					.withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofMillis(500))
					.ignoring(NoSuchElementException.class);

			WebElement element = fluWait.until(ExpectedConditions.presenceOfElementLocated(byElement));
			((JavascriptExecutor) WebDriverFactory.getInstance().getDriver())
					.executeScript("arguments[0].style.border='2px solid red'", element);
			Thread.sleep(500);
			((JavascriptExecutor) WebDriverFactory.getInstance().getDriver())
					.executeScript("arguments[0].style.border=''", element);
			return element;
		}

		catch (Exception e)

		{
			return null;
		}
	}

	/**
	 * Finds and returns all webelements matching the given locator value and wait
	 * for 6sec if not element matches before returning empty list
	 * 
	 * @param byElement
	 * @return List
	 */
	public List<WebElement> findElements(By byElement)

	{
		List<WebElement> result = null;
		try

		{
			for (int i = 0; i < 12; i++)

			{
				if (WebDriverFactory.getInstance().getDriver().findElements(byElement).isEmpty()
						|| (WebDriverFactory.getInstance().getDriver().findElements(byElement).size() == 0))
					Thread.sleep(500);
				else
					break;
			}
			result = WebDriverFactory.getInstance().getDriver().findElements(byElement);
			return result;
		}

		catch (Exception e)

		{
			return result;
		}
	}

	/**
	 * Clear existing value in input field if any, and enter given text value in the
	 * input field
	 * 
	 * @param byElement
	 * @param inputText
	 */
	public void enterText(By byElement, String inputText)

	{
		WebElement ele = findElement(byElement);
		ele.clear();
		while (!ele.getAttribute("value").equals(""))

		{
			ele.sendKeys(Keys.BACK_SPACE);
		}
		ele.sendKeys(inputText);
	}

	public void enterText(WebElement element, String inputText)

	{
		element.clear();
		element.sendKeys(inputText);
	}

	/**
	 * Performs mover-hover of the given web element
	 * 
	 * @param webElement
	 */
	public void mouseOver(WebElement webElement)

	{
		Actions act = new Actions(WebDriverFactory.getInstance().getDriver());
		act.moveToElement(webElement).perform();
	}

	/**
	 * Performs click operation and if element not visible, retries with scroll to
	 * the element and perform click
	 * 
	 * @param byElement
	 */
	public void click(By byElement)

	{
		WebElement webEle = null;

		try

		{
			webEle = findElement(byElement);
			scrollToViewBottom(webEle);
			scrollToViewTop(webEle);
			webEle.click();
			waitForSpinner();
		}

		catch (Exception e)

		{
			((JavascriptExecutor) WebDriverFactory.getInstance().getDriver()).executeScript("arguments[0].click();",
					webEle);
			// waitForSpinner();
		}
	}

	public void click(WebElement element)

	{
		try

		{
			element.click();
			waitForSpinner();
		}

		catch (Exception e)

		{
			((JavascriptExecutor) WebDriverFactory.getInstance().getDriver()).executeScript("arguments[0].click();",
					element);
			waitForSpinner();
		}
	}

	public void doubleClick(By byElement)

	{
		Actions act = new Actions(WebDriverFactory.getInstance().getDriver());
		act.doubleClick(findElement(byElement)).perform();
		waitForSpinner();
	}

	public String switchToChildWindow()

	{
		String parentWindow = WebDriverFactory.getInstance().getDriver().getWindowHandle();
		for (String eachWindow : WebDriverFactory.getInstance().getDriver().getWindowHandles())

		{
			if (!eachWindow.equals(parentWindow))
				switchToWindow(eachWindow);
		}
		return parentWindow;
	}

	public void switchToWindow(String windowTitle)

	{
		WebDriverFactory.getInstance().getDriver().switchTo().window(windowTitle);
	}

	public String getWindowTitle()

	{
		return WebDriverFactory.getInstance().getDriver().getTitle();
	}

	/**
	 * Get the value of given cookie
	 * 
	 * @param cookieName Name of the cookie
	 * @return Cookie value or null if no cookie with the given name is present
	 */
	public String getValueFromCookie(String cookieName)

	{
		return WebDriverFactory.getInstance().getDriver().manage().getCookieNamed(cookieName).getValue();
	}

	public void scrollToViewBottom(WebElement webEle) throws InterruptedException

	{
		((JavascriptExecutor) WebDriverFactory.getInstance().getDriver())
				.executeScript("arguments[0].scrollIntoView(false);", webEle);
		Thread.sleep(1000);
	}

	public void scrollToViewTop(WebElement webEle) throws InterruptedException

	{
		((JavascriptExecutor) WebDriverFactory.getInstance().getDriver())
				.executeScript("arguments[0].scrollIntoView(true);", webEle);
		Thread.sleep(1000);
	}

	public void scrollToPageBottom() throws InterruptedException

	{
		((JavascriptExecutor) WebDriverFactory.getInstance().getDriver())
				.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(1000);
	}

	/**
	 * To verify the element matching the given locator value is present in DOM.
	 * 
	 * @param byElement
	 * @param sec       The timeout in seconds when an expectation is called
	 * @return Web element and returns null value if element not found for the given
	 *         locator value
	 */

	public String getAttributeValue(String xpath, String attributeValue)

	{
		return findElement(By.xpath(xpath)).getAttribute(attributeValue);
	}

	public String getAttributeValue(WebElement element, String attributeValue)

	{
		return element.getAttribute(attributeValue).trim();
	}

	public String getAttributeValue(By byele, String attributeValue)

	{
		return findElement(byele).getAttribute(attributeValue);
	}

	public void waitForPageLoad()

	{
		while (true)

		{
			String pageStatus = (String) ((JavascriptExecutor) WebDriverFactory.getInstance().getDriver())
					.executeScript("return document.readyState");
			if ("complete".equals(pageStatus))

			{
				break;
			}
		}
	}

	/**
	 * @return epoch value of current time
	 */
	@SuppressWarnings("deprecation")
	public int getEpochTime()

	{
		try

		{
			Date currentDate = Calendar.getInstance().getTime();
			SimpleDateFormat crunchifyFormat = new SimpleDateFormat("MMM dd yyyy HH:mm:ss");
			if (currentDate.getMinutes() == 00)

			{
				currentDate.setMinutes(01);
			}
			String currentTime = crunchifyFormat.format(currentDate);
			Date date = crunchifyFormat.parse(currentTime);
			long epochTime = date.getTime();
			return (int) (epochTime / 1000L);
		}

		catch (Exception e)

		{
			return 0;
		}
	}

	/**
	 * @param epochtime
	 * @return hour of given epoch time
	 */
	public int getHourFromEpocTime(int epochtime)

	{
		return Instant.ofEpochSecond(epochtime).atZone(ZoneId.systemDefault()).getHour() + 1;
	}

	/**
	 * To get the hour with maximum cook
	 * 
	 * @param graph xpath
	 * @return Peak hour
	 */

	/**
	 * Convert given hour to standard time format
	 * 
	 * @param hour
	 * @return time in - hh:mm AM/PM format
	 */
	public String convertTimeFormat(Integer hour)

	{
		try

		{
			String time = Integer.toString(hour) + ":00";
			final SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
			final Date dateObj = sdf.parse(time);
			return new SimpleDateFormat("K:mm a").format(dateObj).equals("0:00 PM") ? "12:00 PM"
					: new SimpleDateFormat("K:mm a").format(dateObj);
		}

		catch (Exception e)

		{
			return null;
		}
	}

	/**
	 * Get the visible text of the given element
	 * 
	 * @param byElement
	 * @return Visible text
	 */
	public String getText(By byElement)

	{
		try

		{
			WebElement ele = findElement(byElement);
			verifyElementVisibility(byElement, Duration.ofSeconds(30));
			if (ele.getText().equals("") || ele.getText().isEmpty())
				return ele.getAttribute("innerHTML").trim();
			else
				return ele.getText();
		}

		catch (Exception e)

		{
			return "";
		}
	}

	public String getText(WebElement element)

	{
		try

		{
			if (element.getText().equals("") || element.getText().isEmpty())

			{
				if (!((JavascriptExecutor) WebDriverFactory.getInstance().getDriver())
						.executeScript("return $(arguments[0]).text();", element).toString().equals(""))
					return ((JavascriptExecutor) WebDriverFactory.getInstance().getDriver())
							.executeScript("return $(arguments[0]).text();", element).toString();
				else
					return element.getAttribute("innerHTML").trim();
			}

			else
				return element.getText();
		}

		catch (Exception e1)

		{
			return element.getAttribute("innerHTML").trim();
		}
	}

	public String getText(String xpath)

	{
		if (verifyElementLocated(By.xpath(xpath), Duration.ofSeconds(2)) != null)
			return getText(By.xpath(xpath));
		else
			return null;
	}

	public String getColorValue(By byElement, String property)

	{
		return Color.fromString(findElement(byElement).getCssValue(property)).asHex();
	}

	public String getScreenShot(String imageName, String timeInFormat, String takeScreenshot, String reportName)

	{
		String screenshot = null;
		SimpleDateFormat formater = new SimpleDateFormat("hh_mm_ss");
		try

		{
			if (takeScreenshot.equals("yes"))

			{
				String time = formater.format(Calendar.getInstance().getTime());
				String screenShotLocation = System.getProperty(currentDir) + "/reportingOutput/" + reportName + "_"
						+ timeInFormat + "/Screenshots/";
				File theDir = new File(screenShotLocation);
				theDir.mkdir();
				Shutterbug.shootPage(WebDriverFactory.getInstance().getDriver(), Capture.FULL, true)
						.withName(imageName + time).save(screenShotLocation);
				screenshot = "Screenshots/" + imageName + time + ".png";
			}
		}

		catch (Exception e)

		{
			return null;
		}
		return screenshot;
	}

	public void navigateBack()

	{
		WebDriverFactory.getInstance().getDriver().navigate().back();
		waitForSpinner();
	}

	/**
	 * @param webele
	 * @return Selected drop down option text value
	 */
	public String getDropdownSelectedOption(WebElement webele)

	{
		Select sel = new Select(webele);
		return sel.getFirstSelectedOption().getText();
	}

	public void selectValueFromDropdown(WebElement webele, String value)

	{
		try

		{
			Select sel = new Select(webele);
			for (int i = 0; i < 5; i++)

			{
				if (sel.getOptions().size() > 2)
					break;
				else
					Thread.sleep(1000);
			}
			sel.selectByVisibleText(value);
		}

		catch (Exception e)

		{
			e.printStackTrace();
		}
	}

	public void selectValueByIndexFromDropdown(WebElement webele, int index)

	{
		try

		{
			Select sel = new Select(webele);
			for (int i = 0; i < 5; i++)

			{
				if (sel.getOptions().size() > 2)
					break;
				else
					Thread.sleep(1000);
			}
			sel.selectByIndex(index);
		}

		catch (Exception e)

		{
			e.printStackTrace();
		}
	}

	public List<WebElement> getDropdownOptions(WebElement webele)

	{
		Select sel = new Select(webele);
		return sel.getOptions();
	}

	/**
	 * Wait for spinner to disappear
	 */
	public void waitForSpinner()

	{
		try

		{
			// verifyElementVisibility(By.xpath("(//div[@class='loadingProgressIn'])[2]"),
			// Duration.ofSeconds(3));
			// verifyElementInVisibility(By.xpath("(//div[@class='loadingProgressIn'])[2]"),
			// Duration.ofSeconds(30));
			waitForPageLoad();
		}

		catch (Exception e)

		{

		}
	}

	/**
	 * @param epochTime
	 * @param format
	 * @return Given epoch time to given format
	 */
	public String epochToDateConvertion(int epochTime, String format)

	{
		try

		{
			int epoch = (epochTime != 0) ? epochTime : getEpochTime();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
			return Instant.ofEpochSecond(epoch).atZone(ZoneId.systemDefault()).toLocalDateTime().format(formatter);
		}

		catch (Exception e)

		{
			return null;
		}
	}

	public void refresh()

	{
		WebDriverFactory.getInstance().getDriver().navigate().refresh();
		waitForSpinner();
	}

	public List<Integer> generatePastEpochTime()

	{
		List<Integer> pastEpoch = new ArrayList<>();
		try

		{
			pastEpoch.add(getPastEpochTime(0)); // Current Day
			pastEpoch.add(getPastEpochTime(1)); // Prior Day

			// Prior Week
			int weekStart = today.getDayOfWeek().getValue();
			int weekEnd = weekStart + 6;
			pastEpoch.add(getPastEpochTime(generateRandomNumber(weekStart, weekEnd)));

			// Prior Month
			int monthStart = today.getDayOfMonth();
			int monthEnd = monthStart + 27;
			pastEpoch.add(getPastEpochTime(generateRandomNumber(monthStart, monthEnd)));

			// Prior Quarter
			int currentMonth = today.getMonthValue();
			int currentYear = today.getYear();
			int currentDay = today.getDayOfMonth();
			int quarter = (currentMonth + 2) / 3;
			int quarterStart = 0;

			int i = (quarter * 3) - 2;
			for (int j = i; j < currentMonth; j++)

			{
				YearMonth yearMonthObject = YearMonth.of(currentYear, j);
				quarterStart += yearMonthObject.lengthOfMonth();
			}
			quarterStart += (currentDay + 1);
			int quarterEnd = quarterStart + 88;
			pastEpoch.add(getPastEpochTime(generateRandomNumber(quarterStart, quarterEnd)));

			// Prior Year
			int yearStart = 0;
			for (int z = 1; z < currentMonth; z++)

			{
				YearMonth yearMonthObject = YearMonth.of(currentYear, z);
				yearStart += yearMonthObject.lengthOfMonth();
			}
			yearStart += currentDay;
			int yearEnd = currentDay + 364;
			pastEpoch.add(getPastEpochTime(generateRandomNumber(yearStart, yearEnd)));

			return pastEpoch;
		}

		catch (Exception e)

		{
			return pastEpoch;
		}
	}

	public Date getDate(int epoch) throws ParseException

	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
		String date = Instant.ofEpochSecond(epoch).atZone(ZoneId.systemDefault()).toLocalDateTime().format(formatter);
		return new SimpleDateFormat("dd/MMM/yyyy").parse(date);
	}

	public int getPastEpochTime(int days)

	{
		try {
			Date today = Calendar.getInstance().getTime();
			SimpleDateFormat crunchifyFormat = new SimpleDateFormat("MMM dd yyyy HH:mm:ss");
			if (today.getMinutes() == 00)

			{
				today.setMinutes(01);
			}
			String currentTime = crunchifyFormat.format(today);
			Date date = crunchifyFormat.parse(currentTime);
			long epochTime = (date.getTime());
			int time = (int) (epochTime / 1000L);
			return time - (days * 24 * 60 * 60);
		}

		catch (Exception e)

		{
			return 0;
		}
	}

	public int generateRandomNumber(int min, int max)

	{
		return random.nextInt((max - min) + 1) + min;
	}

	public String generateDaysHoursMinutes(int seconds)

	{
		int day = (int) TimeUnit.SECONDS.toDays(seconds);
		long hours = TimeUnit.SECONDS.toHours(seconds) - (day * 24);
		long minute = TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds) * 60);
		String dayValue = (day <= 1) ? "day" : "days";
		return day + dayValue + " " + hours + "h " + minute + "m";
	}

	public int convertDaysHoursMinutesToSeconds(String dayshourmin)

	{
		int seconds = 0;
		String[] time = dayshourmin.split("\\s+");
		int day = (time[0].contains("days")) ? Integer.parseInt(time[0].replace("days", ""))
				: (time[0].contains("day")) ? Integer.parseInt(time[0].replace("day", ""))
						: Integer.parseInt(time[0].replace("d", ""));
		int hour = Integer.parseInt(time[1].replace("h", ""));
		int min = Integer.parseInt(time[2].replace("m", ""));
		day = day * 60 * 60 * 24;
		hour = hour * 60 * 60;
		min = min * 60;
		seconds = day + hour + min;
		return seconds;
	}

	public String generateDecimalFormat(int number)

	{
		DecimalFormat decimalFormat = new DecimalFormat("###,###.###");
		return decimalFormat.format(number);
	}

	public void closeWindow()

	{
		WebDriverFactory.getInstance().getDriver().close();
	}

	public String getStackTrace(Exception e)

	{
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		return sw.toString();
	}

	public void quit()

	{
		WebDriverFactory.getInstance().getDriver().quit();
		WebDriverFactory.getInstance().killInstance();
	}

	public void openNewWindow(String url)

	{
		((JavascriptExecutor) WebDriverFactory.getInstance().getDriver())
				.executeScript("window.open('" + url + "','_blank');");
	}

	public void switchToFrame(By byEle)

	{
		WebDriverFactory.getInstance().getDriver().switchTo().frame(findElement(byEle));
	}

	public void switchToDefaultContent()

	{
		WebDriverFactory.getInstance().getDriver().switchTo().defaultContent();
	}

	public int getNumericValue(String str)

	{
		return Integer.parseInt(str.replaceAll("[^0-9]", ""));
	}

	public int getTotalWindow()

	{
		return WebDriverFactory.getInstance().getDriver().getWindowHandles().size();
	}

	public void relaunchApplication()

	{
		ObjectClass.getCommonActions().quit();
		WebDriverFactory.getInstance().getDriver().manage().window().maximize();
		ObjectClass.getCommonActions().launchApp(AppConstants.valueOf(ObjectClass.getLogin().env + "_URL").getValue());
	}

	public void uploadFile(By ele, String filePath)

	{
		findElement(ele).sendKeys(filePath);
	}

	public void clearInputFiled(By byElement)

	{
		WebElement ele = findElement(byElement);
		ele.clear();
		while (!ele.getAttribute("value").equals("")) {
			ele.sendKeys(Keys.BACK_SPACE);
		}
	}

	public void enterTextValue(By byElement, String inputText)

	{
		WebElement ele = findElement(byElement);
		ele.sendKeys(inputText);
	}

	public void dragAndDropUsingJs(WebElement from, WebElement to)

	{
		JavascriptExecutor js = (JavascriptExecutor) WebDriverFactory.getInstance().getDriver();
		js.executeScript("function createEvent(typeOfEvent) {\n" + "var event =document.createEvent(\"CustomEvent\");\n"
				+ "event.initCustomEvent(typeOfEvent,true, true, null);\n" + "event.dataTransfer = {\n" + "data: {},\n"
				+ "setData: function (key, value) {\n" + "this.data[key] = value;\n" + "},\n"
				+ "getData: function (key) {\n" + "return this.data[key];\n" + "}\n" + "};\n" + "return event;\n"
				+ "}\n" + "\n" + "function dispatchEvent(element, event,transferData) {\n"
				+ "if (transferData !== undefined) {\n" + "event.dataTransfer = transferData;\n" + "}\n"
				+ "if (element.dispatchEvent) {\n" + "element.dispatchEvent(event);\n"
				+ "} else if (element.fireEvent) {\n" + "element.fireEvent(\"on\" + event.type, event);\n" + "}\n"
				+ "}\n" + "\n" + "function simulateHTML5DragAndDrop(element, destination) {\n"
				+ "var dragStartEvent =createEvent('dragstart');\n" + "dispatchEvent(element, dragStartEvent);\n"
				+ "var dropEvent = createEvent('drop');\n"
				+ "dispatchEvent(destination, dropEvent,dragStartEvent.dataTransfer);\n"
				+ "var dragEndEvent = createEvent('dragend');\n"
				+ "dispatchEvent(element, dragEndEvent,dropEvent.dataTransfer);\n" + "}\n" + "\n"
				+ "var source = arguments[0];\n" + "var destination = arguments[1];\n"
				+ "simulateHTML5DragAndDrop(source,destination);", from, to);
	}

	public boolean isAlertPresent()

	{
		try

		{
			new WebDriverWait(WebDriverFactory.getInstance().getDriver(), Duration.ofSeconds(5))
					.until(ExpectedConditions.alertIsPresent());
			return true;
		}

		catch (Exception e)

		{
			return false;
		}
	}

	public String getAlertText()

	{
		return WebDriverFactory.getInstance().getDriver().switchTo().alert().getText();
	}

	public void alertAccept()

	{
		WebDriverFactory.getInstance().getDriver().switchTo().alert().accept();
	}

	public void navigetToBrowseProd()

	{
		click(navigateToBrowseProduct);
	}

	public void waitForElementToAppear(By byElement)

	{
		WebDriverWait wait = new WebDriverWait(WebDriverFactory.getInstance().getDriver(), Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(byElement));

	}

	public void waitForwebElementToAppear(WebElement byElement)

	{
		WebDriverWait wait = new WebDriverWait(WebDriverFactory.getInstance().getDriver(), Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(byElement));

	}

	public WebElement verifyElementLocated(By byElement, Duration sec)

	{
		try

		{
			wait = new WebDriverWait(WebDriverFactory.getInstance().getDriver(), sec);
			return wait.until(ExpectedConditions.presenceOfElementLocated(byElement));

		}

		catch (Exception e)

		{
			return null;
		}
	}

	/**
	 * To check an element is visible and enabled such that you can click it.
	 * 
	 * @param byElement
	 */
	public void waitForElementToBeClickable(By byElement)

	{
		wait = new WebDriverWait(WebDriverFactory.getInstance().getDriver(), Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(byElement));
	}

	/**
	 * To check an element is present on the DOM of a page and visible.
	 * 
	 * @param byElement
	 * @param sec       The timeout in seconds when an expectation is called
	 * @return true, or false if element if not present/visible on DOM
	 */
	public boolean verifyElementVisibility(By byElement, Duration sec)

	{
		try

		{
			wait = new WebDriverWait(WebDriverFactory.getInstance().getDriver(), sec);
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(byElement));
			return element.isDisplayed();
		}

		catch (Exception e)

		{
			return false;
		}
	}

	public boolean verifyElementInVisibility(By element, Duration sec)

	{
		try

		{
			wait = new WebDriverWait(WebDriverFactory.getInstance().getDriver(), sec);
			return wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
		}

		catch (Exception e)

		{
			return false;
		}
	}

	public boolean verifyExpectedElementValue(By element, String text)

	{
		try

		{
			wait = new WebDriverWait(WebDriverFactory.getInstance().getDriver(), Duration.ofSeconds(10));
			return wait.until(ExpectedConditions.textToBePresentInElementLocated(element, text));
		}

		catch (Exception e)

		{
			try

			{
				return new WebDriverWait(WebDriverFactory.getInstance().getDriver(), Duration.ofSeconds(20))
						.ignoring(StaleElementReferenceException.class)
						.until(ExpectedConditions.textToBePresentInElementLocated(element, text));
			}

			catch (Exception t)

			{
				return false;
			}
		}
	}

	public void enterTextHere(WebElement element, String inputText)

	{
		Actions act = new Actions(WebDriverFactory.getInstance().getDriver());
		act.sendKeys(element, inputText).build().perform();
	}
	
	public void selectValueFromDropDown()

	{
		Actions act = new Actions(WebDriverFactory.getInstance().getDriver());
		act.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
	}
	
	public void clearInputFields()

	{
		Actions act = new Actions(WebDriverFactory.getInstance().getDriver());
		act.sendKeys(Keys.BACK_SPACE).build().perform();
	}
	
	public void enterBulk()
	
	{
		
	}
	
	

	public void clickMultipleTimes(String elementId, int times)

	{
		for (int i = 0; i < times; i++)

		{
			WebDriverFactory.getInstance().getDriver().findElement(By.id(elementId)).click();
		}
	}

}