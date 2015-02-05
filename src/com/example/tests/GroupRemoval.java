package com.example.tests;

import org.testng.annotations.Test;

public class GroupRemoval extends TestBase{    

  @Test
  public void deleteFerstGrop() throws Exception {
	  
    app.getGroupHelper().deleteGroup(0);
  }
   
}
