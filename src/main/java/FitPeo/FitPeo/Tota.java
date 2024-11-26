package FitPeo.FitPeo;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Tota {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        try {
            // Step 1: Navigate to the FitPeo Homepage
            driver.get("https://fitpeo.com"); // Replace with the actual FitPeo homepage URL
            driver.manage().window().maximize();
            Thread.sleep(2000);

            // Step 2: Navigate to the Revenue Calculator Page
            WebElement revenueCalculatorLink = driver.findElement(By.xpath("//a[contains(@href,'/revenue-calculator')]"));
            revenueCalculatorLink.click();
            Thread.sleep(2000);

            // Step 3: Locate the Slider
//            WebElement slider = driver.findElement(By.cssSelector("input[type='range']"));
//            WebElement sliderTrack = driver.findElement(By.cssSelector("span.MuiSlider-track.css-10opxo5"));
          // Update the XPath to locate the slider track

         // Scroll Down to the Slider Section
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement sliderSection = driver.findElement(By.xpath("//*[@class='MuiTypography-root MuiTypography-h4 crimsonPro css-12siehf']"));
            js.executeScript("arguments[0].scrollIntoView(true);", sliderSection);
            Thread.sleep(2000);
            System.out.println("Scrolled to the slider section.");

            // Locate the slider thumb (the draggable part of the slider)
            WebElement slider = driver.findElement(By.className("MuiSlider-thumb"));

            // Get the slider width dynamically (track width)
            WebElement sliderTrack = driver.findElement(By.className("MuiSlider-track"));
            int sliderTrackWidth = sliderTrack.getSize().getWidth();
            System.out.println("Slider Track Width: " + sliderTrackWidth);

            // Define the slider's range (min, max) and the target value
            WebElement sliderInput = driver.findElement(By.xpath("//input[@type='range']"));
            int minValue = Integer.parseInt(sliderInput.getAttribute("min"));
            int maxValue = Integer.parseInt(sliderInput.getAttribute("max"));
            int targetValue = 820;

            System.out.println("Slider min value: " + minValue + ", max value: " + maxValue);

            // Calculate the ratio of the target value in the range (0-2000)
            double valueRatio = (double) (targetValue - minValue) / (maxValue - minValue);
            System.out.println("Value Ratio: " + valueRatio);

            // Calculate the pixel offset from the slider's current position
            int pixelOffset = (int) (sliderTrackWidth * valueRatio);
            System.out.println("Pixel Offset: " + pixelOffset);

            // Create an instance of Actions class
            Actions actions = new Actions(driver);

            // Drag the slider to the target position by the calculated pixel offset
            actions.dragAndDropBy(slider, pixelOffset, 0).perform();
            Thread.sleep(2000);
            System.out.println("Slider moved to the target position.");

            // Verify the updated value in the input field below the slider
            String updatedValue = sliderInput.getAttribute("value");
            System.out.println("Updated Slider Value: " + updatedValue);

            // Validate if the value is updated to 820
            if (updatedValue.equals("820")) {
                System.out.println("Validation Passed: Slider value updated to 820.");
            } else {
                System.out.println("Validation Failed: Expected 820, but found " + updatedValue);
            }

            driver.quit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Ensure the driver quits
            driver.quit();
        }
    }
}
