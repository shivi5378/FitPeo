package FitPeo.FitPeo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class FitPeoTest {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Set up the ChromeDriver path if necessary
        // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testRevenueCalculatorNavigation() {
        try {
            // Step 1: Navigate to the FitPeo Homepage
            driver.get("https://fitpeo.com"); // Replace with the actual FitPeo homepage URL

            // Wait for the page to load
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement revenueCalculatorLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/revenue-calculator']")));
            revenueCalculatorLink.click();

            // Step 2: Wait for the Revenue Calculator page to load
            wait.until(ExpectedConditions.urlContains("/revenue-calculator"));
            Thread.sleep(2000);
            
            // 3. Scroll Down to the Slider section
            WebElement sliderSection = driver.findElement(By.xpath("//*[@class='MuiBox-root css-79elbk']"));    //span[contains(@class, 'MuiSlider-rail')]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sliderSection);

            // Assertion: Check if the slider section is displayed
            Assert.assertTrue(sliderSection.isDisplayed(), "Slider section is not displayed.");
            System.out.println("Navigated to Revenue Calculator and scrolled to the slider section successfully.");
            Thread.sleep(2000);
            
            // 4. Adjust the Slider to value 820
//            WebElement sliderbtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span.MuiSlider-thumb.css-1sfugkh")));
//            sliderbtn.click();
           // Thread.sleep(2000);
            // Determine the slider's width and calculate the offset for 820
            WebElement sliderTrack = driver.findElement(By.cssSelector("span.MuiSlider-track.css-10opxo5[style*='width: 41%']"));
            int trackWidth = sliderTrack.getSize().getWidth(); // Get the width of the slider track
            int maxValue = 2000; // Maximum value of the slider (this should be retrieved from the HTML if dynamic)
            
            // Calculate the offset (how far the thumb needs to be moved to reach 820)
            int targetValue = 820;
            int offset = (int) ((targetValue / (double) maxValue) * trackWidth);
            
            WebElement slider = driver.findElement(By.cssSelector("span.MuiSlider-thumb.css-1sfugkh"));
            Actions actions = new Actions(driver);
            actions.clickAndHold(slider).moveByOffset(offset, 0).release().perform(); // Adjust based on actual UI behavior

            // Get the value from the bottom text field
            WebElement bottomTextField = driver.findElement(By.xpath("//*[@class='MuiFormControl-root MuiTextField-root css-1s5tg4z']"));
            String textValue = bottomTextField.getAttribute("value");
            Thread.sleep(2000);

            // Assertion: Validate if the value is set correctly to 820
            Assert.assertEquals(textValue, "820", "Slider value should be 820 after adjustment.");
            Thread.sleep(2000);
            
            // 5. Adjust the Text Field directly to 560
            bottomTextField.clear();
            bottomTextField.sendKeys("560");

            // Wait for the slider to update based on the text field input
            WebElement updatedSlider = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class, 'MuiSlider-thumb')]")));
            Assert.assertTrue(updatedSlider.isDisplayed(), "Slider did not update based on the text field input.");

            // Validate the new value in the text field
            String updatedTextValue = bottomTextField.getAttribute("value");
            Assert.assertEquals(updatedTextValue, "560", "Text field value should be 560 after input.");

            // Wait for the page to reflect changes
            Thread.sleep(1000); // Optionally, replace with WebDriverWait

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test case failed due to an exception.");
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
