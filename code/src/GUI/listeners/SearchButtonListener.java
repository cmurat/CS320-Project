package GUI.listeners;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import GUI.GUI;

public class SearchButtonListener implements ActionListener {
	private JButton searchButton;
	private GUI gui;

	public SearchButtonListener(JButton searchButton, GUI gui) {
		this.searchButton = searchButton;
		this.gui = gui;
	}

	public void actionPerformed(ActionEvent e) {
		searchButton.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		gui.searchButtonClicked();
		System.out.println("Search button is clicked!");
		searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
}
