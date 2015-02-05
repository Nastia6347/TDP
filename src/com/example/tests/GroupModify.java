package com.example.tests;

import org.testng.annotations.Test;

public class GroupModify extends TestBase{    

  @Test
  public void ModifyGrop() throws Exception {
	  
	GroupObject group = new GroupObject().setName("1965").setHeader("2653").setFooter("3654");
	app.getGroupHelper().modifyGroup(0, group);
  }
}
