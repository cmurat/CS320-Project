package GUIListeners.NavigatorListeners;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import GUI.GUI;

public class HomeButtonListener implements ActionListener {
	private JButton homeButton;
	private GUI gui;

	public HomeButtonListener(JButton homeButton, GUI gui) {
		this.homeButton = homeButton;
		this.gui = gui;
	}

	public void actionPerformed(ActionEvent arg0) {
		homeButton.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		gui.homeButtonClicked();
		homeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		System.out.println("\nHome button is clicked!");
	}
}
