package DataRequester;

import java.io.IOException;

import javax.naming.directory.InvalidAttributesException;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class RequesterTest {
	public static void main(String args[]) throws IOException,
			IllegalStateException, TwitterException, InvalidAttributesException {
		Twitter twitter = TwitterFactory.getSingleton();
		AccountHandler handle = AccountHandler.getInstance();
		handle.setTwitterUser(twitter);
		handle.loginTwitterFromStorage();
	}

}
