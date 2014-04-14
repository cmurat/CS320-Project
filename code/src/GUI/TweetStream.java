package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.border.Border;

import DataRequester.Tweet;

@SuppressWarnings("serial")
public class TweetStream extends JPanel implements MainContent {

	private MainPanel mainPanel;
	private ArrayList<Tweet> tweets;

	public TweetStream(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
		tweets = new ArrayList<Tweet>();
		setLayout(new GridLayout(0, 1));
		
		setBackground(Color.white);
	}

	public Component printTweetStream(ArrayList<Tweet> tweets) {
		addTweets(tweets);
		printTweets();
		System.out.println("\nAssume TweetStream is painted.");
		JScrollPane thePane = new JScrollPane(this);
		thePane.setOpaque(true);
		thePane.getVerticalScrollBar().setUnitIncrement(16);
		thePane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		thePane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		return thePane;
	}

	private void addTweets(ArrayList<Tweet> tweets) {
		this.tweets.addAll(tweets);
	}

	private void printTweets() {
		for (Tweet tweet : tweets) {
			JPanel tweetPanel = new JPanel();
			JPanel imagePanel = new JPanel();
			imagePanel.setBackground(Color.WHITE);
			imagePanel.setOpaque(true);
			JPanel contentPanel = new JPanel();
			contentPanel.setBackground(Color.WHITE);
			contentPanel.setOpaque(true);
			contentPanel.setLayout(new GridLayout(2,1));
			tweetPanel.setLayout(new BorderLayout());
			addUserImage(tweet, imagePanel);
			addUserName(tweet, contentPanel);
			addTweetContent(tweet, contentPanel);
			tweetPanel.add(imagePanel,BorderLayout.WEST);
			tweetPanel.add(contentPanel,BorderLayout.CENTER);
			add(tweetPanel);
		}
	}

	private void addUserImage(Tweet tweet, JPanel imagePanel) {
		JLabel userImage = new JLabel(new ImageIcon(tweet.getUserImage()));
		imagePanel.add(userImage);
	}

	private void addUserName(Tweet tweet, JPanel contentPanel) {
		JLabel userName = new JLabel("" + tweet.getUserName());
		contentPanel.add(userName);
	}

	private void addDate(Tweet tweet, JPanel tweetPanel) {
		JLabel date = new JLabel("" + tweet.getDate());
		tweetPanel.add(date);
	}

	private void addTweetContent(Tweet tweet, JPanel contentPanel) {
		JLabel tweetContent = new JLabel(tweet.getContent());
		contentPanel.add(tweetContent);
	}

}
