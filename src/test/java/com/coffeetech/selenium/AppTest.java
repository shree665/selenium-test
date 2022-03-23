package com.coffeetech.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppTest {
	
	private static final Logger logger = LoggerFactory.getLogger(AppTest.class);
	
	private static WebDriver driver;
	private static String hubUrl = "http://10.1.0.127:5555/";
	
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
