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
	public AuthenticationRequests authenticationRequester;
	public DirectMessageRequests directMessagesRequester;
	public ProfileRequests profileRequests;
	public SearchRequests searchRequests;
	public TweetRequests tweetRequests;
	public UserRequests userRequests;
	public TweetStreamRequests tweetStreamRequester;
	
	public DataRequestManager(GUIManager guiManager2) {
		Twitter twitter = TwitterFactory.getSingleton();
		//this.navigationHandler = new NavigationHandler(twitter);
		this.authenticationRequester=new AuthenticationRequests(new AccountHandler(twitter),guiManager,this);
		this.directMessagesRequester=new DirectMessageRequests(new DMessageHandler(twitter));
		this.tweetStreamRequester=new TweetStreamRequests(this,new NavigationHandler(twitter) );
		 
		//TODO continue with constructor 
		
	}
	public boolean checkPIN(String pin) throws IOException{
		return authenticationRequester.checkPIN(pin);
	}
	
	public ArrayList<Tweet> getTimeline() throws TwitterException{
		return tweetStreamRequester.getTimeline();
	}
	public boolean isAuthExists(){
		return authenticationRequester.isAuthExists();
	}
	
	public String createRequestTokenURL(){
		return authenticationRequester.createRequestTokenURL();
	}
}
