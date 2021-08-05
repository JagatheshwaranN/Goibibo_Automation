package com.qa.test.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.qa.test.logger.LoggerHelper;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

/**
 * 
 * @author Jaga
 *
 */

public class TestBase {

	public static WebDriver driver;
	public static WebDriverWait wait;
	public static Properties properties;
	private static File file;
	private static FileInputStream fileInputStream;
	private static String propertyFilePath = "//src//main//resources//configurations//";
	private static String testConfigFile = "TestConfig.properties";

	private static Logger log = LoggerHelper.getLogger(TestBase.class);

	public WebDriver invokeBrowser() throws Exception {
		DesiredCapabilities capabilities = null;
		ChromeOptions chromeoptions = null;
		try {
			if ("Chrome".equalsIgnoreCase(getTestData("browser.chrome"))) {

				chromeoptions = new ChromeOptions();
				chromeoptions.addArguments("start-maximized");
				chromeoptions.addArguments("disable-extensions");
				capabilities = DesiredCapabilities.chrome();
				capabilities.setAcceptInsecureCerts(true);
				capabilities.setCapability(ChromeOptions.CAPABILITY, chromeoptions);
				chromeoptions.merge(capabilities);
				System.setProperty("webdriver.chrome.driver", getTestData("chrome.driver"));
				log.info("*******************************_Running " + getTestData("browser.chrome") + " Browser");
				driver = new ChromeDriver(chromeoptions);
				log.info("Driver Information : " + driver);
			}
		} catch (Exception ex) {
			log.info("Error occured while launch of browser" + "\n" + ex);
			Assert.assertFalse(true);
		}
		return driver;
	}

	@Before
	public void before() throws Exception {
		try {
			loadPropertyFile();
			invokeBrowser();
		} catch (Exception ex) {
			log.info("Error occured while initialization of test execution" + "\n" + ex);
			Assert.assertFalse(true);
		}
	}

	@After
	public void after(Scenario scenario) {
		try {
			if (scenario.isFailed()) {
				try {
					log.info("FAILED ***** : " + scenario.getName());
					final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
					scenario.attach(screenshot, "image/jpeg", "");
				} catch (Exception ex) {
					log.info("Error occured while capture screenshot" + "\n" + ex);
				}
			} else {
				try {
					log.info("PASSED ***** : " + scenario.getName());
					final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
					scenario.attach(screenshot, "image/jpeg", "");
				} catch (Exception ex) {
					log.info("Error occured while capture screenshot" + "\n" + ex);
				}
			}
			if (driver == null) {
				return;
			}
			driver.quit();
			driver = null;
		} catch (Exception ex) {
			log.info("Error occured while close of the browser" + "\n" + ex);
			Assert.assertFalse(true);
		}
	}

	public void loadPropertyFile() throws IOException {
		try {
			properties = new Properties();
			file = new File(System.getProperty("user.dir") + propertyFilePath + testConfigFile);
			try {
				fileInputStream = new FileInputStream(file);
			} catch (FileNotFoundException ex) {
				log.info("======================== [ Property file " + testConfigFile
						+ " not found ] ========================");
				ex.printStackTrace();
			}
			try {
				properties.load(fileInputStream);
				log.info("======================== [ Property File Load Successful ] ========================");
			} catch (IOException ex) {
				log.info("Error occured while load property file" + "\n" + ex);
				Assert.assertFalse(true);
			}

		} finally {
			fileInputStream.close();
		}
	}

	public static String getTestData(String property) {
		String dataFromPropFile = null;
		try {
			dataFromPropFile = properties.getProperty(property).trim();
		} catch (Exception ex) {
			log.info("Error occured while retrieve data from property file" + "\n" + ex);
			Assert.assertFalse(true);
		}
		return dataFromPropFile;
	}

	public static void launchApp() {
		try {
			String url = getTestData("app.url");
			TestBase.driver.get(url);
		} catch (Exception ex) {
			log.info("Error occured while launch of Goibibo application" + "\n" + ex);
			Assert.assertFalse(true);
		}
	}

}
