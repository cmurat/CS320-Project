package GUI.listeners;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import GUI.MainPanel;

public class DeleteButtonListener implements ActionListener {
	private long tweetId;
	private JButton buttonName;
	private MainPanel mainPanel;

	public DeleteButtonListener(long tweetId, JButton buttonName,
			MainPanel mainPanel) {
		this.tweetId = tweetId;
		this.buttonName = buttonName;
		this.mainPanel = mainPanel;
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("retweet is clicked!");
		buttonName.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		mainPanel.deleteButtonClicked(tweetId);
		buttonName.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
}