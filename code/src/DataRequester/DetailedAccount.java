package DataRequester;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class DetailedAccount extends Account {
	private ArrayList<Tweet> tweets;
	private int followers;
	private int followings;

	public DetailedAccount(ImageIcon profilePicture, String userName, long userId,
			ArrayList<Tweet> tweets, int followers,
			int followings) {
		super(userName, userId, profilePicture);
		this.tweets = tweets;
		this.followers = followers;
		this.followings = followings;
	}


	public ArrayList<Tweet> getTweets() {
		return tweets;
	}

	public int getFollowers() {
		return followers;
	}

	public int getFollowings() {
		return followings;
	}
	

}
