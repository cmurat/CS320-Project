package DataRequester;

import twitter4j.DirectMessage;
import twitter4j.ResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class DMessageHandler {
	Twitter twitter;

	public DMessageHandler(Twitter twitter) {
		this.twitter = twitter;
	}

	public void sendDirectMessage(long userId, String message) throws TwitterException {
		twitter.sendDirectMessage(userId, message);
	}

	public ResponseList<DirectMessage> getDirectMessages() {
		try {
			return twitter.getDirectMessages();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return null;
	}
	public ResponseList<DirectMessage> getSentDirectMessages(){
		try {
			return twitter.getSentDirectMessages();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return null;
	}

}
