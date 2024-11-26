package FitPeo.FitPeo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class senkeys {
	
	    public static void main(String[] args) throws InterruptedException {
	    	// System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
	         WebDriver driver = new ChromeDriver();

	         
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
	          //   WebElement slider = driver.findElement(By.cssSelector(".MuiSlider-thumb.MuiSlider-thumbSizeMedium.MuiSlider-thumbColorPrimary"));
	          // Locate the text field associated with the slider
	             WebElement textFields = driver.findElement(By.xpath("//*[@type='number']")); // Adjust locator as needed
	             textFields.click();
	             textFields.clear();
	             textFields.sendKeys("560");
	             Thread.sleep(2000);
	             // Click on the text field
	           //WebElement patientstxt =
	             //driver.findElement(By.xpath("")).sendKeys("560");
	             //Thread.sleep(1000);
	           //patientstxt.click();
	          // patientstxt.sendKeys("560");
	             // Clear the text field and enter the value 560
	             //textFields.clear();
	           //patientstxt.sendKeys("560");

	             // Optionally, wait for the slider to update
	             //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	             //Thread.sleep(2000);
	          // Locate the slider element
	             WebElement slider = driver.findElement(By.cssSelector("/html/body/div[1]/div[1]/div[1]/div[2]/div/div/span[1]/span[3]")); // Adjust locator as needed
	             boolean isUpdated = false;
	             for (int i = 0; i < 20; i++) { // Try for up to 10 seconds
	                 if (slider.getAttribute("aria-valuenow").equals("560")) {
	                     isUpdated = true;
	                     break;
	                 }
	                 Thread.sleep(500); // Wait 500ms before re-checking
	             }
	             if (isUpdated) {
	                 System.out.println("Slider value updated to 560.");
	             } else {
	                 System.out.println("Failed to update slider value.");
	             }

//	             // Wait until the slider's value is updated to 560
//	             wait.until(ExpectedConditions.attributeToBe(slider, "aria-valuenow", "560"));
//
//	             // Print the success message
//	             System.out.println("Slider value updated to 560.");
	          // 7. Select CPT Codes
	             WebElement cpt99091Checkbox = driver.findElement(By.xpath("(//input[@type='checkbox'])[1]"));
	             WebElement cpt99453Checkbox = driver.findElement(By.xpath("(//input[@type='checkbox'])[2]"));
	             WebElement cpt99454Checkbox = driver.findElement(By.xpath("(//input[@type='checkbox'])[3]"));
	             WebElement cpt99474Checkbox = driver.findElement(By.xpath("(//input[@type='checkbox'])[4]"));
	             
	             cpt99091Checkbox.click();
	             cpt99453Checkbox.click();
	             cpt99454Checkbox.click();
	             cpt99474Checkbox.click();
	             Thread.sleep(2000);
	             
	             // 8. Validate Total Recurring Reimbursement
	             WebElement totalRecurringReimbursement = driver.findElement(By.id("totalRecurringReimbursement"));
	             String totalValue = totalRecurringReimbursement.getText();
	             Assert.assertEquals(totalValue, "$110700", "Total recurring reimbursement should be $110700.");
	         

	             driver.quit();
	             
	         }
	    }
//	    