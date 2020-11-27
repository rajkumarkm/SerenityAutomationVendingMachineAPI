package uk.co.machine.runner;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features",
                glue = "uk.co.machine.runner",
                tags = "@VendingMachine")
public class TestSuite {

}
