package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import DataRequester.Account;
import DataRequester.DMessage;
import DataRequester.DetailedAccount;
import DataRequester.Tweet;

@SuppressWarnings("serial")
public class MainPanel extends JPanel {

	private GUI gui;
	private TweetBox tweetBox;
	private ProfilePanel profilePanel;
	private TweetStream tweetStream;
	private AccountStream accountStream;
	private JScrollPane tweetPane;
	private JScrollPane accountPane;
	private DMessageView dMessageView;
	private SearchScreen searchScreen;
	private SettingsScreen settingsScreen;

	public MainPanel(GUI gui) {
		this.gui = gui;
		setBounds(0, gui.getHeight() / 10, gui.getWidth(), gui.getHeight() - gui.getHeight() / 10);
		setPreferredSize(new Dimension(gui.getWidth(), gui.getHeight() - gui.getHeight() / 10));
		setLayout(new BorderLayout());
		setOpaque(true);
		setBackground(Color.WHITE);
		tweetBox = new TweetBox(this);
		add(tweetBox, BorderLayout.SOUTH);
	}

	public void printTimeline(ArrayList<Tweet> tweets) {
		removeAll();
		TweetStream tweetStream = new TweetStream(this);
		add(tweetStream.printTweetStream(tweets));
		add(tweetBox, BorderLayout.SOUTH);
		refresh();
	}
	public TweetBox getTweetBox(){
		return tweetBox;
	}

	private void stopTimelineTimer() {
		if (tweetStream != null)
			tweetStream.stopRefreshTimer();
	}

	public void refreshTimeline() {
		gui.homeButtonClicked();
	}

	public void refresh() {
		revalidate();
		repaint();
	}

	public void printMentions(ArrayList<Tweet> tweets) {
		removeAll();
		stopTimelineTimer();
		TweetStream tweetStream = new TweetStream(this);
		tweetPane = tweetStream.printTweetStream(tweets);
		add(tweetBox, BorderLayout.SOUTH);
		add(tweetPane, BorderLayout.NORTH);
		refresh();
	}

	public void tweetEntered() {
		gui.tweetEntered();
	}

	public String getTweet() {
		return tweetBox.getTweet();
	}

	public void meButtonClicked() {
		gui.meButtonClicked();
	}

	public void printProfile(DetailedAccount account) {
		removeAll();
		stopTimelineTimer();
		profilePanel = new ProfilePanel(this, account);
		add(profilePanel, BorderLayout.NORTH);
		add(tweetBox, BorderLayout.SOUTH);
		refresh();
	}

	public void printSearchScreen() {
		removeAll();
		stopTimelineTimer();
		searchScreen = new SearchScreen(this);
		add(searchScreen);
		refresh();
	}

	public void printSearchScreenResult(ArrayList<Tweet> searchResults) {
		TweetStream tweetStream = new TweetStream(this);
		searchScreen.printStream(tweetStream.printTweetStream(searchResults));
		refresh();
	}

	public void printDMessages(ArrayList<DMessage> dMessages) {
		removeAll();
		stopTimelineTimer();
		if (dMessageView == null)
			dMessageView = new DMessageView(this);
		dMessageView.printDMessageListPanel(dMessages);
		add(dMessageView, BorderLayout.CENTER);
		add(tweetBox, BorderLayout.SOUTH);
		refresh();
	}

	public void userNameClicked(long userId) {
		gui.userNameClicked(userId);
	}

	public void followingClicked(long userId) {
		gui.followingClicked(userId);
	}

	public void printAccounts(ArrayList<Account> accounts) {
		removeAll();
		stopTimelineTimer();
		accountStream = new AccountStream(this);
		accountPane = accountStream.printAccounttream(accounts);
		add(accountPane);
		refresh();
	}

	public void followersClicked(long userId) {
		gui.followersClicked(userId);
	}

	public void searchEntered() {
		gui.searchEntered();
	}

	public String getSearch() {
		return searchScreen.getSearch();
	}

	public void followButtonPressed(long userID) {
		gui.followButtonPressed(userID);
	}

	public void startRefreshTimer() {
		if (tweetStream != null) {
			System.out.println("\nrefresher started!");
			tweetStream.startRefreshTimer();
		}
	}

	public void favoriteButtonClicked(long tweetId) {
		gui.favoriteButtonClicked(tweetId);
	}

	public void retweetButtonClicked(long tweetId) {
		gui.retweetButtonClicked(tweetId);
	}

	public void unFollowButtonClicled(long userID) {
		gui.unFollowButtonClicked(userID);
	}

	public long getCurrentUserId() {
		return gui.getCurrentUserId();
	}

	public void deleteButtonClicked(long tweetId) {
		gui.deleteButtonClicked(tweetId);
	}

	public void printSettingsScreen() {
		removeAll();
		stopTimelineTimer();
		settingsScreen = new SettingsScreen(this);
		add(settingsScreen);
		refresh();
	}

	public void changeUsernameClicked(String screenName) {
		gui.changeUsernameClicked(screenName);
	}

	public void changeProfilePictureClicked(File image) {
		gui.changeProfilePictureClicked(image);
	}

	public String getCurrentUserScreenName() {
		return gui.getCurrentUserScreenName();
	}

	public void unFavoriteButtonClicked(long tweetId) {
		gui.unFavoriteButtonClicked(tweetId);
		
	}

	public void unRetweetButtonClicked(long tweetId) {
		gui.unRetweetButtonClicked(tweetId);
	}

	public void conversationClicked(String peer) {
		gui.conversationClicked(peer);
	}

	public void printConversationView(String peer, URL userPicture) {
		remove(tweetBox);
		dMessageView.printConversationView(peer, userPicture);
	}

	public void backToMessageListClicked() {
		gui.backToMessageListClicked();
		add(tweetBox, BorderLayout.SOUTH);
	}

	public void newDMessageButtonClicked() {
		gui.newDMessageButtonClicked();
	}

	public void printNewDMessagePanel() {
		dMessageView.printNewDMessageView();
	}

	public void newDMessageEntered() {
		gui.newDMessageEntered();
	}

	public String getNewDMessageReceiver() {
		return dMessageView.getNewDMessageReceiver();
	}

	public String getNewDMessage() {
		return dMessageView.getNewDMessage();
	}

	public void printDMessageError(String errorMessage) {
		dMessageView.printDMessageError(errorMessage);
	}

}
