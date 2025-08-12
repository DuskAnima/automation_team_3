package cl.academy.transferencias.steps;

import org.junit.jupiter.api.Assertions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import cl.academy.transferencias.pages.TransferPage;

public class TransferSteps {
  TransferPage transferPage;

  @Before
  public void setUp() {
    TransferPage.initDriver();
    transferPage = new TransferPage();
  }

  @After
  public void tearDown() {
    TransferPage.closeBrowser();
  }

  @Given("que el usuario ha iniciado sesión")
  public void que_el_usuario_ha_iniciado_sesion_() {
    transferPage.navigateToBank();
  }

  @When("transfiere {double} a la cuenta {string}")
  public void transfiere_a_la_cuenta(double monto, String cuenta) {
    transferPage.enterAmount(monto);
    transferPage.enterAccountNumber(cuenta);
    transferPage.clickButtonTransfer();
    System.out.println("Monto: " + monto);
    System.out.println("Cuenta: " + cuenta);
  }

  @Then("debería ver el mensaje {string}")
  public void deberia_ver_el_mensaje(String expectedMessage) {
    String webMessage = transferPage.getResultMessage();
    System.out.println("Mensaje: " + expectedMessage);
    Assertions.assertTrue(webMessage.contains(expectedMessage), "El mensaje: '" + webMessage + "' no coincide con el resultado esperado: '" + expectedMessage + "'.");
  }
}
