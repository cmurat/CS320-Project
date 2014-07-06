package GUIListeners.TweetListeners;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import DataRequester.Tweet;
import GUI.MainPanel;

public class FavouriteButtonListener implements ActionListener {
	private long tweetId;
	private JButton favButton;
	private MainPanel mainPanel;
	private Tweet tweet;
	private boolean favStatus;

	public FavouriteButtonListener(long tweetId, JButton favButton, MainPanel mainPanel, Tweet tweet) {
		this.tweetId = tweetId;
		this.favButton = favButton;
		this.mainPanel = mainPanel;
		this.tweet=tweet;
		this.favStatus = tweet.isFavorited();
	}

	public void actionPerformed(ActionEvent e) {
		String address="icon/FavoriteIcon.png";
		System.out.println("\n favourite is clicked!");
		favButton.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		if(favStatus){
			address="icon/FavoriteIconBefore.png";
			mainPanel.unFavoriteButtonClicked(tweetId);
			favStatus = false;;
		}
		else{
			mainPanel.favoriteButtonClicked(tweetId);
			favStatus= true;
		}
		Image img = new ImageIcon(address).getImage();
		img = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		favButton.setIcon(new ImageIcon(img));
		favButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	
	}


