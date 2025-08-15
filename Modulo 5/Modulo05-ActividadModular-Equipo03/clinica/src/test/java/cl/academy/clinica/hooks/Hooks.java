package cl.academy.clinica.hooks;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
  public static WebDriver driver;
  public static WebDriverWait wait;

  @Before
  public void setUp() {
    DriverHolder.initDriver();
  }

  @After 
  public void tearDown(Scenario scenario) {
    if(scenario.isFailed()) {
      byte[] shot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
      scenario.attach(shot, "image/png", scenario.getName());
    }

    if (driver != null) {
      driver.quit();
      driver = null;
      wait = null;
    }
  }
}
