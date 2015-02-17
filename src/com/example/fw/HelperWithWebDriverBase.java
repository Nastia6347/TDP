package com.example.fw;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class HelperWithWebDriverBase extends HelperBase {
	
	
	private WebDriver driver;
	
	public HelperWithWebDriverBase(ApplicationManager manager) {
		super(manager);
		driver = manager.getWebDriverHelper().getDriver();
	}
	
	protected boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	protected void type(By locator, String name) {
		if (name!=null) {
			driver.findElement(locator).clear();
			driver.findElement(locator).sendKeys(name);
		}
	}
	
	protected void select(By locator, String optionText) {
		new Select(driver.findElement(locator)).selectByVisibleText(optionText);
	}
	
	protected WebElement findElement(By locator) {
		return driver.findElement(locator);
	}
	
	public List<WebElement> findElements(By locator) {
		return driver.findElements(locator);
	}
	
	protected void openUrl(String string) {
		driver.get(string);
	}
	
	protected void click(By locator) {
		findElement(locator).click();
	}
	
	public boolean onGroupsPage() {
	    if (driver.getCurrentUrl().contains("/group.php")
	        && driver.findElements(By.name("new")).size() > 0) {
	      return true;
	    } else {
	      return false;
	    }
	  }
	
	public boolean onMainPage() {
	    return driver.findElements(By.id("maintable")).size() > 0;
	  }
}
