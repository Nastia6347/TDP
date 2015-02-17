package com.example.fw;

public class ContactObject {
	public String firstname;
	public String lastname;
	public String address;
	public String home;
	public String mobile;
	public String work;
	public String email;
	public String email2;
	public String day;
	public String month;
	public String year;
	public String group;
	public String address2;
	public String phone2;
	public String id;
	
	public ContactObject() {
	}
	
	public ContactObject(ContactObject oldContact) {
		this.firstname = oldContact.firstname;
		this.lastname = oldContact.lastname;
		this.address = oldContact.address;
		this.home = oldContact.home;
		this.mobile = oldContact.mobile;
		this.work = oldContact.work;
		this.email = oldContact.email;
		this.email2 = oldContact.email2;
		this.day = oldContact.day;
		this.month = oldContact.month;
		this.year = oldContact.year;
		this.group = oldContact.group;
		this.address2 = oldContact.address2;
		this.phone2 = oldContact.phone2;
		this.id = oldContact.id;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getAddress() {
		return address;
	}

	public String getHome() {
		return home;
	}

	public String getMobile() {
		return mobile;
	}

	public String getWork() {
		return work;
	}

	public String getEmail() {
		return email;
	}

	public String getEmail2() {
		return email2;
	}

	public String getDay() {
		return day;
	}

	public String getMonth() {
		return month;
	}

	public String getYear() {
		return year;
	}

	public String getGroup() {
		return group;
	}

	public String getAddress2() {
		return address2;
	}

	public String getPhone2() {
		return phone2;
	}

	public String getId() {
		return id;
	}

	public ContactObject setFirstname(String firstname) {
		this.firstname = firstname;
		return this;
	}
	public ContactObject setLastname(String lastname) {
		this.lastname = lastname;
		return this;
	}
	public ContactObject setAddress(String address) {
		this.address = address;
		return this;
	}
	public ContactObject setHome(String home) {
		this.home = home;
		return this;
	}
	public ContactObject setMobile(String mobile) {
		this.mobile = mobile;
		return this;
	}
	public ContactObject setWork(String work) {
		this.work = work;
		return this;
	}
	public ContactObject setEmail(String email) {
		this.email = email;
		return this;
	}
	public ContactObject setEmail2(String email2) {
		this.email2 = email2;
		return this;
	}
	public ContactObject setDay(String day) {
		this.day = day;
		return this;
	}
	public ContactObject setMonth(String month) {
		this.month = month;
		return this;
	}
	public ContactObject setYear(String year) {
		this.year = year;
		return this;
	}
	public ContactObject setGroup(String group) {
		this.group = group;
		return this;
	}
	public ContactObject setAddress2(String address2) {
		this.address2 = address2;
		return this;
	}
	public ContactObject setPhone2(String phone2) {
		this.phone2 = phone2;
		return this;
	}
	public ContactObject setId(String id) {
		this.id = id;
		return this;
	}
	@Override
	public String toString() {
		return "Contact [" + id + ": " + firstname +  ", " + email +  "]";
	}
		
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result	+ ((firstname == null) ? 0 : firstname.hashCode());
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
		ContactObject other = (ContactObject) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (id != null && other.id != null) {
			   return id.equals(other.id);
		}
		return true;
	}
	

}