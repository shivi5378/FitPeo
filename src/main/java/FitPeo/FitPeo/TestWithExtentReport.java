package FitPeo.FitPeo;


	import com.aventstack.extentreports.ExtentReports;
	import com.aventstack.extentreports.ExtentTest;
	import com.aventstack.extentreports.Status;
	import io.github.bonigarcia.wdm.WebDriverManager;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;

	public class TestWithExtentReport {
	    public static void main(String[] args) {
	        // Initialize Extent Reports
	        ExtentReports extent = ExtentReportManager.getExtentReports();
	        ExtentTest test = ExtentReportManager.createTest("Verify Page Title");

	        WebDriver driver = null;
	        try {
	            // Set up WebDriver
	            WebDriverManager.chromedriver().setup();
	            driver = new ChromeDriver();

	            test.log(Status.INFO, "Launching browser");

	            // Navigate to website
	            driver.get("https://www.example.com");
	            test.log(Status.INFO, "Navigated to example.com");

	            // Verify page title
	            String pageTitle = driver.getTitle();
	            if (pageTitle.equals("Example Domain")) {
	                test.log(Status.PASS, "Page title verified: " + pageTitle);
	            } else {
	                test.log(Status.FAIL, "Page title mismatch. Found: " + pageTitle);
	            }
	        } catch (Exception e) {
	            test.log(Status.ERROR, "Test failed due to exception: " + e.getMessage());
	        } finally {
	            // Close browser
	            if (driver != null) {
	                driver.quit();
	            }
	            test.log(Status.INFO, "Browser closed");

	            // Flush reports
	            ExtentReportManager.flushReports();
	        }
	    }
	}



