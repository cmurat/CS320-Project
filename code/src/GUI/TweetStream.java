package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import DataRequester.Tweet;
import GUI.listeners.UserNameListener;

@SuppressWarnings("serial")
public class TweetStream extends JPanel {

	private MainPanel mainPanel;
	private ArrayList<Tweet> tweets;

	public TweetStream(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
		tweets = new ArrayList<Tweet>();
		setLayout(new GridLayout(0, 1));
		
		setBackground(Color.white);
	}

	public JScrollPane printTweetStream(ArrayList<Tweet> tweets) {
		addTweets(tweets);
		printTweets();
		System.out.println("\nAssume TweetStream is painted.");
		JScrollPane tweetPane = new JScrollPane(this);
		tweetPane.setOpaque(true);
		tweetPane.getVerticalScrollBar().setUnitIncrement(16);
		tweetPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		tweetPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		return tweetPane;
	}

	private void addTweets(ArrayList<Tweet> tweets) {
		this.tweets.addAll(tweets);
	}

	private void printTweets() {
		for (Tweet tweet : tweets) {
			JPanel tweetPanel = new JPanel();
			JPanel imagePanel = new JPanel();
			imagePanel.setBackground(Color.WHITE);
			imagePanel.setOpaque(true);
			JPanel contentPanel = new JPanel();
			contentPanel.setBackground(Color.WHITE);
			contentPanel.setOpaque(true);
			contentPanel.setLayout(new GridLayout(2,1));
			tweetPanel.setLayout(new BorderLayout());
			addUserImage(tweet, imagePanel);
			addUserName(tweet, contentPanel);
			addTweetContent(tweet, contentPanel);
			tweetPanel.add(imagePanel,BorderLayout.WEST);
			tweetPanel.add(contentPanel,BorderLayout.CENTER);
			add(tweetPanel);
		}
	}

	private void addUserImage(Tweet tweet, JPanel imagePanel) {
		JLabel userImage = new JLabel(new ImageIcon(tweet.getUserImage()));
		imagePanel.add(userImage);
	}

	private void addUserName(Tweet tweet, JPanel contentPanel) {
		JButton userName = new JButton("" + tweet.getUserName());
		userName.setBorderPainted(false);
		userName.setFocusable(false);
		userName.setContentAreaFilled(false);
		userName.setHorizontalAlignment(JButton.LEFT);
		userName.addActionListener(new UserNameListener(tweet.getUserId(),userName ,mainPanel));
		userName.setCursor(new Cursor(Cursor.HAND_CURSOR));
		contentPanel.add(userName);
	}

	private void addDate(Tweet tweet, JPanel tweetPanel) {
		JLabel date = new JLabel("" + tweet.getDate());
		tweetPanel.add(date);
	}

	private void addTweetContent(Tweet tweet, JPanel contentPanel) {
		JTextArea tweetContent = new JTextArea(tweet.getContent());
		tweetContent.setLineWrap(true);
		tweetContent.setWrapStyleWord(true);
		tweetContent.setEditable(false);
		contentPanel.add(tweetContent);
	}
	
		

}
