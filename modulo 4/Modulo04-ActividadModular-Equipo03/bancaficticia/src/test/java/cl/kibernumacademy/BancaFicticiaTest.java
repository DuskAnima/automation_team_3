package cl.kibernumacademy;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import cl.kibernumacademy.pages.BankTransfer;
import cl.kibernumacademy.pages.CheckBalance;
import cl.kibernumacademy.pages.Dashboard;
import cl.kibernumacademy.pages.Login;
import cl.kibernumacademy.util.Snapshots;
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
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Ejecutar en modo headless
        options.addArguments("--window-size=1920,1080"); // Tamaño recomendado para capturas
        driver = new ChromeDriver(options);
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

    @ParameterizedTest
    @CsvSource({
        "sofia, academy, true",
        "juan, academy, false"
    })
    @DisplayName("Login exitoso y fallido con diferentes credenciales")
    public void testLoginParameterized(String username, String password, boolean expectedSuccess) {
        Snapshots.tomarSnapshot(driver, "login_inicial_" + username);
        loginPage.loginAs(username, password);
    
        if (expectedSuccess) {
            assertTrue(loginPage.isLoginSuccessful(), "Login exitoso con: " + username);
            Snapshots.tomarSnapshot(driver, "login_exitoso_" + username);
        } else {
            assertTrue(loginPage.isLoginFailed(), "Login fallido con: " + username);
            Snapshots.tomarSnapshot(driver, "login_fallido_" + username);
        }
    }

