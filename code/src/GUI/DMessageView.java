package GUI;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import DataRequester.DetailedAccount;

// TODO uncomment when DirectMessage class is implemented.
public class DMessageView extends JPanel implements MainContent {
	private MainPanel mainPanel;
	private DetailedAccount account;
//	private ArrayList<DirectMessage> directMessages;
	
	public DMessageView(MainPanel mainPanel, DetailedAccount account){
		this.mainPanel = mainPanel;
		this.account = account;
//		directMessages = new ArrayList<DirectMessage>();
		calculateBounds();
	}
	
	private void calculateBounds() {
		int xPos = mainPanel.getBounds().x;
		int yPos = mainPanel.getBounds().y + mainPanel.getBounds().height / 10;
		int width = mainPanel.getBounds().width;
		int height = mainPanel.getBounds().height - 2 * yPos;
		setBounds(xPos, yPos, width, height);
	}

	public void printDirectMessageStream(){
		addDirectMessages();
		printDirectMessages();
	}

	private void addDirectMessages() {
//		this.directMessages.addAll(account.getDirectMessage());
	}

	private void printDirectMessages() {
//		for (DirectMessage dm : directMessages) {
//			JLabel userImage = new JLabel(dm.getUserImage());
//			JLabel userName = new JLabel("" + dm.getUserName());
//			JLabel date = new JLabel("" + dm.getDate());
//			JLabel messageContent = new JLabel(dm.getContent());
//
//			JPanel messageHeaderPanel = new JPanel(new FlowLayout());
//			messageHeaderPanel.add(userName);
//			messageHeaderPanel.add(date);
//
//			JPanel messageContentPanel = new JPanel(new GridLayout(2, 1));
//			messageContentPanel.add(messageHeaderPanel, -1);
//			messageContentPanel.add(messageContent, -1);
//
//			JPanel messagePanel = new JPanel(new GridLayout(1, 2));
//			messagePanel.add(userImage, -1);
//			messagePanel.add(messageContentPanel, -1);
//
//			add(messagePanel);
//		}
	}

	
	
}
