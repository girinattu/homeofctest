package com.homeoffice;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by giridharvasudevan on 18/01/2018.
 */
@RunWith(Cucumber.class)

@CucumberOptions(features = "src/test/resources/features",//path to the features
		plugin = {"html:target/cucumber-html-report", "json:target/cucumber-json-report.json"},
		glue = {"com.homeoffice.steps"})
public class testRunner {

}
