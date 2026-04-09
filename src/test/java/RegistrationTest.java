import factoryBrowser.Browser;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pom.RegistrationPage;
import random.UserFactory;
import user.*;

import java.time.Duration;
import java.util.Map;



public class RegistrationTest {
    private WebDriver webDriver;
    private AppConfig appConfig;
    private ApiUser apiUser = new ApiUser();
    private User user;
    private String accessToken;
    private String randomName;
    private String randomEmail;
    private String randomPassword;
    private String randomNegativePassword;


    @BeforeEach
    public void setUP() {
        String browser = System.getProperty("browser", "chrome");
        appConfig = ConfigFactory.create(AppConfig.class, Map.of("env", browser));


        webDriver = new Browser().getWebDriver(browser);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        this.user = UserFactory.createRandom();

        this.randomName = user.getName();
        this.randomEmail = user.getEmail();
        this.randomPassword = user.getPassword();
        this.randomNegativePassword = user.getNegativePassword();

    }

    @Test
    @DisplayName("Успешная регистрация")
    public void testRegistration() {
        RegistrationPage registrationPage = new RegistrationPage(webDriver);
        registrationPage.open()
                .clickUserAccount()
                .clickButtonRegister()
                .enterNameInput(randomName)
                .enterEmailInput(randomEmail)
                .enterPasswordInput(randomPassword)
                .clickButtonLoginSignInForm()
                .textTextLogin();

        UserCreds creds = new UserCreds(randomEmail, randomPassword);
        Response loginResponse = apiUser.loginUserStep(creds);
        accessToken = loginResponse.as(UserLoginResponse.class).getAccessToken();
        System.out.println(loginResponse.body().asString());
    }

    @Test
    @DisplayName("Не правильный пароль")
    public void testErrorPassword() {
        RegistrationPage registrationPage = new RegistrationPage(webDriver);
        registrationPage.open()
                .clickUserAccount()
                .clickButtonRegister()
                .enterNameInput(randomName)
                .enterEmailInput(randomEmail)
                .enterPasswordInput(randomNegativePassword)
                .clickButtonLoginSignInForm()
                .textErrorMessage();

    }

    @AfterEach
    public void tearDown() {
        if (accessToken != null) {
            apiUser.deleteUserStep(accessToken);
        }
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
