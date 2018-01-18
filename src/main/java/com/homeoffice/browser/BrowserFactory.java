package com.homeoffice.browser;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by giridharvasudevan on 18/01/2018.
 */
public class BrowserFactory {
	private static final Logger logger = LoggerFactory.getLogger(BrowserFactory.class.getName());

	private static WebDriver createSafariDriver() {
		return new SafariDriver();
	}

	private static WebDriver createChromeDriver(boolean headless) {
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


}
