package com.exampel.fw;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HelperWithWebDriverBase {
	
	protected final AppManager manager;
	private WebDriver driver;
	
	public HelperWithWebDriverBase(AppManager manager) {
		this.manager = manager;	
		driver = manager.getWebDriverHelper().getDriver();
	}
	
	public void type(String locator, String value) {
		driver.findElement(By.name(locator)).clear();
	    driver.findElement(By.name(locator)).sendKeys(value);
	}
	
	public WebElement findElement(By name) {
		return driver.findElement(name);
	}
	
	public List<WebElement> findElements(By name) {
		return driver.findElements(name);
	}

	protected void openUrl(String string) {
		driver.get(string);
	}

	protected void click(By linkText) {
		findElement(linkText).click();
	}
}
