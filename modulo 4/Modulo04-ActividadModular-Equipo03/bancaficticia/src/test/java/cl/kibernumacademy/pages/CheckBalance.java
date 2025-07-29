package cl.kibernumacademy.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckBalance {
    private WebDriver driver;
    private WebDriverWait wait;

    
    private By balanceAmount = By.id("balance-info");
    private By backMenu = By.id("back-menu");
    private By userName = By.id("user-name");
    

    public CheckBalance(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getBalance() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(balanceAmount));
        return driver.findElement(balanceAmount).getText();
    }

     public String balanceMount() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(balanceAmount)).isDisplayed();
        return driver.findElement(balanceAmount).getText();
    }

    public void clickBackMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(backMenu));
        driver.findElement(backMenu).click();
    }

    public boolean isBackMenuVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(userName)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
