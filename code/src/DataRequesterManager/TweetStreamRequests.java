package DataRequesterManager;

import java.util.ArrayList;

import DataRequester.NavigationHandler;
import DataRequester.Tweet;

public class TweetStreamRequests {
	DataRequestManager dataRequestManager;
	NavigationHandler navigationHandler;

	public TweetStreamRequests(NavigationHandler navigationHandler) {
		this.navigationHandler = navigationHandler;
	}

	public ArrayList<Tweet> getTimeline() {
		return navigationHandler.getTimeline();
	};

}
