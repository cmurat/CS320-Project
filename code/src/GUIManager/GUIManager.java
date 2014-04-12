package GUIManager;

import java.awt.Desktop;
import java.net.URL;

import twitter4j.TwitterException;
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

	public void favoriteClicked() {

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

	public void printMainPanel() throws TwitterException {
		gui.printMainPanel(dataRequestManager.getTimeline());
	}

	public void backToLoginButtonClicked() {
		gui.printLoginPanel();
	}

	public void printLoginPanel() {
		gui.printLoginPanel();
	}

	public void tweetEntered() {
		String tweet = gui.getTweet();
		System.out.println("\nAssume it is sent: " + tweet);
	}

	public void meButtonClicked() {
		gui.printProfile(dataRequestManager.getCurrentUserAccount());
	}
}
