package DataRequester;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class DetailedAccount extends Account {
	private ArrayList<Tweet> tweets;
	private int followers;
	private int followings;
	private int numberOfTweets;
	private boolean followStatus;
	private boolean followRequestSent;
	private boolean accountProtected;

	public DetailedAccount(ImageIcon profilePicture, String userName,
			long userId, ArrayList<Tweet> tweets, int followers,
			int followings, int numberOfTweets) {
		super(userName, userId, profilePicture);
		this.tweets = tweets;
		this.followers = followers;
		this.followings = followings;
		this.numberOfTweets = numberOfTweets;
	}

	public int getNumberOfTweets() {
		return numberOfTweets;
	}

	public ArrayList<Tweet> getTweets() {
		return tweets;
	}

	public int getFollowersAmount() {
		return followers;
	}

	public int getFollowingsAmount() {
		return followings;
	}

	public boolean isAccountProtected() {
		return accountProtected;
	}

	public void setAccountProtected(boolean accountProtected) {
		this.accountProtected = accountProtected;
	}

	public boolean isFollowStatus() {
		return followStatus;
	}

	public void setFollowStatus(boolean followStatus) {
		this.followStatus = followStatus;
	}

	public boolean isFollowRequestSent() {
		return followRequestSent;
	}

	public void setFollowRequestSent(boolean followRequestSent) {
		this.followRequestSent = followRequestSent;
	}

}
