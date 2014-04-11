package GUI;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import DataRequester.Tweet;

public class MainPanel extends JPanel {
	
	private GUI gui;
	private NavigationBar navigationBar;
	private TweetBox tweetBox;
	private MainContent mainContent;
	
	public MainPanel(GUI gui) {
		this.gui = gui;
		setBounds(gui.getBounds());
		setOpaque(false);
		navigationBar = new NavigationBar(this);
		add(navigationBar);
//		tweetBox = new TweetBox();
	}
	
	public void printTimeline(ArrayList<Tweet> tweets) {
		System.out.println("\nSay it is printed..");
//		mainContent = new TweetStream();
//		TweetStream tweetStream = (TweetStream) mainContent;
//		tweetStream.addTweets(tweets);
	}

}
