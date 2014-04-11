package Twitter;

import twitter4j.TwitterException;
import DataRequesterManager.DataRequestManager;
import GUIManager.GUIManager;

public class Twitter {

	private DataRequestManager dataRequestManager;
	private GUIManager guiManager;

	public Twitter() throws TwitterException {
		dataRequestManager = new DataRequestManager(guiManager);
		guiManager = new GUIManager(dataRequestManager);
		if (dataRequestManager.isAuthExists()) {
			guiManager.printMainFrame();
			
		} else {
			guiManager.printLoginPage();
		}
	}

	public static void main(String args[]) throws TwitterException {
		Twitter twitter = new Twitter();
	}

}
