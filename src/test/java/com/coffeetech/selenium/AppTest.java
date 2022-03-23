package com.coffeetech.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppTest {
	
	private static final Logger logger = LoggerFactory.getLogger(AppTest.class);
	 private final static int SLEEP = Integer.parseInt(System.getProperty("sleep", "10000"));
	
	private static WebDriver driver;
	//private static String hubUrl = "http://10.1.0.127:5555/";
	
	@BeforeAll
	public static void openBrowser() throws MalformedURLException {
		System.setProperty("webdriver.gecko.driver", "geckodriver");
		FirefoxOptions options = new FirefoxOptions();
		options.setPlatformName("Linux");
		options.setHeadless(true);
		//driver = new RemoteWebDriver(new URL(hubUrl), options);
		driver = new FirefoxDriver(options);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@Test
	public void testGoogleWebsite() {
		logger.info("Starting test [{}]", "testGoogleWebsite");
		driver.get("https://google.com");
		logger.info(driver.getTitle());
		assertEquals("Google", driver.getTitle());
		logger.info("Ending test [{}]", "testGoogleWebsite");
	}
	
	@Test
    public void testSearch() throws Exception {
		logger.info("Starting test [{}]", "testSearch");
        // And now use this to visit Google
        driver.get("http://www.google.com");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");

        Thread.sleep(SLEEP);

        // Find the text input element by its name
        WebElement element = driver.findElement(By.name("q"));

        // Enter something to search for
        element.sendKeys("Cheese!");

        Thread.sleep(SLEEP);

        // Now submit the form. WebDriver will find the form for us from the element
        element.submit();

        // Check the title of the page
        logger.info("Page title is:[{}]", driver.getTitle());
       

        // Google's search is rendered dynamically with JavaScript.
        // Wait for the page to load, timeout after 10 seconds
        (new WebDriverWait(driver, Duration.ofSeconds(10))).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("cheese!");
            }
        });

        Thread.sleep(SLEEP);

        // Should see: "cheese! - Google Search"
        logger.info("Page title is: [{}]", driver.getTitle());
        assertEquals("Cheese! - Google Search", driver.getTitle());
        logger.info("Ending test [{}]", "testSearch");
    }
	
	@AfterAll
	public static void closeBrowser() {
		driver.quit();
	}

}
