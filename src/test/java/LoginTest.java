import com.github.javafaker.Faker;
import factoryBrowser.Browser;
import io.restassured.RestAssured;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pom.LoginPage;
import random.UserFactory;
import user.ApiUser;
import user.AppConfig;
import user.User;
import org.aeonbits.owner.ConfigFactory;
import java.util.Map;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.response.Response;

public class LoginTest {

    private WebDriver webDriver;
    private ApiUser apiUser = new ApiUser();
    private User user;
    private AppConfig appConfig;


    @BeforeEach
    public void setUP() {
        String browser = System.getProperty("browser", "chrome");
        appConfig = ConfigFactory.create(AppConfig.class, Map.of("env", browser));
        RestAssured.baseURI= "https://stellarburgers.education-services.ru";

        webDriver = new Browser().getWebDriver(browser);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        this.user = UserFactory.createRandom();
        Response response = (Response) apiUser.createNewUserStep(this.user);
        assertEquals(200, response.statusCode(), "Не удалось создать пользователя через API");

    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void testLoginFromMainPage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.open()
                .clickLoginButton()
                .enterEmailInputLogin(user.getEmail())
                .enterPasswordInputLogin(user.getPassword())
                .clickButtonLoginMainPage()
                .textButtonOrder();
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void testLoginUserAccount() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.open()
                .clickPersonalAccount()
                .enterEmailInputLogin(user.getEmail())
                .enterPasswordInputLogin(user.getPassword())
                .clickButtonLoginMainPage()
                .textButtonOrder();
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void testLoginButtonRegistration() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.open()
                .clickPersonalAccount()
                .clickButtonRegister()
                .clickButtonLoginRegistr()
                .enterEmailInputLogin(user.getEmail())
                .enterPasswordInputLogin(user.getPassword())
                .clickButtonLoginMainPage()
                .textButtonOrder();
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void testLoginButtonResetPassword() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.open()
                .clickPersonalAccount()
                .clickResetPassword()
                .clickButtonLoginRegistr()
                .enterEmailInputLogin(user.getEmail())
                .enterPasswordInputLogin(user.getPassword())
                .clickButtonLoginMainPage()
                .textButtonOrder();
    }


    @AfterEach
    public void tearDown() {
        webDriver.quit();
    }
}
