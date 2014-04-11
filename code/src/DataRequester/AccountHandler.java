package DataRequester;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class AccountHandler {
	Twitter twitter;
	private RequestToken requestToken;

	public AccountHandler(Twitter twitter) {
		this.twitter = twitter;
	}

	public boolean loginTwitterFromStorage() throws IOException {

		AccessToken accessToken = loadAccessToken();
		if (accessToken == null)
			return false;
		twitter.setOAuthAccessToken(accessToken);
		return true;
	}

	public boolean loginTwitterNewUser(String Pin) throws IOException {
		AccessToken accessToken;
		try {
			accessToken = createAccessToken(Pin);

		} catch (TwitterException e) {
			return false;
		}
		twitter.setOAuthAccessToken(accessToken);
		storeAccessToken(accessToken);
		return true;
	}

	public String createRequestTokenURL() {
		try {
			requestToken = twitter.getOAuthRequestToken();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return requestToken.getAuthenticationURL();
	}

	private AccessToken createAccessToken(String Pin) throws TwitterException {
		AccessToken access = twitter.getOAuthAccessToken(requestToken, Pin);
		return access;
	}

	private void storeAccessToken(AccessToken accessToken) throws IOException {
		Writer output;
		output = new BufferedWriter(new FileWriter("twitter4j.password", true));
		output.append(accessToken.getToken() + "\n"
				+ accessToken.getTokenSecret());
		output.close();

	}

	private AccessToken loadAccessToken() throws IOException {
		BufferedReader read;
		try {
			read = new BufferedReader(new FileReader("twitter4j.password"));
			String token = read.readLine();
			String tokenSecret = read.readLine();
			read.close();
			return new AccessToken(token, tokenSecret);
		} catch (FileNotFoundException e) {
			return null;
		}

	}

	public DetailedAccount getAccount(long userId) throws TwitterException,
			MalformedURLException, IOException {
		User user = twitter.showUser(userId);
		String userName = user.getName();
		ImageIcon profilePicture = new ImageIcon(user.getProfileImageURL());
		int followers = user.getFollowersCount();
		int followings = user.getFriendsCount();
		int numberOfTweets = user.getStatusesCount();
		ArrayList<Tweet> tweets = createTweetList(userId, userName,
				profilePicture);
		DetailedAccount account = new DetailedAccount(profilePicture, userName,
				userId, tweets, followers, followings, numberOfTweets);

		boolean followStatus = twitter.showFriendship(twitter.getId(),
				user.getId()).isTargetFollowedBySource();
		boolean followRequestSent = user.isFollowRequestSent();
		boolean accountProtected = user.isProtected();
		account.setAccountProtected(accountProtected);
		account.setFollowRequestSent(followRequestSent);
		account.setFollowStatus(followStatus);
		return account;

	}

	private ArrayList<Tweet> createTweetList(long userId, String userName,
			ImageIcon profilePicture) throws TwitterException {
		ArrayList<Tweet> tweets = new ArrayList<Tweet>();
		List<Status> statuses = twitter.getUserTimeline(userId);

		for (Status status : statuses) {
			long tweetId = status.getId();
			String content = status.getText();
			Date createTime = status.getCreatedAt();
			Tweet currentTweet = new Tweet(userId, tweetId, userName, content,
					createTime, profilePicture);
			tweets.add(currentTweet);
		}
		return tweets;
	}

	public void logout() {
		File passwordFile = new File("twitter4j.password");
		passwordFile.delete();
	}

}
