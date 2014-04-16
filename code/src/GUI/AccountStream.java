package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import DataRequester.Account;
import GUI.listeners.UserNameListener;

@SuppressWarnings("serial")
public class AccountStream extends JPanel {
	private MainPanel mainPanel;
	public AccountStream(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
		setLayout(new GridLayout(0, 1));

		setBackground(Color.white);
	}
	public JScrollPane printAccounttream(ArrayList<Account> accounts) {
		printAccounts(accounts);
		System.out.println("\nAssume AccountStream is painted.");
		JScrollPane tweetPane = new JScrollPane(this);
		tweetPane.setOpaque(true);
		tweetPane.getVerticalScrollBar().setUnitIncrement(16);
		tweetPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		tweetPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		return tweetPane;
	}
	private void printAccounts(ArrayList<Account> accounts) {
		for (Account account : accounts) {			
			JPanel imagePanel = new JPanel();
			imagePanel.setBackground(Color.WHITE);
			imagePanel.setOpaque(true);
			
			JPanel contentPanel = new JPanel();
			contentPanel.setBackground(Color.WHITE);
			contentPanel.setOpaque(true);
			contentPanel.setLayout(new GridLayout(2,1));
			
			addUserImage(account, imagePanel);
			addUserName(account, contentPanel);

			JPanel tweetPanel = new JPanel();
			tweetPanel.setLayout(new BorderLayout());
			tweetPanel.add(imagePanel,BorderLayout.WEST);
			tweetPanel.add(contentPanel,BorderLayout.CENTER);
			add(tweetPanel);
		}
	}

	private void addUserImage(Account account, JPanel imagePanel) {
		URL imageURL = null;
		try {
			imageURL = new URL(account.getProfilePictureURL());
		} catch (MalformedURLException e) {
			System.err.println("couldn't locate image");
		}
		JLabel userImage = new JLabel(new ImageIcon(imageURL));
		imagePanel.add(userImage);
	}

	private void addUserName(Account account, JPanel contentPanel) {
		JButton userName = new JButton("" + account.getUserName());
		userName.setBorderPainted(false);
		userName.setFocusable(false);
		userName.setContentAreaFilled(false);
		userName.setHorizontalAlignment(JButton.LEFT);
		userName.addActionListener(new UserNameListener(account.getUserID(),userName,mainPanel));
		userName.setCursor(new Cursor(Cursor.HAND_CURSOR));
		contentPanel.add(userName);
	}

}
