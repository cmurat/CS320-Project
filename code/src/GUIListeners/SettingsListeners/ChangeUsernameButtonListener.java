package GUIListeners.SettingsListeners;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import GUI.MainPanel;

public class ChangeUsernameButtonListener implements ActionListener {
	private MainPanel mainPanel;
	private JButton changeUsernameButton;

	public ChangeUsernameButtonListener(JButton changeUsernameButton, MainPanel mainPanel) {
		this.mainPanel = mainPanel;
		this.changeUsernameButton = changeUsernameButton;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("\n change username is clicked!");
		changeUsernameButton.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		String username = askNewName();
		if (checkValidity(username))
			mainPanel.changeUsernameClicked(username);
		changeUsernameButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	private boolean checkValidity(String username) {
		return !username.equals("")
				&& (username.matches(".*[a-zA-Z]+.*") 
						|| username.matches(".*[0-9]+.*"));
	}

	private String askNewName() {
		String userInput = JOptionPane.showInputDialog(mainPanel, "Enter new username");
		if (userInput != null && (userInput.length() <= 20)) {
			return userInput;
		}
		return "";
	}

}
