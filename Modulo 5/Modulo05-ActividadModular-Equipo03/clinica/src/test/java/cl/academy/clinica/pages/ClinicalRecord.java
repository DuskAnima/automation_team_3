package cl.academy.clinica.pages;

public class ClinicalRecord extends BasePage {

  // Localizadores
  
  private String usernameFieldId = "username";
  private String passwordFieldId = "password";
  private String submitButtonXpath = "//button[@type='submit']";
  private String textErrorXpath = "//*[@id='login-message']/div";

  public void navigateToLogin() {
    navigateTo("https://clinica-modular.netlify.app/");
  } 

  public void enterUser(String usernameInput) {
    write("id", usernameFieldId, usernameInput);
  }

  public void enterPass(String passwordInput) {
    write("id", passwordFieldId, passwordInput);
  }

  public void submitUser() {
    clickElement("xpath", submitButtonXpath);
  }

  public String getErrorLoginMessage() {
    return getElementText("xpath", textErrorXpath);
  }
}
