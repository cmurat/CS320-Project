package GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import DataRequester.DetailedAccount;

@SuppressWarnings("serial")
public class ProfilePanel extends JPanel implements MainContent {

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
		tweetStreamPanel.setLayout(new GridLayout(1,1));
		addTweetStreamTo(tweetStreamPanel);
		addUserInfoTo(userInfoPanel);

		add(userInfoPanel, BorderLayout.NORTH);
		add(tweetStreamPanel, BorderLayout.CENTER);
	}

	private void addUserInfoTo(JPanel userInfoPanel) {
		JPanel picturePanel = new JPanel();
		picturePanel
				.setLayout(new BoxLayout(picturePanel, BoxLayout.Y_AXIS));
		createProfilePictureLabel(picturePanel);
		createProfileNameLabel(picturePanel);
		userInfoPanel.add(picturePanel, BorderLayout.NORTH);

		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(2,3));
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
		JLabel followings = new JLabel("Followings");
		followings.setHorizontalAlignment(JLabel.CENTER);
		userInfoPanel.add(followings);
	}

	private void createFollowersLabel(JPanel userInfoPanel) {
		JLabel followers = new JLabel("Followers");
		followers.setHorizontalAlignment(JLabel.CENTER);
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
