package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)

@CucumberOptions(features = {"Resources"},
				glue= {"GlueCode"},
				tags = {"@Login"},
				plugin = {"pretty", "json:target/cucumber.json","html:target/cucumber-report-html"},
				dryRun=false)

public class CucumberTestRunner {
	
	
}
