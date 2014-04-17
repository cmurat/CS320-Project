package GUI.listeners;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import GUI.MainPanel;

public class FollowingsListener implements ActionListener {
	private MainPanel mainPanel;
	private JButton followings;
	private long userId;

	public FollowingsListener(long userId, JButton followings, MainPanel mainPanel) {
		this.mainPanel = mainPanel;
		this.followings = followings;
		this.userId = userId;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("\n Followings is clicked!");
		followings.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		mainPanel.followingClicked(userId);
		followings.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

}
