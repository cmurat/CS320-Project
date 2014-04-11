package DataRequester;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;

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

	public ArrayList<Tweet> getTimeline() throws TwitterException {
		List<Status> statuses = twitter.getHomeTimeline();
		ArrayList<Tweet> tweets = new ArrayList<Tweet>();
		for (Status status : statuses) {
			long userId = status.getUser().getId();
			long tweetId = status.getId();
			String userName = status.getUser().getName();
			String content = status.getText();
			Date createTime = status.getCreatedAt();
			ImageIcon profileImage = new ImageIcon(status.getUser()
					.getProfileImageURL());
			Tweet currentTweet = new Tweet(userId, tweetId, userName, content,
					createTime, profileImage);
			tweets.add(currentTweet);
		}
		return tweets;
	}

	public ArrayList<Tweet> getMentions() throws TwitterException {
		@SuppressWarnings("deprecation")
		List<Status> statuses = twitter.getMentions();
		ArrayList<Tweet> mentions = new ArrayList<Tweet>();
		for (Status status : statuses) {
			long userId = status.getUser().getId();
			long tweetId = status.getId();
			String userName = status.getUser().getName();
			String content = status.getText();
			Date createTime = status.getCreatedAt();
			ImageIcon profileImage = new ImageIcon(status.getUser()
					.getProfileImageURL());
			Tweet currentTweet = new Tweet(userId, tweetId, userName, content,
					createTime, profileImage);
			mentions.add(currentTweet);
		}
		return mentions;
	}

	public ArrayList<Tweet> getFavorites() throws TwitterException {
		List<Status> statuses = twitter.getFavorites();
		ArrayList<Tweet> favorites = new ArrayList<Tweet>();
		for (Status status : statuses) {
			long userId = status.getUser().getId();
			long tweetId = status.getId();
			String userName = status.getUser().getName();
			String content = status.getText();
			Date createTime = status.getCreatedAt();
			ImageIcon profileImage = new ImageIcon(status.getUser()
					.getProfileImageURL());
			Tweet currentTweet = new Tweet(userId, tweetId, userName, content,
					createTime, profileImage);
			favorites.add(currentTweet);
		}
		return favorites;
	}

	public ArrayList<RepresentationAccount> getFollowers()
			throws TwitterException {
		List<User> users = twitter.getFollowersList(twitter.getId(), -1);
		ArrayList<RepresentationAccount> accounts = new ArrayList<RepresentationAccount>();
		for (User user : users) {
			ImageIcon profileImage = new ImageIcon(user.getProfileImageURL());
			String userName = user.getName();
			long userId = user.getId();
			RepresentationAccount currentAccount = new RepresentationAccount(
					userName, userId, profileImage);
			accounts.add(currentAccount);
		}
		return accounts;
	}

	public ArrayList<RepresentationAccount> getFollowings()
			throws TwitterException {
		List<User> users = twitter.getFriendsList(twitter.getId(), -1);
		ArrayList<RepresentationAccount> accounts = new ArrayList<RepresentationAccount>();
		for (User user : users) {
			ImageIcon profileImage = new ImageIcon(user.getProfileImageURL());
			String userName = user.getName();
			long userId = user.getId();
			RepresentationAccount currentAccount = new RepresentationAccount(
					userName, userId, profileImage);
			accounts.add(currentAccount);
		}
		return accounts;
	}

	public ArrayList<Tweet> searchTweets(String toBeSearched)
			throws TwitterException {
		ArrayList<Tweet> queryResult = new ArrayList<Tweet>();
		Query query = new Query(toBeSearched);
		QueryResult result = twitter.search(query);
		for (Status status : result.getTweets()) {
			long userId = status.getUser().getId();
			long tweetId = status.getId();
			String userName = status.getUser().getName();
			String content = status.getText();
			Date createTime = status.getCreatedAt();
			ImageIcon profileImage = new ImageIcon(status.getUser()
					.getProfileImageURL());
			Tweet currentTweet = new Tweet(userId, tweetId, userName, content,
					createTime, profileImage);
			queryResult.add(currentTweet);
		}
		return queryResult;
	}

	public void follow(long userToFollowId) throws TwitterException {
		twitter.createFriendship(userToFollowId);
	}

	public void unFollow(long userToUnFollowId) throws TwitterException {
		twitter.destroyFriendship(userToUnFollowId);
	}

}
