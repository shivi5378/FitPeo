package io.github.bonigarcia.wdm;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDrivermanager {
    public static void main(String[] args) {
        // Step 1: Set up WebDriverManager for Chrome
        WebDriver.chromedriver().setup();

        // Step 2: Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        // Step 3: Open a website
        driver.get("https://www.example.com");

        // Step 4: Print the page title
        System.out.println("Page Title: " + driver.getTitle());

        // Step 5: Close the browser
        driver.quit();
    }
}
