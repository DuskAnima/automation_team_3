package cl.academy.clinica.steps;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import cl.academy.clinica.pages.ClinicalRecordPage;
import cl.academy.clinica.pages.LoginPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ClinicalRecordSteps {
  ClinicalRecordPage clinicalRecordPage;
  LoginPage loginPage;

  @Given("que el médico inició sesión")
  public void que_el_medico_inicia_sesion() {
    loginPage = new LoginPage();
    loginPage.navigateToLogin();
    loginPage.enterUser("doctor");
    loginPage.enterPass("password");
    loginPage.submitUser();

    clinicalRecordPage = new ClinicalRecordPage();
  }
  @When("ingresa los datos de la ficha:")
  public void ingresa_los_datos_de_la_ficha(DataTable dataTable) {
    Map<String, String> datos = dataTable.asMap(String.class, String.class);
    for (Map.Entry<String, String> entry : datos.entrySet()) {
      switch (entry.getKey()) {
        case "nombre":
          clinicalRecordPage.enterName(entry.getValue());
          break;
        case "diagnostico":
          clinicalRecordPage.enterDiagnosis(entry.getValue());
          break;
        case "edad":
          clinicalRecordPage.enterAge(entry.getValue());
          break;
        case "tratamiento":
          clinicalRecordPage.enterTreatment(entry.getValue());
          break;
      }
    }
    clinicalRecordPage.clickSaveButton();
  }

@When("ingresa el nombre {string}, el diagnóstico {string}, la edad {int} y el tratamiento {string}")
public void ingresa_el_nombre_el_diagnóstico_la_edad_y_el_tratamiento(
    String name, String diagnosis, int age, String treatment) {
    if (treatment == null) treatment = "";
    clinicalRecordPage.enterName(name);
    clinicalRecordPage.enterDiagnosis(diagnosis);
    clinicalRecordPage.enterAge(String.valueOf(age));
    clinicalRecordPage.enterTreatment(treatment);
    clinicalRecordPage.clickSaveButton();
  }

  @Then("se muestra el mensaje {string}")
  public void se_muestra_el_mensaje(String expectedMessage) {
    String realMessage = clinicalRecordPage.getRecordMessage(expectedMessage);
    assertTrue(realMessage.contains(expectedMessage), "Mensaje equivocado. Se obtuvo: " + realMessage);
  }

}
