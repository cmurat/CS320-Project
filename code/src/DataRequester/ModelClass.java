package DataRequester;

import twitter4j.Twitter;

public class ModelClass {
	protected static Twitter twitter;
	
	
	public static void setTwitterUser(Twitter twitter) {
		ModelClass.twitter = twitter;
	}

}
