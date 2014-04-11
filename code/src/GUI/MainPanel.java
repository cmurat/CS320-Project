package GUI;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import DataRequester.Tweet;

public class MainPanel extends JPanel {
	
	private NavigationBar navigationBar;
	private TweetBox tweetBox;
	private MainContent mainContent;
	
	public MainPanel() {
		navigationBar = new NavigationBar();
		tweetBox = new TweetBox();
	}
	
	public void printTimeline(ArrayList<Tweet> tweets) {
		mainContent = new TweetStream();
		TweetStream tweetStream = (TweetStream) mainContent;
		tweetStream.addTweets(tweets);
	}

}
