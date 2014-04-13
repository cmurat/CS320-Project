package GUIManager;

import java.awt.Desktop;
import java.net.URL;

import twitter4j.DirectMessage;
import twitter4j.ResponseList;
import twitter4j.TwitterException;
import DataRequesterManager.DataRequestManager;
import DataRequesterManager.DirectMessageRequests;
import GUI.GUI;

public class GUIManager {

	private GUI gui;
	private String loginURL;
	private DataRequestManager dataRequestManager;
	private TweetListenerHandler tweetListenerHandler;

	public GUIManager(DataRequestManager dataRequestManager) {
		this.dataRequestManager = dataRequestManager;
		this.loginURL = dataRequestManager.createRequestTokenURL();
		this.gui = new GUI(this);
		this.tweetListenerHandler=new TweetListenerHandler(this,dataRequestManager);
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

	
	public void printMainPanel() throws TwitterException {
		gui.printMainPanel(dataRequestManager.getTimeline());
	}

	public void backToLoginButtonClicked() {
		gui.printLoginPanel();
	}

	public void printLoginPanel() {
		gui.printLoginPanel();
	}
	

	public void postTweet() {//renamed from tweetEntered to postTweet
		String tweet = gui.getTweet();
		String imageLocation = null;//TODO for Burak and Ugur Manager need and imageLocation;
		tweetListenerHandler.postTweet(tweet,imageLocation);
		System.out.println("\nAssume it is sent: " + tweet);
	}
	

	public void favoriteClicked() {
			long tweetID = 0;//TODO for Burak and Ugur Manager needs long tweetID
			tweetListenerHandler.favorite(tweetID);
		}
	
	public void unFavoriteClicked(){		
			long tweetID = 0;//TODO for Burak and Ugur Manager needs long tweetID
			tweetListenerHandler.unFavorite(tweetID);			
	}
	
	public void retweetClicked(){		
			long tweetID = 0;//TODO for Burak and Ugur Manager needs long tweetID
			tweetListenerHandler.retweet(tweetID);			
	}
	
	public void deleteClicked(){		
			long tweetID = 0;//TODO for Burak and Ugur Manager needs long tweetID
			tweetListenerHandler.delete(tweetID);	
	}


	public void meButtonClicked() {
		gui.printProfile(dataRequestManager.getCurrentUserAccount());
	}

	public ResponseList<DirectMessage> getDirectMessages() {
		return dataRequestManager.getDirectMessages();
	}
}
