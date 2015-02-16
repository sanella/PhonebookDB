package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

import Controller.AplicationController;

public class Contact extends Application {

	private int id;
	private String name;
	private String surname;
	private String number;
	private final static String tableName = "Contacts";

	public Contact() {
		this.id = -1;
		this.name = "unknown";
		this.surname = "unknown";
		this.number = "unknown";
	}

	public Contact(String name, String surname, String number) {
		this.id = -1;
		this.name = name;
		this.surname = surname;
		this.number = number;
	}

	public Contact(int id, String name, String surname, String number) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.number = number;
	}

	
	public static Contact find(int id){
		ResultSet res = Application.find(id, tableName);
		try {
			int cId = res.getInt("id");
			String cName = res.getString("name");
			String cSurname = res.getString("surname");
			String cNumber = res.getString("number");
			return new Contact(cId, cName, cSurname, cNumber);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
		
	}
	
	
	
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
