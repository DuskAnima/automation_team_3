package cl.academy.transferencias.steps;

import java.util.Map;

import org.junit.jupiter.api.Assertions;

import cl.academy.transferencias.pages.TransferPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.When;

public class UserDataValidation {
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

	@When("completa los siguientes datos:")
	public void completa_los_siguientes_datos(DataTable dataTable) {
		transferPage.navigateToBank();
		System.out.println("Imprimiendo datos de usuario en consola...");
		Map<String, String> datos = dataTable.asMap(String.class, String.class);
		for(Map.Entry<String, String> entry : datos.entrySet()) {
			switch (entry.getKey().trim()) {
				case "nombre":
					transferPage.enterName(entry.getValue()); // Ingresa valor de la tabla a al campo de la web
					Assertions.assertTrue(transferPage.getNameValue().contains(entry.getValue())); // Verifica que el campo web sea el mismo de la tabla
					System.out.println(entry.getKey() + ": " + entry.getValue()); // Imprime valor de tabla en consola
					break;
				case "apellido":
					transferPage.enterLastname(entry.getValue());
					Assertions.assertTrue(transferPage.getLastnameValue().contains(entry.getValue()));
					System.out.println(entry.getKey() + ": " + entry.getValue());
					break;
				case "email":
					transferPage.enterEmail(entry.getValue());
					Assertions.assertTrue(transferPage.getEmailValue().contains(entry.getValue()));
					System.out.println(entry.getKey() + ": " + entry.getValue());
					break;
				case "edad":
					transferPage.enterAge(entry.getValue());
					Assertions.assertTrue(transferPage.getAgeValue().contains(entry.getValue()));
					System.out.println(entry.getKey() + ": " + entry.getValue());
					break;
				default:
					System.out.println("Opción no válida: " + entry.getKey());
					break;
			}
		}
	}
}
