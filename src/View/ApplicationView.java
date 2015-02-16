package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ApplicationView extends Main {

	public static void home() {

		JPanel content = new JPanel();
		JLabel greating = new JLabel("Welcome to BitBook");

		content.add(greating);
		JButton showContact = new JButton("Show Contacts");
		showContact.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		JButton addContact = new JButton("Add Contact");

		addContact.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		content.add(showContact);
		content.add(addContact);
		Main.replacePanel(content);

	}

	public static void main(String[] args) {
		/* povezivanje sa bazom */
		Main.init();
		home();
		
	}

}
