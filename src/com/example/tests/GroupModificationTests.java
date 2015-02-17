package com.example.tests;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.example.fw.GroupObject;
import com.example.fw.Groups;

public class GroupModificationTests extends TestBase{	
		
	@Test
	public void testFirstCanBeModified() throws Exception {
		Groups oldGroups = app.getGroupHelper().getGroups();
		GroupObject oldGroup = oldGroups.getSomeGroup();
		GroupObject newGroup = new GroupObject(oldGroup);
		newGroup.setName("sfs_mod");
		app.getGroupHelper().modifyGroup(oldGroup, newGroup);
		Groups newGroups = app.getGroupHelper().getGroups();
		assertThat(oldGroups, equalTo(newGroups.without(newGroup).withAdded(oldGroup)));
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
