package com.example.fw;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;


public class HibernateHelper extends  HelperBase {
	
	public HibernateHelper (ApplicationManager manager) {
		super(manager);
	}

	public String getLastGroupId() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		return session.createQuery("select max(id) from GroupObject").uniqueResult().toString();
	}
	
	public Groups getGroups() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction t = session.beginTransaction();
		try{
		return new Groups((List<GroupObject>) session.createQuery("from GroupObject").list());
		}finally {
			t.commit();
		}
	}
	
	public String getLastContactId() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		return session.createQuery("select max(id) from ContactObject").uniqueResult().toString();
	}
	
	public Contacts getContacts() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction t = session.beginTransaction();
		try{
		return new Contacts((List<ContactObject>) session.createQuery("from ContactObject").list());
	}finally {
		t.commit();
	}
	}
}
