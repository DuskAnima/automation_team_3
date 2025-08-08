package cl.academy.transferencias.hooks;


import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Hooks {

  /*@Before
  public void setUp() {
    WebDriver driver = WebDriverManager.chromedriver().create();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    DriverHolder.set(driver);
    driver.get("https://web-actividad-4.netlify.app/");
  }

*/

@Before
  public void setUp() {
   
    WebDriver driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    driver.manage().window().maximize();

    DriverHolder.set(driver);
    driver.get("https://web-actividad-4.netlify.app/");
  }


  @After
  public void tearDown(Scenario scenario) {
    // Obtener el driver que se esta usando
    WebDriver driver = DriverHolder.get();
    if(scenario.isFailed()) {
      byte[] shot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
      scenario.attach(shot, "image/png", scenario.getName());
    }
     DriverHolder.get().quit();
    DriverHolder.remove();  
  }
    
}
