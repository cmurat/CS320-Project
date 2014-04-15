package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import twitter4j.DirectMessage;
import twitter4j.ResponseList;
import DataRequester.DetailedAccount;
import DataRequester.Tweet;

@SuppressWarnings("serial")
public class MainPanel extends JPanel {

	private GUI gui;
	private TweetBox tweetBox;
	private ProfilePanel profilePanel;
	private TweetStream tweetStream;
	private JScrollPane tweetPane;
	private DMessageView dMessageView;

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
		add(tweetBox, BorderLayout.SOUTH);
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
		tweetPane = tweetStream.printTweetStream(tweets);
		add(tweetBox, BorderLayout.SOUTH);
		add(tweetPane);
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
		profilePanel = new ProfilePanel(this, account);
		profilePanel.printProfilePanel();
		add(tweetBox, BorderLayout.SOUTH);
		revalidate();
		repaint();
		
	}
	
	public void printDMessages() {
		removeAll();
		dMessageView = new DMessageView(this);
		dMessageView.printDirectMessageView();
		add(tweetBox, BorderLayout.SOUTH);
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
