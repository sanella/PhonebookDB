package Controller;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import Model.*;
import View.*;

public class AplicationController {

	public static void home() {
		// prikaz home GUI
		Contact c = Contact.find(1);
		ApplicationView.home(c);
		
	}

	public static void addContact(){
		ApplicationView.addContact();
	}
	
	//pokusava da napravi novi objekat tipa contact i da ga spasi u bazu
	public static void create(String name, String surname, String number){
		Contact newContact = new Contact(name, surname, number);
		 //treba da spasi u bazu
		if(newContact.save() == true){
			//TODO redirect to contact info
			home();
			JOptionPane.showMessageDialog(null, "Uspjesno si spasio"+newContact.getName(), "Kontakt je dodan", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Doslo je do greske", "Prijava Greske", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public static void list(){
		Contact[] all = Contact.all();
		ApplicationView.list(all);
		
	}
	
	public static void main(String[] args) {
		/* povezivanje sa bazom */
		try {
			Application.init();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
		Main.init();
		home();
		
	}
}
