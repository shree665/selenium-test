package com.coffeetech.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppTest {
	
	private static final Logger logger = LoggerFactory.getLogger(AppTest.class);
	
	private static FirefoxDriver driver;
	//private WebElement element;
	
	@BeforeAll
	public static void openBrowser() {
		System.setProperty("webdriver.gecko.driver", "geckodriver");
		driver = new FirefoxDriver();
		//driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@Test
	public void testWebsite() {
		logger.info("Starting test [{}]", new Object() {}.getClass().getEnclosingMethod().getName());
		driver.get("https://google.com");
		logger.info(driver.getTitle());
		assertEquals("Google", driver.getTitle());
		logger.info("Ending test " + new Object(){}.getClass().getEnclosingMethod().getName());
	}
	
	@AfterAll
	public static void closeBrowser() {
		driver.quit();
	}

}
