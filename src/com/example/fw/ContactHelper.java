package com.example.fw;

import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;



public class ContactHelper extends HelperWithWebDriverBase {

	private Contacts contactsModel;
	private int lastContactId;
	
	public ContactHelper(ApplicationManager manager) {
		super(manager);
		lastContactId = Integer.parseInt(manager.getHibernateHelper().getLastContactId());
	}

	public Contacts getContacts() {
		if (contactsModel == null){
			contactsModel = manager.getHibernateHelper().getContacts();
		}
		return contactsModel;
	}

	public Contacts getUiContacts() {
		HashSet<ContactObject> set = new HashSet<ContactObject>();
		manager.getNavigationHelper().openMainPage();
		WebElement form = findElements(By.tagName("form")).get(2); 
		List<WebElement> checkboxes = form.findElements(By.name("selected[]"));
		for (WebElement checkbox : checkboxes) {
			String title = checkbox.getAttribute("title");
			 Pattern p = Pattern.compile(".*\\((.*)\\)"); 
			 Matcher m = p.matcher(title);
			 if(m.matches()){
				title = m.group(1) ;
			 } 
		   	ContactObject contact = new ContactObject()
		   	.setFirstname(title)
		   	.setId(checkbox.getAttribute("value"))
		   	.setEmail(checkbox.getAttribute("accept"));
		   	set.add(contact);
		}
		return new Contacts(set);
	}

	public void createContact(ContactObject contactObject) {
		manager.getNavigationHelper().openMainPage();
		goToAddNewGroupPage();
		fillContactForm(contactObject, false);
		submitContactCreation();
		returnToHomePage();
		lastContactId = lastContactId +1;
		contactObject.setId("" + lastContactId);
		contactsModel = contactsModel.withAdded(contactObject);
	}
	
	public void modifyContact(ContactObject oldContactObject, ContactObject newContactObject) {
		manager.getNavigationHelper().openMainPage();
		initContactModification(oldContactObject);
		fillContactForm(newContactObject, false);
		submitContactModification();
		returnToHomePage();
		contactsModel = contactsModel.without(oldContactObject).withAdded(newContactObject);
	}
	
	public void deleteContact(ContactObject contactObject) {
		manager.getNavigationHelper().openMainPage();
		click(By.xpath("//a[@href='edit.php?id="+contactObject.id+"']"));
		click(By.xpath("(//input[@name='update'])[2]"));
		returnToHomePage();
		contactsModel = contactsModel.without(contactObject);
	}

	public void goToAddNewGroupPage() {
		click(By.linkText("Добавить новый"));
	}
	
	private void initContactModification(ContactObject contactObject) {		
		click(By.xpath("//a[@href='edit.php?id="+contactObject.id+"']"));
	}

	private void fillContactForm(ContactObject contactObject, boolean fillGroup) {
		type(By.name("firstname"),contactObject.firstname);
		type(By.name("lastname"),contactObject.lastname);
		type(By.name("address"), contactObject.address);
		type(By.name("home"),contactObject.home);
		type(By.name("mobile"),contactObject.mobile);
		type(By.name("work"), contactObject.work);
		type(By.name("email"), contactObject.email);
		type(By.name("email2"),contactObject.email2);
		select(By.name("bday"), contactObject.day);
		select(By.name("bmonth"), contactObject.month);
		type(By.name("byear"), contactObject.year);
		if (fillGroup){
		    select(By.name("new_group"), contactObject.group);
		}
		type(By.name("address2"), contactObject.address2);
		type(By.name("phone2"), contactObject.phone2);
    }
	
	private void submitContactCreation() {
		click(By.name("submit"));
	}
	
	private void submitContactModification() {
		click(By.name("update"));	
	}
	
	private void returnToHomePage() {
		click(By.linkText("home page"));
	}
	   
	public ContactObject convertionContactForList(ContactObject contact) {
		String em;
		if(contact.email != "" && contact.email2 != "")  {
			em = contact.email+";"+contact.email2 ;
		} else { 
			em = contact.email+contact.email2;
		}   
		ContactObject contactForList = new ContactObject().setFirstname(contact.firstname + " "+contact.lastname).setEmail(em);
		return contactForList;
	}
}
