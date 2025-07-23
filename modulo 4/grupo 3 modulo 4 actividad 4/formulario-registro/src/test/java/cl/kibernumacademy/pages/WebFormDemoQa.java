package cl.kibernumacademy.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebFormDemoQa {

    private WebDriver driver;
    private WebDriverWait wait;

    private By textInputFirstName = By.id("firstName");
    private By textInputLastName = By.id("lastName");
    private By textInputEmail = By.id("userEmail");
    private By textInputPhone = By.id("userNumber");
    private By textAreaCurrentAddress = By.id("currentAddress");
    private By stateDropdown = By.id("state");
    private By cityDropdown = By.id("city");
    private By submitButton = By.id("submit");

    public WebFormDemoQa(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    private void waitAndClick(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        scrollToElement(element);
        element.click();
    }

    public void setFirstName(String text) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(textInputFirstName));
        input.clear();
        input.sendKeys(text);
    }

    public String getFirstName() {
        return driver.findElement(textInputFirstName).getAttribute("value");
    }

    public void setLastName(String text) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(textInputLastName));
        input.clear();
        input.sendKeys(text);
    }

    public String getLastName() {
        return driver.findElement(textInputLastName).getAttribute("value");
    }

    public void setEmail(String text) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(textInputEmail));
        input.clear();
        input.sendKeys(text);
    }

    public String getEmail() {
        return driver.findElement(textInputEmail).getAttribute("value");
    }

    public void setPhone(String text) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(textInputPhone));
        input.clear();
        input.sendKeys(text);
    }

    public String getPhone() {
        return driver.findElement(textInputPhone).getAttribute("value");
    }

    public void setCurrentAddress(String text) {
        WebElement textarea = wait.until(ExpectedConditions.visibilityOfElementLocated(textAreaCurrentAddress));
        textarea.clear();
        textarea.sendKeys(text);
    }

    public String getCurrentAddress() {
        return driver.findElement(textAreaCurrentAddress).getAttribute("value");
    }

    public void selectState(String stateName) {
        waitAndClick(stateDropdown);
        By option = By.xpath("//div[contains(@id,'react-select-3-option') and text()='" + stateName + "']");
        waitAndClick(option);
    }

    public void selectCity(String cityName) {
        waitAndClick(cityDropdown);
        By option = By.xpath("//div[contains(@id,'react-select-4-option') and text()='" + cityName + "']");
        waitAndClick(option);
    }

    public void checkHobbie(String hobbieLabel) {
        By hobbieCheckbox = By.xpath("//label[text()='" + hobbieLabel + "']");
        waitAndClick(hobbieCheckbox);
    }

    public void selectGender(String genderLabel) {
        By genderOption = By.xpath("//label[text()='" + genderLabel + "']");
        waitAndClick(genderOption);
    }

    public void submitForm() {
        waitAndClick(submitButton);
    }
}