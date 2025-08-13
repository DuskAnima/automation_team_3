package cl.academy.clinica.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
  protected static WebDriver driver;
  private static WebDriverWait wait;

  public static void initDriver() {
    if (driver != null) driver.quit();
    WebDriverManager.firefoxdriver().setup();
    driver = new FirefoxDriver();
    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  }

  public static void navigateTo(String url) {
    driver.get(url);
  }

  public static void closeBrowser() {
    if (driver != null) {
      driver.quit();
      driver = null;
      wait = null;
    }
  }

  protected WebElement find(String type, String locator) {
    By by;
    switch (type.toLowerCase()) {
      case "id":
        by = By.id(locator);
        break;
      case "css":
        by = By.cssSelector(locator);
        break;
      case "xpath":
        by = By.xpath(locator);
        break;
      case "class":
        by = By.className(locator);
        break;
      case "name":
        by = By.name(locator);
        break;
      case "tag":
        by = By.tagName(locator);
        break;
      case "linktext":
        by = By.linkText(locator);
        break;
      default:
        throw new IllegalArgumentException("Tipo de locator no soportado: " + type);
    }
    return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
  }

  public void writte(String type, String locator, String textToWritte) {
    find(type, locator).clear(); // Limpia campo de texto
    find(type, locator).sendKeys(textToWritte); // Escribe el texto declarado
  }

  public void clickElement(String type, String locator) {
    find(type, locator).click();
  }

}
