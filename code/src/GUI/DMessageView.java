package GUI;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import twitter4j.DirectMessage;
import twitter4j.ResponseList;
import twitter4j.User;

@SuppressWarnings("serial")
public class DMessageView extends JPanel {
	private MainPanel mainPanel;
	private ResponseList<DirectMessage> directMessages;
	
	public DMessageView(MainPanel mainPanel){
		this.mainPanel = mainPanel;
		directMessages = getDirectMessages();
		mainPanel.add(this);
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
		printDirectMessages();
	}

	private void printDirectMessages() {
		for (DirectMessage directMessage : directMessages) {
			User user = directMessage.getRecipient();
			JLabel userImage = new JLabel(new ImageIcon(user.getProfileImageURL()));
			JLabel userName = new JLabel("" + user.getName());
			JLabel date = new JLabel("" + directMessage.getCreatedAt());
			JLabel messageContent = new JLabel(directMessage.getText());

			JPanel messageHeaderPanel = new JPanel(new FlowLayout());
			messageHeaderPanel.add(userName);
			messageHeaderPanel.add(date);

			JPanel messageContentPanel = new JPanel(new GridLayout(2, 1));
			messageContentPanel.add(messageHeaderPanel, -1);
			messageContentPanel.add(messageContent, -1);

			JPanel messagePanel = new JPanel(new FlowLayout());
			messagePanel.add(userImage, -1);
			messagePanel.add(messageContentPanel, -1);

			add(messagePanel);
		}
	}

	public ResponseList<DirectMessage> getDirectMessages(){
		return mainPanel.getDirectMessages();
	}
	
	public void printDirectMessageView() {
		printDirectMessages();
		mainPanel.revalidateGUI();
	}
}
