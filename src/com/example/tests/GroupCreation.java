package com.example.tests;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.example.fw.GroupObject;
import com.example.fw.Groups;

public class GroupCreation extends TestBase{

  @Test(dataProvider = "groupsFromFile", dataProviderClass = GroupDataGenerator.class)
  public void AddGrop(GroupObject validgroup) throws Exception {
	Groups oldList = app.getGroupHelper().getGroups();
    app.getGroupHelper().createGroup(validgroup); 
    Groups newList = app.getGroupHelper().getGroups();
    assertThat(newList, equalTo(oldList.withAdded(validgroup)));
  }
  
  @Test
  public void testUiAndDbConformsWithModel() {
	if(app.getProperty("ui.check") != null) {
		Groups modelList = app.getGroupHelper().getGroups();
		Groups uiList = app.getGroupHelper().getUiGroups();
		assertThat(uiList, equalTo(modelList));
  	} 
	if(app.getProperty("db.check") != null){
		Groups modelList = app.getGroupHelper().getGroups();
		Groups dbList = app.getHibernateHelper().getGroups();
		assertThat(dbList, equalTo(modelList));
	}
  }
}
