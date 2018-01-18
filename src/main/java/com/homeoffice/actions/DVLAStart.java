package com.homeoffice.actions;

import com.homeoffice.page.DVLAStartPage;

/**
 * Created by giridharvasudevan on 18/01/2018.
 */
public class DVLAStart extends BaseActions {

	public DVLAStartPage dvlaStartPage = new DVLAStartPage();

	public void startVehicleCheck() {
		driver.findElement(dvlaStartPage.startButton).click();
	}
}
