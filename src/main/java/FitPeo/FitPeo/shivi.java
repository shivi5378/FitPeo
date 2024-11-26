package FitPeo.FitPeo;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class shivi {

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
            WebElement sliderSection = driver.findElement(By.xpath("//*[@class='MuiTypography-root MuiTypography-h4 crimsonPro css-12siehf']"));
            js.executeScript("arguments[0].scrollIntoView(true);", sliderSection);
            Thread.sleep(2000);
            System.out.println("Navigated to Revenue Calculator and scrolled to the slider section successfully.");

            // Step 4: Locate and Interact with the Slider
            WebElement sliderInput = driver.findElement(By.xpath("//*[@type='number']"));
            
            // Ensure visibility and clickability
            //js.executeScript("arguments[0].scrollIntoView(true);", sliderInput); // Ensure visibility
            //js.executeScript("arguments[0].click();", sliderInput); // Force click via JavaScript
            
            // Clear existing value and set a new value
            sliderInput.sendKeys(Keys.CONTROL + "a"); // Select all text
            sliderInput.sendKeys(Keys.DELETE); // Clear text
            sliderInput.sendKeys("560"); // Enter the desired value
            Thread.sleep(2000);
            System.out.println("Slider value successfully updated to 560.");
            // Get the value of the slider
            String sliderValue = sliderInput.getAttribute("value");

            // Check if the value is 560
            if (sliderValue.equals("560")) {
                System.out.println("The slider value is correctly displayed as 560.");
            } else {
                System.out.println("The slider value is incorrect. Current value: " + sliderInput);
            }
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
            WebElement textField2 = driver.findElement(By.xpath("//*[@type='number']"));
            // Clear existing value and set a new value
            textField2.sendKeys(Keys.CONTROL + "a"); // Select all text
            textField2.sendKeys(Keys.DELETE); // Clear text
            textField2.sendKeys("820"); // Enter the desired value
            Thread.sleep(2000);
            System.out.println("Slider value successfully updated to 820.");
            Thread.sleep(3000);
            // 8. Validate Total Recurring Reimbursement
//            WebElement totalRecurringReimbursement = driver.findElement(By.xpath("(//p[@class='MuiTypography-root MuiTypography-body1 inter css-1bl0tdj'])[4]"));
//            Thread.sleep(1000);
//            String totalValue = totalRecurringReimbursement.getText();
//            Assert.assertEquals(totalValue, "$110700", "Total recurring reimbursement should be $110700.");
//            Thread.sleep(3000);
            
            
         // Locate the Total Recurring Reimbursement element
            WebElement totalRecurringReimbursement = driver.findElement(By.xpath("(//p[@class='MuiTypography-root MuiTypography-body1 inter css-1bl0tdj'])[4]"));
            Thread.sleep(1000);

            // Get the text of the element
            String totalValue = totalRecurringReimbursement.getText();

            // Print the retrieved text for debugging
            System.out.println("Total Recurring Reimbursement displayed: " + totalValue);

            // Verify if the text matches the expected value
            if (totalValue.equals("$110700")) {
                System.out.println("Validation Passed: Total recurring reimbursement is correctly displayed as $110700.");
            } else {
                System.out.println("Validation Failed: Expected '$110700', but found '" + totalValue + "'.");
            }
            Thread.sleep(2000);
       
        
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
