package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JFrame;

import DataRequester.Account;
import DataRequester.DMessage;
import DataRequester.DetailedAccount;
import DataRequester.Tweet;
import GUIManager.GUIManager;

@SuppressWarnings("serial")
public class GUI extends JFrame {

	private GUIManager guiManager;
	private MainPanel mainPanel;
	private LoginPanel loginPanel;
	private NavigationBar navigationBar;

	public GUI(GUIManager guiManager) {
		this.guiManager = guiManager;
		mainPanel = null;
		loginPanel = null;
		navigationBar = null;
		initFrame();
	}

	private void initFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Twitter Desktop Streamer");
		calculateBounds();
		setLayout(new GridBagLayout());
		getContentPane().setBackground(Color.WHITE);
		setResizable(false);
		setVisible(true);
	}

	private void calculateBounds() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) (screenSize.getWidth() / 4);
		int height = (int) screenSize.getHeight() - getTaskBarHeight() - 30;
		int yPos = 0;
		int xPos = (int) (screenSize.getWidth() - width);
		setBounds(xPos, yPos, width, height);
	}

	private int getTaskBarHeight() {
		int taskBarHeight = Toolkit.getDefaultToolkit().getScreenInsets(
				getGraphicsConfiguration()).bottom;
		return taskBarHeight;
	}

	public void loginButtonClicked() {
		guiManager.loginButtonClicked();
	}

	public void printLoginPanel() {
		getContentPane().removeAll();
		if (loginPanel == null)
			loginPanel = new LoginPanel(this);
		loginPanel.printLoginPanel();
		add(loginPanel);
		getContentPane().repaint();
		getContentPane().validate();
	}

	public void pinEntered() {
		guiManager.pinEntered();
	}

	public void printPINInputPanel() {
		getContentPane().removeAll();
		loginPanel.printPINInputPanel();
		add(loginPanel);
		getContentPane().repaint();
		getContentPane().validate();
	}

	public void backToLoginButtonClicked() {
		guiManager.backToLoginButtonClicked();
	}

	public String getPIN() {
		return loginPanel.getPIN();
	}

	public void printMainPanel(ArrayList<Tweet> tweets) {
		getContentPane().removeAll();
		getContentPane().setLayout(new BorderLayout());
		if (navigationBar == null)
			navigationBar = new NavigationBar(this);
		if (mainPanel == null)
			mainPanel = new MainPanel(this);

		getContentPane().add(navigationBar, BorderLayout.NORTH);
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		printTimeline(tweets);
	}

	public void printTimeline(ArrayList<Tweet> tweets) {
		mainPanel.printTimeline(tweets);
	}

	public void tweetEntered() {
		guiManager.postTweet();
	}

	public void printMentions(ArrayList<Tweet> tweets) {
		mainPanel.printMentions(tweets);
	}

	public void printDMessages(ArrayList<DMessage> dMessages) {
		mainPanel.printDMessages(dMessages);
	}

	public String getTweet() {
		return mainPanel.getTweet();
	}

	public void meButtonClicked() {
		guiManager.meButtonClicked();
	}

	public void printProfile(DetailedAccount account) {
		mainPanel.printProfile(account);
	}

	public void homeButtonClicked() {
		guiManager.homeButtonClicked();
	}

	public void mentionsButtonClicked() {
		guiManager.mentionButtonClicked();
	}

	public void userNameClicked(long userId) {
		guiManager.userNameClicked(userId);
	}

	public void dMessageButtonClicked() {
		guiManager.dMessageButtonClicked();
	}

	public void followingClicked(long userId) {
		guiManager.followingClicked(userId);
	}

	public void printAccounts(ArrayList<Account> followers) {
		mainPanel.printAccounts(followers);
	}

	public void followersClicked(long userId) {
		guiManager.followersClicked(userId);
	}

	public void searchEntered() {
		guiManager.getSearch();
	}

	public String getSearch() {
		return mainPanel.getSearch();
	}

	public void searchButtonClicked() {
		guiManager.searchButtonClicked();
	}

	public void printSearchScreen() {
		mainPanel.printSearchScreen();
	}

	public void printSearchScreenResults(ArrayList<Tweet> searchResults) {
		mainPanel.printSearchScreenResult(searchResults);
	}

	public void followButtonPressed(long userID) {
		guiManager.followClicked(userID);
	}

	public void startRefreshTimer() {
		mainPanel.startRefreshTimer();
	}

	public void settingsButtonClicked() {
		guiManager.settingsButtonClicked();
	}

	public void printSettingsScreen() {
		mainPanel.printSettingsScreen();
	}

	public void favoriteButtonClicked(long tweetId) {
		guiManager.favorite(tweetId);
	}

	public void retweetButtonClicked(long tweetId) {
		guiManager.retweet(tweetId);
	}

	public void unFollowButtonClicked(long userID) {
		guiManager.unFollowClicked(userID);
	}

	public long getCurrentUserId() {
		return guiManager.getCurrentUserId();
	}

	public void deleteButtonClicked(long tweetId) {
		guiManager.delete(tweetId);
	}

	public boolean logOutButtonClicked() {
		return guiManager.logOutButtonClicked();
	}

	public void changeUsernameClicked(String screenName) {
		guiManager.changeUsernameClicked(screenName);
	}

	public void changeProfilePictureClicked(File image) {
		guiManager.changeProfilePictureClicked(image);
	}

	public String getCurrentUserScreenName() {
		return guiManager.getCurrentUserScreenName();
	}

	public void unFavoriteButtonClicked(long tweetId) {
		guiManager.unFavoriteButtonClicked(tweetId);
		
	}

	public void unRetweetButtonClicked(long tweetId) {
		guiManager.unRetweetButtonClicked(tweetId);
	}

	public void conversationClicked(String peer) {
		guiManager.conversationClicked(peer);
	}

	public void printConversationView(String peer, URL userPicture) {
		mainPanel.printConversationView(peer, userPicture);
	}

	public void backToMessageListClicked() {
		guiManager.backToMessageListClicked();
	}

}
