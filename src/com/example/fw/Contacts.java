package com.example.fw;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class Contacts {
	
	   private  Set <ContactObject> storedConacts = null;
	   
	   public Contacts(Collection <ContactObject> contacts){
		   storedConacts = new HashSet<ContactObject>(contacts);
	   }

		public Contacts withAdded(ContactObject contact) {
			Contacts contacts2 = new Contacts(storedConacts);
            //contact.setId("$$$");
			contacts2.storedConacts.add(contact);
			//contact.setId(null);
			return contacts2;
		}

		public Contacts without(ContactObject contact) {
			Contacts contacts2 = new Contacts(storedConacts);
			contacts2.storedConacts.remove(contact);
			return contacts2;
			}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((storedConacts == null) ? 0 : storedConacts.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contacts other = (Contacts) obj;
		if (storedConacts == null) {
			if (other.storedConacts != null)
				return false;
		} else if (!storedConacts.equals(other.storedConacts))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Contacts [storedConacts=" + storedConacts + "]";
	}

	public ContactObject getSomeContact() {
		return storedConacts.iterator().next();
	}

}
