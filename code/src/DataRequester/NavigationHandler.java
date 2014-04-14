package DataRequester;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

public class NavigationHandler {
	Twitter twitter;

	public NavigationHandler(Twitter twitter) {
		this.twitter = twitter;
	}

	public ArrayList<Tweet> getTimeline()  {
		List<Status> statuses = null;
		try {
			statuses = twitter.getHomeTimeline();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Tweet> tweets = getTweets(statuses);
		return tweets;
	}

	public ArrayList<Tweet> getMentions(){
		@SuppressWarnings("deprecation")
		List<Status> statuses =null;
		try {
			statuses = twitter.getMentions();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Tweet> mentions = getTweets(statuses);
		return mentions;
	}

	public ArrayList<Tweet> getFavorites() throws TwitterException {
		List<Status> statuses = twitter.getFavorites();
		ArrayList<Tweet> favorites = getTweets(statuses);
		return favorites;
	}

	public ArrayList<Tweet> searchTweets(String toBeSearched)
			throws TwitterException {
		Query query = new Query(toBeSearched);
		QueryResult result = twitter.search(query);
		ArrayList<Tweet> queryResult = getTweets(result.getTweets());
		return queryResult;
	}

	private ArrayList<Tweet> getTweets(List<Status> statuses) {
		ArrayList<Tweet> tweets = new ArrayList<Tweet>();
		for (Status status : statuses) {
			Tweet currentTweet = new Tweet(status);
			tweets.add(currentTweet);
		}
		return tweets;
	}

	public ArrayList<Account> getFollowers() throws TwitterException {
		List<User> users = twitter.getFollowersList(twitter.getId(), -1);
		ArrayList<Account> accounts = getAccounts(users);
		return accounts;
	}

	public ArrayList<Account> getFollowings() throws TwitterException {
		List<User> users = twitter.getFriendsList(twitter.getId(), -1);
		ArrayList<Account> accounts = getAccounts(users);
		return accounts;
	}

	private ArrayList<Account> getAccounts(List<User> users) {
		ArrayList<Account> accounts = new ArrayList<Account>();
		for (User user : users) {
			Account currentAccount = new Account(user);
			accounts.add(currentAccount);
		}
		return accounts;
	}

	public void follow(long userToFollowId) throws TwitterException {
		twitter.createFriendship(userToFollowId);
	}

	public void unFollow(long userToUnFollowId) throws TwitterException {
		twitter.destroyFriendship(userToUnFollowId);
	}

}
