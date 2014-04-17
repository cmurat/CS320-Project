package GUI.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import GUI.MainPanel;

public class UnFollowButtonListener implements ActionListener {
	private long userID;
	private MainPanel mainPanel;
	public UnFollowButtonListener(long userID, MainPanel mainPanel) {
		this.userID = userID;
		this.mainPanel = mainPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mainPanel.unFollowButtonClicled(userID);

	}

}
