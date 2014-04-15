package GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import DataRequester.DetailedAccount;
import GUI.listeners.FollowersListener;
import GUI.listeners.FollowingsListener;

@SuppressWarnings("serial")
public class ProfilePanel extends JPanel {

	private MainPanel mainPanel;
	private DetailedAccount account;
	private TweetStream tweetStream;

	public ProfilePanel(MainPanel mainPanel, DetailedAccount account) {
		this.mainPanel = mainPanel;
		this.account = account;
		this.tweetStream = new TweetStream(mainPanel);
		calculateBounds();
		setLayout(new BorderLayout());

		JPanel userInfoPanel = new JPanel();
		userInfoPanel.setLayout(new BorderLayout());

		JPanel tweetStreamPanel = new JPanel();
		tweetStreamPanel.setLayout(new GridLayout(1, 1));
		
		
		add(userInfoPanel, BorderLayout.NORTH);
		add(tweetStreamPanel, BorderLayout.CENTER);
		
		addTweetStreamTo(tweetStreamPanel);
		addUserInfoTo(userInfoPanel);
		
	}
	
	private void addUserInfoTo(JPanel userInfoPanel) {
		JPanel picturePanel = new JPanel();
		picturePanel.setLayout(new BoxLayout(picturePanel, BoxLayout.Y_AXIS));
		createProfilePictureLabel(picturePanel);
		createProfileNameLabel(picturePanel);
		userInfoPanel.add(picturePanel, BorderLayout.NORTH);

		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(2, 3));
		createLabels(infoPanel);
		userInfoPanel.add(infoPanel, BorderLayout.CENTER);

	}

	private void addTweetStreamTo(JPanel tweetStreamPanel) {
		Component stream = tweetStream.printTweetStream(account.getTweets());
		tweetStreamPanel.add(stream);
	}

	private void calculateBounds() {
		setBounds(mainPanel.getBounds().x, mainPanel.getBounds().y,
				mainPanel.getBounds().width, mainPanel.getBounds().height);
	}

	private void createLabels(JPanel userInfoPanel) {

		createFollowingsLabel(userInfoPanel);
		createFollowersLabel(userInfoPanel);
		createTweetsLabel(userInfoPanel);
		createFollowingsAmountLabel(userInfoPanel);
		createFollowersAmountLabel(userInfoPanel);
		createTweetsAmountLabel(userInfoPanel);
	}

	private void createProfilePictureLabel(JPanel picturePanel) {
		URL imageURL = null;
		try {
			imageURL = new URL(account.getProfilePictureURL());
		} catch (MalformedURLException e) {
			System.err.println("couldn't locate image");
		}
		JLabel profilePicture = new JLabel(new ImageIcon(imageURL));
		profilePicture.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		picturePanel.add(profilePicture);
	}

	private void createProfileNameLabel(JPanel picturePanel) {
		JLabel profileName = new JLabel(account.getUserName());
		profileName.setHorizontalAlignment(JLabel.CENTER);
		profileName.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		picturePanel.add(profileName);
	}

	private void createTweetsLabel(JPanel userInfoPanel) {
		JLabel tweets = new JLabel("Tweets");
		tweets.setHorizontalAlignment(JLabel.CENTER);
		userInfoPanel.add(tweets);
	}

	private void createFollowingsLabel(JPanel userInfoPanel) {
		JButton followings = new JButton("Followings");
		followings.setHorizontalAlignment(JButton.CENTER);
		followings.setBorderPainted(false);
		followings.setFocusable(false);
		followings.setContentAreaFilled(false);
		followings.setHorizontalAlignment(JButton.LEFT);
		followings.addActionListener(new FollowingsListener(account.getUserID(),followings, mainPanel));
		followings.setCursor(new Cursor(Cursor.HAND_CURSOR));
		userInfoPanel.add(followings);
	}

	private void createFollowersLabel(JPanel userInfoPanel) {
		JButton followers = new JButton("Followers");
		followers.setHorizontalAlignment(JButton.CENTER);
		followers.setBorderPainted(false);
		followers.setFocusable(false);
		followers.setContentAreaFilled(false);
		followers.setHorizontalAlignment(JButton.LEFT);
		followers.addActionListener(new FollowersListener(account.getUserID(),followers, mainPanel));
		followers.setCursor(new Cursor(Cursor.HAND_CURSOR));
		userInfoPanel.add(followers);
	}

	private void createTweetsAmountLabel(JPanel userInfoPanel) {
		JLabel tweetNumber = new JLabel("" + account.getTweets().size());
		tweetNumber.setHorizontalAlignment(JLabel.CENTER);
		userInfoPanel.add(tweetNumber);
	}

	private void createFollowingsAmountLabel(JPanel userInfoPanel) {
		JLabel followingNumber = new JLabel("" + account.getFollowingsAmount());
		followingNumber.setHorizontalAlignment(JLabel.CENTER);
		userInfoPanel.add(followingNumber);
	}

	private void createFollowersAmountLabel(JPanel userInfoPanel) {
		JLabel followerNumber = new JLabel("" + account.getFollowersAmount());
		followerNumber.setHorizontalAlignment(JLabel.CENTER);
		userInfoPanel.add(followerNumber);
	}

	public void printProfilePanel() {
		System.out.println("\nAssume profile panel is painted.");
		mainPanel.addComponent(this);

	}

}
