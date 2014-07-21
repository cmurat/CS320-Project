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
import java.util.List;

import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class AccountHandler extends TwitterConnection {
	private static AccountHandler accountHandler;

	private RequestToken requestToken;
	private AccessToken accessToken;
	private User currentUser;

	
	public static AccountHandler getInstance(){
		if(accountHandler==null)
			accountHandler = new AccountHandler();
		return accountHandler;
	}
	

	public boolean loginTwitterFromStorage(){
		accessToken = loadAccessToken();
		if (accessToken == null)
			return false;
		try {
			twitter.setOAuthAccessToken(accessToken);
			currentUser = twitter.verifyCredentials();
			System.out.println(1);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	public boolean isFollowedBy(String screenName) {
		boolean isFollowed = false;
		try {
			 isFollowed = twitter.showFriendship(currentUser.getScreenName(), screenName).isSourceFollowedByTarget();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isFollowed;
		
		
	}

	public boolean loginTwitterNewUser(String Pin){
		accessToken = createAccessToken(Pin);
		twitter.setOAuthAccessToken(accessToken);
		try {
			currentUser = twitter.verifyCredentials();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		storeAccessToken(accessToken);
		return true;
	}

	private AccessToken createAccessToken(String Pin) {
		AccessToken access = null;
		try {
			access = twitter.getOAuthAccessToken(requestToken, Pin);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return access;
	}
	
	public long getAccountUserID(){
		return currentUser.getId();
	}

	public String createRequestTokenURL() {
		try {
			requestToken = twitter.getOAuthRequestToken();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return requestToken.getAuthenticationURL();
	}

	private void storeAccessToken(AccessToken accessToken){
		Writer output;
		try {
			output = new BufferedWriter(new FileWriter("twitter4j.password", true));
			output.append(accessToken.getToken() + "\n"	+ accessToken.getTokenSecret());
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setProfilePicture(File image) {
		try {
			twitter.updateProfileImage(image);
		} catch (TwitterException e) {
			System.out.println("\nProfile Picture couldnt set");
			e.printStackTrace();
		}

	}

	public void setProfileName(String screenName) {
		try {
			twitter.updateProfile(screenName, currentUser.getURL(),
					currentUser.getLocation(), currentUser.getDescription());
			currentUser = twitter.verifyCredentials();
		} catch (TwitterException e) {
			System.out.println("\nProfile name couldnt set");
			e.printStackTrace();
		}
	}

	private AccessToken loadAccessToken(){
		BufferedReader read;
		try {
			read = new BufferedReader(new FileReader("twitter4j.password"));
			String token = read.readLine();
			String tokenSecret = read.readLine();
			read.close();
			return new AccessToken(token, tokenSecret);
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			
			e.printStackTrace();
			return null;
		}

	}

	public DetailedAccount getHomeAccount() {
		ArrayList<Tweet> tweets = null;

		try {
			currentUser = twitter.verifyCredentials();
			tweets = createTweetList(currentUser.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		DetailedAccount account = new DetailedAccount(currentUser, tweets);
		return account;
	}

	public DetailedAccount getDetailedAccount(long userId) {
		User user = null;
		ArrayList<Tweet> tweets = null;

		try {
			user = twitter.showUser(userId);
			tweets = createTweetList(user.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		DetailedAccount account = new DetailedAccount(user, tweets);
		setupAccount(account, user);
		return account;

	}

	private void setupAccount(DetailedAccount account, User user) {
		boolean followStatus = false;
		try {
			followStatus = twitter
					.showFriendship(twitter.getId(), user.getId())
					.isSourceFollowingTarget();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		account.setAccountProtected(user.isProtected());
		account.setFollowRequestSent(user.isFollowRequestSent());
		account.setFollowStatus(followStatus);
	}

	private ArrayList<Tweet> createTweetList(long userId){
		ArrayList<Tweet> tweets = new ArrayList<Tweet>();
		List<Status> statuses = null;
		try {
			statuses = twitter.getUserTimeline(userId);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Status status : statuses) {
			Tweet currentTweet = new Tweet(status);
			tweets.add(currentTweet);
		}
		return tweets;
	}

	public void logout() {
		File passwordFile = new File("twitter4j.password");
		passwordFile.delete();
		System.out.println("succesfully logOut");
		System.exit(0);

	}

	public String getAccountUserScreenName() {
		return currentUser.getScreenName();
	}

}
