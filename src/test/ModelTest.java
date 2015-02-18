package test;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

import model.Application;
import model.Contact;

public class ModelTest {

	
	private static void testFind() {
		// TODO add equals method to COntact class and rewrite this part of the
		// test
		Contact c = Contact.find(1);
		if (!c.getName().equals("Bob") || !c.getSurname().equals("Bobs")
				|| !c.getNumber().equals("12565456")) {
			System.err.println("Found non existing contact");
		}
		c = Contact.find(4);
		if (c != null)
			System.err.println("Found non existing contact");

	}

	private static void testAll() {
		Contact[] all = Contact.all();
		if (!all[0].getName().equals("Bob")
				|| !all[0].getSurname().equals("Bobs")) {
			System.err.println("contact not equal");
		}
		if (!all[1].getName().equals("Rob")
				|| !all[1].getSurname().equals("Robs")) {
			System.err.println("contact not equal");
		}
		if (!all[2].getName().equals("Tob")
				|| !all[2].getSurname().equals("Tobs")) {
			System.err.println("contact not equal");
		}
	}

	public static void main(String[] args) {
		try {
			Application.init("phonebook_test");

		} catch (SQLException e) {
			System.exit(1);
		}
		new Contact(1, "Bob", "Bobs", "1111").save();
		new Contact(2, "Rob", "Robs", "2222").save();
		new Contact(3, "Tob", "Tobs", "3333").save();
		
		
		System.out.println("Testing find");
		testFind();
		System.out.println("Testing all:");
		testAll();
		String[] tableNames = {"contacts"};
		
		
		
		System.out.println("Done testing");
	
	}

}
