package cl.academy.transferencias.pages;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TransferPage {
  protected static WebDriver driver;
  public static WebDriverWait wait;

  public static void initDriver() {
    if (driver != null) driver.quit();
    WebDriverManager.firefoxdriver().setup();
    driver = new FirefoxDriver();
    /* 
    WebDriverManager.chromedriver().setup();
    ChromeOptions chromeOptions = new ChromeOptions();
    driver = new ChromeDriver(chromeOptions);
    */
    wait = new WebDriverWait(driver, Duration.ofSeconds(4));
  }
  
  public void navigateToBank() {
    driver.get("https://web-actividad-4.netlify.app/");
  }

  public static void closeBrowser() {
  if (driver != null) {
    driver.quit();
    driver = null;
    wait = null;
    }
  }

  // Localizadores de transferencia
  private By amountField = By.id("monto");
  private By accountNumberField = By.id("cuenta");
  private By buttonTransfer = By.xpath("//button[@type='submit' and contains(., 'Enviar transferencia')]");
  private By resultMessage = By.id("resultado-transferencia");

  // Localizadores de datos de usuario
  private By nameField = getUserDataFieldsXpath("nombre");
  private By lastnameField = getUserDataFieldsXpath("apellido");
  private By emailField = getUserDataFieldsXpath("email");
  private By ageField = getUserDataFieldsXpath("edad");
  private By dataPrinterButton = By.id("btn-imprimir-datos");

  public void enterAmount(double amount) {
    driver.findElement(amountField).clear();
    driver.findElement(amountField).sendKeys(String.valueOf(amount));
  }

  public void enterAccountNumber(String account) {
    driver.findElement(accountNumberField).clear();
    driver.findElement(accountNumberField).sendKeys(account);
  }

  public void clickButtonTransfer() {
    driver.findElement(buttonTransfer).click();
  }

  public String getResultMessage() {
    WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(resultMessage));
    return message.getText().trim();
  }

  public void enterName(String name) {
    driver.findElement(nameField).clear();
    driver.findElement(nameField).sendKeys(name);
  }

  public void enterLasname(String lastname) {
    driver.findElement(lastnameField).clear();
    driver.findElement(lastnameField).sendKeys(lastname);
  }

  public void enterEmail(String email) {
    driver.findElement(emailField).clear();
    driver.findElement(emailField).sendKeys(email);
  } 

  public void enterAge(String age) {
    driver.findElement(ageField).clear();
    driver.findElement(ageField).sendKeys(age);
  }

  public void clickPrintData() {
    WebElement dataPrinter = wait.until(ExpectedConditions.elementToBeClickable(dataPrinterButton));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", dataPrinter);
    dataPrinter.click();
  }

  // Buscar elementos de la tabla de datos de usuario bajo un patrón xpath
  private By getUserDataFieldsXpath(String tag) { 
    return By.xpath("//td[contains(normalize-space(), '" + tag + "')]/following-sibling::td[1]/input");
  } 

}



 
