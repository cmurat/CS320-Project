package GUI;

import java.util.ArrayList;

import javax.swing.JPanel;

import DataRequester.Tweet;

public class TweetStream extends JPanel implements MainContent {
	
	private MainPanel mainPanel;
	private ArrayList<Tweet> tweets;
	
	public TweetStream(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
		tweets = new ArrayList<Tweet>();
		calculateBounds();
	}
	
	private void calculateBounds() {
		int xPos = mainPanel.getBounds().x;
		int yPos = mainPanel.getBounds().y + mainPanel.getBounds().height / 10;
		int width = mainPanel.getBounds().width;
		int height = mainPanel.getBounds().height - 2 * yPos;
		setBounds(xPos, yPos, width, height);
	}

	public void addTweets(ArrayList<Tweet> tweets) {
		tweets.addAll(tweets);
	}
}
