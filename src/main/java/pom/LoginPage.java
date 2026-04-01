package pom;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final String url = "https://stellarburgers.education-services.ru/";
    private final WebDriver webDriver;


    private final By buttonOrder = By.xpath("//button[contains(@class,'button_button__33qZ0') and text()='Оформить заказ']");
    private final By loginButton = By.xpath("//button[contains(text(), 'Войти в аккаунт')]");
    private final By emailInputLogin = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordInputLogin = By.xpath("//input[contains(@class, 'input__textfield') and @type='password']");
    private final By buttonLoginMainPage = By.xpath("//button[contains(@class,'button_button__33qZ0') and text()='Войти']");
    private final By personalAccount = By.xpath("//a[@class='AppHeader_header__link__3D_hX' and @href='/account']");
    private final By buttonRegister = By.xpath("//a[@class='Auth_link__1fOlj']");
    private final By buttonLoginRegistr = By.xpath("//a[contains(@class, 'Auth_link__1fOlj') and text()='Войти']");
    private final By resetPassword = By.xpath("//a[text()='Восстановить пароль']");

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public LoginPage open() {
        webDriver.get(url);
        return this;
    }

    public LoginPage clickLoginButton() {
        webDriver.findElement(loginButton).click();
        return this;
    }

    public LoginPage enterEmailInputLogin(String text) {
        webDriver.findElement(emailInputLogin).sendKeys(text);
        return this;
    }

    public LoginPage enterPasswordInputLogin(String text) {
        webDriver.findElement(passwordInputLogin).sendKeys(text);
        return this;
    }

    public LoginPage clickButtonLoginMainPage() {
        webDriver.findElement(buttonLoginMainPage).click();
        return this;
    }

    public LoginPage clickButtonLoginRegistr() {
        webDriver.findElement(buttonLoginRegistr).click();
        return this;
    }

    public LoginPage textButtonOrder() {
        String actualText = webDriver.findElement(buttonOrder).getText();
        Assertions.assertEquals("Оформить заказ", actualText, "Текст не совпадает с ожидаемым");
        return this;
    }

    public LoginPage clickPersonalAccount() {
        webDriver.findElement(personalAccount).click();
        return this;
    }

    public LoginPage clickButtonRegister() {
        webDriver.findElement(buttonRegister).click();
        return this;
    }

    public LoginPage clickResetPassword() {
        webDriver.findElement(resetPassword).click();
        return this;
    }

}
