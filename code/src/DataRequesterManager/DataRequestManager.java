package DataRequesterManager;

import java.io.File;
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


	// Model Classes
	public AccountHandler accountHandler;
	public DMessageHandler dMessageHandler;
	public NavigationHandler navigationHandler;
	public TweetHandler tweetHandler;

	public DataRequestManager(GUIManager guiManager) {
		this.guiManager = guiManager;
		initModelClasses();
//		initDataRequesterManagerClasses();
	}

	private void initModelClasses() {
		Twitter twitter = TwitterFactory.getSingleton();
		accountHandler = new AccountHandler(twitter);
		dMessageHandler = new DMessageHandler(twitter);
		navigationHandler = new NavigationHandler(twitter);
		tweetHandler = new TweetHandler(twitter);
	}
	public String createRequestTokenURL() {
		return accountHandler.createRequestTokenURL();
	}

	public boolean checkPIN(String pin){
		return accountHandler.loginTwitterNewUser(pin);
	}

	public boolean isAuthExists() {
		boolean result = false;
		result = accountHandler.loginTwitterFromStorage();
		return result;
	}

	public DetailedAccount getDetailedAccount(long userId) {
		return accountHandler.getDetailedAccount(userId);
	}

	public DetailedAccount getCurrentUserAccount() {
		return accountHandler.getHomeAccount();
	}

	public ArrayList<Tweet> getTimeline() {
		return navigationHandler.getTimeline();
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

	public String getUserScreenName() {
		return accountHandler.getAccountUserScreenName();
	} 

	public void follow(long userToFollowId) {
		navigationHandler.follow(userToFollowId);
		
	}

	public void unFollow(long userToUnFollowId) {
		navigationHandler.unFollow(userToUnFollowId);
		
	}

	public boolean logOutClicked() {
		accountHandler.logout();
		return true;
	}

	public void changeUsername(String screenName) {
		accountHandler.setProfileName(screenName);
	}

	public void changeProfilePicture(File image) {
		accountHandler.setProfilePicture(image);
		
	}

	public void deleteTweet(long tweetId) {
		tweetHandler.delete(tweetId);
		
	}

	public void retweet(long tweetId) {
		tweetHandler.retweet(tweetId);
		
	}

	public void favorite(long tweetId) {
		tweetHandler.favorite(tweetId);
		
	}

	public void unFavorite(long tweetId) {
		tweetHandler.unFavorite(tweetId);
		
	}

	public void unRetweet(long tweetId) {
		tweetHandler.unRetweet(tweetId);
		
	}
}
