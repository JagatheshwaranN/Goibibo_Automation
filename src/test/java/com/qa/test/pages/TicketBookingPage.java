package com.qa.test.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qa.test.base.TestBase;
import com.qa.test.logger.LoggerHelper;

/**
 * 
 * @author Jaga
 *
 */
public class TicketBookingPage extends HomePage {

	private static Logger log = LoggerHelper.getLogger(TicketBookingPage.class);

	private By priceSort = By.xpath("//div[@class='SortOptionsstyles__SortOption-tji0t1-3 ivjAsX']");
	private By priceList = By.xpath("(//div[contains(@class,'srp-card-uistyles__Price-sc-3flq99-17')])[1]");
	private By bookButton = By.xpath(
			"(//div[contains(@class,'srp-card-uistyles__CardRight')]//button[contains(@class,'srp-card-uistyles__BookButton')])[1]");

	public String getTicketBookingPageTitle() {
		return getPageTitle();
	}

	public WebElement getPriceSort() {
		return getElement(priceSort);
	}

	public WebElement getPriceList() {
		return getElement(priceList);
	}

	public WebElement getBookButton() {
		return getElement(bookButton);
	}

	public void verifyTicketBookingTitle() {
		String title = getTicketBookingPageTitle();
		Assert.assertEquals(title, TestBase.getTestData("ticket.booking.page.title"));
	}

	public void bookTicket() {
		int price = 0;
		int lowestPrice = 0;
		try {
			log.info("Ticket booking execution start");
			TestBase.driver.navigate().refresh();
			getPriceList().isDisplayed();
			price = Integer.parseInt(getPriceList().getText().toString().substring(0).replaceAll(",", ""));
			lowestPrice = getLowestPrice();
			if (price == lowestPrice) {
				getBookButton().click();
			}
			log.info("Ticket booking execution end");
		} catch (Exception ex) {
			log.info("Error occured while book ticket" + "\n" + ex);
			Assert.assertFalse(true);
		}
	}

	public int getLowestPrice() {
		List<Integer> priceBucket = new ArrayList<Integer>();
		String price = null;
		int priceInNum = 0;
		try {
			String priceList = "(//div[contains(@class,'srp-card-uistyles__Price-sc-3flq99-17')])";
			int size = TestBase.driver.findElements(By.xpath(priceList)).size();
			for (int i = 1; i <= size; i++) {
				price = TestBase.driver.findElement(By.xpath(priceList + "[" + i + "]")).getText();
				price = price.substring(0);
				price = price.replaceAll(",", "");
				priceInNum = Integer.parseInt(price);
				priceBucket.add(priceInNum);
			}
		} catch (Exception ex) {
			log.info("Error occured while get price list" + "\n" + ex);
			Assert.assertFalse(true);
		}
		Collections.sort(priceBucket);
		int lowestPrice = priceBucket.get(0);
		return lowestPrice;
	}

}
