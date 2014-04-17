package GUI.listeners;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import GUI.MainPanel;

public class ChangeUsernameButtonListener implements ActionListener {
	private MainPanel mainPanel;
	private JButton changeUsername;
	
	public ChangeUsernameButtonListener(JButton changeUsernameButton, MainPanel mainPanel) {
		this.mainPanel = mainPanel;
		this.changeUsername = changeUsernameButton;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("\n change username is clicked!");
		changeUsername.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		
		String newName = askNewName();
		mainPanel.changeUsernameClicked(newName);
		
		changeUsername.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	private String askNewName() {
	     return JOptionPane.showInputDialog(mainPanel, "Enter new username");		
	}

}
