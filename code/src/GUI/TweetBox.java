package GUI;

import java.awt.BorderLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class TweetBox extends JPanel {

	private MainPanel mainPanel;
	private JTextArea tweetField;

	public TweetBox(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
		setLayout(new BorderLayout());
		calculateBounds();
		addTweetField();
	}

	private void calculateBounds() {
		int width = mainPanel.getBounds().width;
		int height = mainPanel.getBounds().height / 10;
		int xPos = mainPanel.getBounds().x;
		int yPos = mainPanel.getBounds().height - height;
		setBounds(xPos, yPos, width, height);
	}

	private void addTweetField() {
		tweetField = new JTextArea();
		tweetField.setColumns(140);
		tweetField.setLineWrap(true);
		tweetField.setWrapStyleWord(false);
		tweetField.setOpaque(false);
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
