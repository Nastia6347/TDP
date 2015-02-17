package com.example.tests;

import org.testng.annotations.Test;

import com.example.fw.ContactObject;
import com.example.fw.Contacts;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class ContactCreationTests extends TestBase{
	

	@Test(dataProvider = "contactsFromFile", dataProviderClass = ContactDataGenerator.class)
	public void testValidContactCanBeCreated(ContactObject validContact) throws Exception {
		Contacts oldList = app.getContactHelper().getContacts();
		app.getContactHelper().createContact(validContact);
		Contacts newList = app.getContactHelper().getContacts();
		assertThat(newList, equalTo(oldList.withAdded(validContact)));
	}

    @Test
    public void testUiAndDbConformsWithModel() {
		if(app.getProperty("ui.check") != null) {
		Contacts dbList = app.getHibernateHelper().getContacts();
		Contacts uiList = app.getContactHelper().getUiContacts();
        assertThat(uiList, equalTo(dbList));
    	} 
		if(app.getProperty("db.check") != null){
		Contacts modelList = app.getContactHelper().getContacts();
		Contacts dbList = app.getHibernateHelper().getContacts();
        assertThat(dbList, equalTo(modelList));
		}
    }
}
