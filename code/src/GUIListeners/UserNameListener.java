package GUIListeners;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import GUI.MainPanel;

public class UserNameListener implements ActionListener {
	private long userId;
	private JButton userName;
	private MainPanel mainPanel;

	public UserNameListener(long userId, JButton userName, MainPanel mainPanel) {
		this.userId = userId;
		this.userName = userName;
		this.mainPanel = mainPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("\n User Name is clicked!");
		userName.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		mainPanel.userNameClicked(userId);
		userName.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

}
