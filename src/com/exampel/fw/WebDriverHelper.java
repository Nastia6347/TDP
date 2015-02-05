package com.exampel.fw;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverHelper {
	
	private static WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	
	public WebDriverHelper() {
		driver = new FirefoxDriver();	    
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://localhost/addressbookv4.1.4/");
	}
	
	public void stop() {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
		  fail(verificationErrorString);
		}
	}

	public WebDriver getDriver() {
		return driver;
	}
}
