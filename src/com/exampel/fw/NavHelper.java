package com.exampel.fw;

import org.openqa.selenium.By;

public class NavHelper extends HelperWithWebDriverBase {
	
	public NavHelper(AppManager manager) {
		super(manager);
	}
	
	public void gotoGroupPage() {
		  click(By.linkText("групп"));
	  }	

	public void openMainPage() {
		  openUrl("http://localhost/addressbookv4.1.4/");
	  }

}
