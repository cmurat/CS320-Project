package GUI;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import DataRequester.Tweet;

@SuppressWarnings("serial")
public class MainPanel extends JPanel {
	
	private GUI gui;
	private NavigationBar navigationBar;
	private TweetBox tweetBox;
	private MainContent mainContent;
	
	public MainPanel(GUI gui) {
		this.gui = gui;
		setBounds(gui.getBounds());
		setLayout(new BorderLayout());
		setOpaque(false);
		navigationBar = new NavigationBar(this);
		add(navigationBar, BorderLayout.NORTH);
		tweetBox = new TweetBox(this);
		add(tweetBox, BorderLayout.SOUTH);
	}
	
	public void printTimeline(ArrayList<Tweet> tweets) {
		System.out.println("\nSay it is printed..");
//		mainContent = new TweetStream();
//		TweetStream tweetStream = (TweetStream) mainContent;
//		tweetStream.addTweets(tweets);
	}

	public void tweetEntered() {
		gui.tweetEntered();
	}

	public String getTweet() {
		return tweetBox.getTweet();
	}

}
