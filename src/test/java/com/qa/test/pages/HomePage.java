package com.qa.test.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qa.test.base.TestBase;
import com.qa.test.common.ReusableFunction;
import com.qa.test.logger.LoggerHelper;

/**
 * 
 * @author Jaga
 *
 */
public class HomePage extends ReusableFunction {

	private static Logger log = LoggerHelper.getLogger(HomePage.class);

	private By homePageHeader = By.xpath("//h1[text()='Domestic and International Flights']");
	private By oneWayTrip = By
			.xpath("//div[contains(@class,'fltSwitchOpt')]//span[@id='oneway' and @class='curPointFlt switchAct']");
	private By fromLocation = By.xpath("//input[@id='gosuggest_inputSrc']");
	private By fromLocationSuggestion = By.xpath(
			"//ul[@id='react-autosuggest-1']//li//div[@class='mainTxt clearfix']//span[text()='Chennai, India']");
	private By toLocation = By.xpath("//input[@id='gosuggest_inputDest']");
	private By toLocationSuggestion = By.xpath(
			"//ul[@id='react-autosuggest-1']//li//div[@class='mainTxt clearfix']//span[text()='Bengaluru, India']");
	private By depatureDate = By.xpath("//input[@id='departureCalendar']");
	private By monthTextInDatePicker = By.xpath("//div[@class='DayPicker-Caption' and @role='heading']");
	private By monthNavigatorInDatePicker = By
			.xpath("//span[@role='button' and @class='DayPicker-NavButton DayPicker-NavButton--next']");
	private By travelSelection = By.xpath("//div[@id='pax_link_common']");
	private By travelSelectionPassenger = By.xpath("//button[@id='adultPaxPlus']");
	private By travelSelectionClass = By.xpath("//select[@class='custSelect width100 whiteBg padTB5 padLR10']");
	private By searchButton = By.xpath("//button[contains(@class,'orange') and @value='Search']");

	public String getHomePageTitle() {
		return getPageTitle();
	}

	public String getHomePageHeader() {
		return getPageHeader(homePageHeader);
	}

	public WebElement getOneWayTrip() {
		return getElement(oneWayTrip);
	}

	public WebElement getFromLocation() {
		return getElement(fromLocation);
	}

	public WebElement getFromLocationSuggestion() {
		return getElement(fromLocationSuggestion);
	}

	public WebElement getToLocation() {
		return getElement(toLocation);
	}

	public WebElement getToLocationSuggestion() {
		return getElement(toLocationSuggestion);
	}

	public WebElement getDepatureDate() {
		return getElement(depatureDate);
	}

	public WebElement getMonthTextInDatePicker() {
		return getElement(monthTextInDatePicker);
	}

	public WebElement getMonthNavigatorInDatePicker() {
		return getElement(monthNavigatorInDatePicker);
	}

	public WebElement getTravelSelection() {
		return getElement(travelSelection);
	}

	public WebElement getTravelSelectionPassenger() {
		return getElement(travelSelectionPassenger);
	}

	public WebElement getTravelSelectionClass() {
		return getElement(travelSelectionClass);
	}

	public WebElement getSearchButton() {
		return getElement(searchButton);
	}

	public void verifyHomePageTitle() {
		String title = getHomePageTitle();
		Assert.assertEquals(title, TestBase.getTestData("home.page.title"));
	}

	public void enterTravelDetails(String fromLocation, String toLocation, String travelClass, String month,
			String day) {
		try {
			log.info("Home page execution start");
			getFromLocation().sendKeys(TestBase.getTestData(fromLocation));
			getFromLocationSuggestion().click();
			getToLocation().sendKeys(TestBase.getTestData(toLocation));
			getToLocationSuggestion().click();
			getDepatureDate().click();
			selectDate(TestBase.getTestData(month), TestBase.getTestData(day));
			getTravelSelection().click();
			selectByValue(getTravelSelectionClass(), TestBase.getTestData(travelClass));
			getSearchButton().click();
			log.info("Home page execution end");
		} catch (Exception ex) {
			log.info("Error occured while enter travel details" + "\n" + ex);
			Assert.assertFalse(true);
		}

	}

	public void selectDate(String month, String day) {
		String monthInDatePicker = null;
		String dateSelectionString = "//div[@class='DayPicker-Week']/div[@class='DayPicker-Day']/div[text()=";
		try {
			while (true) {
				monthInDatePicker = getMonthTextInDatePicker().getText();
				if (monthInDatePicker.equals(month)) {
					break;
				} else {
					getMonthNavigatorInDatePicker().click();
				}
			}
			TestBase.driver.findElement(By.xpath(dateSelectionString + day + "]")).click();
		} catch (Exception ex) {
			log.info("Error occured while select date from calendar" + "\n" + ex);
			Assert.assertFalse(true);
		}
	}
}
