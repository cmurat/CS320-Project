package GUIListeners.NavigatorListeners;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import GUI.GUI;

public class SettingsButtonListener implements ActionListener {
	private JButton settingsButton;
	private GUI gui;

	public SettingsButtonListener(JButton settingsButton, GUI gui) {
		this.settingsButton = settingsButton;
		this.gui = gui;
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("\nSettings button is clicked!");
		settingsButton.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		gui.settingsButtonClicked();
		settingsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
}
