package cl.kibernumacademy;

 
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import static org.junit.jupiter.api.Assertions.assertTrue;


 
public class GoogleSearchTest 
{ 
    private WebDriver driver;

    @BeforeEach
    void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
         
    }
    
    @Test
    void buscarInformacionEnGoogle() {
        driver.get("https://www.google.com");
        WebElement campoBusqueda =driver.findElement(By.name("q"));
        campoBusqueda.sendKeys("automatización con Selenium Driver");
        campoBusqueda.sendKeys(Keys.RETURN);
        assertTrue(driver.getTitle().toLowerCase().contains("selenium"));
        
    }

    @AfterEach
    void tearDown(){
        if(driver != null) {
            driver.quit(); // cierra el navegador
        }
    }

   // return go(f,seed, []);
}
