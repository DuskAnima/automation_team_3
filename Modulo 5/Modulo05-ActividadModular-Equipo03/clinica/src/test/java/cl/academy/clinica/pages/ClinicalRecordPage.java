package cl.academy.clinica.pages;

public class ClinicalRecordPage extends BasePage {

  // Localizadores 
  private String nameFieldId = "nombre";
  private String diagnosisFieldId = "diagnostico";
  private String ageFieldId = "edad";
  private String treatmentFieldId = "tratamiento";
  private String saveButtonXpath = "//form[@id='record-form']//button[@type='submit']";
  private String recordMessageXpath = "//div[@id='record-message']//*[contains(text(),'%s')]";

  public void enterName(String name) {
    write("id", nameFieldId, name);
  }

  public void enterDiagnosis(String diagnosis) {
    write("id", diagnosisFieldId, diagnosis);
  }

  public void enterAge(String age) {
    write("id", ageFieldId, age);
  }

  public void enterTreatment(String treatment) {
    write("id", treatmentFieldId, treatment);
  }

  public void clickSaveButton() {
    clickElement("xpath", saveButtonXpath);
  }

  public String getRecordMessage(String expectedText) {
    String finalXpath = String.format(recordMessageXpath, expectedText);
    System.err.println(finalXpath);
    return getElement("xpath", finalXpath).getText().trim();
  }
}
