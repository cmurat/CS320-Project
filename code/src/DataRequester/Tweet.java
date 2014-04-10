package DataRequester;

import java.util.Date;


public class Tweet {
	private String content;
	private Date date;
	private String userName;
	private long userId;
	private long tweetId;
	public Tweet(long userId, long tweetId, String userName, String content, Date date ){
		this.content = content;
		this.userId = userId;
		this.tweetId = tweetId;
		this.userName = userName;
		this.date = date;
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
	
}
