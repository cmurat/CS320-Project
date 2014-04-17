package GUI.listeners;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import GUI.MainPanel;

public class DeleteButtonListener implements ActionListener {
	private long tweetId;
	private JButton deleteButton;
	private MainPanel mainPanel;

	public DeleteButtonListener(long tweetId, JButton deleteButton, MainPanel mainPanel) {
		this.tweetId = tweetId;
		this.deleteButton = deleteButton;
		this.mainPanel = mainPanel;
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("retweet is clicked!");
		deleteButton.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		mainPanel.deleteButtonClicked(tweetId);
		deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
}