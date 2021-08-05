package com.qa.test.common;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.qa.test.base.TestBase;
import com.qa.test.logger.LoggerHelper;

/**
 * 
 * @author Jaga
 *
 */
public class ReusableFunction {

	private static Logger log = LoggerHelper.getLogger(ReusableFunction.class);

	public String getPageTitle() {
		return TestBase.driver.getTitle();
	}

	public String getPageHeader(By locator) {
		return getElement(locator).getText();

	}

	public WebElement getElement(By locator) {
		WebElement element = null;
		try {
			waitForElementPresent(locator);
			element = TestBase.driver.findElement(locator);
		} catch (Exception ex) {
			log.info("Error occured while creation of element : " + locator.toString() + "\n" + ex);
			Assert.assertFalse(true);
		}
		return element;
	}

	public List<WebElement> getElements(By locator) {
		List<WebElement> elements = null;
		try {
			waitForElementPresent(locator);
			elements = TestBase.driver.findElements(locator);
		} catch (Exception ex) {
			log.info("Error occured while creation of elements : " + locator.toString() + "\n" + ex);
			Assert.assertFalse(true);
		}
		return elements;
	}

	public void waitForElementPresent(By locator) {
		try {
			TestBase.wait = new WebDriverWait(TestBase.driver, 30);
			TestBase.wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		} catch (Exception ex) {
			log.info("Error occured while wait for element : " + locator.toString() + "\n" + ex);
			Assert.assertFalse(true);
		}
	}

	public void waitForElementVisible(By locator) {
		try {
			TestBase.wait = new WebDriverWait(TestBase.driver, 30);
			TestBase.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (Exception ex) {
			log.info("Error occured while wait for element : " + locator.toString() + "\n" + ex);
			Assert.assertFalse(true);
		}
	}

	public void waitForPageTitle(String title) {
		try {
			TestBase.wait = new WebDriverWait(TestBase.driver, 30);
			TestBase.wait.until(ExpectedConditions.titleContains(title));
		} catch (Exception ex) {
			log.info("Error occured while wait for page title : " + title + "\n" + ex);
			Assert.assertFalse(true);
		}
	}

	public void selectByValue(WebElement element, String value) {
		try {
			Select select = new Select(element);
			select.selectByValue(value);
			log.info("The value " + value + " is selected");
		} catch (Exception ex) {
			log.info("Error occured while select option from dropdown" + "\n" + ex);
			Assert.assertFalse(true);
		}
	}

	public void selectByIndex(WebElement element, int index) {
		try {
			Select select = new Select(element);
			select.selectByIndex(index);
			log.info("The value at index " + index + " is selected");
		} catch (Exception ex) {
			log.info("Error occured while select option from dropdown" + "\n" + ex);
			Assert.assertFalse(true);
		}
	}

	public void selectByVisibleText(WebElement element, String visibleText) {
		try {
			Select select = new Select(element);
			select.selectByVisibleText(visibleText);
			log.info("The visible text " + visibleText + " is selected");
		} catch (Exception ex) {
			log.info("Error occured while select option from dropdown" + "\n" + ex);
			Assert.assertFalse(true);
		}
	}

}
