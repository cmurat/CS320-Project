package DataRequester;

import java.io.File;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.naming.directory.InvalidAttributesException;

import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class TweetHandler {
	private final static int TWEET_LENGTH = 140;
	private final static int MEDIA_LENGTH = 26;
	Twitter twitter;
	public TweetHandler(Twitter twitter){
		this.twitter = twitter;
	}
	public void postTweet(String tweet, String imageLocation) throws TwitterException, InvalidAttributesException {
		StatusUpdate status = new StatusUpdate(tweet);
		if(!imageLocation.equals(""))
			status.media(new File(imageLocation));
		twitter.updateStatus(status);
	}
	public boolean checkTweetValid(String tweet, String imageLocation){
		if(tweet.length()>TWEET_LENGTH)
			return false;
		if(tweet.length()>=TWEET_LENGTH-MEDIA_LENGTH && imageLocation.length()!=0)
			return false;
		return true;
	}
	public void deleteTweet(long tweetID) throws TwitterException {
		twitter.destroyStatus(tweetID);
	}
	public void retweet(long tweetID) throws TwitterException{
		twitter.retweetStatus(tweetID);
	}
	public void favorite(long tweetID) throws TwitterException{
		twitter.createFavorite(tweetID);
	}
	public void unFavorite(long tweetID) throws TwitterException{
		twitter.destroyFavorite(tweetID);
	}

}
