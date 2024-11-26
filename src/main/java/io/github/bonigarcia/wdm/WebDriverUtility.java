package io.github.bonigarcia.wdm;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverUtility {
    public static WebDriver getDriver() {
        // Set up the WebDriver for Chrome
        WebDrivermanager.chromedriver().setup();

        // Create a new ChromeDriver instance
        WebDriver driver = new ChromeDriver();

        // Maximize the browser window
        driver.manage().window().maximize();

        return driver;
    }
}

