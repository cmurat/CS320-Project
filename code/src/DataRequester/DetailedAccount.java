package DataRequester;

import java.util.ArrayList;

import twitter4j.User;

public class DetailedAccount extends Account {
	private ArrayList<Tweet> tweets;
	private boolean followStatus;
	private boolean followRequestSent;
	private boolean accountProtected;

	public DetailedAccount(User user, long userId, ArrayList<Tweet> tweets) {
		super(user);
		this.user = user;
		this.tweets = tweets;
	}

	public int getNumberOfTweets() {
		return user.getStatusesCount();
	}

	public ArrayList<Tweet> getTweets() {
		return tweets;
	}

	public int getFollowersAmount() {
		return user.getFollowersCount();
	}

	public int getFollowingsAmount() {
		return user.getFriendsCount();
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
