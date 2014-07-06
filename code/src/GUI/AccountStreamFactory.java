package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import DataRequester.Account;
import GUIListeners.UserNameListener;

@SuppressWarnings("serial")
public class AccountStreamFactory {
	
	public static JScrollPane printAccounttream(ArrayList<Account> accounts, MainPanel mainPanel) {
		JPanel accountPanel = new JPanel();
		accountPanel.setLayout(new BoxLayout(accountPanel, BoxLayout.PAGE_AXIS));
		printAccounts(accounts,accountPanel,mainPanel);
		System.out.println("\nAssume AccountStream is painted.");
		
		JScrollPane accountStream = new JScrollPane(accountPanel);
		accountStream.setOpaque(true);
		accountStream.getVerticalScrollBar().setUnitIncrement(16);
		accountStream.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		accountStream.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		return accountStream;
	}

	private static void printAccounts(ArrayList<Account> accounts, JPanel accountPanel, MainPanel mainPanel) {
		for (Account account : accounts) {
			JPanel imagePanel = new JPanel();
			imagePanel.setBackground(Color.WHITE);
			imagePanel.setOpaque(true);
			JPanel contentPanel = new JPanel();
			contentPanel.setBackground(Color.WHITE);
			contentPanel.setOpaque(true);
			contentPanel.setLayout(new GridLayout(2, 1));

			addUserImage(account, imagePanel);
			addUserName(account, contentPanel,mainPanel);

			JPanel tweetPanel = new JPanel();
			tweetPanel.setLayout(new BorderLayout());
			tweetPanel.add(imagePanel, BorderLayout.WEST);
			tweetPanel.add(contentPanel, BorderLayout.CENTER);
			accountPanel.add(tweetPanel);
		}
	}

	private static void addUserImage(Account account, JPanel imagePanel) {
		URL imageURL = null;
		try {
			imageURL = new URL(account.getProfilePictureURL());
		} catch (MalformedURLException e) {
			System.err.println("couldn't locate image");
		}
		JLabel userImage = new JLabel(new ImageIcon(imageURL));
		imagePanel.add(userImage);
	}

	private static void addUserName(Account account, JPanel contentPanel, MainPanel mainPanel) {
		JButton userName = new JButton(account.getUserName());
		userName.setBorderPainted(false);
		userName.setFocusable(false);
		userName.setContentAreaFilled(false);
		userName.setHorizontalAlignment(JButton.LEFT);
		userName.addActionListener(new UserNameListener(account.getUserID(), userName, mainPanel));
		userName.setCursor(new Cursor(Cursor.HAND_CURSOR));
		contentPanel.add(userName);
	}

}
