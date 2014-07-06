package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

import DataRequester.Tweet;
import GUIListeners.UserNameListener;
import GUIListeners.TweetListeners.DeleteButtonListener;
import GUIListeners.TweetListeners.FavouriteButtonListener;
import GUIListeners.TweetListeners.ReplyButtonListener;
import GUIListeners.TweetListeners.RetweetButtonListener;

public class TweetStreamFactory{

	public static JScrollPane printTweetStream(ArrayList<Tweet> tweets, MainPanel mainPanel,TweetBox tweetBox) {
		JPanel tweetStream = new JPanel();
		tweetStream.setLayout(new BoxLayout(tweetStream, BoxLayout.PAGE_AXIS));
		printTweets(tweets,tweetStream,mainPanel,tweetBox);
		JScrollPane tweetPane = new JScrollPane(tweetStream);
		tweetPane.setOpaque(true);
		tweetPane.getVerticalScrollBar().setUnitIncrement(16);
		tweetPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		tweetPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		return tweetPane;
	}


	private static void printTweets(ArrayList<Tweet> tweets, JPanel tweetStream, MainPanel mainPanel, TweetBox tweetBox) {
		for (Tweet tweet : tweets) {
			JPanel buttonPanel = createPanel();
			buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));

			JPanel imagePanel = createPanel();
			addUserImage(tweet, imagePanel);

			JPanel contentPanel = createPanel();
			contentPanel.setLayout(new BorderLayout());
			
			
			JPanel nameAndScreenNamePanel = createPanel();
			nameAndScreenNamePanel.setLayout(new BorderLayout());
			
			addUserName(tweet, nameAndScreenNamePanel,mainPanel);
			
			addUserScreenName(tweet, nameAndScreenNamePanel);
			
			contentPanel.add(nameAndScreenNamePanel, BorderLayout.NORTH);
			
			addTweetContent(tweet, contentPanel);

			JPanel tweetPanel = new JPanel();
			tweetPanel.setBorder(new LineBorder(Color.BLACK, 1, true));
			tweetPanel.setLayout(new BorderLayout(0, -20));
			tweetPanel.add(imagePanel, BorderLayout.WEST);
			tweetPanel.add(contentPanel, BorderLayout.CENTER);
			
			
			addButtons(tweet, buttonPanel, tweetPanel,mainPanel,tweetStream,tweetBox);

			contentPanel.add(buttonPanel, BorderLayout.SOUTH);
			tweetPanel.setName(String.valueOf(tweet.getTweetId()));
			tweetStream.add(tweetPanel);
		}

	}


	private static void addButtons(Tweet tweet, JPanel buttonPanel,
			JPanel tweetPanel, MainPanel mainPanel, JPanel tweetStream, TweetBox tweetBox) {
		addReplyButton(buttonPanel, tweet,mainPanel,tweetBox);
		addFavouriteButton(buttonPanel, tweet,mainPanel);
		addRetweetButton(buttonPanel, tweet,mainPanel);

		if (mainPanel.getCurrentUserId() == tweet.getUserId()) {
			addDeleteButton(buttonPanel, tweet, tweetPanel,mainPanel,tweetStream);
		}
	}

	private static void addUserScreenName(Tweet tweet, JPanel nameAndScreenNamePanel) {
		JLabel userScreenName = new JLabel("@" + tweet.getScreenName());
		userScreenName.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 10));
		userScreenName.setAlignmentX(JLabel.LEFT);
		userScreenName.setHorizontalAlignment(JLabel.LEFT);
		nameAndScreenNamePanel.add(userScreenName, BorderLayout.CENTER);

	}

	private static void addReplyButton(JPanel buttonPanel, Tweet tweet, MainPanel mainPanel, TweetBox tweetBox) {
		JButton replyButton = getIconButton("icon/reply.png");
		replyButton.addActionListener(new ReplyButtonListener(mainPanel,
				tweetBox, tweet));
		buttonPanel.add(replyButton);

	}

	private static JPanel createPanel() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setOpaque(true);
		return panel;
	}

	private static void addDeleteButton(JPanel buttonPanel, Tweet tweet,
			JPanel tweetPanel, MainPanel mainPanel, JPanel tweetStream) {
		JButton deleteButton = getIconButton("icon/DeleteIcon.png");
		deleteButton.addActionListener(new DeleteButtonListener(tweet
				.getTweetId(), deleteButton, mainPanel, tweetPanel,tweetStream));
		buttonPanel.add(deleteButton);
	}

	private  static void addRetweetButton(JPanel buttonPanel, Tweet tweet, MainPanel mainPanel) {
		JButton retweetButton = getIconButton(selectRetweetButtonAddress(tweet));
		retweetButton.addActionListener(new RetweetButtonListener(tweet
				.getTweetId(), retweetButton, mainPanel, tweet));
		buttonPanel.add(retweetButton);

	}

	private static String selectRetweetButtonAddress(Tweet tweet) {
		String address = "icon/RetweetIconBefore.png";
		if (tweet.isRetweeted()) {
			address = "icon/RetweetIcon.png";
		}
		return address;
	}

	private static void addFavouriteButton(JPanel buttonPanel, Tweet tweet, MainPanel mainPanel) {
		JButton favouriteButton = getIconButton(selectFavouriteButtonAddress(tweet));
		favouriteButton.addActionListener(new FavouriteButtonListener(tweet
				.getTweetId(), favouriteButton, mainPanel, tweet));
		buttonPanel.add(favouriteButton);
	}

	private static String selectFavouriteButtonAddress(Tweet tweet) {
		String address = "icon/FavoriteIconBefore.png";
		if (tweet.isFavorited()) {
			address = "icon/FavoriteIcon.png";
		}
		return address;
	}

	private static JButton getIconButton(String iconLocation) {
		JButton button = new JButton();
		button.setBorderPainted(false);
		button.setFocusable(false);
		button.setContentAreaFilled(false);
		button.setIcon(getImageIcon(iconLocation));
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		return button;
	}

	private static ImageIcon getImageIcon(String iconPath) {
		Image img = new ImageIcon(iconPath).getImage();
		img = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		return new ImageIcon(img);
	}

	private static void addUserImage(Tweet tweet, JPanel imagePanel) {
		JLabel userImage = new JLabel(new ImageIcon(tweet.getUserImage()));
		imagePanel.add(userImage);
	}

	private static void addUserName(Tweet tweet, JPanel nameAndScreenNamePanel, MainPanel mainPanel) {
		JButton userName = new JButton("" + tweet.getUserName());
		userName.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 11));
		userName.setBorderPainted(false);
		userName.setFocusable(false);
		userName.setContentAreaFilled(false);
		userName.setHorizontalAlignment(JButton.LEFT);
		userName.setAlignmentX(JButton.LEFT);
		userName.setBorder(null);
		userName.addActionListener(new UserNameListener(tweet.getUserId(),
				userName, mainPanel));
		userName.setCursor(new Cursor(Cursor.HAND_CURSOR));
		nameAndScreenNamePanel.add(userName, BorderLayout.WEST);
	}


	private static void addTweetContent(Tweet tweet, JPanel contentPanel) {
		JTextArea tweetContent = new JTextArea(tweet.getContent());
		tweetContent.setLineWrap(true);
		tweetContent.setWrapStyleWord(true);
		tweetContent.setEditable(false);
		contentPanel.add(tweetContent, BorderLayout.CENTER);
	}

}
