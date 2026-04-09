import factoryBrowser.Browser;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pom.ConstructorPage;
import user.AppConfig;


import java.time.Duration;
import java.util.Map;

public class ConstructorTest {
    private WebDriver webDriver;
    private AppConfig appConfig;

    @BeforeEach
    public void setUP() {
        String browser = System.getProperty("browser", "chrome");
        appConfig = ConfigFactory.create(AppConfig.class, Map.of("env", browser));
        RestAssured.baseURI= "https://stellarburgers.education-services.ru";

        webDriver = new Browser().getWebDriver(browser);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    @DisplayName("Переход между размделами конструктора")
    public void testGoToConstructorSauces() {
        ConstructorPage constructorPage = new ConstructorPage(webDriver);
        constructorPage.open()
                .textCreateBurger()
                .clickSauces()
                .textSauceSpicy()
                .clickFilling()
                .textFillingMeteorit()
                .clickBuns()
                .textBunsKrator();
    }

    @AfterEach
    public void tearDown() {
        webDriver.quit();
    }
}
