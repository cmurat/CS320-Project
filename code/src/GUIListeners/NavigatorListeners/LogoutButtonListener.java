package GUIListeners.NavigatorListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import GUI.GUI;

public class LogoutButtonListener implements ActionListener {
	private JButton logOutButton;
	private GUI gui;

	public LogoutButtonListener(JButton logOutButton, GUI gui) {
		this.logOutButton = logOutButton;
		this.gui = gui;
	}

	public void actionPerformed(ActionEvent e) {
		gui.logOutButtonClicked();
	}
	
}
