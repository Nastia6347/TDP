package com.example.tests;

import java.util.Set;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

public class GroupCreation extends TestBase{

  @Test(dataProvider = "groupsFromFile", dataProviderClass = GroupDataGenerator.class)
  public void AddGrop(GroupObject validgroup) throws Exception {
	Set<GroupObject>oldList = app.getGroupHelper().getGroups();	  
	app.getGroupHelper().createGroup(validgroup);
	Set<GroupObject>newList = app.getGroupHelper().getGroups();
	verifyGroupAdded(oldList, validgroup, newList );
  }
  
  private void verifyGroupAdded(Set<GroupObject> oldList, GroupObject validgroup,
	  Set<GroupObject> newList) {
	  //assertEquals(newList.size(), oldList.size()+1);	
	  assertThat(newList.size(), equalTo(oldList.size()+1));	
	  validgroup.setId("$$$");
	  oldList.add(validgroup);
	  validgroup.setId(null);
	  assertThat(newList, equalTo(oldList));	  
  }
}
