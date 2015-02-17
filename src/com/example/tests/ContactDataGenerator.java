package com.example.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.testng.annotations.DataProvider;

import com.example.fw.ContactObject;

public class ContactDataGenerator {

	static Random rnd = new Random();
	
	@DataProvider(name = "randomContacts")
	public static Iterator<Object[]> generateRandomContacts(){
		List<ContactObject> contacts = new ContactDataGenerator().generateRandomContactList(5);
		List<Object[]> list = new ArrayList<Object[]>();
		for (ContactObject contact : contacts) {
			list.add(new Object[]{contact});
		}
		return list.iterator();
		}

	@DataProvider(name = "contactsFromFile")
	public static Iterator<Object[]> loadContactsFromFile() throws IOException{
		List<Object[]> list = new ArrayList<Object[]>();
		BufferedReader reader = new BufferedReader(new FileReader("contact.xls"));
		String line = reader.readLine();
		while (line!= null){
			String[] parts = line.split("\t");
			ContactObject contact = new ContactObject()
			.setFirstname(parts[0])
			.setLastname(parts[1])
			.setAddress(parts[2])
			.setHome(parts[3])
			.setMobile(parts[4])
			.setWork(parts[5])
			.setEmail(parts[6])
			.setEmail2(parts[7])
			.setDay(parts[8])
			.setMonth(parts[9])
			.setYear(parts[10])
			.setGroup(parts[11])
			.setAddress2(parts[12])
			.setPhone2(parts[13]);		
			list.add(new Object[]{contact});
			line = reader.readLine();
		}
		reader.close();
		return list.iterator();
		}

	public static void main(String[] args) {
		if(args.length <2){
			System.out.println("Specify two patrametrs: file and count");
			return;
		}
			String file = args[0];
			int count = Integer.parseInt(args[1]);
			try {
				new ContactDataGenerator().generatorDataToFile(file, count);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


	private void generatorDataToFile(String fileName, int count) throws IOException {
		List<ContactObject> contacts = generateRandomContactList(count);
		File file = new File (fileName);
		if (file.exists()){
			System.out.println("File exists, stop generator");
			return;
		}
		FileWriter writer = new FileWriter(file);
		for (ContactObject contact : contacts) {
			writer.write(contact.firstname + "\t" + contact.lastname + "\t" + contact.address +  "\t" + contact.home + "\t" + contact.mobile + "\t" + contact.work
					+ "\t" + contact.email + "\t" + contact.email2 + "\t" + contact.day + "\t" + contact.month + "\t" + contact.year + "\t" + contact.group
					+ "\t" + contact.address2 + "\t" + contact.phone2 +"\n");
		}
		writer.close();
	}

	private List<ContactObject> generateRandomContactList(int count) {
		List<ContactObject> list = new ArrayList<ContactObject>();
		for (int i = 0; i < count; i++) {
			ContactObject contact = new ContactObject()
			.setFirstname("Name"+rnd.nextInt())
			.setLastname("LastName"+rnd.nextInt())
			.setAddress("Street" +rnd.nextInt())
			.setHome("Home"+rnd.nextInt())
			.setMobile("Mobile" +rnd.nextInt())
			.setWork("Company" +rnd.nextInt())
			.setEmail("Email" +rnd.nextInt())
			.setEmail2("Email2" +rnd.nextInt())
			.setDay("3")
			.setMonth("Март")
			.setYear("1999")
			.setGroup("sfs_mod")
			.setAddress2("Address2" +rnd.nextInt())
			.setPhone2("Phone2" +rnd.nextInt());	
			list.add(contact);
		}
		return list;
	}

}
