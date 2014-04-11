package GUIManager;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import twitter4j.TwitterException;

import DataRequesterManager.*;
import GUI.GUI;

public class GUIManager {

	private GUI gui;
	private String loginURL = "https://api.twitter.com/oauth/authorize";
	private DataRequestManager dataRequestManager;

	public GUIManager(DataRequestManager dataRequestManager) {
		this.dataRequestManager = dataRequestManager;
		gui = new GUI(this);
		//.printLoginPage();
	}

	public void pinEntered() throws IOException, TwitterException {
		if (dataRequestManager.checkPIN(gui.getPIN())) {
			gui.printMainFrame(dataRequestManager.getTimeline());
		} else {
			// if pin is not true
		}

	}

	public void favoriteClicked() {

	}

	public void loginButtonClicked() {
		openWebPage();
		gui.printPINField();

	}

	private void openWebPage() {
		try {
			Desktop.getDesktop().browse(new URL(loginURL).toURI());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void printMainFrame() throws TwitterException {
		gui.printMainFrame(dataRequestManager.getTimeline());

	}

	public void backToLoginButtonClicked() {
		gui.printLoginButton();
	}

	public void printLoginPage() {
		gui.printLoginPage();

	}

	public void postTweetClicked() {
		// get tweet content from view
		// dataRequestManager.tweetRequests.postTweet(String tweet, String
		// imageLocation);
	}
}
