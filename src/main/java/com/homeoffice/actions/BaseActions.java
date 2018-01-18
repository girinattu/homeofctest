package com.homeoffice.actions;

import com.homeoffice.browser.BrowserFactory;
import com.homeoffice.model.Result;
import com.homeoffice.page.VehicleCheck;
import com.homeoffice.page.VehicleConfirm;
import com.homeoffice.service.FileInfoService;
import com.homeoffice.service.VehicleService;
import com.homeoffice.util.ResultFilesUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by giridharvasudevan on 18/01/2018.
 */
public class BaseActions {
	// All the actions on all the pages can do here.
	// Kind of a helper class - Each action class will be for one page
	// Data transactions across pages can be done by a static service
	// not implemented here as there was no need but have done it a lot on prev projects
	// to transfer data between step definitions

	protected static List <Result> results = new ArrayList <>();
	protected WebDriver driver;
	protected WebDriverWait webDriverWait;
	protected VehicleCheck vehicleCheck = new VehicleCheck();
	protected VehicleService vehicleService = new VehicleService();
	protected VehicleConfirm vehicleConfirm = new VehicleConfirm();
	protected FileInfoService fileInfoService = new FileInfoService();
	protected ResultFilesUtil resultFilesUtil = new ResultFilesUtil();
	protected Result resultOfTheRun = new Result();

	public BaseActions() {
		driver = BrowserFactory.getWebDriver();
		webDriverWait = new WebDriverWait(driver, 5);
		resultFilesUtil.createDirs();
	}

}

