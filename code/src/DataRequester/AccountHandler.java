package DataRequester;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.swing.ImageIcon;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class AccountHandler {
	Twitter twitter;

	public AccountHandler(Twitter twitter) {
		this.twitter = twitter;
		twitter = TwitterFactory.getSingleton();
	}

	public boolean loginTwitterFromStorage() throws 
			IOException {
		AccessToken accessToken = loadAccessToken();
		twitter.setOAuthAccessToken(accessToken);
		return true;
	}

	public boolean loginTwitterNewUser(RequestToken requestToken, String Pin)
			throws IOException {
		AccessToken accessToken;
		try {
			accessToken = createAccessToken(requestToken, Pin);
			
		} catch (TwitterException e) {
			return false;
		}
		twitter.setOAuthAccessToken(accessToken);
		storeAccessToken(accessToken);
		return true;
	}

	public RequestToken createRequestToken() throws TwitterException{
		RequestToken requestToken = twitter.getOAuthRequestToken();
		return requestToken;
	}

	private AccessToken createAccessToken(RequestToken requestToken, String Pin)
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
		return new DetailedAccount(profilePicture, userName, userId, tweets, followers, followings)
		
	}

	public void logout() {
		File passwordFile = new File("twitter4j.password");
		passwordFile.delete();
	}

}
