package cl.kibernumacademy.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Dashboard {
    
    private WebDriver driver;
    private WebDriverWait wait;

    private By bankTransferBankButton = By.id("btn-transfer");
    private By bankCheckBalanceButton = By.id("btn-balance");
    private By logOut = By.id("btn-logout");
    private By login = By.id("login-section");
    

    public Dashboard(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickBankTransfer() {
        wait.until(ExpectedConditions.elementToBeClickable(bankTransferBankButton));
        driver.findElement(bankTransferBankButton).click();
    }

    public void clickCheckBalance() {
        wait.until(ExpectedConditions.elementToBeClickable(bankCheckBalanceButton));
        driver.findElement(bankCheckBalanceButton).click();
    }

    public void clickLogOut() {
        wait.until(ExpectedConditions.elementToBeClickable(logOut));
        driver.findElement(logOut).click();
    }

    public boolean isBankTransferButtonVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(bankTransferBankButton)).isDisplayed();  
    }   

    public boolean isCheckBalanceButtonVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(bankCheckBalanceButton)).isDisplayed();    
    }   

    public boolean isLogOutButtonVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(logOut)).isDisplayed();
    }   

   public boolean isLogoutSuccessful() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(login));
            return true;
        } catch (Exception e) {
            return false;
        }
   }
    

}
