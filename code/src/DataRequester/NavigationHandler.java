package DataRequester;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.User;

public class NavigationHandler extends TwitterConnection {
	private static NavigationHandler navigationHandler;
	public static NavigationHandler getInstance(){
		if(navigationHandler ==null)
			navigationHandler = new NavigationHandler();
		return navigationHandler;
		
	}

	public ArrayList<Tweet> getTimeline() {
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


	public ArrayList<Tweet> getMentions() {
		List<Status> statuses = null;
		try {
			statuses = twitter.getMentionsTimeline();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Tweet> mentions = getTweets(statuses);
		return mentions;
	}

	public ArrayList<Tweet> getFavorites(){
		List<Status> statuses = null;
		try {
			statuses = twitter.getFavorites();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Tweet> favorites = getTweets(statuses);
		return favorites;
	}

	public ArrayList<Tweet> searchTweets(String toBeSearched) {
		Query query = new Query(toBeSearched);
		QueryResult result = null;
		try {
			result = twitter.search(query);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
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

	public ArrayList<Account> getFollowers(long userId) {
		List<User> users = null;
		try {
			users = twitter.getFollowersList(userId, -1);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		ArrayList<Account> accounts = getAccounts(users);
		return accounts;
	}

	public ArrayList<Account> getFollowings(long userId) {
		List<User> users = null;

		try {
			users = twitter.getFriendsList(userId, -1);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (TwitterException e) {
			e.printStackTrace();
		}

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

	public void follow(long userToFollowId) {
		try {
			twitter.createFriendship(userToFollowId, true);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void unFollow(long userToUnFollowId){
		try {
			twitter.destroyFriendship(userToUnFollowId);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
