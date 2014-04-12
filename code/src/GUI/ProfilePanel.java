package GUI;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import DataRequester.DetailedAccount;

@SuppressWarnings("serial")
public class ProfilePanel extends JPanel implements MainContent {

	private MainPanel mainPanel;
	private ArrayList<JLabel> labelList;
	private DetailedAccount account;
	private TweetStream tweetStream;

	public ProfilePanel(MainPanel mainPanel, DetailedAccount account) {
		this.mainPanel = mainPanel;
		this.account = account;
		this.tweetStream = new TweetStream(mainPanel);
		labelList = new ArrayList<JLabel>();

		calculateBounds();
		setLayout(new GridLayout(0, 3));
		addLabels();
		printLabels();
		printTweetStream();
	}

	private void printTweetStream() {
		add(tweetStream);
	}

	private void calculateBounds() {
		setBounds(mainPanel.getBounds().x, mainPanel.getBounds().y,
				mainPanel.getBounds().width, mainPanel.getBounds().height);
	}

	private void printLabels() {
		for (JLabel label : labelList) {
			add(label);
		}
	}

	private void addLabels() {
		addProfilePicture();
		addProfileName();
		addUserID();
		addTweetsLabel();
		addFollowingsLabel();
		addFollowersLabel();
		addTweetsAmountLabel();
		addFollowingsAmountLabel();
		addFollowersAmountLabel();
	}

	private void addProfilePicture() {
		//TODO Ugur: abi new JLabel() icindeki getProfilePictureURL in etrafina new ImageIcon cak bir tane
		JLabel profilePicture = new JLabel(account.getProfilePictureURL());
		labelList.add(profilePicture);
	}

	private void addProfileName() {
		JLabel profileName = new JLabel(account.getUserName());
		labelList.add(profileName);
	}

	private void addUserID() {
		JLabel userID = new JLabel("" + account.getUserID());
		labelList.add(userID);
	}

	private void addTweetsLabel() {
		JLabel tweets = new JLabel("Tweets");
		labelList.add(tweets);
	}

	private void addFollowingsLabel() {
		JLabel followings = new JLabel("Followings");
		labelList.add(followings);
	}

	private void addFollowersLabel() {
		JLabel followers = new JLabel("Followers");
		labelList.add(followers);
	}

	private void addTweetsAmountLabel() {
		JLabel tweetNumber = new JLabel("" + account.getTweets().size());
		labelList.add(tweetNumber);
	}

	private void addFollowingsAmountLabel() {
		JLabel followingNumber = new JLabel("" + account.getFollowingsAmount());
		labelList.add(followingNumber);
	}

	private void addFollowersAmountLabel() {
		JLabel followerNumber = new JLabel("" + account.getFollowersAmount());
		labelList.add(followerNumber);
	}

	public void printProfilePanel() {
		System.out.println("\nAssume profile panel is painted.");
	}

}
