package DataRequester;

import twitter4j.Twitter;
import twitter4j.TwitterException;


public class DMessageHandler {
	Twitter twitter;
	public DMessageHandler(Twitter twitter){
		this.twitter =twitter;
	}
	public void sendDirectMessage(long userId, String message) throws TwitterException{
		twitter.sendDirectMessage(userId, message);
	}
	public void getDirectMessages() throws TwitterException{
		twitter.getDirectMessages();
	}

}
