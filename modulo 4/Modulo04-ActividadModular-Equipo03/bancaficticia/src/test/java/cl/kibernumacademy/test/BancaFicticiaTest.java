package cl.kibernumacademy.test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cl.kibernumacademy.pages.*;

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
        driver.manage().window().maximize();
        driver.get(URL);
        loginPage = new Login(driver);
        dashboardPage = new Dashboard(driver);
        checkBalancePage = new CheckBalance(driver);
        bankTransfer = new BankTransfer(driver);
    }

     @AfterEach
    void tearDown() {
        if(driver != null) driver.quit();
    }

    @Test 
    @DisplayName("Login exitoso con usuario válido")
    public void testLoginSuccessful() {
        loginPage.loginAs("sofia", "academy");

        assertTrue(loginPage.isLoginSuccessful(), "No existe confirmación de éxito en el login");
        }

    @Test
    @DisplayName("Login fallido con credenciales incorrectas")
    public void testLoginFailed() {
        loginPage.loginAs("juan", "kibernum");

        assertTrue(loginPage.isLoginFailed(), "Credenciales incorrectas");
        }

    @Test
    @DisplayName("Consulta de saldo tras iniciar sesión")
    public void testBalanceMount() {
        loginPage.loginAs("sofia", "academy");
        dashboardPage.clickCheckBalance();

        assertTrue(checkBalancePage.isBalanceVisible(), "Saldo visible");
        assertEquals(checkBalancePage.getBalanceAmount(), 5000);
    }

    @Test
    @DisplayName("Transferencia bancaria exitosa")
    public void testBankTransferSuccessful() {
        loginPage.loginAs("sofia", "academy");
        dashboardPage.clickBankTransfer();
        bankTransfer.enterDestinyAccount("123456789");
        bankTransfer.enterAmount("3000");
        bankTransfer.enterMotive("Pago de servicios");
        bankTransfer.clickTransferButton();

        assertTrue(bankTransfer.isTransferSuccessful(),"Transferencia fallida");
    }  

    @Test
    @DisplayName("Transferencia fallida por saldo insuficiente")
    public void testBankTransferFailed_insufficientBalance() {
        loginPage.loginAs("sofia", "academy");
        dashboardPage.clickBankTransfer();
        bankTransfer.enterDestinyAccount("123456789");
        bankTransfer.enterAmount("1000000"); // Monto mayor al saldo
        bankTransfer.enterMotive("Pago de servicios");
        bankTransfer.clickTransferButton();

        assertTrue(bankTransfer.isTransferFailed(), "Transferencia debió fallar");
    }

    @Test
    @DisplayName("Logout y retorno al formulario de login")
    public void testLogout() {
        loginPage.loginAs("sofia", "academy");
        dashboardPage.clickLogOut();

        assertTrue(dashboardPage.isLogoutSuccessful(), "Logout exitoso");
    }
}
