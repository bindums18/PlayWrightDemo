package testRunners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/1.feature",
        glue = {"stepDefinitions"},
        tags = "@amazon",
        plugin = {"pretty", "html:target/html-reports.html",
                            "json:target/json-reports.json"},
        monochrome = true
)

public class TestRunner {
}


