package DataRequester;

import java.io.IOException;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class RequesterTest {
	public static void main(String args[]) throws IOException, IllegalStateException, TwitterException{
		Twitter twitter = TwitterFactory.getSingleton();
		AccountHandler handle = new AccountHandler(TwitterFactory.getSingleton());
		handle.loginTwitterFromStorage();
	}

}
