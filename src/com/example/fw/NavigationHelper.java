package com.example.fw;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperWithWebDriverBase {
	
	public NavigationHelper(ApplicationManager manager) {
		super(manager);
	}
	
	public void openListGroupPage() {
		if (! onGroupsPage()) {
		      click(By.linkText("групп"));
		    }
	  }	  

	public void openMainPage() {
		if (! onMainPage()) {
			openUrl(manager.getProperty("baseUrl"));
		    }
	  }
}
