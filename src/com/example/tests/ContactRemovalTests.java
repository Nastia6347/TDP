package com.example.tests;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;


import org.testng.annotations.Test;

import com.example.fw.ContactObject;
import com.example.fw.Contacts;

public class ContactRemovalTests extends TestBase{
	
	@Test 
	public void testFirstContactCanBeRemoved() throws Exception {
		Contacts oldContacts = app.getContactHelper().getContacts();
		ContactObject someContact = oldContacts.getSomeContact();
		app.getContactHelper().deleteContact(someContact);
		Contacts newContacts = app.getContactHelper().getContacts();
		assertThat(newContacts, equalTo(oldContacts.without(someContact)));
	}
}
