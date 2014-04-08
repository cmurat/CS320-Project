package DataRequester;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
	public AccountHandler(Twitter twitter){
		this.twitter = twitter;
	}
	public void loginToTwitter() throws TwitterException, IOException {
		twitter = TwitterFactory.getSingleton();
		AccessToken accessToken = loadAccessToken();
		if(accessToken==null){
			accessToken = getAccessCode();
			storeAccessToken(accessToken);
		}
		twitter.setOAuthAccessToken(accessToken);
	}

	private AccessToken getAccessCode()
			throws TwitterException, IOException {
		AccessToken accessToken = null;
		RequestToken requestToken = twitter.getOAuthRequestToken();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (null == accessToken) {
			System.out
			.println("Open the following URL and grant access to your account:");
			System.out.println(requestToken.getAuthorizationURL());
			System.out
			.print("Enter the PIN(if aviailable) or just hit enter.[PIN]:");
			String pin = br.readLine();
			try {
				if (pin.length() > 0) {
					accessToken = twitter
							.getOAuthAccessToken(requestToken, pin);
				} else {
					accessToken = twitter.getOAuthAccessToken();
				}
			} catch (TwitterException te) {
				if (401 == te.getStatusCode()) {
					System.out.println("Unable to get the access token.");
				} else {
					te.printStackTrace();
				}
			}
		}
		return accessToken;
	}

	private void storeAccessToken(AccessToken accessToken)
			throws IOException {
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

}
