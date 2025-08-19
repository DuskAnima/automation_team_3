package cl.academy.clinica.steps;

import static org.junit.jupiter.api.Assertions.assertTrue;

import cl.academy.clinica.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {
  LoginPage loginPage;

  @Given("que el médico abre la página de inicio de sesión")
  public void que_el_medico_abre_la_pagina_de_inicio_de_sesion() {
    loginPage = new LoginPage();
    loginPage.navigateToLogin();
  }

  @When("ingresa el usuario {string} y la contraseña {string}")
  public void ingresa_el_usuario_y_la_contraseña(String user, String password) {
    loginPage.enterUser(user);
    loginPage.enterPass(password);
    loginPage.submitUser();
  }

  @Then("el médico accede a la web {string}")
  public void el_medico_ve_el_resultado(String expectedUrl) {
    String realUrl = loginPage.getCurrentUrl();
    assertTrue(realUrl.contains(expectedUrl));
  }

  @Then("se ve el mensaje de error {string}")
  public void se_ve_el_mensaje_de_error(String expectedError) {
    String realError = loginPage.getErrorLoginMessage();
    assertTrue(realError.contains(expectedError), "Error invalido: " + realError);
  }
}
