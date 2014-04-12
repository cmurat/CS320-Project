package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class TweetBox extends JPanel{
	
	private MainPanel mainPanel;
	private TextField tweetField;
	
	public TweetBox(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
		setLayout(new BorderLayout());
		calculateBounds();
		addTweetField();
	}
	
	private void calculateBounds() {
		int width = mainPanel.getBounds().width;
		int height = mainPanel.getBounds().height/10;
		int xPos = mainPanel.getBounds().x;
		int yPos = mainPanel.getBounds().height - height;
		setBounds(xPos, yPos, width, height);
	}
	
	private void addTweetField() {
		tweetField = new TextField();
		tweetField.setText("Write a tweet, Press Enter..");
		tweetField.addFocusListener(getFocusAdapter());
		tweetField.addKeyListener(getEnterKeyAdapter());
		add(tweetField);
	}

	private FocusAdapter getFocusAdapter() {
		return new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (tweetField.getText().equals("Write a tweet, Press Enter.."))
					tweetField.setText("");
			}
		};
	}

	private KeyAdapter getEnterKeyAdapter() {
		return new KeyAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void keyReleased(KeyEvent event) {
				if (event.getKeyCode() == KeyEvent.VK_ENTER) {
					mainPanel.tweetEntered();
					tweetField.setText("Write a tweet, Press Enter..");
					tweetField.transferFocusUpCycle();
				}
			}
		};
	}

	public String getTweet() {
		return tweetField.getText();
	}
	
}
