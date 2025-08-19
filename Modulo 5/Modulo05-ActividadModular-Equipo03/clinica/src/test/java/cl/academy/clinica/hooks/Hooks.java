package cl.academy.clinica.hooks;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

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
    WebDriver driver = DriverHolder.getDriver();
    
    if(scenario.isFailed()) {
      try {
        byte[] shot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(shot, "image/png", scenario.getName());
      } catch (Exception e) {
        System.err.println("Error tomando screenshot: " + e.getMessage());
      }
    }
    
    if (driver != null) {
        DriverHolder.quitDriver();
    }
  }
}
