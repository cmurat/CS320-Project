package GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import DataRequester.DetailedAccount;
import GUI.listeners.FollowersListener;
import GUI.listeners.FollowingsListener;

@SuppressWarnings("serial")
public class ProfilePanel extends JPanel {

	private MainPanel mainPanel;
	private DetailedAccount account;
	private TweetStream tweetStream;

	public ProfilePanel(MainPanel mainPanel, DetailedAccount account) {
		this.mainPanel = mainPanel;
		this.account = account;
		this.tweetStream = new TweetStream(mainPanel);
		calculateBounds();
		setLayout(new BorderLayout());

		AccountProfileScreen accountInfoPanel = new AccountProfileScreen(account,mainPanel);
		

		JPanel tweetStreamPanel = new JPanel();
		tweetStreamPanel.setLayout(new GridLayout(1, 1));


		add(accountInfoPanel, BorderLayout.NORTH);
		add(tweetStreamPanel, BorderLayout.CENTER);

		addTweetStreamTo(tweetStreamPanel);
	}

	private void addTweetStreamTo(JPanel tweetStreamPanel) {
		Component stream = tweetStream.printTweetStream(account.getTweets());
		tweetStreamPanel.add(stream);
	}

	private void calculateBounds() {
		setBounds(mainPanel.getBounds().x, mainPanel.getBounds().y,
				mainPanel.getBounds().width, mainPanel.getBounds().height);
	}



	public void printProfilePanel() {
		System.out.println("\nAssume profile panel is painted.");
		mainPanel.addComponent(this);

	}

}
