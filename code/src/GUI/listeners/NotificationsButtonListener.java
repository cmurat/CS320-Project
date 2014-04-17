package GUI.listeners;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import GUI.GUI;

public class NotificationsButtonListener implements ActionListener {
	private JButton notificationsButton;
	private GUI gui;

	public NotificationsButtonListener(JButton notificationsButton, GUI gui) {
		this.notificationsButton = notificationsButton;
		this.gui = gui;
	}

	public void actionPerformed(ActionEvent arg0) {
		notificationsButton.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		gui.mentionsButtonClicked();
		notificationsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		System.out.println("\nNotifications button is clicked!");
	}
}
