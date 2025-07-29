package cl.kibernumacademy.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BankTransfer {
    private WebDriver driver;
    private WebDriverWait wait;

    private By destinyAccountField = By.id("destino");
    private By amountField = By.id("monto");
    private By motiveField = By.id("motivo");
    private By transferButton = By.xpath("//*[@id=\"transfer-form\"]/button[1]");
    private By transferSuccessMessage = By.className("alert-success");
    private By transferFailedMessage = By.className("alert-warning");
    private By cancelTransfer = By.id("cancel-transfer");
    private By userName = By.id("user-name");


    public BankTransfer(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterDestinyAccount(String destinyAccount) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(destinyAccountField));
        driver.findElement(destinyAccountField).clear();
        driver.findElement(destinyAccountField).sendKeys(destinyAccount);
    }
    
    public void enterAmount(String amount) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(amountField));
        driver.findElement(amountField).clear();
        driver.findElement(amountField).sendKeys(amount);
    }   

    public void enterMotive(String motive) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(motiveField));
        driver.findElement(motiveField).clear();
        driver.findElement(motiveField).sendKeys(motive);
    }

    public boolean isTransferButtonEnabled() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(transferButton)).isEnabled();  
    }   

    public void clickTransferButton() {
        wait.until(ExpectedConditions.elementToBeClickable(transferButton));
        driver.findElement(transferButton).click();
    }   

    public int getTransferAmount() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(amountField));
        String amountText = driver.findElement(amountField).getAttribute("value");
        return Integer.parseInt(amountText);
    }

    public boolean isTransferSuccessful() {
        try {
             return wait.until(ExpectedConditions.visibilityOfElementLocated(transferSuccessMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
         }
    }

    public boolean isTransferFailed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(transferFailedMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickCancelTransfer() {
        wait.until(ExpectedConditions.elementToBeClickable(cancelTransfer));
        driver.findElement(cancelTransfer).click();
    }

    public boolean isCancelTransferSuccessful() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(userName)).isDisplayed();
    }
 

}

