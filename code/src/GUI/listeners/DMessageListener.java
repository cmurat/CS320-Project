package GUI.listeners;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class DMessageListener implements ActionListener{
	private JButton dMessageButton;
	
	public DMessageListener(JButton dMessageButton) {
		this.dMessageButton = dMessageButton;
	}
	
	public void actionPerformed(ActionEvent e) {
		dMessageButton.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		System.out.println("\nDMessage button is clicked!");
		dMessageButton.setCursor(new Cursor(Cursor.HAND_CURSOR));			
	}
}
