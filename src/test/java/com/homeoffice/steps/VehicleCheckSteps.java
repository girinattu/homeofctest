package com.homeoffice.steps;

import com.homeoffice.actions.CheckVehicle;
import com.homeoffice.actions.DVLAStart;
import com.homeoffice.actions.PrintResults;
import com.homeoffice.browser.BrowserFactory;
import com.homeoffice.util.Config;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.List;

/**
 * Created by giridharvasudevan on 18/01/2018.
 */
public class VehicleCheckSteps {

	public WebDriver driver;
	public CheckVehicle checkVehicle = new CheckVehicle();
	public DVLAStart dvlaStart = new DVLAStart();
	public PrintResults printResults = new PrintResults();

	@Given("^I navigate to the dvla page$")
	public void navigateToPage() throws Exception {
		// Hardcoded but can be removed to put it all on a UTIL Config and read from there
		driver = BrowserFactory.getWebDriver();
		driver.get(Config.getConfigProperty("base.url", "src/test/resources/config.properties"));
	}


	@And("^start the vehicle check$")
	public void startVehicleCheck() throws Exception {
		dvlaStart.startVehicleCheck();
	}

	@Then("^verify all the vehicles in '(.+)' files from the list of files$")
	public void verifyVechiles(String fileTypes) {
		List <String> fileExtns = Arrays.asList(fileTypes.split(","));
		checkVehicle.checkRegNumber(fileExtns);
	}

	@Then("^print out the results to a file$")
	public void printResult() {
		printResults.printResultToFile();
	}


}
