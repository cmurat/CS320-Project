package GUI;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import DataRequester.Tweet;

@SuppressWarnings("serial")
public class TweetStream extends JPanel implements MainContent {

	private MainPanel mainPanel;
	private ArrayList<Tweet> tweets;

	public TweetStream(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
		tweets = new ArrayList<Tweet>();
		calculateBounds();
		setLayout(new FlowLayout());
	}

	private void calculateBounds() {
		int xPos = mainPanel.getBounds().x;
		int yPos = mainPanel.getBounds().y + mainPanel.getBounds().height / 10;
		int width = mainPanel.getBounds().width;
		int height = mainPanel.getBounds().height - 2 * yPos;
		setBounds(xPos, yPos, width, height);
	}

	public void printTweetStream(ArrayList<Tweet> tweets) {
		addTweets(tweets);
		printTweets();
	}

	private void addTweets(ArrayList<Tweet> tweets) {
		this.tweets.addAll(tweets);
	}

	private void printTweets() {
		for (Tweet tweet : tweets) {
			JLabel userImage = new JLabel(tweet.getUserImage());
			JLabel userName = new JLabel("" + tweet.getUserName());
			JLabel date = new JLabel("" + tweet.getDate());
			JLabel tweetContent = new JLabel(tweet.getContent());

			JPanel tweetHeaderPanel = new JPanel(new FlowLayout());
			tweetHeaderPanel.add(userName);
			tweetHeaderPanel.add(date);

			JPanel tweetContentPanel = new JPanel(new GridLayout(2, 1));
			tweetContentPanel.add(tweetHeaderPanel, -1);
			tweetContentPanel.add(tweetContent, -1);

			JPanel tweetPanel = new JPanel(new GridLayout(1, 2));
			tweetPanel.add(userImage, -1);
			tweetPanel.add(tweetContentPanel, -1);

			add(tweetPanel);
		}
	}

}
