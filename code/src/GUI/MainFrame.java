package GUI;

import java.util.ArrayList;

import javax.swing.JFrame;

import DataRequester.Tweet;

public class MainFrame extends JFrame {
	
	private NavigationBar navigationBar;
	private TweetBox tweetBox;
	private MainContent mainContent;
	
	public MainFrame() {
		navigationBar = new NavigationBar();
		tweetBox = new TweetBox();
	}
	
	public void printTimeline(ArrayList<Tweet> tweets) {
		mainContent = new TweetStream();
		TweetStream tweetStream = (TweetStream) mainContent;
		tweetStream.addTweets(tweets);
	}

}
