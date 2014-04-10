package DataRequester;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class NavigationHandler {
	Twitter twitter;
	public NavigationHandler(Twitter twitter){
		this.twitter = twitter;
	}
	public ArrayList<Tweet> getTimeline() throws TwitterException {
		List<Status> statuses = twitter.getHomeTimeline();
		ArrayList<Tweet> tweets = new ArrayList<Tweet>();
		for (Status status : statuses) {
			Tweet currentTweet = new Tweet(status.getUser().getId(),status.getId(),status.getUser().getName(),status.getText(),status.getCreatedAt());
			tweets.add(currentTweet);
		}
		return tweets;
	}
	public ArrayList<Tweet> getMentions() throws TwitterException {
		List<Status> statuses = twitter.getMentions();
		ArrayList<Tweet> mentions = new ArrayList<Tweet>();
		for (Status status : statuses) {
			Tweet currentTweet = new Tweet(status.getUser().getId(),status.getId(),status.getUser().getName(),status.getText(),status.getCreatedAt());
			mentions.add(currentTweet);
		}
		return mentions;
	}
}
