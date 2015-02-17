package com.example.fw;

import org.openqa.selenium.WebDriver;


public class HelperBase {
	
	protected final ApplicationManager manager;
	protected WebDriver driver;
	public boolean acceptNextAlert = true;
	
	public HelperBase(ApplicationManager manager) {
		    this.manager = manager;		    
		  }
}