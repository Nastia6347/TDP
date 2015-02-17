package com.example.fw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class GroupHelper extends HelperWithWebDriverBase {
	
	private Groups groupModel;
	private int lastGroupId;
	
	public GroupHelper(ApplicationManager manager) {
	    super(manager);
	    //groupModel = getGroups();
		lastGroupId = Integer.parseInt(manager.getHibernateHelper().getLastGroupId());
	}

	public Groups getGroups() {
		if (groupModel == null){
			groupModel= manager.getHibernateHelper().getGroups();
		}
		return groupModel;
	}

	public Groups getUiGroups() {
		HashSet<GroupObject> set = new HashSet<GroupObject>();
        manager.getNavigationHelper().openListGroupPage();
        WebElement form = findElements(By.tagName("form")).get(1); 
        List<WebElement> checkboxes = form.findElements(By.name("selected[]"));
        for (WebElement checkbox : checkboxes) {
                String title = checkbox.getAttribute("title");
                 Pattern p = Pattern.compile(".*\\((.*)\\)"); 
                 Matcher m = p.matcher(title);
                 if(m.matches()){
                        title = m.group(1) ;
                 } 
                GroupObject group = new GroupObject()
                	.setName(title)
                	.setId(checkbox.getAttribute("value"));
                set.add(group);
        }
        return new Groups(set);
	}

	public void createGroup(GroupObject groupObject) {
		manager.getNavigationHelper().openListGroupPage();
		initGroupCreation();
		fillGroupForm(groupObject);
		submitGroupCreation();
		returnToListPage();
		lastGroupId = lastGroupId +1;
		groupObject.setId("" + lastGroupId);
		groupModel = groupModel.withAdded(groupObject);
		//lastGroupId = lastGroupId +1;
	}

	public void deleteGroup(GroupObject groupObject) {
		manager.getNavigationHelper().openListGroupPage();
		checkGroup(groupObject);
		click(By.name("delete"));
		returnToListPage();
		groupModel = groupModel.without(groupObject);
	}

	public void modifyGroup(GroupObject oldGroupObject, GroupObject newGroupObject) {
		manager.getNavigationHelper().openListGroupPage();
		initGroupModification(oldGroupObject);
		fillGroupForm(newGroupObject);
		submitGroupModification();
		returnToListPage();
		groupModel = groupModel.without(oldGroupObject).withAdded(newGroupObject);
	}

	private void checkGroup(GroupObject groupObject) {
		click(By.xpath("//input[@value='"+groupObject.id+"']"));
	}
	
	private void initGroupCreation() {
		click(By.name("new"));
	}

	private void initGroupModification(GroupObject groupObject) {
		checkGroup(groupObject);
		click(By.name("edit"));
	}
	
	private void fillGroupForm(GroupObject groupObject) {
		type(By.name("group_name"), groupObject.name);
		type(By.name("group_header"), groupObject.header);
		type(By.name("group_footer"), groupObject.footer);
	}

	private void submitGroupCreation() {
		click(By.name("submit"));
	}

	private void submitGroupModification() {
		click(By.name("update"));	
	}
	
	private void returnToListPage() {
		click(By.linkText("group page"));
	}	
}
