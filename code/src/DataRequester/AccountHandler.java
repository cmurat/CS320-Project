package DataRequester;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class AccountHandler {
	Twitter twitter;

	public AccountHandler(Twitter twitter) {
		this.twitter = twitter;
		twitter = TwitterFactory.getSingleton();
	}

	public boolean loginTwitterFromStorage() throws TwitterException, IOException {
		AccessToken accessToken = loadAccessToken();
		if (accessToken == null) {
			return false;
		}
		twitter.setOAuthAccessToken(accessToken);
		return true;
	}
	public void loginTwitterNewUser(RequestToken requestToken, String Pin) throws TwitterException, IOException{
		AccessToken accessToken = createAccessToken(requestToken,Pin);
		storeAccessToken(accessToken);
		twitter.setOAuthAccessToken(accessToken);
	}

	public RequestToken createRequestToken() throws TwitterException, IOException {
		RequestToken requestToken = twitter.getOAuthRequestToken();
		return requestToken;
	}
	
	private AccessToken createAccessToken(RequestToken requestToken, String Pin) throws TwitterException{
		AccessToken access = null;
		access = twitter.getOAuthAccessToken(requestToken, Pin);
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
	public void logout(){
		File passwordFile = new File("twitter4j.password");
		passwordFile.delete();	
	}

}
