package FitPeo.FitPeo;


	import com.aventstack.extentreports.ExtentReports;
	import com.aventstack.extentreports.ExtentTest;
	import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
	import com.aventstack.extentreports.reporter.configuration.Theme;

	public class ExtentReportManager {
	    private static ExtentReports extent;
	    private static ExtentHtmlReporter htmlReporter;
	    private static ExtentTest test;

	    public static ExtentReports getExtentReports() {
	        if (extent == null) {
	            // Specify the report file location
	            htmlReporter = new ExtentHtmlReporter("extent-report.html");
	            htmlReporter.config().setTheme(Theme.STANDARD);
	            htmlReporter.config().setDocumentTitle("Automation Report");
	            htmlReporter.config().setReportName("Selenium Automation Testing");

	            extent = new ExtentReports();
	            extent.attachReporter(htmlReporter);

	            // Add system information
	            extent.setSystemInfo("Tester", "Your Name");
	            extent.setSystemInfo("Environment", "QA");
	            extent.setSystemInfo("Browser", "Chrome");
	        }
	        return extent;
	    }

	    public static ExtentTest createTest(String testName) {
	        test = extent.createTest(testName);
	        return test;
	    }

	    public static void flushReports() {
	        if (extent != null) {
	            extent.flush();
	        }
	    }
	}


}
