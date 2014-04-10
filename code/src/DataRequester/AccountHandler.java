package DataRequester;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class AccountHandler {
	Twitter twitter;
	private RequestToken requestToken;

	public AccountHandler(Twitter twitter) {
		this.twitter = twitter;
	}

	public boolean loginTwitterFromStorage() throws 
			IOException {
		AccessToken accessToken = loadAccessToken();
		twitter.setOAuthAccessToken(accessToken);
		return true;
	}

	public boolean loginTwitterNewUser(String Pin)
			throws IOException {
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

	public String createRequestTokenURL() throws TwitterException{
		requestToken = twitter.getOAuthRequestToken();
		return requestToken.getAuthenticationURL();
	}

	private AccessToken createAccessToken(String Pin)
			throws TwitterException {
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
	public DetailedAccount getAccount(long userId) throws TwitterException{
		User user = twitter.showUser(userId);
		String userName = user.getName();
		ImageIcon profilePicture = new ImageIcon(user.getProfileImageURL());
		int followers = user.getFollowersCount();
		int followings = user.getFriendsCount();
		int numberOfTweets = user.getStatusesCount();
		List<Status> statuses = twitter.getUserTimeline(userId);
		ArrayList<Tweet> tweets = new ArrayList<Tweet>();
		for (Status status : statuses) {
			long tweetId = status.getId();
			String content = status.getText();
			Date createTime = status.getCreatedAt();
			ImageIcon profileImage = new ImageIcon(status.getUser()
					.getProfileImageURL());
			Tweet currentTweet = new Tweet(userId, tweetId, userName, content,
					createTime, profileImage);
			tweets.add(currentTweet);
		}
		return new DetailedAccount(profilePicture, userName, userId, tweets, followers, followings,numberOfTweets);
		
	}

	public void logout() {
		File passwordFile = new File("twitter4j.password");
		passwordFile.delete();
	}

}
