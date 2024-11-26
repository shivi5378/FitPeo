package FitPeo.FitPeo;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

public class Sample {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Set up the Chrome WebDriver
        //System.setProperty("webdriver.chrome.driver", "path_to_chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testRevenueCalculator() throws InterruptedException {
        // 1. Navigate to FitPeo Homepage
        driver.get("https://fitpeo.com");
        Thread.sleep(2000);
        
        // 2. Navigate to the Revenue Calculator Page (assumes there's a link or button to click)
        WebElement revenueCalculatorLink = driver.findElement(By.xpath("//a[@href='/revenue-calculator']"));
        revenueCalculatorLink.click();
        Thread.sleep(2000);
        
        // 3. Scroll Down to the Slider section
        WebElement sliderSection = driver.findElement(By.xpath("//*[@class='MuiBox-root css-79elbk']")); // Assuming the slider section has an ID
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sliderSection);
        
        // 4. Adjust the Slider to value 820
        WebElement slider = driver.findElement(By.xpath("//span[@class='MuiSlider-thumb MuiSlider-thumbSizeMedium MuiSlider-thumbColorPrimary MuiSlider-thumbSizeMedium MuiSlider-thumbColorPrimary css-1sfugkh']\r\n"
        		+ ""));
        Actions actions = new Actions(driver);
        actions.clickAndHold(slider).moveByOffset(200, 0).release().perform(); // Adjust this based on the page design
        WebElement bottomTextField = driver.findElement(By.xpath("//div[@class= 'MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary MuiInputBase-formControl MuiInputBase-sizeSmall css-129j43u']"));
        String textValue = bottomTextField.getAttribute("820");
        Assert.assertEquals(textValue, "820", "Slider value should be 820 after adjustment.");
        Thread.sleep(2000);
        // 5. Update the Text Field to 560
        bottomTextField.click();
        bottomTextField.clear();
        bottomTextField.sendKeys("560");
        
        // 6. Validate Slider Value Update
        String updatedSliderValue = bottomTextField.getAttribute("value");
        WebElement updatedSlider = driver.findElement(By.xpath("//input[@type='range']"));
        Assert.assertEquals(updatedSliderValue, "560", "Slider value should reflect 560 after text field input.");
        
        // 7. Select CPT Codes
        WebElement cpt99091Checkbox = driver.findElement(By.id("CPT-99091"));
        WebElement cpt99453Checkbox = driver.findElement(By.id("CPT-99453"));
        WebElement cpt99454Checkbox = driver.findElement(By.id("CPT-99454"));
        WebElement cpt99474Checkbox = driver.findElement(By.id("CPT-99474"));
        
        cpt99091Checkbox.click();
        cpt99453Checkbox.click();
        cpt99454Checkbox.click();
        cpt99474Checkbox.click();
        
        // 8. Validate Total Recurring Reimbursement
        WebElement totalRecurringReimbursement = driver.findElement(By.id("totalRecurringReimbursement"));
        String totalValue = totalRecurringReimbursement.getText();
        Assert.assertEquals(totalValue, "$110700", "Total recurring reimbursement should be $110700.");
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}
