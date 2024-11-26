package FitPeo.FitPeo;

import java.io.File;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class data {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        try {
            // Step 1: Navigate to the FitPeo Homepage
            driver.get("https://fitpeo.com"); // Replace with the actual FitPeo homepage URL
            driver.manage().window().maximize();
            Thread.sleep(2000);

            // Step 2: Navigate to the Revenue Calculator Page
            WebElement revenueCalculatorLink = driver.findElement(By.xpath("//a[@href='/revenue-calculator']"));
            revenueCalculatorLink.click();
            Thread.sleep(2000);

            // Step 3: Scroll to Slider Section
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement sliderSection = driver.findElement(By.xpath("//div[@class='MuiBox-root css-79elbk']"));
            js.executeScript("arguments[0].scrollIntoView(true);", sliderSection);
            System.out.println("Navigated to Revenue Calculator and scrolled to the slider section successfully.");

            // Step 4: Locate and Interact with the Slider
            WebElement sliderInput = driver.findElement(By.xpath("//*[@type='number']"));
            
            // Ensure visibility and clickability
            js.executeScript("arguments[0].scrollIntoView(true);", sliderInput); // Ensure visibility
            js.executeScript("arguments[0].click();", sliderInput); // Force click via JavaScript
            
            // Clear existing value and set a new value
            sliderInput.sendKeys(Keys.CONTROL + "a"); // Select all text
            sliderInput.sendKeys(Keys.DELETE); // Clear text
            sliderInput.sendKeys("560"); // Enter the desired value
            System.out.println("Slider value successfully updated to 560.");
            Thread.sleep(3000);

            // Step 5: Handle Checkbox Interactions
            WebElement cpt99091Checkbox = driver.findElement(By.xpath("(//input[@type='checkbox'])[1]"));
            WebElement cpt99453Checkbox = driver.findElement(By.xpath("(//input[@type='checkbox'])[2]"));
            WebElement cpt99454Checkbox = driver.findElement(By.xpath("(//input[@type='checkbox'])[3]"));
            WebElement cpt99474Checkbox = driver.findElement(By.xpath("(//*[@type='checkbox'])[8]"));

            cpt99091Checkbox.click();
            cpt99453Checkbox.click();
            cpt99454Checkbox.click();
            cpt99474Checkbox.click();
            Thread.sleep(2000);
            
            

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
