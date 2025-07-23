package cl.kibernumacademy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import cl.kibernumacademy.pages.WebFormDemoQa;
import io.github.bonigarcia.wdm.WebDriverManager;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FormularioTest {
    
    private WebDriver driver;
    private WebFormDemoQa webFormDemoQa;
    private final String URL = "https://demoqa.com/automation-practice-form";
    private WebDriverWait wait;

    @BeforeAll
    void setUpClass() {
        WebDriverManager.chromedriver().setup();
    }
    
    @BeforeEach
    void setUpTest() {
        driver = new ChromeDriver();
        driver.get(URL);
        // 🔥 Elimina iframes de publicidad
        ((JavascriptExecutor) driver).executeScript(
        "document.querySelectorAll('iframe').forEach(el => el.remove());"
         );

         wait = new WebDriverWait(driver, Duration.ofSeconds(10));
         webFormDemoQa = new WebFormDemoQa(driver);

        // Asegúrate de que el botón Submit sea visible
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element); 
    }

    @AfterEach
    void tearDown() {
        if(driver != null) {
            driver.quit();
        }
    }

    @Test
    void FormDemoQa() {
        String firstName = "Laura";
        String lastName = "Rodriguez";
        String email = "laura@test.com";
        String phone = "987654321";
        String address = "Providencia 456";
        String gender = "Female";
        String hobbie1 = "Reading";
        String hobbie2 = "Music";
        String state = "NCR";
        String city = "Delhi";

        webFormDemoQa.setFirstName(firstName);
        assertEquals(firstName, webFormDemoQa.getFirstName());
        webFormDemoQa.setLastName(lastName);
        assertEquals(lastName, webFormDemoQa.getLastName());
        webFormDemoQa.setEmail(email);
        assertEquals(email, webFormDemoQa.getEmail());
        webFormDemoQa.selectGender(gender);
        webFormDemoQa.setPhone(phone);
        assertEquals(phone, webFormDemoQa.getPhone());
        webFormDemoQa.checkHobbie(hobbie1);
        webFormDemoQa.checkHobbie(hobbie2);
        webFormDemoQa.setCurrentAddress(address); 
        assertEquals(address, webFormDemoQa.getCurrentAddress());
        webFormDemoQa.selectState(state);
        webFormDemoQa.selectCity(city);

    }


}
