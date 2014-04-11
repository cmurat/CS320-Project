package GUI;

import java.util.ArrayList;

import DataRequester.Tweet;

public class TweetStream implements MainContent {
	
	ArrayList<Tweet> tweets;
	
	public TweetStream() {
		tweets = new ArrayList<Tweet>();
	}
	
	public void addTweets(ArrayList<Tweet> tweets) {
		tweets.addAll(tweets);
	}
}
