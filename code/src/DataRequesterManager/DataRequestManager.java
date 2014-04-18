package DataRequesterManager;

import java.io.IOException;
import java.util.ArrayList;

import twitter4j.DirectMessage;
import twitter4j.ResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import DataRequester.Account;
import DataRequester.AccountHandler;
import DataRequester.DMessage;
import DataRequester.DMessageHandler;
import DataRequester.DetailedAccount;
import DataRequester.NavigationHandler;
import DataRequester.Tweet;
import DataRequester.TweetHandler;
import GUIManager.GUIManager;

public class DataRequestManager {

	public GUIManager guiManager;

	// DataRequesterManager classes
	public AccountRequests accountRequests;
	public TweetRequests tweetRequests;
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
		this.accountRequests = new AccountRequests(accountHandler, this);
		this.tweetStreamRequests = new TweetStreamRequests(navigationHandler);
		this.tweetRequests = new TweetRequests(tweetHandler, this);
	}

	public String createRequestTokenURL() {
		return accountRequests.createRequestTokenURL();
	}

	public boolean checkPIN(String pin) throws IOException {
		return accountRequests.checkPIN(pin);
	}

	public boolean isAuthExists() {
		return accountRequests.isAuthExists();
	}

	public DetailedAccount getDetailedAccount(long userId) {
		return accountRequests.getDetailedAccount(userId);
	}

	public DetailedAccount getCurrentUserAccount() {
		return accountRequests.getCurrentUserDetailedAccount();
	}

	public ArrayList<Tweet> getTimeline() {
		return tweetStreamRequests.getTimeline();
	}

	public void sendMessage(long userId, String message) {
		try {
			dMessageHandler.sendDirectMessage(userId, message);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

	public ResponseList<DirectMessage> getDirectMessages() {
		return dMessageHandler.getDirectMessages();
	}
	
	public ResponseList<DirectMessage> getSentDirectMessages() {
		return dMessageHandler.getSentDirectMessages();
	}

	public ArrayList<Tweet> getMentions() {
		return navigationHandler.getMentions();
	}

	public void postTweet(String tweet, String imageLocation) {
		tweetHandler.postTweet(tweet, imageLocation);
	}

	public ArrayList<Account> getFollowers(long userId) {
		return navigationHandler.getFollowers(userId);
	}

	public ArrayList<Account> getFollowings(long userId) {
		return navigationHandler.getFollowings(userId);
	}

	public ArrayList<Tweet> getSearchResults(String search) {
		return navigationHandler.searchTweets(search);
	}

	public long getUserId() {
		return accountHandler.getAccountUserID();
	}

	public ArrayList<DMessage> getAllDMessages() {
		return dMessageHandler.getAllDMessages();
	}
}
