package com.example.tests;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;


import org.testng.annotations.Test;

import com.example.fw.ContactObject;
import com.example.fw.Contacts;


public class ContactModificationTests extends TestBase{
	
	@Test 
	public void testFirstCanBeModified() throws Exception {
		Contacts oldContacts = app.getContactHelper().getContacts();		
		ContactObject oldContact = oldContacts.getSomeContact();
		ContactObject newContact = new ContactObject(oldContact);
		newContact.setFirstname("56").setMonth("Август");
		app.getContactHelper().modifyContact(oldContact, newContact);
		Contacts newContacts = app.getContactHelper().getContacts();
		assertThat(oldContacts, equalTo(newContacts.without(newContact).withAdded(oldContact)));
	}

}
