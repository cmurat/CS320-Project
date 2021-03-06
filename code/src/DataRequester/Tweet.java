package DataRequester;

import java.net.MalformedURLException;
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
		try {
			return new URL(user.getProfileImageURL());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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
	public String getScreenName(){
		return user.getScreenName();
	}

	public long getUserId() {
		return user.getId();
	}

	public long getTweetId() {
		return status.getId();
	}
	public boolean isRetweeted(){
		return status.isRetweetedByMe();
	}
	public boolean isFavorited(){
		return status.isFavorited();
	}

	public String toString() {
		String s = getUserName() + "\n" + getContent();
		return s;
	}

}
