package com.qa.test.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qa.test.base.TestBase;
import com.qa.test.logger.LoggerHelper;

/**
 * @author Jaga
 *
 */
public class TicketDetailsPage extends TicketBookingPage {

	private static Logger log = LoggerHelper.getLogger(TicketDetailsPage.class);

	private By ticketDetailsHeader = By.xpath("//span[contains(@class,'flex1') and text()='TICKET DETAILS']");
	private By ticketDetailsFlightName = By.xpath("//div[contains(@class,'common-elementsstyles__Wid13')]//span[1]");
	private By ticketDetailsTravelClass = By.xpath("//div[contains(@class,'common-elementsstyles__Wid13')]//span[2]");
	private By ticketDetailsFromPlace = By
			.xpath("(//div[contains(@class,'common-elementsstyles__Wid31')]//span[contains(@class,'padR5')])[1]");
	private By ticketDetailsToPlace = By
			.xpath("(//div[contains(@class,'common-elementsstyles__Wid31')]//span[contains(@class,'padR5')])[2]");
	private By ticketFareSummary = By.xpath("//div[@class='padL10 padR10 padT10 BrdrBotDsh flexCol']");
	private By ticketTotalAmount = By.xpath(
			"//div[contains(@class,'fare-summarystyles__TotalAmount')]//div[@class='dF width100 padB15 justifyBetween']");

	public String getTicketDetailsPageTitle() {
		return getPageTitle();
	}

	public String getTicketDetailsHeader() {
		return getPageHeader(ticketDetailsHeader);
	}

	public WebElement getTicketDetailsFlightName() {
		return getElement(ticketDetailsFlightName);
	}

	public WebElement getTicketDetailsTravelClass() {
		return getElement(ticketDetailsTravelClass);
	}

	public WebElement getTicketDetailsFromPlace() {
		return getElement(ticketDetailsFromPlace);
	}

	public WebElement getTicketDetailsToPlace() {
		return getElement(ticketDetailsToPlace);
	}

	public WebElement getTicketFareSummary() {
		return getElement(ticketFareSummary);
	}

	public WebElement getTicketTotalAmount() {
		return getElement(ticketTotalAmount);
	}

	public void verifyTicketDetailsHeader() {
		String ticketDetailsPageHeader = getTicketDetailsHeader();
		Assert.assertEquals(ticketDetailsPageHeader, TestBase.getTestData("ticket.details.page.header"));
	}

	public void verifyTicketDetails() {
		try {
			log.info("Verify ticket details execution start");
			getTicketDetailsFlightName().isDisplayed();
			getTicketDetailsTravelClass().isDisplayed();
			getTicketDetailsFromPlace().isDisplayed();
			getTicketDetailsToPlace().isDisplayed();
			getTicketFareSummary().isDisplayed();
			getTicketTotalAmount().isDisplayed();
			log.info("Verify ticket details execution end");
		} catch (Exception ex) {
			log.info("Error occured while check ticket details" + "\n" + ex);
			Assert.assertFalse(true);
		}
	}

}
