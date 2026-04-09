package factoryBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Browser {
   public WebDriver getWebDriver(String browserName) {
      switch (browserName) {
          case "chrome":
              return new ChromeDriver();
          case "firefox":
              return new FirefoxDriver();
          default:
              throw new IllegalArgumentException(
                      "Браузер '" + browserName + "' не поддерживается. Используйте 'chrome' или 'firefox'."
              );
      }
   }
}

