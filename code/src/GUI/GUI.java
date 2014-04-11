package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;

import DataRequester.Tweet;
import GUIManager.GUIManager;

@SuppressWarnings("serial")
public class GUI extends JFrame {

	private GUIManager guiManager;
	private MainPanel mainPanel;
	private LoginPanel loginPanel;

	public GUI(GUIManager guiManager) {
		this.guiManager = guiManager;
		mainPanel = null;
		loginPanel = null;
		initFrame();
	}

	private void initFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Twitter Desktop Streamer");
		calculateBounds();
		setLayout(new GridBagLayout());
		getContentPane().setBackground(Color.WHITE);
		setResizable(false);
		setVisible(true);
	}

	private void calculateBounds() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) (screenSize.getWidth() / 4);
		int height = (int) screenSize.getHeight();
		int yPos = 0;
		int xPos = (int) (screenSize.getWidth() - width);
		setBounds(xPos, yPos, width, height);
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
		getContentPane().validate();
	}

	public void printLoginButton() {
		printLoginPanel();
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
		loginPanel.printLoginPanel();
	}

	public String getPIN() {
		return loginPanel.getPIN();
	}

	public void printMainPanel(ArrayList<Tweet> tweets) {
		getContentPane().removeAll();
		if (mainPanel == null)
			mainPanel = new MainPanel(this);
		add(mainPanel);
		getContentPane().repaint();
		getContentPane().validate();
		printTimeline(tweets);
	}

	public void printTimeline(ArrayList<Tweet> tweets) {
		mainPanel.printTimeline(tweets);
	}

}
