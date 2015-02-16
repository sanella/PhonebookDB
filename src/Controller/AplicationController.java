package Controller;

import View.*;

public class AplicationController {

	public static void home() {
		// prikaz home GUI
	}

	public static void main(String[] args) {
		/* povezivanje sa bazom */
		Main.init();
		home();
	}
}
