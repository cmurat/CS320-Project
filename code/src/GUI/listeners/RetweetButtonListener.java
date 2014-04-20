package GUI.listeners;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import DataRequester.Tweet;
import GUI.MainPanel;

public class RetweetButtonListener implements ActionListener {
	private long tweetId;
	private JButton retweetButton;
	private MainPanel mainPanel;
	private Tweet tweet;

	public RetweetButtonListener(long tweetId, JButton retweetButton, MainPanel mainPanel, Tweet tweet) {
		this.tweetId = tweetId;
		this.retweetButton = retweetButton;
		this.mainPanel = mainPanel;
		this.tweet=tweet;
	}

	public void actionPerformed(ActionEvent e) {
		String address="icon/RetweetIcon.png";
		System.out.println("retweet is clicked!");
		retweetButton.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		if(!tweet.isRetweeted()){

			mainPanel.retweetButtonClicked(tweetId);
		}
		Image img = new ImageIcon(address).getImage();
		img = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		retweetButton.setIcon(new ImageIcon(img));
		retweetButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}}

