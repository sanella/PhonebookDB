package View;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Controller.AplicationController;
import Model.Contact;

public class ApplicationView extends Main {

	public static void home(Contact c) {

		JPanel content = new JPanel();
		JLabel greating = new JLabel("Welcome to BitBook");

		content.add(greating);
		JButton showContact = new JButton("Show Contacts");
		showContact.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.list();

			}
		});

		JButton addContact = new JButton("Add Contact");

		addContact.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.addContact();

			}
		});

		content.add(showContact);
		content.add(addContact);

		Main.replacePanel(content);

	}

	public static void addContact() {

		JPanel content = new JPanel();
		JLabel name = new JLabel("Name");
		final JTextField nameFild = new JTextField(20);
		JLabel surname = new JLabel("Surname");
		final JTextField surnameFild = new JTextField(20);
		JLabel number = new JLabel("Number");
		JTextField numberFild = new JTextField(20);
		JButton save = new JButton("Save");
		JButton back = new JButton("Backe");
		
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				 * Get the data from input
				 * and send it to the create method
				 */
				String cName = nameFild.getText();
				String cSurname = surnameFild.getText();
				String cNumber = nameFild.getText();
			AplicationController.create(cName, cSurname, cNumber);

			}
		});

		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.home();

			}
		});

		content.add(name);
		content.add(nameFild);
		content.add(surname);
		content.add(surnameFild);
		content.add(number);
		content.add(numberFild);
		content.add(save);
		content.add(back);
		
		replacePanel(content);

	}

	public static void list(Contact[] all){
		int buttonHight = 50;
		
		JPanel content = new JPanel();
		
		JButton back = new JButton("Back");
		JButton contact = new JButton("OK");
		content.add(back);
		content.add(contact);
		
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AplicationController.home();
				
			}
		});
		
		contact.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		
		content.setPreferredSize(new Dimension(ApplicationView.windowWidth-70, all.length * (buttonHight + 20)));
		
		if(all.length<1){
		JLabel greating = new JLabel("You have  no friends");
		Font msg = new Font("SansSerif", Font.BOLD, 30);
		greating.setFont(msg);
		content.add(greating);
		}
		//TODO add Add Contact button
		
		/**
		 * kreira dugme za svakog korisnika
		 * postavlja ime za svaki button
		 * ima actionlistener
		 */
		for(int i = 0; i < all.length; i++){
			JButton current = new JButton(all[i].getDisplayName());
			current.setName(Integer.toString(all[i].getId()));
			current.setPreferredSize(new Dimension(ApplicationView.windowWidth-75, buttonHight));
			current.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO redirect to contact
					JButton clicked = (JButton)(e.getSource());
					int id = Integer.parseInt(clicked.getName());
					System.out.println("Korsnik" + id);
					
				}
			});
			
			content.add(current);
		}
		
		JScrollPane sp = new JScrollPane(content);
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		replacePanel(sp);
	}
}
