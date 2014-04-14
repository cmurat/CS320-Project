package GUI.listeners;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class SettingsButtonListener implements ActionListener{
	private JButton settingsButton;
	public SettingsButtonListener(JButton settingsButton) {
		this.settingsButton = settingsButton;
	}
	
	public void actionPerformed(ActionEvent e) {
		settingsButton.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		System.out.println("\nSettings button is clicked!");
		settingsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));	
	}
}
