package cl.kibernumacademy;


import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cl.kibernumacademy.pages.BankTransfer;
import cl.kibernumacademy.pages.CheckBalance;
import cl.kibernumacademy.pages.Dashboard;
import cl.kibernumacademy.pages.Login;
import io.github.bonigarcia.wdm.WebDriverManager;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BancaFicticiaTest {
    private WebDriver driver;
    private Login loginPage;
    private Dashboard dashboardPage;
    private CheckBalance checkBalancePage;
    private BankTransfer bankTransfer;
    private static final String URL = "https://bco-selenium.netlify.app/";

    @BeforeAll
    void setUpClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(URL);
        loginPage = new Login(driver);
        dashboardPage = new Dashboard(driver);
        checkBalancePage = new CheckBalance(driver);
        bankTransfer = new BankTransfer(driver);
    }

     @AfterEach
    void tearDown() {
        if(driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testLoginSuccessful() {
        loginPage.enterUsername("sofia");
        loginPage.enterPassword("academy");
        loginPage.clickLoginButton();
        assertTrue(loginPage.isLoginSuccessful(), "sofia");
        }

    
    @Test
    public void testLoginFailed() {
        loginPage.enterUsername("juan");
        loginPage.enterPassword("academy");
        loginPage.clickLoginButton();
        assertTrue(loginPage.isLoginFailed(), "Credenciales incorrectas");
        }
        
     @Test
    public void testBalanceMount() {
        loginPage.enterUsername("sofia");
        loginPage.enterPassword("academy");
        loginPage.clickLoginButton();
        assertTrue(loginPage.isLoginSuccessful(), "sofia");
        dashboardPage.clickCheckBalance();
        assertTrue(checkBalancePage.balanceMount().contains("$"), "Saldo actual: $5000");
        checkBalancePage.clickBackMenu();
        assertTrue(checkBalancePage.isBackMenuVisible(), "Volver al menú principal");
    }

    @Test
    public void testBankTransferSuccessful() {
        loginPage.enterUsername("sofia");
        loginPage.enterPassword("academy");
        loginPage.clickLoginButton();
        assertTrue(loginPage.isLoginSuccessful(), "sofia");
        dashboardPage.clickBankTransfer();
        bankTransfer.enterDestinyAccount("123456789");
        bankTransfer.enterAmount("3000");
        bankTransfer.enterMotive("Pago de servicios");
        bankTransfer.clickTransferButton();
        assertTrue(bankTransfer.isTransferSuccessful(),"Transferencia exitosa a 123456789 por $3000. Motivo: Pago de servicios");
    }   

    @Test
    public void testBankTransferFailed() {
        loginPage.enterUsername("sofia");
        loginPage.enterPassword("academy");
        loginPage.clickLoginButton();
        assertTrue(loginPage.isLoginSuccessful(), "sofia");
        dashboardPage.clickBankTransfer();
        bankTransfer.enterDestinyAccount("123456789");
        bankTransfer.enterAmount("1000000"); // Monto mayor al saldo
        bankTransfer.enterMotive("Pago de servicios");
        bankTransfer.clickTransferButton();
        assertTrue(bankTransfer.isTransferFailed(), "Saldo insuficiente");
    }

    @Test
    public void testLogout() {
        loginPage.enterUsername("sofia");
        loginPage.enterPassword("academy");
        loginPage.clickLoginButton();
        assertTrue(loginPage.isLoginSuccessful(), "sofia");
        dashboardPage.clickLogOut();;
        assertTrue(dashboardPage.isLogoutSuccessful(), "Logout exitoso");
    }

    @Test
    public void testCancelTransfer() {
        loginPage.enterUsername("sofia");
        loginPage.enterPassword("academy");
        loginPage.clickLoginButton();
        assertTrue(loginPage.isLoginSuccessful(), "sofia");
        dashboardPage.clickBankTransfer();
        bankTransfer.enterDestinyAccount("123456789");
        bankTransfer.enterAmount("3000");
        bankTransfer.enterMotive("Pago de servicios");
        bankTransfer.clickCancelTransfer();
        assertTrue(bankTransfer.isCancelTransferSuccessful(), "Transferencia cancelada correctamente");
    }
}
