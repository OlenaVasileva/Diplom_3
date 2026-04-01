package pom;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationPage {
    final String url = "https://stellarburgers.education-services.ru/";
    private final WebDriver webDriver;

    private final By personalAccount = By.xpath("//a[@class='AppHeader_header__link__3D_hX' and @href='/account']");
    private final By emailInputLogin = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordInputLogin = By.xpath("//input[contains(@class, 'input__textfield') and @type='password']");
    private final By buttonLogin2 = By.xpath("//button[contains(@class,'button_button__33qZ0') and text()='Войти']");
    private final By buttonOrder = By.xpath("//button[contains(@class,'button_button__33qZ0') and text()='Оформить заказ']");
    private final By nameProfile = By.xpath("//a[contains(text(), 'Профиль')]");
    private final By loginButton = By.xpath("//button[contains(text(), 'Войти в аккаунт')]");
    private final By constructor = By.xpath("//p[contains(text(), 'Конструктор')]");
    private final By buttonExit = By.xpath("//button[text()='Выход']");
    private final By textLogin = By.xpath("//h2[contains(text(), 'Вход')]");

    public NavigationPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public NavigationPage open() {
        webDriver.get(url);
        return this;
    }

    public NavigationPage clickLoginButton() {
        webDriver.findElement(loginButton).click();
        return this;
    }

    public NavigationPage clickPersonalAccount() {
        webDriver.findElement(personalAccount).click();
        return this;
    }

    public NavigationPage enterEmailInputLogin(String text) {
        webDriver.findElement(emailInputLogin).sendKeys(text);
        return this;
    }

    public NavigationPage enterPasswordInputLogin(String text) {
        webDriver.findElement(passwordInputLogin).sendKeys(text);
        return this;
    }

    public NavigationPage clickButtonLogin2() {
        webDriver.findElement(buttonLogin2).click();
        return this;
    }

    public NavigationPage textButtonOrder() {
        String actualText = webDriver.findElement(buttonOrder).getText();
        Assertions.assertEquals("Оформить заказ", actualText, "Текст не совпадает с ожидаемым");
        return this;
    }

    public NavigationPage textNameProfile() {
        String actualText = webDriver.findElement(nameProfile).getText();
        Assertions.assertEquals("Профиль", actualText, "Текст не совпадает с ожидаемым");
        return this;
    }

    public NavigationPage clickConstructor() {
        webDriver.findElement(constructor).click();
        return this;
    }

    public NavigationPage clickButtonExit() {
        webDriver.findElement(buttonExit).click();
        return this;
    }

    public NavigationPage textTextLogin() {
        String actualText = webDriver.findElement(textLogin).getText();
        Assertions.assertEquals("Вход", actualText, "Текст не совпадает с ожидаемым");
        return this;
    }
}
