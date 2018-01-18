package com.homeoffice.model;

/**
 * Created by giridharvasudevan on 18/01/2018.
 */
public class Result {
	private Vehicle vehicle;
	private boolean isMakeCorrect;
	private boolean isColourCorrect;
	private String errorMessage;

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public boolean isMakeCorrect() {
		return isMakeCorrect;
	}

	public void setMakeCorrect(boolean makeCorrect) {
		isMakeCorrect = makeCorrect;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	public boolean isColourCorrect() {
		return isColourCorrect;
	}

	public void setColourCorrect(boolean colourCorrect) {
		isColourCorrect = colourCorrect;
	}

}
