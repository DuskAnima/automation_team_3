package cl.academy.clinica.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cl.academy.clinica.hooks.DriverHolder;

public class BasePage {
  protected WebDriver driver;
  protected WebDriverWait wait;

  public BasePage() {
    this.driver = DriverHolder.getDriver();
    this.wait = DriverHolder.getWait();
  }
  
  public void navigateTo(String url) {
    driver.get(url);
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
      wait.until(ExpectedConditions.elementToBeClickable(find(type, locator))).click();
    }
}
