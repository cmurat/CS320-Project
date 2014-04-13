package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.MediaTracker;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import DataRequester.DetailedAccount;
import DataRequester.Tweet;

@SuppressWarnings("serial")
public class ProfilePanel extends JPanel implements MainContent {

	private MainPanel mainPanel;
	private ArrayList<JLabel> labelList;
	private DetailedAccount account;
	private TweetStream tweetStream;
	private GridBagLayout gridBagLayout = new GridBagLayout();
	private GridBagConstraints cons = new GridBagConstraints();

	public ProfilePanel(MainPanel mainPanel, DetailedAccount account) {
		this.mainPanel = mainPanel;
		this.account = account;
		this.tweetStream = new TweetStream(mainPanel);
		labelList = new ArrayList<JLabel>();
		calculateBounds();
		setLayout(gridBagLayout);
		createLabels();
		printLabels();
		printTweetStream();
	}

	private void printTweetStream() {
		Component stream = tweetStream.printTweetStream(account.getTweets());
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.ipady = mainPanel.getBounds().width;
		cons.weightx = 0.0;
		cons.gridx = 0;
		cons.gridy = 3;
		cons.gridwidth = 3;
		gridBagLayout.setConstraints(stream, cons);
		add(stream);
	}

	private void calculateBounds() {
		setBounds(mainPanel.getBounds().x, 
				mainPanel.getBounds().y,
				mainPanel.getBounds().width, 
				mainPanel.getBounds().height);
	}

	private void printLabels() {
		for (JLabel label : labelList) {
			add(label);
		}
	}

	private void createLabels() {
		createProfilePictureLabel();
		createProfileNameLabel();
		//createUserIDLabel();
		createTweetsLabel();
		createFollowingsLabel();
		createFollowersLabel();
		createTweetsAmountLabel();
		createFollowingsAmountLabel();
		createFollowersAmountLabel();
	}

	private void createProfilePictureLabel() {
		URL imageURL = null;
		try {
			imageURL = new URL(account.getProfilePictureURL());
		} catch (MalformedURLException e) {
			System.err.println("couldn't locate image");
		}
		JLabel profilePicture = new JLabel(new ImageIcon(imageURL));
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.weightx = 1.0;
		cons.gridx = 0;
		cons.gridy = 0;
		gridBagLayout.setConstraints(profilePicture, cons);
		add(profilePicture);
	}

	private void createProfileNameLabel() {
		JLabel profileName = new JLabel(account.getUserName());
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.weightx = 1.0;
		cons.gridx = 1;
		cons.gridy = 0;
		gridBagLayout.setConstraints(profileName, cons);
		add(profileName);
	}

	private void createUserIDLabel() {
		JLabel userID = new JLabel("" + account.getUserID());
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.weightx = 1.0;
		cons.gridx = 2;
		cons.gridy = 0;
		gridBagLayout.setConstraints(userID, cons);
		add(userID);
	}

	private void createTweetsLabel() {
		JLabel tweets = new JLabel("Tweets");
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridx = 0;
		cons.gridy = 1;
		gridBagLayout.setConstraints(tweets, cons);
		add(tweets);
	}

	private void createFollowingsLabel() {
		JLabel followings = new JLabel("Followings");
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.weightx = 1.0;
		cons.gridx = 1;
		cons.gridy = 1;
		gridBagLayout.setConstraints(followings, cons);
		add(followings);
	}

	private void createFollowersLabel() {
		JLabel followers = new JLabel("Followers");
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.weightx = 1.0;
		cons.gridx = 2;
		cons.gridy = 1;
		gridBagLayout.setConstraints(followers, cons);
		labelList.add(followers);
	}

	private void createTweetsAmountLabel() {
		JLabel tweetNumber = new JLabel("" + account.getTweets().size());
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.weightx = 1.0;
		cons.gridx = 0;
		cons.gridy = 2;
		gridBagLayout.setConstraints(tweetNumber, cons);
		add(tweetNumber);
	}

	private void createFollowingsAmountLabel() {
		JLabel followingNumber = new JLabel("" + account.getFollowingsAmount());
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.weightx = 1.0;
		cons.gridx = 1;
		cons.gridy = 2;
		gridBagLayout.setConstraints(followingNumber, cons);
		add(followingNumber);
	}

	private void createFollowersAmountLabel() {
		JLabel followerNumber = new JLabel("" + account.getFollowersAmount());
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.weightx = 1.0;
		cons.gridx = 2;
		cons.gridy = 2;
		gridBagLayout.setConstraints(followerNumber, cons);
		add(followerNumber);
	}

	public void printProfilePanel() {
		System.out.println("\nAssume profile panel is painted.");
		mainPanel.addComponent(this);
		
		//mainPanel.pack();
		mainPanel.revalidateGUI();
	}

}
