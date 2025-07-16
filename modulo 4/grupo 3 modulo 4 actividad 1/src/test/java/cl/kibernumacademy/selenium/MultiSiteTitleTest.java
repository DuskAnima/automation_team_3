package cl.kibernumacademy.selenium;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Clase para pruebas automatizadas
public class MultiSiteTitleTest {
  // Objeto del tipo WebDriver para controlar el navegador
  private WebDriver driver;

  @AfterEach
  void tearDown() {
    // Si el driver fue inicializado, cierra el navegador
    if(driver != null) {
      driver.quit(); // cierra el navegador
    }
  }
  // https://www.google.com
  // Test para validar el titulo de Google
  @Test
  void validarTituloGoogle() {
    // Configurar el driver para chrome y que este proceso sea automatico
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver(); // Inicializamos el navegador Chrome
    // Abre la página de Google
    driver.get("https://www.google.com");

    // Verificar que el titulo contenga la palabra google
    assertTrue(driver.getTitle().contains("Google"));
  }

  @Test
  void validarTituloGitHub() {
    // Configurar el driver para chrome y que este proceso sea automatico
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver(); // Inicializamos el navegador Chrome
    // Abre la página de Google
    driver.get("https://github.com");

    // Verificar que el titulo contenga la palabra google
    assertTrue(driver.getTitle().contains("GitHub"));
  }

  @Test
  void validarTituloStackOverflow() {
    // Configurar el driver para chrome y que este proceso sea automatico
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver(); // Inicializamos el navegador Chrome
    // Abre la página de Google
    driver.get("https://stackoverflow.com");
    // Verificar que el titulo contenga la palabra google
    assertTrue(driver.getTitle().contains("Stack Overflow"));
  }


  @Test
  void validarTituloaws() {
    // Configurar el driver para chrome y que este proceso sea automatico
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver(); // Inicializamos el navegador Chrome
    // Abre la página de Google
    driver.get("https://aws.amazon.com/");
    // Verificar que el titulo contenga la palabra google
    assertTrue(driver.getTitle().contains("Cloud Computing"));
  }


}