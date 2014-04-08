package DataRequester;

import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class NavigationHandler {
	Twitter twitter;
	public NavigationHandler(Twitter twitter){
		this.twitter = twitter;
	}
	public void printTimeline() throws TwitterException {
		List<Status> statuses = twitter.getHomeTimeline();
		System.out.println("Showing home timeline.");
		for (Status status : statuses) {
			System.out.println( "@" +status.getUser().getName() + ""
					+status.getText());
			System.out.println();
		}
	}
	public void printMentions() throws TwitterException {
		List<Status> statuses = twitter.getMentions();
		System.out.println("Showing followers.");
		for (Status status : statuses) {
			System.out.println( "@" +status.getUser().getName() + ""
					+status.getText());
			System.out.println();
		}
	}
}
