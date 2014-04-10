package DataRequester;

import java.awt.Image;

public class ConnectedAccount {
	private Image profilePicture;
	private String userName;
	private long userId;
	public ConnectedAccount(String userName, long userId,Image profilePicture){
		this.profilePicture = profilePicture;
		this.userId = userId;
		this.userName = userName;
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
