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
    private By userName = By.id("user-name");
    

    public CheckBalance(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    public boolean isBalanceVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(balanceAmount)).isDisplayed();
    }


     public int getBalanceAmount() {
         wait.until(ExpectedConditions.visibilityOfElementLocated(balanceAmount));
         String balanceText = driver.findElement(balanceAmount).getText();
         String numeric = balanceText.replaceAll("[^0-9]", "");
         return Integer.parseInt(numeric);
    }

    public boolean isBackMenuVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(userName)).isDisplayed();
    }
}
