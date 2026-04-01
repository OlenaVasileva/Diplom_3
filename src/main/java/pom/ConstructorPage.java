package pom;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConstructorPage {
    final String url = "https://stellarburgers.education-services.ru/";
    private final WebDriver webDriver;

    private final By createBurger = By.xpath("//h1[text()='Соберите бургер']");
    private final By sauces = By.xpath("//span[text()='Соусы']");
    private final By sauceSpicy = By.xpath("//p[contains(text(), 'Spicy-X')]");
    private final By filling = By.xpath("//span[text()='Начинки']");
    private final By fillingMeteorit = By.xpath("//p[contains(text(), 'метеорит')]");
    private final By buns = By.xpath("//span[text()='Булки']");
    private final By bunsKrator = By.xpath("//p[contains(text(), 'Краторная')]");

    public ConstructorPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public ConstructorPage open() {
        webDriver.get(url);
        return this;
    }

    public ConstructorPage textCreateBurger() {
        String actualText = webDriver.findElement(createBurger).getText();
        Assertions.assertEquals("Соберите бургер", actualText, "Текст не совпадает с ожидаемым");
        return this;
    }

    public ConstructorPage clickSauces() {
        webDriver.findElement(sauces).click();
        return this;
    }

    public ConstructorPage textSauceSpicy() {
        String actualText = webDriver.findElement(sauceSpicy).getText();
        Assertions.assertEquals("Соус Spicy-X", actualText, "Текст не совпадает с ожидаемым");
        return this;
    }

    public ConstructorPage clickFilling() {
        webDriver.findElement(filling).click();
        return this;
    }

    public ConstructorPage textFillingMeteorit() {
        String actualText = webDriver.findElement(fillingMeteorit).getText();
        Assertions.assertEquals("Говяжий метеорит (отбивная)", actualText, "Текст не совпадает с ожидаемым");
        return this;
    }

    public ConstructorPage clickBuns() {
        webDriver.findElement(buns).click();
        return this;
    }

    public ConstructorPage textBunsKrator() {
        String actualText = webDriver.findElement(bunsKrator).getText();
        Assertions.assertEquals("Краторная булка N-200i", actualText, "Текст не совпадает с ожидаемым");
        return this;
    }
}
