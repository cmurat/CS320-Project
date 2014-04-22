package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class TweetBox extends JPanel {
	private final int MAX_CHAR_LIMIT = 140;
	private MainPanel mainPanel;
	private JTextArea tweetField;

	public TweetBox(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
		setLayout(new BorderLayout());
		addTweetField();
	}

	private void addTweetField() {
		tweetField = new JTextArea("Write a tweet, Press Enter..");
		tweetField.setColumns(MAX_CHAR_LIMIT);
		tweetField.setLineWrap(true);
		tweetField.setWrapStyleWord(false);
		tweetField.setOpaque(false);
		tweetField.setBorder(new LineBorder(Color.black));

		tweetField.addFocusListener(getFocusAdapter());
		tweetField.addKeyListener(getEnterKeyAdapter());
		tweetField.addKeyListener(checkMaxCharLimit());

		add(tweetField);
	}

	private FocusAdapter getFocusAdapter() {
		return new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (tweetField.getText().equals("Write a tweet, Press Enter.."))
					tweetField.setText("");
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				if (tweetField.getText().equals("")) {
					tweetField.setText("Write a tweet, Press Enter..");
				}
			}
		};
	}

	private KeyAdapter getEnterKeyAdapter() {
		return new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent event) {
				if (event.getKeyCode() == KeyEvent.VK_ENTER) {
					if(tweetField.getText().trim().length() > 0)
						mainPanel.tweetEntered();
					tweetField.setText("Write a tweet, Press Enter..");
					tweetField.transferFocusUpCycle();
				}
			}
		};
	}

	private KeyAdapter checkMaxCharLimit() {
		return new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (tweetField.getText().length() >= MAX_CHAR_LIMIT) {
					tweetField.setText(tweetField.getText().substring(0, MAX_CHAR_LIMIT));
				}
			}
		};
	}

	public String getTweet() {
		return tweetField.getText();
	}
	public void setTweetField(String t){
		tweetField.setText(t);
	}

}
