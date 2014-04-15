package DataRequester;

import java.awt.Component;

import twitter4j.User;

public class Account {
	protected User user;

	public Account(User user) {
		this.user = user;
	}

	public String getProfilePictureURL() {
		return user.getProfileImageURL();
	}

	public String getUserName() {
		return user.getName();
	}

	public long getUserID() {
		return user.getId();
	}

	public String toString() {
		String s = getUserName() + " " + getUserID();
		return s;

	}

}
