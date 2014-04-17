package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import twitter4j.DirectMessage;
import twitter4j.ResponseList;

@SuppressWarnings("serial")
public class DMessageView extends JPanel {
	
	private MainPanel mainPanel;
	private ResponseList<DirectMessage> directMessages;
	private JPanel messageListPanel;
	private JPanel newMessagePanel;
	
	public DMessageView(MainPanel mainPanel, ResponseList<DirectMessage> responseList){
		this.mainPanel = mainPanel;
		directMessages = responseList;
		messageListPanel = null;
		newMessagePanel = null;
		calculateBounds();
		setLayout(new GridLayout(0, 1));
	}
	
	private void calculateBounds() {
		int xPos = mainPanel.getBounds().x;
		int yPos = mainPanel.getBounds().y + mainPanel.getBounds().height / 10;
		int width = mainPanel.getBounds().width;
		int height = mainPanel.getBounds().height - 2 * yPos;
		setBounds(xPos, yPos, width, height);
	}
	

	public void printDMessageView() {
		messageListPanel = new JPanel();
		messageListPanel.setLayout(null);
		messageListPanel.setBackground(Color.cyan);
		messageListPanel.add(getHeaderPanel());
		
//		ArrayList<JPanel> conversations = new ArrayList<JPanel>();
//		
//		for (int i = 0; i < directMessages.size(); i++) {
//			DirectMessage message = directMessages.get(i);
//			
//		}
		
		System.out.println("NumDirectMessages: " + directMessages.size());
		
		
		for (int i = 0; i < directMessages.size(); i++) {
			System.out.println("--------------------------------------------------------------------------");
			System.out.println(i + ". Direct Message:");
			DirectMessage message = directMessages.get(i);
			System.out.println("Access Level: " + message.getAccessLevel());
			System.out.println("DMessage ID: " + message.getId());
			System.out.println("Recipient ID: " + message.getRecipientId());
			System.out.println("Recipient's Screen Name: " + message.getRecipientScreenName());
			System.out.println("Sender ID: " + message.getSenderId());
			System.out.println("Sender's Screen Name: " + message.getSenderScreenName());
			System.out.println("Text: " + message.getText());
			System.out.println("DMessage is created at: " + message.getCreatedAt());
		}
		
		add(messageListPanel);
		repaint();
		revalidate();
	}

	private JPanel getHeaderPanel() {
		JPanel headerPanel = new JPanel();
		headerPanel.setBounds(getX(), getY(), getWidth(), getHeight() / 9);
		headerPanel.setLocation(0, 0);
		headerPanel.setLayout(new BorderLayout());
		JTextField dMessageField = new JTextField("  Direct Message");
		dMessageField.setEditable(false);
		dMessageField.setFocusable(false);
		dMessageField.setBackground(null);
		dMessageField.setBorder(null);
		JButton newDMessageButton = new JButton("New Message");
		newDMessageButton.setBorderPainted(false);
		newDMessageButton.setFocusPainted(false);
		headerPanel.add(dMessageField, BorderLayout.WEST);
		headerPanel.add(newDMessageButton, BorderLayout.EAST);
		return headerPanel;
	}
}
