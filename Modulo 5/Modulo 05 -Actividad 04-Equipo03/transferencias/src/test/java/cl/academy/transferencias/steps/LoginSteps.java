package cl.academy.transferencias.steps;

import org.junit.jupiter.api.Assertions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import cl.academy.transferencias.pages.LoginPage;

public class LoginSteps {
    LoginPage loginPage;



    //Login ok
  @Given("que cliente accede a página del login")
  public void  que_el_cliente_accede_a_página_del_login() {
    loginPage = new LoginPage();
    loginPage.abrir();
  }

  @When("ingresa credenciales válidas")
  public void ingresa_credenciales_válidas() {
    loginPage = new LoginPage();
    loginPage.login("admin", "secret");
  }

  @Then("accede a su cuenta bancaria")
  public void a_su_cuenta_bancaria() {
    loginPage = new LoginPage();
    Assertions.assertTrue(loginPage.obtenerError().isEmpty(), "No debería haber un mensaje de error");
  }


  // Login errado
  /*@Given("que el cliente accede a página del login")
  public void  que_el_cliente_accede_a_página_del_login() {
    loginPage = new LoginPage();
    loginPage.abrir();
  }*/
  
  @When("ingresa credenciales inválidas")
  public void ingresa_credenciales_inválidas() {
    loginPage = new LoginPage();
    loginPage.login("userError", "faild");
  }

  @Then("se muestra un mensaje de error de acceso")
  public void se_muestra_un_mensaje_de_error_de_acceso() {
    loginPage = new LoginPage();
    Assertions.assertTrue(loginPage.obtenerError().contains("inválidas"), "Debe mostrar mensaje de credenciales inválidas");
  }
}
