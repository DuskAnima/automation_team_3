package cl.kibernumacademy.steps;

import static org.junit.jupiter.api.Assertions.assertTrue;

import cl.kibernumacademy.pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {
  private LoginPage loginPage;

  @Before
  public void setUp() {
    LoginPage.initDriver();
    loginPage = new LoginPage();
  }

  @After
  public void tearDown() {
    LoginPage.closeBrowser();
  }

  @Given("que el usuario está en la página de login")
  public void que_el_usuario_esta_en_la_pagina_de_login() {
    loginPage.navigateToLogin();
    loginPage.tomarSnapshot("snapshot_login_page");
  }

  @When("ingresa usuario {string} y clave {string}")
  public void ingresa_usuario_y_clave(String usuario, String clave) {
    loginPage.login(usuario, clave);
    loginPage.tomarSnapshot("snapshot_despues_de_login");
  }

  @Then("debería ver el mensaje {string}")
  public void deberia_ver_el_mensaje(String mensaje) {
    String welcomeMessage = loginPage.getWelcomeMessage();
    assertTrue(welcomeMessage.contains(mensaje), "Mensaje esperado: " + mensaje + ", pero se obtuvo: " + welcomeMessage);
    loginPage.tomarSnapshot("snapshot_welcome");
  }
}
