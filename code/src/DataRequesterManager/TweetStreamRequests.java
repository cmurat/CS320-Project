package DataRequesterManager;

import java.util.ArrayList;

import twitter4j.TwitterException;

import DataRequester.NavigationHandler;
import DataRequester.Tweet;
import DataRequester.TweetHandler;

public class TweetStreamRequests {
	DataRequestManager dataRequestManager;
	NavigationHandler navigationHandler;
	
	public TweetStreamRequests(DataRequestManager dataRequestManager, NavigationHandler navigationHandler) {
		this.dataRequestManager=dataRequestManager;
		this.navigationHandler=navigationHandler;
	}
	
	public ArrayList<Tweet> getTimeline() throws TwitterException{
		return navigationHandler.getTimeline();	
	};

}
