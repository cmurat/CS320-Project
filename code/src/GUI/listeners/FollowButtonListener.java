package GUI.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.MainPanel;

public class FollowButtonListener implements ActionListener {
	private long userID;
	private MainPanel mainPanel;
	public FollowButtonListener(long userID, MainPanel mainPanel) {
		this.userID = userID;
		this.mainPanel = mainPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("\nFollow Button pressed");
		System.out.println(userID);
		mainPanel.followButtonPressed(userID);

	}

}
