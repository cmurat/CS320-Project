package GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JPanel;

import DataRequester.DetailedAccount;

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

		AccountProfileScreen accountInfoPanel = new AccountProfileScreen(account, mainPanel);

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

}
