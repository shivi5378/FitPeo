package FitPeo.FitPeo;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;
import org.monte.screenrecorder.ScreenRecorder;
import org.testng.annotations.*;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Tstng {

    static WebDriver driver;
    static ExtentReports extent;
    static ExtentTest logger;

    @BeforeClass
    public void setup() {
    	
        // Initialize ExtentReports
        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("test-output/asgn_FITPEO5.html");
        extent.attachReporter(spark);

        // Initialize WebDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void navigateToHomePage() throws IOException {
        logger = extent.createTest("Navigate to FitPeo Homepage");
        driver.get("https://fitpeo.com");
        takeScreenshot("HomePage");

        String pageTitle = driver.getTitle();
        if (pageTitle.contains("Remote Patient")) {
            logger.pass("Successfully navigated to the FitPeo Homepage.");
        } else {
            logger.fail("Failed to navigate to the FitPeo Homepage. Title: " + pageTitle);
        }
    }

    @Test(priority = 2, dependsOnMethods = "navigateToHomePage")
    public void navigateToRevenueCalculatorPage() throws IOException {
        logger = extent.createTest("Navigate to Revenue Calculator Page");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement revenueCalculatorLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,'/revenue-calculator')]")));
        revenueCalculatorLink.click();
        takeScreenshot("RevenueCalculatorPage");

        WebElement revenueCalculatorTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Revenue Calculator')]")));
        if (revenueCalculatorTitle.isDisplayed()) {
            logger.pass("Successfully navigated to the Revenue Calculator Page.");
        } else {
            logger.fail("Failed to navigate to the Revenue Calculator Page.");
        }
    }

    @Test(priority = 3, dependsOnMethods = "navigateToRevenueCalculatorPage")
    public void handleSliderAndVerify() throws IOException, InterruptedException {
        logger = extent.createTest("Handle Slider and Verify Value");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement sliderSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='MuiTypography-root MuiTypography-h4 crimsonPro css-12siehf']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sliderSection);
        takeScreenshot("SliderSection");

        WebElement sliderHandle = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='range'][aria-valuenow='200']")));
        Actions actions = new Actions(driver);
        actions.dragAndDropBy(sliderHandle, 94, 0).perform();
        takeScreenshot("SliderMoved");

        String updatedValue = sliderHandle.getAttribute("value");
        if (updatedValue.equals("822")) {
            logger.pass("Slider value correctly updated to 822.");
        } else {
            logger.fail("Expected 822, but found " + updatedValue);
        }
    }

    @Test(priority = 4, dependsOnMethods = "handleSliderAndVerify")
    public void handleCheckboxes() throws IOException {
        logger = extent.createTest("Handle Checkboxes");

        clickCheckbox(By.xpath("(//input[@type='checkbox'])[1]"));
        clickCheckbox(By.xpath("(//input[@type='checkbox'])[2]"));
        clickCheckbox(By.xpath("(//input[@type='checkbox'])[3]"));
        clickCheckbox(By.xpath("(//*[@type='checkbox'])[8]"));
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();

        // Finalize the report
        extent.flush();
        
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
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File("test-output/screenshots/" + screenshotName + ".png");
            FileUtils.copyFile(srcFile, destFile);

            logger.addScreenCaptureFromPath(destFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
