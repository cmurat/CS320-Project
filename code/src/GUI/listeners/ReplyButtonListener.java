package GUI.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

import DataRequester.Tweet;
import GUI.MainPanel;
import GUI.TweetBox;

public class ReplyButtonListener implements ActionListener {
	private TweetBox tweetBox;
	private Tweet tweet;
	private MainPanel mainPanel;
	public ReplyButtonListener(MainPanel mainPanel, TweetBox tweetBox, Tweet tweet) {
		this.tweet =tweet;
		this.tweetBox = tweetBox;
		this.mainPanel = mainPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		StringTokenizer token = new StringTokenizer(tweet.getContent());
		String replyText = "@"+tweet.getScreenName()+ " ";
		while(token.hasMoreTokens()){
			String currentToken = token.nextToken();
			if(ifMention(currentToken)) {
				System.out.println(currentToken);
				if(ifCurrentUser(currentToken))
					continue;
				String plainAlphaNumbericReprentation = currentToken.replaceAll("[^\\p{L}\\p{Nd}\\_]", "");
				replyText+= "@"+plainAlphaNumbericReprentation+ " ";
			}
		}
		tweetBox.setTweetField(replyText);


	}

	private boolean ifCurrentUser(String currentToken) {
		return (currentToken.substring(1)).equals(mainPanel.getCurrentUserScreenName());
	}

	private boolean ifMention(String currentToken) {
		return currentToken.charAt(0)=='@'&&currentToken.charAt(1) != '@';
	}

}
