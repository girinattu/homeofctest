package com.homeoffice.model;

/**
 * Created by giridharvasudevan on 18/01/2018.
 */
public class Vehicle {
	private String regNumber;

	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	private String make;
	private String model;

}
