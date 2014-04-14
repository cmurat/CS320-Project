package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
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
	private TweetBox tweetBox;
	private MainContent mainContent;
	private TweetStream tweetStream;

	public MainPanel(GUI gui) {
		this.gui = gui;
		setBounds(0, gui.getHeight()/10, gui.getWidth(), gui.getHeight());
		setLayout(new BorderLayout());
		setOpaque(true);
		setBackground(Color.WHITE);
		tweetBox = new TweetBox(this);
		add(tweetBox, BorderLayout.SOUTH);
	}

	public void printTimeline(ArrayList<Tweet> tweets) {
		removeAll();
		System.out.println("\nSay timeline is printed..");
		tweetStream = new TweetStream(this);
		add(tweetStream.printTweetStream(tweets));
		revalidate();
		repaint();
	}

	public void addComponent(Component component) {
		add(component);
	}
	public void printMentions(ArrayList<Tweet> tweets) {
		removeAll();
		System.out.println("\nSay mentions is printed..");
		tweetStream = new TweetStream(this);
		add(tweetStream.printTweetStream(tweets));
		revalidate();
		repaint();
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
		removeAll();
		mainContent = new ProfilePanel(this, account);
		ProfilePanel profilePanel = (ProfilePanel) mainContent;
		profilePanel.printProfilePanel();
		revalidate();
		repaint();
		
	}

	public ResponseList<DirectMessage> getDirectMessages() {
		return gui.getDirectMessages();
	}

	public void revalidateGUI() {
		gui.validate();
	}
	public void userNameClicked(long userId){
		gui.userNameClicked(userId);
	}

}
