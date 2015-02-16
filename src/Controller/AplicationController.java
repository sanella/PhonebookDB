package Controller;

import java.sql.SQLException;

import Model.*;
import View.*;

public class AplicationController {

	public static void home() {
		// prikaz home GUI
		Contact c = Contact.find(1);
		ApplicationView.home(c);
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
