import factoryBrowser.Browser;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pom.RegistrationPage;
import user.AppConfig;

import java.time.Duration;
import java.util.Map;

public class RegistrationTest {
    private WebDriver webDriver;
    private AppConfig appConfig;

    @BeforeEach
    public void SetUP() {
        String browser = System.getProperty("browser", "chrome");
        appConfig = ConfigFactory.create(AppConfig.class, Map.of("env", browser));
        RestAssured.baseURI= "https://stellarburgers.education-services.ru";

        webDriver = new Browser().getWebDriver(browser);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @Test
    @DisplayName("Успешная регистрация")
    public void testRegistration() {
        RegistrationPage registrationPage = new RegistrationPage(webDriver);
        registrationPage.open()
                .clickUserAccount()
                .clickButtonRegister()
                .enterNameInput("прааприа")
                .enterEmailInput("dfg5пппwqt@mail.ru")
                .enterPasswordInput("dgfnn559246")
                .clickButtonLoginSignInForm()
                .textTextLogin();


    }

    @Test
    @DisplayName("Не правильный пароль")
    public void testErrorPassword() {
        RegistrationPage registrationPage = new RegistrationPage(webDriver);
        registrationPage.open()
                .clickUserAccount()
                .clickButtonRegister()
                .enterNameInput("Юлия")
                .enterEmailInput("dfg5wqt@mail.ru")
                .enterPasswordInput("dgf")
                .clickButtonLoginSignInForm()
                .textErrorMessage();

    }

    @AfterEach
    public void tearDown() {
        webDriver.quit();
    }
}
