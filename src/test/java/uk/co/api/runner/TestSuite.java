package uk.co.api.runner;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/api",
                glue = "uk.co.api.runner",
                tags = "@CityBike")
public class TestSuite {

}
