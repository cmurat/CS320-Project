package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.LineBorder;

import DataRequester.Tweet;
import GUI.listeners.DeleteButtonListener;
import GUI.listeners.FavouriteButtonListener;
import GUI.listeners.ReplyButtonListener;
import GUI.listeners.RetweetButtonListener;
import GUI.listeners.UserNameListener;

@SuppressWarnings("serial")
public class TweetStream extends JPanel {

	private static int refreshTime = 30000;

	private MainPanel mainPanel;
	private ArrayList<Tweet> tweets;
	private Timer refreshTimer;
	private TweetBox tweetBox;

	public TweetStream(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
		this.tweetBox = mainPanel.getTweetBox();
		refreshTimer = new Timer();
		tweets = new ArrayList<Tweet>();
		setLayout(new GridLayout(0,1));

		setBackground(Color.white);
	}

	public JScrollPane printTweetStream(ArrayList<Tweet> tweets) {
		printTweets(tweets);
		JScrollPane tweetPane = new JScrollPane(this);
		tweetPane.setOpaque(true);
		tweetPane.getVerticalScrollBar().setUnitIncrement(16);
		tweetPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		tweetPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		return tweetPane;
	}

	public void changeRefreshTimerDelay(int delay) {
		refreshTime = delay;
	}

	public void startRefreshTimer() {
		refreshTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				mainPanel.refreshTimeline();
				System.out.println("Timeline is refreshed!");
			}
		}, refreshTime, refreshTime);
	}

	public void stopRefreshTimer() {
		try {
			refreshTimer.cancel();
		} catch (IllegalStateException e) {
			// e.printStackTrace(); OK..
		}
	}

	private void addTweets(ArrayList<Tweet> tweets) {
		this.tweets.addAll(tweets);
	}

	private void printTweets(ArrayList<Tweet> tweets) {
		for (Tweet tweet : tweets) {
			JPanel buttonPanel = createPanel();
			buttonPanel.setLayout(new FlowLayout());
			
			JPanel imagePanel = createPanel();
			addUserImage(tweet, imagePanel);
			
			JPanel contentPanel = createPanel();
			contentPanel.setLayout(new BorderLayout());
			JPanel nameAndScreenNamePanel = createPanel();
			nameAndScreenNamePanel.setLayout(new BorderLayout());
			addUserName(tweet, nameAndScreenNamePanel);
			addUserScreenName(tweet,nameAndScreenNamePanel);
			contentPanel.add(nameAndScreenNamePanel,BorderLayout.NORTH);
			addTweetContent(tweet, contentPanel);
			
			JPanel tweetPanel = new JPanel();
			tweetPanel.setBorder(new LineBorder(Color.BLACK, 1, true));
			tweetPanel.setLayout(new BorderLayout(0, -20));
			tweetPanel.add(imagePanel, BorderLayout.WEST);
			tweetPanel.add(contentPanel, BorderLayout.CENTER);
			addReplyButton(buttonPanel, tweet);
			addFavouriteButton(buttonPanel, tweet);
			addRetweetButton(buttonPanel, tweet);
			
			if (mainPanel.getCurrentUserId() == tweet.getUserId()) {
				addDeleteButton(buttonPanel, tweet);
			}
			
			contentPanel.add(buttonPanel, BorderLayout.SOUTH);
			add(tweetPanel);
		}

	}

	private void addUserScreenName(Tweet tweet, JPanel nameAndScreenNamePanel) {
		JLabel userScreenName = new JLabel("@"+tweet.getScreenName());
		userScreenName.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,10));
		userScreenName.setAlignmentX(JLabel.LEFT);
		userScreenName.setHorizontalAlignment(JLabel.LEFT);
		nameAndScreenNamePanel.add(userScreenName,BorderLayout.CENTER);
		
	}

	private void addReplyButton(JPanel buttonPanel, Tweet tweet) {
		JButton replyButton = getIconButton("icon/reply.png");
		replyButton.addActionListener(new ReplyButtonListener(mainPanel,tweetBox,tweet));
		buttonPanel.add(replyButton);
		
	}

	private JPanel createPanel() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setOpaque(true);
		return panel;
	}

	private void addDeleteButton(JPanel buttonPanel, Tweet tweet) {
		JButton deleteButton = getIconButton("icon/DeleteIcon.png");
		deleteButton.addActionListener(
				new DeleteButtonListener(tweet.getTweetId(), deleteButton, mainPanel));
		buttonPanel.add(deleteButton);
	}

	private void addRetweetButton(JPanel buttonPanel, Tweet tweet) {
		JButton retweetButton = getIconButton(selectRetweetButtonAddress(tweet));
		retweetButton.addActionListener(
				new RetweetButtonListener(tweet.getTweetId(), retweetButton, mainPanel,tweet));
		buttonPanel.add(retweetButton);

	}
	
	private String selectRetweetButtonAddress(Tweet tweet){
		String address="icon/RetweetIconBefore.png";
		if(tweet.isRetweeted()){
			address="icon/RetweetIcon.png";
		}
		return address;
	}
	

	private void addFavouriteButton(JPanel buttonPanel, Tweet tweet) {
		JButton favouriteButton = getIconButton(selectFavouriteButtonAddress(tweet));
		favouriteButton.addActionListener(
				new FavouriteButtonListener(tweet.getTweetId(), favouriteButton, mainPanel,tweet));
		buttonPanel.add(favouriteButton);
	}
	
	private String selectFavouriteButtonAddress(Tweet tweet){
		String address="icon/FavoriteIconBefore.png";
		if(tweet.isFavorited()){
			address="icon/FavoriteIcon.png";
		}
		return address;
	}
	private JButton getIconButton(String iconLocation) {
		JButton button = new JButton();
		button.setBorderPainted(false);
		button.setFocusable(false);
		button.setContentAreaFilled(false);
		button.setIcon(getImageIcon(iconLocation));
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		return button;
	}

	private ImageIcon getImageIcon(String iconPath) {
		Image img = new ImageIcon(iconPath).getImage();
		img = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		return new ImageIcon(img);
	}

	private void addUserImage(Tweet tweet, JPanel imagePanel) {
		JLabel userImage = new JLabel(new ImageIcon(tweet.getUserImage()));
		imagePanel.add(userImage);
	}

	private void addUserName(Tweet tweet, JPanel nameAndScreenNamePanel) {
		JButton userName = new JButton("" + tweet.getUserName());
		userName.setFont(new Font(Font.SANS_SERIF,Font.BOLD,11));
		userName.setBorderPainted(false);
		userName.setFocusable(false);
		userName.setContentAreaFilled(false);
		userName.setHorizontalAlignment(JButton.LEFT);
		userName.setAlignmentX(JButton.LEFT);
		userName.setBorder(null);
		userName.addActionListener(new UserNameListener(tweet.getUserId(), userName, mainPanel));
		userName.setCursor(new Cursor(Cursor.HAND_CURSOR));
		nameAndScreenNamePanel.add(userName, BorderLayout.WEST);
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
		contentPanel.add(tweetContent, BorderLayout.CENTER);
	}

}
