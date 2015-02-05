package com.exampel.fw;

public class AppManager {	
	
private static AppManager singleton;
	
	private WebDriverHelper webDriverHelper;
	private NavHelper navHelper;
	private GroupHelper groupHelper;
	
	public static AppManager getIntance() {
		if (singleton == null){
			singleton = new AppManager();
		}
		return singleton;
	}
	  
	  public void stop() {
		  if (webDriverHelper != null){
		 webDriverHelper.stop();
		  } 
	  }

	public WebDriverHelper getWebDriverHelper() {
		if (webDriverHelper == null){
			webDriverHelper = new WebDriverHelper();
		}
		return webDriverHelper;
	}

	public GroupHelper getGroupHelper() {
		if (groupHelper == null){
			groupHelper = new GroupHelper(this);
		}
		return groupHelper;
	}

	public NavHelper getNavHelper() {
		if (navHelper == null){
			navHelper = new NavHelper(this);
		}
		return navHelper;
	}  
}
