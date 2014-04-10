package DataRequesterManager;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import DataRequester.AccountHandler;
import DataRequester.DMessageHandler;
import DataRequester.NavigationHandler;
import DataRequester.TweetHandler;

public class DataRequestManager {
	public AccountHandler accountHandler;
	public TweetHandler tweetHandler;
	public DMessageHandler dMessageHandler;
	public NavigationHandler navigationHandler;
	
	public DataRequestManager() {
		Twitter twitter = TwitterFactory.getSingleton();
		this.accountHandler = new AccountHandler(twitter);
		this.tweetHandler = new TweetHandler(twitter);
		this.dMessageHandler = new DMessageHandler(twitter);
		this.navigationHandler = new NavigationHandler(twitter);
	}
}
