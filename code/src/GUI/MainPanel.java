package GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JPanel;

import twitter4j.DirectMessage;
import twitter4j.ResponseList;
import DataRequester.DetailedAccount;
import DataRequester.Tweet;

@SuppressWarnings("serial")
public class MainPanel extends JPanel {

	private GUI gui;
	private NavigationBar navigationBar;
	private TweetBox tweetBox;
	private MainContent mainContent;
	private TweetStream tweetStream;

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
		System.out.println("\nSay timeline is printed..");
		tweetStream = new TweetStream(this);
		add(tweetStream.printTweetStream(tweets));
	}

	public void addComponent(Component component) {
		add(component);
	}

	public void tweetEntered() {
		gui.tweetEntered();
	}

	public String getTweet() {
		return tweetBox.getTweet();
	}

	public void meButtonClicked() {
		gui.meButtonClicked();
	}

	public void printProfile(DetailedAccount account) {
		mainContent = new ProfilePanel(this, account);
		ProfilePanel profilePanel = (ProfilePanel) mainContent;
		profilePanel.printProfilePanel();
	}

	public ResponseList<DirectMessage> getDirectMessages() {
		return gui.getDirectMessages();
	}

	public void revalidateGUI() {
		gui.revalidate();
	}

	public void pack() {
		gui.pack();
	}

}
