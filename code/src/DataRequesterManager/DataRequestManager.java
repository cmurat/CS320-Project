package DataRequesterManager;

import java.io.IOException;
import java.util.ArrayList;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import DataRequester.*;
import GUIManager.*;



public class DataRequestManager {
	
	public GUIManager guiManager;
	public TweetHandler tweetHandler;
	public AuthenticationRequester authenticationRequests;
	public DirectMessageRequests directMessagesRequests;
	public ProfileRequests profileRequests;
	public SearchRequests searchRequests;
	public TweetRequests tweetRequests;
	public UserRequests userRequests;
	public TweetStreamRequests tweetStreamRequests;
	
	public DataRequestManager(GUIManager guiManager2) {
		Twitter twitter = TwitterFactory.getSingleton();
		//this.navigationHandler = new NavigationHandler(twitter);
		this.authenticationRequests=new AuthenticationRequester(new AccountHandler(twitter),guiManager,this);
		this.directMessagesRequests=new DirectMessageRequests(new DMessageHandler(twitter));
		this.tweetStreamRequests=new TweetStreamRequests(this,new NavigationHandler(twitter) );
		 
		//TODO continue with constructor 
		
	}
	public boolean checkPIN(String pin) throws IOException{
		return authenticationRequests.checkPIN(pin);
	}
	
	public ArrayList<Tweet> getTimeline() throws TwitterException{
		return tweetStreamRequests.getTimeline();
	}
	public boolean isAuthExists(){
		return authenticationRequests.isAuthExists();
	}
	
	public String createRequestTokenURL(){
		return authenticationRequests.createRequestTokenURL();
	}
}
