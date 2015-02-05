package com.example.tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

import com.exampel.fw.AppManager;

public class TestBase {
	
	protected AppManager app; 	  
	
	@BeforeClass
	  public void setUp() throws Exception {
		app = AppManager.getIntance();		    
	  }
	
	@AfterTest
	  public void tearDown() throws Exception {
		AppManager.getIntance().stop();
	  } 	
}
