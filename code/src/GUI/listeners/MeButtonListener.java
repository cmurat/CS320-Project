package GUI.listeners;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import GUI.GUI;

public class MeButtonListener implements ActionListener {
	private JButton meButton;
	private GUI gui;

	public MeButtonListener(JButton meButton, GUI gui) {
		this.meButton = meButton;
		this.gui = gui;
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("\nMe button is clicked!");
		meButton.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		gui.meButtonClicked();
		meButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
}
