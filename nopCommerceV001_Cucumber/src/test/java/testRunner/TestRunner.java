package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		//features=".//Features//Customers.feature",
		features={".//Features/"}, //-- for all features to run
	//	features={".//Features//Login.feature",".//Features//Customers.feature"}, // different feature then separte by {}
		glue={"stepDefiniiton_1"},
		dryRun=false,
		monochrome=true,
		plugin= {
				"pretty", 
				"html:test-output/dsalgo/HTMLdsalgo.html",
				"junit:test-output/XML/XMLCucumber.xml",
				"json:test-output/JSON/JSONCucumber.json",
				 "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
				},
	//	tags= "@sanity and @regression"
	tags= "@smoke or @regression and not @sanity"
	//tags= "@sanity or @regression"
	//	tags= "@sanity"
		)





public class TestRunner {

}
