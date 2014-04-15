package GUIManager;

import twitter4j.TwitterException;
import DataRequesterManager.DataRequestManager;

public class TweetListenerHandler {
	DataRequestManager dataRequestManager;
	GUIManager guiManager;

	public TweetListenerHandler(GUIManager guiManager,
			DataRequestManager dataRequestManager) {
		this.dataRequestManager=dataRequestManager;
		this.guiManager=guiManager;
	}

	public void postTweet(String tweet, String imageLocation) {
		dataRequestManager.postTweet(tweet, imageLocation);
	}

	public void favorite(long tweetID) {
		try {
			dataRequestManager.tweetHandler.favorite(tweetID);
		} catch (TwitterException e) {
			
			e.printStackTrace();
		}
		
	}

	public void unFavorite(long tweetID) {
		try {
			dataRequestManager.tweetHandler.unFavorite(tweetID);
		} catch (TwitterException e) {
			
			e.printStackTrace();
		}
		
	}

	public void retweet(long tweetID) {
		try {
			dataRequestManager.tweetHandler.retweet(tweetID);
		} catch (TwitterException e) {
			
			e.printStackTrace();
		}
	}

	public void delete(long tweetID) {
		try {
			dataRequestManager.tweetHandler.delete(tweetID);
		} catch (TwitterException e) {
			
			e.printStackTrace();
		}	
	}

}
