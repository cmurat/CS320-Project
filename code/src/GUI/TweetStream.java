package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import DataRequester.Tweet;

@SuppressWarnings("serial")
public class TweetStream extends JPanel implements MainContent {

	private MainPanel mainPanel;
	private ArrayList<Tweet> tweets;
	private GridBagConstraints cons;
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
		return thePane;
	}

	private void addTweets(ArrayList<Tweet> tweets) {
		this.tweets.addAll(tweets);
	}

	private void printTweets() {
		for (Tweet tweet : tweets) {
			gridBagLayout = new GridBagLayout();
			cons = new GridBagConstraints();
			
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
		cons.weightx = 1.0;
		cons.gridx = 0;
		//cons.gridheight = 2;
		cons.gridy = 0;
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.anchor = GridBagConstraints.CENTER;
		gridBagLayout.setConstraints(userImage, cons);
		tweetPanel.add(userImage);
	}

	private void addUserName(Tweet tweet, JPanel tweetPanel) {
		JLabel userName = new JLabel("" + tweet.getUserName());
		cons.weightx = 1.0;
		cons.gridx = 1;
		cons.gridy = 0;
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.anchor = GridBagConstraints.FIRST_LINE_START;
		gridBagLayout.setConstraints(userName, cons);
		tweetPanel.add(userName);
	}

	private void addDate(Tweet tweet, JPanel tweetPanel) {
		JLabel date = new JLabel("" + tweet.getDate());
		cons.weightx = 1.0;
		cons.gridx = 2;
		cons.gridy = 0;
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.anchor = GridBagConstraints.FIRST_LINE_END;
		gridBagLayout.setConstraints(date, cons);
		tweetPanel.add(date);
	}

	private void addTweetContent(Tweet tweet, JPanel tweetPanel) {
		JLabel tweetContent = new JLabel(tweet.getContent());
		cons.weightx = 1.0;
		cons.gridx = 1;
		cons.gridy = 1;
		cons.gridwidth = 2;
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.anchor = GridBagConstraints.PAGE_START;
		gridBagLayout.setConstraints(tweetContent, cons);
		tweetPanel.add(tweetContent);
	}

}
