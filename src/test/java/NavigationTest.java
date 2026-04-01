import factoryBrowser.Browser;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pom.NavigationPage;
import random.UserFactory;
import user.ApiUser;
import user.AppConfig;
import user.User;

import java.time.Duration;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class NavigationTest {
    private WebDriver webDriver;
    private ApiUser apiUser = new ApiUser();
    private User user;
    private AppConfig appConfig;


    @BeforeEach
    public void setUp() {
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
    @DisplayName("Переход в личный кабинет")
    public void testGoToPersonalAccount() {
        NavigationPage navigationPage = new NavigationPage(webDriver);
        navigationPage.open()
                .clickLoginButton()
                .enterEmailInputLogin(user.getEmail())
                .enterPasswordInputLogin(user.getPassword())
                .clickButtonLogin2()
                .clickPersonalAccount()
                .textNameProfile();
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор ")
    public void testGoToConstructor() {
        NavigationPage navigationPage = new NavigationPage(webDriver);
        navigationPage.open()
                .clickLoginButton()
                .enterEmailInputLogin(user.getEmail())
                .enterPasswordInputLogin(user.getPassword())
                .clickButtonLogin2()
                .clickPersonalAccount()
                .clickConstructor()
                .textButtonOrder();
    }

    @Test
    @DisplayName("Выход из аккаунта")
    public void testExitAccount() {
        NavigationPage navigationPage = new NavigationPage(webDriver);
        navigationPage.open()
                .clickLoginButton()
                .enterEmailInputLogin(user.getEmail())
                .enterPasswordInputLogin(user.getPassword())
                .clickButtonLogin2()
                .clickPersonalAccount()
                .clickButtonExit()
                .textTextLogin();

    }

    @AfterEach
    public void tearDown() {
        webDriver.quit();
    }
}
