package GUI.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		// TODO Auto-generated method stub

	}

}