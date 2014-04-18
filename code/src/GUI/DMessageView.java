package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import GUIManager.DMessage;

@SuppressWarnings("serial")
public class DMessageView extends JPanel {

	private MainPanel mainPanel;
	private ArrayList<DMessage> directMessages;
	private JPanel messageListPanel;
	private JPanel newMessagePanel;

	public DMessageView(MainPanel mainPanel, ArrayList<DMessage> dMessages) {
		this.mainPanel = mainPanel;
		directMessages = dMessages;
		messageListPanel = null;
		newMessagePanel = null;
		calculateBounds();
		setLayout(new GridLayout(0, 1));
	}

	private void calculateBounds() {
		int xPos = mainPanel.getBounds().x;
		int yPos = mainPanel.getBounds().y;
		int width = mainPanel.getBounds().width;
		int height = mainPanel.getBounds().height;
		setBounds(xPos, yPos, width, height);
	}

	public void printDMessageView() {
		messageListPanel = new JPanel();
		messageListPanel.setLayout(null);
		messageListPanel.setBackground(Color.cyan);
		messageListPanel.add(getHeaderPanel());
		
		JPanel conversationListPanel = new JPanel();
		conversationListPanel.setBounds(getX(), getY() + getHeight() / 10, getWidth(), getHeight() - getHeight() / 10);
		conversationListPanel.setLocation(0, 0);
		conversationListPanel.setLayout(new GridLayout(0, 1));
		
		HashMap<String, ArrayList<DMessage>> conversationMap = new HashMap<String, ArrayList<DMessage>>();

		for (int i = 0; i < directMessages.size(); i++) {
			DMessage dMessage = directMessages.get(i);
			String peerScreenName = dMessage.getPeer().getScreenName();
			if (conversationMap.containsKey(peerScreenName)) {
				conversationMap.get(peerScreenName).add(dMessage);
			} else {
				ArrayList<DMessage> dMessages = new ArrayList<DMessage>();
				dMessages.add(dMessage);
				conversationMap.put(peerScreenName, dMessages);
			}
		}

		TreeMap<String, ArrayList<DMessage>> sortedConversationMap = new TreeMap<String, ArrayList<DMessage>>(
				new ConversationComparator(conversationMap));
		sortedConversationMap.putAll(conversationMap);
		
		Set<String> keys = sortedConversationMap.keySet();
		for (String key : keys) {
			JPanel conversationPanel = new JPanel();
			conversationPanel.add(new JLabel(key));
			conversationPanel.setBorder(new LineBorder(Color.black));
			conversationPanel.setBackground(null);
//			conversationListPanel.add(conversationPanel);
		}
		conversationListPanel.setBackground(Color.red);
		messageListPanel.add(conversationListPanel);

//		Set<String> keys = sortedConversationMap.keySet();
//		for (String key : keys) {
//			System.out.println("========================================================================");
//			System.out.println("Peer: " + key);
//			System.out.println("------------------------------------------------------------------------");
//			ArrayList<DMessage> dMessages = conversationMap.get(key);
//			for (int i = 0; i < dMessages.size(); i++) {
//				DMessage message = dMessages.get(i);
//				if (message.getIsSent()) {
//					System.out.print("You say: ");
//				} else {
//					System.out.print(message.getPeer().getScreenName() + " says: ");
//				}
//				System.out.print(message.getMessage());
//				System.out.print("\t Time: " + message.getDate());
//				System.out.println("\n------------------------------------------------------------------------");
//			}
//		}

		// System.out.println("NumDirectMessages: " + directMessages.size());
		//
		//
		// for (int i = 0; i < directMessages.size(); i++) {
		// System.out.println("--------------------------------------------------------------------------");
		// System.out.println(i + ". Direct Message:");
		// DMessage message = directMessages.get(i);
		// System.out.println(message.getIsSent() ? "Sent" : "Received");
		// System.out.println("Peer's Screen Name: " +
		// message.getPeer().getScreenName());
		// System.out.println("Text: " + message.getMessage());
		// System.out.println("DMessage is created at: " + message.getDate());
		// }

		add(messageListPanel);
		repaint();
		revalidate();
	}

	private JPanel getHeaderPanel() {
		JPanel headerPanel = new JPanel();
		headerPanel.setBounds(getX(), getY(), getWidth(), getHeight() / 10);
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

class ConversationComparator implements Comparator<String> {

	Map<String, ArrayList<DMessage>> conversationMap;

	public ConversationComparator(
			Map<String, ArrayList<DMessage>> conversationMap) {
		this.conversationMap = conversationMap;
	}

	@Override
	public int compare(String key1, String key2) {
		ArrayList<DMessage> dMessageList1 = conversationMap.get(key1);
		ArrayList<DMessage> dMessageList2 = conversationMap.get(key2);
		Date date1 = dMessageList1.get(0).getDate();
		Date date2 = dMessageList2.get(0).getDate();
		return date2.compareTo(date1);
	}

}
