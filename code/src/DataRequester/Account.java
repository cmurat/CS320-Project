package DataRequester;

import java.awt.Image;
import java.util.ArrayList;

public class Account {
	private Image profilePicture;
	private String userName;
	private long userId;
	private ArrayList<Tweet> tweets;
	private ArrayList<ConnectedAccount> followers;
	private ArrayList<ConnectedAccount> followings;

	public Account(Image profilePicture, String userName, long userId,
			ArrayList<Tweet> tweets, ArrayList<ConnectedAccount> followers,
			ArrayList<ConnectedAccount> followings) {
		super();
		this.profilePicture = profilePicture;
		this.userName = userName;
		this.userId = userId;
		this.tweets = tweets;
		this.followers = followers;
		this.followings = followings;
	}

	public Image getProfilePicture() {
		return profilePicture;
	}

	public String getUserName() {
		return userName;
	}

	public long getUserId() {
		return userId;
	}

	public ArrayList<Tweet> getTweets() {
		return tweets;
	}

	public ArrayList<ConnectedAccount> getFollowers() {
		return followers;
	}

	public ArrayList<ConnectedAccount> getFollowings() {
		return followings;
	}
	

}
