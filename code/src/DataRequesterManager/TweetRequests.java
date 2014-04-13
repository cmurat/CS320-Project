package DataRequesterManager;

import twitter4j.TwitterException;
import DataRequester.TweetHandler;

public class TweetRequests {
	TweetHandler tweetHandler;
	DataRequestManager dataRequestManager;

	public TweetRequests(TweetHandler tweetHandler,
			DataRequestManager dataRequestManager) {
		this.tweetHandler=tweetHandler;
		this.dataRequestManager=dataRequestManager;
	}

	public void postTweet(String tweet, String imageLocation){
		try {
			if(tweetHandler.checkTweetValid(tweet, imageLocation)){
				tweetHandler.postTweet(tweet, imageLocation);
			}
			else{
				System.out.println("PLease review your tweet content and decrease its size.");
			}
		} catch (Exception e) {
			System.out.println("Exception in posting tweet in TweetRequests");
			e.printStackTrace();
		}
	}

	public void favouriteTweet(long tweetID){
		try {
			tweetHandler.favorite(tweetID);
		} catch (TwitterException e) {
			System.out.println("Exception at favourite tweet");
			e.printStackTrace();
		}
	}
	
	public void unFavouriteTweet(long tweetID){
		try {
			tweetHandler.unFavorite(tweetID);
		} catch (TwitterException e) {
			System.out.println("Exception at Unfavourite tweet");
			e.printStackTrace();
		}
	}
	public void retweetTweet(long tweetID){
		try {
			tweetHandler.retweet(tweetID);
		} catch (TwitterException e) {
			System.out.println("Exception at retweet tweet");
			e.printStackTrace();
		}
	}

	public void deleteTweet(long tweetID){
		try {
			tweetHandler.delete(tweetID);
		} catch (TwitterException e) {
			System.out.println("Exception at deleting tweet");
			e.printStackTrace();
		}
	}


}
