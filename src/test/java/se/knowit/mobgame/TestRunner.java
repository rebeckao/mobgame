package se.knowit.mobgame;

import cucumber.api.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources",
        glue = "se.knowit"
)
public class TestRunner {
}
