package DataRequesterManager;

import java.io.IOException;
import java.util.ArrayList;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import DataRequester.AccountHandler;
import DataRequester.DMessageHandler;
import DataRequester.DetailedAccount;
import DataRequester.NavigationHandler;
import DataRequester.Tweet;
import DataRequester.TweetHandler;
import GUIManager.GUIManager;

public class DataRequestManager {

	public GUIManager guiManager;

	// DataRequesterManager classes
	public AuthenticationRequests authenticationRequests;
	public DirectMessageRequests directMessagesRequests;
	public ProfileRequests profileRequests;
	public SearchRequests searchRequests;
	public TweetRequests tweetRequests;
	public UserRequests userRequests;
	public TweetStreamRequests tweetStreamRequests;

	// Model Classes
	public AccountHandler accountHandler;
	public DMessageHandler dMessageHandler;
	public NavigationHandler navigationHandler;
	public TweetHandler tweetHandler;

	public DataRequestManager(GUIManager guiManager) {
		this.guiManager = guiManager;
		initModelClasses();
		initDataRequesterManagerClasses();
	}

	private void initModelClasses() {
		Twitter twitter = TwitterFactory.getSingleton();
		accountHandler = new AccountHandler(twitter);
		dMessageHandler = new DMessageHandler(twitter);
		navigationHandler = new NavigationHandler(twitter);
		tweetHandler = new TweetHandler(twitter);
	}

	private void initDataRequesterManagerClasses() {
		this.authenticationRequests = new AuthenticationRequests(accountHandler, guiManager, this);
		this.directMessagesRequests = new DirectMessageRequests(dMessageHandler);
		this.tweetStreamRequests = new TweetStreamRequests(navigationHandler, this);
		this.tweetRequests = new TweetRequests(tweetHandler, this);
	}

	public boolean checkPIN(String pin) throws IOException {
		return authenticationRequests.checkPIN(pin);
	}

	public ArrayList<Tweet> getTimeline() throws TwitterException {
		return tweetStreamRequests.getTimeline();
	}

	public boolean isAuthExists() {
		return authenticationRequests.isAuthExists();
	}

	public String createRequestTokenURL() {
		return authenticationRequests.createRequestTokenURL();
	}

	public DetailedAccount getDetailedAccount(long userId) {
		return accountHandler.getDetailedAccount(userId);
	}
	
	public DetailedAccount getCurrentUserAccount() {
		return accountHandler.getCurrentUserDetailedAccount();
	}
}
