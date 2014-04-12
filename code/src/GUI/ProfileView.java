package GUI;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import DataRequester.Account;

public class ProfileView implements MainContent {
	
	private MainPanel mainPanel;
	private ArrayList<JLabel> labelList;
	private Account account;
	
	public ProfileView(MainPanel mainPanel, Account account){
		this.mainPanel = mainPanel;
		this.account = account;
		labelList = new ArrayList<JLabel>();
	}
	
	private void addProfilePicture() throws IOException{
		JLabel profilePicture = new JLabel(account.getProfilePicture());
		labelList.add(profilePicture);
	}
	
	private void addProfileName(){
		JLabel profileName = new JLabel(account.getUserName());
		labelList.add(profileName);
	}
	
	private void addTweetsLabel(){
		JLabel tweets = new JLabel("Tweets");
		labelList.add(tweets);
	}
	
	private void addFollowingsLabel(){
		JLabel followings = new JLabel("Followings");
		labelList.add(followings);
	}
	
	private void addFollowersLabel(){
		JLabel followers = new JLabel("Followers");
		labelList.add(followers);
	}
	
	private void addTweetNumberLabel(){
		JLabel tweetNumber = new JLabel("" + account.getTweetNumber());
		labelList.add(tweetNumber);
	}
	
	/*private void addFollowingNumberLabel(){
		JLabel followingNumber = new JLabel("" + account.getTweetNumber());
		labelList.add(followingNumber);
	}
	
	private void addTFollowerNumberLabel(){
		JLabel followerNumber = new JLabel("" + account.getTweetNumber());
		labelList.add(followerNumber);
	}
	*/ // will create method to get numbers
	
	
	
	
	
//	private Account account;	// TODO Account class must be implemented!

}
