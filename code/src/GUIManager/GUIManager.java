package GUIManager;

import java.awt.Desktop;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import twitter4j.DirectMessage;
import twitter4j.ResponseList;
import twitter4j.TwitterException;
import DataRequesterManager.DataRequestManager;
import GUI.GUI;

public class GUIManager {

	private GUI gui;
	private String loginURL;
	private DataRequestManager dataRequestManager;
	public TweetListenerHandler tweetListenerHandler;

	public GUIManager(DataRequestManager dataRequestManager) {
		this.dataRequestManager = dataRequestManager;
		this.loginURL = dataRequestManager.createRequestTokenURL();
		this.gui = new GUI(this);
		this.tweetListenerHandler = new TweetListenerHandler(this,
				dataRequestManager);
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
		String imageLocation = "";// TODO for Burak and Ugur Manager need and
									// imageLocation;
		tweetListenerHandler.postTweet(tweet, imageLocation);
		System.out.println("\nAssume it is sent: " + tweet);
	}

	public void meButtonClicked() {
		gui.printProfile(dataRequestManager.getCurrentUserAccount());
	}

	public long getCurrentUserId() {
		return dataRequestManager.getUserId();
	}
	
	public String getUserScreenName() {
		return dataRequestManager.getUserScreenName();
	}

	public void homeButtonClicked() {
		gui.printTimeline(dataRequestManager.getTimeline());
		gui.startRefreshTimer();
	}

	public void mentionButtonClicked() {
		gui.printTimeline(dataRequestManager.getMentions());
	}

	public void dMessageButtonClicked() {
		ResponseList<DirectMessage> allDMessages = getAllDMessages();
		ArrayList<DMessage> dMessages = convertThemForGUI(allDMessages);
		gui.printDMessages(dMessages);
	}

	private ArrayList<DMessage> convertThemForGUI(
			ResponseList<DirectMessage> allDMessages) {
		String userScreenName = getUserScreenName();
		ArrayList<DMessage> dMessages = new ArrayList<DMessage>();
		for (int i = 0; i < allDMessages.size(); i++) {
			dMessages.add(new DMessage(allDMessages.get(i), userScreenName));
		}
		return dMessages;
	}

	private ResponseList<DirectMessage> getAllDMessages() {
		ResponseList<DirectMessage> receivedDMessages = dataRequestManager.getDirectMessages();
		ResponseList<DirectMessage> sentDMessages = dataRequestManager.getSentDirectMessages();
		receivedDMessages.addAll(sentDMessages);
		sortDMessagesByDate(receivedDMessages);
		return receivedDMessages;
	}

	private void sortDMessagesByDate(ResponseList<DirectMessage> receivedDMessages) {
		Collections.sort(receivedDMessages, new Comparator<DirectMessage>() {
			@Override
			public int compare(DirectMessage o1, DirectMessage o2) {
				Date date1 = o1.getCreatedAt();
				Date date2 = o2.getCreatedAt();
				return date2.compareTo(date1);
			}
		});
	}

	public ResponseList<DirectMessage> getDirectMessages() {
		return dataRequestManager.getDirectMessages();
	}

	public void sendMessageClicked() {
		long userId = 0;// TODO long userId, String message needed to derived
						// from gui Burak and Ugur
		String message = null;
		dataRequestManager.sendMessage(userId, message);
	}

	public void followClicked(long userToFollowId) {
		try {
			dataRequestManager.navigationHandler.follow(userToFollowId);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

	public void unFollowClicked(long userToUnFollowId) {
		try {
			dataRequestManager.navigationHandler.unFollow(userToUnFollowId);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
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
		return dataRequestManager.accountRequests.logOutClicked();
	}

	public void changeUsernameClicked(String screenName) {
		dataRequestManager.accountRequests.changeUsername(screenName);
	}

	public void changeProfilePictureClicked(File image) {
		dataRequestManager.accountRequests.changeProfilePicture(image);
	}

}
