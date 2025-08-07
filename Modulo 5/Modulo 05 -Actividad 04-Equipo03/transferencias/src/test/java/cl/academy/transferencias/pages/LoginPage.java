package cl.academy.transferencias.pages;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import cl.academy.transferencias.hooks.DriverHolder   ;

public class LoginPage {

    private By userField = By.id("username");
    private By passwordField = By.id("password");
    private By btnLogin = By.xpath("//form[@id='login-form']//button[@type='submit']");
    private By messageError = By.id("login-message");           

//  retornar driver web
    private WebDriver getDriver() {
        return DriverHolder.get();
    }   

    public void abrir() {
        getDriver().get(" ");
    }

    public void login(String usuario, String password) {
        WebDriver driver = getDriver();     
        driver.findElement(userField).sendKeys(usuario);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(btnLogin).click();
    }  
 
    public String obtenerError() {
        List<WebElement> messageErrors = getDriver().findElements(messageError);
        return messageErrors.isEmpty() ? "" : messageErrors.get(0).getText().trim();
    }


}



 
