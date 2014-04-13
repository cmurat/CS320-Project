package DataRequester;

import java.net.URL;
import java.util.Date;

import twitter4j.Status;
import twitter4j.User;

public class Tweet {
	private User user;
	private Status status; 

	public Tweet(Status status) {
		this.status = status;
		this.user = status.getUser();
	}

	public URL getUserImage() {
		return user.getProfileImageUrlHttps();
				//getProfileImageURL();
	}

	public String getContent() {
		return status.getText();
	}

	public Date getDate() {
		return status.getCreatedAt();
	}

	public String getUserName() {
		return user.getName();
	}

	public long getUserId() {
		return status.getId();
	}

	public long getTweetId() {
		return status.getId();
	}

	public String toString() {
		String s = getUserName() + "\n" + getContent();
		return s;
	}

}
