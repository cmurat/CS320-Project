package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagLayout;
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
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import DataRequester.Tweet;

@SuppressWarnings("serial")
public class TweetStream extends JPanel implements MainContent {

	private MainPanel mainPanel;
	private ArrayList<Tweet> tweets;

	public TweetStream(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
		tweets = new ArrayList<Tweet>();
		setLayout(new GridLayout(0, 1));
		
		setBackground(Color.white);
	}

	public Component printTweetStream(ArrayList<Tweet> tweets) {
		addTweets(tweets);
		printTweets();
		System.out.println("\nAssume TweetStream is painted.");
		JScrollPane thePane = new JScrollPane(this);
		thePane.setOpaque(true);
		thePane.getVerticalScrollBar().setUnitIncrement(16);
		thePane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		thePane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		return thePane;
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
			imagePanel.setLayout(new GridBagLayout());
			JPanel contentPanel = new JPanel();
			contentPanel.setBackground(Color.WHITE);
			contentPanel.setOpaque(true);
			contentPanel.setLayout(new GridLayout(2,1));
			tweetPanel.setLayout(new BorderLayout());
			addUserImage(tweet, imagePanel);
			addUserName(tweet, contentPanel);
			addTweetContent(tweet, contentPanel);
			tweetPanel.setBorder(new LineBorder(Color.black));
			tweetPanel.add(imagePanel,BorderLayout.WEST);
			tweetPanel.add(contentPanel,BorderLayout.CENTER);
			add(tweetPanel);
		}
	}

	private void addUserImage(Tweet tweet, JPanel imagePanel) {
		JLabel userImage = new JLabel(new ImageIcon(tweet.getUserImage()));
		userImage.setBorder(new LineBorder(Color.black,1,true));
		imagePanel.add(userImage);
	}

	private void addUserName(Tweet tweet, JPanel contentPanel) {
		JButton userName = new JButton("" + tweet.getUserName());
		userName.setBorderPainted(false);
		userName.setFocusable(false);
		userName.setContentAreaFilled(false);
		userName.setHorizontalAlignment(JButton.LEFT);
		userName.addActionListener(userNameListener(tweet.getUserId(),userName));
		userName.setCursor(new Cursor(Cursor.HAND_CURSOR));
		contentPanel.add(userName);
	}

	private void addDate(Tweet tweet, JPanel tweetPanel) {
		
		JLabel date = new JLabel("" + tweet.getDate());
		tweetPanel.add(date);
	}

	private void addTweetContent(Tweet tweet, JPanel contentPanel) {
		JTextArea tweetContent = new JTextArea(tweet.getContent());
		tweetContent.setEditable(false);
		tweetContent.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,12));
		tweetContent.setLineWrap(true);
		tweetContent.setOpaque(false);
		contentPanel.add(tweetContent);
	}

	private ActionListener userNameListener(final long userId, final JButton userName){
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("\n User Name is clicked!");
				System.out.println(userId);
				userName.setCursor(new Cursor(Cursor.WAIT_CURSOR));
				mainPanel.userNameClicked(userId);
				userName.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		};
		
	}
		

}
