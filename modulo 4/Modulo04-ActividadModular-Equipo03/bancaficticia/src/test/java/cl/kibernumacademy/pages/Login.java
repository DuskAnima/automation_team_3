package cl.kibernumacademy.pages;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {
    
    private WebDriver driver;
    private WebDriverWait wait;
    private By usernameField = By.id("username") ;
    private By passwordField = By.id("password");
    private By loginButton = By.className("btn-primary"); 
    private By loginSuccessMessage = By.id("user-name");
    private By loginFailedMessage = By.className("alert");

    public Login(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        driver.findElement(usernameField).clear();
        driver.findElement(usernameField).sendKeys(username);
    }       

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);

    }   

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        driver.findElement(loginButton).click();
    } 
    
    public void loginAs(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public boolean isLoginSuccessful() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(loginSuccessMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isLoginFailed() {
        try {
             return wait.until(ExpectedConditions.visibilityOfElementLocated(loginFailedMessage)).isDisplayed();
        } catch (Exception e) {
             return false;
        }
    }

}
