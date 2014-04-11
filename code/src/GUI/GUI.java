package GUI;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import DataRequester.Tweet;
import GUIManager.GUIManager;

public class GUI {

	private GUIManager guiManager;
	private MainFrame mainFrame;
	private LoginPage loginPage;

	// {xPos, yPos, width, height}
	private int[] bounds;

	public GUI(GUIManager guiManager) {
		this.guiManager = guiManager;
		mainFrame = null;
		loginPage = null;
		calculateBounds();
	}

	private void calculateBounds() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) (screenSize.getWidth() / 4);
		int height = (int) screenSize.getHeight();
		int yPos = 0;
		int xPos = (int) (screenSize.getWidth() - width);
		bounds = new int[]{xPos, yPos, width, height};
	}

	public void setBounds(int[] bounds) {
		this.bounds = bounds;
	}

	public int[] getBounds() {
		return bounds;
	}

	public void loginButtonClicked() {
		//		guiManager.loginButtonClicked();
	}

	public void printLoginPage() {
		loginPage = new LoginPage(this);
		loginPage.printLoginPage();
	}

	public void printLoginButton() {
		loginPage.printLoginPage();
	}

	public void pinEntered() {
		//		guiManager.pinEntered();
	}

	public void printPINField() {
		loginPage.printPINField();
	}

	public void backToLoginButtonClicked() {
		loginPage.printLoginPage();
	}

	public String getPIN() {
		return loginPage.getPIN();
	}

	public void printMainFrame(ArrayList<Tweet> tweets) {
		mainFrame = new MainFrame();
		printTimeline(tweets);
	}

	public void printTimeline(ArrayList<Tweet> tweets) {
		mainFrame.printTimeline(tweets);
	}

}
