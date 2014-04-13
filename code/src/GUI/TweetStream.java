package GUI;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import DataRequester.Tweet;

@SuppressWarnings("serial")
public class TweetStream extends JPanel implements MainContent {

	private MainPanel mainPanel;
	private ArrayList<Tweet> tweets;
	private GridBagConstraints gbc;
	private GridBagLayout gridBagLayout;

	public TweetStream(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
		tweets = new ArrayList<Tweet>();
		setLayout(new GridLayout(0, 1));
	}

	public Component printTweetStream(ArrayList<Tweet> tweets) {
		addTweets(tweets);
		printTweets();
		System.out.println("\nAssume TweetStream is painted.");
		JScrollPane thePane = new JScrollPane(this);
		thePane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		thePane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		return thePane;
	}

	private void addTweets(ArrayList<Tweet> tweets) {
		this.tweets.addAll(tweets);
	}

	private void printTweets() {
		for (Tweet tweet : tweets) {
			gridBagLayout = new GridBagLayout();
			gbc = new GridBagConstraints();
			
			JPanel tweetPanel = new JPanel();
			tweetPanel.setLayout(gridBagLayout);
			addUserImage(tweet, tweetPanel);
			addUserName(tweet, tweetPanel);
			addDate(tweet, tweetPanel);
			addTweetContent(tweet, tweetPanel);
			
			add(tweetPanel);
		}
	}

	private void addUserImage(Tweet tweet, JPanel tweetPanel) {
		JLabel userImage = new JLabel(new ImageIcon(tweet.getUserImage()));
		gbc.weightx = 1.0;
		gbc.gridx = 0;
		gbc.gridheight = 2;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = new Insets(5, 5, 5, 2);
		gridBagLayout.setConstraints(userImage, gbc);
		tweetPanel.add(userImage);
	}

	private void addUserName(Tweet tweet, JPanel tweetPanel) {
		JLabel userName = new JLabel("" + tweet.getUserName());
		gbc.weightx = 1.0;
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(5, 2, 0, 2);
		gridBagLayout.setConstraints(userName, gbc);
		tweetPanel.add(userName);
	}

	private void addDate(Tweet tweet, JPanel tweetPanel) {
		JLabel date = new JLabel("" + tweet.getDate());
		gbc.weightx = 1.0;
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.FIRST_LINE_END;
		gbc.insets = new Insets(5, 2, 0, 5);
		gridBagLayout.setConstraints(date, gbc);
		tweetPanel.add(date);
	}

	private void addTweetContent(Tweet tweet, JPanel tweetPanel) {
		JLabel tweetContent = new JLabel(tweet.getContent());
		gbc.weightx = 1.0;
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.insets = new Insets(0, 2, 5, 5);
		gridBagLayout.setConstraints(tweetContent, gbc);
		tweetPanel.add(tweetContent);
	}

}
