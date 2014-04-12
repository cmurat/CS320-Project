package DataRequester;

import javax.swing.ImageIcon;

public class Account {
	private ImageIcon profilePicture;
	private String userName;
	private long userId;
	private int tweetNumber;

	public Account(String userName, long userId, ImageIcon profileImage, int tweetNumber) {
		this.profilePicture = profileImage;
		this.userName = userName;
		this.userId = userId;
		this.tweetNumber = tweetNumber;
	}

	public ImageIcon getProfilePicture() {
		return profilePicture;
	}

	public String getUserName() {
		return userName;
	}

	public long getUserID() {
		return userId;
	}
	
	public int getTweetNumber(){
		return tweetNumber;
	}

	public String toString() {
		String s = userName + " " + userId;
		return s;

	}
}
