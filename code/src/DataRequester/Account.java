package DataRequester;

import java.awt.Image;

public class Account {
	private Image profilePicture;
	private String userName;
	private long userId;
	
	public Account(String userName, long userId,Image profilePicture) {
		this.profilePicture = profilePicture;
		this.userName = userName;
		this.userId = userId;
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
}
