package DataRequesterManager;

import java.util.ArrayList;

import twitter4j.TwitterException;
import DataRequester.NavigationHandler;
import DataRequester.Tweet;

public class TweetStreamRequests {
	DataRequestManager dataRequestManager;
	NavigationHandler navigationHandler;
	
	public TweetStreamRequests(NavigationHandler navigationHandler) {
		this.navigationHandler=navigationHandler;
	}
	
	public ArrayList<Tweet> getTimeline() throws TwitterException{
		return navigationHandler.getTimeline();	
	};

}
