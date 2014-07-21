package DataRequester;

import twitter4j.Twitter;

public class TwitterConnection {
	protected static Twitter twitter;
	
	
	public static void setTwitterUser(Twitter twitter) {
		TwitterConnection.twitter = twitter;
	}

}
