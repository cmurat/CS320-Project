package DataRequester;

import java.util.Date;

import javax.swing.ImageIcon;

public class Tweet {
	private String content;
	private Date date;
	private String userName;
	private long userId;
	private long tweetId;
	private ImageIcon userImage;

	public Tweet(long userId, long tweetId, String userName, String content,
			Date date, ImageIcon userImage) {
		// TODO Neden profile picture? Neden bu kadar parametre?
		this.content = content;
		this.userId = userId;
		this.tweetId = tweetId;
		this.userName = userName;
		this.date = date;
		this.userImage = userImage;
	}

	public ImageIcon getUserImage() {
		return userImage;
	}

	public String getContent() {
		return content;
	}

	public Date getDate() {
		return date;
	}

	public String getUserName() {
		return userName;
	}

	public long getUserId() {
		return userId;
	}

	public long getTweetId() {
		return tweetId;
	}

	public String toString() {
		String s = userName + "\n" + content;
		return s;
	}

}
