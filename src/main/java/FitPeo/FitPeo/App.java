package FitPeo.FitPeo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

/**
 * Hello world!
 *
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class App {
    public static void main(String[] args) {
    	// System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
         WebDriver driver = new ChromeDriver();

         try {
             // Step 1: Navigate to the FitPeo Homepage
             driver.get("https://fitpeo.com"); // Replace with the actual FitPeo homepage URL
             driver.manage().window().maximize();
             Thread.sleep(2000);

             // Step 2: Navigate to the Revenue Calculator Page
             WebElement revenueCalculatorLink = driver.findElement(By.xpath("//a[@href= '/revenue-calculator']")); // Adjust locator as needed
             revenueCalculatorLink.click();
             Thread.sleep(2000);

             // Step 3: Scroll Down to the Slider section
             JavascriptExecutor js = (JavascriptExecutor) driver;
             WebElement sliderSection = driver.findElement(By.xpath("//div[@class='MuiBox-root css-79elbk']")); // Replace with the actual slider section ID or locator
             js.executeScript("arguments[0].scrollIntoView(true);", sliderSection);
             Thread.sleep(2000);
             System.out.println("Navigated to Revenue Calculator and scrolled to the slider section successfully.");
             
          // Locate the slider element
             WebElement slider = driver.findElement(By.cssSelector(".MuiSlider-thumb.MuiSlider-thumbSizeMedium.MuiSlider-thumbColorPrimary"));

          // Calculate offset
             int sliderWidth = slider.getSize().width;
             int sliderRange = 2000; // Example range
             int desiredValue = 820;
             int currentValue = 200; // Starting value
             int xOffset = (sliderWidth * (desiredValue - currentValue)) / sliderRange;
             
             // Adjust the slider
             Actions actions = new Actions(driver);
             actions.clickAndHold(slider)
                    .moveByOffset(xOffset, 0)
                    .release()
                    .perform();
          // Verify updated value
             WebElement textField = driver.findElement(By.xpath("//div[@class='MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary MuiInputBase-formControl MuiInputBase-sizeSmall css-129j43u']/input"));
             String updatedValue = textField.getAttribute("820");

             if ("820".equals(updatedValue)) {
                 System.out.println("Slider adjusted successfully to: " + updatedValue);
             } else {
                 System.out.println("Slider adjustment failed. Current value: " + updatedValue);
             }
         } catch (Exception e) {
             e.printStackTrace();
         } finally {
             // Close the browser
             driver.quit();
}
    }
}

