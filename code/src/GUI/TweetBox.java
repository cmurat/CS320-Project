package GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.TextField;

import javax.swing.JPanel;

public class TweetBox extends JPanel{
	
	private MainPanel mainPanel;
	private TextField tweetField;
	
	public TweetBox(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
		init();
	}
	
	private void init() {
		setMaximumSize(new Dimension(mainPanel.getWidth(), mainPanel.getHeight()/20));
		setBounds(mainPanel.getBounds());
		setLayout(new FlowLayout());
		addTweetField();
	}
	
	private void addTweetField() {
		tweetField = new TextField();
		tweetField.setBounds(getBounds());
		tweetField.setText("Sev Beni");
	}
	
	public String getTweetText(){
	}
	
}
