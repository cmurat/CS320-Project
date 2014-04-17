package GUI.listeners;

import java.awt.Cursor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import GUI.GUI;
public class logoutButtonListener implements ActionListener{
	private JButton logOutButton;
	private GUI gui;	
	public logoutButtonListener(JButton logOutButton, GUI gui) {
		this.logOutButton = logOutButton;
		this.gui = gui;

	}

	public void actionPerformed(ActionEvent e) {
		
		gui.logOutButtonClicked();
		

	}
}


