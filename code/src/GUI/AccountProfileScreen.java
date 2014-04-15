package GUI;

import java.awt.BorderLayout;
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

public class AccountProfileScreen extends JPanel {
	private static final long serialVersionUID = 5820507962378503935L;
	private DetailedAccount account;
	private MainPanel mainPanel;
	public AccountProfileScreen(DetailedAccount account,MainPanel mainPanel){
		this.account = account;
		this.mainPanel = mainPanel;
		setLayout(new BorderLayout());
		addUserInfoTo();
	}
	private void addUserInfoTo() {
		JPanel picturePanel = new JPanel();
		picturePanel.setLayout(new BoxLayout(picturePanel, BoxLayout.Y_AXIS));
		createProfilePictureLabel(picturePanel);
		createProfileNameLabel(picturePanel);
		add(picturePanel, BorderLayout.NORTH);

		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(2, 3));
		createLabels(infoPanel);
		add(infoPanel, BorderLayout.CENTER);

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
		tweets.setOpaque(true);
		userInfoPanel.add(tweets);
	}

	private void createFollowingsLabel(JPanel userInfoPanel) {
		JButton followings = buttonCreater("Followings");
		followings.addActionListener(new FollowingsListener(account.getUserID(), followings, mainPanel));
		userInfoPanel.add(followings);
	}


	private void createFollowersLabel(JPanel userInfoPanel) {
		JButton followers = buttonCreater("Followers");
		followers.addActionListener(new FollowersListener(account.getUserID(), followers, mainPanel));
		userInfoPanel.add(followers);
	}

	private JButton buttonCreater(String name) {
		JButton button = new JButton(name);
		button.setHorizontalAlignment(JButton.CENTER);
		button.setBorderPainted(false);
		button.setOpaque(true);
		button.setFocusable(false);
		button.setContentAreaFilled(false);
		button.setHorizontalAlignment(JButton.LEFT);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		return button;
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

}
