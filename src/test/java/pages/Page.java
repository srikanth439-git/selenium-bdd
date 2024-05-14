package pages;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

public class Page {
    public static WebDriver driver = null;

    public void openBrowser(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                ChromeOptions option = new ChromeOptions();
                option.addArguments("--remote-allow-origins=*");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(option);

                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "path/to/geckodriver");
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            // Add more cases for other browsers if needed
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);

        }

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    public void waitForElementToBeClickable(WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementToBeVisible(WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public boolean verifyDescendingOrder(List<String> jobDates) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        Date previousDate = null;

        for (String dateString : jobDates) {
            try {
                Date currentDate = dateFormat.parse(dateString);

                if (previousDate != null && currentDate.compareTo(previousDate) > 0) {
                    // Current date is not in descending order
                    return false;
                }

                previousDate = currentDate;
            } catch (ParseException e) {
                e.printStackTrace();

            }
        }

        // All dates are in descending order
        return true;
    }

    public void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollDown() {
        // Execute JavaScript to scroll down the page
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }


    public static WebDriver getDriver() {
        return driver;
    }


}
