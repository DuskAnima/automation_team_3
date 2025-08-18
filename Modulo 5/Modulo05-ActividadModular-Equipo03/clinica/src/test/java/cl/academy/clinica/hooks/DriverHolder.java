package cl.academy.clinica.hooks;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverHolder {

  private static WebDriver driver;
  private static WebDriverWait wait;

  public static void initDriver() {
    if (driver != null) driver.quit();
    WebDriverManager.firefoxdriver().setup();

    driver = new FirefoxDriver();
    driver.manage().window().maximize();
    wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

  public static WebDriver getDriver() {
    if (driver == null) {
      throw new IllegalStateException("WebDriver no inicializado");
    }
    return driver;
  }

  public static WebDriverWait getWait() {
    if (wait == null) {
      throw new IllegalStateException("WebDriverWait no inicializado");
    }
    return wait;
  }

  public static void quitDriver() {
    if (driver != null) {
      driver.quit();
      driver = null;
      wait = null;
    }
  }
}
