package cl.academy.transferencias.steps;

import java.util.Map;

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
			switch (entry.getKey()) {
				case "nombre":
					transferPage.enterName(entry.getValue());
					System.out.println(entry.getValue());
					break;
				case "apellido":
					transferPage.enterLasname(entry.getValue());
					System.out.println(entry.getValue());
					break;
				case "email":
					transferPage.enterEmail(entry.getValue());
					System.out.println(entry.getValue());
					break;
				case "edad":
					transferPage.enterAge(entry.getValue());
					System.out.println(entry.getValue());
				default:
					System.out.println("Opción no válida");
					break;
			}
		}
	}
}
