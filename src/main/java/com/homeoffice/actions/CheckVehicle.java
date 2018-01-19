package com.homeoffice.actions;


import com.homeoffice.browser.BrowserFactory;
import com.homeoffice.model.Result;
import com.homeoffice.model.Vehicle;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.assertTrue;


/**
 * Created by giridharvasudevan on 18/01/2018.
 */
public class CheckVehicle extends BaseActions {
	private static final Logger logger = LoggerFactory.getLogger(CheckVehicle.class.getName());


	public List <Result> checkRegNumber(List <String> fileExtns) {
		// Get all the vehicles
		List <Vehicle> vehiclesList = vehicleService.readFile(fileExtns, fileInfoService.getFileMapWithExtns());

		/** catching the assertion error.
		 *  Not the best way to do
		 *  We can do it at a top level, read the values and assert.
		 *  Or read all the values and write as a report
		 *  There are multiple ways of doing this
		 */
		for (Vehicle vehicle : vehiclesList) {
			driver.findElement(vehicleCheck.enterRegText).sendKeys(vehicle.getRegNumber());
			driver.findElement(vehicleCheck.continueButton).click();
			String makeFromDVLA = null, colourFromDVLA = null, vehicleRegFromDVLA = null, error = null;

			try {
				webDriverWait.until(ExpectedConditions.urlContains("ConfirmVehicle"));
				webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(vehicleConfirm.makeColour));
				vehicleRegFromDVLA = driver.findElement(vehicleConfirm.regNumber).getText();
				makeFromDVLA = driver.findElements(vehicleConfirm.makeColour).get(0).getText();
				colourFromDVLA = driver.findElements(vehicleConfirm.makeColour).get(1).getText();
				assertTrue("Vehicle registration number from file:" + vehicle.getRegNumber() + ".\n" +
								"Vehicle registration number from DVLA: " + vehicleRegFromDVLA,
						vehicleRegFromDVLA.replaceAll("\\s", "").equalsIgnoreCase(vehicle.getRegNumber()
								.replaceAll("\\s", "")));
				assertTrue("Vehicle manufacturer from file :" + vehicle.getMake() + ".\n" +
								"Vehicle manufacturer from DVLA: " + makeFromDVLA,
						vehicle.getMake().toUpperCase().trim().equals(makeFromDVLA.toUpperCase().trim()));
				assertTrue("Vehicle Colour from file :" + vehicle.getColour() + ".\n" +
								"Vehicle Colour from DVLA: " + colourFromDVLA,
						vehicle.getColour().toUpperCase().trim().equals(colourFromDVLA.toUpperCase().trim()));
				error = "Pass";
				BrowserFactory.screenshot("Pass_Screenshots", vehicle.getRegNumber());

			} catch (AssertionError ee) {
				error = ee.getMessage();
				logger.error(error);
				BrowserFactory.screenshot("Fail_Screenshots", vehicle.getRegNumber());
			} catch (TimeoutException ee) {
				error = driver.findElement(vehicleConfirm.vehicleNotFoundError).getText();
				colourFromDVLA = "not found";
				makeFromDVLA = "not found";
				BrowserFactory.screenshot("Fail_Screenshots", vehicle.getRegNumber());
			}
			results.add(setResult(vehicle, colourFromDVLA, makeFromDVLA, error));
			driver.navigate().back();

		}
		return results;

	}

	public Result setResult(Vehicle vehicle, String colour, String make, String error) {
		resultOfTheRun = new Result();
		resultOfTheRun.setVehicle(vehicle);
		resultOfTheRun.setErrorMessage(error);
		resultOfTheRun.setColourCorrect(vehicle.getColour().trim().equalsIgnoreCase(
				colour.trim()));
		resultOfTheRun.setMakeCorrect(vehicle.getMake().trim().equalsIgnoreCase(
				make.trim()));
		return resultOfTheRun;

	}


}
