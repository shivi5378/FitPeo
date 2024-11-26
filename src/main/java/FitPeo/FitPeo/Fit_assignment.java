package FitPeo.FitPeo;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.time.Duration; 
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;

public class Fit_assignment {

    static WebDriver driver;
    static ExtentReports extent;
    static ExtentTest logger;

    public static void main(String[] args) throws InterruptedException, IOException {
        // Initialize ExtentReports
        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("test-output/fitpeoassn.html");
        extent.attachReporter(spark);

        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Start the test log
            logger = extent.createTest("FitPeo Slider Test");

            // Step 1: Navigate to the FitPeo Homepage
            driver.get("https://fitpeo.com");
            driver.manage().window().maximize();
            takeScreenshot("HomePage");
            logger.info("Navigated to FitPeo Homepage");

            // Step 2: Navigate to the Revenue Calculator Page
            WebElement revenueCalculatorLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,'/revenue-calculator')]")));
            revenueCalculatorLink.click();
            takeScreenshot("RevenueCalculatorPage");
            logger.info("Navigated to Revenue Calculator Page");

            // Step 3: Scroll to the Slider Section
            WebElement sliderSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='MuiTypography-root MuiTypography-h4 crimsonPro css-12siehf']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sliderSection);
            takeScreenshot("SliderSection");
            logger.info("Scrolled to the slider section.");

            // Step 4: Locate the Slider Handle (Thumb)
            WebElement sliderHandle = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='range'][aria-valuenow='200']")));

            // Step 5: Use Actions class to drag the slider
            Actions actions = new Actions(driver);
            actions.dragAndDropBy(sliderHandle, 94, 0).perform();
            takeScreenshot("SliderMoved");
            logger.info("Slider moved to the target position.");

            // Verify the updated value in the input field below the slider
            String updatedValue = sliderHandle.getAttribute("value");
            logger.info("Updated Slider Value: " + updatedValue);
            if (updatedValue.equals("822")) {
                logger.pass("Slider value correctly updated to 822.");
            } else {
                logger.fail("Expected 822, but found " + updatedValue);
            }

            // Step 6: Set a New Value in the Slider Input Field
            WebElement sliderInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@type='number']")));
            setSliderValue(sliderInput, "560");

            // Step 7: Handle Checkbox Interactions
            clickCheckbox(By.xpath("(//input[@type='checkbox'])[1]"));
            clickCheckbox(By.xpath("(//input[@type='checkbox'])[2]"));
            clickCheckbox(By.xpath("(//input[@type='checkbox'])[3]"));
            clickCheckbox(By.xpath("(//*[@type='checkbox'])[8]"));
            WebElement textField2 = driver.findElement(By.xpath("//*[@type='number']"));
            // Clear existing value and set a new value
            textField2.sendKeys(Keys.CONTROL + "a"); // Select all text
            textField2.sendKeys(Keys.DELETE); // Clear text
            textField2.sendKeys("820"); // Enter the desired value
            Thread.sleep(2000);
            logger.info("Slider value successfully updated to 820.");
            takeScreenshot("SliderValue820");

            // Step 8: Validate Total Recurring Reimbursement
            WebElement totalRecurringReimbursement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//p[@class='MuiTypography-root MuiTypography-body1 inter css-1bl0tdj'])[4]")));
            String totalValue = totalRecurringReimbursement.getText();
            logger.info("Total Recurring Reimbursement displayed: " + totalValue);
            if (totalValue.equals("$110700")) {
                logger.pass("Total recurring reimbursement is correctly displayed as $110700.");
            } else {
                logger.fail("Expected '$110700', but found '" + totalValue + "'.");
            }

        } catch (Exception e) {
            logger.fail("Test failed due to exception: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close the browser after test completion
            driver.quit();
            extent.flush(); // Finalize the report
        }
    }

    // Method to set a value in the slider input field
    public static void setSliderValue(WebElement sliderInput, String value) throws InterruptedException {
        sliderInput.clear();
        sliderInput.sendKeys(value);
        Thread.sleep(2000);
        logger.info("Slider value successfully updated to " + value + ".");
        takeScreenshot("SliderValueUpdatedTo" + value);
    }

    // Method to click a checkbox
    public static void clickCheckbox(By checkboxLocator) {
        WebElement checkbox = driver.findElement(checkboxLocator);
        checkbox.click();
        logger.info("Checkbox clicked.");
        takeScreenshot("CheckboxClicked");
    }

    // Method to capture a screenshot
    public static void takeScreenshot(String screenshotName) {
        try {
            // Capture screenshot
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File("test-output/screenshots/" + screenshotName + ".png");
            FileUtils.copyFile(srcFile, destFile);

            // Attach screenshot to ExtentReports
            logger.addScreenCaptureFromPath(destFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}