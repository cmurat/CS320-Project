package DataRequester;

import java.io.File;

import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class TweetHandler {
	private final static int TWEET_LENGTH = 140;
	private final static int MEDIA_LENGTH = 26;
	Twitter twitter;

	public TweetHandler(Twitter twitter) {
		this.twitter = twitter;
	}

	public void postTweet(String tweet, String imageLocation) {
		StatusUpdate status = new StatusUpdate(tweet);
		if (!imageLocation.equals(""))
			status.media(new File(imageLocation));

		try {
			twitter.updateStatus(status);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

	public boolean checkTweetValid(String tweet, String imageLocation) {
		if (tweet.length() > TWEET_LENGTH)
			return false;
		if (tweet.length() >= TWEET_LENGTH - MEDIA_LENGTH
				&& imageLocation.length() != 0)
			return false;
		return true;
	}

	public void delete(long tweetID){
		try {
			twitter.destroyStatus(tweetID);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void retweet(long tweetID){
		try {
			twitter.retweetStatus(tweetID);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void favorite(long tweetID){
		try {
			twitter.createFavorite(tweetID);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void unFavorite(long tweetID){
		try {
			twitter.destroyFavorite(tweetID);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
