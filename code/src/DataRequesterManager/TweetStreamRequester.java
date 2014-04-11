package DataRequesterManager;

import java.util.ArrayList;

import twitter4j.TwitterException;

import DataRequester.NavigationHandler;
import DataRequester.Tweet;
import DataRequester.TweetHandler;

public class TweetStreamRequester {
	DataRequestManager dataRequestManager;
	NavigationHandler navigationHandler;
	
	public TweetStreamRequester(DataRequestManager dataRequestManager, NavigationHandler navigationHandler) {
		this.dataRequestManager=dataRequestManager;
		this.navigationHandler=navigationHandler;
	}
	
	public ArrayList<Tweet> getTimeline() throws TwitterException{
		return navigationHandler.getTimeline();	
	};

}
