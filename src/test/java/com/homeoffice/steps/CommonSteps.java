package com.homeoffice.steps;

import com.homeoffice.browser.BrowserFactory;
import cucumber.api.Scenario;
import cucumber.api.java.After;


/**
 * Created by giridharvasudevan on 18/01/2018.
 */
public class CommonSteps {

	@After
	public static void afterScenario(Scenario scenario) {
		// take screenshot
		// based on pass or fail, put it into appropriate folder
		// close browser
		closeBrowser();
	}

	public static void closeBrowser() {
		BrowserFactory.closeBrowser();
	}

}
