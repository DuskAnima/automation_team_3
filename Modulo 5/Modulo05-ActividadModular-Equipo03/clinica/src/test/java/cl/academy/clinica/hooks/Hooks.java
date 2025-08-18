package cl.academy.clinica.hooks;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

  @Before
  public void setUp() {
    DriverHolder.initDriver();
  }

  @After 
  public void tearDown(Scenario scenario) {
    if(scenario.isFailed()) {
      var driver = DriverHolder.getDriver();
      byte[] shot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
      scenario.attach(shot, "image/png", scenario.getName());
    }

    DriverHolder.quitDriver();
  }
}
