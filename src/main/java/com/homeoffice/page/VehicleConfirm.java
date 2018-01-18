package com.homeoffice.page;

import org.openqa.selenium.By;

/**
 * Created by giridharvasudevan on 18/01/2018.
 */
public class VehicleConfirm {

	public By regNumber = By.cssSelector("span.reg-mark");

	// First one is the make, second is the colour from the list of elements
	public By makeColour = By.cssSelector("span strong");

	public By vehicleNotFoundError = By.cssSelector("h3.heading-large");
}
