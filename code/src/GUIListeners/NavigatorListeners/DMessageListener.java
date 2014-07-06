package GUIListeners.NavigatorListeners;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import GUI.GUI;

public class DMessageListener implements ActionListener{
	private JButton dMessageButton;
	private GUI gui;	
	public DMessageListener(JButton dMessageButton, GUI gui) {
		this.dMessageButton = dMessageButton;
		this.gui = gui;
	}
	
	public void actionPerformed(ActionEvent e) {
		dMessageButton.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		gui.dMessageButtonClicked();
		dMessageButton.setCursor(new Cursor(Cursor.HAND_CURSOR));			
	}
}
