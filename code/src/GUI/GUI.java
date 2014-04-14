package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;

import twitter4j.DirectMessage;
import twitter4j.ResponseList;
import twitter4j.TwitterException;
import DataRequester.DetailedAccount;
import DataRequester.Tweet;
import GUIManager.GUIManager;

@SuppressWarnings("serial")
public class GUI extends JFrame {

	private GUIManager guiManager;
	private MainPanel mainPanel;
	private LoginPanel loginPanel;
	private NavigationBar navigationBar;

	public GUI(GUIManager guiManager) {
		this.guiManager = guiManager;
		mainPanel = null;
		loginPanel = null;
		navigationBar = null;
		initFrame();
	}

	private void initFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Twitter Desktop Streamer");
		calculateBounds();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLayout(new GridBagLayout());
		getContentPane().setBackground(Color.WHITE);
		setResizable(false);
		setVisible(true);
	}

	private void calculateBounds() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) (screenSize.getWidth() / 4);
		int height = (int) screenSize.getHeight() - getTaskBarHeight();
		int yPos = 0;
		int xPos = (int) (screenSize.getWidth() - width);
		setBounds(xPos, yPos, width, height);
	}

	private int getTaskBarHeight() {
		int taskBarHeight = Toolkit.getDefaultToolkit().getScreenInsets(
				getGraphicsConfiguration()).bottom;
		return taskBarHeight;
	}

	public void loginButtonClicked() {
		guiManager.loginButtonClicked();
	}

	public void printLoginPanel() {
		getContentPane().removeAll();
		if (loginPanel == null)
			loginPanel = new LoginPanel(this);
		loginPanel.printLoginPanel();
		add(loginPanel);
		getContentPane().repaint();
		getContentPane().validate();
	}

	public void pinEntered() {
		guiManager.pinEntered();
	}

	public void printPINInputPanel() {
		getContentPane().removeAll();
		loginPanel.printPINInputPanel();
		add(loginPanel);
		getContentPane().repaint();
		getContentPane().validate();
	}

	public void backToLoginButtonClicked() {
		guiManager.backToLoginButtonClicked();
	}

	public String getPIN() {
		return loginPanel.getPIN();
	}

	public void printMainPanel(ArrayList<Tweet> tweets) {
		getContentPane().removeAll();
		getContentPane().setLayout(new BorderLayout());
		if (navigationBar == null)
			navigationBar = new NavigationBar(this);
		if (mainPanel == null)
			mainPanel = new MainPanel(this);
		
		
		getContentPane().add(navigationBar, BorderLayout.NORTH);
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		printTimeline(tweets);
		getContentPane().repaint();
		getContentPane().validate();
	}

	public void printTimeline(ArrayList<Tweet> tweets) {
		mainPanel.printTimeline(tweets);
	}


	public void tweetEntered() {
		guiManager.postTweet();
	}
	public void printMentions(ArrayList<Tweet> tweets) {
		mainPanel.printMentions(tweets);
	}

	public String getTweet() {
		return mainPanel.getTweet();
	}

	public void meButtonClicked() {
		guiManager.meButtonClicked();
	}

	public void printProfile(DetailedAccount account) {
		mainPanel.printProfile(account);

	}

	public ResponseList<DirectMessage> getDirectMessages() {
		return guiManager.getDirectMessages();
	}

	public void homeButtonClicked() throws TwitterException {
		guiManager.homeButtonClicked();
		
	}

	public void mentionsButtonClicked() throws TwitterException {
		guiManager.mentionButtonClicked();
		
	}

}
