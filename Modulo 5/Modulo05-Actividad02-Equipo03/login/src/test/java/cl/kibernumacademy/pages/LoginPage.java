package cl.kibernumacademy.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cl.kibernumacademy.utils.SnapshotUtil;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginPage {

  // Setup
  protected static WebDriver driver;
  public static WebDriverWait wait;

  public static void initDriver() {
    if (driver != null) driver.quit();
    WebDriverManager.chromedriver().setup();
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("--headless");
    driver = new ChromeDriver(chromeOptions);
    wait = new WebDriverWait(driver, Duration.ofSeconds(3));
  }
  
  public void navigateToLogin() {
    driver.get("https://bco-selenium.netlify.app/");
  }
  
  public static void closeBrowser() {
    if (driver != null) {
      driver.quit();
      driver = null;
      wait = null;
    }
  }
    
  private By usernameField = By.id("username");
  private By passwordField = By.id("password");
  private By loginMessage = By.id("login-message");
  private By loginButton = By.cssSelector("button[type='submit']");
  private By welcomeMessage = By.className("alert");

  public void enterUsername(String username) {
    driver.findElement(usernameField).sendKeys(username);
  }

  public void enterPassword(String password) {
    driver.findElement(passwordField).sendKeys(password);
  }

  public void clickLoginButton() {
    driver.findElement(loginButton).click();
  }

  public String getWelcomeMessage() {
    return driver.findElement(welcomeMessage).getText();
  }

  public String getLoginMessage() {
    WebElement message = driver.findElement(loginMessage);
    return message.getText();
  }
  
  public void login(String username, String password) {
    enterUsername(username);
    enterPassword(password);
    clickLoginButton();
  }

  public void tomarSnapshot(String nombre) {
    SnapshotUtil.takeASnapshot(nombre, driver);
  }
}
