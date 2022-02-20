package runners;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/Features",glue = {"stepdefinitions", "utils"},
monochrome = true,
        plugin = {"pretty", "html:target/Cucumber-Reports"}
)
public class CucumberTestRunner {
}
