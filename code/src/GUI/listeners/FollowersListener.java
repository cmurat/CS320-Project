package GUI.listeners;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import GUI.MainPanel;

public class FollowersListener implements ActionListener {

	private MainPanel mainPanel;
	private JButton followers;
	private long userId;

	public FollowersListener(long userId, JButton followers, MainPanel mainPanel) {
		this.mainPanel = mainPanel;
		this.followers = followers;
		this.userId = userId;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("\n Followers is clicked!");
		System.out.println(userId);
		followers.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		mainPanel.followersClicked(userId);
		followers.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

}
