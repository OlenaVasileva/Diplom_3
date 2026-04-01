package pom;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
    private final String url = "https://stellarburgers.education-services.ru/";
    private final WebDriver webDriver;


    //Регистрация

    private final By userAccount = By.xpath("//a[@class='AppHeader_header__link__3D_hX' and @href='/account']");
    private final By buttonRegister = By.xpath("//a[@class='Auth_link__1fOlj']");
    private final By nameInput = By.xpath("//input[@name='name']");
    private final By emailInput = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordInput = By.xpath("//input[contains(@class, 'input__textfield') and @type='password']");
    private final By buttonLoginSignInForm = By.xpath("//button[contains(@class,'button_button__33qZ0') and text()='Зарегистрироваться']");
    private final By textLogin = By.xpath("//h2[contains(text(), 'Вход')]");
    private final By errorMessage = By.xpath("//p[contains(@class,'input__error') and text() ='Некорректный пароль']");

    public RegistrationPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public RegistrationPage open() {
        webDriver.get(url);
        return this;
    }

    //Клик по кнопке Личный кабинет
    public RegistrationPage clickUserAccount() {
        webDriver.findElement(userAccount).click();
        return this;
    }

    public RegistrationPage clickButtonRegister() {
        webDriver.findElement(buttonRegister).click();
        return this;
    }

    public RegistrationPage enterNameInput(String text) {
        webDriver.findElement(nameInput).sendKeys(text);
        return this;
    }

    public RegistrationPage enterEmailInput(String text) {
        webDriver.findElement(emailInput).sendKeys(text);
        return this;
    }

    public RegistrationPage enterPasswordInput(String text) {
        webDriver.findElement(passwordInput).sendKeys(text);
        return this;
    }

    public RegistrationPage clickButtonLoginSignInForm() {
        webDriver.findElement(buttonLoginSignInForm).click();
        return this;
    }

    public RegistrationPage textTextLogin() {
        String actualText = webDriver.findElement(textLogin).getText();
        Assertions.assertEquals("Вход", actualText, "Текст не совпадает с ожидаемым");
        return this;
    }

    public RegistrationPage textErrorMessage() {
        String actualText = webDriver.findElement(errorMessage).getText();
        Assertions.assertEquals("Некорректный пароль", actualText, "Текст не совпадает с ожидаемым");
        return this;
    }

}

