package com.example.tests;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.example.fw.GroupObject;
import com.example.fw.Groups;

public class GroupRemovalTests extends TestBase{
	@Test
	public void testSomeCanBeRemoved() throws Exception {
		Groups oldGroups = app.getGroupHelper().getGroups();
		GroupObject someGroup = oldGroups.getSomeGroup();
		app.getGroupHelper().deleteGroup(someGroup);
		Groups newGroups = app.getGroupHelper().getGroups();
		assertThat(newGroups, equalTo(oldGroups.without(someGroup)));
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