/* 
    @Test 
    @DisplayName("Login exitoso con usuario válido")
       public void testLoginSuccessful() {
        Snapshots.tomarSnapshot(driver, "login_inicial");
        loginPage.loginAs("sofia", "academy");
        assertTrue(loginPage.isLoginSuccessful(), "sofia");
        Snapshots.tomarSnapshot(driver, "login_exitoso_sofia");
        }

    
    @Test
    @DisplayName("Login fallido con credenciales incorrectas")
    public void testLoginFailed() {
        Snapshots.tomarSnapshot(driver, "login_inicial");
        loginPage.loginAs("juan", "academy");
        assertTrue(loginPage.isLoginFailed(), "Credenciales incorrectas");
        Snapshots.tomarSnapshot(driver, "login_fallido_juan");
    }
*/
    
    @Test
    @DisplayName("Visibilidad de botones en el dashboard")
    public void testDashboardButtonsVisibility() {
        Snapshots.tomarSnapshot(driver, "login_inicial");
        loginPage.loginAs("sofia", "academy");
        Snapshots.tomarSnapshot(driver, "login_exitoso_sofia");
        assertTrue(loginPage.isLoginSuccessful(), "sofia");
        assertTrue(dashboardPage.isBankTransferButtonVisible(), "Botón de transferencia visible");
        assertTrue(dashboardPage.isCheckBalanceButtonVisible(), "Botón de saldo visible");
        assertTrue(dashboardPage.isLogOutButtonVisible(), "Botón de logout visible");
        Snapshots.tomarSnapshot(driver, "dashboard_visible");
    }

        
    @Test
    @DisplayName("Visualización del saldo luego del login")
    public void testCheckBalance() {
        Snapshots.tomarSnapshot(driver, "login_inicial");
        loginPage.loginAs("sofia", "academy");
        assertTrue(loginPage.isLoginSuccessful(), "sofia");
        Snapshots.tomarSnapshot(driver, "login_exitoso_sofia");
        dashboardPage.clickCheckBalance();
        assertTrue(checkBalancePage.isBalanceVisible(), "Saldo visible");
        Snapshots.tomarSnapshot(driver, "saldo_visible");
    }

     @Test
     @DisplayName("Validar monto del saldo inicial")
    public void testBalanceMount() {
        Snapshots.tomarSnapshot(driver, "login_inicial");
        loginPage.loginAs("sofia", "academy");
        assertTrue(loginPage.isLoginSuccessful(), "sofia");
        Snapshots.tomarSnapshot(driver, "login_exitoso_sofia");
        dashboardPage.clickCheckBalance();
        assertEquals(checkBalancePage.getBalanceAmount() , 5000);
        Snapshots.tomarSnapshot(driver, "saldo_inicial");
    }

    @Test
    @DisplayName("Validar ingreso a consultar saldo y retornar al menú")
    public void testCheckBalanceBackMenu() {
        Snapshots.tomarSnapshot(driver, "login_inicial");
        loginPage.loginAs("sofia", "academy");
        assertTrue(loginPage.isLoginSuccessful(), "sofia");
        Snapshots.tomarSnapshot(driver, "login_exitoso_sofia");
        dashboardPage.clickCheckBalance();
        checkBalancePage.clickBackMenu();
        assertTrue(checkBalancePage.isBackMenuVisible(), "Volver al menú principal");
        Snapshots.tomarSnapshot(driver, "volver_menu_principal");
    }

    @Test
     @DisplayName("Botón de transferencia habilitado")
    public void testTransferButtonEnabled() {
        Snapshots.tomarSnapshot(driver, "login_inicial");
        loginPage.loginAs("sofia", "academy");
        assertTrue(loginPage.isLoginSuccessful(), "sofia");
        Snapshots.tomarSnapshot(driver, "login_exitoso_sofia");
        dashboardPage.clickBankTransfer();
        assertTrue(bankTransfer.isTransferButtonEnabled(), "Botón de transferencia habilitado");
        Snapshots.tomarSnapshot(driver, "boton_transferencia_habilitado");
    }   

    @Test
    @DisplayName("Transferencia bancaria exitosa")
    public void testBankTransferSuccessful() {
        Snapshots.tomarSnapshot(driver, "login_inicial");
        loginPage.loginAs("sofia", "academy");
        assertTrue(loginPage.isLoginSuccessful(), "sofia");
        Snapshots.tomarSnapshot(driver, "login_exitoso_sofia");
        dashboardPage.clickBankTransfer();
        Snapshots.tomarSnapshot(driver, "transferencia_inicial");
        bankTransfer.enterDestinyAccount("123456789");
        bankTransfer.enterAmount("3000");
        bankTransfer.enterMotive("Pago de servicios");
        bankTransfer.clickTransferButton();
        assertTrue(bankTransfer.isTransferSuccessful(),"Transferencia exitosa a 123456789 por $3000. Motivo: Pago de servicios");
        Snapshots.tomarSnapshot(driver, "transferencia_exitosa");
    }  
    
    @Test
    @DisplayName("Verificar saldo luego de una transferencia")
    public void testBalanceAfterTransfer() {
        Snapshots.tomarSnapshot(driver, "login_inicial");
        loginPage.loginAs("sofia", "academy");
        assertTrue(loginPage.isLoginSuccessful(), "sofia");
        Snapshots.tomarSnapshot(driver, "login_exitoso_sofia");
        dashboardPage.clickCheckBalance();
        Snapshots.tomarSnapshot(driver, "saldo_inicial");
        int balanceBefore = checkBalancePage.getBalanceAmount();
        checkBalancePage.clickBackMenu();
        Snapshots.tomarSnapshot(driver, "volver_menu_principal");
        dashboardPage.clickBankTransfer();
        Snapshots.tomarSnapshot(driver, "transferencia_inicial");
        bankTransfer.enterDestinyAccount("123456789");
        bankTransfer.enterAmount("3000");
        int transferAmount = bankTransfer.getTransferAmount();
        bankTransfer.enterMotive("Pago de servicios");
        bankTransfer.clickTransferButton();
        assertTrue(bankTransfer.isTransferSuccessful(), "Transferencia exitosa");
        Snapshots.tomarSnapshot(driver, "transferencia_exitosa");
        dashboardPage.clickCheckBalance();
        int balanceAfter = checkBalancePage.getBalanceAmount();
        assertEquals(balanceBefore - transferAmount, balanceAfter, "Saldo descontado correctamente tras transferencia");
        Snapshots.tomarSnapshot(driver, "saldo_despues_transferencia");
    }   

    @Test
    @DisplayName("Transferencia fallida por saldo insuficiente")
    public void testBankTransferFailed() {
        Snapshots.tomarSnapshot(driver, "login_inicial");
        loginPage.loginAs("sofia", "academy");
        assertTrue(loginPage.isLoginSuccessful(), "sofia");
        Snapshots.tomarSnapshot(driver, "login_exitoso_sofia");
        dashboardPage.clickBankTransfer();
        Snapshots.tomarSnapshot(driver, "transferencia_inicial");
        bankTransfer.enterDestinyAccount("123456789");
        bankTransfer.enterAmount("1000000"); // Monto mayor al saldo
        bankTransfer.enterMotive("Pago de servicios");
        bankTransfer.clickTransferButton();
        assertTrue(bankTransfer.isTransferFailed(), "Saldo insuficiente");
        Snapshots.tomarSnapshot(driver, "transferencia_fallida");
    }

    @Test
    @DisplayName("Cancelar transferencia y volver al dashboard")
    public void testCancelTransfer() {
        Snapshots.tomarSnapshot(driver, "login_inicial");
        loginPage.loginAs("sofia", "academy");
        assertTrue(loginPage.isLoginSuccessful(), "sofia");
        Snapshots.tomarSnapshot(driver, "login_exitoso_sofia");
        dashboardPage.clickBankTransfer();
        Snapshots.tomarSnapshot(driver, "transferencia_inicial");
        bankTransfer.enterDestinyAccount("123456789");
        bankTransfer.enterAmount("3000");
        bankTransfer.enterMotive("Pago de servicios");
        bankTransfer.clickCancelTransfer();
        assertTrue(bankTransfer.isCancelTransferSuccessful(), "Transferencia cancelada correctamente");
        Snapshots.tomarSnapshot(driver, "transferencia_cancelada");
    }

    @Test
    @DisplayName("Cerrar sesión exitosamente")
    public void testLogout() {
        Snapshots.tomarSnapshot(driver, "login_inicial");
        loginPage.loginAs("sofia", "academy");
        assertTrue(loginPage.isLoginSuccessful(), "sofia");
        Snapshots.tomarSnapshot(driver, "login_exitoso_sofia");
        dashboardPage.clickLogOut();;
        assertTrue(dashboardPage.isLogoutSuccessful(), "Logout exitoso");
        Snapshots.tomarSnapshot(driver, "logout_exitoso");
    }

    
}
