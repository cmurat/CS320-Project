package GUIManager;

import java.awt.Desktop;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import twitter4j.DirectMessage;
import twitter4j.ResponseList;
import DataRequesterManager.DataRequestManager;
import GUI.GUI;

public class GUIManager {

	private GUI gui;
	private String loginURL;
	private DataRequestManager dataRequestManager;

	public GUIManager(DataRequestManager dataRequestManager) {
		this.dataRequestManager = dataRequestManager;
		this.loginURL = dataRequestManager.createRequestTokenURL();
		this.gui = new GUI(this);
	}

	public void loginButtonClicked() {
		openWebPage();
		gui.printPINInputPanel();
	}

	private void openWebPage() {
		try {
			Desktop.getDesktop().browse(new URL(loginURL).toURI());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pinEntered() {
		try {
			if (dataRequestManager.checkPIN(gui.getPIN())) {
				gui.printMainPanel(dataRequestManager.getTimeline());
			} else {
				System.out.println("\nEntered pin returned false at checkPIN!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void printMainPanel() {
		gui.printMainPanel(dataRequestManager.getTimeline());
	}

	public void backToLoginButtonClicked() {
		gui.printLoginPanel();
	}

	public void printLoginPanel() {
		gui.printLoginPanel();
	}

	public void postTweet() {
		String tweet = gui.getTweet();
		String imageLocation = "";
		dataRequestManager.postTweet(tweet, imageLocation);
		System.out.println("\nAssume it is sent: " + tweet);
	}

	public void meButtonClicked() {
		gui.printProfile(dataRequestManager.getCurrentUserAccount());
	}

	public long getCurrentUserId() {
		return dataRequestManager.getUserId();
	}

	public void homeButtonClicked() {
		gui.printTimeline(dataRequestManager.getTimeline());
		gui.startRefreshTimer();
	}

	public void mentionButtonClicked() {
		gui.printTimeline(dataRequestManager.getMentions());
	}

	public void dMessageButtonClicked() {
		gui.printDMessages(dataRequestManager.getAllDMessages());
	}

	public ResponseList<DirectMessage> getDirectMessages() {
		return dataRequestManager.getDirectMessages();
	}

	public void sendMessageClicked() {
		String screenName = "";// TODO long userId, String message needed to derived
		// from gui Burak and Ugur
		String message = null;
		dataRequestManager.sendMessage(screenName, message);
	}

	public void followClicked(long userToFollowId) {

		dataRequestManager.follow(userToFollowId);

	}

	public void unFollowClicked(long userToUnFollowId) {
		dataRequestManager.unFollow(userToUnFollowId);
	}

	public void userNameClicked(long userId) {
		gui.printProfile(dataRequestManager.getDetailedAccount(userId));
	}

	public void followingClicked(long userId) {
		gui.printAccounts(dataRequestManager.getFollowings(userId));

	}

	public void followersClicked(long userId) {
		gui.printAccounts(dataRequestManager.getFollowers(userId));

	}

	public void getSearch() {
		String search = gui.getSearch();
		gui.printSearchScreenResults(dataRequestManager
				.getSearchResults(search));
	}

	public void searchButtonClicked() {
		gui.printSearchScreen();

	}

	public void settingsButtonClicked() {
		gui.printSettingsScreen();
	}

	public boolean logOutButtonClicked() {
		return dataRequestManager.logOutClicked();
	}

	public void changeUsernameClicked(String screenName) {
		dataRequestManager.changeUsername(screenName);
	}

	public void changeProfilePictureClicked(File image) {
		dataRequestManager.changeProfilePicture(image);
	}

	public String getCurrentUserScreenName() {
		return dataRequestManager.getUserScreenName();
	}

	public void delete(long tweetId) {
		dataRequestManager.deleteTweet(tweetId);
		
	}

	public void retweet(long tweetId) {
		dataRequestManager.retweet(tweetId);
		
	}

	public void favorite(long tweetId) {
		dataRequestManager.favorite(tweetId);
		
	}

	public void unFavoriteButtonClicked(long tweetId) {
		dataRequestManager.unFavorite(tweetId);
		
	}

	public void unRetweetButtonClicked(long tweetId) {
		dataRequestManager.unRetweet(tweetId);
		
	}

	public void conversationClicked(String peer) {
		String userPicture = dataRequestManager.getCurrentUserAccount().getProfilePictureURL();
		gui.printConversationView(peer, getPictureURL(userPicture));
	}

	private URL getPictureURL(String userPicture) {
		URL pictureURL = null;
		try {
			pictureURL = new URL(userPicture);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return pictureURL;
	}

	public void backToMessageListClicked() {
		gui.printDMessages(null);
	}

	public void newDMessageButtonClicked() {
		gui.printNewDMessagePanel();
	}

	public void newDMessageEntered() {
		String receiver = gui.getNewDMessageReceiver();
		// TODO continue from here..
		System.out.println("Say it is sent.");
	}

}
