package DataRequester;

import javax.swing.ImageIcon;

public class Account {
	private ImageIcon profilePicture;
	private String userName;
	private long userId;

	public Account(String userName, long userId, ImageIcon profileImage) {
		this.profilePicture = profileImage;
		this.userName = userName;
		this.userId = userId;
	}

	public ImageIcon getProfilePicture() {
		return profilePicture;
	}

	public String getUserName() {
		return userName;
	}

	public long getUserId() {
		return userId;
	}

	public String toString() {
		String s = userName + " " + userId;
		return s;

	}
}
