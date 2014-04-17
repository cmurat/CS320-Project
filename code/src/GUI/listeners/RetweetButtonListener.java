package GUI.listeners;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import GUI.MainPanel;

public class RetweetButtonListener implements ActionListener {
	private long tweetId;
	private JButton userName;
	private MainPanel mainPanel;

	public RetweetButtonListener(long tweetId, JButton userName, MainPanel mainPanel) {
		this.tweetId = tweetId;
		this.userName = userName;
		this.mainPanel = mainPanel;
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("retweet is clicked!");
		userName.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		mainPanel.retweetButtonClicked(tweetId);
		userName.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
}