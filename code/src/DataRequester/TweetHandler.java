package DataRequester;

import java.io.InputStreamReader;
import java.util.Scanner;

import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class TweetHandler {
	Twitter twitter;
	public TweetHandler(Twitter twitter){
		this.twitter = twitter;
	}
	public void postTweet() throws TwitterException {
		System.out.print("Enter a tweet:");
		Scanner br = new Scanner(new InputStreamReader(System.in));
		StatusUpdate status = new StatusUpdate(br.nextLine());
		twitter.updateStatus(status);
	}

}
