package com.exampel.fw;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.example.tests.GroupObject;

public class GroupHelper extends HelperWithWebDriverBase {

	public GroupHelper(AppManager manager) {
		super(manager);
	}
	
	 public void createGroup(GroupObject group) {
		    manager.getNavHelper().gotoGroupPage();
		    initGroupCreation();    
			fillGroupForm(group);
		    submitGroupForm();
		    manager.getNavHelper().gotoGroupPage();
		} 

		private void submitGroupForm() {
			click(By.name("submit"));
		}		

		private void fillGroupForm(GroupObject group) {
			type("group_name", group.name);
		    type("group_header", group.header);
		    type("group_footer", group.footer);
		}
		
		private void initGroupCreation() {
			click(By.name("new"));
		}

		public void deleteGroup(int i) {
		    manager.getNavHelper().gotoGroupPage();
		    checkGroup(i);
			click(By.name("delete"));
		    manager.getNavHelper().gotoGroupPage();
		}

		public void modifyGroup(int i, GroupObject group) {
			manager.getNavHelper().gotoGroupPage();
			initGroupModifycation(i);   
			fillGroupForm(group);
		    submitGroupMadifycation();
		    manager.getNavHelper().gotoGroupPage();
		}

		private void submitGroupMadifycation() {
			click(By.name ("update"));			
		}

		private void initGroupModifycation(int i) {
			checkGroup(i);
			click(By.name ("edit"));
		}

		private void checkGroup(int i) {
			click(By.name("selected[]"));
		}

		public Set<GroupObject> getGroups() {
			manager.getNavHelper().gotoGroupPage();
			WebElement form = findElements(By.tagName("form")).get(1);
			List<WebElement> checkboxes = form.findElements(By.name("selected[]"));
			Set<GroupObject> groups = new HashSet<GroupObject>();
			for (WebElement checkbox : checkboxes) {
				String title = checkbox.getAttribute("title");
				title = title.substring("Select (".length(), title.length()-")".length());
				GroupObject group = new GroupObject()
					.setName(title)
					.setId(checkbox.getAttribute("value"));
				groups.add(group);				
			}
			return groups;
		}			
}
