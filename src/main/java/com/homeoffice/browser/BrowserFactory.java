package com.homeoffice.browser;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;


/**
 * Created by giridharvasudevan on 18/01/2018.
 */
public class BrowserFactory {
	private static final Logger logger = LoggerFactory.getLogger(BrowserFactory.class.getName());

	private static WebDriver driver;

	private static WebDriver createSafariDriver() {
		return new SafariDriver();
	}

	private static WebDriver createChromeDriver() {
		ChromeDriverManager.getInstance().setup();
		ChromeOptions chromeOptions = new ChromeOptions();
		// incse we get any
		chromeOptions.addArguments("ignore-certificate-errors");
		chromeOptions.addArguments("disable-infobars");
		return new ChromeDriver(chromeOptions);

	}

	private static WebDriver createInternetExplorerDriver(){
		return new InternetExplorerDriver();
	}

	private static WebDriver createFirefoxDriver() {
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		String geckobin = System.getenv("GECKODRIVER_BIN");
		logger.info("GECKODRIVER_BIN is " + System.getenv("GECKODRIVER_BIN"));
		System.setProperty("webdriver.gecko.driver", geckobin);
		return new FirefoxDriver(capabilities);
	}

	public static WebDriver getWebDriver() {
		if (driver == null) {
			String browser = System.getProperty("browser");
			if (browser == null) {
				browser = "CHROME";
			}
			switch (browser.toUpperCase()) {
				case "CHROME":
					driver = createChromeDriver();
					break;
				case "FIREFOX":
					driver = createFirefoxDriver();
					break;
				case "INTERNETEXPLORER":
					driver = createInternetExplorerDriver();
					break;
				case "SAFARI":
					driver = createSafariDriver();
					break;
				default:
					logger.error("Browser not supported ... " + browser.toUpperCase());
			}
		}
		return driver;
	}

	public static void closeBrowser() {
		driver.close();
	}

	public static void screenshot(String filePath, String fileName) {
		File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(filePath + "/" + fileName + ".png");
		try {
			FileUtils.copyFile(scr, dest);
		} catch (IOException ee) {
			logger.error(ee.getMessage());
		}

	}
}
